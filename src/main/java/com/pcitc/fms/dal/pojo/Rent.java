package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_RENT")
public class Rent implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RENT_ID")
	@SpecialResource(name="r.rentId")
	private Long rentId;
	
	@Column(name = "RENT_NAME")
	@SpecialResource(name="r.rentName")
	private String rentName;
	
	@Column(name = "RENT_CODE")
	@SpecialResource(name="r.rentCode")
	private String rentCode;
	
	@Column(name = "INUSE")
	@SpecialResource(name="r.enable")
	private Integer enable;
	
	
	@Column(name = "DES")
	@SpecialResource(name="r.des")
	private String des;
	
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
	private Date mntDate;//维护时间
	
	@Column(name = "ORG_ID")
	private Long orgId;
	
	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rent(Long rentId, String rentName, String rentCode, Integer enable, String des,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,Long orgId) {
		super();
		this.rentId = rentId;
		this.rentName = rentName;
		this.rentCode = rentCode;
		this.enable = enable;
		this.des = des;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.orgId = orgId;
	}
	
	public Rent(Long rentId, String rentName, String rentCode, Integer enable, String des,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate) {
		super();
		this.rentId = rentId;
		this.rentName = rentName;
		this.rentCode = rentCode;
		this.enable = enable;
		this.des = des;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
	}
	
	
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

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
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
