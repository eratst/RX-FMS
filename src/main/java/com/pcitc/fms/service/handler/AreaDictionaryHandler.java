package com.pcitc.fms.service.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.Area;
import com.pcitc.fms.bll.itf.AreaDictionaryService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


@Controller
public class AreaDictionaryHandler extends BaseHandler{

	private static Logger log = LoggerFactory.getLogger(AreaDictionaryHandler.class);
	@Autowired
	private AreaDictionaryService areaDictionaryService;
	
    @SuppressWarnings("unchecked")
	public void getAreaDictionaryTables(RoutingContext routingContext){
    	HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
	    Pagination pagination = new Pagination();
		try {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				Area areaDictionary = QueryParams.getQueryParams(routingContext, Area.class);
				Pageable pageable = null;
				if(null != areaDictionary.getSkip()&&null!=areaDictionary.getTop()){
             	   pageable = PageUtil.pageable(areaDictionary.getTop(),areaDictionary.getSkip(), null);
                }
				Pager<com.pcitc.fms.bll.entity.Area> sourceData = areaDictionaryService.getAreas(areaDictionary, pageable);
				if (routingContext.request().uri().contains("skip") && areaDictionary.getSkip() != null
						&& areaDictionary.getTop() == null) {
					if (areaDictionary.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<areaDictionary.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				List<Area> tankareaEntity = ObjectConverter.listConverter(sourceData.getContent(), Area.class);
				future.complete(tankareaEntity);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<Area>) res.result(), pagination,hrefBase, com.pcitc.fms.service.model.Area.class);
				} catch (Exception e) {
					log.error("getUnits is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getTankAreas is end!");
			}
		});
		} catch (Exception e) {
			log.error("getTankAreas is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}
}
