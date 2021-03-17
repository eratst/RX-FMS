package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;

 /**
 * Title: orgView
 * Description:组织机构视图 entity
 * @author zhenqiang.zhao
 * @date 2017年6月21日
 * @version 1.0
 */
public class OrgView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orgViewId;   	//组织机构视图Id
	
	private String code;         	//组织机构视图编码，唯一约束，不重复
	
	private String name;         	//组织机构视图名称，如（中石化机构视图）
	
	private String shortName;		//组织机构视图简称，如（石化机构视图）
	
	private String type;			//组织机构视图类型
	
	private Integer appId;          //应用id			
	
	private Integer orgId;			//组织机构Id
	
	private Integer creatorId;		//创建人ID
	
	private String creator;			//创建人
	
	private Date createTime;		//创建时间
	
	private Integer editorId;		//修改人ID
	
	private String editor;			//修改人
	
	private Date maintainTime;		//修改时间
	
	private Integer enabled;		//启用标志
	
	private Integer orderId;		//排序
	
	private String  des;			//说明

	
	
	/**
	 * @return the appId
	 */
	public Integer getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
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
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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
