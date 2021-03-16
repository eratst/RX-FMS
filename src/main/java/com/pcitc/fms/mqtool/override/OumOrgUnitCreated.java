package com.pcitc.fms.mqtool.override;

import org.springframework.stereotype.Service;

import amq.synchronize.model.SyncEvent;
import amq.synchronize.service.SyncService;

@Service
public interface OumOrgUnitCreated extends SyncService{
	
	public void process(SyncEvent evt);
}
