package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_TEAMADNUSER")
public class TeamAndUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TEAMADNUSER_ID")
	@SpecialResource(name="teamAndUser.teamUserId")
	private Long teamUserId;
	
	@Column(name = "USER_ID")
	@SpecialResource(name="teamAndUser.userId")
	private Long userId;
	
	@Transient
	@SpecialResource(name="user.userCode")
	private String userCode;
	
	@Transient
	@SpecialResource(name="user.userName")
	private String userName;
	
	@Column(name = "ORG_ID")
	@SpecialResource(name="teamAndUser.orgId")
	private Long orgId;//班组ID

	@Transient
	@SpecialResource(name="team.orgCode")
	private String orgCode;//班组编码

	@Transient
	@SpecialResource(name="org.orgName")
	private String orgName;//班组名称

	@Transient
	@SpecialResource(name="org1.orgAlias")
	private String orgAlias;//内部组织机构
	
	@Column(name = "CRTUSER_CODE")
	@SpecialResource(name="teamAndUser.crtUserCode")
	private String crtUserCode;
	
	@Column(name = "CRTUSER_NAME")
	@SpecialResource(name="teamAndUser.crtUserName")
	private String crtUserName;
	
	@Column(name = "CRTDATE")
	@SpecialResource(name="teamAndUser.crtDate")
	private Date crtDate;
	
	@Column(name = "MNTUSER_CODE")
	@SpecialResource(name="teamAndUser.mntUserCode")
	private String mntUserCode;
	
	@Column(name = "MNTUSER_NAME")
	@SpecialResource(name="teamAndUser.mntUserName")
	private String mntUserName;
	
	@Column(name = "MNTDATE")
	@SpecialResource(name="teamAndUser.mntDate")
	private Date mntDate;
	
	@Column(name = "DES")
	@SpecialResource(name="teamAndUser.des")
	private String des;
	
	@Column(name = "VERSION")
	@SpecialResource(name="teamAndUser.version")
	private Integer version;
	
	@Column(name = "INUSE")
	@SpecialResource(name="teamAndUser.inUse")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	@SpecialResource(name="teamAndUser.sortNum")
	private Integer sortNum;

	public TeamAndUser() {
		super();
	}

	public TeamAndUser(Long teamUserId, Long userId, String userCode, String userName, Long orgId, String orgCode,
			String orgName, String orgAlias, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, String des, Integer version, Integer inUse, Integer sortNum) {
		this.teamUserId = teamUserId;
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
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


	public Long getTeamUserId() {
		return teamUserId;
	}

	public void setTeamUserId(Long teamUserId) {
		this.teamUserId = teamUserId;
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
	
	
	

}
