package com.pcitc.fms.mqtool.override;

import org.springframework.stereotype.Service;

import amq.synchronize.service.SyncService;

@Service
public interface OumOrgUnitEdited extends SyncService{
	
	public void process();
}
