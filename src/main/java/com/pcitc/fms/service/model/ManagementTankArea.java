package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class ManagementTankArea extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String orgCode;
	
	private String orgName;
	
	private String orgSname;
	
	private Integer areaId;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaSname;
	
	private String tankAreaTypeCode;
	
	private String tankAreaTypeName;
	
	private String technicCode;
	
	private String technicName;
	
	private Integer sortNum;
	
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "inUser")
	private Integer inUse;
	
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "orgCodeList")
	private List<String> orgCodeList;
	
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "tankAreaCodeList")
	private List<String> tankAreaCodeList;
	
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "bizCode")
	private String bizCode;

	public List<String> getOrgCodeList() {
		return orgCodeList;
	}

	public void setOrgCodeList(List<String> orgCodeList) {
		this.orgCodeList = orgCodeList;
	}

	public List<String> getTankAreaCodeList() {
		return tankAreaCodeList;
	}

	public void setTankAreaCodeList(List<String> tankAreaCodeList) {
		this.tankAreaCodeList = tankAreaCodeList;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;

	
	public ManagementTankArea() {
		super();
	}

	public ManagementTankArea(Integer top, Integer skip) {
		super();
		this.top = top;
		this.skip = skip;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgSname() {
		return orgSname;
	}

	public void setOrgSname(String orgSname) {
		this.orgSname = orgSname;
	}

	public String getAreaSname() {
		return areaSname;
	}

	public void setAreaSname(String areaSname) {
		this.areaSname = areaSname;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getTankAreaTypeCode() {
		return tankAreaTypeCode;
	}

	public void setTankAreaTypeCode(String tankAreaTypeCode) {
		this.tankAreaTypeCode = tankAreaTypeCode;
	}

	public String getTankAreaTypeName() {
		return tankAreaTypeName;
	}

	public void setTankAreaTypeName(String tankAreaTypeName) {
		this.tankAreaTypeName = tankAreaTypeName;
	}

	public String getTechnicCode() {
		return technicCode;
	}

	public void setTechnicCode(String technicCode) {
		this.technicCode = technicCode;
	}

	public String getTechnicName() {
		return technicName;
	}

	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

}
