package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

public class Area_NodeType_Num implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nodeType ;
	private Long  num ;

	public Area_NodeType_Num() {
	}

	public Area_NodeType_Num(String nodeType, Long num) {
		this.nodeType = nodeType;
		this.num = num;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
}
