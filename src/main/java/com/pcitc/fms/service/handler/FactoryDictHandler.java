package com.pcitc.fms.service.handler;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.FacilityTypeDict;
import com.pcitc.fms.service.model.NodeObjectTypeDict;
import com.pcitc.imp.common.exception.BusiException;

@Controller
public class FactoryDictHandler extends BaseHandler {
//
//	@Autowired
//	private FactoryDictService dictService;
//
//	/**
//	 * 查询所有设施类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getFacilityTypeDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.FacilityTypeDict> agentList_entity = dictService
//								.getFacilityTypeDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.FacilityTypeDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 根据设施id查询相应设施类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getFacilityTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.FacilityTypeDict> agentList_entity = dictService
//								.getFacilityTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.FacilityTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 装置业务类型字典
//	 */
//	@SuppressWarnings("unchecked")
//	public void getPlantBusinessDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.PlantBusinessDict> agentList_entity = dictService
//								.getPlantBusinessDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.PlantBusinessDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询全部装置业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getPlantBusinessDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.PlantBusinessDict> agentList_entity = dictService
//								.getPlantBusinessDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.PlantBusinessDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 装置工艺类型字典
//	 */
//	@SuppressWarnings("unchecked")
//	public void getPlantTechnicDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.PlantTechnicDict> agentList_entity = dictService
//								.getPlantTechnicDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.PlantTechnicDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询装置全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getPlantTechnicDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.PlantTechnicDict> agentList_entity = dictService
//								.getPlantTechnicDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.PlantTechnicDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 罐区业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankAreaBusineDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankAreaBusineDict> agentList_entity = dictService
//								.getTankAreaBusineDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankAreaBusineDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询罐区全部业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankAreaBusineDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankAreaBusineDict> agentList_entity = dictService
//								.getTankAreaBusineDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankAreaBusineDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 罐区工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankAreaTechnicDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankAreaTechnicDict> agentList_entity = dictService
//								.getTankAreaTechnicDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankAreaTechnicDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询罐区全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankAreaTechnicDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankAreaTechnicDict> agentList_entity = dictService
//								.getTankAreaTechnicDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankAreaTechnicDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 仓库业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getWarehouseBusineDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.WarehouseBusineDict> agentList_entity = dictService
//								.getWarehouseBusineDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.WarehouseBusineDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询仓库全部业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getWarehouseBusineDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.WarehouseBusineDict> agentList_entity = dictService
//								.getWarehouseBusineDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.WarehouseBusineDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 仓库工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getWarehouseTechniDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.WarehouseTechniDict> agentList_entity = dictService
//								.getWarehouseTechniDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.WarehouseTechniDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询仓库全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getWarehouseTechniDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.WarehouseTechniDict> agentList_entity = dictService
//								.getWarehouseTechniDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.WarehouseTechniDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 装卸台工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getLoadingTechnicDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.LoadingTechnicDict> agentList_entity = dictService
//								.getLoadingTechnicDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.LoadingTechnicDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询装卸台全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getLoadingTechnicDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.LoadingTechnicDict> agentList_entity = dictService
//								.getLoadingTechnicDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.LoadingTechnicDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 侧线进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSidelineImportDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SidelineImportDict> agentList_entity = dictService
//								.getSidelineImportDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SidelineImportDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 查询侧线全部进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSidelineImportDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SidelineImportDict> agentList_entity = dictService
//								.getSidelineImportDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SidelineImportDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 侧线工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSidelineTechnicDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SidelineTechnicDict> agentList_entity = dictService
//								.getSidelineTechnicDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SidelineTechnicDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 侧线全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSidelineTechnicDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SidelineTechnicDict> agentList_entity = dictService
//								.getSidelineTechnicDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SidelineTechnicDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 节点对象类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getNodeObjectTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.NodeObjectTypeDict> agentList_entity = dictService
//								.getNodeObjectTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.NodeObjectTypeDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 全部节点对象类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getNodeObjectTypeDictFInd(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.NodeObjectTypeDict> agentList_entity = dictService
//								.getNodeObjectTypeDictFInd();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.NodeObjectTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 储罐节点类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankNodeTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankNodeTypeDict> agentList_entity = dictService
//								.getTankNodeTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankNodeTypeDict>) res
//													.result(),
//													SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 储罐节点全部类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getTankNodeTypeDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.TankNodeTypeDict> agentList_entity = dictService
//								.getTankNodeTypeDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.TankNodeTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//
//	/**
//	 * 料仓节点类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSiloNodeTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SiloNodeTypeDict> agentList_entity = dictService
//								.getSiloNodeTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SiloNodeTypeDict>) res
//													.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 料仓节点全部类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSiloNodeTypeDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SiloNodeTypeDict> agentList_entity = dictService
//								.getSiloNodeTypeDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SiloNodeTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 进出点进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getEdgepointTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.EdgepointTypeDict> agentList_entity = dictService
//								.getEdgepointTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.EdgepointTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 进出点全部进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getEdgepointTypeDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.EdgepointTypeDict> agentList_entity = dictService
//								.getEdgepointTypeDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.EdgepointTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 采样点类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSamplepoinTypeDict(RoutingContext routingContext) {
//		String code = routingContext.request().getParam("code").trim();
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SamplepoinTypeDict> agentList_entity = dictService
//								.getSamplepoinTypeDict(code);
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SamplepoinTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
//	/**
//	 * 全部采样点类型
//	 */
//	@SuppressWarnings("unchecked")
//	public void getSamplepoinTypeDictFind(RoutingContext routingContext) {
//		Vertx vertx = routingContext.vertx();
//		vertx.executeBlocking(
//				future -> {
//					try {
//						List<com.pcitc.fms.bll.entity.SamplepoinTypeDict> agentList_entity = dictService
//								.getSamplepoinTypeDictFind();
//						future.complete(agentList_entity);
//					} catch (Exception e) {
//						future.fail(e);
//					}
//				},
//				res -> {
//					String collecion = null;
//					if (res.failed()) {
//						collecion = buildErrorCollection(routingContext,
//								(BusiException) res.cause());
//						returnCollection(routingContext, collecion);
//					} else if (res.succeeded()) {
//						try {
//							List<SiloNodeTypeDict> listApp = ObjectConverter
//									.listConverter(
//											(List<com.pcitc.fms.bll.entity.SamplepoinTypeDict>) res
//											.result(),
//											SiloNodeTypeDict.class);
//							collecion = RestfulTool.buildCollection(listApp,
//									routingContext.request().absoluteURI(),
//									SiloNodeTypeDict.class);
//							returnCollection(routingContext, collecion);
//						} catch (Exception e) {
//							collecion = buildErrorCollection(routingContext, e);
//							returnCollection(routingContext, collecion);
//						}
//					}
//				});
//	}
}
