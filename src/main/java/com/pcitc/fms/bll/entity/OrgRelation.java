package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

 /**
 * Title: ORG_RELATION
* Description: 机构单元关系映射
 * @author zhenqiang.zhao
 * @date 2017年7月21日
 * @version 1.0
 */
public class OrgRelation   implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Integer orgRelationId;  	//机构关系映射ID
	
	
	private String code;  	//机构关系映射ID
	
	private Integer factoryId;    		//工厂ID
	
	private String factoryCode;
	
	private Integer sourceId;	   		//源ID
	
	private String sourceCode;
	
	private String sourceType;    		//源类型
	
	private Integer targetId;      		//目标ID
	
	private String targetCode;
	
	private String targetType;   		//目标类型
	
	private String orgRelationType;      //机构关系映射类型 ,区分机构单元视图,机构单元
	
	private String creator;        		//创建人
	
	private Date createTime;    		//创建时间
	
	private String editor;         		//修改人
	
	private Date maintainTime; 		    //修改时间
	
	private Integer enabled;       		//启用标识
	
	private String des;           		//描述
	
	private Integer sortNum;      		//排序
	
	private Integer version;       		//乐观锁版本

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	/**
	 * @return the orgRelationId
	 */
	public Integer getOrgRelationId() {
		return orgRelationId;
	}

	/**
	 * @param orgRelationId the orgRelationId to set
	 */
	public void setOrgRelationId(Integer orgRelationId) {
		this.orgRelationId = orgRelationId;
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
	 * @return the sourceId
	 */
	public Integer getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the targetId
	 */
	public Integer getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	/**
	 * @return the targetType
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * @param targetType the targetType to set
	 */
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	/**
	 * @return the orgRelationType
	 */
	public String getOrgRelationType() {
		return orgRelationType;
	}

	/**
	 * @param orgRelationType the orgRelationType to set
	 */
	public void setOrgRelationType(String orgRelationType) {
		this.orgRelationType = orgRelationType;
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

	/**
	 * @return the sortNum
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
