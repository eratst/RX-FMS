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
public class DictionaryTable extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	// 属性id
	@ResourceMember(InTemplate = false)
	private Integer dictionaryTableId;
	@ResourceMember(InQueries = "condition", Name = "entityId")
	@CheckField(CheckName=CheckNameType.IDMAYBENULL, StrLength=50 ) 
	private Integer entityId;
	@CheckField(CheckName=CheckNameType.ENABLED) 
	private Integer enabled;
	// 实体表名
	@CheckField(CheckName=CheckNameType.NAME, StrLength=50 ) 
	@ResourceMember(InQueries = "condition", Name = "entityTableName")
	private String entityTableName;
	
	// 实体类型
	@CheckField(CheckName=CheckNameType.NAME, StrLength=50 ) 
	@ResourceMember(InQueries = "condition", Name = "entityType")
	private String entityType;

	// 字段名
	@CheckField(CheckName=CheckNameType.NAME, StrLength=50 ) //校验
	private String fieldName;

	// 字段值
	@CheckField(CheckName=CheckNameType.NAME, StrLength=50 )
	private String fieldValue;

	// 名称
	@CheckField(CheckName=CheckNameType.NAME, StrLength=50 )
	@ResourceMember(InQueries = "condition", Name = "name")
	private String name;

	// 创建人
	@CheckField(CheckName=CheckNameType.CREATOR, StrLength=50 )
	private String creator;

	// 创建时间
	@ResourceMember(InTemplate = false)
//	@CheckField(CheckName=CheckNameType.CREATETIME)
	private Date createTime;

	// 修改人
	@CheckField(CheckName=CheckNameType.EDITOR,StrLength=50)
	private String editor;

	// 维护时间
	@ResourceMember(InTemplate = false)
//	@CheckField(CheckName=CheckNameType.MAINTAINTIME)
	private Date maintainTime;

	// 备注
	@CheckField(CheckName=CheckNameType.DES, StrLength=200 )
	private String des;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private int top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private int skip;

	public DictionaryTable() {
		super();
	}

	




	public DictionaryTable(String entityTableName, String entityType, String name, List<Integer> idList, int top,
			int skip) {
		super();
		this.entityTableName = entityTableName;
		this.entityType = entityType;
		this.name = name;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}






	public String getEntityType() {
		return entityType;
	}



	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}



	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public Integer getDictionaryTableId() {
		return dictionaryTableId;
	}

	public void setDictionaryTableId(Integer dictionaryTableId) {
		this.dictionaryTableId = dictionaryTableId;
	}

	public String getEntityTableName() {
		return entityTableName;
	}

	public void setEntityTableName(String entityTableName) {
		this.entityTableName = entityTableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}
}
