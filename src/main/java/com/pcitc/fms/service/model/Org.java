package com.pcitc.fms.service.model;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Org extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * OrgId
	 */
	@ResourceMember(InTemplate = false)
	private Long orgId;

	/**
	 * OrgCode
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="组织机构编码")
	@ResourceMember(InQueries = "condition", Name = "orgCode")
	private String orgCode;
	/**
	 * orgName
	 */
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80,Explain="组织机构名称")
	@ResourceMember(InQueries = "condition", Name = "orgName")
	private String orgName;

	/**
	 * orgAlias
	 */
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80,Explain="组织机构简称")
	@ResourceMember(InQueries = "condition", Name = "orgAlias")
	private String orgAlias;

	/**
	 * orgTypeId
	 */
	@CheckField(CheckName = CheckNameType.ID,Explain="组织机构类型")
	private Long orgTypeId;

	
	/**
	 * CrtUserId
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="创建人")
	private String crtUserId;
	/**
	 * CrtUserName
	 */
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80,Explain="创建人名称")
	private String crtUserName;
	/**
	 * CrtDate
	 */
	@CheckField(CheckName = CheckNameType.CREATETIME,Explain="创建时间")
	private Date crtDate;

	/**
	 * MntUserId
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="维护人")
	private String mntUserId;

	/**
	 * MntUserName
	 */
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80,Explain="维护人名称")
	private String mntUserName;
	/**
	 * MntDate
	 */
	@CheckField(CheckName = CheckNameType.MAINTAINTIME,Explain="维护时间")
	private Date mntDate;
	
	/**
	 * dataStatus
	 */
	@CheckField(CheckName = CheckNameType.ENABLED,Explain="是否启用")
	@ResourceMember(InQueries = "condition", Name = "dataStatus")
	private Integer dataStatus;

	/**
	 * Des
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200,Explain="描述")
	private String des;

	/**
	 * SortNum
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10,Explain="排序")
	private Integer sortNum;
	/**
	 * Version
	 */
	@CheckField(CheckName = CheckNameType.ID, StrLength = 10,Explain="版本号")
	private Integer version;
	
	//@CheckField(CheckName = CheckNameType.NAMEMAYBENEG, StrLength = 20)
	private String orgAltitude;
	
	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20)
	private String orgLongitude;
	
	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20)
	private String orgLatitude;

	/**
	 * 组织机构类型名称
	 */
	@ResourceMember(InTemplate = false, InQueries = "condition", Name = "orgTypeName")
	private String orgTypeName;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	private String rentCode;
	
	private String bizCode;
	
	@ResourceMember(InTemplate = false)
	private Long upperOrgId;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="上级组织机构编码")
	private String upperOrgCode;

	@ResourceMember(InTemplate = false)
	private String upperOrgName;

	@ResourceMember(InTemplate = false)
	private String upperOrgAlias;

	public Org() {
		super();
	}

	public Org(String orgCode, String orgName, String orgAlias, Integer dataStatus, String orgTypeName,
               List<String> codeList, Integer top, Integer skip, String rentCode, String bizCode) {
		super();
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.dataStatus = dataStatus;
		this.orgTypeName = orgTypeName;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
		this.rentCode = rentCode;
		this.bizCode = bizCode;
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

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
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

	public String getOrgAltitude() {
		return orgAltitude;
	}

	public void setOrgAltitude(String orgAltitude) {
		this.orgAltitude = orgAltitude;
	}

	public String getOrgLongitude() {
		return orgLongitude;
	}

	public void setOrgLongitude(String orgLongitude) {
		this.orgLongitude = orgLongitude;
	}

	public String getOrgLatitude() {
		return orgLatitude;
	}

	public void setOrgLatitude(String orgLatitude) {
		this.orgLatitude = orgLatitude;
	}

	public Long getUpperOrgId() {
		return upperOrgId;
	}

	public void setUpperOrgId(Long upperOrgId) {
		this.upperOrgId = upperOrgId;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getUpperOrgName() {
		return upperOrgName;
	}

	public void setUpperOrgName(String upperOrgName) {
		this.upperOrgName = upperOrgName;
	}

	public String getUpperOrgAlias() {
		return upperOrgAlias;
	}

	public void setUpperOrgAlias(String upperOrgAlias) {
		this.upperOrgAlias = upperOrgAlias;
	}

	

}
