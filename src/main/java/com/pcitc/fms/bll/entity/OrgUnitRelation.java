package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

public class OrgUnitRelation implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private Integer orgUnitRelationId; 		//组织机构单元关联Id
	
	private Integer orgUnitId;		//组织机构单元id
	
	private String orgUnitCode;		//组织机构单元编码 
	
	private Integer factoryId ;			//工厂Id
	
	private Integer entityId;			//组织机构单元关联实体id
	
	private String entityType;			//组织机构单元关联实体类型
	
	private String type;				//用于区分机构单元 or 机构单元视图
	
	private String creator;				//创建人
	
	private Date createTime;			//创建时间
	
	private String editor;				//修改人姓名
	
	private Date maintainTime;			//修改时间
	
	private Integer enabled;			//启用标志
	
	private Integer Sort_num;			//排序
	
	private Integer Version;			//乐观锁版本
	
	private String des;					//说明

	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the factoryId
	 */
	public Integer getFactoryId() {
		return factoryId;
	}

	/**
	 * @param factoryId the factoryId to set
	 */
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	/**
	 * @return the orgUnitRelationId
	 */
	public Integer getOrgUnitRelationId() {
		return orgUnitRelationId;
	}

	/**
	 * @param orgUnitRelationId the orgUnitRelationId to set
	 */
	public void setOrgUnitRelationId(Integer orgUnitRelationId) {
		this.orgUnitRelationId = orgUnitRelationId;
	}

	/**
	 * @return the orgUnitId
	 */
	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	/**
	 * @param orgUnitId the orgUnitId to set
	 */
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	/**
	 * @return the orgUnitCode
	 */
	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	/**
	 * @param orgUnitCode the orgUnitCode to set
	 */
	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	/**
	 * @return the entityId
	 */
	public Integer getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the maintainTime
	 */
	public Date getMaintainTime() {
		return maintainTime;
	}

	/**
	 * @param maintainTime the maintainTime to set
	 */
	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the sort_num
	 */
	public Integer getSort_num() {
		return Sort_num;
	}

	/**
	 * @param sort_num the sort_num to set
	 */
	public void setSort_num(Integer sort_num) {
		Sort_num = sort_num;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return Version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		Version = version;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}


}
