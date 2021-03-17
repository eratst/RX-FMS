package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询")
public class MtrlMolarDegree extends BaseResRep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(Name = "Amount")
	private Long amount;
	
	@ResourceMember(Name = "InterpolateV")
	private Double interpolateV;
	
	@ResourceMember(Name = "Degree")
	private Long degree;
	
	@ResourceMember(Name = "MtrlId")
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
