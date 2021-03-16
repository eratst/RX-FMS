package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class LoadPointType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long loadPointTypeId;

	private String loadPointTypeCode;

	private String loadPointTypeName;

	private String des;

	private Integer sortNum;

	private Integer inUse;

	private Integer version;

	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;// 维护时间

	public Long getLoadPointTypeId() {
		return loadPointTypeId;
	}

	public void setLoadPointTypeId(Long loadPointTypeId) {
		this.loadPointTypeId = loadPointTypeId;
	}

	public String getLoadPointTypeCode() {
		return loadPointTypeCode;
	}

	public void setLoadPointTypeCode(String loadPointTypeCode) {
		this.loadPointTypeCode = loadPointTypeCode;
	}

	public String getLoadPointTypeName() {
		return loadPointTypeName;
	}

	public void setLoadPointTypeName(String loadPointTypeName) {
		this.loadPointTypeName = loadPointTypeName;
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
