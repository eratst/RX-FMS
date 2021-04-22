package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_BIZORGMAIN")
public class BizorgMain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId;

	@Column(name = "BIZORGMAIN_CODE")
	private String bizCode;

	@Column(name = "BIZORGMAIN_NAME")
	private String bizName;

	@Column(name = "BIZORGMAIN_ALIAS")
	private String bizAlias;

	@Column(name = "INUSE")
	private Integer dataStatus;

	@Column(name = "CRTUSER_CODE")
	private String crtUserId;// 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建日期

	@Column(name = "MNTUSER_CODE")
	private String mntUserId;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;// 维护日期

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;// 排序

	@Column(name = "VERSION")
	private Integer version;// 乐观锁版本

	@Column(name = "RENT_ID")
	private Long rentId;

	@Transient
	private String rentCode;

	@Column(name = "IS_STANDARD")
	private Integer isStandard;

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

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public BizorgMain() {
		super();
	}

	public BizorgMain(Long bizId, String bizCode, String bizName, String bizAlias, Integer dataStatus,
                      String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
                      String des, Integer sortNum, Integer version, Long rentId, String rentCode, Integer isStandard) {
		super();
		this.bizId = bizId;
		this.bizCode = bizCode;
		this.bizName = bizName;
		this.bizAlias = bizAlias;
		this.dataStatus = dataStatus;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.rentId = rentId;
		this.rentCode = rentCode;
		this.isStandard = isStandard;
	}
}
