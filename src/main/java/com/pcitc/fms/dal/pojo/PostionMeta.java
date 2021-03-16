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

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

@Entity
@Table(name = "POSTION")
public class PostionMeta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "POSTIONID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTION_GENERATOR")
	@SequenceGenerator(name = "POSTION_GENERATOR", sequenceName = "POSTION_SEQ", allocationSize = 1)
	private Integer postionId;
	
	@RegionMember
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SHORTNAME")
	private String shortName;
	
	@Column(name = "PARENTID")
	private Integer parentId;
	
	@Column(name = "PARENTTYPE")
	private String parentType;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "CREATORID")
	private String creatorId;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "CREATETIME", insertable = false)
	private Date createTime;
	
	@Column(name = "EDITORID")
	private String editorId;
	
	@Column(name = "EDITOR")
	private String editor;
	
	@Column(name = "MAINTAINTIME", insertable = false)
	private Date maintainTime;
	
	@Column(name = "ENABLED")
	private Integer enabled;
	
	@Column(name = "ORDERID")
	private Integer orderId;
	
	@Column(name = "DES")
	private String des;

	public Integer getPostionId() {
		return postionId;
	}

	public void setPostionId(Integer postionId) {
		this.postionId = postionId;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
