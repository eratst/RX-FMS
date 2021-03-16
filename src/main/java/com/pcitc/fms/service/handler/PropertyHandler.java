package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.EntityService;
import com.pcitc.fms.bll.itf.PropertyService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.EntityDao;
import com.pcitc.fms.dal.pojo.EntityMeta;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PropertyMeta;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/**
 * 元元模型 ---属性 
 * @author XBH
 *
 */
@Controller
public class PropertyHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(PropertyHandler.class);
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private EntityDao entityDao;
	@Autowired
	private EntityService entityService;

	@SuppressWarnings("unchecked")
	public void getProperties(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		String propertyCode = request.getParam("propertyCode") == null ? null : request.getParam("propertyCode").trim();
		
		log.info("*** PropertyHandler START getProperties ***");
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "dictionaryTableId");
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntity = null;
				try {
					String propertyName = request.getParam("propertyName")==null?null:checkField(request.getParam("propertyName").trim(),"propertyName");
					String dataType = request.getParam("dataType")==null?null:checkField(request.getParam("dataType").trim(),"dataType");
					String shortName = request.getParam("shortName")==null?null:checkField(request.getParam("shortName").trim(),"shortName");
					// 从数据库中查询entities
					List<String> codeList=CheckUtil.buildStringToListString("$codeList", codes);
					//List<String> codeList = CheckUtil.checkCodeList(codes);
					List<Integer> idList = CheckUtil.buildStringToListInteger("$idList",ids);
					CheckUtil.checkInput(topStr, skipStr);
					int top = 0;
					int skip = 0;
					int enabledInt;
					boolean checkInput = CheckUtil.checkInput(topStr,skipStr);
					if (null != topStr&&!topStr.equals("")) {
						top = Integer.parseInt(topStr);
					}
					if (null != skipStr&&!skipStr.equals("")) {
						skip = Integer.parseInt(skipStr);
					}
					CheckUtil.validateCodeException("entityCode", entityCode);
					CheckUtil.validateCodeException("propertyCode", propertyCode);
					com.pcitc.fms.service.model.PropertyMeta propertyModel = new com.pcitc.fms.service.model.PropertyMeta(
							propertyCode, propertyName, shortName, dataType, entityCode, codeList, idList, top, skip);
					//判断 是否分页
					Pager<com.pcitc.fms.bll.entity.PropertyMeta> sourceData;
					if(null != topStr&&!topStr.equals("") && null != skipStr && checkInput){
						 propertyModel = new com.pcitc.fms.service.model.PropertyMeta(
								propertyCode, propertyName, shortName, dataType, entityCode, codeList, idList, top, skip);
						Pageable pageable = PageUtil.pageable(top, skip, sort);
						sourceData =  propertyService.getProperties(propertyModel, pageable);
						PageUtil.mergePage(pagination, sourceData);
					}else{
						sourceData =  propertyService.getProperties(propertyModel, null);
					}
					propertyEntity=sourceData.getContent();
					future.complete(propertyEntity);
					
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
						List<com.pcitc.fms.bll.entity.PropertyMeta> propertyList = (List<com.pcitc.fms.bll.entity.PropertyMeta>) res
								.result();
						List<com.pcitc.fms.service.model.PropertyMeta> targets = ObjectConverter.listConverter(propertyList, com.pcitc.fms.service.model.PropertyMeta.class);
//						List<com.pcitc.fms.service.model.PropertyMeta> modelList = new ArrayList<>();
//						String linkUri = uri;
//						for (com.pcitc.fms.service.model.PropertyMeta PropertyMeta : targets) {
//							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<>();
//							links.add(new pcitc.imp.common.ettool.baseresrep.Link("properties", URI.create(linkUri+"/"+PropertyMeta.getPropertyCode()+"/properties"), "属性集合"));
//							
//							PropertyMeta.setLinkObjs(links);
//							modelList.add(PropertyMeta);
//						}
//						 collection = RestfulTool.buildCollection(targets, pagination, hrefBase, com.pcitc.fms.service.model.PropertyMeta.class);
//						collection = RestfulTool.buildCollection(targets, hrefBase,	com.pcitc.fms.service.model.PropertyMeta.class);
							collection = RestfulTool.buildCollection(targets, (pagination==null||pagination.getRecordCount()==null)?null:pagination,uri, com.pcitc.fms.service.model.PropertyMeta.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getUints is end!");
				}
			});
		} catch (Exception e) {
			log.error("getUnits is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	@SuppressWarnings("unchecked")
	public void addProperty(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		String collectionParam = routingContext.getBodyAsString();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		log.debug("*** PropertyHandler START addProperty ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
				    CheckUtil.validateCodeException("entityCode", entityCode);
					List<com.pcitc.fms.service.model.PropertyMeta> propertyModelList = new ArrayList<>();
					List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntityList = new ArrayList<>();
						List<com.pcitc.fms.service.model.PropertyMeta> newPropertyMetaModelList = RestfulTool
								.toResourceRep(collectionParam, com.pcitc.fms.service.model.PropertyMeta.class);
						// 验证
						for (PropertyMeta propertyModel : newPropertyMetaModelList) {
							// com.pcitc.fms.service.model.PropertyMeta
							// newPropertyMetaModel = addCheckProperty(
							// propertyModel);
							propertyModel.setCreateTime(new Date());
							propertyModel.setEntityCode(entityCode);
							propertyModelList.add(propertyModel);
					}
					// 将viewmodel转换成entity
						propertyModelList =	CurrencyCheck.checkObject(propertyModelList,Operation.ADD );//校验方法
					propertyEntityList = ObjectConverter.listConverter(propertyModelList,
							com.pcitc.fms.service.model.PropertyMeta.class);
					List str = propertyService.addProperties(propertyEntityList,entityCode);

					future.complete(str);
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
					//collection = res.result().toString();
					try {
						List<com.pcitc.fms.service.model.PropertyMeta> entityList = (List<com.pcitc.fms.service.model.PropertyMeta>) res.result();
						List modelList = ObjectConverter.listConverter(entityList,PropertyMeta.class);
						collection = RestfulTool.buildCollection(modelList,request.uri(),PropertyMeta.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
				}

				// 输出结果
				returnCollection(routingContext, collection);
			});

			log.debug("*** PropertyHandler END addProperty ***");

		} catch (Exception e) {
			log.error("*** PropertyHandler _ addProperty _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}
	}

	@SuppressWarnings("unchecked")
	public void getProperty(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String propertyCode = request.getParam("propertyCode") == null ? null : request.getParam("propertyCode").trim();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		log.info("*** PropertyHandler START getProperty ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntity = null;
				try {
					CheckUtil.validateCodeException("propertyCode", propertyCode);
					CheckUtil.validateCodeException("entityCode", entityCode);
					propertyEntity = propertyService.getProperty(propertyCode,entityCode);

					future.complete(propertyEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.PropertyMeta> targets = new ArrayList<>();
					try {
						List<com.pcitc.fms.bll.entity.PropertyMeta> eUnitList = (List<com.pcitc.fms.bll.entity.PropertyMeta>) res
								.result();
						for (com.pcitc.fms.bll.entity.PropertyMeta source : eUnitList) {
							PropertyMeta target = ObjectConverter.entityConverter(source,
									com.pcitc.fms.service.model.PropertyMeta.class);
							// target.setHref("/" +
							// target.getDictionaryTableId());
							List<String> propertyLinks = new ArrayList<>();
							// dictionaryTableLinks.add(hrefBase+"/"+target.getDictionaryTableId());
							target.setLinks(propertyLinks);
							targets.add(target);
						}
						collection = RestfulTool.buildCollection(targets, hrefBase, PropertyMeta.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);

				}
				returnCollection(routingContext, collection);
				log.info("getProperty is end!");
			});
		} catch (Exception e) {
			log.error("getProperty is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateProperty(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		//String entityCode = CheckUtil.trim(request.getParam("entityCode"));
		//String propertyCode = CheckUtil.trim(request.getParam("propertyCode"));
		//int propertyCode = Integer.parseInt(propertyCode);
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		String propertyCode = request.getParam("propertyCode") == null ? null : request.getParam("propertyCode").trim();
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** PropertyHandler START updateProperty ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.service.model.PropertyMeta> propertyModelList = RestfulTool
							.toResourceRep(collectionParam, com.pcitc.fms.service.model.PropertyMeta.class);
					// 验证
					CheckUtil.validateCodeException("propertyCode", propertyCode);
					CheckUtil.validateCodeException("entityCode", entityCode);
					List<com.pcitc.fms.service.model.PropertyMeta> newModelList = new ArrayList<>();
					for (com.pcitc.fms.service.model.PropertyMeta propertyModel : propertyModelList) {
						propertyModel.setPropertyCode(propertyCode);
						propertyModel.setEntityCode(entityCode);
						newModelList.add(propertyModel);
					}
					// 将viewmodel转换成entity
					CurrencyCheck.checkObject(propertyModelList,Operation.UPDATE);//校验方法
					List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntityList = ObjectConverter
							.listConverter(propertyModelList, com.pcitc.fms.service.model.PropertyMeta.class);
					if (null != propertyEntityList && propertyEntityList.size() > 0) {
						propertyService.updateProperty(propertyEntityList);
					}
					future.complete(hrefBase);
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
				}

				// 输出结果
				returnCollection(routingContext, collection);
			});

			log.debug("*** PropertyHandler END updateProperty ***");

		} catch (Exception e) {
			log.error("*** PropertyHandler _ updateProperty _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}
	}

	public void deleteProperty(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String propertyCode = request.getParam("propertyCode") == null ? null : request.getParam("propertyCode").trim();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		log.info("*** PropertyHandler START deleteProperty ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					// 验证
					CheckUtil.validateCodeException("propertyCode", propertyCode);
					CheckUtil.validateCodeException("entityCode", entityCode);

					List<com.pcitc.fms.bll.entity.EntityMeta> entid = entityService.getEntityByCode(entityCode);
					if(entid.size() == 0) {
						throw new BusiException("", "entityCode"+"不存在的Code"+":"+entityCode);
				
					}
					List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntity = propertyService.getProperty(propertyCode);
					if(propertyEntity.size() == 0) {
						throw new BusiException("", "propertyCode"+"不存在的Code"+":"+propertyCode);
					}
					List<com.pcitc.fms.bll.entity.PropertyMeta> dscs = propertyService.getPropertyByEntityAndPropertyCode(propertyCode,entityCode);
					if(dscs.size()>0){
						propertyService.deletePropertyByCode(propertyCode);
					}else{
						throw new BusiException("", "property"+"不匹配的"+":"+propertyCode+"与"+entityCode);
					}
					
					future.complete();
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					collection = "";
					returnCollection(routingContext, collection);
					log.info("deleteProperty is end!");
				}
			});
		} catch (Exception e) {
			log.error("deleteProperty is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	private void checkProperty(PropertyMeta propertyModel) throws BusinessException {
		String entityCode = propertyModel.getEntityCode();
		String dataType = propertyModel.getDataType();
		String propertyName = propertyModel.getPropertyName();
		String creator = propertyModel.getCreator();
		String createTime = propertyModel.getCreateTime().toString();
		
	}

	private void addCheckProperty(com.pcitc.fms.service.model.PropertyMeta propertyModel) throws BusinessException {

		String entityCode = propertyModel.getEntityCode().toString();
		String propertyCode = propertyModel.getPropertyCode();
		String propertyName = propertyModel.getPropertyName();
		String dataType = propertyModel.getDataType();
		String creator = propertyModel.getCreator();
		propertyCode = propertyModel.getPropertyCode();
//		String createTime = propertyModel.getCreateTime().toString();
		if (CheckUtil.checkStringIsNull(entityCode)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.ENTITYID_NULL);
		}
		if (CheckUtil.checkStringIsNull(propertyCode)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.PROPERTYCODE_NULL);
		}
		if (CheckUtil.checkStringIsNull(propertyName)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.PROPERTYNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(dataType)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.DATATYPE_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(creator)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.CREATOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(createTime) && CheckUtil.checkIsDateTime(createTime)) {
//			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.CREATETIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(createTime)) {
//			Date date = new Date(createTime);
//			propertyModel.setCreateTime(date);
//			propertyModel.setMaintainTime(date);
//		}
	}

	/**
	 * 
	 * @param propertyModel
	 * @return
	 * @throws BusinessException
	 */
	private void updateCheckEntity(com.pcitc.fms.service.model.PropertyMeta propertyModel) throws BusinessException {

		String entityCode = propertyModel.getEntityCode().toString();
		String propertyCode = propertyModel.getPropertyCode();
		String propertyName = propertyModel.getPropertyName();
		String dataType = propertyModel.getDataType();
		String editor = propertyModel.getEditor();
//		String maintainTime = propertyModel.getMaintainTime().toString();
		if (CheckUtil.checkStringIsNull(entityCode)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.ENTITYID_NULL);
		}
		if (CheckUtil.checkStringIsNull(propertyCode)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.PROPERTYCODE_NULL);
		}
		if (CheckUtil.checkStringIsNull(propertyName)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.PROPERTYNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(dataType)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.DATATYPE_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(editor)) {
			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.EDITOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(maintainTime) && CheckUtil.checkIsDateTime(maintainTime)) {
//			throw new BusinessException(CheckError.PROPERTY_CHECK, "", CheckError.MAINTAINTIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(maintainTime)) {
//			Date date = new Date(maintainTime);
//			propertyModel.setCreateTime(date);
//			propertyModel.setMaintainTime(date);
//		}
	}
	private String checkField(String field,String name) throws BusinessException{
	    if(field != null && !"".equals(field)){   
            if (!CheckUtil.checkStringMatchers(field).find()) {
            throw new BusinessException(CheckError.TUBULATION_CHECK, "", name+"只能由数字字母下划线组成");
            }
            if (CheckUtil.checkCodeIsNull(field)) {
                throw new BusinessException(CheckError.TUBULATION_CHECK, "", name+"不能为空,长度不能大于50");
            }
	    }
        return field.trim();
}
}
