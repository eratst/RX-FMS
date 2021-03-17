package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_PM_USERPOSITION")
public class UserPosition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USERPOSITION_ID")
	private Long userPositionId;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Transient
	private String userCode;
	
	@Transient
	private String userName;
	
	@Column(name = "POSITIONORG_ID")
	private Long positionOrgId;
	
	@Transient
	private String positionCode;
	
	@Transient
	private String orgCode;
	
	@Transient
	private String positionName;
	
	@Transient
	private String orgName;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;
	
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	
	@Column(name = "CRTDATE")
	private Date crtDate;
	
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;
	
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;
	
	@Column(name = "MNTDATE")
	private Date mntDate;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	public UserPosition() {
	}

	public UserPosition(Long userPositionId, Long userId, String userCode, String userName, Long positionOrgId,
			String positionCode, String orgCode, String positionName, String orgName, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
			Integer version, Integer inUse, Integer sortNum) {
		super();
		this.userPositionId = userPositionId;
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.positionOrgId = positionOrgId;
		this.positionCode = positionCode;
		this.orgCode = orgCode;
		this.positionName = positionName;
		this.orgName = orgName;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.version = version;
		this.inUse = inUse;
		this.sortNum = sortNum;
	}

	public Long getUserPositionId() {
		return userPositionId;
	}

	public void setUserPositionId(Long userPositionId) {
		this.userPositionId = userPositionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPositionOrgId() {
		return positionOrgId;
	}

	public void setPositionOrgId(Long positionOrgId) {
		this.positionOrgId = positionOrgId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	
}
