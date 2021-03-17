package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class FactoryModelStr extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer factoryId;
	
	private String code;
	
	private String name;
	
	private String shortName;
	private String businessType;
	private String enabled;
	private String creator;
	
	private String createTime;
	private String editor;
	
	private String maintainTime;
	private String des;

	private List<Integer> idList;
	private List<String> codeList;
	private Integer top;
	private Integer skip;
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
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
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
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Integer getSkip() {
		return skip;
	}
	public void setSkip(Integer skip) {
		this.skip = skip;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
