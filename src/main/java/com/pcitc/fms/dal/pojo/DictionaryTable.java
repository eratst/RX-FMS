package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_FMS_DICTIONARY_TABLE")
public class DictionaryTable implements Serializable {

	private static final long serialVersionUID = 1L;
	// 属性id
	@RegionMember
	@Id
	@Column(name = "DICTIONARY_TABLE_ID")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICTIONARYTABLE_GENERATOR")
//	@SequenceGenerator(name = "DICTIONARYTABLE_GENERATOR", sequenceName = "SEQ_DICTIONARY_TABLE", allocationSize = 100)
	private Integer dictionaryTableId;
	
	@Column(name = "ENABLED")
	private Integer enabled;
	
	@Column(name = "ENTITY_ID")
	private Integer entityId;
	
	@Column(name = "ENTITY_TYPE")
	private String entityType;
	
	// 实体表名
	@Column(name = "ENTITY_TABLE_NAME")
	private String entityTableName;
	// 字段名
	@Column(name = "FIELD_NAME")
	private String fieldName;

	// 字段值
	@Column(name = "FIELD_VALUE")
	private String fieldValue;

	// 名称
	@Column(name = "NAME")
	private String name;

	// 创建人
	@Column(name = "CRT_USER")
	private String creator;

	// 创建时间
	@Column(name = "CREATE_TIME")
	private Date createTime;

	// 修改人
	@Column(name = "MNT_USER")
	private String editor;

	// 维护时间
	@Column(name = "MAINTAIN_TIME")
	private Date maintainTime;

	// 备注
	@Column(name = "DES")
	private String des;

	
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}

}
