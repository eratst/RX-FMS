package com.pcitc.fms.bll.entity;

import java.io.Serializable;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

public class DataDegree implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long amount;
	
	private Double interpolateV;
	
	private Long degree;
	
	private Long tankId;
	
	private String nodeCode;
	
	private String nodeName;
	

	
	

	public String getNodeCode() {
		return nodeCode;
	}


	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}

	
	public Double getInterpolateV() {
		return interpolateV;
	}


	public void setInterpolateV(Double interpolateV) {
		this.interpolateV = interpolateV;
	}


	public Long getAmount() {
		return amount;
	}


	public Long getDegree() {
		return degree;
	}

	public void setDegree(Long degree) {
		this.degree = degree;
	}

	public Long getTankId() {
		return tankId;
	}

	public void setTankId(Long tankId) {
		this.tankId = tankId;
	}
	
	

}
