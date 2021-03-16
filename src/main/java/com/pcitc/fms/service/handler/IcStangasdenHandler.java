package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.IcStangasdenService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.IcStangasden;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
@SuppressWarnings({ "unchecked", "unused" })
public class IcStangasdenHandler extends BaseHandler{
	
	@Autowired
	private IcStangasdenService icStangasdenService;
	
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				IcStangasden icStangasden = QueryParams.getQueryParams(routingContext, IcStangasden.class);
				// 配置分页参数
				Pageable pageable = null;

				Pager<com.pcitc.fms.bll.entity.IcStangasden> sourceData = null;
				if (icStangasden.getTop()!=null && icStangasden.getSkip()==null) {
					icStangasden.setSkip(0);
				}
				
				if (icStangasden.getSkip() != null && icStangasden.getTop() != null) {
					pageable = PageUtil.pageable(icStangasden.getTop(),icStangasden.getSkip(), null);
					sourceData = icStangasdenService.findIcStangasdens(icStangasden, pageable);
				} else {
					sourceData = icStangasdenService.findIcStangasdens(icStangasden, null);
				}
				List<com.pcitc.fms.bll.entity.IcStangasden> list = new ArrayList<>();
				if (routingContext.request().uri().contains("skip") && icStangasden.getSkip() != null
						&& icStangasden.getTop() == null) {
					if (icStangasden.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<icStangasden.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
						sourceData.setTotalElements((long)sourceData.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				list = sourceData.getContent();
				List<IcStangasden> result = new ArrayList<>();
				result = ObjectConverter.listConverter(list, IcStangasden.class);
				future.complete(result);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<IcStangasden>) res.result(), pagination,
							routingContext.request().uri(), IcStangasden.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
	public void findById(RoutingContext routingContext) {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				IcStangasden icStangasden = QueryParams.getQueryParams(routingContext, IcStangasden.class);
				List<com.pcitc.fms.bll.entity.IcStangasden> icStangasdenEntity = icStangasdenService.findIcStangasdenById(icStangasden.getStangasdenId());
				List<IcStangasden> targets = ObjectConverter.listConverter(icStangasdenEntity, IcStangasden.class);
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
					collection = RestfulTool.buildCollection((List<IcStangasden>) res.result(), routingContext.request().uri(),
							IcStangasden.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
}
