package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_IC_PIPENETTNKCOEF")
public class IcPipenettankCoef implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PIPENETTNKCOEF_ID")
	private Long pipenettankCoefId;

	@Column(name = "MONTH")
	private Integer month;

	@Column(name = "COEFFICIENT")
	private Double coefficient;

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

	public IcPipenettankCoef() {
	}

	public IcPipenettankCoef(Long pipenettankCoefId, Integer month, Double coefficient, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
			Integer sortNum, Integer version, Integer inUse) {
		super();
		this.pipenettankCoefId = pipenettankCoefId;
		this.month = month;
		this.coefficient = coefficient;
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

	public Long getPipenettankCoefId() {
		return pipenettankCoefId;
	}

	public void setPipenettankCoefId(Long pipenettankCoefId) {
		this.pipenettankCoefId = pipenettankCoefId;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
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
