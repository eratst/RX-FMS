package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.NodeDictGatherService;
import com.pcitc.fms.service.dictionary.model.EquTechnic;
import com.pcitc.fms.service.dictionary.model.IdxType;
import com.pcitc.fms.service.dictionary.model.MtrlType;
import com.pcitc.fms.service.dictionary.model.NodeType;
import com.pcitc.fms.service.dictionary.model.SamplepointType;
import com.pcitc.fms.service.dictionary.model.SideMtrlType;
import com.pcitc.fms.service.dictionary.model.SidelineType;
import com.pcitc.fms.service.dictionary.model.SignboardType;
import com.pcitc.fms.service.dictionary.model.SiloType;
import com.pcitc.fms.service.dictionary.model.TankType;
import com.pcitc.fms.service.dictionary.model.TransType;
import com.pcitc.fms.service.dictionary.model.VcfType;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class NodeDictGatherHandler extends BaseHandler{
	@Autowired
	private NodeDictGatherService nodeDictGatherService;
	/**
	 * 标识牌类型类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getMtrlTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.MtrlType> dictlist_entity = nodeDictGatherService.getMtrlTypes(code);
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
							List<MtrlType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.MtrlType>) res.result(),MtrlType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),MtrlType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 标识牌类型类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getIdxTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.IdxType> dictlist_entity = nodeDictGatherService.getIdxTypes(code);
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
							List<IdxType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.IdxType>) res.result(),IdxType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),IdxType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 标识牌类型类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getSignboardTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.SignboardType> dictlist_entity = nodeDictGatherService.getSignboardTypes(code);
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
							List<SignboardType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.SignboardType>) res.result(),SignboardType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),SignboardType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 采样点业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getSamplepointTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.SamplepointType> dictlist_entity = nodeDictGatherService.getSamplepointTypes(code);
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
							List<SamplepointType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.SamplepointType>) res.result(),SamplepointType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),SamplepointType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询料仓业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getSiloTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.SiloType> dictlist_entity = nodeDictGatherService.getSiloTypes(code);
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
							List<SiloType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.SiloType>) res.result(),SiloType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),SiloType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询进出厂运输类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getTransTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.TransType> dictlist_entity = nodeDictGatherService.getTransTypes(code);
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
							List<TransType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.TransType>) res.result(),TransType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),TransType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 设备工艺类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getEquTechnics(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.EquTechnic> dictlist_entity = nodeDictGatherService.getEquTechnics(code);
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
							List<EquTechnic> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.EquTechnic>) res.result(),EquTechnic.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),EquTechnic.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询VCF类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getVcfTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.VcfType> dictlist_entity = nodeDictGatherService.getVcfTypes(code);
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
							List<VcfType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.VcfType>) res.result(),VcfType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),VcfType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询罐类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getTankTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.TankType> dictlist_entity = nodeDictGatherService.getTankTypes(code);
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
							List<TankType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.TankType>) res.result(),TankType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),TankType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询侧线业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getSideMtrlTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.SideMtrlType> dictlist_entity = nodeDictGatherService.getSideMtrlTypes(code);
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
							List<SideMtrlType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.SideMtrlType>) res.result(),SideMtrlType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),SideMtrlType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 查询侧线业务类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getSidelineTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.SidelineType> dictlist_entity = nodeDictGatherService.getSidelineTypes(code);
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
							List<SidelineType> dicelist_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.SidelineType>) res.result(),SidelineType.class);
							collecion = RestfulTool.buildCollection(dicelist_model,routingContext.request().absoluteURI(),SidelineType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
	/**
	 * 节点类型
	 * @param routingContext
	 */
	@SuppressWarnings("unchecked")
	public void getNodeTypes(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String code = request.getParam("code") == null ? null : request.getParam("code").trim();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(
				future -> {
					try {
						List<com.pcitc.fms.bll.dictionary.entity.NodeType> areaTypes_entity = nodeDictGatherService.getNodeTypes(code);
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
							List<NodeType> orgTypes_model = ObjectConverter.listConverter((List<com.pcitc.fms.bll.dictionary.entity.NodeType>) res.result(),NodeType.class);
							collecion = RestfulTool.buildCollection(orgTypes_model,routingContext.request().absoluteURI(),NodeType.class);
							returnCollection(routingContext, collecion);
						} catch (Exception e) {
							collecion = buildErrorCollection(routingContext, e);
							returnCollection(routingContext, collecion);
						}
					}
				});
	}
}
