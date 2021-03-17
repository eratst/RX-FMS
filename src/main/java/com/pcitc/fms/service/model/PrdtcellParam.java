package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class PrdtcellParam extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 生产单元ID
	 */
	@ResourceMember(InTemplate = false)
	private Integer cellId;

	/**
	 * 组织机构简称
	 */
	@ResourceMember(InQueries = "condition", Name = "orgAlias")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 36, Explain = "组织机构简称")
	private String orgAlias;

	/**
	 * 区域简称
	 */
	@ResourceMember(InQueries = "condition", Name = "areaAlias")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 36, Explain = "区域简称")
	private String areaAlias;

	/**
	 * 区域ID
	 */
	@ResourceMember(InTemplate = false)
	private Integer areaId;

	/**
	 * 生产单元编码
	 */
	@ResourceMember(InQueries = "condition", Name = "cellCode")
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36, Explain = "编码")
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	@ResourceMember(InQueries = "condition", Name = "cellName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50, Explain = "名称")
	private String cellName;

	/**
	 * 生产单元简称
	 */
	@ResourceMember(InQueries = "condition", Name = "cellAbbrName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50, Explain = "简称")
	private String cellAbbrName;
	
	/**
	 * 是否启用
	 */
	@ResourceMember(InQueries = "condition", Name = "inUse")
	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	private Integer inUse;
	
	/**
	 * 创建人ID
	 */
	private String crtUserId;

	/**
	 * 创建人名称
	 */
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人名称")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	private String mntUserId;

	/**
	 * 最后维护人名称
	 */
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人名称")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10, Explain = "排序")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;
	
	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, Explain = "描述")
	private String des;

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCellCode() {
		return cellCode;
	}

	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellAbbrName() {
		return cellAbbrName;
	}

	public void setCellAbbrName(String cellAbbrName) {
		this.cellAbbrName = cellAbbrName;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	

}
