package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TeamAndUserService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TeamAndUser;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@SuppressWarnings("unchecked")
@Controller
public class TeamAndUserHandler extends BaseHandler{

	@Autowired
	private TeamAndUserService teamAndUserService;
	
	/**
	 * 查询所有
	 */
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					com.pcitc.fms.service.model.TeamAndUser teamAndUser = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.TeamAndUser.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.TeamAndUser> sourceData;
					if (null != teamAndUser.getSkip()&& null!=teamAndUser.getTop()) {
						pageable = PageUtil.pageable(teamAndUser.getTop(),teamAndUser.getSkip(),null);
					} 
					sourceData = teamAndUserService.getAll(teamAndUser, pageable);
					
					if (routingContext.request().uri().contains("skip") && teamAndUser.getSkip() != null
							&& teamAndUser.getTop() == null) {
						if (teamAndUser.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<teamAndUser.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<TeamAndUser> modelTeamAndUsers = ObjectConverter.listConverter(sourceData.getContent(), TeamAndUser.class);
					future.complete(modelTeamAndUsers);
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
						collection = RestfulTool.buildCollection((List<TeamAndUser>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.TeamAndUser.class);
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
