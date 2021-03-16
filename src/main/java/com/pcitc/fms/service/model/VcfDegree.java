package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询")
public class VcfDegree extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InQueries = "condition", Name = "Amount")
	private Long amount;
	
	@ResourceMember(InQueries = "condition", Name = "InterpolateV")
	private Double interpolateV;
	
	@ResourceMember(InQueries = "condition", Name = "VcfTypeId")
	private Long MtrlTypeId;
	
	@ResourceMember(InQueries = "condition", Name = "VcfTypeCode")
	private String MtrlTypeCode;
	
	@ResourceMember(InQueries = "condition", Name = "VcfTypeName")
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
