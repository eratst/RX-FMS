package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

@Entity
@Table(name = "T_PM_AREA")
@Inheritance(strategy = InheritanceType.JOINED)
public class NewArea implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AREA_ID")
	private Integer areaId;
	
	@Column(name = "AREA_CODE")
	private String areaCode;
	
	@Column(name = "AREA_NAME")
	private String areaName;
	
	@Column(name = "AREA_ALIAS")
	private String areaAlias;
	
	@Column(name = "AREA_TYPE_ID")
	private Integer areaTypeId;
	
	@Column(name = "PARENT_AREA_ID")
	private Integer parentAreaId;
	
	@Column(name = "DATA_STATUS")
	private Integer dataStatus;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_TYPE_ID", insertable = false, updatable = false)
	private AreaType areaType;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_AREA_ID", insertable = false, updatable = false)
	private NewOrg newOrg;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_ID", insertable = false, updatable = false)
	private NewTankArea newTankArea;
	
	
	public NewTankArea getNewTankArea() {
		return newTankArea;
	}

	public void setNewTankArea(NewTankArea newTankArea) {
		this.newTankArea = newTankArea;
	}

	public NewOrg getNewOrg() {
		return newOrg;
	}

	public void setNewOrg(NewOrg newOrg) {
		this.newOrg = newOrg;
	}

	public NewArea() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	public Integer getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public Integer getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(Integer parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public AreaType getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
	}
	
}
