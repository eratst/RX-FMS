package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.bll.itf.ValveService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Valve;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * Title: ValveHandler Description:阀门
 * 
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@Controller
public class ValveHandler extends BaseHandler {
	@Autowired
	private ValveService valveService;

	@Autowired
	private CheckType checkType;
	
	@Autowired
	private TPmOrgService tPmOrgService;


	/**
	 * @Title: getValves
	 * @Description: 查询多个
	 * @date 2017年6月14日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getValves(RoutingContext routingContext) {
		tPmOrgService.getAllTrees(1L);
		HttpServerRequest request = routingContext.request();
		try {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Valve valveModel = QueryParams.getQueryParams(routingContext, Valve.class);
					Pageable pageable = null;
					if (valveModel.getTop() != null && valveModel.getSkip() == null) {
						 valveModel.setSkip(0);
					}
					if (valveModel.getTop() != null && valveModel.getSkip() != null) {
						pageable = PageUtil.pageable(valveModel.getTop(),valveModel.getSkip(), null);
					}

					if (valveModel.getOrgCode() != null && valveModel.getAreaCode() != null
							&& valveModel.getAreaTypeCode().equals("areas")) {
						checkType.checkOrgAndArea(valveModel.getOrgCode(), valveModel.getAreaCode());
					}
					if (valveModel.getOrgCode() != null && valveModel.getAreaCode() != null
							&& !valveModel.getAreaTypeCode().equals("areas") && valveModel.getAreaTypeCode() != null) {
						checkType.checkOrgAndType(valveModel.getOrgCode(), valveModel.getAreaTypeCode(),
								valveModel.getAreaCode(), request);
					}

					List<com.pcitc.fms.bll.entity.Valve> EntityList = new ArrayList<>();

					Pager<com.pcitc.fms.bll.entity.Valve> sourceData = valveService.getValves(valveModel, pageable);
					if (routingContext.request().uri().contains("skip") && valveModel.getSkip() != null
							&& valveModel.getTop() == null) {
						if (valveModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < valveModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
						}
					}

					PageUtil.mergePage(pagination, sourceData);
					EntityList = sourceData.getContent();
					List<Valve> teeM = ObjectConverter.listConverter(EntityList, Valve.class);
					future.complete(teeM);
				} catch (Exception e) {
					future.fail(e);
				}

			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Valve> targets = (List<Valve>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<Valve>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.Valve valve : targets) {
	   						valve.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Valve.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					routingContext.request().uri());
			returnCollection(routingContext, collection);
		}
	}
}
