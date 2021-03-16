package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AirDenModCoefService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.AirDenModCoef;
import com.pcitc.fms.service.model.LieCubas;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class AirDenModCoefHandler extends BaseHandler{

	@Autowired
	private AirDenModCoefService airDenModCoefService;

	public void getAirDenModCoef(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.AirDenModCoef> Entity = new ArrayList<>();
				try {
					AirDenModCoef Model = QueryParams.getQueryParams(routingContext, AirDenModCoef.class);
					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.AirDenModCoef> sourceData = null;
					if (Model.getTop() != null && Model.getSkip() == null) {
						Model.setSkip(0);
					}

					if (Model.getSkip() != null && Model.getTop() != null) {
						pageable = PageUtil.pageable(Model.getTop(), Model.getSkip(), null);
						sourceData = airDenModCoefService.getPageAirDenModCoef(Model, pageable);
					} else {
						sourceData = airDenModCoefService.getPageAirDenModCoef(Model, null);
					}
					List<com.pcitc.fms.bll.entity.AirDenModCoef> list = new ArrayList<>();
					if (routingContext.request().uri().contains("skip") && Model.getSkip() != null
							&& Model.getTop() == null) {
						if (Model.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < Model.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					Entity = sourceData.getContent();
					List<AirDenModCoef> airDenModCoefModelList = new ArrayList<>();
					airDenModCoefModelList = ObjectConverter.listConverter(Entity, AirDenModCoef.class);
					future.complete(airDenModCoefModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<AirDenModCoef> targets = (List<AirDenModCoef>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								AirDenModCoef.class);
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
