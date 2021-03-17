package com.pcitc.fms.service.handler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.LiqprodCubaTempCoefService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.LiqprodCubaTempCoef;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.handler.BaseHandler;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class LiqprodCubaTempCoefHandler extends BaseHandler {

	@Autowired
	private LiqprodCubaTempCoefService liqprodCubaTempCoefService;
	
	@SuppressWarnings("unchecked")
	public void findLiqprodCubaTempCoefs(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Sort sort = new Sort(Direction.DESC, "mntDate");
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		
		vertx.executeBlocking(future ->{
			try {
				
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);
				Pageable pageable = null;
				if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
					top = CheckUtil.checkTop(topStr);
					skip = CheckUtil.checkSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
				}
				
				Pager<com.pcitc.fms.bll.entity.LiqprodCubaTempCoef> liqprodCubaTempCoefEn =  
						liqprodCubaTempCoefService.findLiqprodCubaTempCoefs(pageable);
				PageUtil.mergePage(pagination, liqprodCubaTempCoefEn);
				List<LiqprodCubaTempCoef> liqprodCubaTempCoefMo = ObjectConverter.listConverter(liqprodCubaTempCoefEn.getContent(), LiqprodCubaTempCoef.class);
				future.complete(liqprodCubaTempCoefMo);
			}catch(Exception e) {
				future.fail(e);
			}
		}, res -> {
			
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}
			try {
				if (res.succeeded()) {
					collection = RestfulTool.buildCollection((List<LiqprodCubaTempCoef>) res.result(), pagination,
							routingContext.request().absoluteURI(), LiqprodCubaTempCoef.class);
				} else {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
				returnCollection(routingContext, collection);
			} catch (Exception e) {
				String collecion = buildErrorCollection(routingContext, e);
				returnCollection(routingContext, collecion);
			}
		});
	}
}
