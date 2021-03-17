package com.pcitc.fms.dal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

@Entity
@Table(name = "T_PM_TANKAREA")
//@PrimaryKeyJoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
public class NewTankArea {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AREA_ID")
	private Integer areaId;
	
	@Column(name = "TANKAREA_TYPE_ID")
	private Integer tankAreaTypeId;
	
	@Column(name = "TECHNIC_ID")
	private Integer technicId;
	
	@Column(name = "CRT_USER_ID")
	private String crtUserId;
	
	@Column(name = "CRT_USER_NAME")
	private String crtUserName;
	
	@Column(name = "CRT_DATE")
	private Date crtDate;
	
	@Column(name = "MNT_USER_ID")
	private String mntUserId;
	
	@Column(name = "MNT_USER_NAME")
	private String mntUserName;
	
	@Column(name = "MNT_DATE")
	private Date mntDate;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TANKAREA_TYPE_ID", insertable = false, updatable = false)
	private TankAreaType tankAreaType;

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

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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

	public TankAreaType getTankAreaType() {
		return tankAreaType;
	}

	public void setTankAreaType(TankAreaType tankAreaType) {
		this.tankAreaType = tankAreaType;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}
