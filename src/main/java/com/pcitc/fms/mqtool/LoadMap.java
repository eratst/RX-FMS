package com.pcitc.fms.mqtool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcitc.fms.mqtool.impl.OrgDeleted;
import com.pcitc.fms.mqtool.impl.OrgEdited;
import com.pcitc.fms.mqtool.impl.OrgUnitCreated;
import com.pcitc.fms.mqtool.impl.OrgUnitDeleted;
import com.pcitc.fms.mqtool.impl.OrgUnitEdited;
import com.pcitc.fms.mqtool.impl.PositionCreated;
import com.pcitc.fms.mqtool.impl.PositionEdited;
import com.pcitc.fms.mqtool.impl.UserCreated;
import com.pcitc.fms.mqtool.impl.UserDeleted;
import com.pcitc.fms.mqtool.impl.UserDisabled;
import com.pcitc.fms.mqtool.impl.UserEdited;
import com.pcitc.fms.mqtool.impl.UserEnabled;
import com.pcitc.fms.mqtool.override.OumOrgCreated;
import com.pcitc.fms.mqtool.override.OumOrgCreatedImpl;
import com.pcitc.fms.mqtool.override.OumOrgDeleted;
import com.pcitc.fms.mqtool.override.OumOrgDeletedImpl;
import com.pcitc.fms.mqtool.override.OumOrgEdited;
import com.pcitc.fms.mqtool.override.OumOrgEditedImpl;
import com.pcitc.fms.mqtool.override.OumOrgUnitCreated;
import com.pcitc.fms.mqtool.override.OumOrgUnitCreatedImpl;
import com.pcitc.fms.mqtool.override.OumOrgUnitDeleted;
import com.pcitc.fms.mqtool.override.OumOrgUnitDeletedImpl;
import com.pcitc.fms.mqtool.override.OumOrgUnitEdited;
import com.pcitc.fms.mqtool.override.OumOrgUnitEditedImpl;
import com.pcitc.fms.mqtool.override.OumUserCreated;
import com.pcitc.fms.mqtool.override.OumUserDeleted;
import com.pcitc.fms.mqtool.override.OumUserDeletedImpl;
import com.pcitc.fms.mqtool.override.OumUserDisabled;
import com.pcitc.fms.mqtool.override.OumUserEdited;
import com.pcitc.fms.mqtool.override.OumUserEditedImpl;
import com.pcitc.fms.mqtool.override.OumUserEnabled;

import amq.synchronize.service.SyncService;

@Component
public class LoadMap {
	
	@Autowired
	private OumOrgCreated oumOrgCreated; 
	
	@Autowired
	private OumOrgDeleted oumOrgDelete;
	
	@Autowired
	private OumOrgEdited oumOrgEdited;
	
	@Autowired
	private OumOrgUnitCreated oumOrgUnitCreated;
	
	@Autowired
	private OumOrgUnitDeleted oumOrgUnitDeleted;
	
	@Autowired
	private OumOrgUnitEdited oumOrgUnitEdited;
	
	@Autowired
	private OumUserCreated oumUserCreated;
	
	@Autowired
	private OumUserDeleted oumUserDeleted;
	
	@Autowired
	private OumUserDisabled oumUserDisabled;
	
	@Autowired
	private OumUserEdited oumUserEdited;
	
	@Autowired
	private OumUserEnabled oumUserEnabled;
	
	public Map<String, SyncService> loadMap(){
		Map<String, SyncService> services = new HashMap<>();
        services.put("ORG_CREATED", oumOrgCreated);
        services.put("ORG_EDITED", oumOrgEdited);
        services.put("ORG_DELETED", oumOrgDelete);
        services.put("ORGUNIT_CREATED", oumOrgUnitCreated);
        services.put("ORGUNIT_EDITED", oumOrgUnitEdited);
        services.put("ORGUNIT_DELETED", oumOrgUnitDeleted);
        services.put("USER_CREATED", oumUserCreated);
        services.put("USER_EDITED", oumUserEdited);
        services.put("USER_DELETED", oumUserDeleted);
        services.put("USER_ENABLED", oumUserEnabled);
        services.put("USER_DISABLED" ,oumUserDisabled);
        services.put("POSITION_CREATED", new PositionCreated());
        services.put("POSITION_EDITED", new PositionEdited());
		return services;
	}

	@Override
	public String toString() {
		return "LoadMap [oumOrgCreated=" + oumOrgCreated + ", oumOrgDelete=" + oumOrgDelete + ", oumOrgEdited="
				+ oumOrgEdited + ", oumOrgUnitCreated=" + oumOrgUnitCreated + ", oumOrgUnitDeleted=" + oumOrgUnitDeleted
				+ ", oumOrgUnitEdited=" + oumOrgUnitEdited + ", oumUserCreated=" + oumUserCreated + ", oumUserDeleted="
				+ oumUserDeleted + ", oumUserDisabled=" + oumUserDisabled + ", oumUserEdited=" + oumUserEdited
				+ ", oumUserEnabled=" + oumUserEnabled + "]";
	}
	
	

}
