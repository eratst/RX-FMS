package com.pcitc.fms.mqtool.override;

import org.springframework.stereotype.Service;

import amq.synchronize.service.SyncService;

@Service
public interface OumOrgDeleted extends SyncService{
	
	public void process();
}
