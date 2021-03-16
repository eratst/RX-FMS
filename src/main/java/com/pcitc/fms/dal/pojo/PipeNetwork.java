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

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.dispatcher.SysGlobal;


@Entity
@Table(name = "T_PM_PIPENET")
public class PipeNetwork implements Serializable {

private static final long serialVersionUID = 1L;
	
@Id
@Column( name = "AREA_ID")
private Integer pipeNetworkId;
//区域编码
@Column( name="AREA_CODE")
private String pipeNetworkCode;
//创建人Id
@Column( name="CRT_USER_ID")
private String creatorId;
//创建人名称
@Column( name="CRT_USER_NAME")
private String creator;
//创建时间
@Column( name="CRT_DATE",insertable = false)
private Date createTime;
//最后维护人Id
@Column( name="MNT_USER_ID")
private String editorId;
//最后维护人
@Column( name="MNT_USER_NAME")
private String editor;
//维护日期
@Column( name="MNT_DATE",insertable = false)
private Date maintainTime;
//排序
@Column( name="SORT_NUM")
private Integer sortNum;
//乐观锁版本
@Column( name="VERSION")
private Integer version;
//工艺类型
@Column( name="TECHNIC_ID")
private Integer technicId;



public PipeNetwork() {
	super();
}
public Integer getPipeNetworkId() {
	return pipeNetworkId;
}
public void setPipeNetworkId(Integer pipeNetworkId) {
	this.pipeNetworkId = pipeNetworkId;
}
public String getPipeNetworkCode() {
	return pipeNetworkCode;
}
public void setPipeNetworkCode(String pipeNetworkCode) {
	this.pipeNetworkCode = pipeNetworkCode;
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
public Integer getTechnicId() {
	return technicId;
}
public void setTechnicId(Integer technicId) {
	this.technicId = technicId;
}



	
}
