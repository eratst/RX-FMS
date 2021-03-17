package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.StdSecService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StdSec;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class StdSecHandler extends BaseHandler {

	@Autowired
	private StdSecService stdSecService;
	
	public void getStdSec(RoutingContext routingContext){
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.StdSec> StdSecEntity = new ArrayList<>();
				try {
					StdSec stdSecModel = QueryParams.getQueryParams(routingContext, StdSec.class);
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.StdSec> sourceData = null;
					
					if (stdSecModel.getSkip()==null && stdSecModel.getTop()!=null) {
						stdSecModel.setSkip(0);
					}
					if (stdSecModel.getSkip()!=null && stdSecModel.getTop()!=null) {
						pageable = PageUtil.pageable(stdSecModel.getTop(),stdSecModel.getSkip(), null);
						sourceData = stdSecService.getPageStdSec(stdSecModel,pageable);
					} else {
						sourceData = stdSecService.getPageStdSec(stdSecModel,null);
					}
					
					if (routingContext.request().uri().contains("skip") && stdSecModel.getSkip()!=null&&
							stdSecModel.getTop()==null) {
						if (stdSecModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<stdSecModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					StdSecEntity = sourceData.getContent();
					List<StdSec> StdSecModelList = new ArrayList<>();
					StdSecModelList = ObjectConverter.listConverter(StdSecEntity, StdSec.class);
					future.complete(StdSecModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				
				} else if (res.succeeded()) {
					List<StdSec> targets = (List<StdSec>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), StdSec.class);
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
