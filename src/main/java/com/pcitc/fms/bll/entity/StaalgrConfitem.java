package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;


public class StaalgrConfitem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer staalgrConfitemId;
	private String code;
	private Integer staalgrConfId;
	private String name;
	private Integer weightings;
	private Integer craftSchemeId;
	private Integer opeindexId;
	private Integer inUse;
	private String creatorId;
	private String creator;
	private Date createTime;
	private String editorId;
	private String editor;
	private Date maintainTime;
	private Integer sortNum;
	private String des;
	private Integer version;
	private String craftScheme;
	private String opeindex;
	private String agentCode;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCraftScheme() {
		return craftScheme;
	}
	public void setCraftScheme(String craftScheme) {
		this.craftScheme = craftScheme;
	}
	public String getOpeindex() {
		return opeindex;
	}
	public void setOpeindex(String opeindex) {
		this.opeindex = opeindex;
	}
	public Integer getStaalgrConfitemId() {
		return staalgrConfitemId;
	}
	public void setStaalgrConfitemId(Integer staalgrConfitemId) {
		this.staalgrConfitemId = staalgrConfitemId;
	}
	public Integer getStaalgrConfId() {
		return staalgrConfId;
	}
	public void setStaalgrConfId(Integer staalgrConfId) {
		this.staalgrConfId = staalgrConfId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeightings() {
		return weightings;
	}
	public void setWeightings(Integer weightings) {
		this.weightings = weightings;
	}
	public Integer getCraftSchemeId() {
		return craftSchemeId;
	}
	public void setCraftSchemeId(Integer craftSchemeId) {
		this.craftSchemeId = craftSchemeId;
	}
	public Integer getOpeindexId() {
		return opeindexId;
	}
	public void setOpeindexId(Integer opeindexId) {
		this.opeindexId = opeindexId;
	}
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getEditorId() {
		return editorId;
	}
	public void setEditorId(String editorId) {
		this.editorId = editorId;
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
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
