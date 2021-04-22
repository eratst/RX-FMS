package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "T_PM_ORG")
public class Org implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * OrgId
	 */
	@Id
	@Column(name = "ORG_ID")
	private Long orgId;

	/**
	 * OrgCode
	 */
	@Column(name = "ORG_CODE")
	private String orgCode;

	/**
	 * orgName
	 */
	@Column(name = "ORG_NAME")
	private String orgName;

	/**
	 * orgAlias
	 */
	@Column(name = "ORG_ALIAS")
	private String orgAlias;

	/**
	 * orgTypeId
	 */
	@Column(name = "ORGTYPE_ID")
	private Long orgTypeId;
	/**
	 * dataStatus
	 */
	@Column(name = "INUSE")
	private Integer dataStatus;

	/**
	 * CrtUserId
	 */
	@Column(name = "CRTUSER_CODE")
	private String crtUserId;
	/**
	 * CrtUserName
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	/**
	 * CrtDate
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;

	/**
	 * MntUserId
	 */
	@Column(name = "MNTUSER_CODE")
	private String mntUserId;

	/**
	 * MntUserName
	 */
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;
	/**
	 * MntDate
	 */
	@Column(name = "MNTDATE")
	private Date mntDate;
	/**
	 * Des
	 */
	@Column(name = "DES")
	private String des;

	/**
	 * SortNum
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	/**
	 * Version
	 */
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "ORG_ALTITUDE")
	private String orgAltitude;
	
	@Column(name = "ORG_LONGITUDE")
	private String orgLongitude;
	
	@Column(name = "ORG_LATITUDE")
	private String orgLatitude;
	

	/**
	 * 组织机构类型编码
	 */
	@Transient
	private String orgTypeCode;

	/**
	 * 组织机构类型名称
	 */
	@Transient
	private String orgTypeName;
	
	@Column(name = "UPPER_ORG_ID")
	private Long upperOrgId;
	
	@Transient
	private String upperOrgCode;

	@Transient
	private String upperOrgName;

	@Transient
	private String upperOrgAlias;
	

	public Org() {
		super();
	}

	/**
	 * 
	 * @param orgId
	 * @param orgCode
	 * @param orgName
	 * @param orgAlias
	 * @param orgTypeId
	 * @param dataStatus
	 * @param crtUserId
	 * @param crtUserName
	 * @param crtDate
	 * @param mntUserId
	 * @param mntUserName
	 * @param mntDate
	 * @param des
	 * @param sortNum
	 * @param version
	 */
	public Org(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId, Integer dataStatus,
               String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
               String des, Integer sortNum, Integer version, String orgAltitude, String orgLongitude , String orgLatitude) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
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
		this.orgAltitude = orgAltitude;
		this.orgLongitude = orgLongitude;
		this.orgLatitude = orgLatitude;
		
		
		
		
	}

	public Org(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId, Integer dataStatus,
               String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
               String des, Integer sortNum, Integer version, String orgTypeCode, String orgTypeName, String orgAltitude, String orgLongitude , String orgLatitude) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
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
		this.orgTypeCode = orgTypeCode;
		this.orgTypeName = orgTypeName;
		this.orgAltitude = orgAltitude;
		this.orgLongitude = orgLongitude;
		this.orgLatitude = orgLatitude;
	}

	

	public Org(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId, Integer dataStatus,
               String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
               String des, Integer sortNum, Integer version, String orgAltitude, String orgLongitude, String orgLatitude,
               String orgTypeCode, String orgTypeName, Long upperOrgId, String upperOrgCode, String upperOrgName,
               String upperOrgAlias) {
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
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
		this.orgAltitude = orgAltitude;
		this.orgLongitude = orgLongitude;
		this.orgLatitude = orgLatitude;
		this.orgTypeCode = orgTypeCode;
		this.orgTypeName = orgTypeName;
		this.upperOrgId = upperOrgId;
		this.upperOrgCode = upperOrgCode;
		this.upperOrgName = upperOrgName;
		this.upperOrgAlias = upperOrgAlias;
	}

	public Long getUpperOrgId() {
		return upperOrgId;
	}

	public void setUpperOrgId(Long upperOrgId) {
		this.upperOrgId = upperOrgId;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getUpperOrgName() {
		return upperOrgName;
	}

	public void setUpperOrgName(String upperOrgName) {
		this.upperOrgName = upperOrgName;
	}

	public String getUpperOrgAlias() {
		return upperOrgAlias;
	}

	public void setUpperOrgAlias(String upperOrgAlias) {
		this.upperOrgAlias = upperOrgAlias;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
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

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgAltitude() {
		return orgAltitude;
	}

	public void setOrgAltitude(String orgAltitude) {
		this.orgAltitude = orgAltitude;
	}

	public String getOrgLongitude() {
		return orgLongitude;
	}

	public void setOrgLongitude(String orgLongitude) {
		this.orgLongitude = orgLongitude;
	}

	public String getOrgLatitude() {
		return orgLatitude;
	}

	public void setOrgLatitude(String orgLatitude) {
		this.orgLatitude = orgLatitude;
	}

	

}
