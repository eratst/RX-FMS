package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class Deltcnfg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long deltcnfgId;
	
	private String deltcnfgVal;
	
	private String deltcnfgName;
	
	private String deltcnfgType;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	private Long version;


	public Long getDeltcnfgId() {
		return deltcnfgId;
	}

	public void setDeltcnfgId(Long deltcnfgId) {
		this.deltcnfgId = deltcnfgId;
	}

	public String getDeltcnfgVal() {
		return deltcnfgVal;
	}

	public void setDeltcnfgVal(String deltcnfgVal) {
		this.deltcnfgVal = deltcnfgVal;
	}

	public String getDeltcnfgName() {
		return deltcnfgName;
	}

	public void setDeltcnfgName(String deltcnfgName) {
		this.deltcnfgName = deltcnfgName;
	}

	public String getDeltcnfgType() {
		return deltcnfgType;
	}

	public void setDeltcnfgType(String deltcnfgType) {
		this.deltcnfgType = deltcnfgType;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	

}
