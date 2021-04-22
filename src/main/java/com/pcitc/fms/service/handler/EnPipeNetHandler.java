package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.EnPipeNetService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.EnPipeNet;
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
public class EnPipeNetHandler extends BaseHandler {

    @Autowired
    private EnPipeNetService enPipeNetService;

    /**
     * 查询所有
     *
     * @param routingContext
     */
    public void findEnPipeNets(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "netId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                EnPipeNet enPipeNetParam = QueryParams.getQueryParams(routingContext, EnPipeNet.class);
                Pageable pageable = null;
                if (enPipeNetParam.getSkip() != null && enPipeNetParam.getTop() != null) {
                    pageable = PageUtil.pageable(enPipeNetParam.getTop(), enPipeNetParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.EnPipeNet> sourceData;
                sourceData = enPipeNetService.getEnPipeNets(enPipeNetParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<EnPipeNet> enPipeNets = ObjectConverter.listConverter(sourceData.getContent(), EnPipeNet.class);
                future.complete(enPipeNets);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<EnPipeNet>) result.result(), pagination,
                            routingContext.request().uri(), EnPipeNet.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }

}
