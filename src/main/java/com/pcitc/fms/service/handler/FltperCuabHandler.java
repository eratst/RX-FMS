package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.FltperCuabService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.FltperCuab;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class FltperCuabHandler extends BaseHandler {

	@Autowired
	private FltperCuabService fltperCuabService;

	@SuppressWarnings("unchecked")
	public void getfltperCuabs(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.FltperCuab> tankEntity = new ArrayList<>();
				try {
					FltperCuab modelStr = QueryParams.getQueryParams(routingContext, FltperCuab.class);

					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.FltperCuab> sourceData = null;
					
					if (modelStr.getSkip()==null && modelStr.getTop()!=null) {
						modelStr.setSkip(0);
					}
					if (modelStr.getSkip() != null && modelStr.getTop() != null) {
						pageable = PageUtil.pageable(modelStr.getTop(),modelStr.getSkip(), null);
					}
					sourceData = fltperCuabService.getFltperCuab(modelStr, pageable);
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
					tankEntity = sourceData.getContent();
					List<FltperCuab> modelTank = new ArrayList<>();
					modelTank = ObjectConverter.listConverter(tankEntity, FltperCuab.class);
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
					List<com.pcitc.fms.service.model.FltperCuab> targets = (List<com.pcitc.fms.service.model.FltperCuab>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.FltperCuab.class);
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
