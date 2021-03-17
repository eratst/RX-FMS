package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class MtrlVcf implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long mtrlVcfId;
	
	private Long mtrlId;
	
	private String mtrlCode;
	
	private String mtrlName;
	
	private String mtrlAlias;
	
	private Double temp;
	
	private Double vcfVal;
	
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	private String crtUserName;

	/**
	 * 创建时间
	 */
	private Date crtDate;


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
	
	/**
	 * 状态
	 */
	private Integer inUse;

	
	
	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
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
