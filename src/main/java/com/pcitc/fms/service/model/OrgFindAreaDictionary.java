package com.pcitc.fms.service.model;

import com.pcitc.fms.dal.pojo.Area_NodeType_Num;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class OrgFindAreaDictionary extends BaseResRep implements Serializable {

	//区域ID
	private Integer areaDictionaryId;
	//区域编码
	private String areaCode;
	//区域名称
	private String name;
	//区域简称
	private String shortName;
	//区域类型id
	private Integer areaTypeId;
	//所属组织机构（工厂）
	private Integer factoryId;
	
	private String factoryCode;
	//状态
	private Integer enabled;
	//描述
	private String des;
	//类型Name（用于显示）
	private String type;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private String top;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private String skip;
	@ResourceMember(InQueries="search",OnlyQuery=true,Name="$codeList")

	private List<String> codeList;


	private List<Area_NodeType_Num> typeAndNums;

	
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public List<Area_NodeType_Num> getTypeAndNums() {
		return typeAndNums;
	}

	public void setTypeAndNums(List<Area_NodeType_Num> typeAndNums) {
		this.typeAndNums = typeAndNums;
	}
	public Integer getAreaDictionaryId() {
		return areaDictionaryId;
	}
	public void setAreaDictionaryId(Integer areaDictionaryId) {
		this.areaDictionaryId = areaDictionaryId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
	}
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}
	
	
}
