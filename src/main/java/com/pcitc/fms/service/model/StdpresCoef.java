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
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class StdpresCoef extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ResourceMember(OnlyQuery=true,InTemplate=false)
	private Long dataId;
	
	private Long nodeId;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
	@ResourceMember(InQueries="condition")
	private String nodeCode;
	
	private Integer intPl;
	
	private Integer fstDecFra;
	
	private String presRevCofe;
	
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(OnlyQuery=true,InQueries="search",InTemplate=false)
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

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public Integer getIntPl() {
		return intPl;
	}

	public void setIntPl(Integer intPl) {
		this.intPl = intPl;
	}

	public Integer getFstDecFra() {
		return fstDecFra;
	}

	public void setFstDecFra(Integer fstDecFra) {
		this.fstDecFra = fstDecFra;
	}


	public String getPresRevCofe() {
		return presRevCofe;
	}

	public void setPresRevCofe(String presRevCofe) {
		this.presRevCofe = presRevCofe;
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

	
}
