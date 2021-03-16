package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class VcfDegree implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long amount;
	
	private Double interpolateV;
	
	private Long MtrlTypeId;
	
	private String MtrlTypeCode;
	
	private String MtrlTypeName;
	
	
	
	

	public String getMtrlTypeCode() {
		return MtrlTypeCode;
	}

	public void setMtrlTypeCode(String mtrlTypeCode) {
		MtrlTypeCode = mtrlTypeCode;
	}

	public String getMtrlTypeName() {
		return MtrlTypeName;
	}

	public void setMtrlTypeName(String mtrlTypeName) {
		MtrlTypeName = mtrlTypeName;
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

	public Long getMtrlTypeId() {
		return MtrlTypeId;
	}

	public void setMtrlTypeId(Long mtrlTypeId) {
		MtrlTypeId = mtrlTypeId;
	}
	
	
	
	
	
}
