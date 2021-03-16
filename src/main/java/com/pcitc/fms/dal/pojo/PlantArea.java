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

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;
@Entity
@Table(name = "T_PM_UNIT")
public class PlantArea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AREA_ID")
	@SpecialResource(name="a.plantId")
	private Long plantId;
	//区域编码
	@Column(name="AREA_CODE")
	@SpecialResource(name="a.plantCode")
	private String plantCode;
	//区域类型Id
	@Transient
	@SpecialResource(name="ad.areaTypeId")
	private Long areaTypeId;
	//区域名称
	@Transient
	@SpecialResource(name="ad.name")
	private String plantName;
	//区域简称
	@Transient
	@SpecialResource(name="ad.shortName")
	private String plantAlias;
	//上级区域（工厂）
	@Transient
	@SpecialResource(name="ad.factoryId")
	private Long orgId;
	
	//排序
	@Column(name="SORT_NUM")
	@SpecialResource(name="a.sortNum")
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
	//加工能力
	@Column(name="CAPACITY")
	@SpecialResource(name="a.capacity")
	private String capacity;
	
	@SpecialResource(name="a.initialAssetValue")
	@Column(name = "INITIAL_ASSET_VALUE")
	private Double initialAssetValue;

	@SpecialResource(name="a.netAssetValue")
	@Column(name = "NET_ASSET_VALUE")
	private Double netAssetValue;
	
	//区域类型名称
	@Transient
	@SpecialResource(name="ar.areaTypeName")
	private String areaType;
	@Transient
	@SpecialResource(name="org.orgCode")
	private String orgCode;
	@Transient
	@SpecialResource(name="org.orgName")
	private String orgName;
	@Transient
	@SpecialResource(name="org.orgAlias")
	private String orgAlias;
	@Transient
	@SpecialResource(name="u.unitTyprName")
	private String plantTypeName;
	@Transient
	@SpecialResource(name="u.unitTypeCode")
	private String plantTypeCode;
	@Transient
	@SpecialResource(name="t.technicName")
	private String technicName;
	@Transient
	@SpecialResource(name="t.technicCode")
	private String technicCode;
	@Column(name="UNITTYPE_ID")
	@SpecialResource(name="u.unitTypeId")
	private Long plantTypeId;
	//装置工艺类型
	@Column(name="UNITTECHNIC_ID")
	@SpecialResource(name="t.technicId")
	private Long technicId;

	@Transient
	private String areaLatitude;
	
	@Transient
	private String areaAltitude;
	
	@Transient
	private String areaLongitude;
	
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
	
	/**
	 * 加工能力单位
	 */
	@Column(name = "CAPACITYUNIT_ID")
	private Long capacityUnitId;

	@Transient
	private String capacityUnitName;
	
	
	public PlantArea(Long plantId, String plantCode, Long areaTypeId, String plantName, String plantAlias,
			Long orgId, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName,
			Date mntDate, Integer sortNum, String des, Integer version, Integer inUse, String plantTypeName,
			String technicName, String capacity, Double initialAssetValue, Double netAssetValue, String areaType,String orgCode,String orgName,String orgAlias,
			Long plantTypeId,String plantTypeCode,Long technicId,String technicCode,String areaLatitude,String areaAltitude,
			String areaLongitude,Long capacityUnitId,String capacityUnitName) {
		super();
		this.plantId = plantId;
		this.plantCode = plantCode;
		this.areaTypeId = areaTypeId;
		this.plantName = plantName;
		this.plantAlias = plantAlias;
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
		this.plantTypeName = plantTypeName;
		this.technicName = technicName;
		this.capacity = capacity;
		this.areaType = areaType;
		this.orgCode=orgCode;
		this.orgName=orgName;
		this.orgAlias=orgAlias;
		this.plantTypeId=plantTypeId;
		this.plantTypeCode=plantTypeCode;
		this.technicId=technicId;
		this.technicCode=technicCode;
		this.initialAssetValue = initialAssetValue;
		this.netAssetValue = netAssetValue;
		this.areaLongitude=areaLongitude;
		this.areaAltitude=areaAltitude;
		this.areaLatitude=areaLatitude;
		this.capacityUnitId = capacityUnitId;
		this.capacityUnitName = capacityUnitName;
	}

	
	public Long getCapacityUnitId() {
		return capacityUnitId;
	}


	public void setCapacityUnitId(Long capacityUnitId) {
		this.capacityUnitId = capacityUnitId;
	}


	public String getCapacityUnitName() {
		return capacityUnitName;
	}


	public void setCapacityUnitName(String capacityUnitName) {
		this.capacityUnitName = capacityUnitName;
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


	public PlantArea() {
		super();
	}


	public String getAreaType() {
		return areaType;
	}


	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}




	public String getPlantCode() {
		return plantCode;
	}


	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
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





	public String getPlantName() {
		return plantName;
	}


	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}


	public String getPlantAlias() {
		return plantAlias;
	}


	public void setPlantAlias(String plantAlias) {
		this.plantAlias = plantAlias;
	}




	public Integer getInUse() {
		return inUse;
	}


	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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


	public String getPlantTypeName() {
		return plantTypeName;
	}


	public void setPlantTypeName(String plantTypeName) {
		this.plantTypeName = plantTypeName;
	}


	public String getPlantTypeCode() {
		return plantTypeCode;
	}


	public void setPlantTypeCode(String plantTypeCode) {
		this.plantTypeCode = plantTypeCode;
	}


	public String getTechnicName() {
		return technicName;
	}


	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}


	public String getTechnicCode() {
		return technicCode;
	}


	public void setTechnicCode(String technicCode) {
		this.technicCode = technicCode;
	}


	public Long getPlantTypeId() {
		return plantTypeId;
	}


	public void setPlantTypeId(Long plantTypeId) {
		this.plantTypeId = plantTypeId;
	}





	public Long getPlantId() {
		return plantId;
	}


	public void setPlantId(Long plantId) {
		this.plantId = plantId;
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


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public Long getTechnicId() {
		return technicId;
	}


	public void setTechnicId(Long technicId) {
		this.technicId = technicId;
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


	public Double getInitialAssetValue() {
		return initialAssetValue;
	}


	public void setInitialAssetValue(Double initialAssetValue) {
		this.initialAssetValue = initialAssetValue;
	}


	public Double getNetAssetValue() {
		return netAssetValue;
	}


	public void setNetAssetValue(Double netAssetValue) {
		this.netAssetValue = netAssetValue;
	}
	
}

