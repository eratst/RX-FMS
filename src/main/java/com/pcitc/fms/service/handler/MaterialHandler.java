package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.pcitc.fms.bll.itf.MaterialService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.MaterialDaoImpl;
import com.pcitc.fms.service.model.Material;
import com.pcitc.fms.service.model.Measurement;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Plate;
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
 * 物料
 * @author he.yang 改
 *  @author zhaozhenqiang 在改
 *
 */
@Controller
public class MaterialHandler extends BaseHandler{

	private static Logger log = LoggerFactory.getLogger(MaterialHandler.class);
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private MaterialDaoImpl materialDaoImpl;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CheckType checkType;

	@SuppressWarnings("unchecked")
	public void getMaterials(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		try {
			
			Vertx vertx = routingContext.vertx();
			Pagination pagination = new Pagination(); 
			vertx.executeBlocking(future -> {
				try {
					List<com.pcitc.fms.bll.entity.Material> enList = new ArrayList<>();
					enList = getData(routingContext, hrefBase, pagination);
					List<Material> plateMo = ObjectConverter.listConverter(enList, Material.class);
					future.complete(plateMo);
				} catch (Exception e) {
					future.fail(e);
				}
			},false,res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Material> targets = (List<com.pcitc.fms.service.model.Material>) res
							.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								com.pcitc.fms.service.model.Material.class);
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

	private List<com.pcitc.fms.bll.entity.Material> getData(RoutingContext routingContext, String hrefBase,
			Pagination pagination) throws Exception, BusinessException {
		Material plateModel = QueryParams.getQueryParams(routingContext, Material.class);
		Pageable pageable = null;
		if (plateModel.getTop() != null && plateModel.getSkip() == null) {
			plateModel.setSkip(0);
		}
		if (plateModel.getTop() != null && plateModel.getSkip() != null) {
			pageable = PageUtil.pageable(plateModel.getTop(),plateModel.getSkip(), null);
		}
		Pager<com.pcitc.fms.bll.entity.Material> sourceData = new Pager();
		if(hrefBase.contains("operType")) {
			List<com.pcitc.fms.bll.entity.Material> list = materialService.getMaterialsByOperType(plateModel.getMtrlCode(),
					plateModel.getMtrlCodes(),plateModel.getOperType(),plateModel,pageable);
			sourceData.setContent(list);
			sourceData.setTotalElements(Long.valueOf(list.size()));
		}else{
			sourceData = materialService.getMaterials(plateModel, pageable);
		}
		
		if (routingContext.request().uri().contains("skip") && plateModel.getSkip() != null
				&& plateModel.getTop() == null) {
			if (plateModel.getSkip() >= sourceData.getContent().size()) {
				sourceData.setContent(null);
			} else {
				for (int i = 0; i < plateModel.getSkip(); i++) {
					sourceData.getContent().remove(0);
				}
				sourceData.setTotalElements((long) sourceData.getContent().size());
			}
		}
		PageUtil.mergePage(pagination, sourceData);

		List<com.pcitc.fms.bll.entity.Material> enList = sourceData.getContent();
		return enList;
	}
	


}
