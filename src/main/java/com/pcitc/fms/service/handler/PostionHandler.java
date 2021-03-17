package com.pcitc.fms.service.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.PostionService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Administration;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class PostionHandler extends BaseHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(PostionHandler.class);
	@Autowired
	private PostionService postionService;

	@Autowired
	private CheckType checkType;

	@SuppressWarnings({ "unchecked" })
	public void getPostions(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();

		LOGGER.info("*** PostionHandler START getPostions ***");
		String postionId = CheckUtil.trim(request.getParam("postionId"));
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		String ids = request.getParam("$idList") == null ? null : request.getParam("$idList").trim();
		
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		
		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
		String enabled = request.getParam("enabled") == null ? null : request.getParam("enabled").trim();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaEntity = null;
				try {
					// 从数据库中查询entities
					List<String> codeList = CheckUtil.checkCodeList(codes);
					List<Integer> idList = CheckUtil.checkIdList(ids);
					CheckUtil.checkInput(topStr, skipStr);
					int top = 0;
					int skip = 0;
					if (null != topStr) {
						top = CheckUtil.validateIdIntegerException("top", topStr);
					}
					if (null != skipStr) {
						skip = CheckUtil.validateIdIntegerException("skip", skipStr);
					}
					Integer postionIdInt = CheckUtil.strToInt("postionId", postionId);
					Integer enabledInt = CheckUtil.strToInt("enabled", enabled);
					com.pcitc.fms.service.model.PostionMeta postionMetaModel = new com.pcitc.fms.service.model.PostionMeta(
							postionIdInt, name, shortName, enabledInt, idList, codeList, top, skip);
					postionMetaEntity = postionService.getPostions(postionMetaModel);
					future.complete(postionMetaEntity);
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
						List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaEntityList = (List<com.pcitc.fms.bll.entity.PostionMeta>) res
								.result();
						List<com.pcitc.fms.service.model.PostionMeta> targets = ObjectConverter
								.listConverter(postionMetaEntityList, com.pcitc.fms.service.model.PostionMeta.class);
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.PostionMeta.class);
					} catch (Exception e) {
						LOGGER.error("getPostions is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					LOGGER.info("getPostions is end!");
				}
			});
		} catch (Exception e) {
			LOGGER.error("getPostions is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	@SuppressWarnings({ "unchecked" })
	public void getPostion(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String postionId = request.getParam("postionId") == null ? null : request.getParam("postionId").trim();
		LOGGER.info("*** UserHandler START getUser ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaEntity = null;
				try {

					postionMetaEntity = postionService.getPostion(postionId);

					future.complete(postionMetaEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.PostionMeta> targets = new ArrayList<>();
					try {
						List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaList = (List<com.pcitc.fms.bll.entity.PostionMeta>) res
								.result();
						for (com.pcitc.fms.bll.entity.PostionMeta source : postionMetaList) {
							com.pcitc.fms.service.model.PostionMeta target = ObjectConverter.entityConverter(source,
									com.pcitc.fms.service.model.PostionMeta.class);
							List<String> postionLinks = new ArrayList<>();
							target.setLinks(postionLinks);
							targets.add(target);
						}
						collection = RestfulTool.buildCollection(targets, hrefBase,
								com.pcitc.fms.service.model.PostionMeta.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);

				}
				returnCollection(routingContext, collection);
				LOGGER.info("getUser is end!");
			});
		} catch (Exception e) {
			LOGGER.error("getUser is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	@SuppressWarnings({ "unchecked" })
	public void addPostion(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String collectionParam = routingContext.getBodyAsString();
		String hrefBase = request.uri();
		String parentIdString = CheckUtil.trim(request.getParam("orgUnitId"));
		String parentType = CheckUtil.trim(request.getParam("orgUnitsParentType"));
		String creator = request.getParam("creator");
		LOGGER.debug("*** UserHandler START addUser ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					// checkType.check(parentType, "orgUnitsParentType");
					Integer parentId = CheckUtil.validateIdIntegerException("parentId", parentIdString);

					List<com.pcitc.fms.service.model.PostionMeta> postionMetaModelList = RestfulTool
							.toResourceRep(collectionParam, com.pcitc.fms.service.model.PostionMeta.class);
					List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaEntityList = new ArrayList<>();
					List<com.pcitc.fms.service.model.PostionMeta> newPostionModelList = new ArrayList<>();
					for (com.pcitc.fms.service.model.PostionMeta postionModel : postionMetaModelList) {
						checkCreatorAndEditor(postionModel, "add");
						postionModel.setParentId(parentId);
						postionModel.setParentType(parentType);
						// postionModel.setEnabled(1);
						// postionModel.setCreator(creator);
						// postionModel.setCreateTime(new Date());
						postionModel.setType("administrations");
						com.pcitc.fms.service.model.PostionMeta newPostionMetaModel = addCheckPostion(postionModel);
						newPostionModelList.add(newPostionMetaModel);
					}
					// 校验
					postionMetaModelList = CurrencyCheck.checkObject(postionMetaModelList, Operation.ADD);
					// 将viewmodel转换成entity
					postionMetaEntityList = ObjectConverter.listConverter(postionMetaModelList,
							com.pcitc.fms.bll.entity.PostionMeta.class);
					
					List<com.pcitc.fms.bll.entity.PostionMeta> newPostionMetaEntityList = postionService
							.addPostion(postionMetaEntityList);
					
					List<com.pcitc.fms.service.model.PostionMeta> Mlodels = ObjectConverter
							.listConverter(newPostionMetaEntityList, com.pcitc.fms.service.model.PostionMeta.class);
					
					String collection = RestfulTool.buildCollection(Mlodels, hrefBase,
							com.pcitc.fms.service.model.PostionMeta.class);

					future.complete(collection);
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

			LOGGER.debug("*** PostionHandler END addPostion ***");

		} catch (

		Exception e) {
			LOGGER.error("*** PostionHandler _ addPostion _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}

	}

	@SuppressWarnings({ "unchecked" })
	public void updatePostion(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String collectionParam = routingContext.getBodyAsString();
		String postionId = request.getParam("postionId") == null ? null : request.getParam("postionId").trim();
		// String parentIdString =CheckUtil.trim(request.getParam("orgUnitId"));
		// String parentType =
		// CheckUtil.trim(request.getParam("orgUnitsParentType"));
		LOGGER.debug("*** UserHandler START updateUser ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					// Integer parentId =
					// CheckUtil.validateIdIntegerException("parentId",
					// parentIdString);
					List<com.pcitc.fms.service.model.PostionMeta> postionModelList = RestfulTool
							.toResourceRep(collectionParam, com.pcitc.fms.service.model.PostionMeta.class);
					Integer postionIdInt = CheckUtil.strToInt("postionId", postionId);
					List<com.pcitc.fms.service.model.PostionMeta> newPostionModelList = new ArrayList<>();
					for (com.pcitc.fms.service.model.PostionMeta postionModel : postionModelList) {
						postionModel.setPostionId(postionIdInt);
						newPostionModelList.add(postionModel);
					}
					// 校验
					postionModelList = CurrencyCheck.checkObject(postionModelList, Operation.UPDATE);
					// 将viewmodel转换成entity
					List<com.pcitc.fms.bll.entity.PostionMeta> postionEntityList = ObjectConverter
							.listConverter(postionModelList, com.pcitc.fms.bll.entity.PostionMeta.class);
					if (null != postionEntityList && postionEntityList.size() > 0) {
						postionService.updatePostion(postionEntityList);
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
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					collection = res.result().toString();

				}
				returnCollection(routingContext, collection);
			});
			LOGGER.debug("*** UserHandler END updateUser ***");
		} catch (Exception e) {
			LOGGER.error("updateOrgUnit is Exception!(实体更新出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.uri());
			returnCollection(routingContext, collection);
		}

	}

	public void deletePostion(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String postionId = request.getParam("postionId") == null ? null : request.getParam("postionId").trim();
		LOGGER.info("deleteUser is start!");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					postionService.deletePostionById(postionId);
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
					LOGGER.info("deleteUser is end!");
				}
			});
		} catch (Exception e) {
			LOGGER.error("deleteUser is Exception!(实体删除出错)");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**
	 * 
	 * @param materialModel
	 * @param name
	 * @throws BusinessException
	 */
	private void checkCreatorAndEditor(com.pcitc.fms.service.model.PostionMeta postionMetaModel, String name)
			throws BusinessException {
		String creator = postionMetaModel.getCreator();
		String editor = postionMetaModel.getEditor();
		if ("add".equals(name)) {
			if (creator == null || "".equals(creator)) {
				throw new BusinessException("", "", "creator不能为空");
			}
		} else if ("update".equals(name)) {
			if (editor == null || "".equals(editor)) {
				throw new BusinessException("", "", "editor不能为空");
			}
		}
	}

	/**
	 * 
	 * @param postionMetaModel
	 * @throws BusinessException
	 */
	private com.pcitc.fms.service.model.PostionMeta addCheckPostion(
			com.pcitc.fms.service.model.PostionMeta postionMetaModel) throws BusinessException {
		String code = postionMetaModel.getCode();
		String name = postionMetaModel.getName();
		String shortName = postionMetaModel.getShortName();
		String parentId = postionMetaModel.getParentId().toString();
		String parentType = postionMetaModel.getParentType();
		String type = postionMetaModel.getType();
		String creatorId = postionMetaModel.getCreatorId().toString();
		String creator = postionMetaModel.getCreator();
		String editorId = postionMetaModel.getEditorId().toString();
		String enabled = postionMetaModel.getEnabled().toString();
		String orderId = postionMetaModel.getOrderId().toString();
		String des = postionMetaModel.getDes();
		String createTime = postionMetaModel.getCreateTime().toString();

		// code不能为空,长度不能大于50
		if (CheckUtil.checkCodeIsNull(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_NULL);
		}
		// code只能由数字字母下划线组成
		if (CheckUtil.characterFilter(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_NOT);
		}
		// name不能为空,长度不能大于50
		if (CheckUtil.checkNameIsNull(name)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkNameIsNull(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_NULL);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_NOT);
		}
		if (type != null) {
			if (CheckUtil.checkNameIsNull(type)) {
				throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_NULL);
			}
			if (!CheckUtil.checkStringMatchers(type).find()) {
				throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_NOT);
			}
			postionMetaModel.setType(type.trim());

		}

		if (!CheckUtil.checkStringIsNull(createTime) && !CheckUtil.checkIsDateTime(createTime)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CREATETIME_NOT);
		}
		if (!CheckUtil.checkStringIsNull(createTime)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			Date date = new Date();
			try {
				date = format.parse(createTime.trim());
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
			}
			postionMetaModel.setCreateTime(date);
			postionMetaModel.setMaintainTime(date);
		}
		
		//特殊字符判断
		if (CheckUtil.characterFilter(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(name)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.NAME_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(parentId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.PARENTID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(parentType)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.PARENTTYPE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(type)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(creatorId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CREATORID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(creator)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CREATOR_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(editorId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.EDITORID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(enabled)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.ENABLED_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(orderId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.ORDERID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(des)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.DES_SPECIAL_NOT);
		}
		
		postionMetaModel.setCode(code.trim());
		postionMetaModel.setName(name.trim());
		postionMetaModel.setShortName(shortName.trim());
		postionMetaModel.setParentId(Integer.valueOf(parentId));
		postionMetaModel.setParentType(parentType.trim());
		postionMetaModel.setType(type.trim());
		postionMetaModel.setCreatorId(Integer.valueOf(creatorId));
		postionMetaModel.setCreator(creator.trim());
		postionMetaModel.setEditorId(Integer.valueOf(editorId));
		postionMetaModel.setEnabled(Integer.valueOf(enabled));
		postionMetaModel.setOrderId(Integer.valueOf(orderId));
		postionMetaModel.setDes(des.trim());
		return postionMetaModel;
	}
	
	
	private com.pcitc.fms.service.model.PostionMeta updateCheckPostion(
			com.pcitc.fms.service.model.PostionMeta postionMetaModel) throws BusinessException {
		String code = postionMetaModel.getCode();
		String name = postionMetaModel.getName();
		String shortName = postionMetaModel.getShortName();
		String parentId = postionMetaModel.getParentId().toString();
		String parentType = postionMetaModel.getParentType();
		String type = postionMetaModel.getType();
		String creatorId = postionMetaModel.getCreatorId().toString();
		String creator = postionMetaModel.getCreator();
		String editorId = postionMetaModel.getEditorId().toString();
		String enabled = postionMetaModel.getEnabled().toString();
		String orderId = postionMetaModel.getOrderId().toString();
		String des = postionMetaModel.getDes();
		String maintainTime = postionMetaModel.getMaintainTime().toString();

		// code不能为空,长度不能大于50
		if (CheckUtil.checkCodeIsNull(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_NULL);
		}
		// code只能由数字字母下划线组成
		if (CheckUtil.characterFilter(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_NOT);
		}
		// name不能为空,长度不能大于50
		if (CheckUtil.checkNameIsNull(name)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.NAME_NULL);
		}
		if (CheckUtil.checkNameIsNull(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_NULL);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_NOT);
		}
		if (type != null) {
			if (CheckUtil.checkNameIsNull(type)) {
				throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_NULL);
			}
			if (!CheckUtil.checkStringMatchers(type).find()) {
				throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_NOT);
			}
			postionMetaModel.setType(type.trim());

		}

		if (!CheckUtil.checkStringIsNull(maintainTime) && !CheckUtil.checkIsDateTime(maintainTime)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.MAINTAINTIME_NOT);
		}
		if (!CheckUtil.checkStringIsNull(maintainTime)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			Date date = new Date();
			try {
				date = format.parse(maintainTime.trim());
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
			}
			postionMetaModel.setMaintainTime(date);
		}
		
		//特殊字符判断
		if (CheckUtil.characterFilter(code)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CODE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(name)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.NAME_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(shortName)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.SHORTNAME_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(parentId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.PARENTID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(parentType)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.PARENTTYPE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(type)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.TYPE_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(creatorId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CREATORID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(creator)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.CREATOR_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(editorId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.EDITORID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(enabled)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.ENABLED_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(orderId)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.ORDERID_SPECIAL_NOT);
		}
		if (CheckUtil.characterFilter(des)) {
			throw new BusinessException(CheckError.POSTION_CHECK, "", CheckError.DES_SPECIAL_NOT);
		}
		
		postionMetaModel.setCode(code.trim());
		postionMetaModel.setName(name.trim());
		postionMetaModel.setShortName(shortName.trim());
		postionMetaModel.setParentId(Integer.valueOf(parentId));
		postionMetaModel.setParentType(parentType.trim());
		postionMetaModel.setType(type.trim());
		postionMetaModel.setCreatorId(Integer.valueOf(creatorId));
		postionMetaModel.setCreator(creator.trim());
		postionMetaModel.setEditorId(Integer.valueOf(editorId));
		postionMetaModel.setEnabled(Integer.valueOf(enabled));
		postionMetaModel.setOrderId(Integer.valueOf(orderId));
		postionMetaModel.setDes(des.trim());
		return postionMetaModel;
	}
}
