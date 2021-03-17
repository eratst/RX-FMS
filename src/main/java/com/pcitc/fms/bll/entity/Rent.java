package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class Rent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long rentId;
	
	private String rentName;
	
	private String rentCode;
	
	private Integer enable;
	
	private Integer orderId;
	
	private String des;
	
	private Integer crtUserCode;
	
	private String crtUserName;
	
	private Date crtDate;
	
	private Integer mntUserCode;
	
	private String mntUserName;
	
	private Date mntDate;
	
	private Long orgId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public Integer getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(Integer crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public Integer getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(Integer mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public String getRentName() {
		return rentName;
	}

	public void setRentName(String rentName) {
		this.rentName = rentName;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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
