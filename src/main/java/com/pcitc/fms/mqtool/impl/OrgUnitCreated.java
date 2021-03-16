package com.pcitc.fms.mqtool.impl;

import com.pcitc.fms.mqtool.BaseSyncObject;

import amq.synchronize.model.SyncEvent;
import amq.synchronize.model.SyncOrgUnit;
import amq.synchronize.service.SyncService;


public class OrgUnitCreated extends BaseSyncObject implements SyncService {

	private SyncOrgUnit orgUnit = null;
	private String currentEventKey = null;

	public SyncOrgUnit getOrgUnit() {
		return orgUnit;
	}

	public String getCurrentEventKey() {
		return currentEventKey;
	}

	@Override
	public void handler(SyncEvent evt) {
		orgUnit = (SyncOrgUnit) evt.getModel();
		currentEventKey = evt.getKey();
		logger.debug("同步"+evt.getType()+"操作"+evt.getOper()+":" + orgUnit.getOrgUnitName() + "成功");
		process(evt);
	}

	public void process(SyncEvent evt){
		logger.debug("获得到对象事件："+currentEventKey+"各开发组需要重写此方法实现自己的逻辑");
		logger.debug("当前对象："+orgUnit.toString());
	}
}
