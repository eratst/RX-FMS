package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class MtrlMolarDegree implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long amount;
	
	private Double interpolateV;
	
	private Long degree;
	
	private Long mtrlId;
	
	private String mtrlCode;
	
	private String mtrlName;
	
	

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
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

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}
	
	

}
