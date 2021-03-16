package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
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
public class PositionOrg extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResourceMember(InTemplate = false, OnlyQuery = true)
	private Long positionOrgId;

	@ResourceMember(InTemplate = false, OnlyQuery = true)
	private Long positionId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", Name = "positionCode")
	private String positionCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "positionName")
	private String positionName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "positionAlias")
	private String positionAlias;

	@ResourceMember(InTemplate = false, OnlyQuery = true)
	private Long orgId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", Name = "orgCode")
	private String orgCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "orgName")
	private String orgName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "orgAlias")
	private String orgAlias;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 30)
	@ResourceMember(OnlyQuery = true)
	private String crtUserCode;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
	@ResourceMember(OnlyQuery = true)
	private String crtUserName;

	@ResourceMember(InTemplate = false, OnlyQuery = true)
	private Date crtDate;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 30)
	@ResourceMember(OnlyQuery = true)
	private String mntUserCode;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;

	@ResourceMember(InTemplate = false, OnlyQuery = true)
	private Date mntDate;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;

	@CheckField(CheckName = CheckNameType.ID, StrLength = 10)
	@ResourceMember(OnlyQuery = true)
	private Integer version;

	@CheckField(CheckName = CheckNameType.ENABLED, StrLength = 10)
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.ID, StrLength = 10)
	private Integer sortNum;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", OnlyQuery = true)
	private String rentCode;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", OnlyQuery = true)
	private String bizCode;

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 100)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$positionCodes")
	private List<String> positionCodes;

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 100)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$orgCodes")
	private List<String> orgCodes;

	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 100)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 100)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	private Integer skip;

	public Long getPositionOrgId() {
		return positionOrgId;
	}

	public void setPositionOrgId(Long positionOrgId) {
		this.positionOrgId = positionOrgId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionAlias() {
		return positionAlias;
	}

	public void setPositionAlias(String positionAlias) {
		this.positionAlias = positionAlias;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	public Date getMntDate() {
		return mntDate;
	}

	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public List<String> getPositionCodes() {
		return positionCodes;
	}

	public void setPositionCodes(List<String> positionCodes) {
		this.positionCodes = positionCodes;
	}

	public List<String> getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(List<String> orgCodes) {
		this.orgCodes = orgCodes;
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

}
