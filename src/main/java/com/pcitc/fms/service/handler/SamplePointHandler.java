package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.SamplePointService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SamplePoint;
import com.pcitc.fms.service.model.Stock;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class SamplePointHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(SamplePointHandler.class);
	@Autowired
	private SamplePointService samplePointService;
	@Autowired
	private CheckType checkType;
	@Autowired
	private FactoryDictService dictService;
	
	@Autowired
	private Environment env;

	@SuppressWarnings("unchecked")
	public void getSamplePoints(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		try {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.bll.entity.SamplePoint> EntityList = new ArrayList<>();
					EntityList = getData(routingContext,request,pagination);
					List<SamplePoint> teeM = ObjectConverter.listConverter(EntityList, SamplePoint.class);
					future.complete(teeM);
				} catch (Exception e) {
					future.fail(e);
				}
		
			},false, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<SamplePoint> targets = (List<SamplePoint>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<SamplePoint>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.SamplePoint samplePoint : targets) {
	   						samplePoint.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								SamplePoint.class);
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

	private List<com.pcitc.fms.bll.entity.SamplePoint> getData(RoutingContext routingContext, HttpServerRequest request,
			Pagination pagination) throws Exception, BusinessException {
		List<com.pcitc.fms.bll.entity.SamplePoint> EntityList;
		SamplePoint sModel = QueryParams.getQueryParams(routingContext, SamplePoint.class);
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

		

		Pager<com.pcitc.fms.bll.entity.SamplePoint> sourceData = samplePointService.getPageSamplePoints(sModel, pageable);
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
		return EntityList;
	}

}
