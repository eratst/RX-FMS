package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_STANDEN")
public class StanDen implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STANDEN_ID")
	private Long stanDenId;

	@Column(name = "TEMP")
	private Double temp;

	@Column(name = "DEN")
	private Double den;
	
	@Column(name = "MTRLTYPE_ID")
	private Long mtrlTypeId;
	
	/*
	 * 物料类型编码
	 */
	@Transient
	private String mtrlTypeCode;

	/*
	 * 物料类型名称
	 */
	@Transient
	private String mtrlTypeName;
	
	@Column(name = "STANDEN_VAL")
	private Double stanDenVal;

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


	public StanDen() {
		super();
	}
	
	

	public StanDen(Long stanDenId, Double temp, Double den, Long mtrlTypeId, String mtrlTypeCode, String mtrlTypeName,
			Double stanDenVal, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, String des, Integer sortNum, Integer version, Integer inUse) {
		this.stanDenId = stanDenId;
		this.temp = temp;
		this.den = den;
		this.mtrlTypeId = mtrlTypeId;
		this.mtrlTypeCode = mtrlTypeCode;
		this.mtrlTypeName = mtrlTypeName;
		this.stanDenVal = stanDenVal;
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



	public Long getStanDenId() {
		return stanDenId;
	}

	public void setStanDenId(Long stanDenId) {
		this.stanDenId = stanDenId;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public Long getMtrlTypeId() {
		return mtrlTypeId;
	}

	public void setMtrlTypeId(Long mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}

	public String getMtrlTypeCode() {
		return mtrlTypeCode;
	}

	public void setMtrlTypeCode(String mtrlTypeCode) {
		this.mtrlTypeCode = mtrlTypeCode;
	}

	public String getMtrlTypeName() {
		return mtrlTypeName;
	}

	public void setMtrlTypeName(String mtrlTypeName) {
		this.mtrlTypeName = mtrlTypeName;
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



	public Double getStanDenVal() {
		return stanDenVal;
	}



	public void setStanDenVal(Double stanDenVal) {
		this.stanDenVal = stanDenVal;
	}
	

}
