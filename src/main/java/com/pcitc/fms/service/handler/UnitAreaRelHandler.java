package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.UnitAreaRelService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UnitAreaRel;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import java.util.List;

@SuppressWarnings("unchecked")
@Controller
public class UnitAreaRelHandler extends BaseHandler {

    @Autowired
    private UnitAreaRelService unitAreaRelService;

    public void findUnitAreaRels(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "unitAreaRelId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
            	UnitAreaRel unitAreaRelParam = QueryParams.getQueryParams(routingContext, UnitAreaRel.class);
                Pageable pageable = null;
                if (unitAreaRelParam.getSkip() != null && unitAreaRelParam.getTop() != null) {
                    pageable = PageUtil.pageable(unitAreaRelParam.getTop(), unitAreaRelParam.getSkip(), sort);
                }
                
                Pager<com.pcitc.fms.dal.pojo.UnitAreaRel> sourceData;
                sourceData = unitAreaRelService.getUnitAreaRels(unitAreaRelParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<UnitAreaRel> unitAreaRels = ObjectConverter.listConverter(sourceData.getContent(), UnitAreaRel.class);
                future.complete(unitAreaRels);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<UnitAreaRel>) result.result(), pagination,
                            routingContext.request().uri(), UnitAreaRel.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }

    
}
