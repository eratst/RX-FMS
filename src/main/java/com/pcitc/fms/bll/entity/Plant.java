package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class Plant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 区域编码
	private String plantCode;
	// 区域名称
	private String plantName;
	// 区域简称
	private String plantAlias;
	// 排序
	private Integer sortNum;
	// 描述
	private String des;
	// 状态
	private Integer inUse;
	// 区域类型名称
	private String orgCode;

	private String orgName;

	private String orgAlias;

	private String plantTypeName;

	private String plantTypeCode;

	private String technicName;

	private String technicCode;

	private String capacity;

	private Double initialAssetValue;

	private Double netAssetValue;
	
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	/**
	 * 加工能力单位
	 */
	private Long capacityUnitId;

	private String capacityUnitName;
	
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

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
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



	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
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
