package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.StockService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Silo;
import com.pcitc.fms.service.model.Stock;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class StockHandler extends BaseHandler {

	@Autowired
	private StockService stockService;
	@Autowired
	private CheckType checkType;

	@SuppressWarnings("unchecked")
	public void getStocks(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		try {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Stock sModel = QueryParams.getQueryParams(routingContext, Stock.class);
					Pageable pageable = null;
					if (sModel.getTop() != null && sModel.getSkip() == null) {
						sModel.setSkip(0);
					}
					if (sModel.getTop() != null && sModel.getSkip() != null) {
						pageable = PageUtil.pageable(sModel.getTop(),sModel.getSkip(), null);
					}

					if (sModel.getOrgCode() != null && sModel.getAreaCode() != null
							&& sModel.getAreaTypeCode().equals("areas")) {
						checkType.checkOrgAndArea(sModel.getOrgCode(), sModel.getAreaCode());
					}
					if (sModel.getOrgCode() != null && sModel.getAreaCode() != null
							&& !sModel.getAreaTypeCode().equals("areas") && sModel.getAreaTypeCode() != null) {
						checkType.checkOrgAndType(sModel.getOrgCode(), sModel.getAreaTypeCode(),
								sModel.getAreaCode(), request);
					}

					List<com.pcitc.fms.bll.entity.Stock> EntityList = new ArrayList<>();
 
 					Pager<com.pcitc.fms.bll.entity.Stock> sourceData = stockService.getStocks(sModel, pageable);
					if (routingContext.request().uri().contains("skip") && sModel.getSkip() != null
							&& sModel.getTop() == null) {
						if (sModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < sModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
						}
					}

					PageUtil.mergePage(pagination, sourceData);
					EntityList = sourceData.getContent();
					List<Stock> teeM = ObjectConverter.listConverter(EntityList, Stock.class);
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
					List<Stock> targets = (List<Stock>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<Stock>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.Stock stock : targets) {
	   						stock.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Stock.class);
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
