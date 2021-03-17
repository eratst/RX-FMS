package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.LoadPointService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.LoadPoint;
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
public class LoadPointHandler extends BaseHandler{
	
	@Autowired
	private LoadPointService loadPointService;
	
	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public void findAll(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					LoadPoint loadPoint = QueryParams.getQueryParams(routingContext, LoadPoint.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.LoadPoint> sourceData;
					if (null != loadPoint.getSkip()&& null!=loadPoint.getTop()) {
						pageable = PageUtil.pageable(loadPoint.getTop(),loadPoint.getSkip(),null);
					} 
					sourceData = loadPointService.getLoadPoints(loadPoint, pageable);
					
					if (routingContext.request().uri().contains("skip") && loadPoint.getSkip() != null
							&& loadPoint.getTop() == null) {
						if (loadPoint.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<loadPoint.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<LoadPoint> modelLoadPoints = ObjectConverter.listConverter(sourceData.getContent(), LoadPoint.class);
					future.complete(modelLoadPoints);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<LoadPoint> targets = (List<LoadPoint>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<LoadPoint>) setModelEntityWithLinks(targets,linkNames,baseUri);
						
	   					for (LoadPoint sideLine: targets) {
	   						sideLine.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								LoadPoint.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
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
