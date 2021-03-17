package com.pcitc.fms.service.model;

import java.io.Serializable;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询")
public class DataDegree extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(Name = "Amount")
	private Long amount;
	
	@ResourceMember(Name = "InterpolateV")
	private Double interpolateV;
	
	@ResourceMember(Name = "Degree")
	private Long degree;
	
	@ResourceMember(Name = "nodeId")
	private Long tankId;
	
	private String nodeCode;
	
	private String nodeName;
	
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	

	
	
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getAmount() {
		return amount;
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
