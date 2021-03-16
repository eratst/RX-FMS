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

//import com.pcitc.fms.bll.entity.Workshop;
import com.pcitc.fms.service.model.Workshop;
import com.pcitc.fms.bll.itf.WorkshopService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SideLine;
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
 * Title: WorkshopsHandler Description: 车间组织机构
 * 
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class WorkshopsHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(WorkshopsHandler.class);
	@Autowired
	private WorkshopService workshopService;

	/**
	 * @Title: getWorkshops
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getWorkshops(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Workshop workshop = QueryParams.getQueryParams(routingContext, Workshop.class);
					Pager<com.pcitc.fms.bll.entity.Workshop> sourceData;
					Pageable pageable = null;
					if (null != workshop.getSkip() && null != workshop.getTop()) {
						pageable = PageUtil.pageable(workshop.getTop(),workshop.getSkip(), null);
					}
					sourceData = workshopService.getWorkshops(workshop, pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<Workshop> workshopEntity = ObjectConverter.listConverter(sourceData.getContent(),
							Workshop.class);
					future.complete(workshopEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<Workshop> targets = (List<Workshop>) res.result();
					try {
						String linkNames = "areaDictionaries";
						String baseUri = "/FactoryModelService/workshops";
	   					targets = (List<Workshop>) setModelEntityWithLinks(targets,linkNames,baseUri);
						
	   					for (Workshop workshop: targets) {
	   						workshop.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Workshop.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			log.error("getWorkshops is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**
	 * @Title: getWorkshop
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getWorkshop(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		log.info("*** WorkshopsHandler START getWorkshops ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Workshop workshopEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					workshopEntity = workshopService.getWorkshopByCode(orgCode);
					future.complete(workshopEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Workshop> targets = new ArrayList<>();
					try {
						targets.add(ObjectConverter.entityConverter((com.pcitc.fms.bll.entity.Workshop) res.result(),
								com.pcitc.fms.service.model.Workshop.class));
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.Workshop.class);
					} catch (Exception e) {
						log.error("getWorkshops is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getWorkshops is end!");
				}
			});
		} catch (Exception e) {
			log.error("getWorkshops is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

}
