package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.StaalgrConfService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StaalgrConf;
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
@Controller
public class StaalgrConfHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(StaalgrConfHandler.class);
	@Autowired
	private StaalgrConfService staalgrConfService;
	
/**
 * 删除	
 * @param routingContext
 */
public void deleteStaalgrConf(RoutingContext routingContext) {
		
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
//		String unitCodeStr=request.getParam("$unitCode");
//		String monLevelIdStr = request.getParam("monLevelId"); 
		String agentCodeStr = request.getParam("agentCode");
		log.info("*** StaalgrConfHandler START deleteStaalgrConf ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					String agentCode=CheckUtil.getParamForString("agentCode",agentCodeStr,50,true);
//					Integer monLevelId = CheckUtil.getParamForInteger("monLevelId", monLevelIdStr, 6, true);
				    
				    //开始执行删除操作
//				    staalgrConfService.deleteByMonLevelIdAndEquipId(monLevelId,unitCode);
				    staalgrConfService.deleteByAgentCode(agentCode);
					future.complete();
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					
				} else if (res.succeeded()) {
					collection = "";
					log.info("deleteCommunity is end!");
				}
				returnCollection(routingContext, collection);
			});
		} catch (Exception e) {
			log.error("deleteCommunity is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}
	}
	/**
	 * 更新区域
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void updateStaalgrConf(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String unitCodeStr=request.getParam("$unitCode");
		String monLevelIdStr = request.getParam("monLevelId"); 
//		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
//		String codeStr = request.getParam("code")==null?null:request.getParam("code").trim();
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** StaalgrConfHandler START updateStaalgrConf ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					//校验从url获取的参数,不为空字段
				   
					List<com.pcitc.fms.service.model.StaalgrConf> modelList = RestfulTool.toResourceRep(collectionParam,
							com.pcitc.fms.service.model.StaalgrConf.class);
					String unitCode=CheckUtil.getParamForString("unitCode",unitCodeStr,50,true);
					Integer monLevelId = CheckUtil.getParamForInteger("monLevelId", monLevelIdStr, 6, true);
					for (com.pcitc.fms.service.model.StaalgrConf model : modelList) {
						model.setMonLevelId(monLevelId);
					}
					//校验实体字段合法性
					modelList = CurrencyCheck.checkObject(modelList,Operation.UPDATE);
            		//出入实体开始更新
					staalgrConfService.updateStaalgrConf(modelList,unitCode);
					future.complete(hrefBase);
				} catch (BusinessException be) {
					future.fail(be);
				} catch (Exception e) {
					Exception exception = CheckUtil.catchException(e);
					future.fail(exception);
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

			log.debug("*** StaalgrConfHandler END updateStaalgrConf ***");

		} catch (Exception e) {
			log.error("*** StaalgrConfHandler _ updateStaalgrConf _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					// BusinessExceptionInfo.Unknown.getMessage()),
					request.uri());
			returnCollection(routingContext, collection);
		}		
	}
	
	
	
	/**
	 * 新增区域
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void addStaalgrConf(RoutingContext routingContext) {
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.uri();
		String creatorStr = request.getParam("creator");
		//获取报文
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** StaalgrConfHandler START addStaalgrConf ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.service.model.StaalgrConf> modelList = new ArrayList<>();
				try {
				    //校验姓名,用于excel上传,可以空字段
				    String creator = CheckUtil.getParamForString("creator", creatorStr, 50, true);
				    
					//通过excel上传解析
					if(null != fileUploads && fileUploads.size() > 0){
						FileUpload file = (FileUpload) fileUploads.toArray()[0];
						File excelFile = new File(file.uploadedFileName());
				        ExcelHelper eh = ExcelHelper.readExcel(excelFile);
				        modelList = eh.toEntitys(StaalgrConf.class);
				       
					}else{
						modelList = RestfulTool.toResourceRep(collectionParam,
								StaalgrConf.class);
					}
					//获取的字段装入model实体
					for (StaalgrConf model : modelList) {
						model.setCreator(creator == null ? model.getCreator() : creator);
					}
					//校验传入的实体各个字段
					modelList = CurrencyCheck.checkObject(modelList,Operation.ADD);
					//进入service层开始新增
					List<com.pcitc.fms.bll.entity.StaalgrConf> entityList = staalgrConfService.addStaalgrConf(modelList);
					modelList = ObjectConverter.listConverter(entityList,StaalgrConf.class);
					String collection = RestfulTool.buildCollection(modelList,uri,StaalgrConf.class);
					future.complete(collection);
				} catch (BusinessException be) {
					future.fail(be);
				} catch (Exception e) {
					Exception exception = CheckUtil.catchException(e);
					future.fail(exception);
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

			log.debug("*** StaalgrConfHandler END addStaalgrConf ***");

		} catch (Exception e) {
			log.error("*** StaalgrConfHandler _ addStaalgrConf _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					request.uri());
			returnCollection(routingContext, collection);
		}
	}
	@SuppressWarnings("unchecked")
	public void getStaalgrConfs(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String hrefBase = request.uri();
		log.info("*** StaalgrConfHandler START getStaalgrConfs ***");
		//条件查询参数
		String unitCodeStr=request.getParam("$unitCode") == null ? null : request.getParam("$unitCode").trim();
		String name = request.getParam("name") == null ? null : request.getParam("name").trim();
		String shortName = request.getParam("shortName") == null ? null : request.getParam("shortName").trim();
		String inUseStr = request.getParam("inUse") == null ? null : request.getParam("inUse").trim();
		String equipIdStr = request.getParam("equipId") == null ? null : request.getParam("equipId").trim();
		String monLevelIdStr = request.getParam("monLevelId") == null ? null : request.getParam("monLevelId").trim(); 
		String staalgrIdStr = request.getParam("staalgrId") == null ? null : request.getParam("staalgrId").trim(); 
		//集合与分页查询参数
		String codes = request.getParam("$codeList") == null ? null : request.getParam("$codeList").trim();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "staalgrConfId");
		Pagination pagination = new Pagination();
		
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.StaalgrConf> entity = null;
				try {
				    //校验条件查询参数,可以空字段
				    String staalgrConfName = CheckUtil.getParamForString("name", name, 50, true);
				    String staalgrConfShortName = CheckUtil.getParamForString("shortName", shortName, 50, true);
				    String unitCode=CheckUtil.getParamForString("unitCode",unitCodeStr,50,true);
				    Integer inUse = CheckUtil.enabled("enabled", inUseStr);
				    //校验集合与分页查询参数,可以空字段
				    List<String> codeList = CheckUtil.checkCodeList(codes);
				    Integer equipId = CheckUtil.getParamForInteger("equipId", equipIdStr, 6, true);
				    Integer monLevelId = CheckUtil.getParamForInteger("monLevelId", monLevelIdStr, 6, true);
				    Integer staalgrId = CheckUtil.getParamForInteger("staalgrId", staalgrIdStr, 6, true);
					Integer top = CheckUtil.getParamForInteger("top", topStr, 6, true);
					Integer skip = CheckUtil.getParamForInteger("skip", skipStr, 6, true);
					//分页校验
					Pageable pageable = null;
					if (null != topStr && !"".equals(topStr) && null != skip) {
						pageable = PageUtil.pageable(top, skip, sort);
					}
					//组装办公区实体
					com.pcitc.fms.service.model.StaalgrConf model = new com.pcitc.fms.service.model.StaalgrConf(staalgrConfShortName, equipId, monLevelId, staalgrId, inUse, top, skip, codeList,unitCode);
					
					//开始查询
					Pager<com.pcitc.fms.bll.entity.StaalgrConf> sourceData = staalgrConfService.getPageStaalgrConfs(model);
					PageUtil.mergePage(pagination, sourceData);
					entity  = sourceData.getContent();
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
					try {
						List<com.pcitc.fms.bll.entity.StaalgrConf> staalgrConfEntityList = (List<com.pcitc.fms.bll.entity.StaalgrConf>) res.result();
						List<com.pcitc.fms.service.model.StaalgrConf> modelList = ObjectConverter.listConverter(staalgrConfEntityList, com.pcitc.fms.service.model.StaalgrConf.class);
						collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, StaalgrConf.class);
					} catch (Exception e) {
						log.error("getStaalgrConfs is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getStaalgrConfs is end!");
				}
			});
		} catch (Exception e) {
			log.error("getStaalgrConfs is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	
	//hanxiao
		public void getStaalgrConf(RoutingContext routingContext) {

			HttpServerRequest request = routingContext.request();
			String uri = request.absoluteURI();
			String hrefBase = request.uri();
			String codeStr = request.getParam("code")==null?null:request.getParam("code").trim(); 
			log.info("*** StaalgrConfHandler START getStaalgrConf ***");
			try {
				Vertx vertx = routingContext.vertx();
				vertx.executeBlocking(future -> {
					try {
					    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
						com.pcitc.fms.bll.entity.StaalgrConf entity = staalgrConfService.getByCode(code);
						com.pcitc.fms.service.model.StaalgrConf model = ObjectConverter.entityConverter(entity, com.pcitc.fms.service.model.StaalgrConf.class);
						future.complete(model);
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
							List<com.pcitc.fms.bll.entity.StaalgrConf> staalgrConfEntityList = (List<com.pcitc.fms.bll.entity.StaalgrConf>) res.result();
							List<com.pcitc.fms.service.model.StaalgrConf> modelList = ObjectConverter.listConverter(staalgrConfEntityList, com.pcitc.fms.service.model.StaalgrConf.class);
							collection = RestfulTool.buildCollection(modelList, hrefBase, StaalgrConf.class);
						} catch (Exception e) {
							log.error("getStaalgrConf is Exception!");
							collection = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collection);
						}
						returnCollection(routingContext, collection);
						log.info("getStaalgrConf is end!");
					}
				});
			} catch (Exception e) {
				log.error("getStaalgrConf is Exception!");
				String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
				returnCollection(routingContext, collection);
			}

		}
}
