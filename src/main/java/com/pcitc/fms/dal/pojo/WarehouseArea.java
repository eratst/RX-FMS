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
@Table(name = "T_PM_WAREHOUSE")
public class WarehouseArea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AREA_ID")
	@SpecialResource(name="a.warehouseId")
	private Long warehouseId;
	//区域编码
	@Column(name="AREA_CODE")
	@SpecialResource(name="a.warehouseCode")
	private String warehouseCode;
	//区域类型Id
	@Transient
	@SpecialResource(name="ad.areaTypeId")
	private Long areaTypeId;
	//区域名称
	@Transient
	@SpecialResource(name="ad.name")
	private String warehouseName;
	//区域简称
	@Transient
	@SpecialResource(name="ad.shortName")
	private String warehouseAlias;
	//上级区域（工厂）
	@Transient
	@SpecialResource(name="ad.factoryId")
	private Long orgId;
	
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
	//状态
	@Transient
	@SpecialResource(name="tt.warehouseTypeName")
	private String warehouseType;
	//仓库工艺类型
	@Transient
	@SpecialResource(name="tt.warehouseTypeCode")
	private String warehouseTypeCode;
	
	@Transient
	@SpecialResource(name="tt.warehouseTypeName")
	private String warehouseTypeName;
	
	@Transient
	@SpecialResource(name="ta.technicName")
	private String technicName;
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
	@SpecialResource(name="ta.technicCode")
	private String technicCode;
	
	@Column( name="WAREHOUSETYPE_ID")
	@SpecialResource(name="tt.warehouseTypeId")
	private Long warehouseTypeId;
	//仓库工艺类型
	@Column( name="WAREHOUSETECHNIC_ID")
	@SpecialResource(name="ta.technicId")
	private Long technicId;
	
	@Transient
	private String areaLatitude;
	
	@Transient
	private String areaAltitude;
	
	@Transient
	private String areaLongitude;



	public WarehouseArea(Long warehouseId, String warehouseCode, Long areaTypeId, String warehouseName, String warehouseAlias,
			Long orgId, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, Integer sortNum, String des, Integer version, Integer inUse,
			String warehouseType, String technicName,String orgCode,String orgName,String orgAlias
			,String technicCode,String warehouseTypeCode,Long warehouseTypeId,Long technicId
			,String warehouseTypeName,String areaLatitude,String areaAltitude,String areaLongitude) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseCode = warehouseCode;
		this.areaTypeId = areaTypeId;
		this.warehouseName = warehouseName;
		this.warehouseAlias = warehouseAlias;
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
		this.warehouseType = warehouseType;
		this.technicName = technicName;
		this.orgCode=orgCode;
		this.orgName=orgName;
		this.orgAlias=orgAlias;
		this.technicCode=technicCode;
		this.warehouseTypeCode=warehouseTypeCode;
		this.warehouseTypeId=warehouseTypeId;
		this.technicId=technicId;
		this.warehouseTypeName=warehouseTypeName;
		this.areaLongitude=areaLongitude;
		this.areaAltitude=areaAltitude;
		this.areaLatitude=areaLatitude;
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




	public String getWarehouseTypeName() {
		return warehouseTypeName;
	}


	public void setWarehouseTypeName(String warehouseTypeName) {
		this.warehouseTypeName = warehouseTypeName;
	}


	public WarehouseArea() {
		super();
	}


	public String getWarehouseName() {
		return warehouseName;
	}


	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getWarehouseAlias() {
		return warehouseAlias;
	}


	public void setWarehouseAlias(String warehouseAlias) {
		this.warehouseAlias = warehouseAlias;
	}


	public Integer getInUse() {
		return inUse;
	}


	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}


	public String getWarehouseTypeCode() {
		return warehouseTypeCode;
	}


	public void setWarehouseTypeCode(String warehouseTypeCode) {
		this.warehouseTypeCode = warehouseTypeCode;
	}


	public String getTechnicName() {
		return technicName;
	}


	public void setTechnicName(String technicName) {
		this.technicName = technicName;
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


	public String getTechnicCode() {
		return technicCode;
	}


	public void setTechnicCode(String technicCode) {
		this.technicCode = technicCode;
	}


	public String getWarehouseType() {
		return warehouseType;
	}


	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}





	public String getWarehouseCode() {
		return warehouseCode;
	}


	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
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
