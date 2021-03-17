package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.NodeTopDTLService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.NodeTopDTL;
import com.pcitc.fms.service.model.NodeTopDTLPath;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SideLine;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class NodeTopDTLHandler extends BaseHandler {
	@Autowired
	private NodeTopDTLService nodeTopDTLService;
	
//	public void getNodeTopDTLs(RoutingContext routingContext){
//		HttpServerRequest request = routingContext.request();
//		Pagination pagination = new Pagination();
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				List<com.pcitc.fms.bll.entity.NodeTopDTL> nodeTopDTLEntity = new ArrayList<>();
//				try {
//					NodeTopDTL nodeTopDTLModel = QueryParams.getQueryParams(routingContext, NodeTopDTL.class);
//					Pageable pageable = null;
//					if (nodeTopDTLModel.getTop()!=null && nodeTopDTLModel.getSkip()==null) {
//						nodeTopDTLModel.setSkip(0);
//					}
//					if (nodeTopDTLModel.getSkip() != null && nodeTopDTLModel.getTop() != null){
//						pageable =  PageUtil.pageable(nodeTopDTLModel.getTop(),nodeTopDTLModel.getSkip(), null);
//					}
//					//开始查询
//					List<String> list = nodeTopDTLService.getNodeTopDTLs(nodeTopDTLModel,pageable);
//					List<NodeTopDTLPath> dtlPathList=new ArrayList<NodeTopDTLPath>();
//					NodeTopDTLPath dtlPath=new NodeTopDTLPath();
//					dtlPath.setPath(list.toString());
//					dtlPath.setSnodeName(nodeTopDTLModel.getSnodeName());
//					dtlPath.setTnodeName(nodeTopDTLModel.getTnodeName());
//					dtlPathList.add(dtlPath);
//					future.complete(dtlPathList);
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//					returnCollection(routingContext, collection);
//
//				} else if (res.succeeded()) {
//					List<NodeTopDTLPath> targets = (List<NodeTopDTLPath>) res.result();
//					try {
//						String linkNames = "nodeDictionaries";
//						String baseUri = "/FactoryModelService/nodeDictionaries";
//						targets = (List<NodeTopDTLPath>) setModelEntityWithLinks(targets,linkNames,baseUri);
//						
//						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
//								NodeTopDTLPath.class);
//					} catch (Exception e) {
//						e.printStackTrace();
//						returnCollection(routingContext, e.getMessage());
//					}
//					returnCollection(routingContext, collection);
//				}
//			});
//		} catch (Exception e) {
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
//			returnCollection(routingContext, collection);
//		}
//	}
	
	
	
	@SuppressWarnings("unchecked")
	public void findAll(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.NodeTopDTL> nodeTopDTLEntity = new ArrayList<>();
				try {
					com.pcitc.fms.service.model.NodeTopDTL nodeTopDTL = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.NodeTopDTL.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.NodeTopDTL> sourceData;
					if (null != nodeTopDTL.getSkip()&& null!=nodeTopDTL.getTop()) {
						pageable = PageUtil.pageable(nodeTopDTL.getTop(),nodeTopDTL.getSkip(),null);
					} 
					sourceData = nodeTopDTLService.getAll(nodeTopDTL, pageable);
					
					if (routingContext.request().uri().contains("skip") && nodeTopDTL.getSkip() != null
							&& nodeTopDTL.getTop() == null) {
						if (nodeTopDTL.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<nodeTopDTL.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<NodeTopDTL> modelNodeTopDTLs = ObjectConverter.listConverter(sourceData.getContent(), NodeTopDTL.class);
					future.complete(modelNodeTopDTLs);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<NodeTopDTLPath> targets = (List<NodeTopDTLPath>) res.result();
					try {
						String linkNames = "nodeDictionaries";
						String baseUri = "/FactoryModelService/nodeDictionaries";
						targets = (List<NodeTopDTLPath>) setModelEntityWithLinks(targets,linkNames,baseUri);
						
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								NodeTopDTLPath.class);
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
