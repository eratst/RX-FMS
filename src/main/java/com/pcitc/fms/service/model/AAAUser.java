package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class AAAUser extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String usercode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
	
}
