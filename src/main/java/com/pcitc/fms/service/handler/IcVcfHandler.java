package com.pcitc.fms.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.IcVcf;
import com.pcitc.fms.service.model.LiquidProdCoef;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.VcfDegree;
import com.pcitc.fms.bll.itf.IcVcfService;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@Controller
public class IcVcfHandler extends BaseHandler{

	@Autowired
	private IcVcfService icVcfService;
	
	public void findIcVcf(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String uri = request.absoluteURI();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String codeListStr = request.getParam("$vcfTypeCodeList") == null ? null : request.getParam("$vcfTypeCodeList").trim();
		String vcfTypeCode = request.getParam("vcfTypeCode") == null ? null : request.getParam("vcfTypeCode").trim();
		String vcfTypeName = request.getParam("vcfTypeName") == null ? null
				: request.getParam("vcfTypeName").trim();
		Sort sort = new Sort(Sort.Direction.ASC, "vcfId");
		Pagination pagination = new Pagination();
		
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);
				List<String> codeList = CheckUtil.buildStringToListString("$vcfTypeCodeList", codeListStr);
				
				Pageable pageable = null;
				
				if(uri.contains("$top") && topStr==null){
					pageable=null;
				}
				
				if (null != topStr && !"".equals(topStr) && null != skip && !"".equals(skip)) {
					pageable = PageUtil.pageable(top, skip, sort);
				}
				
				IcVcf icVcf = new IcVcf(vcfTypeCode,vcfTypeName,codeList,top,skip);
				Pager<com.pcitc.fms.bll.entity.IcVcf> sourceData = sourceData = icVcfService.findIcVcf(icVcf, pageable);
				List<com.pcitc.fms.bll.entity.IcVcf> list=new ArrayList<>();
				if(uri.contains("$skip") && skip!=null && top==null){
					if (icVcf.getSkip()>=sourceData.getContent().size()) {
						sourceData.setContent(null);
					} else {
						for (int i=0;i<icVcf.getSkip();i++) {
							sourceData.getContent().remove(0);
						}
						sourceData.setTotalElements((long)sourceData.getContent().size());
					}
				}
				PageUtil.mergePage(pagination, sourceData);
				List<IcVcf> targets = ObjectConverter.listConverter(sourceData.getContent(), IcVcf.class);
				future.complete(targets);
			}catch(Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			}
			try {
				if (res.succeeded()) {
					collection = RestfulTool.buildCollection((List<IcVcf>) res.result(), pagination,
							routingContext.request().absoluteURI(), IcVcf.class);
				} else {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
				}
				returnCollection(routingContext, collection);
			} catch (Exception e) {
				String collecion = buildErrorCollection(routingContext, e);
				returnCollection(routingContext, collecion);
			}
		});
	}
	
	//Vcf精度及扣减量
			public void getVcfDegree(RoutingContext routingContext) {
				Pagination pagination = new Pagination();
				Vertx vertx = routingContext.vertx();
				vertx.executeBlocking(future -> {
					try {
						VcfDegree Model = QueryParams.getQueryParams(routingContext, VcfDegree.class);
						List vcfDegree = icVcfService.getVcfDegree(Model);
						List vcfDegreeModelList = ObjectConverter.listConverter(vcfDegree, VcfDegree.class);
						pagination.setRecordCount((long)vcfDegreeModelList.size());
						future.complete(vcfDegreeModelList);
					} catch (Exception e) {
						future.fail(e);
					}

				}, res -> {
					String collection = null;
					if (res.failed()) {
						collection = buildErrorCollection(routingContext, (Exception) res.cause());
						returnCollection(routingContext, collection);

					}else if (res.succeeded()) {
						List<VcfDegree> targets = (List<VcfDegree>) res.result();
						try {
							collection = RestfulTool.buildCollection(targets, pagination, routingContext.request().uri(),
									VcfDegree.class);
						} catch (Exception e) {
							e.printStackTrace();
							returnCollection(routingContext, e.getMessage());
						}
						returnCollection(routingContext, collection);
					}

				});
			}
		
	
}
