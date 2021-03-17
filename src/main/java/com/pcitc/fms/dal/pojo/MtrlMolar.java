package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_MTRLMOLAR")
public class MtrlMolar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MTRLMOLAR_ID")
	private Long mtrlMolarId;
	
	@Column(name = "MTRL_ID")
	private Long mtrlId;
	
	@Transient
	private String mtrlCode;
	
	@Transient
	private String mtrlName;
	
	@Column(name = "DENSITY")
	private Double density;
	
	@Column(name = "WEIGHT")
	private Double weight;
	

	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;


	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;

	/**
	 * 最后维护人名称
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
	
	/**
	 * 状态
	 */
	@Column(name = "INUSE")
	private Integer inUse;
	

	public MtrlMolar() {
		super();
	}

	public MtrlMolar(Long mtrlMolarId, Long mtrlId, String mtrlCode, String mtrlName, Double density, Double weight,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
			String des, Integer sortNum, Integer version, Integer inUse) {
		this.mtrlMolarId = mtrlMolarId;
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.density = density;
		this.weight = weight;
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






	public Long getMtrlMolarId() {
		return mtrlMolarId;
	}

	public void setMtrlMolarId(Long mtrlMolarId) {
		this.mtrlMolarId = mtrlMolarId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}
	
	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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
