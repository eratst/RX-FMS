package com.pcitc.fms.service.stormHandler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.OpenindexVO;
import com.pcitc.fms.bll.itf.OpenindexVOService;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.service.handler.BaseHandler;
import com.pcitc.fms.service.model.Administration;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class StormUnitHandler extends BaseHandler{
		private static Logger log = LoggerFactory.getLogger(StormUnitHandler.class);
		@Autowired
		private OpenindexVOService OpenindexVOService;
		@Autowired
		private PlantService plantService;
		//StandardDensityHelper
		 @SuppressWarnings("unchecked")
			public void getTuple(RoutingContext routingContext){
				HttpServerRequest request = routingContext.request();
				String url = request.uri();
		        //获取参数
				String unitCode = request.getParam("unitCode") == null ? null : request.getParam("unitCode"); 
				try{
					Vertx vertx = routingContext.vertx();
					vertx.executeBlocking(future ->{
						try{
							List<com.pcitc.fms.service.model.OpenindexVO> openindexVOList = OpenindexVOService.getOpenindexVOs(unitCode);
							future.complete(openindexVOList);
						}catch(Exception e){
							future.fail(e);
						}
						
		 			}, res ->{
						String collection = null;
						if(res.failed()){
							collection  = buildErrorCollection(routingContext, (Exception) res.cause());
							returnCollection(routingContext, collection);
						}else if(res.succeeded()){
							try {
								List<com.pcitc.fms.service.model.OpenindexVO> modleList = (List<com.pcitc.fms.service.model.OpenindexVO>)res.result();
								collection = RestfulTool.buildCollection(modleList, url, com.pcitc.fms.service.model.OpenindexVO.class);
							} catch (Exception e) {
								log.error("getTuple is Exception!");
								collection = buildErrorCollection(routingContext, e);
								returnCollection(routingContext, collection);
							}
							returnCollection(routingContext, collection);
						}
					});
				}catch(Exception e){
					log.error("getObject is Exception!");
					String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), "");
					returnCollection(routingContext, collection);
				}
			}

}
