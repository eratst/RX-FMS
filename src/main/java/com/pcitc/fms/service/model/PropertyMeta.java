package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class PropertyMeta extends BaseResRep implements Serializable {

	
	public PropertyMeta(){}
	private static final long serialVersionUID = 1L;

	// 属性id
	private Integer propertyId;

	// 实体id
	private Integer entityId;
	
	// 实体编码
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String entityCode;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String shortName;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String type;
	// 属性编码
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String propertyCode;

	// 属性名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "propertyName")
	private String propertyName;

	// 数据类型
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "dataType")
	private String dataType;

	// 创建人
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String creator;

	// 创建时间
//	@CheckField(CheckName = CheckNameType.CREATETIME ,StrLength=50)
	@ResourceMember(InTemplate = false)
	private Date createTime;

	// 修改人
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength=50)
	private String editor;

	// 维护时间
//	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	@ResourceMember(InTemplate = false)
	private Date maintainTime;

	// 备注
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;



	public PropertyMeta(String propertyCode,String propertyName, String shortName,String dataType, String entityCode, List<String> codeList,
			List<Integer> idList, int top, int skip) {
		super();
		this.propertyCode=propertyCode;
		this.propertyName = propertyName;
		this.shortName = shortName;
		this.dataType = dataType;
		this.entityCode = entityCode;
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}

	
	
	public String getEntityCode() {
		return entityCode;
	}



	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}



	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}


	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
