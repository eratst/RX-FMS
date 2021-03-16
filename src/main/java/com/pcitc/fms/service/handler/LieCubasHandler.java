package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.LieCubasService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.LieCubas;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class LieCubasHandler extends BaseHandler {
	@Autowired
	private LieCubasService lieCubasService;

	public void getLieCubas(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.LieCubas> lieCubasEntity = new ArrayList<>();
				try {
					LieCubas lieCubasModel = QueryParams.getQueryParams(routingContext, LieCubas.class);
					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.LieCubas> sourceData = null;
					if (lieCubasModel.getTop()!=null && lieCubasModel.getSkip()==null) {
						lieCubasModel.setSkip(0);
					}
					
					if (lieCubasModel.getSkip() != null && lieCubasModel.getTop() != null) {
						pageable = PageUtil.pageable(lieCubasModel.getTop(),lieCubasModel.getSkip(), null);
						sourceData = lieCubasService.getPageLieCubas(lieCubasModel, pageable);
					} else {
						sourceData = lieCubasService.getPageLieCubas(lieCubasModel, null);
					}
					List<com.pcitc.fms.bll.entity.LieCubas> list = new ArrayList<>();
					if (routingContext.request().uri().contains("skip") && lieCubasModel.getSkip() != null
							&& lieCubasModel.getTop() == null) {
						if (lieCubasModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<lieCubasModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					lieCubasEntity = sourceData.getContent();
					List<LieCubas> lieCubasModelList = new ArrayList<>();
					lieCubasModelList = ObjectConverter.listConverter(lieCubasEntity, LieCubas.class);
					future.complete(lieCubasModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {

					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<LieCubas> targets = (List<LieCubas>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								LieCubas.class);
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
	
	
	//卧罐罐容精度及扣减量
		public void getLieCubaDegree(RoutingContext routingContext) {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					DataDegree Model = QueryParams.getQueryParams(routingContext, DataDegree.class);
					List lieCubaDegree = lieCubasService.getLieCubaDegree(Model);
					List lieCubaDegreeModelList = ObjectConverter.listConverter(lieCubaDegree, DataDegree.class);
					pagination.setRecordCount((long)lieCubaDegreeModelList.size());
					future.complete(lieCubaDegreeModelList);
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
