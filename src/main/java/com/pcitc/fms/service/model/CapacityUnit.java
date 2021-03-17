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
public class CapacityUnit extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate = false)
	private Long capacityUnitId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "加工能力单位编码")
	@ResourceMember(InQueries = "condition", Name = "capacityUnitCode")
	private String capacityUnitCode;

	@CheckField(CheckName = CheckNameType.UNITNAME, StrLength = 200, Explain = "加工能力单位名称")
	@ResourceMember(InQueries = "condition", Name = "capacityUnitName")
	private String capacityUnitName;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 50, Explain = "创建人编码")
	private String crtUserCode;// 创建人Id

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 200, Explain = "创建人名称")
	private String crtUserName;// 创建人名称

	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;// 创建时间

	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserCode;// 最后维护人Id

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

	@CheckField(CheckName = CheckNameType.CODELIST)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public Long getCapacityUnitId() {
		return capacityUnitId;
	}

	public void setCapacityUnitId(Long capacityUnitId) {
		this.capacityUnitId = capacityUnitId;
	}

	public String getCapacityUnitCode() {
		return capacityUnitCode;
	}

	public void setCapacityUnitCode(String capacityUnitCode) {
		this.capacityUnitCode = capacityUnitCode;
	}

	public String getCapacityUnitName() {
		return capacityUnitName;
	}

	public void setCapacityUnitName(String capacityUnitName) {
		this.capacityUnitName = capacityUnitName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
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

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
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
