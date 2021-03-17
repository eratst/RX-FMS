package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.MtrlVcfService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.MtrlVcf;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class MtrlVcfHandler extends BaseHandler{
	
	@Autowired
	private MtrlVcfService mtrlVcfService;
	
	/**
	 * 查询所有
	 * 
	 * @param routingContext
	 */
	public void findAll(RoutingContext routingContext) {
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.MtrlVcf> MtrlVcfEntity = new ArrayList<>();
				try {
					MtrlVcf Model = QueryParams.getQueryParams(routingContext, MtrlVcf.class);
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.MtrlVcf> sourceData = null;
					
					if (Model.getSkip()==null && Model.getTop()!=null) {
						Model.setSkip(0);
					}
					if (Model.getSkip()!=null && Model.getTop()!=null) {
						pageable = PageUtil.pageable(Model.getTop(),Model.getSkip(), null);
						sourceData = mtrlVcfService.getPageMtrlVcf(Model,pageable);
					} else {
						sourceData = mtrlVcfService.getPageMtrlVcf(Model,null);
					}
					
					if (routingContext.request().uri().contains("skip") && Model.getSkip()!=null&&
							Model.getTop()==null) {
						if (Model.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<Model.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					MtrlVcfEntity = sourceData.getContent();
					List<MtrlVcf> MtrlVcfModelList = new ArrayList<>();
					MtrlVcfModelList = ObjectConverter.listConverter(MtrlVcfEntity, MtrlVcf.class);
					future.complete(MtrlVcfModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				
				} else if (res.succeeded()) {
					List<MtrlVcf> targets = (List<MtrlVcf>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), MtrlVcf.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}
	}

}
