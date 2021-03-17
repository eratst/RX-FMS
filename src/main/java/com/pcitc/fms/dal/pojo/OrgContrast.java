package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_ORGCONTRAST")
public class OrgContrast implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ORGCONTRAST_ID")
	private Long OrgContrastId;
	
	@Column(name = "OUM_ORGID")
	private Long OumOrgId;
	
	@Column(name = "FM_ORGID")
	private Long FmOrgId;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode = "admin"; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName = "admin";// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate = new Date();// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode = "admin";// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName = "admin";// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate = new Date();//维护时间
	
	@Column(name="DES")
	private String des;
	
	@Column(name="SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Long getOrgContrastId() {
		return OrgContrastId;
	}

	public void setOrgContrastId(Long orgContrastId) {
		OrgContrastId = orgContrastId;
	}

	public Long getOumOrgId() {
		return OumOrgId;
	}

	public void setOumOrgId(Long oumOrgId) {
		OumOrgId = oumOrgId;
	}

	public Long getFmOrgId() {
		return FmOrgId;
	}

	public void setFmOrgId(Long fmOrgId) {
		FmOrgId = fmOrgId;
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
	
	

}
