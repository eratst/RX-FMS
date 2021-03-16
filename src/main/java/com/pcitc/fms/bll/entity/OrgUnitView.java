package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import cc.aicode.e2e.annotation.ExcelProperty;

 /**
 * Title: orgUnitView
 * Description:组织机构单元视图
 * @author zhenqiang.zhao
 * @date 2017年6月21日
 * @version 1.0
 */
public class OrgUnitView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orgUnitViewId; 		//组织机构单元视图Id
	
	private String code;				//组织机构单元视图编码，唯一约束，不重复
	
	private String name;				//组织机构单元视图名称，如（炼油一厂）      
	
	private String shortName;			//组织机构单元视图简称
	
	private Integer orgViewId;			//组织机构视图Id
	
	private Integer typeId;				//类型ID（从字典获取，如：分厂、车间…）
	
	private String contract;			//联系人
	
	private Integer parentId;			//机构单元视图父级Id
	
	private Integer orgUnitId;			//机构单元Id
	
	private Integer creatorId;			//创建人ID
	
	private String creator;				//创建人
	
	private Date createTime;			//创建时间
		
	private Integer editorId;			//修改人ID
	
	private String editor;				//修改人姓名
	
	private Date maintainTime;			//修改时间
	
	private Integer enabled;			//启用标志
	
	private Integer orderId;			//排序
	
	private String des;					//说明

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the orgViewId
	 */
	public Integer getOrgViewId() {
		return orgViewId;
	}

	/**
	 * @param orgViewId the orgViewId to set
	 */
	public void setOrgViewId(Integer orgViewId) {
		this.orgViewId = orgViewId;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	 * @return the creatorId
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
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
	 * @return the editorId
	 */
	public Integer getEditorId() {
		return editorId;
	}

	/**
	 * @param editorId the editorId to set
	 */
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
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
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
