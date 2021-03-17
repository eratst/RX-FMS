package com.pcitc.fms.service.handler;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.ManagementMtrlService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.ManagementMtrl;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class ManagementMtrlHandler extends BaseHandler {
	
	@Autowired
	private ManagementMtrlService managementMtrlService;
	
	
	@SuppressWarnings("unchecked")
	public void getAllManagementMtrls(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String uriStr = request.uri().toString();
		String mtrlCode = request.getParam("mtrlCode") == null ? null : request.getParam("mtrlCode");
		String mtrlTypeCode = request.getParam("mtrlTypeCode") == null ? null : request.getParam("mtrlTypeCode");
		String dataStautsStr = request.getParam("inUse") == null ? null : request.getParam("inUse");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
	    String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
	    Sort sort = new Sort(Sort.Direction.ASC, "mtrlId");
	    Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			try {
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
		        Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
		        Integer dataStauts = null;
		        if (StringUtils.isNotEmpty(dataStautsStr)) {
		        	dataStauts = Integer.parseInt(dataStautsStr);
		        }
		        ManagementMtrl managementMtrl = new ManagementMtrl(mtrlCode, mtrlTypeCode, top, skip,dataStauts);
		        
		        Pageable pageable = null;
		        Pager<com.pcitc.fms.bll.entity.ManagementMtrl> sourceData;
		          if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
		            pageable = PageUtil.pageable(top, skip, sort);
		            sourceData = managementMtrlService.getPageManagementMtrl(uriStr,managementMtrl, pageable);
		            PageUtil.mergePage(pagination, sourceData);
		          } else {
		            sourceData = managementMtrlService.getPageManagementMtrl(uriStr,managementMtrl, null);
		            PageUtil.mergePage(pagination, sourceData);
		          }
				List<ManagementMtrl> modelManagementMtrls = ObjectConverter.listConverter(sourceData.getContent(), ManagementMtrl.class);
				future.complete(modelManagementMtrls);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res ->{
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<ManagementMtrl>) res.result(),pagination, routingContext.request().uri(), ManagementMtrl.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			// 结果输出
			returnCollection(routingContext, collection);
		});
	}
}
