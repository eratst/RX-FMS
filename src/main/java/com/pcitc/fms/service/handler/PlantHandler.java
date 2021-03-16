package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageReturn;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.TestPagination;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plant;
import com.pcitc.fms.service.model.TPmBizOrgMain;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/**
 * 装置handler
 * @author HanXiao
 *
 */
@Controller
public class PlantHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(PlantHandler.class);
	@Autowired
	private PlantService plantService;
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
	
	@Autowired
	private Environment env;
	
	public void getPlants(RoutingContext routingContext) {


		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		String hrefBase = request.uri();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Plant> plantEntity = new ArrayList<>();
				try {
					plantEntity = getData(routingContext,pagination);
					List<Plant> plantDocks = ObjectConverter.listConverter(plantEntity, Plant.class);
					future.complete(plantDocks);
				} catch (Exception e) {
					future.fail(e);
				}
			}, false,res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Plant> targets = (List<Plant>) res.result();
					try {
                	   String linkNames = "silos,samplePoints,sideLines,outlets,valves,equipments,tees,plates,prdtcells";
   					   targets = (List<Plant>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
	   	
					   collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Plant.class);
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

	private List<com.pcitc.fms.bll.entity.Plant> getData(RoutingContext routingContext, Pagination pagination)
			throws Exception, BusinessException {
		List<com.pcitc.fms.bll.entity.Plant> plantEntity;
		Plant plantModel = QueryParams.getQueryParams(routingContext, Plant.class);
		Pageable pageable = null;
		if (plantModel.getTop()!=null && plantModel.getSkip()==null) {
			plantModel.setSkip(0);
		}
		if (plantModel.getSkip() != null && plantModel.getTop() != null){
			pageable =  PageUtil.pageable(plantModel.getTop(),plantModel.getSkip(), null);
		}
		if(plantModel.getOrgCode()!=null){
			checkType.checkOrg(plantModel.getOrgCode());
		}
		//开始查询
		Pager<com.pcitc.fms.bll.entity.Plant> sourceData = plantService.getPagePlants(plantModel, pageable);

		if (routingContext.request().uri().contains("skip") && plantModel.getSkip() != null
				&& plantModel.getTop() == null) {
			if (plantModel.getSkip()>=sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i=0;i<plantModel.getSkip();i++) {
					sourceData.getContent().remove(0);
				}
			}
		}
		PageUtil.mergePage(pagination, sourceData);
		plantEntity = sourceData.getContent();
		return plantEntity;
	}
	
}
