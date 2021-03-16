package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;


import com.pcitc.fms.bll.itf.StanDenService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StanDen;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


@Controller
@SuppressWarnings({ "unchecked", "unused" })
public class StanDenHandler  extends BaseHandler{
	
	@Autowired
	private StanDenService stanDenService;
	
	
	public void getAll(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					StanDen stanDen = QueryParams.getQueryParams(routingContext, StanDen.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.StanDen> sourceData;
					if (null != stanDen.getSkip()&& null!=stanDen.getTop()) {
						pageable = PageUtil.pageable(stanDen.getTop(),stanDen.getSkip(),null);
					} 
					sourceData = stanDenService.getStanDens(stanDen, pageable);
					
					if (routingContext.request().uri().contains("skip") && stanDen.getSkip() != null
							&& stanDen.getTop() == null) {
						if (stanDen.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<stanDen.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<StanDen> modelStanDens = ObjectConverter.listConverter(sourceData.getContent(), StanDen.class);
					future.complete(modelStanDens);
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
						collection = RestfulTool.buildCollection((List<StanDen>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.StanDen.class);
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
