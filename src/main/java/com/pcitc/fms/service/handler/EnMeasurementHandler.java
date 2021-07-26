package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.EnMeasurementService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.EnMeasurement;
import com.pcitc.fms.service.model.Pager;
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
public class EnMeasurementHandler extends BaseHandler {

    @Autowired
    private EnMeasurementService enMeasurementService;

    /**
     * 能源度量指标
     * 查询所有
     *
     * @param routingContext
     */
    public void findEnMeasurements(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "enMeasurementId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                EnMeasurement enMeasurementParam = QueryParams.getQueryParams(routingContext, EnMeasurement.class);
                Pageable pageable = null;
                if (enMeasurementParam.getSkip() != null && enMeasurementParam.getTop() != null) {
                    pageable = PageUtil.pageable(enMeasurementParam.getTop(), enMeasurementParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.EnMeasurement> sourceData;
                sourceData = enMeasurementService.getEnMeasurements(enMeasurementParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<EnMeasurement> enMeasurements = ObjectConverter.listConverter(sourceData.getContent(), EnMeasurement.class);
                future.complete(enMeasurements);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<EnMeasurement>) result.result(), pagination,
                            routingContext.request().uri(), EnMeasurement.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }
}
