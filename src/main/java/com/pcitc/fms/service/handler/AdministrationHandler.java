package com.pcitc.fms.service.handler;

import cc.aicode.e2e.ExcelHelper;
import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Community;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PipeNetwork;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * 办公区handler
 *
 * @author HanXiao  2017.11.20
 */
@Controller
public class AdministrationHandler extends BaseHandler {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(AdministrationHandler.class);
	/**
	 * The Administration service.
	 */
	@Autowired
	private AdministrationService administrationService;
	/**
	 * The Produce factory service.
	 */
	@Autowired
	private ProduceFactoryService produceFactoryService;
	/**
	 * The T pm org dao.
	 */
	@Autowired
	private TPmOrgDao tPmOrgDao;
	/**
	 * The Area dict service.
	 */
	@Autowired
	private AreaDictGatherService areaDictService;
	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	
	@Autowired
	private CheckType checkType;
	/**
	 * Gets administrations.
	 *
	 * @param routingContext the routing context
	 */
	@SuppressWarnings("unchecked")
	public void getAdministrations(RoutingContext routingContext) {

		HttpServerRequest request = routingContext.request();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				List<com.pcitc.fms.bll.entity.Administration> administrationEntity = new ArrayList<>();
				try {
					Administration administrationModel = QueryParams.getQueryParams(routingContext, Administration.class);
					Pageable pageable = null;
					if (administrationModel.getTop()!=null && administrationModel.getSkip()==null) {
						administrationModel.setSkip(0);
					}
					if (administrationModel.getSkip() != null && administrationModel.getTop() != null){
						pageable = PageUtil.pageable(administrationModel.getTop(),administrationModel.getSkip(), null);
					}
					if(administrationModel.getOrgCode()!=null){
						checkType.checkOrg(administrationModel.getOrgCode());
					}
					//开始查询
					Pager<com.pcitc.fms.bll.entity.Administration> sourceData = administrationService.getPageAdministrations(administrationModel,pageable);
					
					if (routingContext.request().uri().contains("skip") && administrationModel.getSkip() != null
							&& administrationModel.getTop() == null) {
						if (administrationModel.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<administrationModel.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					administrationEntity = sourceData.getContent();
					List<Administration> modelAdministrations = ObjectConverter.listConverter(administrationEntity, Administration.class);
					future.complete(modelAdministrations);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Administration> targets = (List<Administration>) res.result();
					try {
						String linkNames = "samplePoints,valves,equipments,tees,plates,tubulations";
	   					targets = (List<Administration>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());

						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Administration.class);
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
