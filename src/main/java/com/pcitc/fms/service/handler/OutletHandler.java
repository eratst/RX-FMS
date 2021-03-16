package com.pcitc.fms.service.handler;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.NodeDictionary;
//import com.pcitc.fms.bll.entity.Outlet;
import com.pcitc.fms.bll.itf.OutletService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.ExcelUtils;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.EdgePoint;
import com.pcitc.fms.service.model.Outlet;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SamplePoint;
import com.pcitc.fms.service.model.Valve;
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
public class OutletHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(OutletHandler.class);
	@Autowired
	private OutletService outletService;
	@Autowired
	private CheckType checkType;

	@SuppressWarnings("unchecked")
	public void getOutlets(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();

		try {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					Outlet outletModel = QueryParams.getQueryParams(routingContext, Outlet.class);
					Pageable pageable = null;
					if (outletModel.getTop() != null && outletModel.getSkip() == null) {
						outletModel.setSkip(0);
					}
					if (outletModel.getTop() != null && outletModel.getSkip() != null) {
						pageable =  PageUtil.pageable(outletModel.getTop(),outletModel.getSkip(), null);
					}

					if (outletModel.getOrgCode() != null && outletModel.getAreaCode() != null
							&& outletModel.getAreaTypeCode().equals("areas")) {
						checkType.checkOrgAndArea(outletModel.getOrgCode(), outletModel.getAreaCode());
					}
					if (outletModel.getOrgCode() != null && outletModel.getAreaCode() != null
							&& !outletModel.getAreaTypeCode().equals("areas") && outletModel.getAreaTypeCode() != null) {
						checkType.checkOrgAndType(outletModel.getOrgCode(), outletModel.getAreaTypeCode(),
								outletModel.getAreaCode(), request);
					}

					List<com.pcitc.fms.bll.entity.Outlet> EntityList = new ArrayList<>();

					Pager<com.pcitc.fms.bll.entity.Outlet> sourceData = outletService.getPageOutlets(outletModel, pageable);
					if (routingContext.request().uri().contains("skip") && outletModel.getSkip() != null
							&& outletModel.getTop() == null) {
						if (outletModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < outletModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
						}
					}

					PageUtil.mergePage(pagination, sourceData);
					EntityList = sourceData.getContent();
					List<Outlet> teeM = ObjectConverter.listConverter(EntityList, Outlet.class);
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
					List<Outlet> targets = (List<Outlet>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<Outlet>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (Outlet outlet : targets) {
	   						outlet.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Outlet.class);
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
