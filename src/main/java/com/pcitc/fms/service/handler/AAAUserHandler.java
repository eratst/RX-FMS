package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.AAAUser;
import com.pcitc.fms.service.model.NodeIdxType;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class AAAUserHandler extends BaseHandler{

	public void getUser(RoutingContext routingContext) {

		
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					String username = "";
					String usercode = "";
					try {
						HttpServerRequest request = routingContext.request();
						username = request.getHeader("username");
						usercode = request.getHeader("userCode");
						AAAUser aaaUser = new AAAUser();
						if(StringUtils.isEmpty(username)) {
                            username = "admin";
                        }
						aaaUser.setUsername(username);
						aaaUser.setUsercode(usercode);
						List<AAAUser> lists = new ArrayList<>();
						lists.add(aaaUser);
						future.complete(lists);
					} catch (Exception e) {
						future.fail("get userCode fail !");
					} 
					
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				} else if (res.succeeded()) {
					try {
						collection = RestfulTool.buildCollection((List<AAAUser>) res.result(), routingContext.request().uri(), AAAUser.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
					}
					//结果输出
				}
				returnCollection(routingContext, collection);
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}
	}
}
