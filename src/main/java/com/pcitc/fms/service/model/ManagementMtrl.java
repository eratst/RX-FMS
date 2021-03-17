package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class ManagementMtrl extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResourceMember(InQueries = "condition", Name = "mtrlCode")
	private String mtrlCode;

	private String mtrlName;

	private String mtrlSname;

	private String upperMtrlCode;

	private String mtrlTypeCode;

	private String mtrlTypeName;

	private String vcfTypeCode;

	private String vcfTypeName;

	private String dimensionCode;

	private String dimensionName;

	private String dimensionSname;
	
	private Integer inUse;

	private Integer dec;

	private Integer sortNum;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public ManagementMtrl(String mtrlCode, String mtrlTypeCode, Integer top, Integer skip, Integer inUse) {
		super();
		this.mtrlCode = mtrlCode;
		this.mtrlTypeCode = mtrlTypeCode;
		this.top = top;
		this.skip = skip;
		this.inUse = inUse;
	}

	public ManagementMtrl() {
		super();
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

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
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
