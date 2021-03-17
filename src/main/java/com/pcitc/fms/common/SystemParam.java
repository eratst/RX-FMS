package com.pcitc.fms.common;

import java.io.Serializable;

public class SystemParam implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 物料服务配置信息
	 */
	private String pm_address_base;
	
	private int pm_port;
	
	private String pm_host;

	public String getPm_address_base() {
		return pm_address_base;
	}

	public void setPm_address_base(String pm_address_base) {
		this.pm_address_base = pm_address_base;
	}

	public int getPm_port() {
		return pm_port;
	}

	public void setPm_port(int pm_port) {
		this.pm_port = pm_port;
	}

	public String getPm_host() {
		return pm_host;
	}

	public void setPm_host(String pm_host) {
		this.pm_host = pm_host;
	}
	
	
}
