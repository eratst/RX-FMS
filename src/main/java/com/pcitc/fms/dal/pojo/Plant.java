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
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.dispatcher.SysGlobal;


@Entity
@Table(name = "T_PM_UNIT")
public class Plant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column( name = "AREA_ID")
	private Long plantId;
	//区域编码
	@Column( name="AREA_CODE")
	private String plantCode;
	//排序
	@Column( name="SORT_NUM")
	private Integer sortNum;
	//乐观锁版本
	@Column( name="VERSION")
	private Integer version;
	//装置业务类型
	@Column(name="UNITTYPE_ID")
	private Long unitTypeId;
	//装置工艺类型
	@Column(name="UNITTECHNIC_ID")
	private Long technicId;
	//加工能力
	@Column(name="CAPACITY")
	private String capacity;
	@Transient
	private String crtUserCode; // 创建人ID

	@Transient
	private String crtUserName;// 创建人名称

	@Transient
	private Date crtDate;// 创建时间

	@Transient
	private String mntUserCode;// 最后维护人ID

	@Transient
	private String mntUserName;// 最后维护人名称

	@Transient
	private Date mntDate;//维护时间
	
	@Transient
	private Integer inUse;
	
	public Plant() {
		super();
	}

	public Plant(Long plantId, String plantCode, String crtUserCode, String crtUserName,
			Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer sortNum,
			Integer version, Long unitTypeId, Long technicId, String capacity,Integer inUse) {
		super();
		this.plantId = plantId;
		this.plantCode = plantCode;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.version = version;
		this.unitTypeId = unitTypeId;
		this.technicId = technicId;
		this.capacity = capacity;
		this.inUse=inUse;
	}

	
	

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public Long getPlantId() {
		return plantId;
	}

	public void setPlantId(Long plantId) {
		this.plantId = plantId;
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

	public void setUnitTypeId(Long unitTypeId) {
		this.unitTypeId = unitTypeId;
	}

	public void setTechnicId(Long technicId) {
		this.technicId = technicId;
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


	public Long getUnitTypeId() {
		return unitTypeId;
	}

	public Long getTechnicId() {
		return technicId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	
	
}
