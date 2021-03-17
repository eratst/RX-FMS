package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.IcMtrlFormCnfgService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.IcMtrlFormCnfg;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class IcMtrlFormCnfgHandler extends BaseHandler{
	
	@Autowired
	private IcMtrlFormCnfgService icMtrlFormCnfgService;
	
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				IcMtrlFormCnfg icMtrlFormCnfg = QueryParams.getQueryParams(routingContext, IcMtrlFormCnfg.class);
				// 配置分页参数
				Pageable pageable = null;

				Pager<com.pcitc.fms.bll.entity.IcMtrlFormCnfg> sourceData = null;
				if (icMtrlFormCnfg.getTop()!=null && icMtrlFormCnfg.getSkip()==null) {
					icMtrlFormCnfg.setSkip(0);
				}
				
				if (icMtrlFormCnfg.getSkip() != null && icMtrlFormCnfg.getTop() != null) {
					pageable = PageUtil.pageable(icMtrlFormCnfg.getTop(),icMtrlFormCnfg.getSkip(), null);
					sourceData = icMtrlFormCnfgService.findIcMtrlFormCnfgs(icMtrlFormCnfg, pageable);
				} else {
					sourceData = icMtrlFormCnfgService.findIcMtrlFormCnfgs(icMtrlFormCnfg, pageable);
				}
				List<com.pcitc.fms.bll.entity.IcMtrlFormCnfg> list = new ArrayList<>();
				if (routingContext.request().uri().contains("skip") && icMtrlFormCnfg.getSkip() != null
						&& icMtrlFormCnfg.getTop() == null) {
					if (icMtrlFormCnfg.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<icMtrlFormCnfg.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
						sourceData.setTotalElements((long)sourceData.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				list = sourceData.getContent();
				List<IcMtrlFormCnfg> result = new ArrayList<>();
				result = ObjectConverter.listConverter(list, IcMtrlFormCnfg.class);
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
					collection = RestfulTool.buildCollection((List<IcMtrlFormCnfg>) res.result(), pagination,
							routingContext.request().uri(), IcMtrlFormCnfg.class);
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
				IcMtrlFormCnfg icMtrlFormCnfg = QueryParams.getQueryParams(routingContext, IcMtrlFormCnfg.class);
				List<com.pcitc.fms.bll.entity.IcMtrlFormCnfg> icMtrlFormCnfgEntity = icMtrlFormCnfgService.findIcMtrlFormCnfgById(icMtrlFormCnfg.getMtrlFormCnfgId());
				List<IcMtrlFormCnfg> targets = ObjectConverter.listConverter(icMtrlFormCnfgEntity, IcMtrlFormCnfg.class);
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
					collection = RestfulTool.buildCollection((List<IcMtrlFormCnfg>) res.result(), routingContext.request().uri(),
							IcMtrlFormCnfg.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
}
