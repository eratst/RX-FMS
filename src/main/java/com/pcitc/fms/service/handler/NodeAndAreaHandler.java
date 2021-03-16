package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.NodeAndAreaService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.service.model.NodeAndArea;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

 /**
 * Title: OrgRelationHandler
* Description: TODO 组织结构视图实体关联
 * @author zhenqiang.zhao
 * @date 2017年7月1日
 * @version 1.0
 */
@Controller
public class NodeAndAreaHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(NodeAndAreaHandler.class);
	
	@Autowired
	private NodeAndAreaService nodeAndAreaService;
	
	
	/**   
	 * @Title: getNodeAndAreas   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年8月4日      
	 * @return: void
	 * @author 赵振强      
	 */
	public void  getNodeAndAreas(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase =request.absoluteURI();
		String uri = StringUtils.replace(routingContext.normalisedPath(), " ", "");
		log.info("*** OrgRelationHandler START getOrgRelations ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		//以下条件可以为空
		String nodeCode = request.getParam("nodeCode") == null ? null : request.getParam("nodeCode").trim();
		String nodeType = request.getParam("nodeType") == null ? null : request.getParam("nodeType").trim();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.NodeAndArea> nodeAndAreaList = new ArrayList();
			try {	
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					CheckUtil.validateCodeStringMybeNullException("nodeCode", nodeCode);
					NodeAndArea nodeAndAreaModel = new NodeAndArea(null, null, null, nodeType, null, null, null, null, factoryCode);
					com.pcitc.fms.bll.entity.NodeAndArea nodeAndArea = nodeAndAreaService.getNodeAndAreas(nodeAndAreaModel);
					nodeAndAreaList.add(nodeAndArea);
					future.complete(nodeAndAreaList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<NodeAndArea> targets = new ArrayList<>();
				try {
					List<com.pcitc.fms.bll.entity.NodeAndArea> NodeAndAreaList = (List<com.pcitc.fms.bll.entity.NodeAndArea>) res.result();
					List<NodeAndArea> orgModelList = (List<NodeAndArea>) ObjectConverter.listConverter(NodeAndAreaList, NodeAndArea.class);
					if (orgModelList != null && orgModelList.size()>0 ) {
						for (NodeAndArea NodeAndArea : orgModelList) {
//							NodeAndArea.setHref("/" + NodeAndArea.getNodeAndAreaId());
//							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
//							links.add(new Link(NodeAndArea.getTargetType(),URI.create(request.scheme()+"://"+request.host()+"/FactoryModelService/factories/"+NodeAndArea.getFactoryId()+"/"+CheckUtil.getUrlNameByTargetType(NodeAndArea.getTargetType())+"/"+NodeAndArea.getTargetId()),""));
//							NodeAndArea.setLinkObjs(links);
							targets.add(NodeAndArea);
						}
					}
					collection = RestfulTool.buildCollection(targets,uri,NodeAndArea.class);
				} catch (Exception e) {
					log.error("getNodeAndAreas is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getNodeAndAreas is end!");
			}
		});
	}
	
}
