package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;
@Entity
@Table(name = "T_PM_AREA")
public class AreaDictionary implements Serializable{
	//区域ID
	@Id
	@Column(name="AREA_ID")
	private Long areaDictionaryId;
	//区域编码
	@SpecialResource(name="t.areaCode")
	@Column(name="AREA_CODE")
	private String areaCode;
	//区域名称
	@Column(name="AREA_NAME")
	private String name;
	
	@SpecialResource(name="t.name")
	@Transient
	private String areaName;
	
	//区域简称
	@Column(name="AREA_ALIAS")
	private String shortName;
	
	@SpecialResource(name="t.shortName")
	@Transient
	private String areaAlias;
	
	//区域类型id
	@Column(name="AREATYPE_ID")
	private Long areaTypeId;
	//所属组织机构（工厂）
	@Column(name="ORG_ID")
	private Long factoryId;
	
	@Transient
	private Integer orgId;
	
	@SpecialResource(name="o.orgCode")
	@Transient
	private String orgCode;
	
	@SpecialResource(name="o.orgName")
	@Transient
	private String orgName;
	
	@SpecialResource(name="o.orgAlias")
	@Transient
	private String orgAlias;
	
	@SpecialResource(name="at.areaTypeCode")
	@Transient
	private String areaTypeCode;
	
	@SpecialResource(name="at.areaTypeName")
	@Transient
	private String areaTypeName;
	
	//状态
	@Column(name="INUSE")
	private Integer enabled;
	
	@SpecialResource(name="t.enabled")
	@Transient
	private Integer inUse;
	
	@Column(name="AREA_LATITUDE")
	private String areaLatitude;
	
	@Column(name="AREA_ALTITUDE")
	private String areaAltitude;
	
	@Column(name="AREA_LONGITUDE")
	private String areaLongitude;
	//排序
	@Column(name="SORT_NUM")
	private Integer sortNum;
	//描述
	@Column(name="DES")
	private String des;
	//乐观锁版本
	@Column(name="VERSION")
	private Integer version;
	//类型Name（用于显示）
	@Transient
	private String type;
	
	@Transient
	private String factoryCode;

	@Transient
	private List<Area_NodeType_Num> typeAndNums;
	
	@Column(name="CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name="CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name="CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name="MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name="MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name="MNTDATE")
	private Date mntDate;//维护时间
	
	
	
	public AreaDictionary() {
		super();
	}

	public AreaDictionary(String areaCode, String areaName, String areaAlias, String orgCode, String orgName,
			String orgAlias, String areaTypeCode, String areaTypeName, Integer inUse,Integer sortNum,String des
			,String areaLatitude,String areaAltitude,String areaLongitude,String crtUserCode,String crtUserName
			,Date crtDate,String mntUserCode,String mntUserName,Date mntDate) {
		super();
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaAlias = areaAlias;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.areaTypeCode = areaTypeCode;
		this.areaTypeName = areaTypeName;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
		this.areaLongitude=areaLongitude;
		this.areaAltitude=areaAltitude;
		this.areaLatitude=areaLatitude;
		this.crtUserCode=crtUserCode;
		this.crtUserName=crtUserName;
		this.crtDate=crtDate;
		this.mntUserCode=mntUserCode;
		this.mntUserName=mntUserName;
		this.mntDate=mntDate;
	}

	public AreaDictionary(Long areaDictionaryId, String areaCode,
			String name, String shortName, Long areaTypeId,
			Long factoryId, Integer enabled, Integer sortNum, String des,
			Integer version, String type, String factoryCode) {
		super();
		this.areaDictionaryId = areaDictionaryId;
		this.areaCode = areaCode;
		this.name = name;
		this.shortName = shortName;
		this.areaTypeId = areaTypeId;
		this.factoryId = factoryId;
		this.enabled = enabled;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.type = type;
		this.factoryCode = factoryCode;
	}
	
	public AreaDictionary(Long areaDictionaryId, String areaCode,
			String name, String shortName, Long areaTypeId,
			Long factoryId, Integer enabled, Integer sortNum, String des,
			Integer version, String type) {
		super();
		this.areaDictionaryId = areaDictionaryId;
		this.areaCode = areaCode;
		this.name = name;
		this.shortName = shortName;
		this.areaTypeId = areaTypeId;
		this.factoryId = factoryId;
		this.enabled = enabled;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.type = type;
	}
	
	public AreaDictionary(Long areaDictionaryId, String areaCode,
			String name, String shortName, Long areaTypeId,
			Long factoryId, Integer enabled, Integer sortNum, String des,
			Integer version) {
		super();
		this.areaDictionaryId = areaDictionaryId;
		this.areaCode = areaCode;
		this.name = name;
		this.shortName = shortName;
		this.areaTypeId = areaTypeId;
		this.factoryId = factoryId;
		this.enabled = enabled;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
	}
	public Long getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public Long getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
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

	public List<Area_NodeType_Num> getTypeAndNums() {
		return typeAndNums;
	}

	public void setTypeAndNums(List<Area_NodeType_Num> typeAndNums) {
		this.typeAndNums = typeAndNums;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getAreaCode() {
		return areaCode;
	}



	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getShortName() {
		return shortName;
	}



	public void setShortName(String shortName) {
		this.shortName = shortName;
	}




	public Integer getEnabled() {
		return enabled;
	}



	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
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

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
}
