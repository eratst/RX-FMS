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
public class Rent extends BaseResRep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(OnlyQuery = true)
	private Long rentId;
	
	@CheckField(CheckName = CheckNameType.RENTCODE, StrLength = 50)
	private String rentName;
	
	@CheckField(CheckName = CheckNameType.RENTCODE, StrLength = 35)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.ENABLED, StrLength = 50)
	private Integer enable;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 50)
	private Integer orderId;
	
	private String des;
	
	@ResourceMember(OnlyQuery = true)
	private Integer crtUserCode;
	
	@ResourceMember(OnlyQuery = true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery = true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery = true)
	private Integer mntUserCode;
	
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery = true)
	private Date mntDate;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@ResourceMember(OnlyQuery = true)
	private Long orgId;
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getRentName() {
		return rentName;
	}

	public void setRentName(String rentName) {
		this.rentName = rentName;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public Integer getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(Integer crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public Integer getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(Integer mntUserCode) {
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
