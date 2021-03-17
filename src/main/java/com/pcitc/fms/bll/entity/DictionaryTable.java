package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class DictionaryTable implements Serializable {
	private static final long serialVersionUID = 1L;

	// 属性id
	private Integer dictionaryTableId;

	private Integer entityId;

	private Integer enabled;
	// 实体表名
	private String entityTableName;
	// 实体类型
	private String entityType;

	// 字段名
	private String fieldName;

	// 字段值
	private String fieldValue;

	// 名称
	private String name;

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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getDictionaryTableId() {
		return dictionaryTableId;
	}

	public void setDictionaryTableId(Integer dictionaryTableId) {
		this.dictionaryTableId = dictionaryTableId;
	}

	public String getEntityTableName() {
		return entityTableName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setEntityTableName(String entityTableName) {
		this.entityTableName = entityTableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

}
