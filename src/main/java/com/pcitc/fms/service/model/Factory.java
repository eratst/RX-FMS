package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href="/FactoryModelService/Factories",rel="search",prompt="列表查询")
@QueryContract(href="/FactoryModelService/Factories",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class Factory extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer factoryId;
	
	
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String code;
	
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="name")
	private String name;
	
	
	@CheckField(CheckName = CheckNameType.NAME,StrLength=50)
	@ResourceMember(InQueries="condition",Name="shortName")
	private String shortName;
	
	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;
	
	
	@CheckField(CheckName =CheckNameType.CREATETIME)
	@ResourceMember(InTemplate = false)
	private Date createTime;
	
	
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength =50)
	private String editor;
	
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	@ResourceMember(InTemplate = false)
	private Date maintainTime;
	
	
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	
	//工艺类型
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(InQueries="condition",Name="businessType")
	private Integer businessType;
	
	//所属模块
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries="condition",Name="Enabled")
	private Integer enabled;
	
	@CheckField(CheckName = CheckNameType.CREATORID)
	private Integer creatorId;
	
	@CheckField(CheckName = CheckNameType.EDITORID)
	private Integer editorId;
	
	
	@ResourceMember(InQueries="search",OnlyQuery=true,Name="$codeList")
	private String codeList;
	
	@ResourceMember(InQueries="search",OnlyQuery=true,Name="$idList")
	private String idList;
	
	public Integer getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
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
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getEditorId() {
		return editorId;
	}
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}
	public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public Integer getEnabled() {
        return enabled;
    }
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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
	
	public String getCodeList() {
		return codeList;
	}
	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
}
