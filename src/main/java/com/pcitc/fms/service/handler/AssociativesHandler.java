package com.pcitc.fms.service.handler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AssociativesService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Associatives;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.Link;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class AssociativesHandler extends BaseHandler{
	
	@Autowired
	private AssociativesService associativesService;
	
	@SuppressWarnings("unchecked")
	public void findAssociatives(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future ->{
			
			try {
				
				Associatives associatives = QueryParams.getQueryParams(routingContext, Associatives.class);
				Pageable pageable = null;
				if(null != associatives.getSkip() && null!=associatives.getTop()){
					pageable = PageUtil.pageable(associatives.getTop(),associatives.getSkip(), null);
				}
				Pager<com.pcitc.fms.bll.entity.Associatives> sourceData;
				sourceData = associativesService.findAssociatives(associatives, pageable);
				if (routingContext.request().uri().contains("skip") && associatives.getSkip() != null
						&& associatives.getTop() == null) {
					if (associatives.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<associatives.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				
				List<Associatives> associativesEntity = ObjectConverter.listConverter(sourceData.getContent(), Associatives.class);
				future.complete(associativesEntity);
				
			} catch (Exception e) {
				future.fail(e);
			}
			
		}, res ->{
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<Associatives> targets = (List<Associatives>) res.result();
				try {
					
					for (Associatives target : targets) {
						List<Link> links = new ArrayList<>();
						Link linkOne = new Link("materials",URI.create("/FactoryModelService/associatives/"+target.getNodeCode()+"/materials"),"物料集合");
						links.add(linkOne);
						target.setHref(routingContext.request().uri());
						target.setLinkObjs(links);
					}
					
					collection = RestfulTool.buildCollection(targets, pagination, hrefBase, com.pcitc.fms.service.model.Associatives.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
			}
		});
	}

}
