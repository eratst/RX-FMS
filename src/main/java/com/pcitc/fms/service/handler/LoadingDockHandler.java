package com.pcitc.fms.service.handler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.service.model.LoadingDock;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.baseresrep.Link;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


/*
 * 
*<p>Title: LoadingDockHandler<p>
*<p>Description: 装卸台handler</p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年7月12日
 */
@Controller
public class LoadingDockHandler extends BaseHandler{

	private static Logger log = LoggerFactory.getLogger(LoadingDockHandler.class);
	@Autowired
	private LoadingDockService loadingDockService;
	@Autowired
	private ProduceFactoryService produceFactoryService;
	@Autowired
	private TPmOrgDao tPmOrgDao;
	@Autowired
	private AreaDictGatherService areaDictService;
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	@Autowired
	private CheckType checkType;

	public void getLoadingDockes(RoutingContext routingContext){

		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.LoadingDock> loadingDockEntity = new ArrayList<>();
				try {
					LoadingDock loadingDockModel = QueryParams.getQueryParams(routingContext, LoadingDock.class);
					Pageable pageable = null;
					if (loadingDockModel.getTop()!=null && loadingDockModel.getSkip()==null) {
						loadingDockModel.setSkip(0);
					}
					if (loadingDockModel.getSkip() != null && loadingDockModel.getTop() != null){
						pageable = PageUtil.pageable(loadingDockModel.getTop(),loadingDockModel.getSkip(), null);
					}
					if(loadingDockModel.getOrgCode()!=null){
						checkType.checkOrg(loadingDockModel.getOrgCode());
					}
					//开始查询
					Pager<com.pcitc.fms.bll.entity.LoadingDock> sourceData = loadingDockService.getLoadingDocksByModel(loadingDockModel, pageable);

					if (routingContext.request().uri().contains("skip") && loadingDockModel.getSkip() != null
							&& loadingDockModel.getTop() == null) {
						if (loadingDockModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<loadingDockModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					loadingDockEntity = sourceData.getContent();
					List<LoadingDock> modelLoadingDocks = ObjectConverter.listConverter(loadingDockEntity, LoadingDock.class);
					future.complete(modelLoadingDocks);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					try {
					List<LoadingDock> targets = (List<LoadingDock>) res.result();
					String linkNames = "outlets,valves,equipments,tees,tubulations,plates,edgePoints,loadPoints";
					targets = (List<LoadingDock>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
					
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								LoadingDock.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	
	}
}
