package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.FMSObjectTreeService;
import com.pcitc.fms.service.model.FMSObjectTree;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class FMSObjectTreeHandler extends BaseHandler{
	
	@Autowired
	private FMSObjectTreeService fMSObjectTreeService;
	
	@SuppressWarnings("unchecked")
	public void getFMSObjectTree(RoutingContext routingContext) {
		
		String rentCode = routingContext.request().getParam("rentCode");
		String orgCode = routingContext.request().getParam("orgCode");
		String bizCode = routingContext.request().getParam("bizCode");
		String type = routingContext.request().getParam("type");
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			List<FMSObjectTree> models = new ArrayList<>();
			try {
				//bizCode=standard02  orgCode=SINOPEC
				List<com.pcitc.fms.bll.entity.FMSObjectTree> entitys = fMSObjectTreeService.getFMSObjectTreeToNode(rentCode,bizCode, orgCode,type);
				
				models = ObjectConverter.listConverter(entitys, FMSObjectTree.class);
			} catch (Exception e) {
				future.fail(e);
			}
			future.complete(models);
		}, res ->{
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<FMSObjectTree>)res.result(), routingContext.request().uri(), com.pcitc.fms.service.model.FMSObjectTree.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
			}
		});
		
	}

}
