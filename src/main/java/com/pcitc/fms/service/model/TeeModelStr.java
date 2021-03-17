package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class TeeModelStr extends BaseResRep implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teeId;
	private String code;
	private String name;
	private String shortName;
	private String tubulation;
	private String parentID;
	private String parentType;
	private String type;
	private String creator;
	private String createTime;
	private String editor;
	private String maintainTime;
	private String enabled;
	private String des;
	private String parentCode;
	
	public String getParentCode() {
		return parentCode;
	}


	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}


	public TeeModelStr() {
		super();
	}
	
	
	public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getTeeId() {
		return teeId;
	}
	public void setTeeId(String teeId) {
		this.teeId = teeId;
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
	public String getTubulation() {
		return tubulation;
	}
	public void setTubulation(String tubulation) {
		this.tubulation = tubulation;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
	

}
