package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_COMMUNITY")
//@SequenceGenerator(name = "mseq", sequenceName = "SEQ_COMMUNITY", allocationSize = 1)
public class Community implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(table = "T_PM_COMMUNITY",name = "AREA_ID")
	private Integer communityId;
	//区域编码
	@Column(table = "T_PM_COMMUNITY",name="AREA_CODE")
	private String communityCode;
	//创建人Id
	@Column(table = "T_PM_COMMUNITY",name="CRT_USER_ID")
	private String creatorId;
	//创建人名称
	@Column(table = "T_PM_COMMUNITY",name="CRT_USER_NAME")
	private String creator;
	//创建时间
	@Column(table = "T_PM_COMMUNITY",name="CRT_DATE",insertable = false)
	private Date createTime;
	//最后维护人Id
	@Column(table = "T_PM_COMMUNITY",name="MNT_USER_ID")
	private String editorId;
	//最后维护人
	@Column(table = "T_PM_COMMUNITY",name="MNT_USER_NAME")
	private String editor;
	//维护日期
	@Column(table = "T_PM_COMMUNITY",name="MNT_DATE",insertable = false)
	private Date maintainTime;
	//排序
	@Column(table = "T_PM_COMMUNITY",name="SORT_NUM")
	private Integer sortNum;
	//乐观锁版本
	@Column(table = "T_PM_COMMUNITY",name="VERSION")
	private Integer version;
	public Community(Integer communityId, String communityCode, String creatorId, String creator, Date createTime,
			String editorId, String editor, Date maintainTime, Integer sortNum, Integer version) {
		super();
		this.communityId = communityId;
		this.communityCode = communityCode;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.version = version;
	}
	
	public Community() {
		super();
	}

	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getCommunityCode() {
		return communityCode;
	}
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
	
	
}















