package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import com.pcitc.fms.bll.itf.GlbPreCoefService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.handler.BaseHandler;
import com.pcitc.fms.service.model.GlbPreCoef;
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
public class GlbPreCoefHandler extends BaseHandler{
	
	@Autowired
	private GlbPreCoefService glbPreCoefService;
	
	
	public void getAll(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					GlbPreCoef glbPreCoef = QueryParams.getQueryParams(routingContext, GlbPreCoef.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.GlbPreCoef> sourceData;
					if (null != glbPreCoef.getSkip()&& null!=glbPreCoef.getTop()) {
						pageable = PageUtil.pageable(glbPreCoef.getTop(),glbPreCoef.getSkip(),null);
					} 
					sourceData = glbPreCoefService.getGlbPreCoefs(glbPreCoef, pageable);
					
					if (routingContext.request().uri().contains("skip") && glbPreCoef.getSkip() != null
							&& glbPreCoef.getTop() == null) {
						if (glbPreCoef.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<glbPreCoef.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<GlbPreCoef> modelGlbPreCoefs = ObjectConverter.listConverter(sourceData.getContent(), GlbPreCoef.class);
					future.complete(modelGlbPreCoefs);
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
						collection = RestfulTool.buildCollection((List<GlbPreCoef>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.GlbPreCoef.class);
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
