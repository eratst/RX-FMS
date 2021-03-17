package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.service.dictionary.model.AreaType;
import com.pcitc.fms.service.dictionary.model.Controldep;
import com.pcitc.fms.service.dictionary.model.LoadrackTechnic;
import com.pcitc.fms.service.dictionary.model.OpenindexClass;
import com.pcitc.fms.service.dictionary.model.OrgType;
import com.pcitc.fms.service.dictionary.model.PipenetTechnic;
import com.pcitc.fms.service.dictionary.model.Staalgr;
import com.pcitc.fms.service.dictionary.model.TankAreaTechnic;
import com.pcitc.fms.service.dictionary.model.TankAreaType;
import com.pcitc.fms.service.dictionary.model.Technic;
import com.pcitc.fms.service.dictionary.model.UnitType;
import com.pcitc.fms.service.dictionary.model.WarehousTechnic;
import com.pcitc.fms.service.dictionary.model.WarehouseType;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class AreaDictGatherHandler extends BaseHandler{
	@Autowired
	private AreaDictGatherService areaDictService;
	/**
	 * 操作平稳率算法类型
	 */
	@SuppressWarnings("unchecked")
	public void getStaalgrs(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.Staalgr> dictlist_entity = areaDictService.getStaalgrs(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<Staalgr> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.Staalgr>) res.result(),Staalgr.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),Staalgr.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 控制部门类型
	 */
	@SuppressWarnings("unchecked")
	public void getControldeps(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.Controldep> dictlist_entity = areaDictService.getControldeps(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<Controldep> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.Controldep>) res.result(),Controldep.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),Controldep.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 操作指标类型
	 */
	@SuppressWarnings("unchecked")
	public void getOpeindexclass(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.OpenindexClass> dictlist_entity = areaDictService.getOpenindexClass(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<OpenindexClass> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.OpenindexClass>) res.result(),OpenindexClass.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),OpenindexClass.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 管网工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getPipenetTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.PipenetTechnic> dictlist_entity = areaDictService.getPipenetTechnics(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<PipenetTechnic> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.PipenetTechnic>) res.result(),PipenetTechnic.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),PipenetTechnic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 装卸台工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getLoadrackTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic> dictlist_entity = areaDictService.getLoadrackTechnics(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<LoadrackTechnic> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic>) res.result(),LoadrackTechnic.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),LoadrackTechnic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 仓库工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getWarehousTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.WarehousTechnic> dictlist_entity = areaDictService.getWarehousTechnics(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<WarehousTechnic> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.WarehousTechnic>) res.result(),WarehousTechnic.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),WarehousTechnic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 仓库业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getWarehouseTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.WarehouseType> dictlist_entity = areaDictService.getWarehouseTypes(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<WarehouseType> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.WarehouseType>) res.result(),WarehouseType.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),WarehouseType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 罐区工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getTankAreaTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic> dictlist_entity = areaDictService.getTankAreaTechnics(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<TankAreaTechnic> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic>) res.result(),TankAreaTechnic.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),TankAreaTechnic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 罐区业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getTankAreaTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.TankAreaType> dictlist_entity = areaDictService.getTankAreaTypes(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<TankAreaType> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.TankAreaType>) res.result(),TankAreaType.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),TankAreaType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 查询装置工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.Technic> dictlist_entity = areaDictService.getTechnics(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<Technic> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.Technic>) res.result(),Technic.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),Technic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 查询装置业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getUnitTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.UnitType> dictlist_entity = areaDictService.getUnitTypes(code);
						future.complete(dictlist_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<UnitType> dictlist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.UnitType>) res.result(),UnitType.class);
							collecion = RestfulTool.buildCollection(dictlist_model,routingContext.request().absoluteURI(),UnitType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}
	/**
	 * 查询组织机构类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getOrgTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.OrgType> aorgTypes_entity = areaDictService.getOrgTypes(code);
						future.complete(aorgTypes_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<OrgType> orgTypes_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.OrgType>) res.result(),OrgType.class);
							collecion = RestfulTool.buildCollection(orgTypes_model,routingContext.request().absoluteURI(),OrgType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	
	}

	/**
	 * 查询区域类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getAreaTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.AreaType> areaTypes_entity = areaDictService.getAreaTypes(code);
						future.complete(areaTypes_entity);
					} catch (Exception e) {
						future.fail(e);
					}
				},
				res -> {
					String collecion = null;
					if (res.failed()) {
						collecion = buildErrorCollection(routingContext,(BusiException) res.cause());
						returnCollection(routingContext, collecion);
					} else if (res.succeeded()) {
						try {
							List<AreaType> orgTypes_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.AreaType>) res.result(),AreaType.class);
							collecion = RestfulTool.buildCollection(orgTypes_model,routingContext.request().absoluteURI(),AreaType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
}
