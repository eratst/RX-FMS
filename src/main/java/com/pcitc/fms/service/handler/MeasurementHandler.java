package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.MeasurementService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Measurement;
import com.pcitc.fms.service.model.MeasurementStr;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plate;
import com.pcitc.imp.common.exception.BusiException;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class MeasurementHandler extends BaseHandler {
	private static Logger log = LoggerFactory.getLogger(SideLineHandler.class);
	@Autowired
	private MeasurementService measurementService;
	
	@Autowired
	private CheckType checkType;
	
	@Autowired
	private Environment env;
	
	public void getMeasurements(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
	    try{
	    	Vertx vertx = routingContext.vertx();
	    	Pagination pagination = new Pagination();
	    	vertx.executeBlocking(future ->{
	    		try {
	    			List<com.pcitc.fms.bll.entity.Measurement> enList = new ArrayList<>();
					enList = getData(routingContext, pagination);
					List<Measurement> plateMo = ObjectConverter.listConverter(enList, Measurement.class);
					future.complete(plateMo);
				} catch (Exception e) {
					future.fail(e);
				}
	    	},false, res ->{
	    		String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Measurement> targets = (List<Measurement>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Measurement.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
	    }catch(Exception e){
	    	String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()),
					routingContext.request().uri());
			returnCollection(routingContext, collection);
	    }
	    
	}

	private List<com.pcitc.fms.bll.entity.Measurement> getData(RoutingContext routingContext, Pagination pagination)
			throws Exception, BusinessException {
		List<com.pcitc.fms.bll.entity.Measurement> enList;
		Measurement measurementModel = QueryParams.getQueryParams(routingContext, Measurement.class);
		Pageable pageable = null;
		if (measurementModel.getTop() != null && measurementModel.getSkip() == null) {
			measurementModel.setSkip(0);
		}
		if (measurementModel.getTop() != null && measurementModel.getSkip() != null) {
			pageable =  PageUtil.pageable(measurementModel.getTop(),measurementModel.getSkip(), null);
		}


		Pager<com.pcitc.fms.bll.entity.Measurement> sourceData = measurementService.getPageMeasurements(measurementModel, pageable);
		if (routingContext.request().uri().contains("skip") && measurementModel.getSkip() != null
				&& measurementModel.getTop() == null) {
			if (measurementModel.getSkip() >= sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i = 0; i < measurementModel.getSkip(); i++) {
					sourceData.getContent().remove(0);
				}
				sourceData.setTotalElements((long) sourceData.getContent().size());
			}
		}
		PageUtil.mergePage(pagination, sourceData);

		enList = sourceData.getContent();
		return enList;
	}
	
	
}

