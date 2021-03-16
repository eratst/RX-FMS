package com.pcitc.fms.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.CacheRentService;
import com.pcitc.fms.common.CacheRentAndBizInfo;
import com.pcitc.fms.common.CacheRentInfo;

import io.vertx.ext.web.RoutingContext;

@Controller
public class CacheRentHandler extends BaseHandler{
	
	@Autowired
	private CacheRentService cacheRentService;
	
	public void execute() {
		cacheRentService.getCacheInfo();
	}
	
	public void refreshCache(RoutingContext routingContext){
		CacheRentInfo.remove();
		cacheRentService.getCacheInfo();
		routingContext.response().end();
	}
	
//	public void getCacheInfoForBiz(){
//		cacheRentService.getCacheInfoForBiz();
//	}
	
	public void refreshCacheBiz(RoutingContext routingContext){
		CacheRentInfo.remove();
		cacheRentService.getCacheInfo();
//		CacheRentAndBizInfo.remove();
//		cacheRentService.getCacheInfoForBiz();
		routingContext.response().end();
	}

}
