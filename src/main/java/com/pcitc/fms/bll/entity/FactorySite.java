package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;


public class FactorySite implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer factorySiteId;
	//区域编码
	private String factorySiteCode;
	//区域名称
	private String name;
	//区域简称
	private String shortName;
	//区域类型Id
	private Integer areaTypeId;
	//上级区域（工厂）
	private Integer factoryId;
	//组织机构Id
	private Integer orgId;
	//创建人Id
	private Integer creatorId;
	//创建人名称
	private String creator;
	//创建时间
	private Date createTime;
	//最后维护人Id
	private Integer editorId;
	//最后维护人
	private String editor;
	//维护日期
	private Date maintainTime;
	//排序
	private Integer sortNum;
	//描述
	private String des;
	//乐观锁版本
	private Integer version;
	//状态
	private Integer enabled;
	private List<String> codeList;
	private List<Integer> idList;
	
	
	public FactorySite() {
		super();
	}


	public Integer getFactorySiteId() {
		return factorySiteId;
	}


	public void setFactorySiteId(Integer factorySiteId) {
		this.factorySiteId = factorySiteId;
	}


	public String getFactorySiteCode() {
		return factorySiteCode;
	}


	public void setFactorySiteCode(String factorySiteCode) {
		this.factorySiteCode = factorySiteCode;
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


	public Integer getAreaTypeId() {
		return areaTypeId;
	}


	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}


	public Integer getFactoryId() {
		return factoryId;
	}


	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	public List<String> getCodeList() {
		return codeList;
	}


	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}


	public List<Integer> getIdList() {
		return idList;
	}


	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
}

