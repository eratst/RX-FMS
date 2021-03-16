package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_IC_STANGASDEN")
public class IcStangasden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STANGASDEN_ID")
	private Long stangasdenId;

	@Column(name = "LIQUID_DEN")
	private Double liquidDen;

	@Column(name = "GAS_DEN")
	private Double gasDen;

	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;

	@Column(name = "CRTDATE")
	private Date crtDate;

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;

	@Column(name = "MNTDATE")
	private Date mntDate;

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "INUSE")
	private Integer inUse;

	public IcStangasden() {
	}

	public IcStangasden(Long stangasdenId, Double liquidDen, Double gasDen, String crtUserCode, String crtUserName,
			Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des, Integer sortNum,
			Integer version, Integer inUse) {
		super();
		this.stangasdenId = stangasdenId;
		this.liquidDen = liquidDen;
		this.gasDen = gasDen;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.inUse = inUse;
	}

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
