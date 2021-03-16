package com.pcitc.fms.service.handler;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.EntityMeta;
import com.pcitc.fms.bll.itf.EntityService;
import com.pcitc.fms.bll.itf.PropertyService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.ExcelUtils;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.PropertyDao;
import com.pcitc.fms.dal.pojo.PropertyMeta;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
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
 * 
 * @author sun-shunan
 *
 */
@Controller
public class EntityHandler extends BaseHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(EntityHandler.class);
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private EntityService entityService;

	/**
	 * 获取全部数据集合
	 * 
	 * @param routingContext
	 */
	@SuppressWarnings({ "unchecked" })
	public void getEntities(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String hrefBase = request.uri();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		LOGGER.info("*** EntityHandler START getEntities ***");
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "entityCode");
		
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.EntityMeta> Entity = null;
				try {
					String entityName = request.getParam("entityName")==null?null:checkField(request.getParam("entityName").trim(),"entityName");
					String entityType = request.getParam("entityType")==null?null:checkField(request.getParam("entityType").trim(),"entityType");
					String entityTableName = request.getParam("entityTableName")==null?null:checkField(request.getParam("entityTableName").trim(),"entityTableName");
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
//					List<String> codeList = CheckUtil.checkCodeList(codes);
					List<String> codeList=CheckUtil.buildStringToListString("$codeList", codes);
					com.pcitc.fms.service.model.EntityMeta findEntities = new com.pcitc.fms.service.model.EntityMeta(idList, codeList, entityCode, entityName, entityType, entityTableName, top, skip);
					
					Pager<com.pcitc.fms.bll.entity.EntityMeta> sourceData = null;
					if(null != topStr &&!topStr.equals("")&& null != skipStr&&checkInput){
						Pageable pageable = PageUtil.pageable(top, skip, sort);
						sourceData = entityService.getEntities(findEntities,pageable);
						PageUtil.mergePage(pagination, sourceData);
					}else{
						sourceData = entityService.getEntities(findEntities,null);
					}
					Entity=sourceData.getContent();
					future.complete(Entity);
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
						List<com.pcitc.fms.bll.entity.EntityMeta> entityList = (List<com.pcitc.fms.bll.entity.EntityMeta>) res.result();
						List<com.pcitc.fms.service.model.EntityMeta> targets = ObjectConverter.listConverter(entityList,com.pcitc.fms.service.model.EntityMeta.class);
						List<com.pcitc.fms.service.model.EntityMeta> modelList = new ArrayList<>();
						String linkUri = uri;
						if(!linkUri.endsWith("entities")){
							linkUri = uri.substring(0, uri.indexOf("entities"))+"properties";
						}
						for (com.pcitc.fms.service.model.EntityMeta entityMeta : targets) {
							List<pcitc.imp.common.ettool.baseresrep.Link> links = new ArrayList<>();
							links.add(new pcitc.imp.common.ettool.baseresrep.Link("properties", URI.create(linkUri+"/"+entityMeta.getCode()+"/properties"), "属性集合"));
							
							entityMeta.setLinkObjs(links);
							modelList.add(entityMeta);
						}
						collection = RestfulTool.buildCollection(targets, pagination,hrefBase,com.pcitc.fms.service.model.EntityMeta.class);
					} catch (Exception e) {
						LOGGER.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					LOGGER.info("getUints is end!");
				}
			});
		} catch (Exception e) {
			LOGGER.error("getEntities is Exception!(取得多个实体出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	/**
	 * 插入实体
	 * 
	 * @param routingContext
	 */
	@SuppressWarnings({ "unchecked" })
	public void addEntity(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		String collectionParam = routingContext.getBodyAsString();
		LOGGER.info("*** EntityHandler START addEntity ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.service.model.EntityMeta> entityModelList = new ArrayList<>();
						List<com.pcitc.fms.service.model.EntityMeta> newEntityModelList = new ArrayList<>();
						newEntityModelList = RestfulTool.toResourceRep(collectionParam,
								com.pcitc.fms.service.model.EntityMeta.class);
						// 验证
						for (com.pcitc.fms.service.model.EntityMeta entityModel : newEntityModelList) {
							// checkPlant(plantModel);
							// com.pcitc.fms.service.model.EntityMeta
							// newEntityModel = addCheckEntity(entityModel);
							entityModel.setCreateTime(new Date());
							entityModelList.add(entityModel);
					}

					// 将viewmodel转换成entity
						entityModelList = CurrencyCheck.checkObject(entityModelList,Operation.ADD );//校验方法
					List<com.pcitc.fms.bll.entity.EntityMeta> entityList = ObjectConverter
							.listConverter(entityModelList, com.pcitc.fms.service.model.EntityMeta.class);
					// 调用全局变量alertPointService的add方法添加addAlertPoint数据
					List<com.pcitc.fms.service.model.EntityMeta> str = entityService.addEntities(entityList);
					List<com.pcitc.fms.service.model.EntityMeta> modelList = ObjectConverter.listConverter(str,com.pcitc.fms.service.model.EntityMeta.class);
//					String  collection = RestfulTool.buildCollection(modelList,request.uri(),com.pcitc.fms.service.model.EntityMeta.class);
					future.complete(modelList);
				} catch (BusinessException e) {
					future.fail(e);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;

				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				} else if (res.succeeded()) {
					try {
						
						List resList= (List<com.pcitc.fms.service.model.EntityMeta>) res.result();
//						List modelList = ObjectConverter.listConverter(resList,com.pcitc.fms.service.model.EntityMeta.class);
						collection = RestfulTool.buildCollection(resList,request.uri(),com.pcitc.fms.service.model.EntityMeta.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
//					collection = res.result().toString();
				}

				// 输出结果
				returnCollection(routingContext, collection);
			});

			LOGGER.debug("*** EntityHandler END addEntity ***");
		} catch (Exception e) {
			LOGGER.error("addEntity is Exception!(添加实体出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}
	}

	/**
	 * 取得实体
	 * 
	 * @param routingContext
	 */
	@SuppressWarnings({ "unchecked" })
	public void getEntity(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		LOGGER.info("*** EntityHandler START getEntity ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.EntityMeta> entity = null;
				try {
					CheckUtil.validateCodeException("entityCode", entityCode);
					entity = entityService.getEntityByCode(entityCode);
					future.complete(entity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.EntityMeta> targets = new ArrayList<>();
					try {
						List<com.pcitc.fms.bll.entity.EntityMeta> eUnitList = (List<com.pcitc.fms.bll.entity.EntityMeta>) res
								.result();
						for (com.pcitc.fms.bll.entity.EntityMeta source : eUnitList) {
							com.pcitc.fms.service.model.EntityMeta target = ObjectConverter.entityConverter(source,
									com.pcitc.fms.service.model.EntityMeta.class);
							target.setHref("/" + target.getCode());
							List<String> entityLinks = new ArrayList<>();
							entityLinks.add(hrefBase + "/Properties");
							target.setLinks(entityLinks);
							targets.add(target);
						}
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.EntityMeta.class);
					} catch (Exception e) {
						LOGGER.error("getEntity is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					LOGGER.info("getEntity is end!");
				}
			});
		} catch (Exception e) {
			LOGGER.error("getEntity is Exception!(取得一个实体出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param routingContext
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateEntity(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String collectionParam = routingContext.getBodyAsString();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		LOGGER.debug("*** EntityHandler START updateEntity ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.service.model.EntityMeta> entityModelList = RestfulTool.toResourceRep(collectionParam,com.pcitc.fms.service.model.EntityMeta.class);
					// 验证
					List<com.pcitc.fms.service.model.EntityMeta> newModelList = new ArrayList<>();
					CheckUtil.validateCodeException("entityCode", entityCode);
					List ModelStrList = CurrencyCheck.checkObject(entityModelList,Operation.UPDATE );//校验方法
					List<com.pcitc.fms.bll.entity.EntityMeta> EntityList = ObjectConverter.listConverter(ModelStrList,com.pcitc.fms.bll.entity.EntityMeta.class);
					for (com.pcitc.fms.bll.entity.EntityMeta entity : EntityList) {
						entity.setCode(entityCode);
						entityService.updateEntity(entity);
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

			LOGGER.debug("*** EntityHandler END updateEntity ***");

		} catch (Exception e) {
			LOGGER.error("updateEntity is Exception!(实体更新出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param routingContext
	 */
	public void delEntity(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String entityCode = request.getParam("entityCode") == null ? null : request.getParam("entityCode").trim();
		LOGGER.info("delEntity is start!");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				
				try {
					CheckUtil.validateCodeException("entityCode", entityCode);
					List<com.pcitc.fms.bll.entity.EntityMeta> entity = entityService.getEntityByCode(entityCode);
				if(entity.size() == 0){
					throw new BusiException("", "entityCode:"+entityCode+"不存在！");
				}
				List<com.pcitc.fms.bll.entity.PropertyMeta> propertyList = propertyService.getPropertyByEntityCode(entityCode);
				if(propertyList.size() > 0) {
					throw new com.pcitc.fms.exception.BusinessException("", "","entityCode:"+entityCode+"下存在关联信息无法删除!");
				}
						entityService.deleteEntityByCode(entityCode);
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
					LOGGER.info("delEntity is end!");
				}
			});
		} catch (Exception e) {
			LOGGER.error("delEntity is Exception!(实体删除出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	/**
	 * 
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */
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
					throw new BusinessException(CheckError.ENTITY_CHECK, "", "id只能为大于0的数字");
				}

				idList.add(Integer.valueOf(id.trim()));
			}
		}
		return idList;
	}

	/**
	 * 
	 * @param entityModel
	 * @return entityModel
	 * @throws BusinessException
	 */
	private void addCheckEntity(com.pcitc.fms.service.model.EntityMeta entityModel) throws BusinessException {

		String entityName = entityModel.getEntityName();
		String entityType = entityModel.getEntityType();
		String entityTableName = entityModel.getEntityTableName();
		String enabled = entityModel.getEnabled().toString();
		String creator = entityModel.getCreator();
//		String createTime = entityModel.getCreateTime().toString();
		if (CheckUtil.checkStringIsNull(entityName)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityType)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYTYPE_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityTableName)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYTABLENAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(enabled)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENABLED_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(creator)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.CREATOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(createTime) && CheckUtil.checkIsDateTime(createTime)) {
//			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.CREATETIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(createTime)) {
//			Date date = new Date(createTime);
//			entityModel.setCreateTime(date);
//			entityModel.setMaintainTime(date);
//		}
	}

	/**
	 * 
	 * @param entityModel
	 * @return entityModel
	 * @throws BusinessException
	 */
	private void updateCheckEntity(EntityMeta entityModel) throws BusinessException {

		String entityName = entityModel.getEntityName();
		String entityType = entityModel.getEntityType();
		String entityTableName = entityModel.getEntityTableName();
		String enabled = entityModel.getEnabled().toString();
		String editor = entityModel.getEditor();
//		String maintainTime = entityModel.getMaintainTime().toString();
		if (CheckUtil.checkStringIsNull(entityName)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityType)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYTYPE_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityTableName)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENTITYTABLENAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(enabled)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.ENABLED_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(editor)) {
			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.EDITOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(maintainTime) && CheckUtil.checkIsDateTime(maintainTime)) {
//			throw new BusinessException(CheckError.ENTITY_CHECK, "", CheckError.MAINTAINTIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(maintainTime)) {
//			Date date = new Date(maintainTime);
//			entityModel.setCreateTime(date);
//			entityModel.setMaintainTime(date);
		}
//	}
	
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
