package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_OPM_MONLEVEL")
public class MonLevel implements Serializable{
	@Id
	@Column(name="MONLEVEL_ID")
	private Long monLevelId;
	@Column(name="MONLEVEL_NAME")
	private String name;
	@Column(name="CRTUSER_CODE")
	private Integer crtUserCode;
	@Column(name="CRTUSER_NAME")
	private String crtUserName;
	@Column(name="CRTDATE")
	private Date createTime;
	@Column(name="MNTUSER_CODE")
	private Integer mntUserCode;
	@Column(name="MNTUSER_NAME")
	private String mntUserName;
	@Column(name="MNTDATE")
	private Date mntDate;
	@Column(name="SORT_NUM")
	private Integer sortNum;
	@Column(name="DES")
	private String des;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="INUSE")
	private Integer inUse;
	public MonLevel() {
		super();
	}
	
	public Long getMonLevelId() {
		return monLevelId;
	}

	public void setMonLevelId(Long monLevelId) {
		this.monLevelId = monLevelId;
	}

	public Integer getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(Integer crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Integer getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(Integer mntUserCode) {
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	
}
