package com.pcitc.fms.service.handler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.bll.itf.CnfgTankService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.CnfgTank;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class CnfgTankHandler extends BaseHandler{

	@Autowired
	private CnfgTankService cnfgTankService;
	
	public void findCnfgTanks(RoutingContext routingContext) {
		
		HttpServerRequest request = routingContext.request();
		String nodeCode = request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
		// 分页参数
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String codes = request.getParam("$nodeCodeList") == null ? null : request.getParam("$nodeCodeList").trim();
		String rentCode = request.getParam("rentCode") == null ? null : request.getParam("rentCode").trim();
		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
		Sort sort = new Sort(Direction.DESC, "mntDate");
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		
		vertx.executeBlocking(future -> { 
			try {
				CheckUtil.validateCodeStringMybeNullException("物料编码", nodeCode);
				List<String> codeList = CheckUtil.buildStringToListString("$nodeCodeList", codes);
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = null;
				if(StringUtils.isNotEmpty(topStr)) {
					top= CheckUtil.checkTop(topStr);
				}
				if (skip == null) {
					skip=0;
				}
				
				if (StringUtils.isEmpty(codes) && StringUtils.isEmpty(nodeCode)) {
					throw new BusiException("", "请传入nodeCode或$nodeCodeList参数！！！");
				}
				Pageable pageable = null;
				if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
					top = CheckUtil.checkTop(topStr);
					skip = CheckUtil.checkSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
				}
				CnfgTank cnfgTank = new CnfgTank(nodeCode,top,skip,codes,rentCode,bizCode);
				Pager<com.pcitc.fms.bll.entity.CnfgTank> sourceData;
				if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = cnfgTankService.findCnfgTanks(cnfgTank, pageable);
				} else {
					sourceData = cnfgTankService.findCnfgTanks(cnfgTank, null);
					
				}
				if (routingContext.request().uri().contains("skip") && skip!=null&&
						top==null) {
					if (cnfgTank.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<cnfgTank.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
						sourceData.setTotalElements((long)sourceData.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				List<CnfgTank> modelList = ObjectConverter.listConverter(sourceData.getContent(), CnfgTank.class);
			
				future.complete(modelList);
			}catch(Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<CnfgTank>) res.result(), pagination, request.uri(),
							CnfgTank.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
}
