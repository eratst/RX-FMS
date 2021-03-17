package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.Factory;
import com.pcitc.fms.bll.entity.TankArea;
import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.CommunityService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Community;
import com.pcitc.fms.service.model.CommunityStr;
import com.pcitc.fms.service.model.FactoryModelStr;
import com.pcitc.fms.service.model.LoadingDock;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Tank;
import com.pcitc.fms.service.model.WarehouseStr;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


/**
 * 生活区
 * @author hehe
 * @author hanxiao
 *
 */
@Controller
public class CommunityHandler extends BaseHandler {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private CheckType checkType;
	
	@SuppressWarnings("unchecked")
	public void getCommunities(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Community> communityEntity = new ArrayList<>();
				try {
					Community communityModel = QueryParams.getQueryParams(routingContext, Community.class);
					Pageable pageable = null;
					if (communityModel.getTop()!=null && communityModel.getSkip()==null) {
						communityModel.setSkip(0);
					}
					if (communityModel.getSkip() != null && communityModel.getTop() != null){
						pageable = PageUtil.pageable(communityModel.getTop(),communityModel.getSkip(), null);
					}
					if(communityModel.getOrgCode()!=null){
						checkType.checkOrg(communityModel.getOrgCode());
					}
					//开始查询
					Pager<com.pcitc.fms.bll.entity.Community> sourceData = communityService.getPageCommunities(communityModel, pageable);
					if (routingContext.request().uri().contains("skip") && communityModel.getSkip() != null
							&& communityModel.getTop() == null) {
						if (communityModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<communityModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					communityEntity = sourceData.getContent();
					List<Community> modelCommunities = ObjectConverter.listConverter(communityEntity, Community.class);
					future.complete(modelCommunities);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Community> targets = (List<Community>) res.result();
					try {
						String linkNames = "samplePoints,valves,equipments,tees,tubulations,plates";
						targets = (List<Community>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());

						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Community.class);
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