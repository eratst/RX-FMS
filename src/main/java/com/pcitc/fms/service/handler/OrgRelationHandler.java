package com.pcitc.fms.service.handler;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.OrgRelationService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.service.model.OrgRelation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Relations;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.Link;
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
public class OrgRelationHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(OrgRelationHandler.class);
	
	@Autowired
	private OrgRelationService orgRelationService;
	
	@Autowired
	private CheckType checkType;
	
	/**   
	 * @Title: getOrgRelations   
	 * @Description: TODO task mark zhenqiang.zhao	
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getOrgRelations(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase =request.absoluteURI();   
		String uri = StringUtils.replace(routingContext.normalisedPath(), " ", "");
		log.info("*** OrgRelationHandler START getOrgRelations ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		//以下条件可以为空
		String sourceCode = request.getParam("sourceCode") == null ? null : request.getParam("sourceCode").trim();
		String sourceType = request.getParam("sourceType") == null ? null : request.getParam("sourceType").trim();
		String targetCode = request.getParam("targetCode") == null ? null : request.getParam("targetCode").trim();
		String targetType = request.getParam("targetType") == null ? null : request.getParam("targetType").trim();
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		String enabledStr = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
		
		//分页
		Sort sort = new Sort(Sort.Direction.ASC, "orgRelationCode");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			List<com.pcitc.fms.bll.entity.OrgRelation> orgRelationList = null;
			try {	
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					checkType.checkURLEntityCode("factories", factoryCode,"factorys");
					CheckUtil.validateCodeStringMybeNullException("sourceCode", sourceCode);
					CheckUtil.validateCodeStringMybeNullException("targetCode", targetCode);
					Integer enabled =  CheckUtil.validateIdIntegerMybeNullException("enabled", enabledStr);
					List<Integer> idList = CheckUtil.buildStringToListInteger("ID", ids);
					CheckUtil.validateNegativeIntegerMybeNullFormat("skip", skipStr);
					Integer top = CheckUtil.validateNegativeIntegerMybeNullFormat("top", topStr);
					Pageable pageable = null;
					Integer skip = skipStr == null||"".equals(skipStr) ? 0 : Integer.parseInt(skipStr);
					if(StringUtils.isNotEmpty(topStr) && StringUtils.isNotEmpty(skipStr)){	
						top = CheckUtil.validateNegativeTopIntegerFormat("top", topStr);
						skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
					    pageable = PageUtil.pageable(top, skip, sort);
		    		}
					OrgRelation orgRelationModel = new OrgRelation(factoryCode, sourceCode, sourceType, targetCode, targetType, enabled, idList, top, skip);
					Pager<com.pcitc.fms.bll.entity.OrgRelation> sourceData = orgRelationService.getOrgRelations(orgRelationModel,pageable);
					PageUtil.mergePage(pagination, sourceData);
					orgRelationList  = sourceData.getContent();
					future.complete(orgRelationList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<OrgRelation> targets = new ArrayList<>();
				try {
					List<com.pcitc.fms.bll.entity.OrgRelation> orgRelationList = (List<com.pcitc.fms.bll.entity.OrgRelation>) res.result();
					List<OrgRelation> orgModelList = (List<OrgRelation>) ObjectConverter.listConverter(orgRelationList, OrgRelation.class);
					if (orgModelList != null && orgModelList.size()>0 ) {
						for (OrgRelation orgRelation : orgModelList) {
							orgRelation.setHref("/" + orgRelation.getOrgRelationId());
							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
							links.add(new Link(orgRelation.getTargetType(),URI.create(request.scheme()+"://"+request.host()+"/FactoryModelService/factories/"+orgRelation.getFactoryId()+"/"+CheckUtil.getUrlNameByTargetType(orgRelation.getTargetType())+"/"+orgRelation.getTargetId()),""));
							orgRelation.setLinkObjs(links);
							targets.add(orgRelation);
						}
					}
					collection = RestfulTool.buildCollection(targets, pagination,uri,OrgRelation.class);
				} catch (Exception e) {
					log.error("getOrgRelations is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getOrgRelations is end!");
			}
		});
	}
	
	/**   
	 * @Title: getOrgRelationById   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void getOrgRelationByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase =request.absoluteURI();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		String orgRelationCode = request.getParam("orgRelationCode") == null ? null : request.getParam("orgRelationCode").trim();//不能为空
		log.info("*** OrgRelationHandler START getOrgRelationById ***");
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			com.pcitc.fms.bll.entity.OrgRelation OrgRelation = null;
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				checkType.checkURLEntityCode("factories", factoryCode,"factories");
				OrgRelation = orgRelationService.getOrgRelationByCode(factoryCode ,CheckUtil.validateCodeException("orgRelationCode", orgRelationCode));
				future.complete(OrgRelation);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;// 返回结果字符串
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				List<OrgRelation> targets = new ArrayList<>();
				try {
					com.pcitc.fms.bll.entity.OrgRelation orgRelation = (com.pcitc.fms.bll.entity.OrgRelation) res.result();
					OrgRelation orgMode =  ObjectConverter.entityConverter(orgRelation, OrgRelation.class);
					if (orgMode != null) {
							orgMode.setHref("/" + orgMode.getOrgRelationId());
							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<pcitc.imp.common.ettool.baseresrep.Link>();
							links.add(new Link(orgMode.getTargetType(),URI.create(request.scheme()+"://"+request.host()+"/FactoryModelService/factories/"+orgMode.getFactoryId()+"/"+orgMode.getTargetType()+"/"+orgMode.getTargetId()),""));
							orgMode.setLinkObjs(links);
							targets.add(orgMode);
					}
					collection = RestfulTool.buildCollection(targets, hrefBase, OrgRelation.class);
				} catch (Exception e) {
					log.error("getOrgRelationById is Exception!");
					collection = buildErrorCollection(routingContext, e);
					returnCollection(routingContext, collection);
				}
				returnCollection(routingContext, collection);
				log.info("getOrgRelationById is end!");
			}
		});

	}
	
	/**   
	 * @Title: addOrgRelations   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void addOrgRelations(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** OrgRelationHandler START addOrgRelations ***");
		String body = routingContext.getBodyAsString();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		String creator = request.getParam("creator") == null ? null : request.getParam("creator").trim();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				List<com.pcitc.fms.bll.entity.OrgRelation> orgRelationList = null;
			    CheckUtil.validateCodeException("factoryCode", factoryCode);
				checkType.checkURLEntityCode("factories", factoryCode,"factories");
				List<OrgRelation> orgModleList =null;
				if (null != fileUploads && fileUploads.size()>0) {
					FileUpload fileUpload = (FileUpload) fileUploads.toArray()[0];
					if (null != fileUpload && fileUpload.size()>0) {
						File file = new File(fileUpload.uploadedFileName());
						ExcelHelper excel = ExcelHelper.readExcel(file);
						orgModleList = excel.toEntitys(OrgRelation.class);
					}
				}else{
					orgModleList = RestfulTool.toResourceRep(body,OrgRelation.class);
				}
				for (OrgRelation orgRelation : orgModleList) {
					orgRelation.setFactoryCode(factoryCode);
					if (creator != null) {
						orgRelation.setCreator(creator);
					}
				}
				
//				for (OrgRelation orgRelation : orgModleList) {
//					relations.setSourceCode(sourceCode);
//					relations.setSourceType(sourceType);
//					boolean flag = CheckUtil.buildStringToListStringNoCheck("targetType", SysGlobal.getDispatcherParam("relations."+relations.getSourceType()+".targetType")).contains(relations.getTargetType());
//					if (!flag) {
//						throw new com.pcitc.fms.exception.BusinessException("", "", CheckError.TARGET_TYPE_ISINCORRECT+":"+relations.getTargetType());
//					}
//				}
				
				orgModleList  = CurrencyCheck.checkObject(orgModleList, Operation.ADD);
				orgRelationList = ObjectConverter.listConverter(orgModleList,com.pcitc.fms.bll.entity.OrgRelation.class);
				future.complete(RestfulTool.buildCollection(ObjectConverter.listConverter(orgRelationService.addOrgRelations(orgRelationList), OrgRelation.class), hrefBase, com.pcitc.fms.service.model.OrgRelation.class));
			} catch (BusinessException be) {
				future.fail(be);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = "";
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);
			} else if (res.succeeded()) {
				collection = res.result().toString();
				returnCollection(routingContext, collection);
				log.info("getOrgRelations is end!");
			
			}
			
		});
	}

	/**   
	 * @Title: updateOrgRelation   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void updateOrgRelation(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** OrgRelationHandler START addOrgRelations ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		String orgRelationCode = request.getParam("orgRelationCode")==null?null:request.getParam("orgRelationCode").trim();
		String editor = request.getParam("editor") == null ? null : request.getParam("editor").trim();
//		String orgsType = request.getParam("orgsType") == null ? null : request.getParam("orgsType").trim();
		String body = routingContext.getBodyAsString();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				checkType.checkURLEntityCode("factories", factoryCode,"factories");
				List<OrgRelation> orgModelList = RestfulTool.toResourceRep(body,OrgRelation.class);
				for (OrgRelation orgRelation : orgModelList) {
					orgRelation.setCode(CheckUtil.validateCodeException("orgRelationCode", orgRelationCode));
					orgRelation.setFactoryCode(factoryCode);
					if (editor != null) {
						orgRelation.setEditor(editor);
					}
				}
				orgModelList  = CurrencyCheck.checkObject(orgModelList, Operation.UPDATE);
				// 验证,只能传递一条记录
				List<com.pcitc.fms.bll.entity.OrgRelation> OrgRelationList = ObjectConverter.listConverter(orgModelList, com.pcitc.fms.bll.entity.OrgRelation.class);
				for (com.pcitc.fms.bll.entity.OrgRelation orgRelation : OrgRelationList) {
					orgRelationService.updateOrgRelation(orgRelation);
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
	 * @Title: deleteOrgRelationsByIds   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteOrgRelationsByCodes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** OrgRelationHandler START addOrgRelations ***");
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				checkType.checkURLEntityCode("factories", factoryCode,"factories");
				orgRelationService.deleteOrgRelationByCodes(factoryCode,CheckUtil.buildStringToListString("$codeList", codes));
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
				log.info("deleteOrgRelationById is end!");
			}
		});

	}
	
	/**   
	 * @Title: deleteOrgRelationById   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteOrgRelationByCode(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** OrgRelationHandler START addOrgRelations ***");
		String orgRelationCode = request.getParam("orgRelationCode") == null ? null : request.getParam("orgRelationCode").trim();
		String factoryCode = request.getParam("factoryCode") == null ? null : request.getParam("factoryCode").trim();//不能为空
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				CheckUtil.validateCodeException("factoryCode", factoryCode);
				checkType.checkURLEntityCode("factories", factoryCode,"factories");
				orgRelationService.deleteOrgRelationByCode(factoryCode,CheckUtil.validateCodeException("orgRelationCode", orgRelationCode));
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
				log.info("deleteAdministration is end!");
			}
		});

	}
}
