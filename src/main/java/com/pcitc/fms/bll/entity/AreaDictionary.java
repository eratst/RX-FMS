package com.pcitc.fms.bll.entity;

import com.pcitc.fms.dal.pojo.Area_NodeType_Num;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import cc.aicode.e2e.annotation.ExcelEntity;
@ExcelEntity
public class AreaDictionary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	//类型名称（用于显示）
	private String type;
	
	//排序
	private Integer sortNum;
	//描述
	private String des;
	//乐观锁版本
	private Integer version;
	
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	//code集合
	private List<String> codeList;
	//id集合
	private List<Integer> idList;

	private List<Area_NodeType_Num> typeAndNums;

	
	
	
	public String getAreaLatitude() {
		return areaLatitude;
	}

	public void setAreaLatitude(String areaLatitude) {
		this.areaLatitude = areaLatitude;
	}

	public String getAreaAltitude() {
		return areaAltitude;
	}

	public void setAreaAltitude(String areaAltitude) {
		this.areaAltitude = areaAltitude;
	}

	public String getAreaLongitude() {
		return areaLongitude;
	}

	public void setAreaLongitude(String areaLongitude) {
		this.areaLongitude = areaLongitude;
	}

	public List<Area_NodeType_Num> getTypeAndNums() {
		return typeAndNums;
	}

	public void setTypeAndNums(List<Area_NodeType_Num> typeAndNums) {
		this.typeAndNums = typeAndNums;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public AreaDictionary() {
		super();
	}
	


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
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
	
	
}
