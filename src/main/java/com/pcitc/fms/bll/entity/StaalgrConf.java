package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
public class StaalgrConf implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer staalgrConfId;
	private String code;
	private String name;
	private Integer equipId;
	private Integer monLevelId;
	private Integer staalgrId;
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
	private String staalgr;
	private String monLevle;
	private String agentCode;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public Integer getStaalgrConfId() {
		return staalgrConfId;
	}
	public void setStaalgrConfId(Integer staalgrConfId) {
		this.staalgrConfId = staalgrConfId;
	}
	
	public String getEditorId() {
		return editorId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getMonLevelId() {
		return monLevelId;
	}
	public void setMonLevelId(Integer monLevelId) {
		this.monLevelId = monLevelId;
	}
	public Integer getStaalgrId() {
		return staalgrId;
	}
	public void setStaalgrId(Integer staalgrId) {
		this.staalgrId = staalgrId;
	}
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public void setEditorId(String editorId) {
		this.editorId = editorId;
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
	public String getStaalgr() {
		return staalgr;
	}
	public void setStaalgr(String staalgr) {
		this.staalgr = staalgr;
	}
	public String getMonLevle() {
		return monLevle;
	}
	public void setMonLevle(String monLevle) {
		this.monLevle = monLevle;
	}
	
}
