package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;


public class Administration implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long administrationId;
	//区域编码
	private String administrationCode;
	//区域类型Id
	private Long areaTypeId;
	//区域名称
	private String administrationName;
	//区域简称
	private String administrationAlias;
	//所属组织机构（工厂）
	private Long orgId;
	
	private String orgCode;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	//排序
	private Integer sortNum;
	//描述
	private String des;
	//乐观锁版本
	private Integer version;
	//状态
	private Integer inUse;
	//区域类型名称
	private String areaType;
	
	private String orgName;
	
	private String orgAlias;
	
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	

	
	
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


	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
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
	
}
