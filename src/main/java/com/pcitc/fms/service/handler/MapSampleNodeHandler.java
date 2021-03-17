package com.pcitc.fms.service.handler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.MapSampleNodeService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.MapSampleNode;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TankArea;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.baseresrep.Link;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class MapSampleNodeHandler extends BaseHandler{

	
	@Autowired
	private MapSampleNodeService mapSampleNodeService;
	
	@Autowired
	private CheckType checkType;
	
	public void getMapSampleNodes(RoutingContext routingContext) {


		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.MapSampleNode> mapSampleNodeEntity = new ArrayList<>();
				try {
					MapSampleNode mapSampleNodeModel = QueryParams.getQueryParams(routingContext, MapSampleNode.class);
					Pageable pageable = null;
					if (mapSampleNodeModel.getTop()!=null && mapSampleNodeModel.getSkip()==null) {
						mapSampleNodeModel.setSkip(0);
					}
					if (mapSampleNodeModel.getTop()!=null && mapSampleNodeModel.getSkip()!=null) {
						pageable =  PageUtil.pageable(mapSampleNodeModel.getTop(),mapSampleNodeModel.getSkip(), null);
					}
					
					Pager<com.pcitc.fms.bll.entity.MapSampleNode> sourceData = mapSampleNodeService.getPageMapSampleNodes(mapSampleNodeModel, pageable);
					PageUtil.mergePage(pagination, sourceData);
					mapSampleNodeEntity = sourceData.getContent();
					List<MapSampleNode> modelMapSampleNodes = ObjectConverter.listConverter(mapSampleNodeEntity, MapSampleNode.class);
					future.complete(modelMapSampleNodes);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<MapSampleNode> targets = (List<MapSampleNode>) res.result();
					try {
						for (MapSampleNode target : targets) {
							List<Link> links = new ArrayList<>();
							Link linkOne = new Link("samplePoints",URI.create("/FactoryModelService/mapSampleNodes/"+target.getNodeCode()+"/samplePoints"),"采样点集合");
							links.add(linkOne);
							target.setHref(routingContext.request().uri());
							target.setLinkObjs(links);
						}
						
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								MapSampleNode.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}
		
	}


}
