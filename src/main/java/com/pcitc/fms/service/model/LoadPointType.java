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
public class LoadPointType extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@ResourceMember(InTemplate = false)
	private Long loadPointTypeId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装卸点类型编码")
	@ResourceMember(InQueries = "condition", Name = "loadPointTypeCode")
	private String loadPointTypeCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "装卸点类型名称")
	@ResourceMember(InQueries = "condition", Name = "loadPointTypeName")
	private String loadPointTypeName;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 50, Explain = "创建人Id")
	private String crtUserId;// 创建人Id

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 200, Explain = "创建人名称")
	private String crtUserName;// 创建人名称

	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;// 创建时间

	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 50, Explain = "最后维护人Id")
	private String mntUserId;// 最后维护人Id

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 200, Explain = "最后维护人名称")
	private String mntUserName;// 最后维护人名称

	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护时间")
	private Date mntDate;// 维护时间

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;// 描述

	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;// 排序

	@CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
	private Integer version;// 乐观锁版本

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	@CheckField(CheckName = CheckNameType.CODELIST)
	private List<String> codeList;
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public Long getLoadPointTypeId() {
		return loadPointTypeId;
	}

	public void setLoadPointTypeId(Long loadPointTypeId) {
		this.loadPointTypeId = loadPointTypeId;
	}

	public String getLoadPointTypeCode() {
		return loadPointTypeCode;
	}

	public void setLoadPointTypeCode(String loadPointTypeCode) {
		this.loadPointTypeCode = loadPointTypeCode;
	}

	public String getLoadPointTypeName() {
		return loadPointTypeName;
	}

	public void setLoadPointTypeName(String loadPointTypeName) {
		this.loadPointTypeName = loadPointTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
	}

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	public Date getMntDate() {
		return mntDate;
	}

	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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
