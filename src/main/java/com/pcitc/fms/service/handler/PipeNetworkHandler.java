package com.pcitc.fms.service.handler;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PipeNetwork;
import com.pcitc.fms.service.model.PipeNetworkStr;
import com.pcitc.fms.service.model.Plant;
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
/*
 * 
*<p>Title: PipeNetworkHandler<p>
*<p>Description: 管网handler</p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年7月12日
 */
@Controller
public class PipeNetworkHandler extends BaseHandler {

	@Autowired
	private PipeNetworkService pipeNetworkService;
	
	@Autowired
	private CheckType checkType;
	
	@SuppressWarnings("unchecked")
	public void getPipeNetworkes(RoutingContext routingContext){

		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.PipeNetwork> pipeNetworkEntity = new ArrayList<>();
				try {
					PipeNetwork pipeNetworkModel = QueryParams.getQueryParams(routingContext, PipeNetwork.class);
					Pageable pageable = null;
					if (pipeNetworkModel.getTop()!=null && pipeNetworkModel.getSkip()==null) {
						pipeNetworkModel.setSkip(0);
					}
					if (pipeNetworkModel.getSkip() != null && pipeNetworkModel.getTop() != null){
						pageable =  PageUtil.pageable(pipeNetworkModel.getTop(),pipeNetworkModel.getSkip(), null);
					}
					if(pipeNetworkModel.getOrgCode()!=null){
						checkType.checkOrg(pipeNetworkModel.getOrgCode());
					}
					//开始查询
					Pager<com.pcitc.fms.bll.entity.PipeNetwork> sourceData =pipeNetworkService.getPipeNetworks(pipeNetworkModel, pageable);
					
					if (routingContext.request().uri().contains("skip") && pipeNetworkModel.getSkip() != null
							&& pipeNetworkModel.getTop() == null) {
						if (pipeNetworkModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<pipeNetworkModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					pipeNetworkEntity = sourceData.getContent();
					List<PipeNetwork> modelPipeNetworks = ObjectConverter.listConverter(pipeNetworkEntity, PipeNetwork.class);
					future.complete(modelPipeNetworks);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<PipeNetwork> targets = (List<PipeNetwork>) res.result();
					try {
						String linkNames = "samplePoints,outlets,tees,plates,tubulations";
	   					targets = (List<PipeNetwork>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());

						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								PipeNetwork.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
			returnCollection(routingContext, collection);
		}

	
	}
	
}
