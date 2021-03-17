package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class ManagementMtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer mtrlId;
	
	private String mtrlCode;
	
	private String mtrlName;
	
	private String mtrlSname;
	
	private String upperMtrlCode;
	
	private Integer mtrlTypeId;
	
	private String mtrlTypeCode;
	
	private String mtrlTypeName;
	
	private Integer vcfTypeId;
	
	private String vcfTypeCode;
	
	private String vcfTypeName;
	
	private Integer dimensionId;
	
	private String dimensionCode;
	
	private String dimensionName;
	
	private String dimensionSname;
	
	private Integer inUse;
	
	private Integer dec;
	
	private Integer sortNum;

	public Integer getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Integer mtrlId) {
		this.mtrlId = mtrlId;
	}

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

	public String getUpperMtrlCode() {
		return upperMtrlCode;
	}

	public void setUpperMtrlCode(String upperMtrlCode) {
		this.upperMtrlCode = upperMtrlCode;
	}

	public Integer getMtrlTypeId() {
		return mtrlTypeId;
	}

	public void setMtrlTypeId(Integer mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}

	public String getMtrlTypeCode() {
		return mtrlTypeCode;
	}

	public void setMtrlTypeCode(String mtrlTypeCode) {
		this.mtrlTypeCode = mtrlTypeCode;
	}

	public String getMtrlTypeName() {
		return mtrlTypeName;
	}

	public void setMtrlTypeName(String mtrlTypeName) {
		this.mtrlTypeName = mtrlTypeName;
	}

	public Integer getVcfTypeId() {
		return vcfTypeId;
	}

	public void setVcfTypeId(Integer vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
	}

	public String getVcfTypeCode() {
		return vcfTypeCode;
	}

	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
	}

	public String getVcfTypeName() {
		return vcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public Integer getDec() {
		return dec;
	}

	public void setDec(Integer dec) {
		this.dec = dec;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getMtrlSname() {
		return mtrlSname;
	}

	public void setMtrlSname(String mtrlSname) {
		this.mtrlSname = mtrlSname;
	}

	public String getDimensionSname() {
		return dimensionSname;
	}

	public void setDimensionSname(String dimensionSname) {
		this.dimensionSname = dimensionSname;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
}
