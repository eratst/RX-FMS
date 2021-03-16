package com.pcitc.fms.service.handler;

	import java.util.List;


	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.NodeIndexTypeService;
import com.pcitc.fms.service.model.NodeIdxType;

import io.vertx.core.Vertx;
	import io.vertx.core.http.HttpServerRequest;
	import io.vertx.ext.web.RoutingContext;
	import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
	import pcitc.imp.common.ettool.utils.RestfulTool;
	@Controller
	public class NodeIdxTypeHandler extends BaseHandler {
		private static  Logger log = LoggerFactory.getLogger(NodeIdxTypeHandler.class);
		@Autowired
		private NodeIndexTypeService nodeIndexTypeService;
		    // 更新配置页面
			public void getIdxType(RoutingContext routingContext) {
				HttpServerRequest request = routingContext.request();
				String hrefBase = request.uri();
				String type = request.getParam("nodeType") == null ? null : request.getParam("nodeType").trim();	
				try {
					Vertx vertx = routingContext.vertx();
					vertx.executeBlocking(future -> {
						try {
						
							List<com.pcitc.fms.service.model.NodeIdxType> idxTypes = nodeIndexTypeService.getByIdxType(type);
							
							future.complete(idxTypes);
						} catch (Exception e) {
							future.fail(e);
						}
					}, res -> {
						String collection = null;
						if (res.failed()) {
							collection = buildErrorCollection(routingContext, (Exception) res.cause());
						} else if (res.succeeded()) {
							List<com.pcitc.fms.service.model.NodeIdxType> idxTypeModels = (List<com.pcitc.fms.service.model.NodeIdxType>) res.result();
							try {
								collection = RestfulTool.buildCollection(idxTypeModels, hrefBase, NodeIdxType.class);
							} catch (Exception e) {
								log.error("getIdxType is Exception!");
								collection = buildErrorCollection(routingContext, e);
							}
							//结果输出
						}
						returnCollection(routingContext, collection);
					});
				} catch (Exception e) {
					log.error("getIdxType failed", e);
					String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
					returnCollection(routingContext, collection);
				}
			}
}
