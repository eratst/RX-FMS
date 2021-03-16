package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.CapacityUnitService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.CapacityUnit;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;



@Controller
public class CapacityUnitHandler extends BaseHandler{
	
	@Autowired
	private CapacityUnitService capacityUnitService;
	
	@SuppressWarnings("unchecked")
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					com.pcitc.fms.service.model.CapacityUnit capacityUnit = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.CapacityUnit.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.CapacityUnit> sourceData;
					if (null != capacityUnit.getSkip()&& null!=capacityUnit.getTop()) {
						pageable = PageUtil.pageable(capacityUnit.getTop(),capacityUnit.getSkip(),null);
					} 
					sourceData = capacityUnitService.findAll(capacityUnit, pageable);
					
					if (routingContext.request().uri().contains("skip") && capacityUnit.getSkip() != null
							&& capacityUnit.getTop() == null) {
						if (capacityUnit.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<capacityUnit.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<CapacityUnit> modelCapacityUnits = ObjectConverter.listConverter(sourceData.getContent(), CapacityUnit.class);
					future.complete(modelCapacityUnits);
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
						collection = RestfulTool.buildCollection((List<CapacityUnit>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.CapacityUnit.class);
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

	@SuppressWarnings("unchecked")
	public void findByCapacityUnitCode(RoutingContext routingContext) {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				com.pcitc.fms.service.model.CapacityUnit capacityUnit = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.CapacityUnit.class);
				List<com.pcitc.fms.bll.entity.CapacityUnit> CapacityUnit_entity = capacityUnitService.findOne(capacityUnit.getCapacityUnitCode());
				List<CapacityUnit> targets = ObjectConverter.listConverter(CapacityUnit_entity, CapacityUnit.class);
				future.complete(targets);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<CapacityUnit>) res.result(),
							routingContext.request().absoluteURI(), CapacityUnit.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}

}
