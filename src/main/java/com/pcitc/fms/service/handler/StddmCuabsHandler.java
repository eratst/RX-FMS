package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.StddmCuabsService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StddmCuabs;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class StddmCuabsHandler extends BaseHandler{
	@Autowired
	private StddmCuabsService cuabsService;
	
	public void getStddmCuabs(RoutingContext routingContext){
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.StddmCuabs> stddmCuabsEntity = new ArrayList<>();
				try {
					StddmCuabs stddmCuabsModel = QueryParams.getQueryParams(routingContext, StddmCuabs.class);
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.StddmCuabs> sourceData = null;
					if (stddmCuabsModel.getSkip()==null && stddmCuabsModel.getTop()!=null) {
						stddmCuabsModel.setSkip(0);
					}
					
					if (stddmCuabsModel.getSkip()!=null && stddmCuabsModel.getTop()!=null) {
						pageable = PageUtil.pageable(stddmCuabsModel.getTop(),stddmCuabsModel.getSkip(), null);
						sourceData = cuabsService.getPageStddmCuabs(stddmCuabsModel,pageable);
					} else {
						sourceData = cuabsService.getPageStddmCuabs(stddmCuabsModel,null);
					}
					
					if (routingContext.request().uri().contains("skip") && stddmCuabsModel.getSkip()!=null&&
							stddmCuabsModel.getTop()==null) {
						if (stddmCuabsModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<stddmCuabsModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					stddmCuabsEntity = sourceData.getContent();
					List<StddmCuabs> lieCubasModelList = new ArrayList<>();
					lieCubasModelList = ObjectConverter.listConverter(stddmCuabsEntity, StddmCuabs.class);
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
					List<StddmCuabs> targets = (List<StddmCuabs>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), StddmCuabs.class);
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
