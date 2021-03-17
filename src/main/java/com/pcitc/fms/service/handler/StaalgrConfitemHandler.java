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

import com.pcitc.fms.bll.itf.StaalgrConfitemService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StaalgrConfitem;
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
public class StaalgrConfitemHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(StaalgrConfitemHandler.class);
	@Autowired
	private StaalgrConfitemService staalgrConfitemService;
	
/**
 * 删除	
 * @param routingContext
 */
public void deleteStaalgrConfitem(RoutingContext routingContext) {
		
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
//		String opeindexIdStr = request.getParam("opeindexId"); 
//		String staalgrConfIdStr = request.getParam("staalgrConfId");
		String agentCodeStr = request.getParam("agentCode");
		log.info("*** StaalgrConfitemHandler START deleteStaalgrConfitem ***");

		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
//					Integer opeindexId = CheckUtil.getParamForInteger("opeindexId", opeindexIdStr, 6, true);
//				    Integer staalgrConfId = CheckUtil.getParamForInteger("staalgrConfId", staalgrConfIdStr, 6, true);
//				    //开始执行删除操作 -- 通过操作指标id和平稳率配置id删除单条
//				    if(opeindexId!=null && staalgrConfId!=null) {
//				    	staalgrConfitemService.deleteByOpeindexIdAndStaalgrConfId(opeindexId,staalgrConfId);
//				    }
//				    else if (opeindexId==null && staalgrConfId!=null) {
//				    	staalgrConfitemService.deleteByStaalgrConfId(staalgrConfId);
//				    }
					String agentCode=CheckUtil.getParamForString("agentCode",agentCodeStr,50,true);
					staalgrConfitemService.deleteByAgentCode(agentCode);
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
	public void updateStaalgrConfitem(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String opeindexIdStr = request.getParam("opeindexId"); 
		String staalgrConfIdStr = request.getParam("staalgrConfId");
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** StaalgrConfitemHandler START updateStaalgrConfitem ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					//校验从url获取的参数,不为空字段
					Integer opeindexId = CheckUtil.getParamForInteger("opeindexId", opeindexIdStr, 6, true);
				    Integer staalgrConfId = CheckUtil.getParamForInteger("staalgrConfId", staalgrConfIdStr, 6, true);
					List<com.pcitc.fms.service.model.StaalgrConfitem> modelList = RestfulTool.toResourceRep(collectionParam,
							com.pcitc.fms.service.model.StaalgrConfitem.class);
				 
					for (com.pcitc.fms.service.model.StaalgrConfitem model : modelList) {
						model.setOpeindexId(opeindexId);
						model.setStaalgrConfId(staalgrConfId);
					}
					//校验实体字段合法性
					modelList = CurrencyCheck.checkObject(modelList,Operation.ADD);
            		//出入实体开始更新
					staalgrConfitemService.updateStaalgrConfitem(modelList);
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

			log.debug("*** StaalgrConfitemHandler END updateStaalgrConfitem ***");

		} catch (Exception e) {
			log.error("*** StaalgrConfitemHandler _ updateStaalgrConfitem _ Exception " + e.getMessage() + " ***");
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
	public void addStaalgrConfitem(RoutingContext routingContext) {
		Set<FileUpload> fileUploads = routingContext.fileUploads();
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.uri();
		String creatorStr = request.getParam("creator");
		//获取报文
		String collectionParam = routingContext.getBodyAsString();
		log.debug("*** StaalgrConfitemHandler START addStaalgrConfitem ***");
		// 将template转换成viewmodel
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.service.model.StaalgrConfitem> modelList = new ArrayList<>();
				try {
				    //校验姓名,用于excel上传,可以空字段
				    String creator = CheckUtil.getParamForString("creator", creatorStr, 50, true);
				    
					//通过excel上传解析
					if(null != fileUploads && fileUploads.size() > 0){
						FileUpload file = (FileUpload) fileUploads.toArray()[0];
						File excelFile = new File(file.uploadedFileName());
				        ExcelHelper eh = ExcelHelper.readExcel(excelFile);
				        modelList = eh.toEntitys(StaalgrConfitem.class);
				       
					}else{
						modelList = RestfulTool.toResourceRep(collectionParam,
								StaalgrConfitem.class);
					}
					//获取的字段装入model实体
					for (StaalgrConfitem model : modelList) {
						model.setCreator(creator == null ? model.getCreator() : creator);
					}
					//校验传入的实体各个字段
					modelList = CurrencyCheck.checkObject(modelList,Operation.ADD);
					//进入service层开始新增
					List<com.pcitc.fms.bll.entity.StaalgrConfitem> entityList = staalgrConfitemService.addStaalgrConfitem(modelList);
					modelList = ObjectConverter.listConverter(entityList,StaalgrConfitem.class);
					String collection = RestfulTool.buildCollection(modelList,uri,StaalgrConfitem.class);
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

			log.debug("*** StaalgrConfitemHandler END addStaalgrConfitem ***");

		} catch (Exception e) {
			log.error("*** StaalgrConfitemHandler _ addStaalgrConfitem _ Exception " + e.getMessage() + " ***");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					request.uri());
			returnCollection(routingContext, collection);
		}
	}
	@SuppressWarnings("unchecked")
	public void getStaalgrConfitems(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String hrefBase = request.uri();
		log.info("*** StaalgrConfitemHandler START getStaalgrConfitems ***");
		//条件查询参数
		String name = request.getParam("name");
		String shortName = request.getParam("shortName");
		String inUseStr = request.getParam("inUse");
		String unitIdStr = request.getParam("unitId");
		String craftSchemeIdStr = request.getParam("craftSchemeId"); 
		String opeindexIdStr = request.getParam("opeindexId"); 
		String staalgrConfIdStr = request.getParam("staalgrConfId"); 
		String opeindexCodeStr = request.getParam("opeindexCode"); 
		//集合与分页查询参数
		String codes = request.getParam("$codeList");
		String topStr = request.getParam("$top");
		String skipStr = request.getParam("$skip");
		Sort sort = new Sort(Sort.Direction.ASC, "staalgrConfitemId");
		Pagination pagination = new Pagination();
		
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.StaalgrConfitem> entity = null;
				try {
				    //校验条件查询参数,可以空字段
				    String staalgrConfitemName = CheckUtil.getParamForString("name", name, 50, true);
				    String staalgrConfitemShortName = CheckUtil.getParamForString("shortName", shortName, 50, true);
				    String opeindexCode = CheckUtil.getParamForString("opeindexCode", opeindexCodeStr, 50, true);
				    Integer inUse = CheckUtil.enabled("enabled", inUseStr);
				    //校验集合与分页查询参数,可以空字段
				    List<String> codeList = CheckUtil.checkCodeList(codes);
				    Integer opeindexId = CheckUtil.getParamForInteger("opeindexId", opeindexIdStr, 6, true);
				    Integer staalgrConfId = CheckUtil.getParamForInteger("staalgrConfId", staalgrConfIdStr, 6, true);
				    Integer craftSchemeId = CheckUtil.getParamForInteger("craftSchemeId", craftSchemeIdStr, 6, true);
					Integer top = CheckUtil.getParamForInteger("top", topStr, 6, true);
					Integer skip = CheckUtil.getParamForInteger("skip", skipStr, 6, true);
					//分页校验
					Pageable pageable = null;
					if (null != topStr && !"".equals(topStr) && null != skip) {
						pageable = PageUtil.pageable(top, skip, sort);
					}
					//组装办公区实体
					com.pcitc.fms.service.model.StaalgrConfitem model = new com.pcitc.fms.service.model.StaalgrConfitem(craftSchemeId, opeindexId, inUse, top, skip, codeList,opeindexCode,staalgrConfId);
					
					//开始查询
					Pager<com.pcitc.fms.bll.entity.StaalgrConfitem> sourceData = staalgrConfitemService.getPageStaalgrConfitems(model);
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
						List<com.pcitc.fms.bll.entity.StaalgrConfitem> staalgrConfitemEntityList = (List<com.pcitc.fms.bll.entity.StaalgrConfitem>) res.result();
						List<com.pcitc.fms.service.model.StaalgrConfitem> modelList = ObjectConverter.listConverter(staalgrConfitemEntityList, com.pcitc.fms.service.model.StaalgrConfitem.class);
						collection = RestfulTool.buildCollection(modelList, pagination, hrefBase, StaalgrConfitem.class);
					} catch (Exception e) {
						log.error("getStaalgrConfitems is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getStaalgrConfitems is end!");
				}
			});
		} catch (Exception e) {
			log.error("getStaalgrConfitems is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	
	//hanxiao
		public void getStaalgrConfitem(RoutingContext routingContext) {

			HttpServerRequest request = routingContext.request();
			String uri = request.absoluteURI();
			String hrefBase = request.uri();
			String codeStr = request.getParam("code")==null?null:request.getParam("code").trim(); 
			log.info("*** StaalgrConfitemHandler START getStaalgrConfitem ***");
			try {
				Vertx vertx = routingContext.vertx();
				vertx.executeBlocking(future -> {
					try {
					    String code = CheckUtil.getParamForString("code", codeStr, 36, true);
						com.pcitc.fms.bll.entity.StaalgrConfitem entity = staalgrConfitemService.getByCode(code);
						com.pcitc.fms.service.model.StaalgrConfitem model = ObjectConverter.entityConverter(entity, com.pcitc.fms.service.model.StaalgrConfitem.class);
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
							com.pcitc.fms.service.model.StaalgrConfitem model = (com.pcitc.fms.service.model.StaalgrConfitem) res.result();
							List<com.pcitc.fms.service.model.StaalgrConfitem> modelList = new ArrayList<>();
							modelList.add(model);
							collection = RestfulTool.buildCollection(modelList,  hrefBase, StaalgrConfitem.class);
						} catch (Exception e) {
							log.error("getStaalgrConfitem is Exception!");
							collection = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collection);
						}
						returnCollection(routingContext, collection);
						log.info("getStaalgrConfitem is end!");
					}
				});
			} catch (Exception e) {
				log.error("getCommunity is Exception!");
				String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
				returnCollection(routingContext, collection);
			}

		}
}
