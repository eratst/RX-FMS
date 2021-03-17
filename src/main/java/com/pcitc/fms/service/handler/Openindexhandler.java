package com.pcitc.fms.service.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.DeupdownlimitService;
import com.pcitc.fms.bll.itf.OpenindexService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.service.model.Deupdownlimit;
import com.pcitc.fms.service.model.Openindex;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/**
 * 操作指标handler
 * @author hanxiao
 *
 */
@Controller
public class Openindexhandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(Openindexhandler.class);
	@Autowired
	private OpenindexService openindexService;
	@Autowired
	private DeupdownlimitService deupdownlimitService;

	@SuppressWarnings("unchecked")
	public void getOpenindexs(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();

		String hrefBase = request.uri();
		try {
			com.pcitc.fms.service.model.Openindex openindex = getOpenindexParams(request);
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Pager<com.pcitc.fms.bll.entity.Openindex> sourceData = openindexService.getPageOpenindexs(openindex);
					PageUtil.mergePage(pagination, sourceData);
					List<com.pcitc.fms.bll.entity.Openindex> entity =  sourceData.getContent();
					future.complete(entity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					try {
						List<com.pcitc.fms.bll.entity.Openindex> entityList = (List<com.pcitc.fms.bll.entity.Openindex>) res.result();
						List<com.pcitc.fms.service.model.Openindex> modelList = ObjectConverter.listConverter(entityList, com.pcitc.fms.service.model.Openindex.class);
						collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, Openindex.class);
					} catch (Exception e) {
						log.error("getOpenindexs is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getOpenindexs is end!");
				}
			});
		} catch (Exception e) {
			log.error("getOpenindexs is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	
	@SuppressWarnings("unchecked")
	public void getDeupdownlimits(RoutingContext routingContext) {

		log.info("*** Openindexhandler START getDeupdownlimits ***");
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		try {
			com.pcitc.fms.service.model.Deupdownlimit deupdownlimit = getDeupdownlimitParams(request);
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					
					List<com.pcitc.fms.bll.entity.Deupdownlimit> sourceData = deupdownlimitService.getPageDeupdownlimits(deupdownlimit);
//					PageUtil.mergePage(pagination, sourceData);
//					List<com.pcitc.fms.bll.entity.Deupdownlimit> entity =  sourceData.getContent();
					future.complete(sourceData);
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
						List<com.pcitc.fms.bll.entity.Deupdownlimit> entityList = (List<com.pcitc.fms.bll.entity.Deupdownlimit>) res.result();
						List<com.pcitc.fms.service.model.Deupdownlimit> modelList = ObjectConverter.listConverter(entityList, com.pcitc.fms.service.model.Deupdownlimit.class);
						collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, Deupdownlimit.class);
					} catch (Exception e) {
						log.error("getDeupdownlimits is Exception!");
						collection = buildErrorCollection(routingContext, e);
					}
					returnCollection(routingContext, collection);
					log.info("getDeupdownlimits is end!");
				}
			});
		} catch (Exception e) {
			log.error("getDeupdownlimits is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	private Deupdownlimit getDeupdownlimitParams(HttpServerRequest request) throws BusinessException {
		String openindexCode =checkString(request.getParam("openindexCode"));
		Integer skip =checkStringToInteger(request.getParam("$skip"));
		Integer top =checkStringToInteger(request.getParam("$top"));
		com.pcitc.fms.service.model.Deupdownlimit model = new Deupdownlimit( top, skip,openindexCode);
		
		return model;
	}

	private com.pcitc.fms.service.model.Openindex getOpenindexParams(HttpServerRequest request) throws BusinessException {
		
		String type = request.getParam("$type");
		String mCode =checkString(request.getParam("$code"));
		String orderby =checkString(request.getParam("$orderby"));
		Integer skip =checkStringToInteger(request.getParam("$skip"));
		Integer top =checkStringToInteger(request.getParam("$top"));
		Integer monLevelId =checkStringToInteger(request.getParam("$monLevelId"));
		String rentCode =checkString(request.getParam("rentCode"));
		String bizCode =checkString(request.getParam("bizCode"));
		com.pcitc.fms.service.model.Openindex model = null;
		model = new Openindex(skip, top, type, mCode,monLevelId,orderby,rentCode,bizCode);
		return model;
	}
	private Integer checkStringToInteger(String param) throws BusinessException {
		Integer result = null;
		if(param != null) {
			if(!VerifyUtil.validateSortNum(param.trim())) {
				throw new BusinessException("", "", "param:"+param+"只能为整数！");
			}
			result = Integer.parseInt(param);
		}
		return result;
	}
	private String checkString(String param) throws BusinessException {
		if(param != null) {
			if(!VerifyUtil.validateCode(param)) {
				throw new BusinessException("", "", param+"不能含有特殊字符！");
			}
		}
		return param;
	}
}
