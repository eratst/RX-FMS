package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TempdenService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Tempden;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class TempdenHandler extends BaseHandler{
	
	@Autowired
	private TempdenService tempdenService;
	
	@SuppressWarnings("unchecked")
	public void getTempdens(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Tempden> tempdenEntity = new ArrayList<>();
				try {
					Tempden tempden = QueryParams.getQueryParams(routingContext, Tempden.class);

					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.Tempden> sourceData = null;
					if (tempden.getSkip()==null && tempden.getTop()!=null) {
						tempden.setSkip(0);
					}
					
					if (tempden.getSkip() != null && tempden.getTop() != null) {
						pageable = PageUtil.pageable(tempden.getTop(),tempden.getSkip(), null);
					}
					sourceData = tempdenService.getTempden(tempden, pageable);
					
					if (routingContext.request().uri().contains("skip") && tempden.getSkip()!=null&&
							tempden.getTop()==null) {
						if (tempden.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<tempden.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					tempdenEntity = sourceData.getContent();
					List<Tempden> modelTempden = new ArrayList<>();
					modelTempden = ObjectConverter.listConverter(tempdenEntity, Tempden.class);
					future.complete(modelTempden);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Tempden> targets = (List<com.pcitc.fms.service.model.Tempden>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.Tempden.class);
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
