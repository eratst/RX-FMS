package com.pcitc.fms.service.handler;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.entity.Factory;
import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.bll.itf.WarehouseService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.LoadingDock;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PipeNetworkStr;
import com.pcitc.fms.service.model.TankArea;
import com.pcitc.fms.service.model.Warehouse;
import com.pcitc.fms.service.model.WarehouseStr;
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
 * 仓库
 * @author hehe
 * @author hanxiao
 *
 */
@Controller
public class WarehouseHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(WarehouseHandler.class);
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private ProduceFactoryService produceFactoryService;
	@Autowired
	private TPmOrgDao tPmOrgDao;
	@Autowired
	private AreaDictGatherService areaDictService;
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	@Autowired
	private CheckType checkType;
	
	public void getWarehouses(RoutingContext routingContext) {


		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Warehouse> warehouseEntity = new ArrayList<>();
				try {
					Warehouse warehouseModel = QueryParams.getQueryParams(routingContext, Warehouse.class);
					Pageable pageable = null;
					if (warehouseModel.getTop()!=null && warehouseModel.getSkip()==null) {
						warehouseModel.setSkip(0);
					}
					if (warehouseModel.getSkip() != null && warehouseModel.getTop() != null){
						pageable = PageUtil.pageable(warehouseModel.getTop(),warehouseModel.getSkip(), null);
					}
					if(warehouseModel.getOrgCode()!=null){
						checkType.checkOrg(warehouseModel.getOrgCode());
					}
					//开始查询
					Pager<com.pcitc.fms.bll.entity.Warehouse> sourceData = warehouseService.getPageWarehouses(warehouseModel, pageable);
					
					if (routingContext.request().uri().contains("skip") && warehouseModel.getSkip() != null
							&& warehouseModel.getTop() == null) {
						if (warehouseModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<warehouseModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					warehouseEntity = sourceData.getContent();
					List<Warehouse> modelWarehouses = ObjectConverter.listConverter(warehouseEntity, Warehouse.class);
					future.complete(modelWarehouses);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Warehouse> targets = (List<Warehouse>) res.result();
					try {
						String linkNames = "samplePoints,outlets,valves,equipments,tees,tubulations,plates,stocks";
	   					targets = (List<Warehouse>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());

						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Warehouse.class);
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

