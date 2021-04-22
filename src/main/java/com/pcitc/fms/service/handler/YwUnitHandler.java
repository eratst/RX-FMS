package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.YwUnitService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.YwUnit;
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
public class YwUnitHandler extends BaseHandler {

    @Autowired
    private YwUnitService ywUnitService;

    public void findYwUnits(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "ywUnitId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                YwUnit ywUnitParam = QueryParams.getQueryParams(routingContext, YwUnit.class);
                Pageable pageable = null;
                if (ywUnitParam.getSkip() != null && ywUnitParam.getTop() != null) {
                    pageable = PageUtil.pageable(ywUnitParam.getTop(), ywUnitParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.YwUnit> sourceData;
                sourceData = ywUnitService.getYwUnits(ywUnitParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<YwUnit> ywUnits = ObjectConverter.listConverter(sourceData.getContent(), YwUnit.class);
                future.complete(ywUnits);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<YwUnit>) result.result(), pagination,
                            routingContext.request().uri(), YwUnit.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }
}
