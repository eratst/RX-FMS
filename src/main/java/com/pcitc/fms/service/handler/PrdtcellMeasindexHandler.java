package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.PrdtcellMeasindexService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PrdtcellMeasindex;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 *
 * zhao.li
 */
@Controller
@SuppressWarnings("unchecked")
public class PrdtcellMeasindexHandler extends BaseHandler {

	@Autowired
	private PrdtcellMeasindexService prdtcellMeasindexService;

	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String idxCode = request.getParam("idxCode") == null ? null : request.getParam("idxCode").trim();
		String orderby = request.getParam("$orderby") == null ? null : request.getParam("$orderby").trim();
		String idxName = request.getParam("idxName") == null ? null : request.getParam("idxName").trim();
		String idxAlias = request.getParam("idxAbbrName") == null ? null : request.getParam("idxAbbrName").trim();
		String idxTypeCode = request.getParam("idxTypeCode") == null ? null : request.getParam("idxTypeCode").trim();
		String inUseInteger = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();
		String codes = request.getParam("$idxCodes") == null ? null : request.getParam("$idxCodes").trim();
		String cellCodesTemp = request.getParam("$cellCodes") == null ? null : request.getParam("$cellCodes").trim();
		String rentCode = request.getParam("rentCode") == null ? null : request.getParam("rentCode").trim();
		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String cellCode = request.getParam("cellCode") == null ? null : request.getParam("cellCode").trim();
		Pagination pagination = new Pagination();
		Sort sort = new Sort(Direction.DESC, "mntDate");
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeStringMybeNullException("生产单元编码", cellCode);
				CheckUtil.validateCodeStringMybeNullException("度量指标编码", idxCode);
				CheckUtil.validateNameStrMayBeNullException("度量指标名称", idxName);
				Integer inUse = CheckUtil.validateNegativeIntegerMybeNullFormat("是否启用", inUseInteger);
				List<String> codeList = CheckUtil.buildStringToListString("$codeList", codes);
				List<String> cellCodes = CheckUtil.buildStringToListString("$codeList", cellCodesTemp);

				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);
				Pageable pageable = null;
				if (top!=null &&skip!=null) {
					top = CheckUtil.checkTop(topStr);
					skip = CheckUtil.checkSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
				}
				PrdtcellMeasindex prdtcellMeasindex = new PrdtcellMeasindex(idxCode,idxName,idxAlias,idxTypeCode,inUse,orderby,codeList,cellCodes,top,skip,rentCode,bizCode,cellCode);
				Pager<com.pcitc.fms.bll.entity.PrdtcellMeasindex> sourceData;
				
				if (null != top && !"".equals(top) && skip != null && !"".equals(skip)) {
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = prdtcellMeasindexService.findPrdtcellMeasindexs(prdtcellMeasindex, pageable);
					
				} else {
					sourceData = prdtcellMeasindexService.findPrdtcellMeasindexs(prdtcellMeasindex, null);
				}
				if (routingContext.request().uri().contains("skip") && skip != null
						&& top == null) {
					if (skip>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<skip;i++) {
							sourceData.getContent().remove(0);
						}
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				List<PrdtcellMeasindex> prdtcellMeasindexModelList = ObjectConverter
						.listConverter(sourceData.getContent(), PrdtcellMeasindex.class);
				future.complete(prdtcellMeasindexModelList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}
			try {
				if (res.succeeded()) {
					collection = RestfulTool.buildCollection((List<PrdtcellMeasindex>) res.result(), pagination,
							request.uri(), PrdtcellMeasindex.class);
				} else if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
				returnCollection(routingContext, collection);
			} catch (Exception e) {
				String str = buildErrorCollection(routingContext, e);
				returnCollection(routingContext, str);
			}
		});
	}

	public void findOne(RoutingContext routingContext) {
		String idxCode = routingContext.request().getParam("idxCode") == null ? null
				: routingContext.request().getParam("idxCode").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeStringMybeNullException("度量指标编码", idxCode);
				List<com.pcitc.fms.bll.entity.PrdtcellMeasindex> prdtcellMeasindexEntityList = prdtcellMeasindexService
						.findPrdtcellMeasindexByIdxCode(idxCode);
				List<PrdtcellMeasindex> prdtcellMeasindexModelList = ObjectConverter
						.listConverter(prdtcellMeasindexEntityList, PrdtcellMeasindex.class);
				future.complete(prdtcellMeasindexModelList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}
			try {
				if (res.succeeded()) {
					collection = RestfulTool.buildCollection((List<PrdtcellMeasindex>) res.result(),
							routingContext.request().uri(), PrdtcellMeasindex.class);
				} else if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
				returnCollection(routingContext, collection);
			} catch (Exception e) {
				String str = buildErrorCollection(routingContext, e);
				returnCollection(routingContext, str);
			}
		});
	}
}
