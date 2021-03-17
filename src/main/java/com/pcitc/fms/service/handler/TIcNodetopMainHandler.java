package com.pcitc.fms.service.handler;

import cc.aicode.e2e.ExcelHelper;
import com.pcitc.fms.bll.itf.TIcNodetopMainService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TIcNodetopMain;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/* @Description 拓扑关系明细
* @DATE 2018/1/18
* @Author yanghe
**/
@Controller
@Component("tIcNodetopMainHandler")
public class TIcNodetopMainHandler extends BaseHandler implements ResourceRegister {

  private static Logger log = LoggerFactory.getLogger(TIcNodetopMainHandler.class);
  @Autowired
  private TIcNodetopMainService tIcNodetopMainservice;

  /**
   * @Title: getNodetopMains
   * @Description: TODO task mark he.yang
   * @date 2018年1月18日
   * @return: void
   * @author he.yang
   */
  @SuppressWarnings("unchecked")
public void getNodetopMains(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String uri = request.absoluteURI();
    String topCode =
        request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String topName =
        request.getParam("topName") == null ? null : request.getParam("topName").trim();
    String topAlias =
        request.getParam("topAlias") == null ? null : request.getParam("topAlias").trim();
    String dataStatusInteger =
        request.getParam("dataStatus") == null ? null : request.getParam("dataStatus").trim();
    String StrSortNum =
        request.getParam("sortNum") == null ? null : request.getParam("sortNum").trim();
    String opertype = request.getParam("$opertype") == null ? null
        : request.getParam("$opertype").trim();//TODO 该查询条件没有设置
    //要查询集合的级别类型(leaves叶子节点,children 子节点,ancestors祖节点)
    log.info("*** NodetopMainsHandler START getNodetopMains ***");
    String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
    //获取分页的参数
    String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
    String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();

    Sort sort = new Sort(Sort.Direction.ASC, "topId");
    Pagination pagination = new Pagination();
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking((Future<Object> future) -> {
        try {
          Integer dataStatus = CheckUtil
              .validateNegativeIntegerMybeNullFormat("状态", dataStatusInteger);
          List<String> codeList = CheckUtil.buildStringToListString("$scodeList", codes);//源CODE集合
          List<TIcNodetopMain> TIcNodetopMainEntity = new ArrayList<>();
          List<com.pcitc.fms.bll.entity.TIcNodetopMain> ticNodetopMain = null;
          Integer sortNum = CheckUtil.validateNegativeIntegerMybeNullFormat("sortNum", StrSortNum);
          Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
          Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
          Pageable pageable = null;
          if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
            top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
            skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
            pageable = PageUtil.pageable(top, skip, sort);
          }
          
          TIcNodetopMain NodetopMainsTableModel = new TIcNodetopMain(topCode, topName, topAlias, dataStatus, sortNum, codeList, top, skip);
          //判断 是否分页
          Pager<com.pcitc.fms.bll.entity.TIcNodetopMain> sourceData;
          if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
            pageable = PageUtil.pageable(top, skip, sort);
            sourceData = tIcNodetopMainservice.getNodetopMains(NodetopMainsTableModel, pageable);
            PageUtil.mergePage(pagination, sourceData);
          } else {
            sourceData = tIcNodetopMainservice.getNodetopMains(NodetopMainsTableModel, null);
            PageUtil.mergePage(pagination, sourceData);
          }
          ticNodetopMain = sourceData.getContent();
          future.complete(ticNodetopMain);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TIcNodetopMain> targets = new ArrayList<>();
          try {
            List<com.pcitc.fms.bll.entity.TIcNodetopMain> TIcNodetopMainList = (List<com.pcitc.fms.bll.entity.TIcNodetopMain>) res.result();
            List<TIcNodetopMain> target = ObjectConverter.listConverter(TIcNodetopMainList, TIcNodetopMain.class);
            collection = RestfulTool.buildCollection(target, pagination, hrefBase, TIcNodetopMain.class);
          } catch (Exception e) {
            log.error("getUnits is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getNodetopMains is end!");
        }
      });
    } catch (Exception e) {
      log.error("getNodetopMains is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }

  /**
   * @Title: getNodetopMain
   * @Description: TODO task mark he.yang
   * @date 2018年1月18日
   * @return: void
   * @author he.yang
   */
  @SuppressWarnings("unchecked")
public void getNodetopMain(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String topCode =
        request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    log.info("*** NodetopMainsHandler START getNodetopMain ***");
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        List<com.pcitc.fms.bll.entity.TIcNodetopMain> TIcNodetopMainEntity = null;
        try {
          CheckUtil.validateCodeException("拓扑关系编码", topCode);
          TIcNodetopMainEntity = tIcNodetopMainservice.getNodetopMain(topCode);
          future.complete(TIcNodetopMainEntity);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;// 返回结果字符串
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TIcNodetopMain> targets = new ArrayList<TIcNodetopMain>();
          List<com.pcitc.fms.bll.entity.TIcNodetopMain> ticNodetopMainEntity = (List<com.pcitc.fms.bll.entity.TIcNodetopMain>) res
              .result();
          try {
        	  targets = ObjectConverter.listConverter(ticNodetopMainEntity, TIcNodetopMain.class);
            collection = RestfulTool.buildCollection(
                ObjectConverter.listConverter(targets, TIcNodetopMain.class), hrefBase,
                TIcNodetopMain.class);
          } catch (Exception e) {
            log.error("getNodetopMains is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getNodetopMains is end!");
        }
      });
    } catch (Exception e) {
      log.error("getNodetopMains is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }

  /**
   * @Title: addNodetopMains
   * @Description: TODO task mark he.yang
   * @date 2018年1月18日
   * @return: void
   * @author he.yang
   */
  @SuppressWarnings("unchecked")
public void addNodetopMains(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String uri = request.uri();
    Set<FileUpload> fileUploads = routingContext.fileUploads();
    String typeByUri = CheckUtil.getUriEndType(uri);
    String collectionParam = routingContext.getBodyAsString();
    log.debug("*** NodetopMainsHandler START addNodetopMains ***");
    // 将template转换成viewmodel
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        try {
          List<TIcNodetopMain> TIcNodetopMainEntityList = new ArrayList<>();
          List<TIcNodetopMain> ticNodetopMainModelList = new ArrayList<>();
          List<com.pcitc.fms.bll.entity.TIcNodetopMain> ticNodetopMainList = new ArrayList<>();
          if (fileUploads != null && fileUploads.size() > 0) {
            FileUpload file = (FileUpload) fileUploads.toArray()[0];
            if (file != null && file.size() > 0) {
              String name = file.uploadedFileName();
              File fileIo = new File(name);
              ExcelHelper em = ExcelHelper.readExcel(fileIo);
              TIcNodetopMainEntityList = em.toEntitys(TIcNodetopMain.class);
              for (TIcNodetopMain TIcNodetopMainEntity : TIcNodetopMainEntityList) {
                TIcNodetopMainEntity.setCrtDate(new Date());
              }
            }
          } else {
            ticNodetopMainModelList = RestfulTool
                .toResourceRep(collectionParam, TIcNodetopMain.class);
            // 验证
            for (TIcNodetopMain TIcNodetopMainModel : ticNodetopMainModelList) {
              TIcNodetopMainModel.setCrtDate(TIcNodetopMainModel.getCrtDate() == null ? new Date()
                  : TIcNodetopMainModel.getCrtDate());
              TIcNodetopMainModel.setMntDate(TIcNodetopMainModel.getMntDate() == null ? new Date()
                  : TIcNodetopMainModel.getMntDate());
            }
            List<TIcNodetopMain> ticNodetopMainModelStrList = CurrencyCheck
                .checkObject(ticNodetopMainList, Operation.ADD);//校验方法
            ticNodetopMainList = ObjectConverter.listConverter(ticNodetopMainModelStrList,
                com.pcitc.fms.bll.entity.TIcNodetopMain.class);
          }
          // 调用全局变量alertPointService的add方法添加addAlertPoint数据
          List str = tIcNodetopMainservice.addNodetopMains(ticNodetopMainList);
          List<TIcNodetopMain> entityList = ObjectConverter.listConverter(str, TIcNodetopMain.class);
          future.complete(entityList);
        } catch (BusinessException be) {
          future.fail(be);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
        } else if (res.succeeded()) {
          try {
            List<TIcNodetopMain> entityList = (List<TIcNodetopMain>) res.result();
            collection = RestfulTool
                .buildCollection(entityList, request.uri(), TIcNodetopMain.class);
          } catch (Exception e) {
            collection = buildErrorCollection(routingContext, e);
//            returnCollection(routingContext, collection);
          }
        }
        // 输出结果
        returnCollection(routingContext, collection);
      });

      log.debug("*** NodetopMainsHandler END addNodetopMains ***");

    } catch (Exception e) {
      log.error("*** NodetopMainsHandler _ addNodetopMains _ Exception " + e.getMessage() + " ***");
      String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), uri);
      returnCollection(routingContext, collection);
    }
  }

  /**
   * @Title: updateNodetopMain
   * @Description: TODO task mark he.yang
   * @date 2018年1月18日
   * @return: void
   * @author he.yang
   */
  @SuppressWarnings("unchecked")
