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

//import com.pcitc.fms.bll.entity.Department;
import com.pcitc.fms.service.model.Department;
import com.pcitc.fms.bll.itf.DepartmentService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
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
 * Title: DepartmentsHandler Description: 科室组织机构
 * 
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class DepartmentsHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(DepartmentsHandler.class);
	@Autowired
	private DepartmentService departmentService;

	/**
	 * @Title: getDepartments
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getDepartments(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Department department = QueryParams.getQueryParams(routingContext, Department.class);
					Pager<com.pcitc.fms.bll.entity.Department> sourceData;
					Pageable pageable = null;
					if (null != department.getSkip() && null != department.getTop()) {
						pageable =  PageUtil.pageable(department.getTop(),department.getSkip(), null);
					}
					sourceData = departmentService.getDepartments(department, pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<Department> departmentEntity = ObjectConverter.listConverter(sourceData.getContent(),
							Department.class);
					future.complete(departmentEntity);
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
						collection = RestfulTool.buildCollection((List<Department>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.Department.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getDepartments is end!");
				}
			});
		} catch (Exception e) {
			log.error("getDepartments is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**
	 * @Title: getDepartment
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getDepartment(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
		log.info("*** DepartmentsHandler START getDepartments ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Department departmentEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					departmentEntity = departmentService.getDepartmentByCode(orgCode);
					future.complete(departmentEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Department> targets = new ArrayList<>();
					try {
						targets.add(ObjectConverter.entityConverter((com.pcitc.fms.bll.entity.Department) res.result(),
								com.pcitc.fms.service.model.Department.class));
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.Department.class);
					} catch (Exception e) {
						log.error("getDepartments is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getDepartments is end!");
				}
			});
		} catch (Exception e) {
			log.error("getDepartments is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

}
