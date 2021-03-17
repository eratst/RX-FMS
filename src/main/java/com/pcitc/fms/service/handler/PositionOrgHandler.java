package com.pcitc.fms.service.handler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.PositionOrgService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PositionOrg;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.baseresrep.Link;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class PositionOrgHandler extends BaseHandler{
	
	@Autowired
	private PositionOrgService positionOrgService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void getPositionOrgs(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		try {
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination();
			vertx.executeBlocking(future -> {
				try {
					PositionOrg positionModel = QueryParams.getQueryParams(routingContext, PositionOrg.class);
					Pageable pageable = null;
					if (positionModel.getTop() != null && positionModel.getSkip() == null) {
						positionModel.setSkip(0);
					}
					if (positionModel.getTop() != null && positionModel.getSkip() != null) {
						pageable =  PageUtil.pageable(positionModel.getTop(),positionModel.getSkip(), null);
					}


					Pager<com.pcitc.fms.bll.entity.PositionOrg> sourceData = positionOrgService.getPositionOrgs(positionModel, pageable);
					if (routingContext.request().uri().contains("skip") && positionModel.getSkip() != null
							&& positionModel.getTop() == null) {
						if (positionModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < positionModel.getSkip(); i++) {
								sourceData.getContent().remove(i);
							}
							sourceData.setTotalElements((long) sourceData.getContent().size());
						}
					}
					PageUtil.mergePage(pagination, sourceData);

					List<com.pcitc.fms.bll.entity.PositionOrg> enList = sourceData.getContent();
					List<PositionOrg> positionMo = ObjectConverter.listConverter(enList, PositionOrg.class);
					future.complete(positionMo);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<PositionOrg> targets = (List<PositionOrg>) res
							.result();
					try {
						
//						for (PositionOrg target : targets) {
//							List<Link> links = new ArrayList<>();
//							Link linkOne = new Link("users",URI.create("/FactoryModelService/orgs/"+target.getOrgCode()+"/users"),"用户集合");
//							links.add(linkOne);
//							target.setHref(routingContext.request().uri());
//							target.setLinkObjs(links);
//						}
						
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								PositionOrg.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	}

}
