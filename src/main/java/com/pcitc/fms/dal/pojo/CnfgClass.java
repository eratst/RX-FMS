package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [字典表]罐量计算配置基础分类
 */
@Entity
@Table(name = "T_IC_CNFGCLASS")
public class CnfgClass implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	/*
	 * 基础分类ID CNFG_CLASS_ID
	 */
	@Id
	@Column(name = "CNFGCLASS_ID")
	private Long cnfgClassId;

	/*
	 * 基础分类编码 CNFG_CLASS_CODE
	 */
	@Column(name = "CNFGCLASS_CODE")
	private String cnfgClassCode;

	/*
	 * 基础分类名称  CNFG_CLASS_NAME
	 */
	@Column(name = "CNFGCLASS_NAME")
	private String cnfgClassName;

	/*
	 * 适用罐类型  TANK_TYPE
	 */
	@Column(name = "TANK_TYPE")
	private String tankType;  
	
	/*
	 * 创建人ID  CRT_USER_ID
	 */
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;
	
	/*
	 * 创建人名称 CRT_USER_NAME
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	
	/*
	 * 创建时间 CRT_DATE
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;
	
	/*
	 * 最后维护人ID MNT_USER_ID
	 */
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;
	
	/*
	 * 最后维护人名称 MNT_USER_NAME
	 */
	@Column(name = "MNTUSER_NAME")	
	private String mntUserName;
	
	/*
	 * 维护日期 MNT_DATE
	 */
	@Column(name = "MNTDATE")	
	private Date mntDate;
	
	/*
	 * 排序   SORT_NUM
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	/*
	 * 描述  DES
	 */
	@Column(name = "DES")
	private String des;
	
	/*
	 * 状态
	 */
	@Column(name = "INUSE")
	private String inUse;

	
	
	public Long getCnfgClassId() {
		return cnfgClassId;
	}

	public void setCnfgClassId(Long cnfgClassId) {
		this.cnfgClassId = cnfgClassId;
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

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getCnfgClassCode() {
		return cnfgClassCode;
	}

	public void setCnfgClassCode(String cnfgClassCode) {
		this.cnfgClassCode = cnfgClassCode;
	}

	public String getCnfgClassName() {
		return cnfgClassName;
	}

	public void setCnfgClassName(String cnfgClassName) {
		this.cnfgClassName = cnfgClassName;
	}

	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
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
