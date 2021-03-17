package com.pcitc.fms.service.model;

import com.pcitc.fms.dal.pojo.Area_NodeType_Num;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class AreaDictionary extends BaseResRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer areaDictionaryId;
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String factoryCode;
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String areaCode;
	@ResourceMember(InQueries="condition",Name="name")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String name;
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String creator;
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String type;
	private Date maintainTime;
	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
	private String editor;
	private Date createTime;
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer enabled;
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private String top;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private String skip;
	@ResourceMember(InQueries="search",OnlyQuery=true,Name="$codeList")
	private List<String> codeList;
	@ResourceMember(InQueries="search",OnlyQuery=true,Name="$idList")
	private List<Integer> idList;
	private Integer factoryId;
	
	private Integer areaId;
	private Integer version;
	private Integer sortNum;
	private String shortName;
	private String creatorId;
	private String editorId;

	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	
	

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
	
	
	public AreaDictionary() {
		super();
	}

	
	public AreaDictionary(String name, Integer enabled, String top, String skip, List<String> codeList,
			List<Integer> idList) {
		super();
		this.name = name;
		this.enabled = enabled;
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.idList = idList;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getSortNum() {
		return sortNum;
	}


	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public Integer getAreaId() {
		return areaId;
	}


	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}


	public Integer getFactoryId() {
		return factoryId;
	}


	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}


	public String getCreator() {
		return creator;
	}







	public void setCreator(String creator) {
		this.creator = creator;
	}







	public Date getMaintainTime() {
		return maintainTime;
	}







	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}







	public String getEditor() {
		return editor;
	}







	public void setEditor(String editor) {
		this.editor = editor;
	}







	public Date getCreateTime() {
		return createTime;
	}







	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}







	public Integer getEnabled() {
		return enabled;
	}







	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}







	public String getFactoryCode() {
		return factoryCode;
	}





	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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





	public List<Integer> getIdList() {
		return idList;
	}





	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	
	
}
