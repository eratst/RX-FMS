package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.UserPositionService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UserPosition;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class UserPositionHandler extends BaseHandler{
	
	@Autowired
	private UserPositionService userPositionService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void getUserPositions(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		try {
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination();
			vertx.executeBlocking(future -> {
				try {
					UserPosition userPositionModel = QueryParams.getQueryParams(routingContext, UserPosition.class);
					Pageable pageable = null;
					if (userPositionModel.getTop() != null && userPositionModel.getSkip() == null) {
						userPositionModel.setSkip(0);
					}
					if (userPositionModel.getTop() != null && userPositionModel.getSkip() != null) {
						pageable =  PageUtil.pageable(userPositionModel.getTop(),userPositionModel.getSkip(), null);
					}


					Pager<com.pcitc.fms.bll.entity.UserPosition> sourceData = userPositionService.getUserPositions(userPositionModel, pageable);
					if (routingContext.request().uri().contains("skip") && userPositionModel.getSkip() != null
							&& userPositionModel.getTop() == null) {
						if (userPositionModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < userPositionModel.getSkip(); i++) {
								sourceData.getContent().remove(i);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);

					List<com.pcitc.fms.bll.entity.UserPosition> enList = sourceData.getContent();
					List<UserPosition> positionMo = ObjectConverter.listConverter(enList, UserPosition.class);
					future.complete(positionMo);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<UserPosition> targets = (List<UserPosition>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								UserPosition.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	}

}
