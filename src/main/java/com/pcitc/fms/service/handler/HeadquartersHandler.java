package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.vertx.core.Future;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.Headquarter;

import com.pcitc.fms.bll.itf.HeadquarterService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TPmBizOrgMain;
import com.pcitc.imp.common.exception.BusiException;
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

/**
 * Title: HeadquartersHandler Description: 总部组织机构
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Controller
public class HeadquartersHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(HeadquartersHandler.class);
	@Autowired
	private HeadquarterService headquarterService;

	/**
	 * @Title: getHeadquarters
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getHeadquarters(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					Headquarter headquarter = QueryParams.getQueryParams(routingContext, Headquarter.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.Headquarter> sourceData;
					if (null != headquarter.getSkip()&& null!=headquarter.getTop()) {
						pageable = PageUtil.pageable(headquarter.getTop(),headquarter.getSkip(), null);
					} 
					sourceData = headquarterService.getHeadquarters(headquarter, pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<Headquarter> modelHeadquarters = ObjectConverter.listConverter(sourceData.getContent(), Headquarter.class);
					future.complete(modelHeadquarters);
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
					   List<Headquarter> targets = (List<Headquarter>) res.result();
                	   String linkNames = "divisions,enterprises,offices,produceFactories,departments,workshops";
   				       targets = (List<Headquarter>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
					   collection = RestfulTool.buildCollection(targets, pagination, hrefBase,
								com.pcitc.fms.service.model.Headquarter.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getHeadquarters is end!");
				}
			});
		} catch (Exception e) {
			log.error("getHeadquarters is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**
	 * @Title: getHeadquarter
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getHeadquarter(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		log.info("*** HeadquartersHandler START getHeadquarters ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Headquarter headquarterEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					headquarterEntity = headquarterService.getHeadquarterByCode(orgCode);
					future.complete(headquarterEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Headquarter> targets = new ArrayList<com.pcitc.fms.service.model.Headquarter>();
					com.pcitc.fms.bll.entity.Headquarter headquarterEntity = (com.pcitc.fms.bll.entity.Headquarter) res
							.result();
					try {
						targets.add(ObjectConverter.entityConverter(headquarterEntity,
								com.pcitc.fms.service.model.Headquarter.class));
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.Headquarter.class);
					} catch (Exception e) {
						log.error("getHeadquarters is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getHeadquarters is end!");
				}
			});
		} catch (Exception e) {
			log.error("getHeadquarters is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}


}
