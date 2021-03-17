package com.pcitc.fms.service.handler;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.FactorySite;
import com.pcitc.fms.service.model.Pager;
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

/**
 * 办公区handler
 * @author HanXiao
 * 2017.11.20
 */
@Controller
public class FactorySiteHandler extends BaseHandler {
//
//	private static Logger log = LoggerFactory.getLogger(FactorySiteHandler.class);
//	@Autowired
//	private FactorySiteService factorySiteService;
//	@Autowired
//	private TPmOrgDao tPmOrgDao;
//	@Autowired
//	private AreaDictGatherService areaDictService;
//	@Autowired
//	private AreaDictionaryDao areaDictionaryDao;
//	@SuppressWarnings("unchecked")
//	public void getFactorySites(RoutingContext routingContext) {
//
//		HttpServerRequest request = routingContext.request();
//		String uri = request.absoluteURI();
//		String hrefBase = request.uri();
//		log.info("*** FactorySiteHandler START getFactorySites ***");
//		//从url获取的参数
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
//		String factoryIdStr = request.getParam("factoryId")==null?null:request.getParam("factoryId").trim();
//		String orgIdStr = request.getParam("orgId")==null?null:request.getParam("orgId").trim();
//		String typeByUrl = CheckUtil.getUrlType(hrefBase).trim();
//		//条件查询参数
//		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
//		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
//		String enabledStr = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
//		//集合与分页查询参数
//		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
//		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
//		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
//		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
//		Sort sort = new Sort(Sort.Direction.ASC, "factorySiteId");
//		Pagination pagination = new Pagination();
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntity = null;
//				try {
//					
//					 //校验从url获取的参数,不为空字段
//				    String  factoryCodestr = CheckUtil.getParamForString("factoryCode", factoryCode, 36, true);
//				    Integer factoryId = CheckUtil.getParamForInteger("factoryId", factoryIdStr, 6, true);
//				    //校验条件查询参数,可以空字段
//				    Integer orgId = CheckUtil.getParamForInteger("orgId", orgIdStr, 6, true);
//				    String nameStr = CheckUtil.getParamForString("name", name, 50, true);
//				    String shortNameStr = CheckUtil.getParamForString("shortName", shortName, 50, true);
//				    Integer enabled = CheckUtil.enabled("enabled", enabledStr);
//				    //校验集合与分页查询参数,可以空字段
//				    List<String> codeList = CheckUtil.checkCodeList(codes);
//					List<Integer> idList = CheckUtil.checkIdLists(ids);
//					Integer top = CheckUtil.getParamForInteger("top", topStr, 6, true);
//					Integer skip = CheckUtil.getParamForInteger("skip", skipStr, 6, true);
//					//分页校验
//					Pageable pageable = null;
//					if (null != topStr && !"".equals(topStr) && null != skip) {
//						pageable = PageUtil.pageable(top, skip, sort);
//					}
//					//对工厂和区域url校验并返回factoryId
//					factoryId = checkUrl(factoryCode,null);
//					//校验orgId是否存在
//					orgId = checkOrgId(orgId);
//					
//					//组装办公区实体
//					com.pcitc.fms.service.model.FactorySite factorySiteModel = new FactorySite(nameStr, factoryId, orgId, shortNameStr, enabled, codeList, idList, top, skip);
//					
//					//开始查询
//					Pager<com.pcitc.fms.bll.entity.FactorySite> sourceData = factorySiteService.getPageFactorySites(factorySiteModel, pageable);
//					PageUtil.mergePage(pagination, sourceData);
//					factorySiteEntity  = sourceData.getContent();
//					future.complete(factorySiteEntity);
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;// 返回结果字符串
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//					returnCollection(routingContext, collection);
//				} else if (res.succeeded()) {
//					try {
//						List<com.pcitc.fms.bll.entity.FactorySite> factorySiteList = (List<com.pcitc.fms.bll.entity.FactorySite>) res.result();
//						List<FactorySite> targets = ObjectConverter.listConverter(factorySiteList, FactorySite.class);
//						List<FactorySite> modelList = new ArrayList<>();
//						String linkUri = uri;
//						if(!linkUri.endsWith("factorySites")){
//							linkUri = uri.substring(0, uri.indexOf("factorySites"))+"factorySites";
//						}
//						for (FactorySite factorySite : targets) {
//							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<>();
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("equipments", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/equipments"), "设备集合"));
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("tees", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/tees"), "三通集合"));
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("tubulations", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/tubulations"), "管段集合"));
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("valves", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/valves"), "阀门集合"));
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("plates", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/plates"), "盲板集合"));
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("samplePoints", URI.create(linkUri+"/"+factorySite.getFactorySiteId()+"/samplePoints"), "采样点集合"));
//							
//							factorySite.setLinkObjs(links);
//							modelList.add(factorySite);
//						}
////						 collection = RestfulTool.buildCollection(modelList, hrefBase, Administration.class);
//						 collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, FactorySite.class);
//					} catch (Exception e) {
//						log.error("getFactorySites is Exception!");
//						collection = buildErrorCollection(routingContext, e);
//						returnCollection(routingContext, collection);
//					}
//					returnCollection(routingContext, collection);
//					log.info("getFactorySites is end!");
//				}
//			});
//		} catch (Exception e) {
//			log.error("getFactorySites is Exception!");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
//			returnCollection(routingContext, collection);
//		}
//
//	}
//	/**
//	 * 新增区域
//	 * @param routingContext
//	 */
//	@SuppressWarnings("unchecked")
//	public void addFactorySite(RoutingContext routingContext) {
//		Set<FileUpload> fileUploads = routingContext.fileUploads();
//		HttpServerRequest request = routingContext.request();
//		String hrefBase = request.uri();
//		String uri = request.uri();
//		//从url获取的参数
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
////		String factoryIdStr = request.getParam("factoryId")==null?null:request.getParam("factoryId").trim();
////		String orgIdStr = request.getParam("orgId")==null?null:request.getParam("orgId").trim();
//		String typeByUrl = CheckUtil.getUrlType(hrefBase).trim();
//		String creatorStr = request.getParam("creator");
//		//获取报文
//		String collectionParam = routingContext.getBodyAsString();
//		log.debug("*** FactorySiteHandler START addFactorySite ***");
//		// 将template转换成viewmodel
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				List<com.pcitc.fms.service.model.FactorySite> factorySiteModelList = new ArrayList<>();
//				try {
//					 //校验从url获取的参数,不为空字段
//				    CheckUtil.getParamForString("factoryCode", factoryCode, 36, false);
////				    Integer factoryId = CheckUtil.getParamForInteger("factoryId", factoryIdStr, 6, false);
////				    Integer orgId = CheckUtil.getParamForInteger("orgId", orgIdStr, 6, false);
//				    //校验姓名,用于excel上传,可以空字段
//				    String creator = CheckUtil.getParamForString("creator", creatorStr, 50, true);
//				    
//					//通过excel上传解析
//					if(null != fileUploads && fileUploads.size() > 0){
//						FileUpload file = (FileUpload) fileUploads.toArray()[0];
//						File excelFile = new File(file.uploadedFileName());
//				        ExcelHelper eh = ExcelHelper.readExcel(excelFile);
//				        factorySiteModelList = eh.toEntitys(FactorySite.class);
//				       
//					}else{
//						factorySiteModelList = RestfulTool.toResourceRep(collectionParam,
//								FactorySite.class);
//					}
//					com.pcitc.fms.service.model.FactorySite factorySite = factorySiteModelList.get(0);
//					//对工厂和区域url校验并返回factoryId
//					Integer factoryId = checkUrl(factoryCode,null);
//					//对区域类型和code做校验判断，并返回区域类型Id
//					Integer areaTypeId = checkAreaTypeId(typeByUrl,null);
//					//校验orgId是否存在
//					Integer orgId = checkOrgId(factorySite.getOrgId());
//					//获取的字段装入model实体
//					for (FactorySite model : factorySiteModelList) {
//						model.setFactoryId(factoryId);
//						model.setOrgId(orgId);
//						model.setAreaTypeId(areaTypeId);
//						model.setCreator(creator == null ? model.getCreator() : creator);
//					}
//					//校验传入的实体各个字段
//					factorySiteModelList = CurrencyCheck.checkObject(factorySiteModelList,Operation.ADD);
//					
//					//进入service层开始新增
//					List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList = factorySiteService.addFactorySites(factorySiteModelList);
//					factorySiteModelList = ObjectConverter.listConverter(factorySiteEntityList,FactorySite.class);
//					String collection = RestfulTool.buildCollection(factorySiteModelList,uri,FactorySite.class);
//					future.complete(collection);
//				} catch (BusinessException be) {
//					future.fail(be);
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;
//
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//				} else if (res.succeeded()) {
//					collection = res.result().toString();
//				}
//
//				// 输出结果
//				returnCollection(routingContext, collection);
//			});
//
//			log.debug("*** FactorySiteHandler END addFactorySite ***");
//
//		} catch (Exception e) {
//			log.error("*** FactorySiteHandler _ addFactorySite _ Exception " + e.getMessage() + " ***");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
//					request.uri());
//			returnCollection(routingContext, collection);
//		}
//	}
//	@SuppressWarnings("unchecked")
//	public void getFactorySiteByCode(RoutingContext routingContext) {
//		HttpServerRequest request = routingContext.request();
//		String uri = request.absoluteURI();
//		String hrefBase = request.uri();
//		//从url获取的参数
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
//		String codeStr = request.getParam("factorySiteCode")==null?null:request.getParam("factorySiteCode").trim(); 
//		String orgIdStr = request.getParam("orgId")==null?null:request.getParam("orgId");
//		String typeByUrl = CheckUtil.getUrlType(hrefBase).trim();
//		try {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(future -> {
//			try {
//				//校验从url获取的参数,不为空字段
//			    CheckUtil.getParamForString("factoryCode", factoryCode, 36, true);
//			    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
//			  //校验从url获取的参数,可以为空
//			    Integer orgId = CheckUtil.getParamForInteger("orgId", orgIdStr, 6, true);
//
//			    //对工厂和区域url校验并返回factoryId
//				Integer factoryId = checkUrl(factoryCode,code);
//				//对区域类型和code做校验判断，并返回区域类型Id
//				Integer areaTypeId = checkAreaTypeId(typeByUrl,code);
//			    //通过工厂id和code查询
//				com.pcitc.fms.bll.entity.FactorySite factorySiteEntity = factorySiteService.getByCode(code);
//				com.pcitc.fms.service.model.FactorySite factorySitModel = ObjectConverter.entityConverter(factorySiteEntity, com.pcitc.fms.service.model.FactorySite.class);
//				future.complete(factorySitModel);
//			} catch (Exception e) {
//				future.fail(e);
//			}
//		}, res -> {
//			String collection = null;// 返回结果字符串
//			if (res.failed()) {
//				collection = buildErrorCollection(routingContext, (Exception) res.cause());
//				returnCollection(routingContext, collection);
//			} else if (res.succeeded()) {
//				try {
//					com.pcitc.fms.service.model.FactorySite target = (com.pcitc.fms.service.model.FactorySite) res.result();
//					String linkUri = uri;
//					if(!linkUri.endsWith("factorySites")){
//						linkUri = uri.substring(0, uri.indexOf("administrations"))+"factories";
//					}
//					target.setHref("/" + target.getFactoryId());
//					List<String> tankAreaLinks = new ArrayList<>();
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/equipments");
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/tees");
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/tubulations");
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/valves");
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/plates");
//					tankAreaLinks.add(linkUri+"/"+factoryCode+"/administrations/"+target.getFactorySiteCode()+"/samplePoints");
//					List<com.pcitc.fms.service.model.FactorySite> targets = new ArrayList<>();
//					targets.add(target);
//					collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.FactorySite.class);
//				} catch (Exception e) {
//					log.error("getFactorySiteByCode is Exception!");
//					collection = buildErrorCollection(routingContext, e);
//					returnCollection(routingContext, collection);
//				}
//				returnCollection(routingContext, collection);
//				log.info("getFactorySiteByCode is end!");
//			}
//		});
//		} catch (Exception e) {
//			log.error("getFactorySiteByCode is Exception!");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
//			returnCollection(routingContext, collection);
//		}
//	}
//
//
//	@SuppressWarnings("unchecked")
//	public void updateFactorySite(RoutingContext routingContext) {
//		HttpServerRequest request = routingContext.request();
//		String hrefBase = request.uri();
//		//从url获取的参数
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
//		String codeStr = request.getParam("factorySiteCode")==null?null:request.getParam("factorySiteCode").trim();
//		String typeByUrl = CheckUtil.getUrlType(hrefBase).trim();
////		String factoryIdStr = request.getParam("factoryId")==null?null:request.getParam("factoryId").trim();
////		String orgIdStr = request.getParam("orgId")==null?null:request.getParam("orgId").trim();
//		//获取报文
//		String collectionParam = routingContext.getBodyAsString();
//		log.debug("*** FactorySiteHandler START updateFactorySite ***");
//		// 将template转换成viewmodel
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				try {
//					//校验从url获取的参数,不为空字段
//				    CheckUtil.getParamForString("factoryCode", factoryCode, 36, true);
//				    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
//				   //对工厂和区域url校验并返回factoryId
//					Integer factoryId = checkUrl(factoryCode,code);
//					//对区域类型和code做校验判断，并返回区域类型Id
//					Integer areaTypeId = checkAreaTypeId(typeByUrl,code);
//				    
//					List<FactorySite> factorySiteModelList = RestfulTool.toResourceRep(collectionParam,
//							FactorySite.class);
//					//将url获取的字段装入实体
//					for (FactorySite model : factorySiteModelList) {
//						model.setFactorySiteCode(code);
//					}
//					//校验实体字段合法性
//					factorySiteModelList = CurrencyCheck.checkObject(factorySiteModelList,Operation.UPDATE);
//            		//出入实体开始更新
//					factorySiteService.updateFactorySite(factorySiteModelList);
//					future.complete(hrefBase);
//				} catch (BusinessException be) {
//					future.fail(be);
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;
//
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//				} else if (res.succeeded()) {
//					collection = res.result().toString();
//				}
//
//				// 输出结果
//				returnCollection(routingContext, collection);
//			});
//
//			log.debug("*** FactorySiteHandler END updateFactorySite ***");
//
//		} catch (Exception e) {
//			log.error("*** FactorySiteHandler _ updateFactorySite _ Exception " + e.getMessage() + " ***");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
//					// BusinessExceptionInfo.Unknown.getMessage()),
//					request.uri());
//			returnCollection(routingContext, collection);
//		}		
//	}
//	
//	
//public void deleteFactorySite(RoutingContext routingContext) {
//		HttpServerRequest request = routingContext.request();
//		String hrefBase = request.uri();
//		//从url获取的参数
//		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
//		String codeStr = request.getParam("factorySiteCode")==null?null:request.getParam("factorySiteCode").trim();
//		String typeByUrl = CheckUtil.getUrlType(hrefBase).trim();
////		String factoryIdStr = request.getParam("factoryId")==null?null:request.getParam("factoryId").trim();
////		String orgIdStr = request.getParam("orgId")==null?null:request.getParam("orgId").trim();
//		log.info("*** FactorySiteHandler START deleteFactorySite ***");
//		try {
//			Vertx vertx = routingContext.vertx();
//			vertx.executeBlocking(future -> {
//				try {
//					//校验从url获取的参数,不为空字段
//				    CheckUtil.getParamForString("factoryCode", factoryCode, 36, true);
//				    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
//				    //对工厂和区域url校验并返回factoryId
//					Integer factoryId = checkUrl(factoryCode,code);
//					//对区域类型和code做校验判断，并返回区域类型Id
//					Integer areaTypeId = checkAreaTypeId(typeByUrl,code);
//				    //开始执行删除操作
//				    factorySiteService.deleteFactorySiteByCode(code);
//					
//					future.complete();
//				} catch (Exception e) {
//					future.fail(e);
//				}
//			}, res -> {
//				String collection = null;// 返回结果字符串
//				if (res.failed()) {
//					collection = buildErrorCollection(routingContext, (Exception) res.cause());
//					returnCollection(routingContext, collection);
//				} else if (res.succeeded()) {
//					collection = "";
//					returnCollection(routingContext, collection);
//					log.info("deleteFactorySite is end!");
//				}
//			});
//		} catch (Exception e) {
//			log.error("deleteFactorySite is Exception!");
//			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
//			returnCollection(routingContext, collection);
//		}
//	}
//
//
//private Integer checkOrgId(Integer orgId) throws BusinessException {
//	//对于orgId正确性查询
//	if(orgId != null) {
//		com.pcitc.fms.dal.pojo.TPmOrg org = tPmOrgDao.findOne(orgId);
//		if(org == null) {
//			throw new BusinessException("", "", "orgId:"+orgId+"不存在！");
//		}
//	}
//	return orgId;
//}
//private Integer checkAreaTypeId(String typeByUrl, String code) throws BusinessException {
//	List<com.pcitc.fms.bll.dictionary.entity.AreaType> areaTypes_entity = areaDictService.getAreaTypes(code);
//	Long areaTypeId = areaTypes_entity.get(0).getAreaTypeId();
//	if(code != null) {
//		//分区域code和factoryCode是否匹配验证
//		com.pcitc.fms.dal.pojo.AreaDictionary areaDictionary = areaDictionaryDao.getAreaDictionaryByAreaCodeAndAreaTypeId(code, areaTypeId);   
//		if(areaDictionary != null) {
//			throw new BusinessException("", "", "此区域下不存在code："+code);
//		}
//	}
//	return areaTypeId.intValue();
//}
//private Integer checkUrl(String factoryCode, String code) throws BusinessException {
//	com.pcitc.fms.bll.entity.FactorySite  factory = null;
//	try {
//		factory = factorySiteService.getByCode(factoryCode);
//	} catch (Exception e) {
//		log.error("fail",e);
//		throw  new BusinessException("","",e.getMessage());
//	}
//	if(factory == null) {
//		throw new BusinessException("", "", "facoryCode:"+factoryCode+"不存在！");
//	}
//	Integer factoryId = factory.getFactoryId();
//	if(code != null) {
//		//分区域code和factoryCode是否匹配验证
//		com.pcitc.fms.bll.entity.FactorySite factorySite = null;
//		try {
//			factorySite = factorySiteService.getByCode(factoryId,code);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//		if(factorySite != null) {
//			throw new BusinessException("", "", "facoryCode:"+factoryCode+"下不存在code："+code);
//		}
//	}
//	return factoryId;

	
}
