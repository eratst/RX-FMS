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
public class Deltcnfg extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long deltcnfgId;
	
	private String deltcnfgVal;
	
	private String deltcnfgName;
	
	private String deltcnfgType;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	private Long version;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(OnlyQuery = true)
	private String orderby;
	
	

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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Long getDeltcnfgId() {
		return deltcnfgId;
	}

	public void setDeltcnfgId(Long deltcnfgId) {
		this.deltcnfgId = deltcnfgId;
	}

	public String getDeltcnfgVal() {
		return deltcnfgVal;
	}

	public void setDeltcnfgVal(String deltcnfgVal) {
		this.deltcnfgVal = deltcnfgVal;
	}

	public String getDeltcnfgName() {
		return deltcnfgName;
	}

	public void setDeltcnfgName(String deltcnfgName) {
		this.deltcnfgName = deltcnfgName;
	}

	public String getDeltcnfgType() {
		return deltcnfgType;
	}

	public void setDeltcnfgType(String deltcnfgType) {
		this.deltcnfgType = deltcnfgType;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	
	

}
