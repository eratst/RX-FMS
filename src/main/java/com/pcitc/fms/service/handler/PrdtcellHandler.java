package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.PrdtcellService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Prdtcell;
import com.pcitc.fms.service.model.TankArea;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


@Controller
public class PrdtcellHandler extends BaseHandler {
	
	@Autowired
	private PrdtcellService prdtcellService;
	
	
	/**
	 * 查询全部对象
	 */
	@SuppressWarnings("unchecked")
	public void getAllPrdtcell(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String orderby = request.getParam("$orderby") == null ? null : request.getParam("$orderby").trim();

		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String codeListTemp = request.getParam("$cellCodes") == null ? null : request.getParam("$cellCodes").trim();
		String plantCodesTemp = request.getParam("$plantCodes") == null ? null : request.getParam("$plantCodes").trim();
		String rentCode = request.getParam("rentCode") == null ? null : request.getParam("rentCode").trim();
		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
		String cellCode = request.getParam("cellCode") == null ? null : request.getParam("cellCode").trim();
		String cellName = request.getParam("cellName") == null ? null : request.getParam("cellName").trim();
		String cellAbbrName = request.getParam("cellAbbrName") == null ? null : request.getParam("cellAbbrName").trim();
		String orgAlias = request.getParam("orgAlias") == null ? null : request.getParam("orgAlias").trim();
		String areaAlias = request.getParam("areaAlias") == null ? null : request.getParam("areaAlias").trim();
		String dataStautsStr = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();
		String plantCode = request.getParam("plantCode") == null ? null : request.getParam("plantCode").trim();
		Sort sort = new Sort(Direction.ASC, "mntDate");
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			// 将String类型的codeList转化为List
			List<String> codeList = new ArrayList<>();
			List<String> plantCodes = new ArrayList<>();
			if (StringUtils.isNotEmpty(codeListTemp)) {
				codeList = Arrays.asList(codeListTemp.split(","));
			}
			if (StringUtils.isNotEmpty(plantCodesTemp)) {
				plantCodes = Arrays.asList(plantCodesTemp.split(","));
			}
			List<Prdtcell> model_pmPrdtcells = new ArrayList<>();

			try {
				CheckUtil.validateCodeStringMybeNullException("装置编码", plantCode);
				CheckUtil.validateCodeStringMybeNullException("生产单元编码", cellCode);
				CheckUtil.validateNameStrMayBeNullException("生产单元名称", cellName);
				CheckUtil.validateNameStrMayBeNullException("生产单元简称", cellAbbrName);
				CheckUtil.validateNameStrMayBeNullException("组织机构简称", orgAlias);
				CheckUtil.validateNameStrMayBeNullException("区域简称", areaAlias);
				Integer inUse = CheckUtil.validateNegativeIntegerMybeNullFormat("是否起用", dataStautsStr);
				// 将skipStr，topStr转为Integer
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);
				// 配置分页参数
				Pageable pageable = null;
				if ((null != top && skip!= null)) {
					top = CheckUtil.checkTop(topStr);
					skip = CheckUtil.checkSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
				}
				// 配置动态查询参数
				Prdtcell pmPrdtcell = new Prdtcell(cellCode,cellName,cellAbbrName,
						inUse, codeList,plantCodes,top,skip,orderby,rentCode,bizCode,plantCode);
				Pager<com.pcitc.fms.bll.entity.Prdtcell> pager_pmPrdtcells = null;
				// 判断是否分页
				if (null != top && skip!= null) {
					// 分页查询
					pager_pmPrdtcells = prdtcellService.getPrdtcellByParam(pmPrdtcell, pageable);
					PageUtil.mergePage(pagination, pager_pmPrdtcells);
				} else {
					// 不分页查询
					pager_pmPrdtcells = prdtcellService.getPrdtcellByParam(pmPrdtcell, null);
					PageUtil.mergePage(pagination, pager_pmPrdtcells);
				}
				 if (routingContext.request().uri().contains("skip") && skip != null
							&& top == null) {
						if (skip>=pager_pmPrdtcells.getContent().size()) {
							pager_pmPrdtcells.setContent(null);
						} else {
								for (int i=0;i<skip;i++) {
									pager_pmPrdtcells.getContent().remove(0);
								}
						}
					}

				model_pmPrdtcells = ObjectConverter.listConverter(pager_pmPrdtcells.getContent(), Prdtcell.class);
				future.complete(model_pmPrdtcells);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				List<Prdtcell> targets =(List<Prdtcell>) res.result();
				try {
					String linkNames = "prdtCellMeasurements";
					targets = (List<Prdtcell>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
					
					collection = RestfulTool.buildCollection(targets, pagination,
							routingContext.request().absoluteURI(), Prdtcell.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
	
	/**
	 * 条件查询对象
	 */
	@SuppressWarnings("unchecked")
	public void getPrdtcellByCellCode(RoutingContext routingContext) {
		String cellCode = routingContext.request().getParam("cellCode") == null ? null
				: routingContext.request().getParam("cellCode").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.Prdtcell> entity_pmPrdtcell = new ArrayList<>();
			List<Prdtcell> model_pmPrdtcells = new ArrayList<>();
			try {
				CheckUtil.validateCodeStringMybeNullException("生产单元编码", cellCode);
				entity_pmPrdtcell = prdtcellService.getPrdtcellByCellCode(cellCode);
				model_pmPrdtcells = ObjectConverter.listConverter(entity_pmPrdtcell, Prdtcell.class);
				future.complete(model_pmPrdtcells);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<Prdtcell>) res.result(), routingContext.request().uri(),
							Prdtcell.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	
}
