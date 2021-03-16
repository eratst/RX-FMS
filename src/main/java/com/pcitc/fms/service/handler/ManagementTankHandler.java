package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.ManagementTankService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.ManagementTank;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class ManagementTankHandler extends BaseHandler {

	@Autowired
	private ManagementTankService managementTankService;

	@SuppressWarnings("unchecked")
	public void getManagementTanks(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();

		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String orgCodes = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
		String nodeCodes = request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
		String tankAreas = request.getParam("tankArea") == null ? null : request.getParam("tankArea").trim();
		String dataStautsStr = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();

		Sort sort = new Sort(Sort.Direction.ASC, "nodeCode");
		Pagination pagination = new Pagination();

		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				Integer dataStauts = null ;
				if (StringUtils.isNotEmpty(dataStautsStr)) {
					dataStauts = Integer.parseInt(dataStautsStr);
				}
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
				Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
				List<String> orgCodeList = new ArrayList<>();
				List<String> nodeCodeList = new ArrayList<>();
				List<String> tankAreaList = new ArrayList<>();
				if (StringUtils.isNotEmpty(orgCodes)) {
					orgCodeList = Arrays.asList(orgCodes.split(","));
				}
				if (StringUtils.isNotEmpty(nodeCodes)) {
					nodeCodeList = Arrays.asList(nodeCodes.split(","));
				}
				if (StringUtils.isNotEmpty(tankAreas)) {
					tankAreaList = Arrays.asList(tankAreas.split(","));
				}

				ManagementTank managementTank = new ManagementTank(orgCodeList, bizCode, nodeCodeList, tankAreaList,
						top, skip, dataStauts);

				Pager<com.pcitc.fms.bll.entity.ManagementTank> sourceData;
				List<ManagementTank> modelManagementTanks = new ArrayList<>();
				Pageable pageable = null;
				if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = managementTankService.getManagementTanks(managementTank, pageable);
					PageUtil.mergePage(pagination, sourceData);
				} else {
					sourceData = managementTankService.getManagementTanks(managementTank, null);
					PageUtil.mergePage(pagination, sourceData);
				}
				modelManagementTanks = ObjectConverter.listConverter(sourceData.getContent(), ManagementTank.class);
				future.complete(modelManagementTanks);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collecion = null;
			if (res.failed()) {
				collecion = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collecion);
			} else if (res.succeeded()) {
				try {
					collecion = RestfulTool.buildCollection((List<ManagementTank>) res.result(),pagination,
							routingContext.request().uri(), ManagementTank.class);
					returnCollection(routingContext, collecion);
				} catch (Exception e) {
					collecion = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collecion);
				}
			}
		});
	}
}
