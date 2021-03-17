package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

@Entity
@Table(name = "T_PM_ORG")
@Inheritance(strategy = InheritanceType.JOINED)
public class NewOrg implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	@Column(name = "ORG_CODE")
	private String orgCode;
	
	@Column(name = "ORG_NAME")
	private String orgName;
	
	@Column(name = "ORG_ALIAS")
	private String orgAlias;
	
	@Column(name = "ORG_TYPE_ID")
	private Integer orgTypeId;
	
	@Column(name = "DATA_STATUS")
	private Integer dataStatus;
	
	@Column(name = "CRT_USER_ID")
	private String crtUserId;
	
	@Column(name = "CRT_USER_NAME")
	private String crtUserName;
	
	@Column(name = "CRT_DATE")
	private Date crtDate;
	
	@Column(name = "MNT_USER_ID")
	private String mntUserId;
	
	@Column(name = "MNT_USER_NAME")
	private String mntUsrName;
	
	@Column(name = "MNT_DATE")
	private Date mntDate;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_TYPE_ID", insertable = false, updatable = false)
	private OrgType orgType;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
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

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public String getMntUsrName() {
		return mntUsrName;
	}

	public void setMntUsrName(String mntUsrName) {
		this.mntUsrName = mntUsrName;
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

	public OrgType getOrgType() {
		return orgType;
	}

	public void setOrgType(OrgType orgType) {
		this.orgType = orgType;
	}
	
}
