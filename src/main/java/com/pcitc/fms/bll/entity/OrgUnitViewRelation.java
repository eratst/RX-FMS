package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class OrgUnitViewRelation implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private Integer orgUnitViewRelationId; 		//组织机构单元视图关联Id
	
	private Integer orgUnitViewId;		//组织机构单元id
	
	private String orgUnitViewCode;		//组织机构单元视图编码 
	
	private Integer factoryId ;			//工厂Id
	
	private Integer entityId;			//组织机构单元视图关联实体id
	
	private String entityType;			//组织机构单元视图关联实体类型
	
	private String creator;				//创建人
	
	private Date createTime;			//创建时间
	
	private String editor;				//修改人姓名
	
	private Date maintainTime;			//修改时间
	
	private Integer enabled;			//启用标志
	
	private Integer Sort_num;			//排序
	
	private Integer Version;			//乐观锁版本
	
	private String des;					//说明

	
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
	 * @return the orgUnitViewRelationId
	 */
	public Integer getOrgUnitViewRelationId() {
		return orgUnitViewRelationId;
	}

	/**
	 * @param orgUnitViewRelationId the orgUnitViewRelationId to set
	 */
	public void setOrgUnitViewRelationId(Integer orgUnitViewRelationId) {
		this.orgUnitViewRelationId = orgUnitViewRelationId;
	}

	/**
	 * @return the orgUnitViewId
	 */
	public Integer getOrgUnitViewId() {
		return orgUnitViewId;
	}

	/**
	 * @param orgUnitViewId the orgUnitViewId to set
	 */
	public void setOrgUnitViewId(Integer orgUnitViewId) {
		this.orgUnitViewId = orgUnitViewId;
	}

	/**
	 * @return the orgUnitViewCode
	 */
	public String getOrgUnitViewCode() {
		return orgUnitViewCode;
	}

	/**
	 * @param orgUnitViewCode the orgUnitViewCode to set
	 */
	public void setOrgUnitViewCode(String orgUnitViewCode) {
		this.orgUnitViewCode = orgUnitViewCode;
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
