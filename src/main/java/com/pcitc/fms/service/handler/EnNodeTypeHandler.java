package com.pcitc.fms.service.handler;

import com.pcitc.fms.bll.itf.EnNodeTypeService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.EnNodeType;
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
public class EnNodeTypeHandler extends BaseHandler {

    @Autowired
    private EnNodeTypeService enNodeTypeService;

    public void findEnNodeTypes(RoutingContext routingContext) {
        Sort sort = new Sort(Sort.Direction.DESC, "enNodeTypeId");
        Pagination pagination = new Pagination();
        Vertx vertx = routingContext.vertx();
        vertx.executeBlocking(future -> {
            try {
            	EnNodeType enNodeTypeParam = QueryParams.getQueryParams(routingContext, EnNodeType.class);
                Pageable pageable = null;
                if (enNodeTypeParam.getSkip() != null && enNodeTypeParam.getTop() != null) {
                    pageable = PageUtil.pageable(enNodeTypeParam.getTop(), enNodeTypeParam.getSkip(), sort);
                }
                Pager<com.pcitc.fms.dal.pojo.EnNodeType> sourceData;
                sourceData = enNodeTypeService.getEnNodeTypes(enNodeTypeParam, pageable);
                PageUtil.mergePage(pagination, sourceData);
                List<EnNodeType> enNodeTypes = ObjectConverter.listConverter(sourceData.getContent(), EnNodeType.class);
                future.complete(enNodeTypes);
            } catch (Exception e) {
                future.fail(e);
            }
        }, result -> {
            String collection = null;
            if (result.failed()) {
                collection = buildErrorCollection(routingContext, (Exception) result.cause());
            } else if (result.succeeded()) {
                try {
                    collection = RestfulTool.buildCollection((List<EnNodeType>) result.result(), pagination,
                            routingContext.request().uri(), EnNodeType.class);
                } catch (Exception e) {
                    collection = buildErrorCollection(routingContext, e);
                }
            }
            returnCollection(routingContext, collection);
        });
    }

}
