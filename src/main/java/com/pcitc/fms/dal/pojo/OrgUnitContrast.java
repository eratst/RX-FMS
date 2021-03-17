package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_ORGUNITCONTRAST")
public class OrgUnitContrast implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ORGUNITCONTRAST_ID")
	private Long orgUnitContrastId;
	
	@Column(name = "OUM_ORGUNITID")
	private Long oumOrgUnitId;
	
	@Column(name = "FM_ORGID")
	private Long fmOrgId;
	
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
	
	
	
	public OrgUnitContrast() {
		super();
	}

	public OrgUnitContrast(Long orgUnitContrastId, Long oumOrgUnitId, Long fmOrgId, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
			Integer sortNum, Integer version, Integer inUse) {
		super();
		this.orgUnitContrastId = orgUnitContrastId;
		this.oumOrgUnitId = oumOrgUnitId;
		this.fmOrgId = fmOrgId;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.inUse = inUse;
	}

	

	public Long getOrgUnitContrastId() {
		return orgUnitContrastId;
	}

	public void setOrgUnitContrastId(Long orgUnitContrastId) {
		this.orgUnitContrastId = orgUnitContrastId;
	}

	public Long getOumOrgUnitId() {
		return oumOrgUnitId;
	}

	public void setOumOrgUnitId(Long oumOrgUnitId) {
		this.oumOrgUnitId = oumOrgUnitId;
	}

	public Long getFmOrgId() {
		return fmOrgId;
	}

	public void setFmOrgId(Long fmOrgId) {
		this.fmOrgId = fmOrgId;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	

}
