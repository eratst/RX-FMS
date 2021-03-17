package com.pcitc.fms.service.handler;

import java.io.File;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;









import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;









import com.pcitc.fms.bll.entity.EntityMeta;
import com.pcitc.fms.bll.entity.Factory;
import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.ExcelUtils;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.TankDao;
import com.pcitc.fms.dal.pojo.PropertyMeta;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.DictionaryTable;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plant;
import com.pcitc.fms.service.model.TankArea;
import com.pcitc.fms.service.model.TankAreaModelStr;
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
 * 罐区handler
 * @author hanxiao
 *
 */
@Controller
public class TankAreaHandler extends BaseHandler {
	
	@Autowired
	private TankAreaService tankAreaService;
	
	@Autowired
	private CheckType checkType;
	
	@Autowired
	private Environment env;
	
	public void getTankAreas(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.TankArea> tankAreaEntity = new ArrayList<>();
				try {
					tankAreaEntity = getData(routingContext, pagination);
					List<TankArea> modelTankAreas = ObjectConverter.listConverter(tankAreaEntity, TankArea.class);
					future.complete(modelTankAreas);
				} catch (Exception e) {
					future.fail(e);
				}
			},false,res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<TankArea> targets = (List<TankArea>) res.result();
					try {
						String linkNames = "tanks,samplePoints,outlets,valves,equipments,tees,tubulations,plates";
	   					targets = (List<TankArea>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
		   	
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								TankArea.class);
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

	private List<com.pcitc.fms.bll.entity.TankArea> getData(RoutingContext routingContext, Pagination pagination)
			throws Exception, BusinessException, BusiException {
		List<com.pcitc.fms.bll.entity.TankArea> tankAreaEntity;
		TankArea tankAreaModel = QueryParams.getQueryParams(routingContext, TankArea.class);
		Pageable pageable = null;
		if (tankAreaModel.getTop()!=null && tankAreaModel.getSkip()==null) {
			tankAreaModel.setSkip(0);
		}
		if (tankAreaModel.getSkip() != null && tankAreaModel.getTop() != null){
			pageable = PageUtil.pageable(tankAreaModel.getTop(),tankAreaModel.getSkip(), null);
		}
		if(tankAreaModel.getOrgCode()!=null){
			checkType.checkOrg(tankAreaModel.getOrgCode());
		}
		Pager<com.pcitc.fms.bll.entity.TankArea> sourceData = tankAreaService.getTankAreas(tankAreaModel, pageable);
		if (routingContext.request().uri().contains("skip") && tankAreaModel.getSkip() != null
				&& tankAreaModel.getTop() == null) {
			if (tankAreaModel.getSkip()>=sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i=0;i<tankAreaModel.getSkip();i++) {
					sourceData.getContent().remove(0);
				}
			}
		}
		PageUtil.mergePage(pagination, sourceData);
		tankAreaEntity = sourceData.getContent();
		return tankAreaEntity;
	}

}
