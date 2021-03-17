package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class Administration extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Long administrationId;
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	@BuildLink(resourceCode = "administrationCode")
	private String administrationCode;
	//区域名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="administrationName")
	private String administrationName;
	//区域类型Id
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Long areaTypeId;
	//所属组织机构（工厂）
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Long orgId;
	//区域简称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="administrationAlias")
	private String administrationAlias;
	//创建人Id
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=30)
	private String crtUserCode;
	//创建人名称
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String crtUserName;
	//创建时间
	
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Date crtDate;
	//最后维护人Id
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=30)
	private String mntUserCode;
	//最后维护人
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
	private String mntUserName;
	//维护日期
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Date mntDate;
	//排序
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer sortNum;
	//描述
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	//乐观锁版本
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(OnlyQuery=true)
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.ENABLED ,StrLength=10)
	private Integer inUse;
	//工厂code，从url获取12.2
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String orgCode;
	//区域类型,从url中获取，12.2
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private String typeByUrl;
	//codelist,从url中获取，12.2
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private String codeListStr;
	
	private String orgName;
	
	private String orgAlias;
	//区域类型名称
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private String areaType;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST ,StrLength=100)
	private List<String> areaCodes;
	
	@CheckField(CheckName = CheckNameType.CODELIST ,StrLength=100)
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$orgs")
	private List<String> orgs;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO ,StrLength=100)
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private Integer top;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO ,StrLength=100)
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private Integer skip;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	
	
	
	
	public String getAreaLatitude() {
		return areaLatitude;
	}



	public void setAreaLatitude(String areaLatitude) {
		this.areaLatitude = areaLatitude;
	}



	public String getAreaAltitude() {
		return areaAltitude;
	}



	public void setAreaAltitude(String areaAltitude) {
		this.areaAltitude = areaAltitude;
	}



	public String getAreaLongitude() {
		return areaLongitude;
	}



	public void setAreaLongitude(String areaLongitude) {
		this.areaLongitude = areaLongitude;
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



	public String getOrderby() {
		return orderby;
	}



	public void setOrderby(String orderby) {
		this.orderby = orderby;
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



	public Administration() {
		super();
	}


	public Long getAdministrationId() {
		return administrationId;
	}



	public void setAdministrationId(Long administrationId) {
		this.administrationId = administrationId;
	}



	public Long getAreaTypeId() {
		return areaTypeId;
	}



	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}



	public Long getOrgId() {
		return orgId;
	}



	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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



	public String getAdministrationCode() {
		return administrationCode;
	}



	public void setAdministrationCode(String administrationCode) {
		this.administrationCode = administrationCode;
	}



	public String getAdministrationName() {
		return administrationName;
	}



	public void setAdministrationName(String administrationName) {
		this.administrationName = administrationName;
	}


	public String getAdministrationAlias() {
		return administrationAlias;
	}



	public void setAdministrationAlias(String administrationAlias) {
		this.administrationAlias = administrationAlias;
	}


	public Integer getSortNum() {
		return sortNum;
	}



	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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



	public String getOrgCode() {
		return orgCode;
	}



	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



	public String getTypeByUrl() {
		return typeByUrl;
	}



	public void setTypeByUrl(String typeByUrl) {
		this.typeByUrl = typeByUrl;
	}



	public String getCodeListStr() {
		return codeListStr;
	}



	public void setCodeListStr(String codeListStr) {
		this.codeListStr = codeListStr;
	}



	public String getAreaType() {
		return areaType;
	}



	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}



	public List<String> getAreaCodes() {
		return areaCodes;
	}



	public void setAreaCodes(List<String> areaCodes) {
		this.areaCodes = areaCodes;
	}



	public List<String> getOrgs() {
		return orgs;
	}



	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
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
	

}
