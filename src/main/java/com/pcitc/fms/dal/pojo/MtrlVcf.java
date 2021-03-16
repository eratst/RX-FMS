package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_MTRLVCF")
public class MtrlVcf implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MTRLVCF_ID")
	private Long mtrlVcfId;
	
	@Column(name = "MTRL_ID")
	private Long mtrlId;
	
	@Transient
	private String mtrlName;
	
	@Transient
	private String mtrlAlias;
	
	@Transient
	private String mtrlCode;
	
	@Column(name = "TEMP")
	private Double temp;
	
	@Column(name = "VCF_VAL")
	private Double vcfVal;
	
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

	
	
	
	public MtrlVcf() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	public MtrlVcf(Long mtrlVcfId, Long mtrlId, String mtrlName, String mtrlAlias, String mtrlCode, Double temp,
			Double vcfVal, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName,
			Date mntDate, String des, Integer sortNum, Integer version, Integer inUse) {
		super();
		this.mtrlVcfId = mtrlVcfId;
		this.mtrlId = mtrlId;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
		this.mtrlCode = mtrlCode;
		this.temp = temp;
		this.vcfVal = vcfVal;
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

	public String getMtrlAlias() {
		return mtrlAlias;
	}

	public void setMtrlAlias(String mtrlAlias) {
		this.mtrlAlias = mtrlAlias;
	}

	public Long getMtrlVcfId() {
		return mtrlVcfId;
	}

	public void setMtrlVcfId(Long mtrlVcfId) {
		this.mtrlVcfId = mtrlVcfId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getVcfVal() {
		return vcfVal;
	}

	public void setVcfVal(Double vcfVal) {
		this.vcfVal = vcfVal;
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
