package com.pcitc.fms.mqtool;

import amq.synchronize.model.SyncEvent;
import amq.synchronize.service.MQContext;
import amq.synchronize.service.SyncMessageService;
import amq.synchronize.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StartSyncService implements SyncService {

	protected transient Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String ORG_CREATED = "ORG_CREATED";
	public static final String ORG_EDITED = "ORG_EDITED";
	public static final String ORG_DELETED = "ORG_DELETED";
	public static final String ORGUNIT_CREATED = "ORGUNIT_CREATED";
	public static final String ORGUNIT_EDITED = "ORGUNIT_EDITED";
	public static final String ORGUNIT_DELETED = "ORGUNIT_DELETED";
	public static final String USER_CREATED = "USER_CREATED";
	public static final String USER_EDITED = "USER_EDITED";
	public static final String USER_DELETED = "USER_DELETED";
	public static final String USER_ENABLED = "USER_ENABLED";
	public static final String USER_DISABLED = "USER_DISABLED";
	public static final String POSITION_CREATED = "POSITION_CREATED";
	public static final String POSITION_EDITED = "POSITION_EDITED";



	protected static Map<String, SyncService> services = new HashMap<>();

	public void init(String oumInfoUrl,String appCode,Map<String, SyncService> services) {
		try {
			this.services = services;
			MQContext mqc = getWebInfo(oumInfoUrl,appCode);
			SyncMessageService.SyncAction(this,mqc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 直接从mq获取
	 * @return
	 */
	public void init(Map<String, SyncService> services) {
		try {
			this.services = services;
			MQContext mqc = getWebInfo();
			SyncMessageService.SyncAction(this,mqc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void restart(String oumInfoUrl,String appCode,Map<String, SyncService> services) {
		try {
			SyncMessageService.stop();
			Thread.sleep(3000);
			MQContext mqc = getWebInfo(oumInfoUrl,appCode);
			this.services = services;
			SyncMessageService.SyncAction(this,mqc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MQContext getWebInfo(String oumInfoUrl,String appCode){
		String borkerUrl ="";
		String userName ="";
		String password ="";
		String destination ="";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(oumInfoUrl+appCode,String.class);
		String[] re = result.split(";");
		borkerUrl = re[0];
		userName = re[1];
		password = re[2];
		destination = re[3];
		MQContext mqc = null;
		try {
			mqc = new MQContext(borkerUrl,userName,password,destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mqc;
	}
	/**
	 * 直接从mq获取
	 * @return
	 */
	private MQContext getWebInfo(){
		String borkerUrl =System.getenv("activemq_broker_url");
		String userName =System.getenv("activemq_user");
		String password =System.getenv("activemq_password");
		String destination =System.getenv("mq_consumer_destination");
		MQContext mqc = null;
		try {
			mqc = new MQContext(borkerUrl,userName,password,destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mqc;
	}

	@Override
	public void handler(SyncEvent evt) {
		SyncService service = services.get(evt.getKey());
		if (service == null) {
			logger.error("Cannot find the service to handle event[" + evt.getKey() + "]");
			return;
		}
		try {
			//触发获得对应事件，并将消息序列化为对应的JAVA对象
			service.handler(evt);
		} catch (Exception e) {
			logger.error("Error happend!", e);
		}
	}

}
