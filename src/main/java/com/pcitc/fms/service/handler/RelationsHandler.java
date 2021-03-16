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

import com.pcitc.fms.bll.itf.RelationsService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Relations;
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
 * Title: RelationsHandler
 * Description:关联集合
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@Controller
public class RelationsHandler  extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(RelationsHandler.class);
	@Autowired
	private RelationsService relationsService;
	
	@Autowired
	private CheckType checkType;
	/**   
	 * @Title: getRelations   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getRelations(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		String uri = StringUtils.replace(routingContext.normalisedPath(), " ", "");
		log.info("*** RelationsHandler START getRelations ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		// 获取参数,按名连接集合的源类型,连接集合的目标类型查询
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String targetCode = request.getParam("targetCode") == null ? null : request.getParam("targetCode").trim();
		String targetType = request.getParam("targetType") == null ? null : request.getParam("targetType").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		//分页
		Sort sort = new Sort(Sort.Direction.ASC, "relationId");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Pagination pagination = new Pagination();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.Relations> relationsList = null;
			try {	
					List<String> codeList = CheckUtil.buildStringToListString("CODE", codes);
					 CheckUtil.validateCodeException("factoryCode", factoryCode);
					CheckUtil.validateCodeException("areasCode", areasCode);
					Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
					Integer skip = CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
					checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"relations");
					Pageable pageable = null;
					Relations relationsModel = new Relations(sourceCode, sourceType, targetCode, targetType, codeList, top, skip);
					if (StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)) {
						top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
						skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
						PageUtil.pageable(top, skip, sort);
					}
					Pager<com.pcitc.fms.bll.entity.Relations> sourceData = relationsService.getPagerRelations(relationsModel,pageable);
					PageUtil.mergePage(pagination, sourceData);
					relationsList = sourceData.getContent();
					future.complete(relationsList);
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
					List<Relations> targets = new ArrayList<>();
					List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList = (List<com.pcitc.fms.bll.entity.Relations>) res.result();
					 List<Relations> relationsModleList = ObjectConverter.listConverter(relationsModelEntityList, Relations.class);
					 if (relationsModleList != null) {
						 for (Relations relations : relationsModleList) {
								relations.setHref("/" + relations.getRelationId());
								List<pcitc.imp.common.ettool.baseresrep.Link> relationsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
								relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getSourceType(),URI.create(hrefBase+"/"+relations.getRelationId()+"/"+relations.getSourceType()),"(只支持查询)"));
								relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getTargetType(),URI.create(hrefBase+"/"+relations.getRelationId()+"/"+relations.getTargetType()),"(只支持查询)"));
								relations.setLinkObjs(relationsLinks);
								targets.add(relations);
							}
					 }
					
					collection = RestfulTool.buildCollection(targets, pagination,uri, Relations.class);
				} catch (Exception e) {
					log.error("getRelations is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getRelations is end!");
			}
		});
	

	}
	
	/**   
	 * @Title: getRelationsById   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getRelationsByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String sourceCode =request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String relationsCode = request.getParam("relationsCode")==null?null:request.getParam("relationsCode").trim();
		log.info("*** RelationsHandler START getRelationsById ***");
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			com.pcitc.fms.bll.entity.Relations relations = null;
			try {
//				checkType.check(parentType, "relations");
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"relations");
				relations = relationsService.getRelationsByCode(CheckUtil.validateCodeException("编码", relationsCode));
				future.complete(relations);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<Relations> targets = new ArrayList<>();
				try {
					com.pcitc.fms.bll.entity.Relations relationsEntity = (com.pcitc.fms.bll.entity.Relations) res.result();
					List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList = new ArrayList<com.pcitc.fms.bll.entity.Relations>();
					relationsModelEntityList.add(relationsEntity);
					List<Relations> relationsModelList = (List<Relations>) ObjectConverter.listConverter(relationsModelEntityList, Relations.class);
					if (relationsEntity != null) {
						for (Relations relations : relationsModelList) {
							relations.setHref("/" + relations.getRelationId());
							List<pcitc.imp.common.ettool.baseresrep.Link> relationsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
							relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getSourceType(),URI.create(hrefBase+"/"+relations.getSourceType()),"(只支持查询)"));
							relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getTargetType(),URI.create(hrefBase+"/"+relations.getTargetType()),"(只支持查询)"));
							relations.setLinkObjs(relationsLinks);
							targets.add(relations);
						}
					}
					
					collection = RestfulTool.buildCollection(targets, hrefBase, Relations.class);
				} catch (Exception e) {
					log.error("getRelationsById is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getRelationsById is end!");
			}
		});

	}
	/**
	 * 罐量计算使用
	 * @param routingContext
	 */
	public void getRelationsForPM(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.absoluteURI();
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String codes = request.getParam("$sourceCodeList") == null ? null : request.getParam("$sourceCodeList").trim();
		String targetType = request.getParam("targetType") == null ? null : request.getParam("targetType").trim();
		log.info("*** RelationsHandler START getRelationsById ***");
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.Relations> relations = null;
			try {
				List<String> sourceCodeList = CheckUtil.buildStringToListStringNoCheck("编码", codes);
				relations = relationsService.getRelationsCodeIn(sourceType,sourceCodeList,targetType);
				future.complete(relations);
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
					List<Relations> targets = new ArrayList<>();
					List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList = (List<com.pcitc.fms.bll.entity.Relations>) res.result();
					 List<Relations> relationsModleList = ObjectConverter.listConverter(relationsModelEntityList, Relations.class);
					 if (relationsModleList != null) {
						 for (Relations relations : relationsModleList) {
								relations.setHref("/" + relations.getRelationId());
								List<pcitc.imp.common.ettool.baseresrep.Link> relationsLinks = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
								relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getSourceType(),URI.create(hrefBase+"/"+relations.getRelationId()+"/"+relations.getSourceType()),"(只支持查询)"));
								relationsLinks.add(new pcitc.imp.common.ettool.baseresrep.Link(relations.getTargetType(),URI.create(hrefBase+"/"+relations.getRelationId()+"/"+relations.getTargetType()),"(只支持查询)"));
								relations.setLinkObjs(relationsLinks);
								targets.add(relations);
							}
					 }
					
					collection = RestfulTool.buildCollection(relationsModleList,hrefBase, Relations.class);
				} catch (Exception e) {
					log.error("getRelations is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getRelations is end!");
			}
		});

	}
	
	/**   
	 * @Title: addRelations   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void addRelations(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** RelationsHandler START addRelations ***");
		String body = routingContext.getBodyAsString();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//TODO zhaozhenqiang 需要校验
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();//TODO zhaozhenqiang 需要校验
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();//TODO zhaozhenqiang 需要校验
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String sourceCode =request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String relationsCode = request.getParam("relationsCode")==null?null:request.getParam("relationsCode").trim();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("parentCode", sourceCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"relations");
				List<Relations> relationsModleList = null;
				List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList= null;
				if (null != fileUploads && fileUploads.size()>0) {
					FileUpload fileUpload = (FileUpload) fileUploads.toArray()[0];
					if (null != fileUpload && fileUpload.size()>0) {
						File file = new File(fileUpload.uploadedFileName());
						ExcelHelper excel = ExcelHelper.readExcel(file);
						relationsModleList = excel.toEntitys(Relations.class);
					}
				}else {
					relationsModleList = RestfulTool.toResourceRep(body,Relations.class);
				}
				for (Relations relations : relationsModleList) {
					relations.setSourceCode(sourceCode);
					relations.setSourceType(sourceType);
					boolean flag = CheckUtil.buildStringToListStringNoCheck("targetType", SysGlobal.getDispatcherParam("relations."+relations.getSourceType()+".targetType")).contains(relations.getTargetType());
					if (!flag) {
						throw new com.pcitc.fms.exception.BusinessException("", "", CheckError.TARGET_TYPE_ISINCORRECT+":"+relations.getTargetType());
					}
				}
				relationsModleList  = CurrencyCheck.checkObject(relationsModleList, Operation.ADD);
				relationsModelEntityList = ObjectConverter.listConverter(relationsModleList,com.pcitc.fms.bll.entity.Relations.class);
				future.complete(RestfulTool.buildCollection(ObjectConverter.listConverter(relationsService.addRelations(relationsModelEntityList), Relations.class), hrefBase, Relations.class));
			} catch (BusinessException be) {
				future.fail(be);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = "";
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				// 输出结果
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				collection = res.result().toString();
				returnCollection(routingContext, collection);
				log.info("getRelations is end!");
			}
		});
	}
	
	/**   
	 * @Title: updateRelations   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void updateRelations(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** RelationsHandler START addRelations ***");
		
		String relationsCode = request.getParam("relationsCode")==null?null:request.getParam("relationsCode").trim();
		String body = routingContext.getBodyAsString();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String sourceCode =request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String relationsIdStr = request.getParam("relationsCode")==null?null:request.getParam("relationsCode").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"relations");
				List<Relations> relationsModleList = RestfulTool.toResourceRep(body,Relations.class);
				for (Relations relations : relationsModleList) {
					relations.setSourceCode(sourceCode);
					relations.setSourceType(sourceType);
					boolean flag = CheckUtil.buildStringToListStringNoCheck("targetType", SysGlobal.getDispatcherParam("relations."+relations.getSourceType()+".targetType")).contains(relations.getTargetType());
					if (!flag) {
						throw new com.pcitc.fms.exception.BusinessException("", "", CheckError.TARGET_TYPE_ISINCORRECT+":"+relations.getTargetType());
					}
				}
				relationsModleList  = CurrencyCheck.checkObject(relationsModleList, Operation.UPDATE);
				// 验证,只能传递一条记录
				List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList = ObjectConverter.listConverter(relationsModleList, com.pcitc.fms.bll.entity.Relations.class);
				for (com.pcitc.fms.bll.entity.Relations relations : relationsModelEntityList) {
					relations.setCode(CheckUtil.validateCodeException("编码", relationsIdStr));
					relationsService.updateRelations(relations);
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
	 * @Title: deleteRelationsById   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteRelationsByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** RelationsHandler START addRelations ***");
		String relationsCode = request.getParam("relationsCode")==null?null:request.getParam("relationsCode").trim();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();
		String areas = request.getParam("areas") == null ? null : request.getParam("areas").trim();
		String areasCode = request.getParam("areasCode") == null ? null : request.getParam("areasCode").trim();
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String sourceCode =request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				CheckUtil.validateCodeException("sourceCode", sourceCode);
				CheckUtil.validateCodeException("areasCode", areasCode);
				checkType.checkURLForThree("factories", factoryCode,areas, areasCode, sourceType, sourceCode,"relations");
				relationsService.deleteRelationsByCode(CheckUtil.validateCodeException("编码",relationsCode));
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
				log.info("deleteRelationsById is end!");
			}
		});

	}
	
	/**   
	 * @Title: deleteRelationsByIds   
	 * @Description: TODO  
	 * @date 2017年6月16日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	/*public void deleteRelationsByIds(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** RelationsHandler START addRelations ***");
		String codeList = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				List<Integer> idIntegerList = CheckUtil.buildStringToListInteger(codeList);
				relationsService.deleteRelationsByIds(idIntegerList);
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
				log.info("deleteRelation is end!");
			}
		});

	}*/
}
