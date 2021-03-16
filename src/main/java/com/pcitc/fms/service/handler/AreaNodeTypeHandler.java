package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.AreaNodeTypeService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TPmAreaNodeType;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/* @Description
 * parameter a * @DATE 2018/1/3
 * @Author zhaozhenqiang
 * return
 */
@Controller
@Component(value = "areaNodeTypeHandler")
public class AreaNodeTypeHandler extends BaseHandler implements ResourceRegister {

  private static Logger log = LoggerFactory.getLogger(AreaNodeTypeHandler.class);

  @Autowired
  private AreaNodeTypeService areaNodeTypeService;

  /* @Description
   * parameter 
   * @DATE 2018/1/3
   * @Author zhaozhenqiang
   * return @
   **/
  public void getAreaNodeTypes(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String uri = request.absoluteURI();
    String areaCode = request.getParam("areaCode") == null ? null : request.getParam("areaCode").trim();
    String nodeTypeCode = request.getParam("nodeTypeCode") == null ? null : request.getParam("nodeTypeCode").trim();
    log.info("*** AreaNodeTypesHandler START getAreaNodeTypes ***");
    String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
    //获取分页的参数
    String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
    String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
    Sort sort = new Sort(Sort.Direction.ASC, "areaCode");
    Pagination pagination = new Pagination();
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking((Future<Object> future) -> {
        try {
          CheckUtil.validateCodeStringMybeNullException("", areaCode);
          CheckUtil.validateCodeStringMybeNullException("", nodeTypeCode);
          List<TPmAreaNodeType> tpmAreaNodeTypeEntity = new ArrayList<>();
          List<com.pcitc.fms.bll.entity.TPmAreaNodeType> tpmAreaNodeType = null;
          List<String> codeList = CheckUtil.buildStringToListString("$codeList", codes);
          Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
          Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
          Pageable pageable = null;
          if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
            top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
            skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
            pageable = PageUtil.pageable(top, skip, sort);
          }
          TPmAreaNodeType AreaNodeTypesTableModel = new TPmAreaNodeType(areaCode, nodeTypeCode, codeList, top, skip);
          //判断 是否分页
          Pager<com.pcitc.fms.bll.entity.TPmAreaNodeType> sourceData;
          if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
            pageable = PageUtil.pageable(top, skip, sort);
            sourceData = areaNodeTypeService.getAreaNodeTypes(AreaNodeTypesTableModel, pageable);
            PageUtil.mergePage(pagination, sourceData);
          } else {
            sourceData = areaNodeTypeService.getAreaNodeTypes(AreaNodeTypesTableModel, null);
            PageUtil.mergePage(pagination, sourceData);
          }
          tpmAreaNodeType = sourceData.getContent();
          future.complete(tpmAreaNodeType);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TPmAreaNodeType> targets = new ArrayList<>();
          try {
            List<TPmAreaNodeType> tpmAreaNodeTypeList = (List<TPmAreaNodeType>) res.result();
            List<TPmAreaNodeType> target = ObjectConverter.listConverter(tpmAreaNodeTypeList, TPmAreaNodeType.class);
            collection = RestfulTool
                .buildCollection(target, pagination, hrefBase, TPmAreaNodeType.class);
          } catch (Exception e) {
            log.error("getUnits is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getAreaNodeTypes is end!");
        }
      });
    } catch (Exception e) {
      log.error("getAreaNodeTypes is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }

  /* @Description
   * parametera
   * @DATE 2018/1/3
   * @Author zhaozhenqiang
   * retun @a
   **/
  public void getAreaNodeType(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String hrefBase = request.uri();
    String areaCode = request.getParam("areaCode") == null ? null : request.getParam("areaCode").trim();
    log.info("*** AreaNodeTypesHandler START getAreaNodeTypes ***");
    try {
      Vertx vertx = routingContext.vertx();
      vertx.executeBlocking(future -> {
        List<com.pcitc.fms.bll.entity.TPmAreaNodeType> tpmAreaNodeTypeEntity = null;
        try {
          CheckUtil.validateCodeException("", areaCode);
          tpmAreaNodeTypeEntity = areaNodeTypeService.getAreaNodeType(areaCode, null);
          future.complete(tpmAreaNodeTypeEntity);
        } catch (Exception e) {
          future.fail(e);
        }
      }, res -> {
        String collection = null;
        if (res.failed()) {
          collection = buildErrorCollection(routingContext, (Exception) res.cause());
          returnCollection(routingContext, collection);
        } else if (res.succeeded()) {
          List<TPmAreaNodeType> targets = new ArrayList<TPmAreaNodeType>();
          List<com.pcitc.fms.bll.entity.TPmAreaNodeType> tpmAreaNodeTypeEntity = (List<com.pcitc.fms.bll.entity.TPmAreaNodeType>) res.result();
          try {
            collection = RestfulTool.buildCollection(ObjectConverter.listConverter(tpmAreaNodeTypeEntity, TPmAreaNodeType.class), hrefBase,
                TPmAreaNodeType.class);
          } catch (Exception e) {
            log.error("getAreaNodeTypes is Exception!");
            collection = buildErrorCollection(routingContext, e);
            returnCollection(routingContext, collection);
          }
          returnCollection(routingContext, collection);
          log.info("getAreaNodeTypes is end!");
        }
      });
    } catch (Exception e) {
      log.error("getAreaNodeTypes is Exception!");
      String collection = RestfulTool
          .buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
      returnCollection(routingContext, collection);
    }

  }

  @Override
  public void registeResource(Router router) {
    router.get(BASEURL+"/areaNodeTypes").produces("application/json;charset=UTF-8").handler(this::getAreaNodeTypes);
    router.get(BASEURL+"/areaNodeTypes/:areaCode").produces("application/json;charset=UTF-8").handler(this::getAreaNodeTypes);//走一个方法
  }
}
