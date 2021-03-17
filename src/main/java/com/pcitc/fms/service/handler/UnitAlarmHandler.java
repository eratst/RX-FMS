package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.UnitAlarmService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UnitAlarm;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
@Controller
public class UnitAlarmHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(UnitAlarmHandler.class);
	@Autowired
	UnitAlarmService unitAlarmService;
	
	
	
	@SuppressWarnings("unchecked")
	public void getUnitAlarms(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String hrefBase = request.uri();
		log.info("*** UnitAlarmHandler START getUnitAlarms ***");
		//条件查询参数
		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
		String inUseStr = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();
		String unitCode = request.getParam("unitCode") == null ? null : request.getParam("unitCode").trim();
		//集合与分页查询参数
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "unitAlarmId");
		Pagination pagination = new Pagination();
		
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.UnitAlarm> communityEntity = null;
				try {
				    //校验条件查询参数,可以空字段
				    String unitAlarmName = CheckUtil.getParamForString("name", name, 50, true);
				    String unitAlarmShortName = CheckUtil.getParamForString("shortName", shortName, 50, true);
				    Integer inUse = CheckUtil.enabled("enabled", inUseStr);
				    //校验集合与分页查询参数,可以空字段
				    List<String> codeList = CheckUtil.checkCodeList(codes);
					Integer top = CheckUtil.getParamForInteger("top", topStr, 6, true);
					Integer skip = CheckUtil.getParamForInteger("skip", skipStr, 6, true);
					String unitCodes = CheckUtil.getParamForString("unitCode", unitCode, 50, true);
					//分页校验
					Pageable pageable = null;
					if (null != topStr && !"".equals(topStr) && null != skip) {
						pageable = PageUtil.pageable(top, skip, sort);
					}
					//组装办公区实体
					com.pcitc.fms.service.model.UnitAlarm model = new com.pcitc.fms.service.model.UnitAlarm(unitAlarmName, unitAlarmShortName, inUse, top, skip, codeList, unitCodes);
					
					//开始查询
					Pager<com.pcitc.fms.bll.entity.UnitAlarm> sourceData = unitAlarmService.getPageUnitAlarms(model);
					PageUtil.mergePage(pagination, sourceData);
					communityEntity  = sourceData.getContent();
					future.complete(communityEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					try {
						List<com.pcitc.fms.bll.entity.UnitAlarm> unitAlarmEntityList = (List<com.pcitc.fms.bll.entity.UnitAlarm>) res.result();
						List<com.pcitc.fms.service.model.UnitAlarm> modelList = ObjectConverter.listConverter(unitAlarmEntityList, com.pcitc.fms.service.model.UnitAlarm.class);
						collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, UnitAlarm.class);
					} catch (Exception e) {
						log.error("getUnitAlarms is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getUnitAlarms is end!");
				}
			});
		} catch (Exception e) {
			log.error("getUnitAlarms is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	
	//hanxiao
		public void getUnitAlarm(RoutingContext routingContext) {

			HttpServerRequest request = routingContext.request();
			String uri = request.absoluteURI();
			String hrefBase = request.uri();
			String codeStr = request.getParam("code")==null?null:request.getParam("code").trim(); 
			log.info("*** UnitAlarmHandler START getCommunity ***");
			try {
				Vertx vertx = routingContext.vertx();
				vertx.executeBlocking(future -> {
					try {
					    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
						com.pcitc.fms.bll.entity.UnitAlarm entity = unitAlarmService.getByCode(code);
						com.pcitc.fms.service.model.UnitAlarm model = ObjectConverter.entityConverter(entity, com.pcitc.fms.service.model.UnitAlarm.class);
						future.complete(model);
					} catch (Exception e) {
						future.fail(e);
					}
				}, res -> {
					String collection = null;// 返回结果字符串
					if (res.failed()) {
						collection = buildErrorCollection(routingContext, (Exception) res.cause());
						returnCollection(routingContext, collection);
					} else if (res.succeeded()) {
						try {
							List<com.pcitc.fms.bll.entity.UnitAlarm> unitAlarmEntityList = (List<com.pcitc.fms.bll.entity.UnitAlarm>) res.result();
							List<com.pcitc.fms.service.model.UnitAlarm> modelList = ObjectConverter.listConverter(unitAlarmEntityList, com.pcitc.fms.service.model.UnitAlarm.class);
							collection = RestfulTool.buildCollection(modelList,  hrefBase, UnitAlarm.class);
						} catch (Exception e) {
							log.error("getUnitAlarm is Exception!");
							collection = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collection);
						}
						returnCollection(routingContext, collection);
						log.info("getUnitAlarm is end!");
					}
				});
			} catch (Exception e) {
				log.error("getCommunity is Exception!");
				String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
				returnCollection(routingContext, collection);
			}

		}

	
}
