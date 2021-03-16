package com.pcitc.fms.mqtool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pcitc.fms.common.JDBCUtil;
import com.pcitc.fms.exception.BusinessException;

import amq.synchronize.service.SyncService;

/**
 * Created by dell on 2018/11/19.
 */
public class OumRun {
	
	private static ApplicationContext context;
	
	public static void setContext(ApplicationContext context) {
		OumRun.context = context;
	}

	public void run() {
		System.out.println(".........开始......................");
		StartSyncService startSyncService = new StartSyncService();
        Map<String, SyncService> services = new HashMap<>();
        LoadMap loadMap = (LoadMap) context.getBean("loadMap");
//        readProperty readProperty=new readProperty();
//  	  Map read = readProperty.read();
//        String url = (String)read.get("activemq.broker-url");
//        String key = (String) read.get("oum.key");
//        startSyncService.init(url,key,loadMap.loadMap());
        startSyncService.init(loadMap.loadMap());
        System.out.println(".........结束......................");
	}
	
}
