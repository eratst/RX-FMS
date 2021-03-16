package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.service.model.TPmBizOrgMain;
import com.pcitc.fms.service.model.TPmBizOrgMain;
import com.pcitc.fms.bll.itf.BizOrgMainService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.model.LoadingDock;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TPmBizOrgMain;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


/**
* Title: BizOrgMainHandler
* Description: 业务域
* @author zhenqiang.zhao
* @date 2017年12月1日
* @version 1.0
*/
@Controller
@Component("bizOrgMainHandler")
public class BizOrgMainHandler extends BaseHandler implements ResourceRegister {

   private static Logger log = LoggerFactory.getLogger(BizOrgMainHandler.class);
   @Autowired
   private BizOrgMainService bizOrgMainService;

   /**
    * @Title: getBizOrgMains
    * @Description: TODO task mark zhenqiang.zhao
    * @param routingContext
    * @date 2017年11月21日
    * @return: void
    * @author zhenqiang.zhao
    */
   @SuppressWarnings("unchecked")
   public void getBizOrgMains(RoutingContext routingContext) {
       HttpServerRequest request = routingContext.request();
       String hrefBase = request.uri();
       Pagination pagination = new Pagination();
       try {
           Vertx vertx = routingContext.vertx();
           vertx.executeBlocking((Future<Object> future) -> {
                try {
                   TPmBizOrgMain bizOrgMainsTableModel = QueryParams.getQueryParams(routingContext,TPmBizOrgMain.class); 
                   Pageable pageable = null;
                   Pager<com.pcitc.fms.bll.entity.TPmBizOrgMain> sourceData;
                   if(null != bizOrgMainsTableModel.getSkip()&&null!=bizOrgMainsTableModel.getTop()){
                	   pageable = PageUtil.pageable(bizOrgMainsTableModel.getTop(),bizOrgMainsTableModel.getSkip(),null);
                   }
                   sourceData = bizOrgMainService.getBizOrgMains(bizOrgMainsTableModel,pageable);
                   PageUtil.mergePage(pagination, sourceData);
                   List<TPmBizOrgMain> modelLists = new ArrayList<>();
                   modelLists = ObjectConverter.listConverter(sourceData.getContent(), TPmBizOrgMain.class);
                   if (bizOrgMainsTableModel.getTop()==null && bizOrgMainsTableModel.getSkip()!=null) {
                	   for (int i=0;i<bizOrgMainsTableModel.getSkip();i++) {
                		   modelLists.remove(0);
                	   }
                   }
                   future.complete(modelLists);
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
                	   List<TPmBizOrgMain> targets = (List<TPmBizOrgMain>) res.result();
                	   String linkNames = "业务域明细";
   					   targets = (List<TPmBizOrgMain>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
   	
                       collection = RestfulTool.buildCollection(targets, pagination, hrefBase, TPmBizOrgMain.class);
                   } catch (Exception e) {
                       log.error("getUnits is Exception!");
                       collection = buildErrorCollection(routingContext, e);
                       returnCollection(routingContext, collection);
                   }
                   returnCollection(routingContext, collection);
                   log.info("getBizOrgMains is end!");
               }
           });
       } catch (Exception e) {
           log.error("getBizOrgMains is Exception!");
           String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
           returnCollection(routingContext, collection);
       }

   }

   /**
    * @Title: getBizOrgMain
    * @Description: TODO task mark zhenqiang.zhao
    * @param routingContext
    * @date 2017年11月21日
    * @return: void
    * @author zhenqiang.zhao
    */
   public void getBizOrgMain(RoutingContext routingContext) {
       HttpServerRequest request = routingContext.request();
       String hrefBase = request.uri();
       String bizCode = request.getParam("bizCode")==null?null:request.getParam("bizCode").trim();
       log.info("*** BizOrgMainsHandler START getBizOrgMains ***");
       try {
           Vertx vertx = routingContext.vertx();
           vertx.executeBlocking(future -> {
               com.pcitc.fms.bll.entity.TPmBizOrgMain bizOrgMainEntity = null;
               try {
                   CheckUtil.validateCodeException("业务域编码编码", bizCode);
                   bizOrgMainEntity = bizOrgMainService.getBizOrgMainByCode(bizCode);
                   future.complete(bizOrgMainEntity);
               } catch (Exception e) {
                   future.fail(e);
               }
           }, res -> {
               String collection = null;// 返回结果字符串
               if (res.failed()) {
                   collection = buildErrorCollection(routingContext, (Exception) res.cause());
                   returnCollection(routingContext, collection);
               } else if (res.succeeded()) {
                   List<TPmBizOrgMain> targets = new  ArrayList<TPmBizOrgMain>();
                   com.pcitc.fms.bll.entity.TPmBizOrgMain bizOrgMainEntity = (com.pcitc.fms.bll.entity.TPmBizOrgMain) res.result();
                   try {
                       targets.add(ObjectConverter.entityConverter(bizOrgMainEntity, TPmBizOrgMain.class));
                	   String linkNames = "bizOrgDTLs,headquarters";
   					   targets = (List<TPmBizOrgMain>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
                       collection = RestfulTool.buildCollection(targets, hrefBase, TPmBizOrgMain.class);
                   } catch (Exception e) {
                       log.error("getBizOrgMains is Exception!");
                       collection = buildErrorCollection(routingContext, e);
                       returnCollection(routingContext, collection);
                   }
                   returnCollection(routingContext, collection);
                   log.info("getBizOrgMains is end!");
               }
           });
       } catch (Exception e) {
           log.error("getBizOrgMains is Exception!");
           String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
           returnCollection(routingContext, collection);
       }

   }
   

    @Override
    public void registeResource(Router router) {
        router.get(BASEURL + "/users").produces("application/json;charset=UTF-8").handler(this::getBizOrgMains);
        router.get(BASEURL + "/bizOrgMains").produces("application/json;charset=UTF-8").handler(this::getBizOrgMains);
        router.get(BASEURL + "/rents/:rentCode/bizOrgMains").produces("application/json;charset=UTF-8").handler(this::getBizOrgMains);
        router.get(BASEURL + "/bizOrgMains/:bizCode").produces("application/json;charset=UTF-8").handler(this::getBizOrgMain);
        router.get(BASEURL + "/rents/:rentCode/bizOrgMains/:bizCode").produces("application/json;charset=UTF-8").handler(this::getBizOrgMain);
        router.get(BASEURL + "/users/:bizCode").produces("application/json;charset=UTF-8").handler(this::getBizOrgMain);
    }
}
