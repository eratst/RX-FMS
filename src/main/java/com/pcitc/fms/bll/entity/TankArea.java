package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
public class TankArea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long tankAreaId;
	private String tankAreaCode;
	private String tankAreaName;
	private String tankAreaAlias;
	private Long orgId;
	private String orgCode;
	private String orgName;
	private String orgAlias;
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	private Integer sortNum;
	private String des;
	private Integer inUse;
	private Integer version;
	private Long tankAreaTypeId;
	private String tankAreaTypeCode;
	private String tankAreaTypeName;
	private Long technicId;
	private String technicCode;
	private String technicName;
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	
	
	public Long getTankAreaId() {
		return tankAreaId;
	}
	public void setTankAreaId(Long tankAreaId) {
		this.tankAreaId = tankAreaId;
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
	public String getMntUserCode() {
		return mntUserCode;
	}
	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}
	public Long getTankAreaTypeId() {
		return tankAreaTypeId;
	}
	public void setTankAreaTypeId(Long tankAreaTypeId) {
		this.tankAreaTypeId = tankAreaTypeId;
	}
	public Long getTechnicId() {
		return technicId;
	}
	public void setTechnicId(Long technicId) {
		this.technicId = technicId;
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
	public String getTechnicCode() {
		return technicCode;
	}
	public void setTechnicCode(String technicCode) {
		this.technicCode = technicCode;
	}
	public String getTechnicName() {
		return technicName;
	}
	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}
	private List codeList;
	private List idList;
	public List getCodeList() {
		return codeList;
	}
	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}
	public List getIdList() {
		return idList;
	}
	public void setIdList(List idList) {
		this.idList = idList;
	}
	public String getTankAreaCode() {
		return tankAreaCode;
	}
	public void setTankAreaCode(String tankAreaCode) {
		this.tankAreaCode = tankAreaCode;
	}
	public String getTankAreaName() {
		return tankAreaName;
	}
	public void setTankAreaName(String tankAreaName) {
		this.tankAreaName = tankAreaName;
	}
	public String getTankAreaAlias() {
		return tankAreaAlias;
	}
	public void setTankAreaAlias(String tankAreaAlias) {
		this.tankAreaAlias = tankAreaAlias;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getTankAreaTypeCode() {
		return tankAreaTypeCode;
	}
	public void setTankAreaTypeCode(String tankAreaTypeCode) {
		this.tankAreaTypeCode = tankAreaTypeCode;
	}
	public String getTankAreaTypeName() {
		return tankAreaTypeName;
	}
	public void setTankAreaTypeName(String tankAreaTypeName) {
		this.tankAreaTypeName = tankAreaTypeName;
	}

	
	
}
