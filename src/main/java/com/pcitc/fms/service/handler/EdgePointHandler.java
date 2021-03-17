package com.pcitc.fms.service.handler;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.PipeNetwork;
import com.pcitc.fms.bll.entity.Valve;
import com.pcitc.fms.bll.itf.EdgePointService;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.bll.itf.ValveService;
import com.pcitc.fms.bll.itf.WarehouseService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.EdgePoint;
import com.pcitc.fms.service.model.EdgePointModelStr;
import com.pcitc.fms.service.model.Equipment;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import cc.aicode.e2e.exception.ExcelParseException;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


/*
 * 
*<p>Title: EdgePointHandler<p>
*<p>Description: 进出厂点handler</p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年7月12日
 */
@Controller
public class EdgePointHandler extends BaseHandler {
    
    
	private static Logger log = LoggerFactory.getLogger(FactoryHandler.class);
	@Autowired
	private EdgePointService edgePointService;
	@Autowired
	private FactoryService factoryService;
	@Autowired
	private ValveService valveService;
	@Autowired
    private CheckType checkType;

	public void getEdgePoints(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.EdgePoint> EdgePointEntity = new ArrayList<>();
				try {
					EdgePoint EdgePointModel = QueryParams.getQueryParams(routingContext, EdgePoint.class);
					Pageable pageable = null;
					if (EdgePointModel.getTop()!=null && EdgePointModel.getSkip()==null) {
						EdgePointModel.setSkip(0);
					}
					if (EdgePointModel.getSkip() != null && EdgePointModel.getTop() != null){
						pageable =  PageUtil.pageable(EdgePointModel.getTop(),EdgePointModel.getSkip(), null);
					}
					//层级校验
					
					if(EdgePointModel.getAreaTypeCode()!=null){
						if(EdgePointModel.getOrgCode() !=null && EdgePointModel.getAreaCode() != null && !EdgePointModel.getAreaTypeCode().equals("areas") && EdgePointModel.getAreaTypeCode()!=null){
							checkType.checkOrgAndType(EdgePointModel.getOrgCode(),EdgePointModel.getAreaTypeCode(),EdgePointModel.getAreaCode(),request);
						}
					}else{
						if(EdgePointModel.getOrgCode() !=null && EdgePointModel.getAreaCode() != null){
							checkType.checkOrgAndArea(EdgePointModel.getOrgCode(),EdgePointModel.getAreaCode());
						}
					}
					Pager<com.pcitc.fms.bll.entity.EdgePoint>sourceData = edgePointService.getEdgePointByModel(EdgePointModel,pageable);
					
					if (routingContext.request().uri().contains("skip") && EdgePointModel.getSkip() != null
							&& EdgePointModel.getTop() == null) {
						if (EdgePointModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<EdgePointModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					EdgePointEntity = sourceData.getContent();
					List<EdgePoint> modelEdgePoints = ObjectConverter.listConverter(EdgePointEntity, EdgePoint.class);
					future.complete(modelEdgePoints);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<EdgePoint> targets = (List<EdgePoint>) res.result();
					try {
						
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<EdgePoint>) setModelEntityWithLinks(targets,linkNames,baseUri);

	   					for (EdgePoint edgePoint : targets) {
	   						edgePoint.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								EdgePoint.class);
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
	
	public void getEdgePointsByCodeLinks(RoutingContext routingContext){
	    
	    HttpServerRequest request = routingContext.request();
        String hrefBase = request.uri();
        
        try{
            Vertx vertx = routingContext.vertx();
            vertx.executeBlocking(future ->{
                List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
                try{
                    
                    String parentType = VerifyUtil.getParentType(hrefBase, "edgePoints");
                    String parentCode = VerifyUtil.getParentCode(hrefBase, "edgePoints");
                    String edgePointCode = VerifyUtil.getEntityCode(hrefBase, "edgePoints");
                    edgePointEntityList = edgePointService.getEdgePointesByIdLinks(parentType,parentCode,edgePointCode);
                    future.complete(edgePointEntityList);
                }catch(Exception e){
                    future.fail(e);
                }
            }, res ->{
                String collection = null;
                if(res.failed()){
                    collection = buildErrorCollection(routingContext, (Exception)res.cause());
                    returnCollection(routingContext, collection);
                }else if(res.succeeded()){
                    List<com.pcitc.fms.service.model.EdgePoint> targets = new ArrayList<>();
                    try{
                        List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = (List<com.pcitc.fms.bll.entity.EdgePoint>)res.result();
                        for(com.pcitc.fms.bll.entity.EdgePoint edgepoint : edgePointEntityList){
                            com.pcitc.fms.service.model.EdgePoint target = ObjectConverter.entityConverter(edgepoint, com.pcitc.fms.service.model.EdgePoint.class);
                            target.setHref("/"+target.getNodeId());
                            List<String> links = new ArrayList<>();
                            links.add(hrefBase+"/connections");
                            links.add(hrefBase+"/relations");
                            links.add(hrefBase+"/measurements");
                            links.add(hrefBase+"/materials");
                            links.add(hrefBase+"/valves");
                            links.add(hrefBase+"/plates");
                            links.add(hrefBase+"/tubulations");
                            links.add(hrefBase+"/tees");
                            links.add(hrefBase+"/outlets");
                            target.setLinks(links);
                            targets.add(target);
                        } 
                        collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.EdgePoint.class);
                    }catch(Exception e){
                        log.debug("getEdgePointsByIdLinks is Exception");
                        collection = buildErrorCollection(routingContext, e);
                        returnCollection(routingContext, collection);
                    }
                    returnCollection(routingContext, collection);
                    log.info("getEdgePointsByIdLinks is end!");
                }
            });
        }catch(Exception e){
            log.debug("getEdgePoints is Exception");
            String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),hrefBase);
            returnCollection(routingContext, collection);
        }
	}

}
