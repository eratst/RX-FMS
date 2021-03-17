package com.pcitc.fms.service.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TankBaseInfoService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TankBaseInfo;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class TankBaseInfoHandler extends BaseHandler {

	@Autowired
	TankBaseInfoService tankBaseInfoService;
	
	@SuppressWarnings("unchecked")
	public void getTankBaseInfo(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		
		//获取分页的参数
		String topStr=request.getParam("$top")==null?null:request.getParam("$top").trim();
		String skipStr=request.getParam("$skip")==null?null:request.getParam("$skip").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "nodeId");
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		
		vertx.executeBlocking(future -> {
			
			try {
				List<Integer> idList = CheckUtil.buildStringToListInteger("$idList",ids);
				List<String> codeList = CheckUtil.buildStringToListString("$codeList",codes);
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
				Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
				Pageable pageable = null;
				if(StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)){	
					top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
					skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
				    pageable = PageUtil.pageable(top, skip, sort);
	    		}
				TankBaseInfo tankmo = new TankBaseInfo(codeList,idList,top,skip);
				Pager<com.pcitc.fms.bll.entity.TankBaseInfo> sourceData;
				if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = tankBaseInfoService.getTankBaseInfo(tankmo, pageable);	
					System.out.println(sourceData);
					PageUtil.mergePage(pagination, sourceData);
				} else {
					sourceData = tankBaseInfoService.getTankBaseInfo(tankmo, null);		
					System.out.println(sourceData.getContent());
					PageUtil.mergePage(pagination, sourceData);
				}
				List<TankBaseInfo> tankBaseInfos = ObjectConverter.listConverter(sourceData.getContent(), TankBaseInfo.class);
				future.complete(tankBaseInfos);
				
			} catch (Exception e) {
				future.fail(e);
			}
			
		}, res -> {
			
			String collection = null;
			if(res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}else if(res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<TankBaseInfo>)res.result(), pagination, routingContext.request().uri(), TankBaseInfo.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
}
