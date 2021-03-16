package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.LoadPointTypeService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.LoadPointType;
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
public class LoadPointTypeHandler extends BaseHandler{
	
	@Autowired
	private LoadPointTypeService loadPointTypeService;
	
	@SuppressWarnings("unchecked")
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					com.pcitc.fms.service.model.LoadPointType loadPointType = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.LoadPointType.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.LoadPointType> sourceData;
					if (null != loadPointType.getSkip()&& null!=loadPointType.getTop()) {
						pageable = PageUtil.pageable(loadPointType.getTop(),loadPointType.getSkip(),null);
					} 
					sourceData = loadPointTypeService.findAll(loadPointType, pageable);
					
					if (routingContext.request().uri().contains("skip") && loadPointType.getSkip() != null
							&& loadPointType.getTop() == null) {
						if (loadPointType.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<loadPointType.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<LoadPointType> modelLoadPointTypes = ObjectConverter.listConverter(sourceData.getContent(), LoadPointType.class);
					future.complete(modelLoadPointTypes);
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
						collection = RestfulTool.buildCollection((List<LoadPointType>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.LoadPointType.class);
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
	public void findByLoadPointTypeCode(RoutingContext routingContext) {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				com.pcitc.fms.service.model.LoadPointType loadPointType = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.LoadPointType.class);
				List<com.pcitc.fms.bll.entity.LoadPointType> LoadPointType_entity = loadPointTypeService.findOne(loadPointType.getLoadPointTypeCode());
				List<LoadPointType> targets = ObjectConverter.listConverter(LoadPointType_entity, LoadPointType.class);
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
					collection = RestfulTool.buildCollection((List<LoadPointType>) res.result(),
							routingContext.request().absoluteURI(), LoadPointType.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	

}
