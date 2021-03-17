package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;
/**
 * [组态关系表] 多业务组织层次(业务域)主表
 * @author Administrator
 *
 */
public class TPmBizOrgMain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long bizId;
	private String bizCode;
	private String bizName;
	private String bizAlias;
	private Integer inUse;
	
	private String crtUserCode;
	
	private String crtUserName;
	
	private Date crtDate;
	
	private String mntUserCode;
	
	private String mntUserName;
	
	private Date mntDate;
	
	private Integer version;
	
	private Integer sortNum;
	
	private String des;
	
	private Integer isStandard;
	
	private Long rentId;
	
	private String rentCode;
	
	
	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAlias() {
		return bizAlias;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	} 
	
	
}
