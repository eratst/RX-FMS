package com.pcitc.fms.service.handler;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.service.model.OrgTree;
import com.pcitc.fms.service.model.Rent;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class TPmOrgHandler extends BaseHandler {

	@Autowired
	private TPmOrgService tPmOrgService;

	@Autowired
	private RentDao rentDao;

	@SuppressWarnings("unchecked")
	public void getTrees(RoutingContext routingContext) {
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {

			try {
				String isPlatformRent = routingContext.request().getParam("isPlatformRent");
				if (StringUtils.isEmpty(isPlatformRent)) {
					isPlatformRent = "1";
				}
				Rent rent = QueryParams.getQueryParams(routingContext, Rent.class);
				com.pcitc.fms.dal.pojo.Rent pojoRent = rentDao.getRentByRentCode(rent.getRentCode());
				if (pojoRent==null) {
					throw new BusiException("", rent.getRentCode() + "：该租户不存在！");
				}else if (pojoRent.getOrgId()==null) {
					throw new BusiException("", rent.getRentCode() + "未在标准组织组织机构树上指定位置");
				}
				List<com.pcitc.fms.bll.entity.OrgTree> entityOrgTrees = new ArrayList<>();
				//判断是否是平台租户
				if (isPlatformRent.equals("1")) {
					entityOrgTrees = tPmOrgService.getAllTrees(pojoRent.getOrgId());
				} else if (isPlatformRent.equals("0")) {
					entityOrgTrees = tPmOrgService.getBranchTrees(pojoRent.getOrgId());
				}
				List<OrgTree> results = ObjectConverter.listConverter(entityOrgTrees, OrgTree.class);
				future.complete(results);
			} catch (Exception e) {
				future.fail(e);
				e.printStackTrace();
			}

		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<OrgTree>) res.result(), routingContext.request().uri(),
							com.pcitc.fms.service.model.OrgTree.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
			}
		});
	}

}
