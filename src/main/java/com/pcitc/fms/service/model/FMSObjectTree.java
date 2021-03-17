package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class FMSObjectTree extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String name;
	
	private String alias;
	
	private String typeName;
	
	private String hierarchyTypeName;
	
	private String upperCode;
	
	private String upperName;
	
	private String upperAlias;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getHierarchyTypeName() {
		return hierarchyTypeName;
	}

	public void setHierarchyTypeName(String hierarchyTypeName) {
		this.hierarchyTypeName = hierarchyTypeName;
	}

	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}

	public String getUpperName() {
		return upperName;
	}

	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}

	public String getUpperAlias() {
		return upperAlias;
	}

	public void setUpperAlias(String upperAlias) {
		this.upperAlias = upperAlias;
	}
	
}
