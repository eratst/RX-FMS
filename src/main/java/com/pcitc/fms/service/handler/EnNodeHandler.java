package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.EnNodeService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.EnNode;
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
public class EnNodeHandler extends BaseHandler {

    @Autowired
    private EnNodeService enNodeService;

    public void findEnNodes(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "enNodeId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
                EnNode enNodeParam = QueryParams.getQueryParams(routingContext, EnNode.class);
                Pageable pageable = null;
                if (enNodeParam.getSkip() != null && enNodeParam.getTop() != null) {
                    pageable = PageUtil.pageable(enNodeParam.getTop(), enNodeParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.EnNode> sourceData;
                sourceData = enNodeService.getEnNodes(enNodeParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<EnNode> enNodes = ObjectConverter.listConverter(sourceData.getContent(), EnNode.class);
                future.complete(enNodes);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<EnNode>) result.result(), pagination,
                            routingContext.request().uri(), EnNode.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }
}
