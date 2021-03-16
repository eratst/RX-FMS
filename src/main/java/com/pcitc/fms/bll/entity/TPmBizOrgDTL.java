package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class TPmBizOrgDTL implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long dtlId;  //主键iD

	private Long orgId;  //组织机构ID

	private String orgCode;

	private String orgName; //组织机构名称

	private String orgAlias; //组织机构简称

	private Long orgTypeId; //组织机构类型

	private String orgTypeCode; //组织机构类型(用于显示)
	
	private String orgTypeName;

	private Long parentOrgId;//上一级组织机构ID

	private String parentOrgCode;//上一级组织机构编码
	
	private String parentOrgName;
	
	private String parentOrgAlias;

	private Long expendFlag;//扩展标识

	private Integer inUse;//状态

	private Long bizId; //业务域ID

	private String bizCode; //业务域CODE

	private String bizName; //业务域名称

	private String bizAlias; //业务域简称

	private String crtUserCode; //创建人ID

	private String crtUserName;//创建人名称

	private Date crtDate;//创建时间

	private String mntUserCode;//最后维护人ID

	private String mntUserName;//最后维护人名称

	private Date mntDate;//最后维护人名称

	private Integer version;

	private Integer sortNum;//排序

	private String des;//描述

	public TPmBizOrgDTL() {
		super();
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAlias() {
		return bizAlias;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public Long getDtlId() {
		return dtlId;
	}

	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}


	public Long getExpendFlag() {
		return expendFlag;
	}

	public void setExpendFlag(Long expendFlag) {
		this.expendFlag = expendFlag;
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

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getParentOrgAlias() {
		return parentOrgAlias;
	}

	public void setParentOrgAlias(String parentOrgAlias) {
		this.parentOrgAlias = parentOrgAlias;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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


	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}



	
}
