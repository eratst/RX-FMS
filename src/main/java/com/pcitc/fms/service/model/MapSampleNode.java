package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class MapSampleNode extends BaseResRep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String nodeCode;
	
	@CheckField(CheckName = CheckNameType.NAME)
	@ResourceMember(InQueries = "condition", Name = "nodeName")
	private String nodeName;
	
	@CheckField(CheckName = CheckNameType.NAME)
	@ResourceMember(InQueries = "condition", Name = "nodeAlias")
	private String nodeAlias;
	
	private String SampleName;
	
	private String SampleAlias;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String SampleCode;
	
	private String samplePointTypeCode;
	
	private String samplePointTypeName;
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;
	
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip = 0;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> nodeCodes;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> areaCodes;
	
	@CheckField(CheckName = CheckNameType.CODELIST)
	@ResourceMember(InQueries = "condition", OnlyQuery = true,Name = "$SampleCodes")
	private List<String> SampleCodes;
	
	
	
	
	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getSamplePointTypeCode() {
		return samplePointTypeCode;
	}

	public void setSamplePointTypeCode(String samplePointTypeCode) {
		this.samplePointTypeCode = samplePointTypeCode;
	}

	public String getSamplePointTypeName() {
		return samplePointTypeName;
	}

	public void setSamplePointTypeName(String samplePointTypeName) {
		this.samplePointTypeName = samplePointTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

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

	public String getNodeAlias() {
		return nodeAlias;
	}

	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
	}

	public String getSampleCode() {
		return SampleCode;
	}

	public void setSampleCode(String sampleCode) {
		SampleCode = sampleCode;
	}

	public List<String> getSampleCodes() {
		return SampleCodes;
	}

	public void setSampleCodes(List<String> sampleCodes) {
		SampleCodes = sampleCodes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSampleName() {
		return SampleName;
	}

	public void setSampleName(String sampleName) {
		SampleName = sampleName;
	}

	public String getSampleAlias() {
		return SampleAlias;
	}

	public void setSampleAlias(String sampleAlias) {
		SampleAlias = sampleAlias;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public List<String> getNodeCodes() {
		return nodeCodes;
	}

	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
	}

	public List<String> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<String> areaCodes) {
		this.areaCodes = areaCodes;
	}
	
}
