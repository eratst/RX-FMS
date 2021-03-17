package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.StdcmmmCubasService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StdcmmmCubas;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class StdcmmmCubasHandler extends BaseHandler {
	@Autowired
	private StdcmmmCubasService stdcmmmCubasService;
	
	public void getStdcmmmCubas(RoutingContext routingContext){
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.StdcmmmCubas> stdcmmmCubasEntity = new ArrayList<>();
				try {
					StdcmmmCubas stdcmmmCubasModel = QueryParams.getQueryParams(routingContext, StdcmmmCubas.class);
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.StdcmmmCubas> sourceData = null;
					if (stdcmmmCubasModel.getSkip()==null && stdcmmmCubasModel.getTop()!=null) {
						stdcmmmCubasModel.setSkip(0);
					}
					
					if (stdcmmmCubasModel.getSkip()!=null && stdcmmmCubasModel.getTop()!=null) {
						pageable = PageUtil.pageable(stdcmmmCubasModel.getTop(),stdcmmmCubasModel.getSkip(), null);
						sourceData = stdcmmmCubasService.getstdcmmmCubas(stdcmmmCubasModel,pageable);
					} else {
						sourceData = stdcmmmCubasService.getstdcmmmCubas(stdcmmmCubasModel,null);
					}
					
					if (routingContext.request().uri().contains("skip") && stdcmmmCubasModel.getSkip()!=null&&
							stdcmmmCubasModel.getTop()==null) {
						if (stdcmmmCubasModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<stdcmmmCubasModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					stdcmmmCubasEntity = sourceData.getContent();
					List<StdcmmmCubas> stdcmmmCubaslList = new ArrayList<>();
					stdcmmmCubaslList = ObjectConverter.listConverter(stdcmmmCubasEntity, StdcmmmCubas.class);
					future.complete(stdcmmmCubaslList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				
				} else if (res.succeeded()) {
					List<StdcmmmCubas> targets = (List<StdcmmmCubas>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), StdcmmmCubas.class);
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
