package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "T_PM_OUMORGUNIT")
public class OumOrgUnit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ORG_UNIT_ID")
	private Long orgUnitId;
	/**
	 * 机构单元编码
	 */
	@Column(name = "ORG_UNIT_CODE")
	private String orgUnitCode;
	/**
	 * 机构单元名称
	 */
	@Column(name = "ORG_UNIT_NAME")
	private String orgUnitName;
	
	/**
	 * 机构单元简称
	 */
	@Column(name = "ORG_UNIT_ALIA")
	private String orgUnitAlia;
	
	/**
	 * 类型
	 */
	@Column(name = "ORG_UNIT_TYPE_ID")
	private Long orgUnitTypeId = 0l;
	
	/**
	 * 联系人
	 */
	@Column(name = "CONTRACT")
	private String contract;
	
	/**
	 * 机构Id
	 */
	@Column(name = "ORG_ID")
	private Long orgId = 0l;
	/**
	 * 机构单元父级Id
	 */
	@Column(name = "PARENT_ID")
	private Long parentId;
	/**
	 * 创建人Id
	 */
	@Column(name = "CRT_USER_ID")
	private String crtUserId = "admin";
	/**
	 * 创建人Name
	 */
	@Column(name = "CRT_USER_NAME")
	private String crtUserName = "admin";
	/**
	 * 创建时间
	 */
	@Column(name = "CRT_USER_DATE")
	private Date crtUserDate = new Date();
	/**
	 * 修改人Id
	 */
	@Column(name = "MNT_USER_ID")
	private String mntUserId = "admin";
	/**
	 * 修改人Name
	 */
	@Column(name = "MNT_USER_NAME")
	private String mntUserName = "admin";
	/**
	 * 修改时间
	 */
	@Column(name = "MNT_USER_DATE")
	private Date mntUserDate= new Date();
	/**
	 * 乐观锁
	 */
	@Version
	@Column(name = "VERSION")
	private Integer version;
	/**
	 * 是否启用
	 */
	@Column(name = "ENABLED")
	private Long enabled = 1l;
	/**
	 * 排序
	 */
	@Column(name = "ORDER_ID")
	private Long orderId = 1l;
	/**
	 * 备注
	 */
	@Column(name = "DES")
	private String des="";
	
	/**
	 * 是否配置
	 */
	@Column(name = "ISDEPLOY")
	private Integer isDeploy;
	
	
	
	public Integer getIsDeploy() {
		return isDeploy;
	}

	public void setIsDeploy(Integer isDeploy) {
		this.isDeploy = isDeploy;
	}

	public Long getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Long orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getOrgUnitAlia() {
		return orgUnitAlia;
	}

	public void setOrgUnitAlia(String orgUnitAlia) {
		this.orgUnitAlia = orgUnitAlia;
	}

	public Long getOrgUnitTypeId() {
		return orgUnitTypeId;
	}

	public void setOrgUnitTypeId(Long orgUnitTypeId) {
		this.orgUnitTypeId = orgUnitTypeId;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Date getCrtUserDate() {
		return crtUserDate;
	}

	public void setCrtUserDate(Date crtUserDate) {
		this.crtUserDate = crtUserDate;
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

	public Date getMntUserDate() {
		return mntUserDate;
	}

	public void setMntUserDate(Date mntUserDate) {
		this.mntUserDate = mntUserDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getEnabled() {
		return enabled;
	}

	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}
