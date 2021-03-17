package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "T_PM_OUMUSER")
public class OumUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户Id
	 */
	@Id
	@Column(name = "USER_ID")
	private Long userId;
	/**
	 * 用户编码
	 */
	@Column(name = "USER_CODE")
	private String userCode;
	/**
	 * 用户姓名
	 */
	@Column(name = "USER_NAME")
	private String userName;
	/**
	 * 职位描述
	 */
	@Column(name = "JOB_DESC")
	private String jobDesc;
	/**
	 * 电子邮件
	 */
	@Column(name = "EMAIL")
	private String email;
	/**
	 * 办公电话
	 */
	@Column(name = "TEL")
	private String tel;
	/**
	 * 移动电话
	 */
	@Column(name = "MOBILE")
	private String mobile;
	/**
	 * 性别
	 */
	@Column(name = "SEX")
	private Long sex;
	/**
	 * 出生日期
	 */
	@Column(name = "BIRTHDAY")
	private Date birthday;
	/**
	 * 用户所属机构单元id
	 */
	@Column(name = "ORG_UNIT_ID")
	private Long orgUnitId;
	/**
	 * 员工编号
	 */
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	/**
	 * 用户过期时间
	 */
	@Column(name = "EXPIRED_TIME")
	private Date expiredTime;
	/**
	 * 密码
	 */
	@Column(name = "APP_USER_CODE")
	private String appUserCode;
	/**
	 * 岗位编码
	 */
	@Column(name = "POSITION_CODE")
	private String postionCode;
	/**
	 * 岗位ID 
	 */
	@Column(name = "POSITION_ID")
	private Long postionId;
	/**
	 * 绑定Id
	 */
	@Column(name = "OPEN_ID")
	private String openId;
	/**
	 * 身份证号码
	 */
	@Column(name = "IDENTITY_CARD_NUMBER")
	private String idNumber;
	
	/**
	 * 创建人Id
	 */
	@Column(name = "CRT_USER_ID")
	private String crtUserId = "admin";
	/**
	 * 创建人Name
	 */
	@Column(name = "CRT_USER_NAME")
	private String crtUserName = "admin";
	/**
	 * 创建时间
	 */
	@Column(name = "CRT_USER_DATE")
	private Date crtUserDate = new Date();
	/**
	 * 修改人Id
	 */
	@Column(name = "MNT_USER_ID")
	private String mntUserId = "admin";
	/**
	 * 修改人Name
	 */
	@Column(name = "MNT_USER_NAME")
	private String mntUserName = "admin";
	/**
	 * 修改时间
	 */
	@Column(name = "MNT_USER_DATE")
	private Date mntUserDate = new Date();

	/**
	 * 乐观锁
	 */
	@Version
	@Column(name = "VERSION")
	private Integer version;

	/**
	 * 是否启用
	 */
	@Column(name = "ENABLED")
	private Long enabled = 1l;

	/**
	 * 排序
	 */
	@Column(name = "ORDER_ID")
	private Long orderId = 1l;

	/**
	 * 备注
	 */
	@Column(name = "DES")
	private String des="";
	
	/**
	 * 是否配置
	 */
	@Column(name = "ISDEPLOY")
	private Integer isDeploy;
	
	
	

	public Integer getIsDeploy() {
		return isDeploy;
	}

	public void setIsDeploy(Integer isDeploy) {
		this.isDeploy = isDeploy;
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

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Long orgUnitId) {
		this.orgUnitId = orgUnitId;
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

	public String getPostionCode() {
		return postionCode;
	}

	public void setPostionCode(String postionCode) {
		this.postionCode = postionCode;
	}

	public Long getPostionId() {
		return postionId;
	}

	public void setPostionId(Long postionId) {
		this.postionId = postionId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	public Date getCrtUserDate() {
		return crtUserDate;
	}

	public void setCrtUserDate(Date crtUserDate) {
		this.crtUserDate = crtUserDate;
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

	public Date getMntUserDate() {
		return mntUserDate;
	}

	public void setMntUserDate(Date mntUserDate) {
		this.mntUserDate = mntUserDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getEnabled() {
		return enabled;
	}

	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}
