package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.ManagementTankAreaService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.ManagementTankArea;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.netty.util.internal.StringUtil;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class ManagementTankAreaHandler extends BaseHandler {

	@Autowired
	private ManagementTankAreaService managementTankAreaService;
	
	@SuppressWarnings("unchecked")
	public void getManagementTankAreas(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String orgCodes = request.getParam("orgCodeList") == null ? null : request.getParam("orgCodeList").trim();
		String tankAreaCodes = request.getParam("tankAreaCodeList") == null ? null : request.getParam("tankAreaCodeList").trim();
		String inUse = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();
		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
	    String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
	    Sort sort = new Sort(Sort.Direction.ASC, "areaName");
	    Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			try {
				List<String> orgCodeList = new ArrayList<>();
				List<String> tankAreaCodeList = new ArrayList<>();
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
		        Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
		        if(StringUtils.isNotEmpty(orgCodes)){
		        	orgCodeList=Arrays.asList(orgCodes.split(","));
		        }
		        if(StringUtils.isNotEmpty(tankAreaCodes)){
		        	tankAreaCodeList=Arrays.asList(tankAreaCodes.split(","));
		        }
		        ManagementTankArea managementTankArea = new ManagementTankArea(top, skip);
		        managementTankArea.setTankAreaCodeList(tankAreaCodeList);
		        managementTankArea.setOrgCodeList(orgCodeList);
		        managementTankArea.setInUse(CheckUtil.validateIdIntegerMybeNullException("区域状态",inUse));
		        
		        Pageable pageable = null;
		        Pager<com.pcitc.fms.bll.entity.ManagementTankArea> sourceData;
		          if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
		            pageable = PageUtil.pageable(top, skip, sort);
		            sourceData = managementTankAreaService.getManagementTankAreas(managementTankArea, pageable);
		            PageUtil.mergePage(pagination, sourceData);
		          } else {
		            sourceData = managementTankAreaService.getManagementTankAreas(managementTankArea, null);
		            PageUtil.mergePage(pagination, sourceData);
		          }
		          List<ManagementTankArea> modelManagementTankAreas = new ArrayList<>();
		          List<com.pcitc.fms.bll.entity.ManagementTankArea> entityResult = sourceData.getContent();
		          modelManagementTankAreas = ObjectConverter.listConverter(entityResult, ManagementTankArea.class);
		          future.complete(modelManagementTankAreas);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res ->{
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<ManagementTankArea>) res.result(),pagination, routingContext.request().uri(), ManagementTankArea.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			// 结果输出
			returnCollection(routingContext, collection);
		});
	}
}
