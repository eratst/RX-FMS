package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
public class StaalgrConf extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelProperty("操作平稳率计算配置ID")
	@ResourceMember(InQueries="condition",Name="staalgrConfId")
	private Integer staalgrConfId;
	@ExcelProperty("操作平稳率计算配置编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=200)
	private String code;
	@ExcelProperty("名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=200)
	@ResourceMember(InQueries="condition",Name="name")
	private String name;
	@ExcelProperty("装置ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer equipId;
	@ExcelProperty("监控级别ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(InTemplate = true ,InQueries="condition",Name="monLevelId")
	private Integer monLevelId;
	@ExcelProperty("操作平稳率算法ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(InTemplate = true ,InQueries="condition",Name="staalgrId")
	private Integer staalgrId;
	@ExcelProperty("是否启用")
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries="condition",Name="inUse")
	private Integer inUse;
	//创建人Id
	@ExcelProperty("创建人ID")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=10)
	private String creatorId;
	//创建人名称
	@ExcelProperty("创建人名称")
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String creator;
//		创建时间
	@ResourceMember(InTemplate = false)
	private Date createTime;
	//最后维护人Id
	@ExcelProperty("最后维护人ID")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=10)
	private String editorId;
	//最后维护人
	@ExcelProperty("最后维护人名称")
	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
	private String editor;
	//维护日期
	@ResourceMember(InTemplate = false)
	private Date maintainTime;
	//排序
	private Integer sortNum;
	//agentLet编码
	@ExcelProperty("agentLet编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=100)
	private String agentCode;
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
	private String staalgr;
	@ResourceMember(InTemplate = false)
	private String monLevle;
	@ResourceMember(InTemplate = false)
	private String unitCode;
	
	public StaalgrConf(Integer staalgrConfId, String code, String name, Integer equipId, Integer inUse,
			String creatorId, String creator, Date createTime, String editorId, String editor, Date maintainTime,
			Integer sortNum, String des, Integer version, Integer top, Integer skip, List<String> codeList,
			String staalgr, String monLevle) {
		super();
		this.staalgrConfId = staalgrConfId;
		this.code = code;
		this.name = name;
		this.equipId = equipId;
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
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.staalgr = staalgr;
		this.monLevle = monLevle;
	}

	public StaalgrConf(String name, Integer equipId, Integer monLevelId, Integer staalgrId, Integer inUse, Integer top,
			Integer skip, List<String> codeList,String unitCode) {
		super();
		this.name = name;
		this.equipId = equipId;
		this.monLevelId = monLevelId;
		this.staalgrId = staalgrId;
		this.inUse = inUse;
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.unitCode = unitCode;
	}
	
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public StaalgrConf() {
		super();
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

	public Integer getStaalgrConfId() {
		return staalgrConfId;
	}
	public void setStaalgrConfId(Integer staalgrConfId) {
		this.staalgrConfId = staalgrConfId;
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
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getMonLevelId() {
		return monLevelId;
	}
	public void setMonLevelId(Integer monLevelId) {
		this.monLevelId = monLevelId;
	}
	public Integer getStaalgrId() {
		return staalgrId;
	}
	public void setStaalgrId(Integer staalgrId) {
		this.staalgrId = staalgrId;
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
	public String getStaalgr() {
		return staalgr;
	}
	public void setStaalgr(String staalgr) {
		this.staalgr = staalgr;
	}
	public String getMonLevle() {
		return monLevle;
	}
	public void setMonLevle(String monLevle) {
		this.monLevle = monLevle;
	}
	
}
