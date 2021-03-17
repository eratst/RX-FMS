package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_USERCONTRAST")
public class UserContrast implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USERCONTRAST_ID")
	private Long UserContrastId;
	
	@Column(name = "OUM_USERID")
	private Long OumUserId;
	
	@Column(name = "FM_USERID")
	private Long FmUserId;
	
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

	public Long getUserContrastId() {
		return UserContrastId;
	}

	public void setUserContrastId(Long userContrastId) {
		UserContrastId = userContrastId;
	}

	public Long getOumUserId() {
		return OumUserId;
	}

	public void setOumUserId(Long oumUserId) {
		OumUserId = oumUserId;
	}

	public Long getFmUserId() {
		return FmUserId;
	}

	public void setFmUserId(Long fmUserId) {
		FmUserId = fmUserId;
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
