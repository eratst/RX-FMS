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

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_FMS_ENTITY")
@SequenceGenerator(name = "ENTITY_GENERATOR", sequenceName = "SEQ_ENTITY")
public class EntityMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	// 实体ID(自增)
	@Id
	@Column(name = "ENTITY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITY_GENERATOR")
	private Integer entityId;

	// 实体名称
	@Column(name = "ENTITY_NAME")
	private String entityName;
	
	@RegionMember
	@Column(name = "CODE")
	private String code;

	// 实体类型
	@Column(name = "ENTITY_TYPE")
	private String entityType;

	@Column(name = "SHORT_NAME")
	private String shortName;
	// 实体表名
	@Column(name = "ENTITY_TABLE_NAME")
	private String entityTableName;

	// 创建人(新增时必填)
	@Column(name = "CRT_USER")
	private String creator;

	// 创建时间(如果为空字符串，则按系统时间生成)
	@Column(name = "CREATE_TIME", insertable = false)
	private Date createTime;

	// 修改人(更新时必填)
	@Column(name = "MNT_USER")
	private String editor;

	// 维护时间(如果为空字符串，则按系统时间生成)
	@Column(name = "MAINTAIN_TIME", insertable = false)
	private Date maintainTime;

	// 启用标识
	@Column(name = "ENABLED")
	private Integer enabled;


	// 备注
	@Column(name = "DES")
	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityTableName() {
		return entityTableName;
	}

	public void setEntityTableName(String entityTableName) {
		this.entityTableName = entityTableName;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
