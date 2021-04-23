package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * [模型表]3.1 装置对象
 * 
 * @author zhao.li
 *
 */
@Entity
@Table(name = "T_PM_UNITAREAREL")
public class UnitAreaRel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 装置与装置界区ID
	 */
	@Id
	@Column(name = "UNIT_AREA_REL_ID")
	private Long unitAreaRelId;
	
	/**
	 * 装置ID
	 */
	@Column(name = "AREA_ID")
	private Long unitId;

	@Transient
	private String unitCode;

	@Transient
	private String unitName;
	/**
	 * 装置界区ID
	 */
	@Column(name = "UNIT_AREA_ID")
	private Long unitAreaId;

	@Transient
	private String unitAreaCode;

	@Transient
	private String unitAreaName;

	/**
	 * 状态
	 */
	@Column(name = "INUSE")
	private Integer dataStatus;

	/**
	 * 创建人ID
	 */
	@Transient
	private String crtUserId;

	/**
	 * 创建人名称
	 */
	@Transient
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@Transient
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	@Transient
	private String mntUserId;

	/**
	 * 最后维护人名称
	 */
	@Transient
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@Transient
	private Date mntDate;

	/**
	 * 排序
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;
	
	/**
	 * 描述
	 */
	@Column(name = "DES")
	private String des;
	
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId;

	@Transient
	private String bizCode;

	public UnitAreaRel() {
		super();
	}


	public UnitAreaRel(Long unitAreaRelId, Long unitId, String unitCode, String unitName, Long unitAreaId,
			String unitAreaCode, String unitAreaName, Integer dataStatus, String crtUserId, String crtUserName,
			Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer sortNum, Integer version,
			String des, Long bizId, String bizCode) {
		super();
		this.unitAreaRelId = unitAreaRelId;
		this.unitId = unitId;
		this.unitCode = unitCode;
		this.unitName = unitName;
		this.unitAreaId = unitAreaId;
		this.unitAreaCode = unitAreaCode;
		this.unitAreaName = unitAreaName;
		this.dataStatus = dataStatus;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.version = version;
		this.des = des;
		this.bizId = bizId;
		this.bizCode = bizCode;
	}


	public Long getBizId() {
		return bizId;
	}


	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}


	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getUnitAreaCode() {
		return unitAreaCode;
	}

	public void setUnitAreaCode(String unitAreaCode) {
		this.unitAreaCode = unitAreaCode;
	}

	public String getUnitAreaName() {
		return unitAreaName;
	}

	public void setUnitAreaName(String unitAreaName) {
		this.unitAreaName = unitAreaName;
	}

	public Long getUnitAreaRelId() {
		return unitAreaRelId;
	}

	public void setUnitAreaRelId(Long unitAreaRelId) {
		this.unitAreaRelId = unitAreaRelId;
	}

	public Long getUnitAreaId() {
		return unitAreaId;
	}

	public void setUnitAreaId(Long unitAreaId) {
		this.unitAreaId = unitAreaId;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
