package com.pcitc.fms.service.handler;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.SideLineService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PipeNetwork;
import com.pcitc.fms.service.model.SideLine;
import com.pcitc.fms.service.model.SideLineStr;
import com.pcitc.fms.service.model.TankModelStr;
import com.pcitc.fms.service.model.WarehouseStr;
import com.pcitc.imp.common.exception.BusiException;
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
 * 侧线
 * 
 * @author hehe
 *
 */
@Controller
public class SideLineHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(SideLineHandler.class);
	@Autowired
	private SideLineService sideLineService;
	@Autowired
	private FactoryDictService  dictService;
	@Autowired
    private CheckType checkType;
	
	@Autowired
	private Environment env;
	
	public void getSideLines(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.SideLine> sideLineEntity = new ArrayList<>();
				try {
					sideLineEntity = getData(routingContext, request,pagination);
					List<SideLine> modelSideLines = ObjectConverter.listConverter(sideLineEntity, SideLine.class);
					future.complete(modelSideLines);
				} catch (Exception e) {
					future.fail(e);
				}
			}, false,res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<SideLine> targets = (List<SideLine>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<SideLine>) setModelEntityWithLinks(targets,linkNames,baseUri);
						
	   					for (SideLine sideLine: targets) {
	   						sideLine.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								SideLine.class);
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

	private List<com.pcitc.fms.bll.entity.SideLine> getData(RoutingContext routingContext, HttpServerRequest request,
			Pagination pagination) throws Exception, BusinessException {
		List<com.pcitc.fms.bll.entity.SideLine> sideLineEntity;
		SideLine sideLineModel = QueryParams.getQueryParams(routingContext, SideLine.class);
		Pageable pageable = null;
		if (sideLineModel.getTop()!=null && sideLineModel.getSkip()==null) {
			sideLineModel.setSkip(0);
		}
		if (sideLineModel.getSkip() != null && sideLineModel.getTop() != null){
			pageable = PageUtil.pageable(sideLineModel.getTop(),sideLineModel.getSkip(), null);
		}
		//层级校验
		if(sideLineModel.getOrgCode() !=null && sideLineModel.getAreaCode() != null){
			if(sideLineModel.getAreaTypeCode()!=null){
				checkType.checkOrgAndArea(sideLineModel.getOrgCode(),sideLineModel.getAreaCode());
			}else{
				checkType.checkOrgAndType(sideLineModel.getOrgCode(),sideLineModel.getAreaTypeCode(),sideLineModel.getAreaCode(),request);
			}
		}
			
		Pager<com.pcitc.fms.bll.entity.SideLine> sourceData = sideLineService.getSideLineModel(sideLineModel,pageable);
		if (routingContext.request().uri().contains("skip") && sideLineModel.getSkip() != null
				&& sideLineModel.getTop() == null) {
			if (sideLineModel.getSkip()>=sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i=0;i<sideLineModel.getSkip();i++) {
					sourceData.getContent().remove(0);
				}
			}
		}
		PageUtil.mergePage(pagination, sourceData);
		sideLineEntity = sourceData.getContent();
		return sideLineEntity;
	}

	public void getLinkSideLines(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String uri = request.uri();
		String hrefBase = request.uri();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();
     	log.info("*** TankHandler START getFactories ***");
		String parentType = VerifyUtil.getParentType(uri, "sideLines");
		try {
			String parentCode = VerifyUtil.getParentCode(uri, "sideLines");
			String sideLineCode = VerifyUtil.getEntityCode(uri, "sideLines");
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.SideLine> sideLineEntity = new ArrayList<>();
				try {
					SideLineStr modelStr = new SideLineStr();
					if (sideLineCode != null) {
						modelStr.setCode(sideLineCode);

					}
					modelStr.setParentCode(parentCode);
					modelStr.setParentType(parentType);
					sideLineEntity = sideLineService.getLinkSideLines(modelStr);
					future.complete(sideLineEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Tank> targets = new ArrayList<>();
					try {
						List<Tank> tankList = (List<Tank>) res.result();
						for (Tank tank : tankList) {
							com.pcitc.fms.service.model.Tank target = ObjectConverter.entityConverter(tank,
									com.pcitc.fms.service.model.Tank.class);
							target.setHref("/" + target.getNodeId());
							List<String> tankLinks = new ArrayList<>();

							tankLinks.add(uri + "/" + target.getNodeId() + "/Relations");// 关联集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Connections");// 连接集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Measurements");// 度量指标集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Materials");// 物料集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Valves");// 阀门集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Plates");// 盲板集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Tubulations");// 管段集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Tees");// 三通集合
							tankLinks.add(uri + "/" + target.getNodeId() + "/Outlet");// 排放口集合
							target.setLinks(tankLinks);
							targets.add(target);
						}

						collection = RestfulTool.buildCollection(targets, uri, com.pcitc.fms.service.model.Tank.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getFactories is end!");
				}
			});
		} catch (Exception e) {
			log.error("getFactories is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), uri);
			returnCollection(routingContext, collection);
		}

	}
}
