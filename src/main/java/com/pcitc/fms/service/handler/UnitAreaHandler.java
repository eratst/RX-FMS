package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.UnitAreaService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UnitArea;
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

/**
 * @author yalin.zhao
 * @date 2021/4/21 9:44
 */
@SuppressWarnings("unchecked")
@Controller
public class UnitAreaHandler extends BaseHandler {

    @Autowired
    private UnitAreaService unitAreaService;

    /**
     * 查询所有
     *
     * @param routingContext
     */
    public void findUnitAreas(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "unitAreaId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                UnitArea unitAreaParam = QueryParams.getQueryParams(routingContext, UnitArea.class);
                Pageable pageable = null;
                if (unitAreaParam.getSkip() != null && unitAreaParam.getTop() != null) {
                    pageable = PageUtil.pageable(unitAreaParam.getTop(), unitAreaParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.UnitArea> sourceData;
                sourceData = unitAreaService.getUnitAreas(unitAreaParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<UnitArea> unitAreas = ObjectConverter.listConverter(sourceData.getContent(), UnitArea.class);
                future.complete(unitAreas);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<UnitArea>) result.result(), pagination,
                            routingContext.request().uri(), UnitArea.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }
}
