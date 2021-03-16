package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.DeltcnfgService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Deltcnfg;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class DeltcnfgHandler extends BaseHandler{
	@Autowired
	private DeltcnfgService deltcnfgService;
	
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
				List<com.pcitc.fms.bll.entity.Deltcnfg> DeltcnfgEntity = new ArrayList<>();
				try {
					Deltcnfg deltcnfgModel = QueryParams.getQueryParams(routingContext, Deltcnfg.class);
					String str_deltcnfgId = routingContext.request().getParam("deltcnfgId") == null ? null
							: routingContext.request().getParam("deltcnfgId").trim();
					if(StringUtils.isNotEmpty(str_deltcnfgId)){
						deltcnfgModel.setDeltcnfgId(Long.valueOf(str_deltcnfgId));
					}
					
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.Deltcnfg> sourceData = null;
					
					if (deltcnfgModel.getSkip()==null && deltcnfgModel.getTop()!=null) {
						deltcnfgModel.setSkip(0);
					}
					if (deltcnfgModel.getSkip()!=null && deltcnfgModel.getTop()!=null) {
						pageable = PageUtil.pageable(deltcnfgModel.getTop(),deltcnfgModel.getSkip(), null);
						sourceData = deltcnfgService.getPageDeltcnfg(deltcnfgModel,pageable);
					} else {
						sourceData = deltcnfgService.getPageDeltcnfg(deltcnfgModel,null);
					}
					
					if (routingContext.request().uri().contains("skip") && deltcnfgModel.getSkip()!=null&&
							deltcnfgModel.getTop()==null) {
						if (deltcnfgModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<deltcnfgModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					DeltcnfgEntity = sourceData.getContent();
					List<Deltcnfg> DeltcnfgModelList = new ArrayList<>();
					DeltcnfgModelList = ObjectConverter.listConverter(DeltcnfgEntity, Deltcnfg.class);
					future.complete(DeltcnfgModelList);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				
				} else if (res.succeeded()) {
					List<Deltcnfg> targets = (List<Deltcnfg>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), Deltcnfg.class);
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
