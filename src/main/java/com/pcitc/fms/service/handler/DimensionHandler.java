package com.pcitc.fms.service.handler;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import com.pcitc.fms.bll.itf.DimensionService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Dimension;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plate;
import com.pcitc.imp.common.model.Pagination;

@Controller
public class DimensionHandler extends BaseHandler{
	private static Logger log = LoggerFactory.getLogger(TankHandler.class);
	@Autowired
	private DimensionService dimensionService;
	
	@SuppressWarnings("unchecked")
	public void getDimensions(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		try {
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination();
			vertx.executeBlocking(future -> {
				try {
					Dimension plateModel = QueryParams.getQueryParams(routingContext, Dimension.class);
					Pageable pageable = null;
					if (plateModel.getTop() != null && plateModel.getSkip() == null) {
						plateModel.setSkip(0);
					}
					if (plateModel.getTop() != null && plateModel.getSkip() != null) {
						pageable =  PageUtil.pageable(plateModel.getTop(),plateModel.getSkip(), null);
					}


					Pager<com.pcitc.fms.bll.entity.Dimension> sourceData = dimensionService.getPageDimensions(plateModel, pageable);
					if (routingContext.request().uri().contains("skip") && plateModel.getSkip() != null
							&& plateModel.getTop() == null) {
						if (plateModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < plateModel.getSkip(); i++) {
								sourceData.getContent().remove(i);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);

					List<com.pcitc.fms.bll.entity.Dimension> enList = sourceData.getContent();
					List<Plate> plateMo = ObjectConverter.listConverter(enList, Dimension.class);
					future.complete(plateMo);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Dimension> targets = (List<Dimension>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Dimension.class);
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
