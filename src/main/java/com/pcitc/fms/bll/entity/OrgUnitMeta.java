package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrgUnitMeta implements Serializable {
	private static final long serialVersionUID = 1L;

	// 机构单元Id
	private Integer orgUnitId;
	// 机构单元编码
	private String code;
	// 机构单元名称
	private String name;

	// 机构的简称
	private String shortName;

	// 机构的英文名称
	private String englishName;

	// 机构性质
	private Integer typeId;

	// 启用标志
	private Integer enabled;

	// 联系人
	private String contract;

	// 机构Id
	private Integer orgId;

	// 机构单元父级Id
	private Integer parentId;

	// 创建人ID
	private Integer creatorId;

	// 创建人
	private String creator;

	// 创建时间
	private Date createTime;

	// 修改人Id
	private Integer editorId;

	// 修改人(更新时必填)
	private String editor;

	// 维护时间(如果为空字符串，则按系统时间生成)
	private Date maintainTime;

	// 排序
	private Integer orderId;

	// 说明
	private String des;

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
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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
