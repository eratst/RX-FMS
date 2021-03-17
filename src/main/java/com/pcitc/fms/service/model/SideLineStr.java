package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class SideLineStr extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer sideLineId;
	
	private String code;
	private String name;
	private String shortName;
	private Integer parentId;
	private String parentType;
	private String type;
	private String inOut;
	private String technic;
	private String creator;
	private String createTime;
	private String editor;
	private String maintainTime;
	private String des;
	private String enabled;
	private String parentCode;
	/**装置id**/
	private Integer unitId;
	/**物料类型id**/
	private Integer mtrlTypeId;
	private String top;
	private String skip;
	
	
	
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getMtrlTypeId() {
		return mtrlTypeId;
	}
	public void setMtrlTypeId(Integer mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
	}
	public Integer getSideLineId() {
		return sideLineId;
	}
	public void setSideLineId(Integer sideLineId) {
		this.sideLineId = sideLineId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
