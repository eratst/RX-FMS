package com.pcitc.fms.service.handler;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.Factory;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.FactoryModelStr;
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
 * 工厂handler
 * @author he.yang
 * @author yan.yang
 *
 */
@Controller
public class FactoryHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(FactoryHandler.class);
	@Autowired
	private FactoryService factoryService;

	@SuppressWarnings("unchecked")
	public void getFactories(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String urlType = CheckUtil.getUriEndType(hrefBase);
		String uri = "/FactoryModelService/factories";
		log.info("*** FactoryHandler START getFactories ***");

		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		//分页
		Sort sort = new Sort(Sort.Direction.ASC, "factoryId");
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Pagination pagination = new Pagination();

		// 获取参数,按名称，简称，业务类型，启用标识条件查询
		String name = request.getParam("Name") == null ? null : request.getParam("Name").trim();
		String shortName = request.getParam("ShortName") == null ? null : request.getParam("ShortName").trim();
		String businessType = request.getParam("BusinessType") == null ? null : request.getParam("BusinessType").trim();
		String factoryId = request.getParam("factoryId") == null ? null : request.getParam("factoryId").trim();
		String orgUnitRootCode = request.getParam("$orgUnitRootCode")==null?null:request.getParam("$orgUnitRootCode").trim();
		
		String type = request.getParam("$orgType")==null?"orgUnitMeta":request.getParam("$orgType").trim();
		
		String enabled = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<Factory> factoryEntity = new ArrayList<>();
				FactoryModelStr modelStr= new FactoryModelStr();
				try {
					// 从数据库中查询factories
//					Integer factoryIds=null;
				/*	if(checkId(factoryId)){
						factoryIds=Integer.parseInt(factoryId);
					}*/
					CheckUtil.checkInput(topStr,skipStr);
					Integer skip = skipStr == null||"".equals(skipStr) ? 0 : Integer.parseInt(skipStr);
					Integer top = null;
					Pageable pageable = null;
					
					if(topStr != null && !"".equals(topStr) && skip != null)
					{
						top = Integer.parseInt(topStr);
						
//						top = CheckUtil.validateNegativeIntegerFormat("top", topStr);
//						skip = CheckUtil.validateNegativeIntegerFormat("skip", skipStr);
						pageable = PageUtil.pageable(top, skip, sort);
//						Pager<com.pcitc.fms.bll.entity.Factory> sourceData = factoryService.getPageFactories(modelStr, pageable);
//						PageUtil.mergePage(pagination, sourceData);
//						factoryEntity  = sourceData.getContent();
					}
					modelStr.setTop(top);
					modelStr.setSkip(skip);
					Integer enabledInt = null;
					if(null != enabled && !"".equals(enabled))
					enabledInt = CheckUtil.enabled("enabled", enabled);
					
					
					//判断查询条件
					if(codes!=null){
						List<String> codeList = CheckUtil.checkCodeList(codes);
						modelStr.setCodeList(codeList);
					}else if(ids!=null){
						List<Integer> idList = CheckUtil.buildStringToListInteger("$idList", ids);
						modelStr.setIdList(idList);
					}else if(name!=null||shortName!=null||businessType!=null||enabled!=null){
						modelStr.setName(name);
						modelStr.setShortName(shortName);
						modelStr.setBusinessType(businessType);
						modelStr.setEnabled(enabled);
					}
					
					//查询数据
//					com.pcitc.fms.service.model.Factory factoryModel = new com.pcitc.fms.service.model.Factory(factorieIdInt,name, shortName,  businessType, enabledInt, top, skip);
					Pager<com.pcitc.fms.bll.entity.Factory> sourceData = factoryService.getFactories(modelStr, pageable);
					PageUtil.mergePage(pagination, sourceData);
					factoryEntity  = sourceData.getContent();
					if(orgUnitRootCode!=null){
						factoryEntity = factoryService.getFactoryByOrgCode(orgUnitRootCode,type,urlType);
					}
					future.complete(factoryEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Factory> targets = new ArrayList<>();
					try {
						List<Factory> factoryList = (List<Factory>) res.result();
						for (Factory factory : factoryList) {
							com.pcitc.fms.service.model.Factory target = ObjectConverter.entityConverter(factory, com.pcitc.fms.service.model.Factory.class);
							target.setHref("/" + target.getCode());
							List<String> factoryLinks = new ArrayList<>();
							factoryLinks.add(uri+"/"+target.getCode()+"/loadingDocks");
							factoryLinks.add(uri+"/"+target.getCode()+"/tankAreas");
							factoryLinks.add(uri+"/"+target.getCode()+"/pipeNetworks");
							factoryLinks.add(uri+"/"+target.getCode()+"/plants");
							factoryLinks.add(uri+"/"+target.getCode()+"/warehouses");
							factoryLinks.add(uri+"/"+target.getCode()+"/administrations");
							factoryLinks.add(uri+"/"+target.getCode()+"/communities");
							factoryLinks.add(uri+"/"+target.getCode()+"/connections");
							factoryLinks.add(uri+"/"+target.getCode()+"/nodeAndArea");
							factoryLinks.add(uri+"/"+target.getCode()+"/orgRelations");
							target.setLinks(factoryLinks);
							targets.add(target);
						}
						
						collection = RestfulTool.buildCollection(targets,pagination, hrefBase, com.pcitc.fms.service.model.Factory.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getFactories is end!");
				}
			});
		} catch (Exception e) {
			log.error("getFactories is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	public void getFactory(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = "/FactoryModelService/factories";
		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
		log.info("*** FactoryHandler START getFactory ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<Factory> factoryEntity = null;
				try {
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					factoryEntity = factoryService.getFactoryByCode(factoryCode);
					
					future.complete(factoryEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Factory> targets = new ArrayList<>();
					try {
						List<Factory> eUnitList = (List<Factory>) res.result();
						for (Factory source : eUnitList) {
							com.pcitc.fms.service.model.Factory target = ObjectConverter.entityConverter(source, com.pcitc.fms.service.model.Factory.class);
							target.setHref("/" + target.getCode());
							List<String> factoryLinks = new ArrayList<>();
							factoryLinks.add(uri+"/"+target.getCode()+"/loadingDocks");
							factoryLinks.add(uri+"/"+target.getCode()+"/tankAreas");
							factoryLinks.add(uri+"/"+target.getCode()+"/pipeNetworks");
							factoryLinks.add(uri+"/"+target.getCode()+"/plants");
							factoryLinks.add(uri+"/"+target.getCode()+"/warehouses");
							factoryLinks.add(uri+"/"+target.getCode()+"/administrations");
							factoryLinks.add(uri+"/"+target.getCode()+"/communities");
							factoryLinks.add(uri+"/"+target.getCode()+"/connections");
							factoryLinks.add(uri+"/"+target.getCode()+"/nodeAndArea");
							factoryLinks.add(uri+"/"+target.getCode()+"/orgRelations");
							target.setLinks(factoryLinks);
							targets.add(target);
						}
						collection = RestfulTool.buildCollection(targets, "/FactoryModelService/Factories", com.pcitc.fms.service.model.Factory.class);
					} catch (Exception e) {
						log.error("getFactory is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getFactory is end!");
				}
			});
		} catch (Exception e) {
			log.error("getFactory is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	
	@SuppressWarnings("unchecked")
	public void addFactories(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String orgUnitRootCode = request.getParam("$orgUnitRootCode")== null ? null : request.getParam("$orgUnitRootCode").trim();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		String collectionParam = routingContext.getBodyAsString();
		String creator = request.getParam("creator");
		String href = request.uri();
		String urlType = CheckUtil.getUriEndType(href);
		log.debug("*** FactoryHandler START addFactory ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.bll.entity.Factory> factoryEntityList = new ArrayList<>();
					List<com.pcitc.fms.service.model.Factory> factoryModelList = new ArrayList<>();
					List<com.pcitc.fms.service.model.Factory> factoryModelStrListCopy = new ArrayList<>();
					List<com.pcitc.fms.service.model.Factory> factoryModelStrList = new ArrayList<>();
					if(fileUploads!=null&&fileUploads.size()>0){
						FileUpload file = (FileUpload)fileUploads.toArray()[0];
						if(file != null && file.size()>0){
							String name = file.uploadedFileName();
							File fileIo = new File(name);
							ExcelHelper em = ExcelHelper.readExcel(fileIo);
							factoryEntityList = em.toEntitys(com.pcitc.fms.bll.entity.Factory.class);
							factoryModelStrListCopy=ObjectConverter.listConverter(factoryEntityList, com.pcitc.fms.service.model.Factory.class);
							for(com.pcitc.fms.service.model.Factory factory : factoryModelStrListCopy){
								String code = factory.getCode();
								if(code!=null && code.trim()!="" && !(code.isEmpty())){
									factory.setCreator(creator);
								   factoryModelStrList.add(factory);
								}
									
							}
						}
                    }else{
						factoryModelStrList = RestfulTool.toResourceRep(collectionParam,com.pcitc.fms.service.model.Factory.class);						
						// 验证
						for (com.pcitc.fms.service.model.Factory factoryModelStr : factoryModelStrList) {
//							com.pcitc.fms.service.model.Factory factoryModel = addCheckFactory(factoryModelStr);
//							factoryModelList.add(factoryModel);
							factoryModelStr.setCreateTime(new Date());
							factoryModelStr.setCreator(creator == null ? factoryModelStr.getCreator() : creator);
						}
						// 将viewmodel转换成entity
					}
					factoryModelStrList=CurrencyCheck.checkObject(factoryModelStrList,Operation.ADD);
					// 调用全局变量alertPointService的add方法添加addAlertPoint数据
					if(orgUnitRootCode != null){
						factoryEntityList = factoryService.addFactories(ObjectConverter.listConverter(factoryModelStrList,Factory.class),orgUnitRootCode, urlType);
					}
					else {
						factoryEntityList = factoryService.addFactories(ObjectConverter.listConverter(factoryModelStrList,Factory.class));
					}
//					factoryEntityList = factoryService.addFactories(ObjectConverter.listConverter(factoryModelStrList,Factory.class));
					factoryModelList = ObjectConverter.listConverter(factoryEntityList, com.pcitc.fms.service.model.Factory.class);
					String collectionJson=RestfulTool.buildCollection(factoryModelList, href, com.pcitc.fms.service.model.Factory.class);
					future.complete(collectionJson);
				} catch (BusinessException be) {
					future.fail(be);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;

				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				} else if (res.succeeded()) {
					collection = res.result().toString();
					/*try {
						List<com.pcitc.fms.bll.entity.Factory> entityList = (List<com.pcitc.fms.bll.entity.Factory>) res.result();
						List modelList = ObjectConverter.listConverter(entityList,com.pcitc.fms.service.model.Factory.class);
						collection = RestfulTool.buildCollection(modelList);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}*/
				}
				// 输出结果
				returnCollection(routingContext, collection);
			});

			log.debug("*** FactoryHandler END addFactory ***");

		} catch (Exception e) {
			log.error("*** FactoryHandler _ addFactory _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					// BusinessExceptionInfo.Unknown.getMessage()),
					request.uri());
			returnCollection(routingContext, collection);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateFactory(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String collectionParam = routingContext.getBodyAsString();
		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
		log.debug("*** FactoryHandler START updateFactory ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.service.model.Factory> factoryModelStrList = RestfulTool.toResourceRep(collectionParam,com.pcitc.fms.service.model.Factory.class);
					// 验证
					CheckUtil.validateCodeException("factoryCode", factoryCode);
//					Integer factoryIdInt = CheckUtil.strToInt("factoryId", factoryId);
					List<Factory> factoryByCode = factoryService.getFactoryByCode(factoryCode);
					int id = 0;
					if(null != factoryByCode){
						id = factoryByCode.get(0).getFactoryId();
					}
					List<com.pcitc.fms.service.model.Factory> factoryModelList = new ArrayList<>();
					for (com.pcitc.fms.service.model.Factory factoryModelStr : factoryModelStrList) {
//						com.pcitc.fms.service.model.Factory factoryModel = updateCheckFactory(factoryModelStr);
						factoryModelStr.setCode(factoryCode);
						factoryModelStr.setFactoryId(id);
						factoryModelStr.setMaintainTime(new Date());
						factoryModelList.add(factoryModelStr);
					}
					factoryModelStrList=CurrencyCheck.checkObject(factoryModelStrList,Operation.UPDATE);
					List<Factory> factoryEntityList = ObjectConverter.listConverter(factoryModelStrList,Factory.class);
					// 调用全局变量alertPointService的add方法添加addAlertPoint数据
					factoryService.updateFactory(factoryEntityList);

					future.complete(hrefBase);
				}catch (BusinessException be) {
					future.fail(be);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;

				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				} else if (res.succeeded()) {
					collection = res.result().toString();
				}

				// 输出结果
				returnCollection(routingContext, collection);
			});

			log.debug("*** FactoryHandler END updateFactory ***");

		} catch (Exception e) {
			log.error("*** FactoryHandler _ updateFactory _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					// BusinessExceptionInfo.Unknown.getMessage()),
					request.uri());
			returnCollection(routingContext, collection);
		}		
	}
	
	public void deleteFactory(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String factoryCode = request.getParam("factoryCode")==null?null:request.getParam("factoryCode").trim();
		String urlType = CheckUtil.getUriEndType(hrefBase, factoryCode);
		log.info("*** FactoryHandler START deleteFactory ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<Factory> factoryEntity = null;
				try {
					CheckUtil.validateCodeException("factoryCode", factoryCode);
					 factoryService.deleteByFactoryCode(factoryCode,urlType);
					future.complete();
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					//外键删除抛出异常
					if(res.cause().getMessage().contains("_REFERENCE_FACTORY"))
						collection = RestfulTool.buildCollection(new ErrorInfo("", "", "已找到子记录，无法删除！"), hrefBase);
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					collection = "";
					returnCollection(routingContext, collection);
					log.info("deleteFactory is end!");
				}
			});
		} catch (Exception e) {
			log.error("deleteFactory is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}
	
	private boolean checkId(String id) throws BusinessException{
		// TODO Auto-generated method stub
//		return false;
		 if(CheckUtil.checkStringIsNotNull(id, 50)){
	            throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.ID_NULL);    
	        }
	        if((id != null) && (!CheckUtil.validateSortNum(id))){
	            throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.ID_NOT);    
	        }
	        return true;
	}

	private com.pcitc.fms.service.model.Factory addCheckFactory(FactoryModelStr factoryModelStr) throws BusinessException {
		com.pcitc.fms.service.model.Factory factoryModel = new com.pcitc.fms.service.model.Factory();
		String code = factoryModelStr.getCode();
		String name = factoryModelStr.getName();
		String shortName = factoryModelStr.getShortName();
		String businessType = factoryModelStr.getBusinessType();
		String enabled = factoryModelStr.getEnabled();
		String creator = factoryModelStr.getCreator();
		String createTime = factoryModelStr.getCreateTime();
		String des = factoryModelStr.getDes();
		if (CheckUtil.checkCodeIsNull(code)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CODE_NULL);
		}
		if (CheckUtil.checkNameIsNull(name)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkNameIsNull(shortName)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.SHORTNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(businessType)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.BUSINESSTYPE_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(creator)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CREATOR_NULL);
		}
		if (CheckUtil.checkStringIsNull(enabled)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.ENABLED_NULL);
		}
		if (!CheckUtil.checkMatcher(code).find()) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CODE_NOT);
		}
		if (CheckUtil.characterFilter(name)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.NAME_NOT);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.SHORTNAME_NOT);
		}
		if (CheckUtil.checkStringIsNull(businessType)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.BUSINESSTYPE_NOT);
		}
		if (CheckUtil.checkCrtMntNameIsNull(creator)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CREATOR_NOT);
		}
		if (!CheckUtil.checkStringIsNull(createTime)&&!CheckUtil.checkIsDateTime(createTime)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CREATETIME_NOT);
		}
		if(!CheckUtil.checkStringIsNull(createTime)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			Date date = new Date();
			try {
				date = format.parse(createTime.trim());
			} catch (ParseException e) {
			log.error(e.getMessage());
			}
			factoryModel.setCreateTime(date);
			factoryModel.setMaintainTime(date);
		}
		factoryModel.setCode(code);
		factoryModel.setName(name);
		factoryModel.setShortName(shortName);
		factoryModel.setBusinessType(Integer.valueOf(businessType));
		factoryModel.setEnabled(Integer.valueOf(enabled));
		factoryModel.setCreator(creator);
		factoryModel.setEditor(creator);
		factoryModel.setDes(des);
		return factoryModel;
	}

	
	// getFactories --- 把codes字符串集合转换成为List
 
	
	// getFactories --- 把ids字符串集合转换成为List
	private List<Integer> checkIdList(String ids) throws BusinessException {
		List<Integer> idList = new ArrayList<>();
		if (ids != null) {
			String[] arrIds = ids.split(",");
			for (String id : arrIds) {
				if (!CheckUtil.checkStringIsNotNull(id)) {
					continue;
				}
				if (!CheckUtil.checkDigit(id)) {
					throw new BusinessException(CheckError.FACTORY_CHECK, "", "id只能为大于0的数字");
				}
				
				idList.add(Integer.valueOf(id.trim()));
			}
		}
		return idList;
	}
	
	private com.pcitc.fms.service.model.Factory updateCheckFactory(com.pcitc.fms.service.model.Factory factoryModelStr) throws BusinessException {
		com.pcitc.fms.service.model.Factory factoryModel = new com.pcitc.fms.service.model.Factory();
		String code = factoryModelStr.getCode();
		String name = factoryModelStr.getName();
		String shortName = factoryModelStr.getShortName();
		String businessType = String.valueOf(factoryModelStr.getBusinessType());
		String editor = factoryModelStr.getEditor();
        Integer enabled=factoryModelStr.getEnabled();
		if (CheckUtil.checkCodeIsNull(code)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CODE_NULL);
		}
		if (CheckUtil.checkNameIsNull(name)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(businessType)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.BUSINESSTYPE_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(editor)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CREATOR_NULL);
		}
		if (!CheckUtil.checkMatcher(code).find()) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CODE_NOT);
		}
		if (CheckUtil.characterFilter(name)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.NAME_NOT);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.SHORTNAME_NOT);
		}
		if (CheckUtil.checkStringIsNull(businessType)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.BUSINESSTYPE_NOT);
		}
		if (CheckUtil.checkCrtMntNameIsNull(editor)) {
			throw new BusinessException(CheckError.FACTORY_CHECK, "", CheckError.CREATOR_NOT);
		}
	/*	if(!CheckUtil.checkStringIsNull(maintainTime)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			Date date = new Date();
			try {
				date = format.parse(maintainTime.trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			factoryModel.setMaintainTime(date);
		}*/
		factoryModel.setCode(code);
		factoryModel.setName(name);
		factoryModel.setShortName(shortName);
		factoryModel.setBusinessType(Integer.valueOf(businessType));
		factoryModel.setEnabled(Integer.valueOf(enabled));
		factoryModel.setEditor(editor);

		return factoryModel;
	}
	private List<String> checkCodeList(String codes) {
		List<String> codeList = new ArrayList<>();
		if (codes != null) {
			String[] arrCodes = codes.split(",");
			for (String code : arrCodes) {
				if (CheckUtil.checkStringIsNotNull(code)) {
					codeList.add(code.trim());
				}
			}
		}
		return codeList;
	}
}
