package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
import cc.aicode.e2e.annotation.ExcelEntity;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class OrgUnitRelation extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@ResourceMember(InTemplate =true)
	private Integer orgUnitRelationId; 		//组织机构单元关联Id
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer orgUnitId;		//组织机构单元id
	
	@CheckField(CheckName = CheckNameType.CODE,StrLength = 50)
	private String orgUnitCode;		//组织机构单元编码 
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer factoryId ;			//工厂Id
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer entityId;			//组织机构单元关联实体id
	
	@CheckField(CheckName = CheckNameType.NAME,StrLength = 50)
	@ResourceMember(InQueries="condition",Name="entityType")
	private String entityType;			//组织机构单元关联实体类型
	
	@CheckField(CheckName = CheckNameType.NAME,StrLength = 50)
	private String type;				//用于区分机构单元 or 机构单元视图
	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;				//创建人
	
	@CheckField(CheckName = CheckNameType.CREATETIME)
	private Date createTime;			//创建时间
	
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength = 50)
	private String editor;				//修改人姓名
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime;			//修改时间
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer enabled;			//启用标志
	
	@ResourceMember(InTemplate =false)
	private Integer sort_num;			//排序
	
	@ResourceMember(InTemplate =false)
	private Integer version;			//乐观锁版本
	
	@CheckField(CheckName = CheckNameType.DES,StrLength = 200)
	private String des;					//说明
	
	@ResourceMember(InQueries="search",Name="$idList", OnlyQuery =true)
	private List<Integer> idList;
	
	@ResourceMember(InQueries="search,condition",Name="$top", OnlyQuery =true)
	private Integer top;
	
	@ResourceMember(InQueries="search,condition",Name="$skip", OnlyQuery =true)
	private Integer skip;
	
	/**
	 * 
	 */
	public OrgUnitRelation() {
		super();
	}
	

	/**
	 * @param orgUnitId
	 * @param orgUnitCode
	 * @param entityId
	 * @param entityType
	 * @param enabled
	 * @param idList
	 */
	public OrgUnitRelation(Integer orgUnitId, String orgUnitCode,
			Integer entityId, String entityType, String type ,Integer enabled,
			List<Integer> idList) {
		super();
		this.orgUnitId = orgUnitId;
		this.orgUnitCode = orgUnitCode;
		this.entityId = entityId;
		this.entityType = entityType;
		this.type = type;
		this.enabled = enabled;
		this.idList = idList;
	}


	
	
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
		return sort_num;
	}


	/**
	 * @param sort_num the sort_num to set
	 */
	public void setSort_num(Integer sort_num) {
		this.sort_num = sort_num;
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
	 * @return the idList
	 */
	public List<Integer> getIdList() {
		return idList;
	}


	/**
	 * @param idList the idList to set
	 */
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}


	/**
	 * @return the top
	 */
	public Integer getTop() {
		return top;
	}


	/**
	 * @param top the top to set
	 */
	public void setTop(Integer top) {
		this.top = top;
	}


	/**
	 * @return the skip
	 */
	public Integer getSkip() {
		return skip;
	}


	/**
	 * @param skip the skip to set
	 */
	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	
	
	
}
