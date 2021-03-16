package com.pcitc.fms.service.handler;

import cc.aicode.e2e.ExcelHelper;
import com.pcitc.fms.bll.itf.TIcNodetopDTLService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TIcNodetopDTL;
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
* @DATE 2017/12/27
* @Author zhaozhenqiang
**/
@Controller
@Component("tIcNodetopDTLHandler")
public class TIcNodetopDTLHandler extends BaseHandler implements ResourceRegister {

  private static Logger log = LoggerFactory.getLogger(TIcNodetopDTLHandler.class);
  @Autowired
  private TIcNodetopDTLService tIcNodetopDTLService;

  /**
   * @Title: getNodetopDTLs
   * @Description: TODO task mark zhenqiang.zhao
   * @date 2017年11月21日
   * @return: void
   * @author zhenqiang.zhao
   */
  public void getNodetopDTLs(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String uri = request.absoluteURI();
    String topCode =
        request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String sNodeCode =
        request.getParam("sNodeCode") == null ? null : request.getParam("sNodeCode").trim();
    String tNodeCode =
        request.getParam("tNodeCode") == null ? null : request.getParam("tNodeCode").trim();
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
    log.info("*** NodetopDTLsHandler START getNodetopDTLs ***");
    String sCodes = request.getParam("$scodeList") == null ? null : request.getParam("$scodeList").trim();
    String tCodes = request.getParam("$tCodeList") == null ? null : request.getParam("$tCodeList").trim();
    //获取分页的参数
    String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
    String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();

    Sort sort = new Sort(Sort.Direction.ASC, "SnodeId");
    Pagination pagination = new Pagination();
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking((Future<Object> future) -> {
        try {
          CheckUtil.validateCodeException("拓扑关系编码", topCode);
          Integer dataStatus = CheckUtil
              .validateNegativeIntegerMybeNullFormat("状态", dataStatusInteger);
          List<TIcNodetopDTL> ticNodetopDTLEntity = new ArrayList<>();
          List<com.pcitc.fms.bll.entity.TIcNodetopDTL> TIcNodetopDTL = null;
          List<String> scodeList = CheckUtil.buildStringToListString("$scodeList", sCodes);//源CODE集合
          List<String> tcodeList = CheckUtil.buildStringToListString("$tcodeList", tCodes);//目标CODE集合
          Integer sortNum = CheckUtil.validateNegativeIntegerMybeNullFormat("sortNum", StrSortNum);
          Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
          Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
          Pageable pageable = null;
          if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
            top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
            skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
            pageable = PageUtil.pageable(top, skip, sort);
          }
          TIcNodetopDTL NodetopDTLsTableModel = new TIcNodetopDTL(sNodeCode, tNodeCode, topCode,
              topName, topAlias, sortNum, scodeList, tcodeList, top, skip, opertype);
          //判断 是否分页
          Pager<com.pcitc.fms.bll.entity.TIcNodetopDTL> sourceData;
          if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
            pageable = PageUtil.pageable(top, skip, sort);
            sourceData = tIcNodetopDTLService.getNodetopDTLs(NodetopDTLsTableModel, pageable);
            PageUtil.mergePage(pagination, sourceData);
          } else {
            sourceData = tIcNodetopDTLService.getNodetopDTLs(NodetopDTLsTableModel, null);
            PageUtil.mergePage(pagination, sourceData);
          }
          TIcNodetopDTL = sourceData.getContent();
          future.complete(TIcNodetopDTL);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TIcNodetopDTL> targets = new ArrayList<>();
          try {
            List<com.pcitc.fms.bll.entity.TIcNodetopDTL> ticNodetopDTLList = (List<com.pcitc.fms.bll.entity.TIcNodetopDTL>) res.result();
            List<TIcNodetopDTL> target = ObjectConverter.listConverter(ticNodetopDTLList, TIcNodetopDTL.class);
            collection = RestfulTool.buildCollection(target, pagination, hrefBase, TIcNodetopDTL.class);
          } catch (Exception e) {
            log.error("getUnits is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getNodetopDTLs is end!");
        }
      });
    } catch (Exception e) {
      log.error("getNodetopDTLs is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }

  /**
   * @Title: getNodetopDTL
   * @Description: TODO task mark zhenqiang.zhao
   * @date 2017年11月21日
   * @return: void
   * @author zhenqiang.zhao
   */
  public void getNodetopDTL(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
//    String orgCode =
//        request.getParam("orgCode") == null ? null : request.getParam("orgCode").trim();
//    String opertype = request.getParam("$opertype") == null ? null
//        : request.getParam("$opertype").trim();//TODO 该查询条件没有设置
    String topCode =
            request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String nodeCode =
            request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
    log.info("*** NodetopDTLsHandler START getNodetopDTLs ***");
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        List<com.pcitc.fms.bll.entity.TIcNodetopDTL> ticNodetopDTLEntity = null;
        try {
//          CheckUtil.validateCodeException("组织机构编码编码", orgCode);
//          CheckUtil.validateCodeStringMybeNullException("操作类型", opertype);
          ticNodetopDTLEntity = tIcNodetopDTLService.getNodetopDTLByCode(topCode, nodeCode);
          future.complete(ticNodetopDTLEntity);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;// 返回结果字符串
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TIcNodetopDTL> targets = new ArrayList<TIcNodetopDTL>();
          List<com.pcitc.fms.bll.entity.TIcNodetopDTL> ticNodetopDTLEntity = (List<com.pcitc.fms.bll.entity.TIcNodetopDTL>) res
              .result();
          try {
            collection = RestfulTool.buildCollection(
                ObjectConverter.listConverter(ticNodetopDTLEntity, TIcNodetopDTL.class), hrefBase,
                TIcNodetopDTL.class);
          } catch (Exception e) {
            log.error("getNodetopDTLs is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getNodetopDTLs is end!");
        }
      });
    } catch (Exception e) {
      log.error("getNodetopDTLs is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }


  /**
   * @Title: updateNodetopDTL
   * @Description: TODO task mark zhenqiang.zhao
   * @date 2017年11月21日
   * @return: void
   * @author zhenqiang.zhao
   */
  public void updateNodetopDTL(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String topCode =
            request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String nodeCode =
            request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
    Set<FileUpload> fileUploads = routingContext.fileUploads();
    String collectionParam = routingContext.getBodyAsString();
    log.debug("*** NodetopDTLsHandler START updateNodetopDTLs ***");
    // 将template转换成viewmodel
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        try {
          List<TIcNodetopDTL> ticNodetopDTLList = RestfulTool
              .toResourceRep(collectionParam, TIcNodetopDTL.class);
          // 验证
          if (ticNodetopDTLList.size() > 0) {
            TIcNodetopDTL tak = ticNodetopDTLList.get(0);
//                       tak.setOrgCode(orgCode);
          }
          ticNodetopDTLList = CurrencyCheck.checkObject(ticNodetopDTLList, Operation.UPDATE);//校验方法
          List<com.pcitc.fms.bll.entity.TIcNodetopDTL> ticNodetopDTLEntityList = ObjectConverter
              .listConverter(ticNodetopDTLList, com.pcitc.fms.bll.entity.TIcNodetopDTL.class);
          // 调用全局变量alertPointService的add方法添加addAlertPoint数据
          if (null != ticNodetopDTLEntityList && ticNodetopDTLEntityList.size() > 0) {
            tIcNodetopDTLService.updateNodetopDTL(ticNodetopDTLEntityList, topCode, nodeCode);
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
      log.debug("*** NodetopDTLsHandler END updateNodetopDTLs ***");
    } catch (Exception e) {
      log.error(
          "*** NodetopDTLsHandler _ updateNodetopDTLs _ Exception " + e.getMessage() + " ***");
      String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
          // BusinessExceptionInfo.Unknown.getMessage()),
          request.uri());
      returnCollection(routingContext, collection);
    }
  }

  /**
   * @Title: deleteNodetopDTL
   * @Description: TODO task mark zhenqiang.zhao
   * @date 2017年11月21日
   * @return: void
   * @author zhenqiang.zhao
   */
  public void deleteNodetopDTL(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String topCode =
            request.getParam("topCode") == null ? null : request.getParam("topCode").trim();
    String nodeCode =
        request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
//    String urlType = CheckUtil.getUriEndType(hrefBase, orgCode);
    log.info("*** NodetopDTLsHandler START deleteNodetopDTLs ***");
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        try {
//          CheckUtil.validateCodeException("组织机构编码", orgCode);
          tIcNodetopDTLService.deleteNodetopDTLByCode(topCode, nodeCode);
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
          log.info("deleteNodetopDTLs is end!");
        }
      });
    } catch (Exception e) {
      log.error("deleteNodetopDTLs is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }
  }


  @Override
  public void registeResource(Router router) {
    router.get(BASEURL + "/icNodetopMains/:topCode/icNodetopDtls")
        .produces("application/json;charset=UTF-8")
        .handler(this::getNodetopDTLs);
    router.get(BASEURL + "/icNodetopMains/:topCode/icNodetopDtls/:nodeCode")
        .produces("application/json;charset=UTF-8").handler(this::getNodetopDTL);
    router.put(BASEURL + "/icNodetopMains/:topCode/icNodetopDtls/:nodeCode")
        .produces("application/json;charset=UTF-8").handler(this::updateNodetopDTL);
    router.delete(BASEURL + "/icNodetopMains/:topCode/icNodetopDtls/:nodeCode")
        .produces("application/json;charset=UTF-8").handler(this::deleteNodetopDTL);
  }
}
