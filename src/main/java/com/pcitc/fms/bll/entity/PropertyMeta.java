package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class PropertyMeta implements Serializable {
	private static final long serialVersionUID = 1L;

	// 属性id
	private Integer propertyId;

	// 实体id
	private Integer entityId;
	
	// 实体编码
	private String entityCode;

	// 属性编码
	private String propertyCode;

	private String shortName;
	private String type;
	// 属性名称
	private String propertyName;

	// 数据类型
	private String dataType;

	// 创建人
	private String creator;

	// 创建时间
	private Date createTime;

	// 修改人
	private String editor;

	// 维护时间
	private Date maintainTime;

	// 备注
	private String des;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	
}