public void updateNodetopMain(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String topCode =
        request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String collectionParam = routingContext.getBodyAsString();
    log.debug("*** NodetopMainsHandler START updateNodetopMains ***");
    // 将template转换成viewmodel
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        try {
          List<TIcNodetopMain> TIcNodetopMainList = RestfulTool
              .toResourceRep(collectionParam, TIcNodetopMain.class);
          // 验证
          CheckUtil.validateCodeException("拓扑关系编码", topCode);
        
          TIcNodetopMainList = CurrencyCheck.checkObject(TIcNodetopMainList, Operation.UPDATE);//校验方法
          List<com.pcitc.fms.bll.entity.TIcNodetopMain> TIcNodetopMainEntityList = ObjectConverter
              .listConverter(TIcNodetopMainList, com.pcitc.fms.bll.entity.TIcNodetopMain.class);

          if (null != TIcNodetopMainEntityList && TIcNodetopMainEntityList.size() > 0) {
            tIcNodetopMainservice.updateNodetopMain(TIcNodetopMainEntityList,topCode);
          }
          future.complete(hrefBase);
        } catch (BusinessException be) {
          future.fail(be);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
        } else if (res.succeeded()) {
          collection = res.result().toString();
        }
        // 输出结果
        returnCollection(routingContext, collection);
      });
      log.debug("*** NodetopMainsHandler END updateNodetopMains ***");
    } catch (Exception e) {
      log.error(
          "*** NodetopMainsHandler _ updateNodetopMains _ Exception " + e.getMessage() + " ***");
      String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
          // BusinessExceptionInfo.Unknown.getMessage()),
          request.uri());
      returnCollection(routingContext, collection);
    }
  }

  /**
   * @Title: deleteNodetopMain
   * @Description: TODO task mark he.yang
   * @date 2018年1月18日
   * @return: void
   * @author he.yang
   */
  public void deleteNodetopMain(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String topCode =
        request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    log.info("*** NodetopMainsHandler START deleteNodetopMains ***");
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        try {
        	// 验证
	        CheckUtil.validateCodeException("拓扑关系编码", topCode);
	        tIcNodetopMainservice.deleteNodetopMainByCode(topCode);
	        future.complete();
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;// 返回结果字符串
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          collection = "";
          returnCollection(routingContext, collection);
          log.info("deleteNodetopMains is end!");
        }
      });
    } catch (Exception e) {
      log.error("deleteNodetopMains is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }
  }


  @Override
  public void registeResource(Router router) {
    router.get(BASEURL + "/icNodetopMains")
        .produces("application/json;charset=UTF-8")
        .handler(this::getNodetopMains);
    router.get(BASEURL + "/icNodetopMains/:topCode")
        .produces("application/json;charset=UTF-8").handler(this::getNodetopMain);
    router.post(BASEURL + "/icNodetopMains")
        .produces("application/json;charset=UTF-8")
        .handler(this::addNodetopMains);
    router.put(BASEURL + "/icNodetopMains/:topCode")
        .produces("application/json;charset=UTF-8").handler(this::updateNodetopMain);
    router.delete(BASEURL + "/icNodetopMains/:topCode")
        .produces("application/json;charset=UTF-8").handler(this::deleteNodetopMain);
  }
}
