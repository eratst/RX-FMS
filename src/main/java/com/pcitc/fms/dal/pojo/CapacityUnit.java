package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_CAPACITYUNIT")
public class CapacityUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CAPACITYUNIT_ID")
	private Long capacityUnitId;

	@Column(name = "CAPACITYUNIT_CODE")
	private String capacityUnitCode;

	@Column(name = "CAPACITYUNIT_NAME")
	private String capacityUnitName;

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "INUSE")
	private Integer inUse;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;// 维护时间
	
	public CapacityUnit() {
		super();
	}

	public CapacityUnit(Long capacityUnitId, String capacityUnitCode, String capacityUnitName, String des,
			Integer sortNum, Integer inUse, Integer version, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate) {
		super();
		this.capacityUnitId = capacityUnitId;
		this.capacityUnitCode = capacityUnitCode;
		this.capacityUnitName = capacityUnitName;
		this.des = des;
		this.sortNum = sortNum;
		this.inUse = inUse;
		this.version = version;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
	}

	public Long getCapacityUnitId() {
		return capacityUnitId;
	}

	public void setCapacityUnitId(Long capacityUnitId) {
		this.capacityUnitId = capacityUnitId;
	}

	public String getCapacityUnitCode() {
		return capacityUnitCode;
	}

	public void setCapacityUnitCode(String capacityUnitCode) {
		this.capacityUnitCode = capacityUnitCode;
	}

	public String getCapacityUnitName() {
		return capacityUnitName;
	}

	public void setCapacityUnitName(String capacityUnitName) {
		this.capacityUnitName = capacityUnitName;
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

}
