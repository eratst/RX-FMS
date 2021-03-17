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
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class MtrlMolar extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(OnlyQuery = true)
	private Long mtrlMolarId;
	
	private Long mtrlId;
	
	@ResourceMember(InQueries = "condition")
	@CheckField(CheckName = CheckNameType.CODE,Explain="状态")
	private String mtrlCode;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80)
	private String mtrlName;
	
	private Double density;
	
	private Double weight;
	

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人编码")
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;


	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;
	
	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;
	
	/**
	 * 乐观锁版本
	 */
	private Integer version;
	
	/**
	 * 状态
	 */
	@CheckField(CheckName = CheckNameType.ENABLED,Explain="状态")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public Long getMtrlMolarId() {
		return mtrlMolarId;
	}

	public void setMtrlMolarId(Long mtrlMolarId) {
		this.mtrlMolarId = mtrlMolarId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}

	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
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
