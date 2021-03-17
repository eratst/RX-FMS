package com.pcitc.fms;
	
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import com.pcitc.fms.common.JDBCUtil;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.LoadMap;
import com.pcitc.fms.mqtool.OumRun;
import com.pcitc.fms.mqtool.StartSyncService;
import com.pcitc.fms.verticle.ServiceVerticle;
import com.pcitc.imp.common.log.LogTool;

import amq.synchronize.service.SyncService;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Runner {   
	
	public static void main(String[] args) throws IOException {
		
	        //创建vertx容器实例
			final Vertx vertxInstance = Vertx.vertx();
	        //部署verticle组件
	        DeploymentOptions options = new DeploymentOptions();
	        JsonObject config = new JsonObject();
	        config.put("http.port",8082);
	        options.setConfig(config); 
	        options.setInstances(1); 
	        vertxInstance.deployVerticle(new ServiceVerticle(),options);
	        LogTool logger = new LogTool();  
			logger.info("Deployment done");
	    }
	}
