package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class Dimension extends BaseResRep implements Serializable {
	/**
	 * 量钢
	 */
	private static final long serialVersionUID = 1L;
	// 量纲ID
	private Long dimensionId;
	// 量纲编码
	private String dimensionCode;
	// 量纲名称
	private String dimensionName;
	// 量纲简称名称
	private String dimensionAlias;
	// 状态
	private Integer inUse;
	// 创建人ID
	private String crtUserCode;
	// 创建人名称
	private String crtUserName;
	// 创建时间
	private Date crtDate;
	// 最后维护人ID
	private String mntUserCode;
	// 最后维护人名称
	private String mntUserName;
	// 维护日期
	private Date mntDate;
	// 描述
	private String des;

	private Integer sortNum;
	
	private String symbol;
	
	
	
	
	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
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

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public String getDimensionAlias() {
		return dimensionAlias;
	}

	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
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
}
