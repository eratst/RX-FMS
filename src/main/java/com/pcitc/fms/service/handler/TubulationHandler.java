package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.bll.itf.TubulationService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Outlet;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Tubulation;
import com.pcitc.fms.service.model.TubulationModelStr;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/*
 * 
*<p>Title: TubulationHandler<p>
*<p>Description:管段handler </p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年7月12日
 */
@Controller
public class TubulationHandler extends BaseHandler{
	private static Logger log = LoggerFactory.getLogger(PipeNetworkHandler.class);
	@Autowired
	private TubulationService tubulationService;
	@Autowired
	private CheckType checkType;
	
	
	@SuppressWarnings("unchecked")
	public void getTubulations(RoutingContext routingContext){
	    HttpServerRequest request = routingContext.request();
        try{
            Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future ->{
				try{
					Tubulation tubaModel = QueryParams.getQueryParams(routingContext, Tubulation.class);
					Pageable pageable = null;
					if (tubaModel.getTop() != null && tubaModel.getSkip() == null) {
						tubaModel.setSkip(0);
					}
					if (tubaModel.getTop() != null && tubaModel.getSkip() != null) {
						pageable = PageUtil.pageable(tubaModel.getTop(),tubaModel.getSkip(), null);
					}

					if (tubaModel.getOrgCode() != null && tubaModel.getAreaCode() != null
							&& tubaModel.getAreaTypeCode().equals("areas")) {
						checkType.checkOrgAndArea(tubaModel.getOrgCode(), tubaModel.getAreaCode());
					}
					if (tubaModel.getOrgCode() != null && tubaModel.getAreaCode() != null
							&& !tubaModel.getAreaTypeCode().equals("areas") && tubaModel.getAreaTypeCode() != null) {
						checkType.checkOrgAndType(tubaModel.getOrgCode(), tubaModel.getAreaTypeCode(),
								tubaModel.getAreaCode(), request);
					}

					List<com.pcitc.fms.bll.entity.Tubulation> EntityList = new ArrayList<>();

					Pager<com.pcitc.fms.bll.entity.Tubulation> sourceData = tubulationService.getPageTubulations(tubaModel, pageable);
					if (routingContext.request().uri().contains("skip") && tubaModel.getSkip() != null
							&& tubaModel.getTop() == null) {
						if (tubaModel.getSkip() >= sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i = 0; i < tubaModel.getSkip(); i++) {
								sourceData.getContent().remove(0);
							}
						}
					}

					PageUtil.mergePage(pagination, sourceData);
					EntityList = sourceData.getContent();
					List<Tubulation> teeM = ObjectConverter.listConverter(EntityList, Tubulation.class);
					future.complete(teeM);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res ->{
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				} else if (res.succeeded()) {
					List<Tubulation> targets = (List<Tubulation>) res.result();
					try {
						String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
						String baseUri = "/FactoryModelService/nodeDictionaries";
	   					targets = (List<Tubulation>) setModelEntityWithLinks(targets,linkNames,baseUri);
	   					for (com.pcitc.fms.service.model.Tubulation tubulation : targets) {
	   						tubulation.setHref(routingContext.request().uri());
	   					}
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								Tubulation.class);
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
	
}
