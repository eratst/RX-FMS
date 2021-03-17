package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_OPM_CRAFTSCHEME")
public class CraftScheme implements Serializable{
	@RegionMember
	@Id
	@Column(name="CRAFT_SCHEME_ID")
	private Integer craftSchemeId;
	@Column(name="NAME")
	private String name;
	@Column(name="SNAME")
	private String shortName;
	@Column(name="STD_CODE")
	private String code;
	@Column(name="EQUIP_ID")
	private Integer equipId;
	@Column(name="CRT_USER_ID")
	private Integer creatorId;
	@Column(name="CRT_USER_NAME")
	private String creator;
	@Column(name="CRT_DATE")
	private Date createTime;
	@Column(name="MNT_USER_ID")
	private Integer editorId;
	@Column(name="MNT_USER_NAME")
	private String editor;
	@Column(name="MNT_DATE")
	private Date maintainTime;
	@Column(name="SORT_NUM")
	private Integer sortNum;
	@Column(name="DES")
	private String des;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="IN_USE")
	private Integer inUse;
	public CraftScheme() {
		super();
	}
	public Integer getCraftSchemeId() {
		return craftSchemeId;
	}
	public void setCraftSchemeId(Integer craftSchemeId) {
		this.craftSchemeId = craftSchemeId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
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
	public Integer getEditorId() {
		return editorId;
	}
	public void setEditorId(Integer editorId) {
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
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	
	
}
