package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.SpclCuabService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SpclCuab;
import com.pcitc.fms.service.model.StdpresCoef;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class SpclCuabHandler extends BaseHandler{
	
	@Autowired
	private SpclCuabService spclCuabService;
	
	@SuppressWarnings("unchecked")
	public void getSpclCuabs(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.SpclCuab> spclCuabEntity = new ArrayList<>();
				try {
					SpclCuab modelStr = QueryParams.getQueryParams(routingContext, SpclCuab.class);

					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.SpclCuab> sourceData = null;
					if (modelStr.getSkip()==null && modelStr.getTop()!=null) {
						modelStr.setSkip(0);
					}
					
					if (modelStr.getSkip() != null && modelStr.getTop() != null) {
						pageable = PageUtil.pageable(modelStr.getTop(),modelStr.getSkip(), null);
					}
					sourceData = spclCuabService.getSpclCuab(modelStr, pageable);
					
					if (routingContext.request().uri().contains("skip") && modelStr.getSkip()!=null&&
							modelStr.getTop()==null) {
						if (modelStr.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<modelStr.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					spclCuabEntity = sourceData.getContent();
					List<StdpresCoef> modelTank = new ArrayList<>();
					modelTank = ObjectConverter.listConverter(spclCuabEntity, SpclCuab.class);
					future.complete(modelTank);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.SpclCuab> targets = (List<com.pcitc.fms.service.model.SpclCuab>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.SpclCuab.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	}

}
