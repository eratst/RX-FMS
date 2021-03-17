package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TankCnfgService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TankCnfg;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
import org.apache.commons.lang3.StringUtils;

/**
 * 单罐配置对象Handler
 * 
 * @author chao.guo
 *
 * @author guobin.chen
 * @since 2018-03-19
 */
@SuppressWarnings("unchecked")
@Controller
public class TankCnfgHandler extends BaseHandler {

	@Autowired
	private TankCnfgService tankCnfgService;

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
				List<com.pcitc.fms.bll.entity.TankCnfg> TankCnfgEntity = new ArrayList<>();
				try {
					TankCnfg tankCnfgModel = QueryParams.getQueryParams(routingContext, TankCnfg.class);
					String str_tnkCnfgId = routingContext.request().getParam("tankCnfgId") == null ? null
							: routingContext.request().getParam("tankCnfgId").trim();
					
					if(StringUtils.isNotEmpty(str_tnkCnfgId)){
						tankCnfgModel.setTankCnfgId(Long.valueOf(str_tnkCnfgId));
					}
				    Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.TankCnfg> sourceData = null;
					
					if (tankCnfgModel.getSkip()==null && tankCnfgModel.getTop()!=null) {
						tankCnfgModel.setSkip(0);
					}
					if (tankCnfgModel.getSkip()!=null && tankCnfgModel.getTop()!=null) {
						pageable = PageUtil.pageable(tankCnfgModel.getTop(),tankCnfgModel.getSkip(), null);
						sourceData = tankCnfgService.getPageTankCnfg(tankCnfgModel,pageable);
					} else {
						sourceData = tankCnfgService.getPageTankCnfg(tankCnfgModel,null);
					}
					
					if (routingContext.request().uri().contains("skip") && tankCnfgModel.getSkip()!=null&&
							tankCnfgModel.getTop()==null) {
						if (tankCnfgModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<tankCnfgModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
							sourceData.setTotalElements((long)sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					TankCnfgEntity = sourceData.getContent();
					List<TankCnfg> StdSecModelList = new ArrayList<>();
					StdSecModelList = ObjectConverter.listConverter(TankCnfgEntity, TankCnfg.class);
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
					List<TankCnfg> targets = (List<TankCnfg>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), TankCnfg.class);
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
