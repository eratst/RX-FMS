package com.pcitc.fms.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Temppreden;
import com.pcitc.fms.bll.itf.TemppredenService;
import com.pcitc.fms.service.handler.BaseHandler;
import com.pcitc.imp.common.model.Pagination;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/**
 * 
 * @author xin.kou
 *
 */
@Controller
public class TemppredenHandler extends BaseHandler {
	
	@Autowired
	private TemppredenService temppredenService;
	
	public void findTemppredens(RoutingContext routingContext) {
		// 获取查询参数
		HttpServerRequest request = routingContext.request();
		String topStr = request.getParam("$top") == null ? null : request.getParam("$top").trim();
		String skipStr = request.getParam("$skip") == null ? null : request.getParam("$skip").trim();
		String temp = request.getParam("temp") == null ? null : request.getParam("temp").trim();
		Pagination pagination = new Pagination();
		Sort sort = new Sort(Direction.DESC, "mntDate");
		Vertx vertx = routingContext.vertx();
		vertx.executeBlocking(future -> {
			try {
				// 校验
				// Double temp = null;
				// if (tempS != null) {
				// temp = Double.valueOf(tempS);
				// }
				Integer skip = CheckUtil.checkSkip(skipStr);
				Integer top = CheckUtil.checkTop(topStr);

				Pager<com.pcitc.fms.bll.entity.Temppreden> sourceData;
				Pageable pageable = null;
				if (null != topStr && !"".equals(topStr) && skipStr != null && !"".equals(skipStr)) {
					pageable = PageUtil.pageable(top, skip, sort);
					sourceData = temppredenService.findTemppredens(temp, pageable);
					PageUtil.mergePage(pagination, sourceData);
				} else {
					sourceData = temppredenService.findTemppredens(temp, null);
					PageUtil.mergePage(pagination, sourceData);
				}
				List<Temppreden> modelList = ObjectConverter.listConverter(sourceData.getContent(), Temppreden.class);
				future.complete(modelList);
			} catch (Exception e) {
				future.fail(e);
			}
		}, res -> {
			String collection = null;
			if (res.failed()) {
				collection = buildErrorCollection(routingContext, (Exception) res.cause());
			} else if (res.succeeded()) {
				try {
					collection = RestfulTool.buildCollection((List<Temppreden>) res.result(), pagination, request.uri(),
							Temppreden.class);
				} catch (Exception e) {
					collection = buildErrorCollection(routingContext, e);
				}
			}
			returnCollection(routingContext, collection);
		});
	}

}
