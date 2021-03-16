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
@Table(name = "T_PM_USER")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_ID")
	@SpecialResource(name="user.nodeId")
	private Long userId;
	
	@Column(name = "ORG_ID")
	@SpecialResource(name="user.orgId")
	private Long orgId;

	@Transient
	@SpecialResource(name="org.orgCode")
	private String orgCode;

	@Transient
	@SpecialResource(name="org.orgName")
	private String orgName;

	@Transient
	@SpecialResource(name="org.orgAlias")
	private String orgAlias;
	
	@Column(name = "USER_CODE")
	@SpecialResource(name="user.userCode")
	private String userCode;
	
	@Column(name = "USER_NAME")
	@SpecialResource(name="user.userName")
	private String userName;
	
	@Column(name = "JOB_DESC")
	@SpecialResource(name="user.jobDesc")
	private String jobDesc;
	
	@Column(name = "EMAIL")
	@SpecialResource(name="user.email")
	private String email;
	
	@Column(name = "TEL")
	@SpecialResource(name="user.tel")
	private String tel;
	
	@Column(name = "MOBILE")
	@SpecialResource(name="user.mobile")
	private String mobile;
	
	@Column(name = "SEX")
	@SpecialResource(name="user.sex")
	private Integer sex;
	
	@Column(name = "BIRTHDAY")
	@SpecialResource(name="user.birthday")
	private Date birthday;
	
	@Column(name = "EMPLOYEE_ID")
	@SpecialResource(name="user.employeeId")
	private String employeeId;
	
	@Column(name = "EXPIRED_TIME")
	@SpecialResource(name="user.expiredTime")
	private Date expiredTime;
	
	@Column(name = "APP_USER_CODE")
	@SpecialResource(name="user.appUserCode")
	private String appUserCode;
	
	@Column(name = "CRTUSER_CODE")
	@SpecialResource(name="user.crtUserCode")
	private String crtUserCode;
	
	@Column(name = "CRTUSER_NAME")
	@SpecialResource(name="user.crtUserName")
	private String crtUserName;
	
	@Column(name = "CRTDATE")
	@SpecialResource(name="user.crtDate")
	private Date crtDate;
	
	@Column(name = "MNTUSER_CODE")
	@SpecialResource(name="user.mntUserCode")
	private String mntUserCode;
	
	@Column(name = "MNTUSER_NAME")
	@SpecialResource(name="user.mntUserName")
	private String mntUserName;
	
	@Column(name = "MNTDATE")
	@SpecialResource(name="user.mntDate")
	private Date mntDate;
	
	@Column(name = "DES")
	@SpecialResource(name="user.des")
	private String des;
	
	@Column(name = "VERSION")
	@SpecialResource(name="user.version")
	private Integer version;
	
	@Column(name = "INUSE")
	@SpecialResource(name="user.inUse")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	@SpecialResource(name="user.sortNum")
	private Integer sortNum;

	public User() {
		super();
	}

	public User(Long userId, Long orgId, String orgCode, String orgName, String orgAlias, String userCode,
			String userName, String jobDesc, String email, String tel, String mobile, Integer sex, Date birthday,
			String employeeId, Date expiredTime, String appUserCode, String crtUserCode, String crtUserName,
			Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des, Integer version,
			Integer inUse, Integer sortNum) {
		this.userId = userId;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.userCode = userCode;
		this.userName = userName;
		this.jobDesc = jobDesc;
		this.email = email;
		this.tel = tel;
		this.mobile = mobile;
		this.sex = sex;
		this.birthday = birthday;
		this.employeeId = employeeId;
		this.expiredTime = expiredTime;
		this.appUserCode = appUserCode;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getAppUserCode() {
		return appUserCode;
	}

	public void setAppUserCode(String appUserCode) {
		this.appUserCode = appUserCode;
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
