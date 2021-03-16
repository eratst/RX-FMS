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
import com.pcitc.fms.bll.itf.PlateService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Outlet;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plate;
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

/**
 * Title: PlateHandler Description:盲板
 * 
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@Controller
public class PlateHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(PlateHandler.class);
	@Autowired
	private PlateService plateService;

	@Autowired
	private CheckType checkType;

	/**
	 * @Title: getPlates
	 * @Description: TODO
	 * @date 2017年6月16日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getPlates(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();

		try {
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination();
			vertx.executeBlocking(future -> {

				try {
					Plate plateModel = QueryParams.getQueryParams(routingContext, Plate.class);
					Pageable pageable = null;
					if (plateModel.getTop() != null && plateModel.getSkip() == null) {
						plateModel.setSkip(0);
					}
					if (plateModel.getTop() != null && plateModel.getSkip() != null) {
						pageable = PageUtil.pageable(plateModel.getTop(),plateModel.getSkip(), null);
					}

					if (plateModel.getOrgCode() != null && plateModel.getAreaCode() != null
							&& plateModel.getAreaTypeCode().equals("areas")) {
						checkType.checkOrgAndArea(plateModel.getOrgCode(), plateModel.getAreaCode());
					}
					if (plateModel.getOrgCode() != null && plateModel.getAreaCode() != null
							&& !plateModel.getAreaTypeCode().equals("areas") && plateModel.getAreaTypeCode() != null) {
						checkType.checkOrgAndType(plateModel.getOrgCode(), plateModel.getAreaTypeCode(),
								plateModel.getAreaCode(), request);
					}

					Pager<com.pcitc.fms.bll.entity.Plate> sourceData = plateService.getPlates(plateModel, pageable);
					if (routingContext.request().uri().contains("skip") && plateModel.getSkip() != null
							&& plateModel.getTop() == null) {
						if (plateModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < plateModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);

					List<com.pcitc.fms.bll.entity.Plate> enList = sourceData.getContent();
					List<Plate> plateMo = ObjectConverter.listConverter(enList, Plate.class);
					future.complete(plateMo);
				} catch (Exception e) {
					future.fail(e);
				}

			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Plate> targets = (List<com.pcitc.fms.service.model.Plate>) res
							.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<com.pcitc.fms.service.model.Plate>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.Plate plate : targets) {
	   						plate.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.Plate.class);
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
