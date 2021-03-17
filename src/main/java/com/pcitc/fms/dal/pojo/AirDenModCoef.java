package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * pojo 空气密度修正系数表实体类
 * 
 *
 */
@Entity
@Table(name = "T_IC_DENMODCOEF")
public class AirDenModCoef implements Serializable {

	private static final long serialVersionUID = 1L;

	// 空气密度修正系数ID
	@Id
	@Column(name = "DENMODCOEF_ID")
	private Long denModCoefId;

	// 密度下限
	@Column(name = "DEN_FLR_LMT")
	private Double denFlrLmt;

	// 密度上限
	@Column(name = "DEN_UP_LMT")
	private Double denUpLmt;

	// 浮力修正系数
	@Column(name = "FLOG_MOD_COEF")
	private Double flogModCoef;

	/**
	 * 创建人id
	 */
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;

	/**
	 * 创建人姓名
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;

	/**
	 * 最后维护人id
	 */
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;

	/**
	 * 最后维护人姓名
	 */
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@Column(name = "MNTDATE")
	private Date mntDate;

	/**
	 * 描述
	 */
	@Column(name = "DES")
	private String des;
	/**
	 * 排序
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	
	
	public AirDenModCoef() {
		super();
	}

	public AirDenModCoef(Long denModCoefId, Double denFlrLmt, Double denUpLmt, Double flogModCoef, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
			Integer sortNum, Integer version, Integer inUse) {
		super();
		this.denModCoefId = denModCoefId;
		this.denFlrLmt = denFlrLmt;
		this.denUpLmt = denUpLmt;
		this.flogModCoef = flogModCoef;
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

	public Long getDenModCoefId() {
		return denModCoefId;
	}

	public void setDenModCoefId(Long denModCoefId) {
		this.denModCoefId = denModCoefId;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Double getDenFlrLmt() {
		return denFlrLmt;
	}

	public void setDenFlrLmt(Double denFlrLmt) {
		this.denFlrLmt = denFlrLmt;
	}

	public Double getDenUpLmt() {
		return denUpLmt;
	}

	public void setDenUpLmt(Double denUpLmt) {
		this.denUpLmt = denUpLmt;
	}

	public Double getFlogModCoef() {
		return flogModCoef;
	}

	public void setFlogModCoef(Double flogModCoef) {
		this.flogModCoef = flogModCoef;
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

}
