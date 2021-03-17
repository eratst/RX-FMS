package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class TPmOrg extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long orgId;

	private String orgCode;

	private String orgName; // 组织机构名称

	private String orgAlias; // 组织机构简称

	private Long orgTypeId; // 组织机构类型

	private String orgType; // 组织机构类型(用于显示)

	private String orgTypeCode;

	private String orgTypeName;

	private Integer inUse;// 状态

	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;// 最后维护人名称

	private Integer version;

	private Integer sortNum;// 排序

	private String des;// 描述

	private String orgLatitude;

	private String orgAltitude;

	private String orgLongitude;

	private Long upperOrgId;

	private String upperOrgCode;

	private String upperOrgName;

	private String upperOrgAlias;

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

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getOrgLatitude() {
		return orgLatitude;
	}

	public void setOrgLatitude(String orgLatitude) {
		this.orgLatitude = orgLatitude;
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
