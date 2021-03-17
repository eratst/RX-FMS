package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

/**
 * [组态关系表] 多业务组织层次(业务域)主表
 *
 * @author Administrator
 */
@Entity
@Table(name = "T_PM_BIZORGMAIN")
public class TPmBizOrgMain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SpecialResource(name="orgMain.bizId")
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId;
	
	@SpecialResource(name="orgMain.bizCode")
	@Column(name = "BIZORGMAIN_CODE")
	private String bizCode;

	@SpecialResource(name="orgMain.bizName")
	@Column(name = "BIZORGMAIN_NAME")
	private String bizName;

	@SpecialResource(name="orgMain.bizAlias")
	@Column(name = "BIZORGMAIN_ALIAS")
	private String bizAlias;
	
	@SpecialResource(name="orgMain.inUse")
	@Column(name = "INUSE")
	private Integer inUse;
	
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
	
	@SpecialResource(name="orgMain.version")
	@Column(name = "VERSION")
	private Integer version;
	
	@SpecialResource(name="orgMain.sortNum")
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@SpecialResource(name="orgMain.des")
	@Column(name = "DES")
	private String des;
	
	@Column(name = "IS_STANDARD")
	private Integer isStandard;
	
	@Column(name = "RENT_ID")
	private Long rentId;
	
	@Transient
	private String rentCode;


	public TPmBizOrgMain() {
	}
	
	public TPmBizOrgMain(Long bizId, String bizCode, String bizName, String bizAlias, Integer inUse, String crtUserCode, 
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer version, Integer sortNum, String des,
			Integer isStandard,Long rentId,String rentCode) {
		this.bizId = bizId;
		this.bizCode = bizCode;
		this.bizName = bizName;
		this.bizAlias = bizAlias;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.version = version;
		this.sortNum = sortNum;
		this.des = des;
		this.isStandard=isStandard;
		this.rentId=rentId;
		this.rentCode=rentCode;
	}
	
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
