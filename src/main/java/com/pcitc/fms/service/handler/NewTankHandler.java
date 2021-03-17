package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.NewTankService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.NewTank;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * 罐handler
 * @author he.yang
 *
 */
@Controller
public class NewTankHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(TankHandler.class);
	@Autowired
	private NewTankService tankService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void getTanks(RoutingContext routingContext) {
		String tankTypeCode = routingContext.request().getParam("tankTypeCode");
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.NewTank> tankEntity = new ArrayList<>();
				try {
				    NewTank modelStr = QueryParams.getQueryParams(routingContext, NewTank.class);
					Pageable pageable =null;
					Pager<com.pcitc.fms.bll.entity.NewTank> sourceData = null;
					if (modelStr.getSkip()!=null && modelStr.getTop()!=null) {
						pageable = new PageRequest(modelStr.getSkip(),modelStr.getTop(),modelStr.getOrderby());
						sourceData = tankService.getPageTanks(modelStr,pageable);
					} else {
						sourceData = tankService.getPageTanks(modelStr,null);
					}
					PageUtil.mergePage(pagination, sourceData);
					tankEntity = sourceData.getContent();
					List<NewTank> modelTank = new ArrayList<>();
					modelTank = ObjectConverter.listConverter(tankEntity, NewTank.class);
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
					List<com.pcitc.fms.service.model.NewTank> targets = (List<com.pcitc.fms.service.model.NewTank>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets,pagination, routingContext.request().uri(), com.pcitc.fms.service.model.NewTank.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			log.error("getFactories is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	}
	
}
