package com.pcitc.fms.service.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

//import com.pcitc.fms.bll.entity.Team;
import com.pcitc.fms.service.model.Team;
import com.pcitc.fms.bll.itf.TeamService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.CurrencyCheck;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TankArea;
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
 */
 /**
 * Title: TeamsHandler
* Description: 班组组织机构
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Controller
public class TeamsHandler extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(TeamsHandler.class);
	@Autowired
	private TeamService teamService;

	/**   
	 * @Title: getTeams   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public void getTeams(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		Pagination pagination = new Pagination();
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				 try {
					 Team team = QueryParams.getQueryParams(routingContext, Team.class);
					Pager<com.pcitc.fms.bll.entity.Team> sourceData;
					 Pageable pageable = null;
					if(null != team.getSkip()&&null!=team.getTop()){
						pageable = PageUtil.pageable(team.getTop(),team.getSkip(), null);
					}
					sourceData = teamService.getTeams(team,pageable);
					PageUtil.mergePage(pagination, sourceData);
					List<Team> teamEntity = ObjectConverter.listConverter(sourceData.getContent(), Team.class);
					future.complete(teamEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<Team> targets = (List<Team>) res.result();
					try {
						String linkNames = "teamAndUsers,users";
	   					targets = (List<Team>) setModelEntityWithLinks(targets,linkNames,routingContext.request().uri());
						collection = RestfulTool.buildCollection(targets, pagination, hrefBase, com.pcitc.fms.service.model.Team.class);
					} catch (Exception e) {
						log.error("getUnits is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getTeams is end!");
				}
			});
		} catch (Exception e) {
			log.error("getTeams is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}

	/**   
	 * @Title: getTeam   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param routingContext
	 * @date 2017年11月21日      
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void getTeam(RoutingContext routingContext) {
		HttpServerRequest request = routingContext.request();
		String hrefBase = request.uri();
		String orgCode = request.getParam("orgCode")==null?null:request.getParam("orgCode").trim();
		log.info("*** TeamsHandler START getTeams ***");
		try {
			Vertx vertx = routingContext.vertx();
			vertx.executeBlocking(future -> {
				com.pcitc.fms.bll.entity.Team teamEntity = null;
				try {
					CheckUtil.validateCodeException("组织机构编码编码", orgCode);
					teamEntity = teamService.getTeamByCode(orgCode);
					future.complete(teamEntity);
				} catch (Exception e) {
					future.fail(e);
				}
			}, res -> {
				String collection = null;// 返回结果字符串
				if (res.failed()) {
					collection = buildErrorCollection(routingContext, (Exception) res.cause());
					returnCollection(routingContext, collection);
				} else if (res.succeeded()) {
					List<com.pcitc.fms.service.model.Team> targets = new ArrayList<>();
					try {
						targets.add(ObjectConverter.entityConverter((com.pcitc.fms.bll.entity.Team)res.result(),com.pcitc.fms.service.model.Team.class));
						collection = RestfulTool.buildCollection(targets, hrefBase, com.pcitc.fms.service.model.Team.class);
					} catch (Exception e) {
						log.error("getTeams is Exception!");
						collection = buildErrorCollection(routingContext, e);
						returnCollection(routingContext, collection);
					}
					returnCollection(routingContext, collection);
					log.info("getTeams is end!");
				}
			});
		} catch (Exception e) {
			log.error("getTeams is Exception!");
			String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), hrefBase);
			returnCollection(routingContext, collection);
		}

	}
}
