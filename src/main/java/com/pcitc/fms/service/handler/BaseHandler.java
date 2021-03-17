package com.pcitc.fms.service.handler;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.RedisUtil;
import com.pcitc.fms.common.SystemParam;
import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.exception.BusinessException;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.baseresrep.Link;
import pcitc.imp.common.ettool.utils.RestfulTool;
import redis.clients.jedis.Jedis;

/**
 * Created by pcitc on 2016/12/27.
 */
public class BaseHandler implements Handler<RoutingContext> {
	
	private static Map<String,String> prompts = new ConcurrentHashMap<>();
	
	static {
		prompts.put("equipments", "设备");
		prompts.put("stocks", "库位");
		prompts.put("outlets", "排放口");
		prompts.put("sideLines", "侧线");
		prompts.put("edgePoints", "进出厂点");
		prompts.put("tanks", "罐");
		prompts.put("silos", "料仓");
		prompts.put("tubulations", "管段");
		prompts.put("tees", "三通");
		prompts.put("plates", "盲板");
		prompts.put("valves", "阀门");
		prompts.put("samplePoints", "采样点");
		prompts.put("bizOrgMains", "业务域");
		prompts.put("measurements", "度量指标");
		prompts.put("mapSampleNodes", "采样点与节点关联");
		prompts.put("nodetopDTL", "拓扑关系");
		prompts.put("associatives", "节点物料关系");
		prompts.put("nodeDictionaries", "目标节点");
		prompts.put("headquarters", "总部");
		prompts.put("divisions", "事业部");
		prompts.put("enterprises", "企业");
		prompts.put("offices", "公司处室");
		prompts.put("produceFactories", "生产工厂");
		prompts.put("departments", "科室");
		prompts.put("workshops", "车间");
		prompts.put("teams", "班组");
		prompts.put("prdtcells", "生产单元");
		prompts.put("prdtCellMeasurements", " 生产单元度量指标");
		prompts.put("users", "用户");
	}
	
	@Override
	public void handle(RoutingContext routingContext) {

	}
	
	 protected SystemParam param;
	 
	    public SystemParam getParam() {
	        return param;
	    }

	    public void setParam(SystemParam param) {
	        this.param = param;
	    }
	protected void returnCollection(RoutingContext routingContext, String collection) {
		if (collection == null) {
			if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
				if (routingContext.request().method() == HttpMethod.GET) {
					DISCMAp.setContentMaps(routingContext.request().uri(), collection);
				}
			} else {
				Jedis jedis = RedisUtil.getJedis();
				jedis.set("collectionCache:data:"+routingContext.request().uri(), collection);
			}
			
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end();
		} else {
			if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
				if (routingContext.request().method() == HttpMethod.GET) {
					DISCMAp.setContentMaps(routingContext.request().uri(), collection);
				}
			} else {
				Jedis jedis = RedisUtil.getJedis();
				jedis.set("collectionCache:data:"+routingContext.request().uri(), collection);
			}
			
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(collection);
		}
	}

	protected String buildErrorCollection(RoutingContext routingContext, BusinessException e) {
		// 构造错误返回值
		String collecion = null;
		collecion = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
				routingContext.request().uri().trim());
		if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
			if (routingContext.request().method() == HttpMethod.GET) {
				DISCMAp.setContentMaps(routingContext.request().uri(), collecion);
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			jedis.set("collectionCache:data:"+routingContext.request().uri(), collecion);
		}
		return collecion;
	}

	protected String buildErrorCollection(RoutingContext routingContext, Exception e) {
		String collecionStr;
		collecionStr = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()==null ?String.valueOf(e):e.getMessage()),routingContext.request().uri().trim());
		if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
			if (routingContext.request().method() == HttpMethod.GET) {
				DISCMAp.setContentMaps(routingContext.request().uri(), collecionStr);
			}
		} else {
			Jedis jedis = RedisUtil.getJedis();
			jedis.set("collectionCache:data:"+routingContext.request().uri(), collecionStr);
		}
		return collecionStr;
	}
	
	protected void returnMessage(RoutingContext routingContext, String message) {
		if ("".equals(message) || null == message) {
			if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
				if (routingContext.request().method() == HttpMethod.GET) {
					DISCMAp.setContentMaps(routingContext.request().uri(), message);
				}
			} else {
				Jedis jedis = RedisUtil.getJedis();
				jedis.set("collectionCache:data:"+routingContext.request().uri(), message);
			}
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end();
		} else {
			if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
				if (routingContext.request().method() == HttpMethod.GET) {
					DISCMAp.setContentMaps(routingContext.request().uri(), message);
				}
			} else {
				Jedis jedis = RedisUtil.getJedis();
				jedis.set("collectionCache:data:"+routingContext.request().uri(), message);
			}
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(message);
		}
	}
	
	public List<? extends BaseResRep> setModelEntityWithLinks(List<? extends BaseResRep> sources, String linkNameStr,String baseUri) throws BusinessException{
		if (null != sources) {
			if (StringUtils.isNotEmpty(baseUri)) {
				baseUri = baseUri.split("\\?")[0];
			}
			List<String> linkNames = new ArrayList<>();
			if (StringUtils.isNotEmpty(linkNameStr)) {
				Arrays.asList(linkNameStr.split(",")).forEach(linkName ->{
					linkNames.add(linkName.trim());
				});
			}
			Object ownSourceName = "";
			for (int i=0; i<sources.size();i++) {
				Field[] fields = sources.get(i).getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(BuildLink.class)) {
						try {
							ownSourceName = field.get(sources.get(i));
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("", e.getMessage());
						} 
						break;
					}
				}
				List<Link> links = new ArrayList<>();
				for (String linkName : linkNames) {
					String promptValue = prompts.get(linkName);
					if (StringUtils.isEmpty(promptValue)) {
						promptValue = linkName;
					}
					
					//防止出现   访问路径是：http://localhost:8081/FactoryModelService/nodeDictionaries/shuibiao0001
					//       link变为：/FactoryModelService/nodeDictionaries/shuibiao0001/shuibiao0001/mapSampleNodes
					if(baseUri.endsWith("/"+ownSourceName)){
						Link linkOne = new Link(linkName,URI.create(baseUri+"/"+linkName),promptValue+"集合");
						links.add(linkOne);
					} else {
						Link linkOne = new Link(linkName,URI.create(baseUri+"/"+ownSourceName+"/"+linkName),promptValue+"集合");
						links.add(linkOne);
					}
				}
				sources.get(i).setHref(baseUri+"/"+ownSourceName);
				sources.get(i).setLinkObjs(links);
			}
		}
		
		return sources;
	}
}
