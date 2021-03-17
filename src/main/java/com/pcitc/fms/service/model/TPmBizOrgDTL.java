package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class TPmBizOrgDTL extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	@ResourceMember( OnlyQuery = true)
	private Long dtlId;  //主键iD

	@ResourceMember( OnlyQuery = true)
	private Long orgId;  //组织机构ID
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "orgCode")
	@BuildLink(resourceCode = "orgCode")
	private String orgCode;//组织机构编码

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "orgName")
	private String orgName; //组织机构名称

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "orgAlias")
	private String orgAlias; //组织机构简称
	
	@ResourceMember(OnlyQuery=true)
	private Long orgTypeId; //组织机构类型

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries="condition",InTemplate=false)
	private String orgTypeCode; //组织机构类型(用于显示)
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InTemplate=false)
	private String orgTypeName; //组织机构类型(用于显示)

	@ResourceMember( OnlyQuery = true)
	private Long parentOrgId;//上一级组织机构ID
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	private String parentOrgCode;//上一级组织机构编码
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InTemplate=false)
	private String parentOrgName;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InTemplate=false)
	private String parentOrgAlias;
	
	@ResourceMember( OnlyQuery = true)
	private Long bizId; //业务域id
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(Name = "bizCode",InTemplate = false)
	private String bizCode; //业务域CODE

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(Name = "bizName",InTemplate = false)
	private String bizName; //业务域名称

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "bizAlias",InTemplate = false)
	private String bizAlias; //业务域简称

	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(InQueries = "condition",Name = "expendFlag")
	private Long expendFlag;//扩展标识
	
	@ResourceMember( OnlyQuery = true)
	private String crtUserCode; //创建人ID
	
	@ResourceMember( OnlyQuery = true)
	private String crtUserName;//创建人名称
	
	@ResourceMember( OnlyQuery = true)
	private Date crtDate;//创建时间
	
	@ResourceMember( OnlyQuery = true)
	private String mntUserCode;//最后维护人ID
	
	@ResourceMember( OnlyQuery = true)
	private String mntUserName;//最后维护人名称
	
	@ResourceMember( OnlyQuery = true)
	private Date mntDate;//最后维护时间

	@ResourceMember( OnlyQuery = true)
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;//状态
	
	@CheckField(CheckName = CheckNameType.DES)
	private String des;//描述
	
	@CheckField(CheckName = CheckNameType.SORTNUM)
	private Integer sortNum;//排序

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orgCodes")
	private List<String> orgCodes;

	@CheckField(CheckName = CheckNameType.TREE)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$operType")
	private String operType;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InQueries = "condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InQueries = "condition", OnlyQuery = true, Name = "$skip")
	private Integer skip =  0;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember( OnlyQuery = true)
	private String orderby ;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	
	
	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public TPmBizOrgDTL() {
		super();
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Long getDtlId() {
		return dtlId;
	}

	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAlias() {
		return bizAlias;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public List<String> getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(List<String> orgCodes) {
		this.orgCodes = orgCodes;
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

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}


	public Long getExpendFlag() {
		return expendFlag;
	}

	public void setExpendFlag(Long expendFlag) {
		this.expendFlag = expendFlag;
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


	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getParentOrgAlias() {
		return parentOrgAlias;
	}

	public void setParentOrgAlias(String parentOrgAlias) {
		this.parentOrgAlias = parentOrgAlias;
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


	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}



	
}
