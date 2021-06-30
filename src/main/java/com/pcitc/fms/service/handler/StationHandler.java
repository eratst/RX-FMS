package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.StationService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Station;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import java.util.List;

@SuppressWarnings("unchecked")
@Controller
public class StationHandler extends BaseHandler {

    @Autowired
    private StationService stationService;

    public void findAllStation(RoutingContext routingContext) {
        Sort sort = new Sort(Direction.DESC, "areaId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                Station station = QueryParams.getQueryParams(routingContext, Station.class);
                Pageable pageable = null;
                if (station.getSkip() != null && station.getTop() != null) {
                    pageable = PageUtil.pageable(station.getTop(), station.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.Station> sourceData;
                sourceData = stationService.findAllStation(station, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<Station> mo_com = ObjectConverter.listConverter(sourceData.getContent(), Station.class);
                future.complete(mo_com);
            } catch (Exception e) {
                future.fail(e);
            }
        }, res -> {
            String collection = null;
            if (res.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) res.cause());
            }
            try {
                if (res.succeeded()) {
                    collection = RestfulTool.buildCollection((List<Station>) res.result(), pagination, routingContext.request().uri(),
                            Station.class);
                } else {
                    collection = buildErrorCollection(routingContext, (Exception) res.cause());
                }
                returnCollection(routingContext, collection);
            } catch (Exception e) {
                String collecion = buildErrorCollection(routingContext, e);
                returnCollection(routingContext, collection);
            }
        });
    }
}
