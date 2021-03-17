package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_IC_DELTCNFG")
public class Deltcnfg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DELTCNFG_ID")
	private Long deltcnfgId;
	
	@Column(name = "DELTCNFG_VAL")
	private String deltcnfgVal;
	
	@Column(name = "DELTCNFG_NAME")
	private String deltcnfgName;
	
	@Column(name = "DELTCNFG_TYPE")
	private String deltcnfgType;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称
	
	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "VERSION")
	private Long version;


	public Long getDeltcnfgId() {
		return deltcnfgId;
	}

	public void setDeltcnfgId(Long deltcnfgId) {
		this.deltcnfgId = deltcnfgId;
	}

	public String getDeltcnfgVal() {
		return deltcnfgVal;
	}

	public void setDeltcnfgVal(String deltcnfgVal) {
		this.deltcnfgVal = deltcnfgVal;
	}

	public String getDeltcnfgName() {
		return deltcnfgName;
	}

	public void setDeltcnfgName(String deltcnfgName) {
		this.deltcnfgName = deltcnfgName;
	}

	public String getDeltcnfgType() {
		return deltcnfgType;
	}

	public void setDeltcnfgType(String deltcnfgType) {
		this.deltcnfgType = deltcnfgType;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Deltcnfg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deltcnfg(Long deltcnfgId, String deltcnfgVal, String deltcnfgName, String deltcnfgType, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer inUse,
			Integer sortNum, String des, Long version) {
		super();
		this.deltcnfgId = deltcnfgId;
		this.deltcnfgVal = deltcnfgVal;
		this.deltcnfgName = deltcnfgName;
		this.deltcnfgType = deltcnfgType;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
	}

	
	
	
	

}
