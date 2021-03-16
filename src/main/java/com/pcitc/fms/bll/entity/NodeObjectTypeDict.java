package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class NodeObjectTypeDict implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long nodeObjectTypeId;
	private Long code;
	private String name;
	private String grouping;
	
	public Long getNodeObjectTypeId() {
		return nodeObjectTypeId;
	}
	public void setNodeObjectTypeId(Long nodeObjectTypeId) {
		this.nodeObjectTypeId = nodeObjectTypeId;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
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
