package com.pcitc.fms.service.handler;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.itf.EquipmentService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Equipment;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Tank;
import com.pcitc.imp.common.model.Pagination;

import cc.aicode.e2e.ExcelHelper;

@Controller
public class EquipmentHandler extends BaseHandler {

    private static Logger log = LoggerFactory.getLogger(EquipmentHandler.class);
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CheckType checkType;

    @SuppressWarnings("unchecked")
    public void getEquipments(RoutingContext routingContext) {
	HttpServerRequest request = routingContext.request();
	Pagination pagination = new Pagination();
	try {
	    Vertx vertx = routingContext.vertx();
	    vertx.executeBlocking(future -> {
		List<com.pcitc.fms.bll.entity.Equipment> equipmentEntity = new ArrayList<>();
		try {
			Equipment EquipmentModel = QueryParams.getQueryParams(routingContext, Equipment.class);
			Pageable pageable = null;
			if (EquipmentModel.getTop()!=null && EquipmentModel.getSkip()==null) {
				EquipmentModel.setSkip(0);
			}
			if (EquipmentModel.getSkip() != null && EquipmentModel.getTop() != null){
				pageable =  PageUtil.pageable(EquipmentModel.getTop(),EquipmentModel.getSkip(), null);
			}
			//层级校验
			if(EquipmentModel.getOrgCode() !=null && EquipmentModel.getAreaCode() != null && EquipmentModel.getAreaTypeCode().equals("areas")){
				checkType.checkOrgAndArea(EquipmentModel.getOrgCode(),EquipmentModel.getAreaCode());
			}
			if(EquipmentModel.getOrgCode() !=null && EquipmentModel.getAreaCode() != null && !EquipmentModel.getAreaTypeCode().equals("areas") && EquipmentModel.getAreaTypeCode()!=null){
				checkType.checkOrgAndType(EquipmentModel.getOrgCode(),EquipmentModel.getAreaTypeCode(),EquipmentModel.getAreaCode(),request);
			}
		    Pager<com.pcitc.fms.bll.entity.Equipment> sourceData = equipmentService.getPageEquipments(EquipmentModel, pageable);
		    
		    if (routingContext.request().uri().contains("skip") && EquipmentModel.getSkip() != null
					&& EquipmentModel.getTop() == null) {
				if (EquipmentModel.getSkip()>=sourceData.getContent().size()) {
					sourceData.setContent(null);
				} else {
					for (int i=0;i<EquipmentModel.getSkip();i++) {
						sourceData.getContent().remove(0);
					}
				}
			}
		    PageUtil.mergePage(pagination, sourceData);
		    equipmentEntity = sourceData.getContent();
			List<Equipment> modelEquipments = ObjectConverter.listConverter(equipmentEntity, Equipment.class);
		    future.complete(modelEquipments);
		} catch (Exception e) {
		    future.fail(e);
		}
	    }, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
				returnCollection(routingContext, collection);

			} else if (res.succeeded()) {
				List<Equipment> targets = (List<Equipment>) res.result();
				try {
					String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
					String baseUri = "/FactoryModelService/nodeDictionaries";
   					targets = (List<Equipment>) setModelEntityWithLinks(targets,linkNames,baseUri);

   					for (Equipment equipment : targets) {
   						equipment.setHref(routingContext.request().uri());
   					}
   					
					collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
							Equipment.class);
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
