package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class IcVcf implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * VCFID
	 */
	private Long vcfId;

	/**
	 * 密度
	 */
	private Double den;

	/**
	 * 温度
	 */
	private Double temp;

	/**
	 * VCF值
	 */
	private Double vcfVal;

	/**
	 * 算法类别
	 */
	private Long vcfTypeId;
	/**
	 * 创建人ID
	 */
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	private String crtUserName;

	/**
	 * 创建时间
	 */
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	private String mntUserName;

	/**
	 * 维护日期
	 */
	private Date mntDate;

	/**
	 * 描述
	 */
	private String des;

	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;

	private String vcfTypeCode;
	
	private String vcfTypeName;

	
	
	
	
	public Long getVcfId() {
		return vcfId;
	}

	public void setVcfId(Long vcfId) {
		this.vcfId = vcfId;
	}

	public Long getVcfTypeId() {
		return vcfTypeId;
	}

	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
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

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
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

	public String getVcfTypeCode() {
		return vcfTypeCode;
	}

	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
	}

	public String getVcfTypeName() {
		return vcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
	}
	
	
}
