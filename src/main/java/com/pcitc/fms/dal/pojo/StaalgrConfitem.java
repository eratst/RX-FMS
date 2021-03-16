package com.pcitc.fms.dal.pojo;

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
@Entity
@Table(name = "T_OPM_STAALGRCONFITEM")
public class StaalgrConfitem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RegionMember
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STAALGRCONFITEM_GENERATOR")
	@SequenceGenerator(name="STAALGRCONFITEM_GENERATOR", sequenceName="SEQ_T_OPM_STAALGRCONFITEM",allocationSize=1) 
	@Column(name="STA_ALGR_CONF_ITEM_ID")
	private Integer staalgrConfitemId;
	@Column(name="STA_ALGR_CONF_ID")
	private Integer staalgrConfId;
	@Column(name="CODE")
	private String code;
	@Column(name="NAME")
	private String name;
	@Column(name="WEIGHTINGS")
	private Integer weightings;
	@Column(name="CRAFT_SCHEME_ID")
	private Integer craftSchemeId;
	@Column(name="OPE_INDEX_ID")
	private Integer opeindexId;
	@Column(name="IN_USE")
	private Integer inUse;
	@Column(name="CRT_USER_ID")
	private String creatorId;
	@Column(name="CRT_USER_NAME")
	private String creator;
	@Column(name="CRT_DATE",insertable = false)
	private Date createTime;
	@Column(name="MNT_USER_ID")
	private String editorId;
	@Column(name="MNT_USER_NAME")
	private String editor;
	@Column(name="MNT_DATE",insertable = false)
	private Date maintainTime;
	@Column(name="SORT_NUM")
	private Integer sortNum;
	@Column(name="DES")
	private String des;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="AGENTCODE")
	private String agentCode;
	@Transient
	private String craftScheme;
	@Transient
	private String opeindex;
	public StaalgrConfitem(Integer staalgrConfitemId, Integer staalgrConfId, String code, String name,
			Integer weightings, Integer inUse, String creatorId, String creator, Date createTime, String editorId,
			String editor, Date maintainTime, Integer sortNum, String des, Integer version, String craftScheme,
			String opeindex,String agentCode,Integer craftSchemeId,Integer opeindexId) {
		super();
		this.staalgrConfitemId = staalgrConfitemId;
		this.staalgrConfId = staalgrConfId;
		this.code = code;
		this.name = name;
		this.weightings = weightings;
		this.inUse = inUse;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.craftScheme = craftScheme;
		this.opeindex = opeindex;
		this.agentCode = agentCode;
		this.craftSchemeId = craftSchemeId;
		this.opeindexId = opeindexId;
	}
	public StaalgrConfitem() {
		super();
	}
	
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
	
	
}
