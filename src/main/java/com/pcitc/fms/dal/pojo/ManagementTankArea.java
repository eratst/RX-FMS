package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_PM_TANKAREA")
public class ManagementTankArea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AREA_ID")
	private Integer areaId;
	
	@Column(name = "AREA_CODE")
	private String areaCode;
	
	@Transient
	private String areaName;
	
	@Transient
	private String areaSname;
	
	@Column(name = "TANKAREA_TYPE_ID")
	private Integer tankAreaTypeId;
	
	@Column(name = "TECHNIC_ID")
	private Integer technicId;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Transient
	private Integer inUse;
	
	@Transient
	private String orgCode;
	
	@Transient
	private String orgName;
	
	@Transient
	private String orgSname;
	
	@Transient
	private String tankAreaTypeCode;
	
	@Transient
	private String tankAreaTypeName;
	
	@Transient
	private String technicCode;
	
	@Transient
	private String technicName;
	
	public ManagementTankArea() {
		super();
	}
	
	public ManagementTankArea(Integer areaId, String areaCode, String areaName, String areaSname,
			Integer tankAreaTypeId, Integer technicId, Integer sortNum, Integer inUse, String orgCode, String orgName,
			String orgSname, String tankAreaTypeCode, String tankAreaTypeName, String technicCode,
			String technicName) {
		super();
		this.areaId = areaId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaSname = areaSname;
		this.tankAreaTypeId = tankAreaTypeId;
		this.technicId = technicId;
		this.sortNum = sortNum;
		this.inUse = inUse;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgSname = orgSname;
		this.tankAreaTypeCode = tankAreaTypeCode;
		this.tankAreaTypeName = tankAreaTypeName;
		this.technicCode = technicCode;
		this.technicName = technicName;
	}


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaSname() {
		return areaSname;
	}

	public void setAreaSname(String areaSname) {
		this.areaSname = areaSname;
	}

	public Integer getTankAreaTypeId() {
		return tankAreaTypeId;
	}

	public void setTankAreaTypeId(Integer tankAreaTypeId) {
		this.tankAreaTypeId = tankAreaTypeId;
	}

	public Integer getTechnicId() {
		return technicId;
	}

	public void setTechnicId(Integer technicId) {
		this.technicId = technicId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgSname() {
		return orgSname;
	}

	public void setOrgSname(String orgSname) {
		this.orgSname = orgSname;
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

	
	
	
}
