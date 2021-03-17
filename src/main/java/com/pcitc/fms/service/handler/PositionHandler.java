package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.PositionService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Position;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class PositionHandler extends BaseHandler{
	
	@Autowired
	private PositionService positionService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void getPositions(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		try {
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination();
			vertx.executeBlocking(future -> {
				try {
					Position positionModel = QueryParams.getQueryParams(routingContext, Position.class);
					Pageable pageable = null;
					if (positionModel.getTop() != null && positionModel.getSkip() == null) {
						positionModel.setSkip(0);
					}
					if (positionModel.getTop() != null && positionModel.getSkip() != null) {
						pageable =  PageUtil.pageable(positionModel.getTop(),positionModel.getSkip(), null);
					}


					Pager<com.pcitc.fms.bll.entity.Position> sourceData = positionService.getPositions(positionModel, pageable);
					if (routingContext.request().uri().contains("skip") && positionModel.getSkip() != null
							&& positionModel.getTop() == null) {
						if (positionModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < positionModel.getSkip(); i++) {
								sourceData.getContent().remove(i);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);

					List<com.pcitc.fms.bll.entity.Position> enList = sourceData.getContent();
					List<Position> positionMo = ObjectConverter.listConverter(enList, Position.class);
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
					List<Position> targets = (List<Position>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Position.class);
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
