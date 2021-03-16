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
@Table(name = "T_FMS_PROPERTY")
public class PropertyMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PROPERTY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPERTY_GENERATOR")
	@SequenceGenerator(name = "PROPERTY_GENERATOR", sequenceName = "SEQ_PROPERTY", allocationSize = 1)
	private Integer propertyId;

	// 实体id
	@Column(name = "ENTITY_ID")
	private Integer entityId;
	
	
	// 实体编码
	@Column(name = "ENTITY_CODE")
	private String entityCode;

	@Column(name = "SHORT_NAME")
	private String shortName;

	@Column(name = "TYPE")
	private String type;

	// 属性编码
	@RegionMember
	@Column(name = "PROPERTY_CODE")
	private String propertyCode;

	// 属性名称
	@Column(name = "PROPERTY_NAME")
	private String propertyName;

	// 数据类型
	@Column(name = "DATA_TYPE")
	private String dataType;

	// 创建人
	@Column(name = "CRT_USER")
	private String creator;

	// 创建时间
	@Column(name = "CREATE_TIME", insertable = false)
	private Date createTime;

	// 修改人
	@Column(name = "MNT_USER")
	private String editor;

	// 维护时间
	@Column(name = "MAINTAIN_TIME", insertable = false)
	private Date maintainTime;

	// 备注
	@Column(name = "DES")
	private String des;

	
	
	


	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

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
}
