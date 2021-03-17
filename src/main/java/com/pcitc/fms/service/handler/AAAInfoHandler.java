package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AAAInfoService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.service.model.AAAProperty;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class AAAInfoHandler extends BaseHandler{
	
	@Autowired
	private AAAInfoService aaaInfoService;
	
	@SuppressWarnings("unchecked")
	public void getAAAProperties(RoutingContext routingContext) {
		
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String rentCode = request.getParam("rentCode")==null?null:request.getParam("rentCode").trim();
		String userCode = request.getParam("userCode")==null?null:request.getParam("userCode").trim();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					CheckUtil.validateCodeException("租户编码", rentCode);
					CheckUtil.validateCodeException("用户编码", userCode);
					List<AAAProperty> mpdelAAAProperty = aaaInfoService.getAAAProperties(userCode, rentCode);
					future.complete(mpdelAAAProperty);
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
						collection = RestfulTool.buildCollection((List<AAAProperty>)res.result(), hrefBase, AAAProperty.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
		
	}

}
