package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.SpecialResource;

import cc.aicode.e2e.annotation.ExcelProperty;
@Entity
@Table(name = "T_PM_ADMINISTRATION")
public class AdministrationArea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AREA_ID")
	@SpecialResource(name="a.administrationId")
	private Long administrationId;
	//区域编码
	@Column(name="AREA_CODE")
	@SpecialResource(name="a.administrationCode")
	private String administrationCode;
	//区域类型Id
	@Transient
	@SpecialResource(name="ad.areaTypeId")
	private Long areaTypeId;
	//区域名称
	@Transient
	@SpecialResource(name="ad.name")
	private String administrationName;
	//区域简称
	@Transient
	@SpecialResource(name="ad.shortName")
	private String administrationAlias;
	//所属组织机构（工厂）
	@Transient
	@SpecialResource(name="ad.factoryId")
	private Long orgId;
	
	@Transient
	@SpecialResource(name="org.orgCode")
	private String orgCode;
	@Transient
	private String crtUserCode; // 创建人ID

	@Transient
	private String crtUserName;// 创建人名称

	@Transient
	private Date crtDate;// 创建时间

	@Transient
	private String mntUserCode;// 最后维护人ID

	@Transient
	private String mntUserName;// 最后维护人名称

	@Transient
	private Date mntDate;//维护时间
	//排序
	@Column(name="SORT_NUM")
	private Integer sortNum;
	//描述
	@Column(name="DES")
	@SpecialResource(name="ad.des")
	private String des;
	//乐观锁版本
	@Column(name="VERSION")
	@SpecialResource(name="ad.version")
	private Integer version;
	//状态
	@Transient
	@SpecialResource(name="ad.enabled")
	private Integer inUse;
	//区域类型名称
	@Transient
	@SpecialResource(name="ar.areaTypeName")
	private String areaType;
	
	@Transient
	@SpecialResource(name="org.orgName")
	private String orgName;
	
	@Transient
	@SpecialResource(name="org.orgAlias")
	private String orgAlias;
	
	@Transient
	private String areaLatitude;
	
	@Transient
	private String areaAltitude;
	
	@Transient
	private String areaLongitude;


	public AdministrationArea(Long administrationId, String administrationCode, Long areaTypeId, String administrationName,
			String administrationAlias, Long orgId, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, Integer sortNum, String des, Integer version, Integer inUse,
			String areaType,String orgCode,String orgName,String orgAlias,String areaLatitude,String areaAltitude,String areaLongitude) {
		super();
		this.administrationId = administrationId;
		this.administrationCode = administrationCode;
		this.areaTypeId = areaTypeId;
		this.administrationName = administrationName;
		this.administrationAlias = administrationAlias;
		this.orgId = orgId;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.inUse = inUse;
		this.areaType = areaType;
		this.orgCode=orgCode;
		this.orgName=orgName;
		this.orgAlias=orgAlias;
	    this.areaLongitude=areaLongitude;
		this.areaAltitude=areaAltitude;
		this.areaLatitude=areaLatitude;
	}

	
	
	public Long getAdministrationId() {
		return administrationId;
	}



	public void setAdministrationId(Long administrationId) {
		this.administrationId = administrationId;
	}



	public Long getAreaTypeId() {
		return areaTypeId;
	}



	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}



	public Long getOrgId() {
		return orgId;
	}



	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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



	public String getAreaLatitude() {
		return areaLatitude;
	}



	public void setAreaLatitude(String areaLatitude) {
		this.areaLatitude = areaLatitude;
	}



	public String getAreaAltitude() {
		return areaAltitude;
	}



	public void setAreaAltitude(String areaAltitude) {
		this.areaAltitude = areaAltitude;
	}



	public String getAreaLongitude() {
		return areaLongitude;
	}



	public void setAreaLongitude(String areaLongitude) {
		this.areaLongitude = areaLongitude;
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


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public AdministrationArea() {
		super();
	}


	public String getAreaType() {
		return areaType;
	}


	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}


	public String getAdministrationName() {
		return administrationName;
	}


	public void setAdministrationName(String administrationName) {
		this.administrationName = administrationName;
	}


	public String getAdministrationAlias() {
		return administrationAlias;
	}


	public void setAdministrationAlias(String administrationAlias) {
		this.administrationAlias = administrationAlias;
	}



	public Integer getInUse() {
		return inUse;
	}


	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}



	public String getAdministrationCode() {
		return administrationCode;
	}


	public void setAdministrationCode(String administrationCode) {
		this.administrationCode = administrationCode;
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
	
	public Integer getVersion() {
		return version;
	}



	public void setVersion(Integer version) {
		this.version = version;
	}

	
}
