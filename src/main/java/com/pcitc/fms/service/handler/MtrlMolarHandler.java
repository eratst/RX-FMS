package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.bll.itf.MtrlMolarService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.MtrlMolar;
import com.pcitc.fms.service.model.MtrlMolarDegree;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;



@Controller
public class MtrlMolarHandler extends BaseHandler{
	
	@Autowired
	private MtrlMolarService mtrlMolarService;
	
	
	public void getAll(RoutingContext routingContext){
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String uri = request.absoluteURI();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking((Future<Object> future) -> {
				try {
					MtrlMolar mtrlMolar = QueryParams.getQueryParams(routingContext, MtrlMolar.class);
					Pageable pageable = null;
					// 判断 是否分页
					Pager<com.pcitc.fms.bll.entity.MtrlMolar> sourceData;
					if (null != mtrlMolar.getSkip()&& null!=mtrlMolar.getTop()) {
						pageable = PageUtil.pageable(mtrlMolar.getTop(),mtrlMolar.getSkip(),null);
					} 
					sourceData = mtrlMolarService.getMtrlMolars(mtrlMolar, pageable);
					
					if (routingContext.request().uri().contains("skip") && mtrlMolar.getSkip() != null
							&& mtrlMolar.getTop() == null) {
						if (mtrlMolar.getSkip()>=sourceData.getContent().size()) {
							sourceData.setContent(null);
						} else {
							for (int i=0;i<mtrlMolar.getSkip();i++) {
								sourceData.getContent().remove(0);
							}
						}
					}
					PageUtil.mergePage(pagination, sourceData);
					List<MtrlMolar> modelMtrlMolars = ObjectConverter.listConverter(sourceData.getContent(), MtrlMolar.class);
					future.complete(modelMtrlMolars);
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
						collection = RestfulTool.buildCollection((List<MtrlMolar>) res.result(), pagination, hrefBase,
								com.pcitc.fms.service.model.MtrlMolar.class);
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
	//球罐罐容精度及扣减量
		public void getMtrlMolarDegree(RoutingContext routingContext) {
			Pagination pagination = new Pagination();
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				try {
					MtrlMolarDegree Model = QueryParams.getQueryParams(routingContext, MtrlMolarDegree.class);
					List mtrlMolarDegree = mtrlMolarService.getMtrlMolarDegree(Model);
					List mtrlMolarDegreeModelList = ObjectConverter.listConverter(mtrlMolarDegree, MtrlMolarDegree.class);
					pagination.setRecordCount((long)mtrlMolarDegreeModelList.size());
					future.complete(mtrlMolarDegreeModelList);
				} catch (Exception e) {
					future.fail(e);
				}

			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);

				}else if (res.succeeded()) {
					List<MtrlMolarDegree> targets = (List<MtrlMolarDegree>) res.result();
					try {
						collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
								MtrlMolarDegree.class);
					} catch (Exception e) {
						e.printStackTrace();
						returnCollection(routingContext, e.getMessage());
					}
					returnCollection(routingContext, collection);
				}

			});
		}
}
