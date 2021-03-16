package com.pcitc.fms.service.stormHandler;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.pcitc.fms.common.JsonTool;
import com.pcitc.fms.common.ParamNum;
import com.pcitc.fms.service.handler.BaseHandler;
import com.pcitc.fms.service.stormModel.TankCalcPara;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.RestfulTool;
@Controller
public class StormTankHandler extends BaseHandler{
	private static Logger log = LoggerFactory.getLogger(StormTankHandler.class);
	//StandardDensityHelper
	 @SuppressWarnings("unchecked")
		public void getObject(RoutingContext routingContext){
			HttpServerRequest request = routingContext.request();
			String url = request.uri();
	        //获取参数
			String param = request.getParam("param");
			String methodName = request.getParam("methodName");
			String className = request.getParam("className");
			log.info("getObject is end!");
			try{
				Vertx vertx = routingContext.vertx();
				vertx.executeBlocking(future ->{
					try{
						String clazzName = getClazzName(url);
						Class clazz = Class.forName("com.pcitc.fms.bll.stormItf."+className);
						String num = ParamNum.getPamraNum(className+"."+methodName);
						Object str = null;
						if(num.equals("1a")) {
							TankCalcPara tankCal = JsonTool.stringToBean(param, TankCalcPara.class);
							Method mm = clazz.getMethod(methodName,tankCal.getClass());
							str = mm.invoke(clazz.newInstance(), tankCal);
						} else if(num.equals("0")){
							Method mm = clazz.getMethod(methodName);
							str = mm.invoke(clazz.newInstance());
						} else {
							Class[] clazzs = getClassNum(Integer.parseInt(num));
							Method mm = clazz.getMethod(methodName,clazzs);
							String[] params = param.split("\\|");
							if(params.length == 1) {
								str = mm.invoke(clazz.newInstance(), param);
							}
							str = mm.invoke(clazz.newInstance(), params);
						}
						future.complete(str);
					}catch(Exception e){
						future.fail(e);
					}
					
	 			}, res ->{
					String collection = null;
					if(res.failed()){
						collection  = buildErrorCollection(routingContext, (Exception) res.cause());
						returnCollection(routingContext, collection);
					}else if(res.succeeded()){
						collection = res.result().toString();
						returnCollection(routingContext, collection);
						log.info("getObject is end!");
					}
				});
			}catch(Exception e){
				log.error("getObject is Exception!");
				String collection = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), "");
				returnCollection(routingContext, collection);
			}
		}

	 
	private Class[] getClassNum(Integer num) {
		switch(num) {
		    case 0:
		    return null;
		    case 1:
		    return new Class[] {String.class};
		    case 2:
			return new Class[] {String.class,String.class};
		    case 3:
			return new Class[] {String.class,String.class,String.class};
		    case 4:
			return new Class[] {String.class,String.class,String.class,String.class};
		}
			
		return null;
	}

	private String getClazzName(String url) {
		String url1 = url.split("/")[2];
		String urls = url1.split("\\?")[0];
		return urls;
	}
		
	
}
