package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class StaalgrConfitem  extends BaseResRep implements Serializable{
	@ExcelProperty("操作平稳率计算配置项ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer staalgrConfitemId;
	@ExcelProperty("操作平稳率计算配置项编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=200)
	private String code;
	@ExcelProperty("操作平稳率计算配置ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer staalgrConfId;
	@ExcelProperty("名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=200)
	@ResourceMember(InQueries="condition",Name="name")
	private String name;
	@ExcelProperty("权重")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer weightings;
	@ExcelProperty("工艺方案ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(InTemplate = true ,InQueries="condition",OnlyQuery=true,Name="craftSchemeId")
	private Integer craftSchemeId;
	@ExcelProperty("操作指标ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(InTemplate = true ,InQueries="condition",OnlyQuery=false,Name="opeindexId")
	private Integer opeindexId;
	private String opeindexCode;//操作指标编码 供查询使用
	@ExcelProperty("是否启用")
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries="condition",Name="inUse")
	private Integer inUse;
	//创建人Id
	@ExcelProperty("创建人ID")
//	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String creatorId;
	//创建人名称
	@ExcelProperty("创建人名称")
//	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String creator;
//		创建时间
	@ResourceMember(InTemplate = false)
	private Date createTime;
	//最后维护人Id
	@ExcelProperty("最后维护人ID")
//	@CheckField(CheckName = CheckNameType.CODE ,StrLength=30)
	private String editorId;
	//agentLet编码
	@ExcelProperty("agentLet编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=100)
	private String agentCode;
	//最后维护人
	@ExcelProperty("最后维护人名称")
//	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
	private String editor;
	//维护日期
	@ResourceMember(InTemplate = false)
	private Date maintainTime;
	//排序
	private Integer sortNum;
	//描述
	@ExcelProperty("说明")
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	//乐观锁版本
	private Integer version;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private Integer top;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private Integer skip;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$codeList")
	private List<String> codeList;
	@ResourceMember(InTemplate = false)
	private String craftScheme;
	@ResourceMember(InTemplate = false)
	private String opeindex;
	
	public StaalgrConfitem() {
		super();
	}
	public StaalgrConfitem(Integer craftSchemeId, Integer opeindexId, Integer inUse, Integer top, Integer skip,
			List<String> codeList,String opeindexCode,Integer staalgrConfId) {
		super();
		this.craftSchemeId = craftSchemeId;
		this.opeindexId = opeindexId;
		this.inUse = inUse;
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.opeindexCode = opeindexCode;
		this.staalgrConfId= staalgrConfId;
	}
	
	
	
	public StaalgrConfitem(Integer staalgrConfitemId, String code, Integer staalgrConfId, String name,
			Integer weightings, Integer inUse, String creatorId, String creator, Date createTime, String editorId,
			String editor, Date maintainTime, Integer sortNum, String des, Integer version, String craftScheme,
			String opeindex,Integer opeindexId) {
		super();
		this.staalgrConfitemId = staalgrConfitemId;
		this.code = code;
		this.staalgrConfId = staalgrConfId;
		this.name = name;
		this.weightings = weightings;
		this.inUse = inUse;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.craftScheme = craftScheme;
		this.opeindex = opeindex;
		this.opeindexId = opeindexId;
	}
	
	public String getOpeindexCode() {
		return opeindexCode;
	}
	public void setOpeindexCode(String opeindexCode) {
		this.opeindexCode = opeindexCode;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStaalgrConfitemId() {
		return staalgrConfitemId;
	}
	public void setStaalgrConfitemId(Integer staalgrConfitemId) {
		this.staalgrConfitemId = staalgrConfitemId;
	}
	public Integer getStaalgrConfId() {
		return staalgrConfId;
	}
	public void setStaalgrConfId(Integer staalgrConfId) {
		this.staalgrConfId = staalgrConfId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeightings() {
		return weightings;
	}
	public void setWeightings(Integer weightings) {
		this.weightings = weightings;
	}
	public Integer getCraftSchemeId() {
		return craftSchemeId;
	}
	public void setCraftSchemeId(Integer craftSchemeId) {
		this.craftSchemeId = craftSchemeId;
	}
	public Integer getOpeindexId() {
		return opeindexId;
	}
	public void setOpeindexId(Integer opeindexId) {
		this.opeindexId = opeindexId;
	}
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}
	public String getCraftScheme() {
		return craftScheme;
	}
	public void setCraftScheme(String craftScheme) {
		this.craftScheme = craftScheme;
	}
	public String getOpeindex() {
		return opeindex;
	}
	public void setOpeindex(String opeindex) {
		this.opeindex = opeindex;
	}
	
	
}
