package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class IcStangasden implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long stangasdenId;

	private Double liquidDen;

	private Double gasDen;

	private String crtUserCode;

	private String crtUserName;

	private Date crtDate;

	private String mntUserCode;

	private String mntUserName;

	private Date mntDate;

	private String des;

	private Integer sortNum;

	private Integer version;

	private Integer inUse;

	public Long getStangasdenId() {
		return stangasdenId;
	}

	public void setStangasdenId(Long stangasdenId) {
		this.stangasdenId = stangasdenId;
	}

	public Double getLiquidDen() {
		return liquidDen;
	}

	public void setLiquidDen(Double liquidDen) {
		this.liquidDen = liquidDen;
	}

	public Double getGasDen() {
		return gasDen;
	}

	public void setGasDen(Double gasDen) {
		this.gasDen = gasDen;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

}
