package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.GlbCubasService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.GlbCubas;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class GlbCubasHandler extends BaseHandler {
	@Autowired
	private GlbCubasService glbCubasService;

	public void getGlbCubas(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.GlbCubas> glbCubasEntity = new ArrayList<>();
				try {
					GlbCubas glbCubasModel = QueryParams.getQueryParams(routingContext, GlbCubas.class);
					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.GlbCubas> sourceData = null;
					if (glbCubasModel.getSkip() == null && glbCubasModel.getTop() != null) {
						glbCubasModel.setSkip(0);
					}

					if (glbCubasModel.getSkip() != null && glbCubasModel.getTop() != null) {
						pageable = PageUtil.pageable(glbCubasModel.getTop(), glbCubasModel.getSkip(), null);
						sourceData = glbCubasService.getPageGlbCubas(glbCubasModel, pageable);
					} else {
						sourceData = glbCubasService.getPageGlbCubas(glbCubasModel, null);
					}

					if (routingContext.request().uri().contains("skip") && glbCubasModel.getSkip() != null
							&& glbCubasModel.getTop() == null) {
						if (glbCubasModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < glbCubasModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					glbCubasEntity = sourceData.getContent();
					List<GlbCubas> glbCubasModelList = new ArrayList<>();
					glbCubasModelList = ObjectConverter.listConverter(glbCubasEntity, GlbCubas.class);
					future.complete(glbCubasModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<GlbCubas> targets = (List<GlbCubas>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								GlbCubas.class);
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

	//球罐罐容精度及扣减量
	public void getGlbCubaDegree(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				DataDegree Model = QueryParams.getQueryParams(routingContext, DataDegree.class);
				List glbCubaDegree = glbCubasService.getGlbCubaDegree(Model);
				List glbCubaDegreeModelList = ObjectConverter.listConverter(glbCubaDegree, DataDegree.class);
				pagination.setRecordCount((long)glbCubaDegreeModelList.size());
				future.complete(glbCubaDegreeModelList);
			} catch (Exception e) {
				future.fail(e);
			}

		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);

			}else if (res.succeeded()) {
				List<DataDegree> targets = (List<DataDegree>) res.result();
				try {
					collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
							DataDegree.class);
				} catch (Exception e) {
					e.printStackTrace();
					returnCollection(routingContext, e.getMessage());
				}
				returnCollection(routingContext, collection);
			}

		});
	}

}
