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
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class EntityMeta extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	// Query
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String code;
//	@CheckField(CheckName = CheckNameType.IDMAYBENULL ,StrLength=50)
	private Integer entityId;
	// 实体名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "entityName")
	private String entityName;
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "shortName")
	private String shortName;

	// 实体类型
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "entityType")
	private String entityType;

	// 实体表名
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "entityTableName")
	private String entityTableName;

	// 创建人(新增时必填)
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength=50)
	private String creator;

	// 创建时间(如果为空字符串，则按系统时间生成)
//	@CheckField(CheckName = CheckNameType.CREATETIME)
	@ResourceMember(InTemplate=false)
	private Date createTime;

	// 修改人(更新时必填)
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength=50)
	private String editor;

	// 维护时间(如果为空字符串，则按系统时间生成)
	@ResourceMember(InTemplate = false)
//	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime;

	// 启用标识
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer enabled;

	// 说明
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;

	public EntityMeta() {
		super();
	}

	

	

	public EntityMeta(List<Integer> idList, List<String> codeList, String code, String entityName, String entityType,
			String entityTableName, Integer top, Integer skip) {
		super();
		this.idList = idList;
		this.codeList = codeList;
		this.code = code;
		this.entityName = entityName;
		this.entityType = entityType;
		this.entityTableName = entityTableName;
		this.top = top;
		this.skip = skip;
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

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityTableName() {
		return entityTableName;
	}

	public void setEntityTableName(String entityTableName) {
		this.entityTableName = entityTableName;
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

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
