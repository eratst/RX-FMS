package com.pcitc.fms.service.handler;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import com.pcitc.fms.bll.itf.OrgFindAreaService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.AreaDictionary;
import com.pcitc.fms.service.model.Department;
import com.pcitc.fms.service.model.Material;
import com.pcitc.fms.service.model.OrgFindAreaDictionary;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

/*
 * 查询所有区域
 */
@Controller
public class OrgFindAreaHandler extends BaseHandler{
	private static Logger log = LoggerFactory.getLogger(AdministrationHandler.class);
	@Autowired
	private OrgFindAreaService orgFindAreaService;
	
	@SuppressWarnings("unchecked")
	public void getOrgFindArea(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		//查询有关联关系的物料
//		String relSourceCodes = request.getParam("$relSourceCodes") == null ? null : request.getParam("$relSourceCodes").trim();
		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		String areaCode = request.getParam("areaCode") == null ? null : request.getParam("areaCode").trim();
		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
		String enabled = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
		String type = request.getParam("type") == null ? null : request.getParam("type").trim();
		//获取分页的参数
		String topStr=request.getParam("$top")==null?null:request.getParam("$top").trim();
		String skipStr=request.getParam("$skip")==null?null:request.getParam("$skip").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "areaId");
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			try {
				List<com.pcitc.fms.bll.entity.AreaDictionary> areaDictionaryList = new ArrayList<>();
				com.pcitc.fms.service.model.AreaDictionary AreaDictionaryTableModel = new com.pcitc.fms.service.model.AreaDictionary();
				List<String> codeList = CheckUtil.buildStringToListString("$codeList",codes);
				if(areaCode != null || name != null || shortName != null){
					AreaDictionaryTableModel.setAreaCode(areaCode);
					AreaDictionaryTableModel.setName(name);
					AreaDictionaryTableModel.setShortName(shortName);
				}
				if(enabled !=null ){
					AreaDictionaryTableModel.setEnabled(Integer.valueOf(enabled));
				}
				if(codeList != null){
					AreaDictionaryTableModel.setCodeList(codeList);
				}
				if(type != null){
					AreaDictionaryTableModel.setType(type);
				}
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
				Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);Pageable pageable = null;
				if(StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)){	
					top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
					skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
				    pageable = PageUtil.pageable(top, skip, sort);
	    		}
				Pager<com.pcitc.fms.bll.entity.AreaDictionary> sourceData;
				if(null != topStr&&!"".equals(topStr)&&skipStr!=null&&!"".equals(skipStr)){
					AreaDictionaryTableModel.setSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = orgFindAreaService.getAreaDictionarys(AreaDictionaryTableModel,pageable,orgCode);
					PageUtil.mergePage(pagination, sourceData);
				}else{
					 List<String> relSourceCodeList = CheckUtil.buildStringToListString("orgCode", orgCode);
//					 areaDictionaryList = orgFindAreaService.getMeasurementForRelation(relSourceCodeList);
					 sourceData = orgFindAreaService.getAreaDictionarys(AreaDictionaryTableModel,pageable,orgCode);
				}
				areaDictionaryList=sourceData.getContent();
				future.complete(areaDictionaryList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res ->{
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<com.pcitc.fms.service.model.AreaDictionary> targets = new ArrayList<>();
				try {
					List<OrgFindAreaDictionary> departmentList = (List<OrgFindAreaDictionary>) res.result();
					List<com.pcitc.fms.service.model.OrgFindAreaDictionary> target = ObjectConverter.listConverter(departmentList, com.pcitc.fms.service.model.OrgFindAreaDictionary.class);
					collection = RestfulTool.buildCollection(target, pagination, hrefBase, com.pcitc.fms.service.model.OrgFindAreaDictionary.class);
				} catch (Exception e) {
					log.error("getUnits is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getDepartments is end!");
			}
		});
	}

	/* @Description
	 * parameter  routingContext
   * @DATE 2018/1/2
   * retur void
   **/
	public void getOrgFindAreaNodes(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		String areaCode = request.getParam("areaCode") == null ? null : request.getParam("areaCode").trim();
		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
		String enabled = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
		String type = request.getParam("type") == null ? null : request.getParam("type").trim();
		//获取分页的参数
		String topStr=request.getParam("$top")==null?null:request.getParam("$top").trim();
		String skipStr=request.getParam("$skip")==null?null:request.getParam("$skip").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "areaId");
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			try {
				List<com.pcitc.fms.bll.entity.AreaDictionary> areaDictionaryList = new ArrayList<>();
				com.pcitc.fms.service.model.AreaDictionary AreaDictionaryTableModel = new com.pcitc.fms.service.model.AreaDictionary();
				List<String> codeList = CheckUtil.buildStringToListString("$codeList",codes);
				if(areaCode != null || name != null || shortName != null){
					AreaDictionaryTableModel.setAreaCode(areaCode);
					AreaDictionaryTableModel.setName(name);
					AreaDictionaryTableModel.setShortName(shortName);
				}
				if(enabled !=null ){
					AreaDictionaryTableModel.setEnabled(Integer.valueOf(enabled));
				}
				if(codeList != null){
					AreaDictionaryTableModel.setCodeList(codeList);
				}
				if(type != null){
					AreaDictionaryTableModel.setType(type);
				}
				Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
				Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);Pageable pageable = null;
				if(StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)){
					top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
					skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
				}
				Pager<com.pcitc.fms.bll.entity.AreaDictionary> sourceData;
				if(null != topStr&&!"".equals(topStr)&&skipStr!=null&&!"".equals(skipStr)){
					AreaDictionaryTableModel.setSkip(skipStr);
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = orgFindAreaService.getOrgFindAreaNodes(AreaDictionaryTableModel,pageable,orgCode);
					PageUtil.mergePage(pagination, sourceData);
				}else{
					List<String> relSourceCodeList = CheckUtil.buildStringToListString("orgCode", orgCode);
					sourceData = orgFindAreaService.getOrgFindAreaNodes(AreaDictionaryTableModel,pageable,orgCode);
				}
				areaDictionaryList=sourceData.getContent();
				future.complete(areaDictionaryList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res ->{
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<com.pcitc.fms.service.model.AreaDictionary> targets = new ArrayList<>();
				try {
					List<OrgFindAreaDictionary> departmentList = (List<OrgFindAreaDictionary>) res.result();
					List<com.pcitc.fms.service.model.OrgFindAreaDictionary> target = ObjectConverter.listConverter(departmentList, com.pcitc.fms.service.model.OrgFindAreaDictionary.class);
					collection = RestfulTool.buildCollection(target, pagination, hrefBase, com.pcitc.fms.service.model.OrgFindAreaDictionary.class);
				} catch (Exception e) {
					log.error("getUnits is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getDepartments is end!");
			}
		});
	}

}
