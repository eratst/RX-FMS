package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class StdSec extends BaseResRep implements Serializable {
	/**
	 * 罐量指标—立罐区间
	 */
	private static final long serialVersionUID = 1L;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	@ResourceMember(OnlyQuery=true,InTemplate=true)
	private Long dataId;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	private Long nodeId;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	private Integer secId;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	private Double secFlrLmt;
	
	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	private Double secUpLmt;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries="search,condition",InTemplate=false)
	private String nodeCode;
	
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private List<String> nodeCodeList;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer skip;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer top;
	
	@CheckField(CheckName=CheckNameType.ORDER)
	@ResourceMember(OnlyQuery=true,InTemplate=false)
	public String orderby;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	
    @ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	
	private String des;
	
	private Integer inUse;
	
	private Integer sortNum;
	
	
	

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public List<String> getNodeCodeList() {
		return nodeCodeList;
	}

	public void setNodeCodeList(List<String> nodeCodeList) {
		this.nodeCodeList = nodeCodeList;
	}

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

	public Integer getSecId() {
		return secId;
	}

	public void setSecId(Integer secId) {
		this.secId = secId;
	}

	public Double getSecFlrLmt() {
		return secFlrLmt;
	}

	public void setSecFlrLmt(Double secFlrLmt) {
		this.secFlrLmt = secFlrLmt;
	}

	public Double getSecUpLmt() {
		return secUpLmt;
	}

	public void setSecUpLmt(Double secUpLmt) {
		this.secUpLmt = secUpLmt;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
}
