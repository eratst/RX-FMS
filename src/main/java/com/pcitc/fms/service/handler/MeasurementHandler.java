package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.MeasurementService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Measurement;
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
public class MeasurementHandler extends BaseHandler {

    @Autowired
    private MeasurementService measurementService;

    /**
     * 度量指标
     * 查询所有
     *
     * @param routingContext
     */
    public void findMeasurements(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "measurementId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                Measurement measurementParam = QueryParams.getQueryParams(routingContext, Measurement.class);
                Pageable pageable = null;
                if (measurementParam.getSkip() != null && measurementParam.getTop() != null) {
                    pageable = PageUtil.pageable(measurementParam.getTop(), measurementParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.Measurement> sourceData;
                sourceData = measurementService.getMeasurements(measurementParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<Measurement> measurements = ObjectConverter.listConverter(sourceData.getContent(), Measurement.class);
                future.complete(measurements);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<Measurement>) result.result(), pagination,
                            routingContext.request().uri(), Measurement.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }
}
