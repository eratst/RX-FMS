package com.pcitc.fms.service.handler;

import java.io.File;
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

import com.pcitc.fms.bll.itf.DictionaryTableService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.ExcelUtils;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.DictionaryTable;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PropertyMeta;
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
 * 元元模型 - 字典表 操作
 * @author XBH
 *
 */
@Controller
public class DictionaryTableHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(DictionaryTableHandler.class);
	@Autowired
	private DictionaryTableService dictionaryTableService;

	@SuppressWarnings("unchecked")
	public void getDictionaryTables(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		log.info("*** DictionaryTableHandler START getDictionaryTables ***");
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String enabled = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "dictionaryTableId");
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntity = null;
				try {
					String entityTableName = request.getParam("entityTableName")==null?null:checkField(request.getParam("entityTableName").trim(),"entityTableName");
					String entityType = request.getParam("entityType")==null?null:checkField(request.getParam("entityType").trim(),"entityType");
					String name = request.getParam("name")==null?null:checkField(request.getParam("name").trim(),"entityType");
					// 从数据库中查询entities
					List<Integer> idList = CheckUtil.buildStringToListInteger("$idList",ids);
					CheckUtil.checkInput(topStr, skipStr);
					int top = 0;
					int skip = 0;
					int enabledInt;
					boolean checkInput = CheckUtil.checkInput(topStr,skipStr);
					if(null != enabled && !"".equals(enabled)){
						 enabledInt = CheckUtil.strToInt("enabled", enabled);
					}
					if (null != topStr&&!topStr.equals("")) {
						top = CheckUtil.strToInt("top", topStr);
//						top = Integer.parseInt(topStr);
					}
					if (null != skipStr&&!skipStr.equals("")) {
						skip = Integer.parseInt(skipStr);
//						skip = CheckUtil.strToInt("skip", skipStr);
					}
					DictionaryTable dictionaryTableModel = new DictionaryTable(entityTableName, entityType, name, idList, top, skip);
					//判断 是否分页
					Pager<com.pcitc.fms.bll.entity.DictionaryTable> sourceData;
					if(null != topStr &&!topStr.equals("")&& null != skipStr && checkInput){
						Pageable pageable = PageUtil.pageable(top, skip, sort);
						sourceData = dictionaryTableService.getDictionaryTables(dictionaryTableModel, pageable);
						PageUtil.mergePage(pagination, sourceData);
					}else{
						sourceData = dictionaryTableService.getDictionaryTables(dictionaryTableModel, null);
					}
					dictionaryTableEntity=sourceData.getContent();
					future.complete(dictionaryTableEntity);
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
						List<com.pcitc.fms.bll.entity.DictionaryTable> propertyList = (List<com.pcitc.fms.bll.entity.DictionaryTable>) res
								.result();
						// 将viewmodel转换成entity  
						
						List<DictionaryTable> targets = ObjectConverter
								.listConverter(propertyList, DictionaryTable.class);
						collection = RestfulTool.buildCollection(targets,pagination, hrefBase, DictionaryTable.class);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addDictionaryTable(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** PropertyHandler START addProperty ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					
					List<com.pcitc.fms.service.model.DictionaryTable> dictionaryTableModelList = RestfulTool
							.toResourceRep(collectionParam, com.pcitc.fms.service.model.DictionaryTable.class);
					List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList = new ArrayList<>();
					// 验证
					 for (com.pcitc.fms.service.model.DictionaryTable dictionaryTableModel : dictionaryTableModelList) {
						 dictionaryTableModel.setCreateTime(new Date());
//						 dictionaryTableEntityList.add(dictionaryTableModel);
					 }
					// 将viewmodel转换成entity
					dictionaryTableModelList = CurrencyCheck.checkObject(dictionaryTableModelList,Operation.ADD );//校验方法
					dictionaryTableEntityList = ObjectConverter.listConverter(dictionaryTableModelList,
							com.pcitc.fms.bll.entity.DictionaryTable.class);
					

					// 调用全局变量alertPointService的add方法添加addAlertPoint数据
					List str = dictionaryTableService.addDictionaryTable(dictionaryTableEntityList);

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
						List<DictionaryTable> resList = (List<DictionaryTable>) res.result();
						List modelList = ObjectConverter.listConverter(resList,DictionaryTable.class);
						collection = RestfulTool.buildCollection(modelList,request.uri(),DictionaryTable.class);
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
	public void getDictionaryTable(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String dictionaryTableId = request.getParam("dictionaryTableId") == null ? null
				: request.getParam("dictionaryTableId").trim();
		log.info("*** FactoryHandler START getFactory ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntity = null;
				try {

					dictionaryTableEntity = dictionaryTableService.getDictionaryTable(dictionaryTableId);

					future.complete(dictionaryTableEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.DictionaryTable> targets = new ArrayList<>();
					try {
						List<com.pcitc.fms.bll.entity.DictionaryTable> eUnitList = (List<com.pcitc.fms.bll.entity.DictionaryTable>) res
								.result();
						for (com.pcitc.fms.bll.entity.DictionaryTable source : eUnitList) {
							DictionaryTable target = ObjectConverter.entityConverter(source, DictionaryTable.class);
							// target.setHref("/" +
							// target.getDictionaryTableId());
							List<String> dictionaryTableLinks = new ArrayList<>();
							// dictionaryTableLinks.add(hrefBase+"/"+target.getDictionaryTableId());
							target.setLinks(dictionaryTableLinks);
							targets.add(target);
						}
						collection = RestfulTool.buildCollection(targets, hrefBase, DictionaryTable.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			log.error("getFactory is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateDictionaryTable(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
//		String dictionaryTableId = CheckUtil.trim(request.getParam("dictionaryTableId"));
//		int dictionaryTableIdInt = Integer.parseInt(dictionaryTableId);
		String dictionaryTable = request.getParam("dictionaryTableId")==null?null:request.getParam("dictionaryTableId").trim();
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** PropertyHandler START updateProperty ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.service.model.DictionaryTable> dictionaryTableModelList = RestfulTool
							.toResourceRep(collectionParam, com.pcitc.fms.service.model.DictionaryTable.class);
					// 验证
					Integer tankAreaIdInt = CheckUtil.strToInt("dictionaryTableId", dictionaryTable);
					List<com.pcitc.fms.service.model.DictionaryTable> newModelList = new ArrayList<>();
					for (com.pcitc.fms.service.model.DictionaryTable dictionaryTableModel : dictionaryTableModelList) {
						dictionaryTableModel.setDictionaryTableId(tankAreaIdInt);
						newModelList.add(dictionaryTableModel);
					}
					// 将viewmodel转换成entity
					dictionaryTableModelList=CurrencyCheck.checkObject(dictionaryTableModelList,Operation.UPDATE );//校验方法
					List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList = ObjectConverter
							.listConverter(dictionaryTableModelList, com.pcitc.fms.service.model.DictionaryTable.class);
					if (null != dictionaryTableEntityList && dictionaryTableEntityList.size() > 0) {
						dictionaryTableService.updateDictionaryTable(dictionaryTableEntityList);
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

	public void deleteDictionaryTable(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String dictionaryTableId = request.getParam("dictionaryTableId") == null ? null
				: request.getParam("dictionaryTableId").trim();
		log.info("*** PropertyHandler START deleteDictionaryTable ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					dictionaryTableService.deleteDictionaryTable(dictionaryTableId);
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
					log.info("deleteDictionaryTable is end!");
				}
			});
		} catch (Exception e) {
			log.error("deleteDictionaryTable is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}

	private void addCheckDictionaryTable(com.pcitc.fms.service.model.DictionaryTable dictionaryTableModel)
			throws BusinessException {

		String entityTableName = dictionaryTableModel.getEntityTableName();
		String fieldName = dictionaryTableModel.getFieldName();
		String fieldValue = dictionaryTableModel.getFieldValue();
		String name = dictionaryTableModel.getName();
		String creator = dictionaryTableModel.getCreator();
//		String createTime = dictionaryTableModel.getCreateTime().toString();
		if (CheckUtil.checkStringIsNull(fieldName)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.FIELDNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityTableName)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.ENTITYTABLENAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(fieldValue)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.FIELDVALUE_NULL);
		}
		if (CheckUtil.checkStringIsNull(name)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(creator)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.CREATOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(createTime) && CheckUtil.checkIsDateTime(createTime)) {
//			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.CREATETIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(createTime)) {
//			Date date = new Date(createTime);
//			dictionaryTableModel.setCreateTime(date);
//			dictionaryTableModel.setMaintainTime(date);
//		}
	}

	/**
	 * 
	 * @param entityModel
	 * @return
	 * @throws BusinessException
	 */
	private void updateCheckDictionaryTable(com.pcitc.fms.service.model.DictionaryTable dictionaryTableModel)
			throws BusinessException {

		String entityTableName = dictionaryTableModel.getEntityTableName();
		String fieldName = dictionaryTableModel.getFieldName();
		String fieldValue = dictionaryTableModel.getFieldValue();
		String name = dictionaryTableModel.getName();
		String editor = dictionaryTableModel.getEditor();
//		String maintainTime = dictionaryTableModel.getMaintainTime().toString();

		if (CheckUtil.checkStringIsNull(fieldName)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.FIELDNAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityTableName)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.ENTITYTABLENAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(fieldValue)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.FIELDVALUE_NULL);
		}
		if (CheckUtil.checkStringIsNull(name)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkStringIsNull(entityTableName)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.ENTITYTABLENAME_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(editor)) {
			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.EDITOR_NOT);
		}
//		if (!CheckUtil.checkStringIsNull(maintainTime) && CheckUtil.checkIsDateTime(maintainTime)) {
//			throw new BusinessException(CheckError.DICTIONARYTABLE_CHECK, "", CheckError.MAINTAINTIME_NOT);
//		}
//		if (!CheckUtil.checkStringIsNull(maintainTime)) {
//			Date date = new Date(maintainTime);
//			dictionaryTableModel.setCreateTime(date);
//			dictionaryTableModel.setMaintainTime(date);
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
