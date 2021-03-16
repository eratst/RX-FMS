package com.pcitc.fms.service.handler;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.ConnectionsService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.service.model.Connections;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

 /**
 * Title: ConnectionsHandler
 * Description:连接集合
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@Controller
public class ConnectionsHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(ConnectionsHandler.class);
	@Autowired
	private ConnectionsService connectionsService;
	
	@Autowired
	private CheckType checkType;
	/**   
	 * @Title: getConnections   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getConnections(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		String uri = StringUtils.replace(routingContext.normalisedPath(), " ", "");
		log.info("*** ConnectionsHandler START getConnections ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		// 获取参数,按名连接集合的源类型,连接集合的目标类型查询
		String parentCode =request.getParam("parentCode") == null ? null : request.getParam("parentCode").trim();
		String sourceId = request.getParam("sourceId") == null ? null : request.getParam("sourceId").trim();
		String targetId = request.getParam("targetId") == null ? null : request.getParam("targetId").trim();
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String sourceType = request.getParam("conParentType") == null ? null : request.getParam("conParentType").trim();
		String targetCode = request.getParam("targetCode") == null ? null : request.getParam("targetCode").trim();
		String targetType = request.getParam("targetType") == null ? null : request.getParam("targetType").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		//分页
		Sort sort = new Sort(Sort.Direction.ASC, "connectionId");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.Connections> conneList = null;
			try {	
					List<String> codeList = CheckUtil.buildStringToListString("连接关系编码", codes);
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					CheckUtil.validateCodeException("sourceCode", sourceCode);
					CheckUtil.validateCodeException("areasCode", areasCode);
					Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
					CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
					Integer skip = skipStr == null||"".equals(skipStr) ? 0 : Integer.parseInt(skipStr);
					CheckUtil.validateCodeStringMybeNullException("targetCode", targetCode);
					checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"connections");
					Pageable pageable = null;
					Connections connectionsModel = new Connections(CheckUtil.validateNegativeIntegerMybeNullFormat("sourceId",sourceId), sourceCode, sourceType,CheckUtil.validateNegativeIntegerMybeNullFormat("targetId",targetId), targetCode, targetType, codeList,top ,skip);
					connectionsModel.setSkip(skip);
					if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
						top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
						PageUtil.pageable(top, skip, sort);
					}
					Pager<com.pcitc.fms.bll.entity.Connections> sourceData =  connectionsService.getPageConnections(connectionsModel,pageable,factoryCode);
					PageUtil.mergePage(pagination, sourceData);
					conneList = sourceData.getContent();
					future.complete(conneList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				try {
					List<Connections> targets = new ArrayList<>();
					List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList = (List<com.pcitc.fms.bll.entity.Connections>) res.result();
					 List<Connections> ConnectionsModelList = ObjectConverter.listConverter(connectionsModelEntityList, Connections.class);
					 if (ConnectionsModelList != null) {
						 for (Connections connections : ConnectionsModelList) {
							 	connections.setHref("/" + connections.getConnectionId());
								List<pcitc.imp.common.ettool.baseresrep.Link> connectionsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
								connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getSourceType(),URI.create(hrefBase+"/"+connections.getConnectionId()+"/"+connections.getSourceType()),"(只支持查询)"));
								connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getTargetType(),URI.create(hrefBase+"/"+connections.getConnectionId()+"/"+connections.getTargetType()),"(只支持查询)"));
								connections.setLinkObjs(connectionsLinks);
								targets.add(connections);
						}
					 }
					collection = RestfulTool.buildCollection(targets, pagination,uri, Connections.class);
				} catch (Exception e) {
					log.error("getConnections is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getConnections is end!");
			}
		});
	}
	
	
	/**   
	 * @Title: getConnectionsByFactoryId   
	 * @Description: 根据工厂ID获取该工厂下的所有关系
	 * @param routingContext
	 * @date 2017年8月3日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getConnectionsByFactoryCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		String uri = StringUtils.replace(routingContext.normalisedPath(), " ", "");
		log.info("*** ConnectionsHandler START getConnections ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		//分页
		Sort sort = new Sort(Sort.Direction.ASC, "connectionId");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.Connections> conneList = null;
			try {	
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
					Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
					Pageable pageable = null;
					Connections connectionsModel = new Connections();
					if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
						top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
						skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
						PageUtil.pageable(top, skip, sort);
					}
					Pager<com.pcitc.fms.bll.entity.Connections> sourceData =  connectionsService.getPageConnections(connectionsModel,pageable,factoryCode);
					PageUtil.mergePage(pagination, sourceData);
					conneList = sourceData.getContent();
					future.complete(conneList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				try {
					List<Connections> targets = new ArrayList<>();
					List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList = (List<com.pcitc.fms.bll.entity.Connections>) res.result();
					 List<Connections> ConnectionsModelList = ObjectConverter.listConverter(connectionsModelEntityList, Connections.class);
					 if (ConnectionsModelList != null) {
						 for (Connections connections : ConnectionsModelList) {
							 	connections.setHref("/" + connections.getConnectionId());
								List<pcitc.imp.common.ettool.baseresrep.Link> connectionsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
								connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getSourceType(),URI.create(hrefBase+"/"+connections.getConnectionId()+"/"+connections.getSourceType()),"(只支持查询)"));
								connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getTargetType(),URI.create(hrefBase+"/"+connections.getConnectionId()+"/"+connections.getTargetType()),"(只支持查询)"));
								connections.setLinkObjs(connectionsLinks);
								targets.add(connections);
						}
					 }
					collection = RestfulTool.buildCollection(targets, pagination,uri, Connections.class);
				} catch (Exception e) {
					log.error("getConnections is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getConnections is end!");
			}
		});
	
	}
	
	/**   
	 * @Title: getConnectionsById   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getConnectionsByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		log.info("*** ConnectionsHandler START getConnectionsById ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String sourceType = request.getParam("conParentType") == null ? null : request.getParam("conParentType").trim();
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String connectionsCode = request.getParam("connectionsCode")==null?null:request.getParam("connectionsCode").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			com.pcitc.fms.bll.entity.Connections connections = null;
			try {
				 CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
//				checkType.check(sourceType, "connections");
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"connections");
				connections = connectionsService.getConnectionsByCode(CheckUtil.validateCodeException("连接关系编码", connectionsCode));
				future.complete(connections);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<Connections> targets = new ArrayList<>();
				try {
					com.pcitc.fms.bll.entity.Connections connectionsEntity = (com.pcitc.fms.bll.entity.Connections) res.result();
					List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList = new ArrayList<com.pcitc.fms.bll.entity.Connections>();
					connectionsModelEntityList.add(connectionsEntity);
					List<Connections> ConnectionsModelList = (List<Connections>) ObjectConverter.listConverter(connectionsModelEntityList, Connections.class);
					if (ConnectionsModelList != null) {
						for (Connections connections : ConnectionsModelList) {
							connections.setHref("/" + connections.getConnectionId());
							List<pcitc.imp.common.ettool.baseresrep.Link> connectionsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
							connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getSourceType(),URI.create(hrefBase+"/"+connections.getSourceType()),"(只支持查询)"));
							connectionsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(connections.getTargetType(),URI.create(hrefBase+"/"+connections.getTargetType()),"(只支持查询)"));
							connections.setLinkObjs(connectionsLinks);
							targets.add(connections);
						}
					}
					collection = RestfulTool.buildCollection(targets, hrefBase, Connections.class);
				} catch (Exception e) {
					log.error("getConnectionsById is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getConnectionsById is end!");
			}
		});

	}
	
	/**   
	 * @Title: addConnections   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void addConnections(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** ConnectionsHandler START addConnections ***");
		String body = routingContext.getBodyAsString();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String sourceType = request.getParam("conParentType") == null ? null : request.getParam("conParentType").trim();
		
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				//URL校验
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"connections");
				List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList = null;
				List<Connections> connectionsModleList = null;
				if (null != fileUploads && fileUploads.size()>0) {
					FileUpload fileUpload = (FileUpload) fileUploads.toArray()[0];
					if (null != fileUpload && fileUpload.size()>0) {
						File file = new File(fileUpload.uploadedFileName());
						ExcelHelper excel = ExcelHelper.readExcel(file);
						connectionsModleList = excel.toEntitys(Connections.class);
					}
				}else {
				   connectionsModleList = RestfulTool.toResourceRep(body,Connections.class);
				}
				for (Connections connections : connectionsModleList) {
					connections.setSourceCode(sourceCode);
					connections.setSourceType(sourceType);
					//校验报文数据
					boolean flag = CheckUtil.buildStringToListStringNoCheck("targetType", SysGlobal.getDispatcherParam("connections."+connections.getSourceType()+".targetType")).contains(connections.getTargetType());
					if (!flag) {
						throw new com.pcitc.fms.exception.BusinessException("", "", CheckError.TARGET_TYPE_ISINCORRECT+":"+connections.getTargetType());
					}
				}
				connectionsModleList  = CurrencyCheck.checkObject(connectionsModleList, Operation.ADD);
				connectionsModelEntityList = ObjectConverter.listConverter(connectionsModleList,com.pcitc.fms.bll.entity.Connections.class);
				future.complete(RestfulTool.buildCollection(ObjectConverter.listConverter(connectionsService.addConnections(connectionsModelEntityList), Connections.class), hrefBase, Connections.class));
			} catch (BusinessException be) {
				future.fail(be);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				collection = res.result().toString();
				returnCollection(routingContext, collection);
				log.info("getConnections is end!");
			
			}
		});
	}
	
	/**   
	 * @Title: updateConnections   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void updateConnections(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** ConnectionsHandler START addConnections ***");
		
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		String sourceType = request.getParam("conParentType") == null ? null : request.getParam("conParentType").trim();
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String connectionsCode = request.getParam("connectionsCode")==null?null:request.getParam("connectionsCode").trim();
		String body = routingContext.getBodyAsString();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
//				checkType.check(sourceType, "connections");
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				//URL校验
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"connections");
				List<Connections> connectionsModelList = RestfulTool.toResourceRep(body,Connections.class);
				for (Connections connections : connectionsModelList) {
					connections.setSourceCode(sourceCode);
					connections.setSourceType(sourceType);
					boolean flag = CheckUtil.buildStringToListString("targetType", SysGlobal.getDispatcherParam("connections."+connections.getSourceType()+".targetType")).contains(connections.getTargetType());
					if (!flag) {
						throw new com.pcitc.fms.exception.BusinessException("", "", CheckError.TARGET_TYPE_ISINCORRECT+":"+connections.getTargetType());
					}
				}
				connectionsModelList  = CurrencyCheck.checkObject(connectionsModelList, Operation.UPDATE);
				// 验证,只能传递一条记录
				List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList = ObjectConverter.listConverter(connectionsModelList, com.pcitc.fms.bll.entity.Connections.class);
				for (com.pcitc.fms.bll.entity.Connections connections : connectionsModelEntityList) {
					connections.setCode(CheckUtil.validateCodeException("连接关系编码", connectionsCode));
					connectionsService.updateConnections(connections);
				}
				future.complete();
			} catch (BusinessException be) {
				future.fail(be);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				returnMessage(routingContext, hrefBase);
			}
		});

	}
	
	/**   
	 * @Title: deleteConnectionsById   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteConnectionsByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** ConnectionsHandler START addConnections ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String connectionsCode = request.getParam("connectionsCode")==null?null:request.getParam("connectionsCode").trim();
		String sourceType = request.getParam("conParentType") == null ? null : request.getParam("conParentType").trim();
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
//				checkType.check(sourceType, "connections");
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"connections");
				List<String> idIntegerList = idIntegerList = new ArrayList<String>();
				idIntegerList.add(CheckUtil.validateCodeException("连接关系编码",connectionsCode));
				connectionsService.deleteConnectionsByCodes(idIntegerList);
				future.complete();
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				collection = "";
				returnCollection(routingContext, collection);
				log.info("deleteConnectionsById is end!");
			}
		});

	}
	
	/**   
	 * @Title: deleteConnectionsByIds   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	/*public void deleteConnectionsByIds(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** ConnectionsHandler START addConnections ***");
		String codeList = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				List<Integer> idIntegerList = CheckUtil.buildStringToListInteger(codeList);
				connectionsService.deleteConnectionsByIds(idIntegerList);
				future.complete();
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				collection = "";
				returnCollection(routingContext, collection);
				log.info("deleteConnectionsByIds is end!");
			}
		});

	}*/
}
