package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

@Entity
@Table(name = "T_FMS_USER")
public class UserMeta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GENERATOR")
	@SequenceGenerator(name = "USER_GENERATOR", sequenceName = "SEQ_T_FMS_USER", allocationSize = 1)
	private Integer userId;

	@RegionMember
	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "USER_TYPE")
	private Integer userType;
	
	@Column(name = "USER_CLASS")
	private Integer userClass;
	
	@Column(name = "JOB_DESC")
	private String jobDesc;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TEL")
	private String tel;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "PHONE_IMEI")
	private String phoneIMEI;
	
	@Column(name = "ISADMIN")
	private Integer isAdmin;
	
	@Column(name = "SEX")
	private Integer sex;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name = "EXPIRED_TIME")
	private Date expiredTime;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PARENT_ID")
	private Integer parentId;
	
	@Column(name = "PARENT_TYPE")
	private String parentType;
	
	@Column(name = "CREATOR_ID")
	private Integer creatorId;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "CREATE_TIME", insertable = false)
	private Date createTime;
	
	@Column(name = "EDITOR_ID")
	private Integer editorId;
	
	@Column(name = "EDITOR")
	private String editor;
	
	@Column(name = "MAINTAIN_TIME", insertable = false)
	private Date maintainTime;
	
	@Column(name = "ENABLED")
	private Integer enabled;
	
	@Column(name = "ORDERID")
	private Integer orderId;
	
	@Column(name = "DES")
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
