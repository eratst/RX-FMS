package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class IcStangasden extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@CheckField(CheckName=CheckNameType.ID)
	@ResourceMember(InTemplate = false)
	private Long stangasdenId;

	private Double liquidDen;

	private Double gasDen;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人编码")
	private String crtUserCode;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;

	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserCode;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;

	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;

	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;

	private Integer version;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public Long getStangasdenId() {
		return stangasdenId;
	}

	public void setStangasdenId(Long stangasdenId) {
		this.stangasdenId = stangasdenId;
	}

	public Double getLiquidDen() {
		return liquidDen;
	}

	public void setLiquidDen(Double liquidDen) {
		this.liquidDen = liquidDen;
	}

	public Double getGasDen() {
		return gasDen;
	}

	public void setGasDen(Double gasDen) {
		this.gasDen = gasDen;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
