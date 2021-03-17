package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.RentService;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Rent;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class RentHandler extends BaseHandler{
	@Autowired
	private RentService rentService;
	
	public void getRents(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					Rent rent = QueryParams.getQueryParams(routingContext, Rent.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.Rent> sourceData;
					if (null != rent.getSkip()&& null!=rent.getTop()) {
						pageable = PageUtil.pageable(rent.getTop(),rent.getSkip(),null);
					} 
					sourceData = rentService.getRents(rent, pageable);
					
					if (routingContext.request().uri().contains("skip") && rent.getSkip() != null
							&& rent.getTop() == null) {
						if (rent.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<rent.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<Rent> modelRents = ObjectConverter.listConverter(sourceData.getContent(), Rent.class);
					future.complete(modelRents);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					try {
						collection = RestfulTool.buildCollection((List<Rent>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.Rent.class);
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
				}
			});
		} catch (Exception e) {
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
	@SuppressWarnings("unchecked")
	public void addRents(RoutingContext routingContext){

		String collectionParam = routingContext.getBodyAsString();
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				List<Rent> modelList = RestfulTool.toResourceRep(collectionParam, Rent.class);
				modelList = CurrencyCheck.checkObject(modelList, Operation.ADD);
				List<com.pcitc.fms.bll.entity.Rent> entityList = new ArrayList<>();
				entityList = ObjectConverter.listConverter(modelList, com.pcitc.fms.bll.entity.Rent.class);
				rentService.addRent(entityList);
				future.complete(entityList.size()+"");
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = (String) res.result();
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}
	 public void deleteByCode(RoutingContext routingContext) {
		   String rentCode = routingContext.request().getParam("rentCode");
		   Vertx vertx = routingContext.vertx();
		   vertx.executeBlocking(future ->{
			   try {
				   rentService.deleteByRentCode(rentCode);
				future.complete("");
			} catch (Exception e) {
				future.fail(e);	
			}
		   }, res ->{
			   String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				} else if (res.succeeded()) {
					try {
						collection = (String) res.result();
					} catch (Exception e) {
						collection = buildErrorCollection(routingContext, e);
					}
				}
				returnCollection(routingContext, collection);
		   });
	   }
	
}
