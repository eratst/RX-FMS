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

import com.pcitc.fms.service.model.ProduceFactory;
//import com.pcitc.fms.bll.entity.ProduceFactory;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Office;
import com.pcitc.fms.service.model.Pager;
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
 */
 /**
 * Title: ProduceFactoriesHandler
* Description: 生产工厂组织机构
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class ProduceFactoriesHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(ProduceFactoriesHandler.class);
	@Autowired
	private ProduceFactoryService produceFactoryService;

	/**   
	 * @Title: getProduceFactories   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getProduceFactories(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				 try {
					 ProduceFactory produceFactory = QueryParams.getQueryParams(routingContext, ProduceFactory.class);
					Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.ProduceFactory> sourceData;
					if(null != produceFactory.getSkip()&&null!= produceFactory.getTop()){
						pageable = PageUtil.pageable(produceFactory.getTop(),produceFactory.getSkip(), null);
					}
					sourceData = produceFactoryService.getProduceFactories(produceFactory,pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<ProduceFactory> produceFactoryEntity = ObjectConverter.listConverter(sourceData.getContent(), ProduceFactory.class);
					future.complete(produceFactoryEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<ProduceFactory> targets = (List<ProduceFactory>) res.result();
					try {
						   String bizCode = routingContext.request().getParam("bizCode");
	                	   String linkNames = "workshops";
	                	   String baseUri=routingContext.request().uri();
	                	   if (bizCode!=null) {
	                		   baseUri = "/FactoryModelService/bizOrgMains/"+bizCode+"/ProduceFactories";
						}
	                	   targets = (List<ProduceFactory>) setModelEntityWithLinks(targets,linkNames,baseUri);
						   collection = RestfulTool.buildCollection(targets, pagination, hrefBase,
									com.pcitc.fms.service.model.ProduceFactory.class);
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
			log.error("getProduceFactories is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**   
	 * @Title: getProduceFactory   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getProduceFactory(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode")==null?null:request.getParam("orgCode").trim();
		log.info("*** ProduceFactoriesHandler START getProduceFactories ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.ProduceFactory produceFactoryEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					produceFactoryEntity = produceFactoryService.getProduceFactoryByCode(orgCode);
					future.complete(produceFactoryEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.ProduceFactory> targets = new ArrayList<>();
					com.pcitc.fms.bll.entity.ProduceFactory produceFactoryEntity = (com.pcitc.fms.bll.entity.ProduceFactory)res.result();
					try {
						targets.add(ObjectConverter.entityConverter(produceFactoryEntity, com.pcitc.fms.service.model.ProduceFactory.class));
						collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.ProduceFactory.class);
					} catch (Exception e) {
						log.error("getProduceFactories is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getProduceFactories is end!");
				}
			});
		} catch (Exception e) {
			log.error("getProduceFactories is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

}
