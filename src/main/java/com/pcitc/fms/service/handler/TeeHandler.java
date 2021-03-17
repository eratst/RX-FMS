package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.TeeService;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/*
 * 
*<p>Title: TeeHandler<p>
*<p>Description:三通handler </p>
*<p>Company: </p>
*@author:HanXiao
*@date:2017年7月12日
 */
@Controller
public class TeeHandler extends BaseHandler{
	
	@Autowired
	private TeeService teeService;
	@Autowired
    private CheckType checkType;
	
	
	@SuppressWarnings("unchecked")
	public void getTees(RoutingContext routingContext){
			HttpServerRequest request = routingContext.request();
		    try{
		        Pagination pagination = new Pagination();
		    	Vertx vertx = routingContext.vertx();
		    	vertx.executeBlocking(future ->{
		    		try{
		    			com.pcitc.fms.service.model.Tee teeModel = QueryParams.getQueryParams(routingContext, com.pcitc.fms.service.model.Tee.class);
		    			 Pageable pageable = null;
		    			 if(teeModel.getTop() != null &&  teeModel.getSkip() == null) {
		    				 teeModel.setSkip(0);
		    			 }
		    			 if(teeModel.getTop() != null &&  teeModel.getSkip() != null) {
		    				 pageable = PageUtil.pageable(teeModel.getTop(),teeModel.getSkip(), null);
		    			 }
		    			 
		    			 if(teeModel.getOrgCode() != null && teeModel.getAreaCode() !=null && teeModel.getAreaTypeCode().equals("areas")) {
		    				 checkType.checkOrgAndArea(teeModel.getOrgCode(),teeModel.getAreaCode());
		    			 }
		    			 if(teeModel.getOrgCode() !=null && teeModel.getAreaCode() != null && !teeModel.getAreaTypeCode().equals("areas") && teeModel.getAreaTypeCode()!=null) {
		    				 checkType.checkOrgAndType(teeModel.getOrgCode(),teeModel.getAreaTypeCode(),teeModel.getAreaCode(),request);
		    			 }
		    			 
		    		    List<com.pcitc.fms.bll.entity.Tee> teeEntityList = new ArrayList<>();
		    		    
	    			    Pager<com.pcitc.fms.bll.entity.Tee>  sourceData = teeService.getTeesByModel(teeModel, pageable);
	    			    if (routingContext.request().uri().contains("skip") && teeModel.getSkip() != null
								&& teeModel.getTop() == null) {
							if (teeModel.getSkip()>=sourceData.getContent().size()) {
								sourceData.setContent(null);
							} else {
									for (int i=0;i<teeModel.getSkip();i++) {
										sourceData.getContent().remove(0);
									}
							}
						}
	    			    
	    			    
	    				PageUtil.mergePage(pagination, sourceData);
	    				teeEntityList = sourceData.getContent();
	    				List<com.pcitc.fms.service.model.Tee> teeM = ObjectConverter.listConverter(teeEntityList, com.pcitc.fms.service.model.Tee.class);
	    				future.complete(teeM);
		    		}catch(Exception e){
		    			future.fail(e);
		    		}
		    	}, res ->{
		    		String collection = null;
					if (res.failed()) {
						collection = buildErrorCollection(routingContext, (Exception) res.cause());
						returnCollection(routingContext, collection);

					} else if (res.succeeded()) {
						List<com.pcitc.fms.service.model.Tee> targets = (List<com.pcitc.fms.service.model.Tee>) res.result();
						try {
							String linkNames = "associatives,measurements,mapSampleNodes,nodetopDTL";
							String baseUri = "/FactoryModelService/nodeDictionaries";
		   					targets = (List<com.pcitc.fms.service.model.Tee>) setModelEntityWithLinks(targets,linkNames,baseUri);

							collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
									com.pcitc.fms.service.model.Tee.class);
						} catch (Exception e) {
							e.printStackTrace();
							returnCollection(routingContext, e.getMessage());
						}
						returnCollection(routingContext, collection);
					}
				
		    	});
		    }catch(Exception e){
				String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), routingContext.request().uri());
				returnCollection(routingContext, collection);
		    }
		    
		}
	
}
