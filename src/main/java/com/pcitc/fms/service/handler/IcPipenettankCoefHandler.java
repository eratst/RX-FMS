package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.IcPipenettankCoefService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.IcPipenettankCoef;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class IcPipenettankCoefHandler extends BaseHandler{
	
	@Autowired
	private IcPipenettankCoefService icPipenettankCoefService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				IcPipenettankCoef icPipenettankCoef = QueryParams.getQueryParams(routingContext, IcPipenettankCoef.class);
				// 配置分页参数
				Pageable pageable = null;

				Pager<com.pcitc.fms.bll.entity.IcPipenettankCoef> sourceData = null;
				if (icPipenettankCoef.getTop()!=null && icPipenettankCoef.getSkip()==null) {
					icPipenettankCoef.setSkip(0);
				}
				
				if (icPipenettankCoef.getSkip() != null && icPipenettankCoef.getTop() != null) {
					pageable = PageUtil.pageable(icPipenettankCoef.getTop(),icPipenettankCoef.getSkip(), null);
					sourceData = icPipenettankCoefService.findIcPipenettankCoefs(icPipenettankCoef, pageable);
				} else {
					sourceData = icPipenettankCoefService.findIcPipenettankCoefs(icPipenettankCoef, pageable);
				}
				List<com.pcitc.fms.bll.entity.IcPipenettankCoef> list = new ArrayList<>();
				if (routingContext.request().uri().contains("skip") && icPipenettankCoef.getSkip() != null
						&& icPipenettankCoef.getTop() == null) {
					if (icPipenettankCoef.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<icPipenettankCoef.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
						sourceData.setTotalElements((long)sourceData.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				list = sourceData.getContent();
				List<IcPipenettankCoef> result = new ArrayList<>();
				result = ObjectConverter.listConverter(list, IcPipenettankCoef.class);
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
					collection = RestfulTool.buildCollection((List<IcPipenettankCoef>) res.result(), pagination,
							routingContext.request().uri(), IcPipenettankCoef.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
	@SuppressWarnings("unchecked")
	public void findById(RoutingContext routingContext) {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				IcPipenettankCoef icPipenettankCoef = QueryParams.getQueryParams(routingContext, IcPipenettankCoef.class);
				List<com.pcitc.fms.bll.entity.IcPipenettankCoef> icPipenettankCoefs = icPipenettankCoefService.findIcPipenettankCoefById(icPipenettankCoef.getPipenettankCoefId());
				List<IcPipenettankCoef> targets = ObjectConverter.listConverter(icPipenettankCoefs, IcPipenettankCoef.class);
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
					collection = RestfulTool.buildCollection((List<IcPipenettankCoef>) res.result(), routingContext.request().uri(),
							IcPipenettankCoef.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}

}
