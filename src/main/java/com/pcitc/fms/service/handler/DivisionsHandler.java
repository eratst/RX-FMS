package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import com.pcitc.fms.service.model.Division;
import com.pcitc.fms.service.model.Headquarter;
//import com.pcitc.fms.bll.entity.Division;
import com.pcitc.fms.bll.itf.DivisionService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
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
 */
 /**
 * Title: DivisionsHandler
* Description: 事业部组织机构
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class DivisionsHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(DivisionsHandler.class);
	@Autowired
	private DivisionService divisionsService;

	/**   
	 * @Title: getDivisions   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getDivisions(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				 try {
					Division division = QueryParams.getQueryParams(routingContext, Division.class);
					Pager<com.pcitc.fms.bll.entity.Division> sourceData;
					Pageable pageable = null;
					if(null != division.getSkip() && null!=division.getTop()){
						pageable =  PageUtil.pageable(division.getTop(),division.getSkip(), null);
					}
					sourceData = divisionsService.getDivisions(division,pageable);
					if (routingContext.request().uri().contains("skip") && division.getSkip() != null
							&& division.getTop() == null) {
						if (division.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<division.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<Division> headquarterEntity = ObjectConverter.listConverter(sourceData.getContent(), Division.class);
					future.complete(headquarterEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					  List<Division> targets = (List<Division>) res.result();
					try {
						String bizCode = routingContext.request().getParam("bizCode");
	                	   String linkNames = "enterprises,offices,produceFactories,departments,workshops";
	                	   String baseUri=routingContext.request().uri();
	                	   if (bizCode!=null) {
	                		   baseUri = "/FactoryModelService/bizOrgMains/"+bizCode+"/divisions";
						}
	                	   targets = (List<Division>) setModelEntityWithLinks(targets,linkNames,baseUri);
						   collection = RestfulTool.buildCollection(targets, pagination, hrefBase,
									com.pcitc.fms.service.model.Division.class);
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
			log.error("getDivisions is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**   
	 * @Title: getDivision   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getDivision(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode")==null?null:request.getParam("orgCode").trim();
		log.info("*** DivisionsHandler START getDivisions ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Division headquarterEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					headquarterEntity = divisionsService.getDivisionByCode(orgCode);
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
					List<com.pcitc.fms.service.model.Division> targets = new ArrayList<>();
					try {
						targets.add(ObjectConverter.entityConverter((com.pcitc.fms.bll.entity.Division)res.result(),com.pcitc.fms.service.model.Division.class));
						collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.Division.class);
					} catch (Exception e) {
						log.error("getDivisions is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getDivisions is end!");
				}
			});
		} catch (Exception e) {
			log.error("getDivisions is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
}
