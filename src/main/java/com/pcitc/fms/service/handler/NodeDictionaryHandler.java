package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictionaryService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.NodeDictionaryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.LoadingDock;
import com.pcitc.fms.service.model.LoadingDockStr;
import com.pcitc.fms.service.model.NodeDictionary;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PipeNetwork;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;


/*
*<p>Title: NodeDictionaryHandler<p>
*<p>Description: 节点字典表handler</p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年9月25日
 */
@Controller
public class NodeDictionaryHandler extends BaseHandler{

	private static Logger log = LoggerFactory.getLogger(NodeDictionaryHandler.class);
	@Autowired
	private AreaDictionaryService areaDictionaryTableService;
	@Autowired
    private FactoryService factoryService;
	@Autowired
	private NodeDictionaryService nodeDictionaryService;
	
	@Autowired
	private Environment env;
	
		public void getNodeDictionaryTables(RoutingContext routingContext){
		Pagination pagination = new Pagination();
		String hrefBase = routingContext.request().uri();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryEntity = new ArrayList<>();
				try {
					nodeDictionaryEntity = getData(routingContext,pagination);
					List<NodeDictionary> nodeDictionarys = ObjectConverter.listConverter(nodeDictionaryEntity, NodeDictionary.class);
					future.complete(nodeDictionarys);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<NodeDictionary> targets = (List<NodeDictionary>) res.result();
					try {
						
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
	   					targets = (List<NodeDictionary>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								NodeDictionary.class);
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

		private List<com.pcitc.fms.bll.entity.NodeDictionary> getData(RoutingContext routingContext,
				Pagination pagination) throws Exception, BusinessException {
			List<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryEntity;
			NodeDictionary nodeDictionaryModel = QueryParams.getQueryParams(routingContext, NodeDictionary.class);
			Pageable pageable = null;
			if (nodeDictionaryModel.getTop()!=null && nodeDictionaryModel.getSkip()==null) {
				nodeDictionaryModel.setSkip(0);
			}
			if (nodeDictionaryModel.getSkip() != null && nodeDictionaryModel.getTop() != null){
				pageable =  PageUtil.pageable(nodeDictionaryModel.getTop(),nodeDictionaryModel.getSkip(), null);
			}
			Pager<com.pcitc.fms.bll.entity.NodeDictionary> sourceData = nodeDictionaryService.getNodeDictionaryTablesByModel(nodeDictionaryModel,pageable);

			if (routingContext.request().uri().contains("skip") && nodeDictionaryModel.getSkip() != null
					&& nodeDictionaryModel.getTop() == null) {
				if (nodeDictionaryModel.getSkip()>=sourceData.getContent().size()) {
					sourceData.setContent(null);
				} else {
					for (int i=0;i<nodeDictionaryModel.getSkip();i++) {
						sourceData.getContent().remove(0);
					}
				}
			}
			PageUtil.mergePage(pagination, sourceData);
			nodeDictionaryEntity = sourceData.getContent();
			return nodeDictionaryEntity;
		}
	

    
//	//查询指定节点字典表-指定
//	public void getNodeDictionaryTableByCode(RoutingContext routingContext){
//		HttpServerRequest request = routingContext.request();
//		String hrefBase = request.uri();
//		log.info("*** NodeDictionaryHandler START getNodeDictionaryTableByCode ***");
//		String areaDictionaryTableCode = request.getParam("areaDictionaryTableCode") == null ? null : request.getParam("areaDictionaryTableCode").trim();
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
//		String nodeDictionaryCode = request.getParam("nodeDictionaryCode") == null ? null : request.getParam("nodeDictionaryCode").trim();
//		try{
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future ->{
//				try{
//				    List<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryTableEntityList = new ArrayList<>();
//				    CheckUtil.checkCode(factoryCode);
//                    CheckUtil.checkCode(areaDictionaryTableCode);
//                    CheckUtil.checkCode(nodeDictionaryCode);
//                    checkUrl(factoryCode,areaDictionaryTableCode,nodeDictionaryCode);
//                    nodeDictionaryTableEntityList = nodeDictionaryService.getNodeDictionaryByCode(areaDictionaryTableCode,nodeDictionaryCode);
//					future.complete(nodeDictionaryTableEntityList);
//				}catch(Exception e){
//					future.fail(e);
//				}
//			}, res ->{
//				String collection = null;
//				if(res.failed()){
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//					returnCollection(routingContext, collection);
//				}else if(res.succeeded()){
//					List<com.pcitc.fms.service.model.NodeDictionary> targets = new ArrayList<>();
//					try{
//						List<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryTableEntityList = (List<com.pcitc.fms.bll.entity.NodeDictionary>)res.result();
//						for(com.pcitc.fms.bll.entity.NodeDictionary nodeDictionaryTable :nodeDictionaryTableEntityList){
//						    com.pcitc.fms.service.model.NodeDictionary target = ObjectConverter.entityConverter(nodeDictionaryTable, com.pcitc.fms.service.model.NodeDictionary.class);
//							target.setHref("/"+target.getNodeCode());
//							List<String> strList = new ArrayList<String>();
//							targets.add(target);
//						}
//						collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.NodeDictionary.class);
//					}catch(Exception e){
//						log.error("getNodeDictionaryTableByCode is Exception!");
//						collection = buildErrorCollection(routingContext, e);
//						returnCollection(routingContext, collection);
//					}
//					returnCollection(routingContext, collection);
//					log.info("getNodeDictionaryTableByCode is end!");
//					
//				}
//			});
//		}catch(Exception e){
//			log.error("getLoadingDockById is Exception!");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
//			returnCollection(routingContext, collection);
//		}
//	}
//	
}
