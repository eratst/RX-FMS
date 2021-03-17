package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

public class UserMeta implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String code;
	private String name;
	private Integer userType;
	private Integer userClass;
	private String jobDesc;
	private String email;
	private String tel;
	private String mobile;
	private String phoneIMEI;
	private Integer isAdmin;
	private Integer sex;
	private String address;
	private Date birthday;
	private String employeeId;
	private Date expiredTime;
	private String password;
	private Integer parentId;
	private String parentType;
	private Integer creatorId;
	private String creator;
	private Date createTime;
	private Integer editorId;
	private String editor;
	private Date maintainTime;
	private Integer enabled;
	private Integer orderId;
	private String des;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getUserClass() {
		return userClass;
	}
	public void setUserClass(Integer userClass) {
		this.userClass = userClass;
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
	public String getPhoneIMEI() {
		return phoneIMEI;
	}
	public void setPhoneIMEI(String phoneIMEI) {
		this.phoneIMEI = phoneIMEI;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getEditorId() {
		return editorId;
	}
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

}
