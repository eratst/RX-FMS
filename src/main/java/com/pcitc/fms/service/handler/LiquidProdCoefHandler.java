package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.LiquidProdCoef;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.bll.itf.LiquidProdCoefService;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class LiquidProdCoefHandler extends BaseHandler{

	@Autowired
	private LiquidProdCoefService liquidProdCoefService;
	
	@SuppressWarnings("unchecked")
	public void findLiquidProdCoefs(RoutingContext routingContext) {
		
		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String mtrlCode = request.getParam("mtrlCode") == null ? null : request.getParam("mtrlCode").trim();
		String mtrlName = request.getParam("mtrlName") == null ? null : request.getParam("mtrlName").trim();
		String codes = request.getParam("$mtrlCodeList") == null ? null : request.getParam("$mtrlCodeList").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String orderby = request.getParam("$orderby") == null ? null : request.getParam("$orderby").trim();

		Sort sort = new Sort(Direction.DESC, "mntDate");
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeStringMybeNullException("物料编码", mtrlCode);
				CheckUtil.validateNameStrMayBeNullException("物料名称", mtrlName);
				List<String> codeList = CheckUtil.buildStringToListString("$codeList", codes);
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);
				Pageable pageable = null;
				if(uri.contains("$top") && topStr==null){
					pageable=null;
				}
				if (null != topStr && !"".equals(topStr) && null != skip && !"".equals(skip)) {
					pageable = PageUtil.pageable(top, skip, sort);
				}
				LiquidProdCoef liquidProdCoef = new LiquidProdCoef(mtrlCode, mtrlName, top, skip,codeList,orderby);
				Pager<com.pcitc.fms.bll.entity.LiquidProdCoef> liqProdCoefEn = liquidProdCoefService.findLiquidProdCoefs(liquidProdCoef, pageable);;
				List<com.pcitc.fms.bll.entity.LiquidProdCoef> list=new ArrayList<>();
				if(uri.contains("$skip") && skip!=null && top==null){
					if (liquidProdCoef.getSkip()>=liqProdCoefEn.getContent().size()) {
						liqProdCoefEn.setContent(null);
					} else {
						for (int i=0;i<liquidProdCoef.getSkip();i++) {
							liqProdCoefEn.getContent().remove(0);
						}
						liqProdCoefEn.setTotalElements((long)liqProdCoefEn.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, liqProdCoefEn);
				List<LiquidProdCoef> liqProdCoefModel = ObjectConverter.listConverter(liqProdCoefEn.getContent(),
						LiquidProdCoef.class);
				future.complete(liqProdCoefModel);
			}catch(Exception e) { 
				future.fail(e);
			}
			
		}, res -> {
			
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}
			try {
				if (res.succeeded()) {
					collection = RestfulTool.buildCollection((List<LiquidProdCoef>) res.result(), pagination,
							routingContext.request().absoluteURI(), LiquidProdCoef.class);
				} else {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
				returnCollection(routingContext, collection);
			} catch (Exception e) {
				String collecion = buildErrorCollection(routingContext, e);
				returnCollection(routingContext, collecion);
			}
		});
	}
}
