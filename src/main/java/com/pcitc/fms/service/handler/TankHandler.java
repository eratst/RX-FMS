package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import com.pcitc.fms.bll.entity.TankArea;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.TankService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.NewTank;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SideLine;
import com.pcitc.fms.service.model.Tank;
import com.pcitc.imp.common.exception.BusiException;
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
 * 罐handler
 * @author he.yang
 *
 */
@Controller
public class TankHandler extends BaseHandler {
	@Autowired
	private TankService tankService;
	@Autowired
	private CheckType checkType;
	@Autowired
	private Environment env;
	
	@SuppressWarnings("unchecked")
	public void getTanks(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Tank> tankEntity = new ArrayList<>();
				try {
					tankEntity = getData(routingContext, request, pagination);
					List<Tank> modelTanks = ObjectConverter.listConverter(tankEntity, Tank.class);
					future.complete(modelTanks);
				} catch (Exception e) {
					future.fail(e);
				}
			},false,res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Tank> targets = (List<Tank>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<Tank>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.Tank tank : targets) {
	   						tank.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Tank.class);
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

	private List<com.pcitc.fms.bll.entity.Tank> getData(RoutingContext routingContext, HttpServerRequest request,
			Pagination pagination) throws Exception, BusinessException {
		List<com.pcitc.fms.bll.entity.Tank> tankEntity;
		Tank tankModel = QueryParams.getQueryParams(routingContext, Tank.class);
		Pageable pageable = null;
		if (tankModel.getTop()!=null && tankModel.getSkip()==null) {
			tankModel.setSkip(0);
		}
		if (tankModel.getSkip() != null && tankModel.getTop() != null){
			pageable = PageUtil.pageable(tankModel.getTop(),tankModel.getSkip(), null);
		}
		//层级校验
		if(tankModel.getOrgCode() !=null && tankModel.getAreaCode() != null && tankModel.getAreaTypeCode().equals("areas")){
			checkType.checkOrgAndArea(tankModel.getOrgCode(),tankModel.getAreaCode());
		}
		if(tankModel.getOrgCode() !=null && tankModel.getAreaCode() != null && !tankModel.getAreaTypeCode().equals("areas") && tankModel.getAreaTypeCode()!=null){
			checkType.checkOrgAndType(tankModel.getOrgCode(),tankModel.getAreaTypeCode(),tankModel.getAreaCode(),request);
		}
		Pager<com.pcitc.fms.bll.entity.Tank> sourceData = tankService.getPageTanks(tankModel,pageable);
		
		if (routingContext.request().uri().contains("skip") && tankModel.getSkip() != null
				&& tankModel.getTop() == null) {
			if (tankModel.getSkip()>=sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i=0;i<tankModel.getSkip();i++) {
					sourceData.getContent().remove(0);
				}
			}
		}
		PageUtil.mergePage(pagination, sourceData);
		tankEntity = sourceData.getContent();
		return tankEntity;
	}

}
