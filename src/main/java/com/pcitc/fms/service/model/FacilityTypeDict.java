package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class FacilityTypeDict extends BaseResRep implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String grouping;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrouping() {
		return grouping;
	}
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	
}
