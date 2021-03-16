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
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class TeamAndUser  extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate = false)
	private Long teamUserId;
	
	@CheckField(CheckName = CheckNameType.ID,Explain="用户ID")
	@ResourceMember(InTemplate = false)
	private Long userId;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="用户编码")
	@ResourceMember(InQueries = "condition", Name = "userCode")
	private String userCode;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80,Explain="用户名称")
	@ResourceMember(InQueries = "condition", Name = "userName",InTemplate = false)
	private String userName;
	
	@CheckField(CheckName = CheckNameType.ID,Explain="班组ID")
	@ResourceMember(InTemplate = false)
	private Long orgId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="班组编码")
	@ResourceMember(InQueries = "condition", Name = "orgCode")
	private String orgCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80,Explain="班组名称")
	@ResourceMember(InQueries = "condition", Name = "orgName",InTemplate = false)
	private String orgName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80,Explain="所属组织机构简称")
	private String orgAlias;
	
	@ResourceMember(OnlyQuery = true)
	private String crtUserCode;
	
	@ResourceMember(OnlyQuery = true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery = true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery = true)
	private String mntUserCode;
	
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery = true)
	private Date mntDate;
	
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200,Explain="描述")
	private String des;
	
	@ResourceMember(OnlyQuery = true)
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.ENABLED,Explain="是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;
	
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10,Explain="排序")
	private Integer sortNum;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	private Integer skip = 0;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery=true)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery=true)
	private String bizCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.ORDER)
	private String orderby;

	public TeamAndUser() {
		super();
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
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

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	

}
