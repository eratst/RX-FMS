package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class WarehouseStr extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String warehouseId;
	private String factoryID;
	private String code;
	private String name;
	private String shortName;
	private String businessType;
	private String technic;
	private String creator;
	private String createTime;
	private String editor;
	private String maintainTime;
	private String enabled;
	private String des;
	private String factoryCode;
	
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getTechnic() {
		return technic;
	}
	public void setTechnic(String technic) {
		this.technic = technic;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
