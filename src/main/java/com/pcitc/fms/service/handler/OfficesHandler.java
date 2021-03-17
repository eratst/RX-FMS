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

import com.pcitc.fms.service.model.Enterprise;
import com.pcitc.fms.service.model.Office;
//import com.pcitc.fms.bll.entity.Office;
import com.pcitc.fms.bll.itf.OfficeService;
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
 * Title: OfficesHandler
* Description: 公司处室组织机构
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class OfficesHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(OfficesHandler.class);
	@Autowired
	private OfficeService officeService;

	/**   
	 * @Title: getOffices   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getOffices(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		 String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				 try {
					 Office office = QueryParams.getQueryParams(routingContext, Office.class);
					 Pageable pageable = null;
					Pager<com.pcitc.fms.bll.entity.Office> sourceData;
					if(null != office.getSkip()&&null!=office.getTop()){
						pageable =  PageUtil.pageable(office.getTop(),office.getSkip(), null);
					}
					sourceData = officeService.getOffices(office,pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<Office> officeEntity = ObjectConverter.listConverter(sourceData.getContent(), Office.class);
					future.complete(officeEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<Office> targets = (List<Office>) res.result();
					try {
						   String bizCode = routingContext.request().getParam("bizCode");
	                	   String linkNames = "departments";
	                	   String baseUri=routingContext.request().uri();
	                	   if (bizCode!=null) {
	                		   baseUri = "/FactoryModelService/bizOrgMains/"+bizCode+"/offices";
						}
	                	   targets = (List<Office>) setModelEntityWithLinks(targets,linkNames,baseUri);
						   collection = RestfulTool.buildCollection(targets, pagination, hrefBase,
									com.pcitc.fms.service.model.Office.class);
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
			log.error("getOffices is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**   
	 * @Title: getOffice   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getOffice(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode")==null?null:request.getParam("orgCode").trim();
		log.info("*** OfficesHandler START getOffices ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Office officeEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					officeEntity = officeService.getOfficeByCode(orgCode);
					future.complete(officeEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Office> targets = new ArrayList<>();
					try {
						targets.add(ObjectConverter.entityConverter((com.pcitc.fms.bll.entity.Office)res.result(),com.pcitc.fms.service.model.Office.class));
						collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.Office.class);
					} catch (Exception e) {
						log.error("getOffices is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getOffices is end!");
				}
			});
		} catch (Exception e) {
			log.error("getOffices is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
}
