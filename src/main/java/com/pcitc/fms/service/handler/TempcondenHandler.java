package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TempcondenService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Tempconden;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class TempcondenHandler extends BaseHandler {

	@Autowired
	private TempcondenService tempcondenService;

	@SuppressWarnings("unchecked")
	public void getTempcondens(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Tempconden> tempcondenEntity = new ArrayList<>();
				try {
					Tempconden tempconden = QueryParams.getQueryParams(routingContext, Tempconden.class);

					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.Tempconden> sourceData = null;

					if (tempconden.getSkip() == null && tempconden.getTop() != null) {
						tempconden.setSkip(0);
					}
					if (tempconden.getSkip() != null && tempconden.getTop() != null) {
						pageable = PageUtil.pageable(tempconden.getTop(),tempconden.getSkip(), null);
					}
					sourceData = tempcondenService.getTempconden(tempconden, pageable);

					if (routingContext.request().uri().contains("skip") && tempconden.getSkip() != null
							&& tempconden.getTop() == null) {
						if (tempconden.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < tempconden.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}

					}
					PageUtil.mergePage(pagination, sourceData);
					tempcondenEntity = sourceData.getContent();
					List<Tempconden> modelTempconden = new ArrayList<>();
					modelTempconden = ObjectConverter.listConverter(tempcondenEntity, Tempconden.class);
					future.complete(modelTempconden);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Tempconden> targets = (List<com.pcitc.fms.service.model.Tempconden>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.Tempconden.class);
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
