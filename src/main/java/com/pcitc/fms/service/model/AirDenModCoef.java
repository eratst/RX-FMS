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
public class AirDenModCoef extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	// 空气密度修正系数ID
	@ResourceMember(OnlyQuery=true)
	private Long denModCoefId;

	// 密度下限
	private Double denFlrLmt;

	// 密度上限
	private Double denUpLmt;

	// 浮力修正系数
	private Double flogModCoef;

	/**
	 * 创建人Code
	 */
	private String crtUserCode;

	/**
	 * 创建人姓名
	 */
	private String crtUserName;

	/**
	 * 创建时间
	 */
	private Date crtDate;

	/**
	 * 最后维护人Code
	 */
	private String mntUserCode;

	/**
	 * 最后维护人姓名
	 */
	private String mntUserName;

	/**
	 * 维护日期
	 */
	private Date mntDate;

	/**
	 * 描述
	 */
	private String des;
	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;

	@CheckField(CheckName=CheckNameType.ENABLED)
	private Integer inUse;
	
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer skip;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer top;
	
	@CheckField(CheckName=CheckNameType.ORDER)
	@ResourceMember(OnlyQuery=true,InTemplate=false)
	public String orderby;
	
	

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Long getDenModCoefId() {
		return denModCoefId;
	}

	public void setDenModCoefId(Long denModCoefId) {
		this.denModCoefId = denModCoefId;
	}

	public Double getDenFlrLmt() {
		return denFlrLmt;
	}

	public void setDenFlrLmt(Double denFlrLmt) {
		this.denFlrLmt = denFlrLmt;
	}

	public Double getDenUpLmt() {
		return denUpLmt;
	}

	public void setDenUpLmt(Double denUpLmt) {
		this.denUpLmt = denUpLmt;
	}

	public Double getFlogModCoef() {
		return flogModCoef;
	}

	public void setFlogModCoef(Double flogModCoef) {
		this.flogModCoef = flogModCoef;
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
	
	
	

}
