package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.User;
import com.pcitc.fms.bll.itf.UserService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
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
public class UserHandler extends BaseHandler{

	@Autowired
	private UserService userService;
	
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
					com.pcitc.fms.service.model.User user = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.User.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.User> sourceData;
					if (null != user.getSkip()&& null!=user.getTop()) {
						pageable = PageUtil.pageable(user.getTop(),user.getSkip(),null);
					} 
					sourceData = userService.getUsers(user, pageable);
					
					if (routingContext.request().uri().contains("skip") && user.getSkip() != null
							&& user.getTop() == null) {
						if (user.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<user.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<User> modelUsers = ObjectConverter.listConverter(sourceData.getContent(), User.class);
					future.complete(modelUsers);
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
						collection = RestfulTool.buildCollection((List<User>) res.result(), pagination, hrefBase, com.pcitc.fms.service.model.User.class);
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
