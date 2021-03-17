package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.BizOrgDTLService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.dal.dao.BizOrgDTLDaoImpl;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TPmBizOrgDTL;
import com.pcitc.fms.service.model.TankArea;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

import antlr.StringUtils;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * Title: BizOrgDTLsHandler Description: 多业务组织层次明细
 * 
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
@Component("bizOrgDTLHandler")
public class BizOrgDTLHandler extends BaseHandler implements ResourceRegister {

	@Autowired
	private BizOrgDTLService bizOrgDTLService;
	@Autowired
	private BizOrgDTLDaoImpl bizOrgDTLDaoImpl;
	
	/**
	 * @Title: getBizOrgDTLs
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getBizOrgDTLs(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Pageable pageable = null;
					TPmBizOrgDTL BizOrgDTLsTableModel = QueryParams.getQueryParams(routingContext, TPmBizOrgDTL.class);
					if (org.apache.commons.lang.StringUtils.isNotEmpty(BizOrgDTLsTableModel.getRentCode())) {
						if (org.apache.commons.lang.StringUtils.isEmpty(BizOrgDTLsTableModel.getBizCode())) {
							BizOrgDTLsTableModel.setBizCode(BizOrgDTLsTableModel.getRentCode()+"_SYSTEM_STANDARD_BIZ");
						}
					}
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.TPmBizOrgDTL> sourceData = new Pager();
					if (null != BizOrgDTLsTableModel.getSkip() && null != BizOrgDTLsTableModel.getTop()) {
						pageable = pageable = PageUtil.pageable(BizOrgDTLsTableModel.getTop(),BizOrgDTLsTableModel.getSkip(), null);
					}
					List<String> orgCodes= BizOrgDTLsTableModel.getOrgCodes();
					if (hrefBase.contains("operType")) {
						List<com.pcitc.fms.bll.entity.TPmBizOrgDTL> list = bizOrgDTLService.getBizOrgDTLByCode(
								BizOrgDTLsTableModel.getOrgCode(), BizOrgDTLsTableModel.getOperType(),
								orgCodes, BizOrgDTLsTableModel.getBizCode(),BizOrgDTLsTableModel, pageable);
						sourceData.setContent(list);
						if(list.isEmpty()){
							sourceData.setTotalElements(0l);
						}else{
							sourceData.setTotalElements((long)list.size());
						}
					} else {
						sourceData = bizOrgDTLService.getBizOrgDTLs(BizOrgDTLsTableModel, pageable);
					}

					PageUtil.mergePage(pagination, sourceData);
					List<TPmBizOrgDTL> tpmBizOrgDTLmodels = ObjectConverter.listConverter(sourceData.getContent(),
							TPmBizOrgDTL.class);
					future.complete(tpmBizOrgDTLmodels);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<TPmBizOrgDTL> targets = (List<TPmBizOrgDTL>) res.result();
					try {
						if(hrefBase.contains("/orgs")){
							String linkNames = "headquarters,divisions,offices,produceFactories,departments,workshops,teams";
							targets = (List<TPmBizOrgDTL>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());

						}
						collection = RestfulTool.buildCollection(targets, pagination,hrefBase, TPmBizOrgDTL.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

//	/**
//	 * @Title: getBizOrgDTL
//	 * @Description: TODO task mark zhenqiang.zhao
//	 * @param routingContext
//	 * @date 2017年11月21日
//	 * @return: void
//	 * @author zhenqiang.zhao
//	 */
//	public void getBizOrgDTL(RoutingContext routingContext) {
//		HttpServerRequest request = routingContext.request();
//		String hrefBase1 = request.uri();
//		String hrefBase = request.uri();
//		String orgCode = request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
//		String bizCode = request.getParam("bizCode") == null ? null : request.getParam("bizCode").trim();
//		String opertype = request.getParam("$operType") == null ? null : request.getParam("$operType").trim();// TODO
//																												// 该查询条件没有设置
//		log.info("*** BizOrgDTLsHandler START getBizOrgDTLs ***");
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				List<com.pcitc.fms.bll.entity.TPmBizOrgDTL> tpmBizOrgDTLEntity = null;
//				try {
//					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
//					CheckUtil.validateCodeStringMybeNullException("操作类型", opertype);
//					tpmBizOrgDTLEntity = bizOrgDTLService.getBizOrgDTLByCode(orgCode, opertype, null, bizCode);
//					future.complete(tpmBizOrgDTLEntity);
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;// 返回结果字符串
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//					returnCollection(routingContext, collection);
//				} else if (res.succeeded()) {
//					List<TPmBizOrgDTL> targets = new ArrayList<TPmBizOrgDTL>();
//					List<com.pcitc.fms.bll.entity.TPmBizOrgDTL> tpmBizOrgDTLEntity = (List<com.pcitc.fms.bll.entity.TPmBizOrgDTL>) res
//							.result();
//					try {
//						collection = RestfulTool.buildCollection(
//								ObjectConverter.listConverter(tpmBizOrgDTLEntity, TPmBizOrgDTL.class), hrefBase,
//								TPmBizOrgDTL.class);
//					} catch (Exception e) {
//						log.error("getBizOrgDTLs is Exception!");
//						collection = buildErrorCollection(routingContext, e);
//						returnCollection(routingContext, collection);
//					}
//					returnCollection(routingContext, collection);
//					log.info("getBizOrgDTLs is end!");
//				}
//			});
//		} catch (Exception e) {
//			log.error("getBizOrgDTLs is Exception!");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
//			returnCollection(routingContext, collection);
//		}
//
//	}

	@Override
	public void registeResource(Router router) {
		
	}
}

