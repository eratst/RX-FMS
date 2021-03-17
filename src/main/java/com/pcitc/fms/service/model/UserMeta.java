package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class UserMeta extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;
	// Query
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private int top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private int skip;
	
	private Integer userId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
	private String code;

	@ResourceMember(InQueries = "condition", Name = "name")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String name;

	@ResourceMember(InQueries = "condition", Name = "userType")
	@CheckField(CheckName=CheckNameType.ID )
	private Integer userType;
	
	@CheckField(CheckName=CheckNameType.ID )
	private Integer userClass;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String jobDesc;

//	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String email;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String tel;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String mobile;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String phoneIMEI;

	private Integer isAdmin;

	private Integer sex;
	
	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String address;

	private Date birthday;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String employeeId;

	private Date expiredTime;

//	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String password;
	
	@CheckField(CheckName=CheckNameType.ID )
	private Integer parentId;

	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	private String parentType;
	
	@CheckField(CheckName=CheckNameType.ID )
	private Integer creatorId;
	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;
	
	@CheckField(CheckName = CheckNameType.CREATETIME)
	private Date createTime;
	
	@CheckField(CheckName=CheckNameType.ID )
	private Integer editorId;
	
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength= 50)
	private String editor;
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime;

	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition", Name = "enabled")
	private Integer enabled;

	private Integer orderId;
	
	@CheckField(CheckName = CheckNameType.DES,StrLength=200)
	private String des;

	public UserMeta() {
		super();
	}

	public UserMeta(Integer userId, String name, Integer userType, Integer enabled, List<Integer> idList,
			List<String> codeList, int top, int skip) {
		super();
		this.userId = userId;
		this.name = name;
		this.userType = userType;
		this.enabled = enabled;
		this.idList = idList;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

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
