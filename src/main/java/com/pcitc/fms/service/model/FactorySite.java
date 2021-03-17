package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

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
public class FactorySite extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelProperty("工厂id")
	private Integer factorySiteId;
	@ExcelProperty("工厂编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String factorySiteCode;
	//区域名称
	@ExcelProperty("区域名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String name;
	//区域类型Id
	@ExcelProperty("区域类型")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=6)
	private Integer areaTypeId;
	//上级区域（工厂）
	@ExcelProperty("上级区域（工厂）")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=6)
	private Integer factoryId;
	//组织机构Id
	@CheckField(CheckName = CheckNameType.ID ,StrLength=6)
	@ExcelProperty("组织机构Id")
	private Integer orgId;
	//区域简称
	@ExcelProperty("区域简称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String shortName;
	//创建人Id
	@ExcelProperty("创建人ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=20)
	private Integer creatorId;
	//创建人名称
	@ExcelProperty("创建人名称")
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String creator;
	//创建时间
	@ResourceMember(InTemplate = false)
	private Date createTime;
	//最后维护人Id
	@ExcelProperty("最后维护人ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=20)
	private Integer editorId;
	//最后维护人
	@ExcelProperty("最后维护人名称")
	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
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
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer enabled;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$codeList")
	private List<String> codeList;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$idList")
	private List<Integer> idList;
	@ResourceMember(InTemplate = false ,InQueries="search,condition",OnlyQuery=true,Name="$top")
	private Integer top;
	@ResourceMember(InTemplate = false ,InQueries="search,condition",OnlyQuery=true,Name="$skip")
	private Integer skip ;
	
	
	
	public FactorySite() {
		super();
	}
	

	public FactorySite(String name, Integer factoryId, Integer orgId, String shortName, Integer enabled,
			List<String> codeList, List<Integer> idList, Integer top, Integer skip) {
		super();
		this.name = name;
		this.factoryId = factoryId;
		this.orgId = orgId;
		this.shortName = shortName;
		this.enabled = enabled;
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}


	public Integer getFactorySiteId() {
		return factorySiteId;
	}


	public void setFactorySiteId(Integer factorySiteId) {
		this.factorySiteId = factorySiteId;
	}


	public String getFactorySiteCode() {
		return factorySiteCode;
	}


	public void setFactorySiteCode(String factorySiteCode) {
		this.factorySiteCode = factorySiteCode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	public Integer getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
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


	public Integer getEditorId() {
		return editorId;
	}


	public void setEditorId(Integer editorId) {
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


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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



}

