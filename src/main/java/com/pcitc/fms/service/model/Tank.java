package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import oracle.sql.DATE;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Tank extends BaseResRep implements Serializable {
	/**
	 * 罐
	 */
	private static final long serialVersionUID = 1L;
	
	private Long nodeId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@BuildLink
	private String nodeCode;
	
	@CheckField(CheckName = CheckNameType.NAME)
	@ResourceMember(InQueries = "condition", Name = "nodeName")
	private String nodeName;
	
	@CheckField(CheckName = CheckNameType.NAME)
	@ResourceMember(InQueries = "condition", Name = "nodeAlias")
	private String nodeAlias;
	
	private Long tankTypeId;
	
	private Long areaId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(InQueries = "condition",Name = "tankTypeCode")
	private String tankTypeCode;
	
	private String tankTypeName;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String areaTypeCode;
	
	private Double tankHgt;
	
	private Double tankTotlCuba;
	
	private Double fltPlatWgt;
	
	private Double fltPlatPerhgt;
	
	private Double fltTipLst;
	
	private Double maxTankHgt;
	
	private Double minTankHgt;
	
	private Double maxTankStoarge;
	
	private Double minTankStoarge;
	
	private Integer htPretTank;
	
	private String nodeLongitude;

	private String nodeLatitude;
	
	private String nodeAltitude;
	
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
	
	@ResourceMember(InQueries = "condition", Name = "inUse")
	@CheckField(CheckName = CheckNameType.ENABLED, StrLength = 36)
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	@ResourceMember(OnlyQuery=true)
	private Long nodeTypeId;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String orgCode;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> nodeCodes;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> areaCodes;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;

	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;
	
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip = 0;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Long tankTypeId) {
		this.tankTypeId = tankTypeId;
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

	public Long getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeAlias() {
		return nodeAlias;
	}

	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
	}

	public String getTankTypeCode() {
		return tankTypeCode;
	}

	public void setTankTypeCode(String tankTypeCode) {
		this.tankTypeCode = tankTypeCode;
	}

	public String getTankTypeName() {
		return tankTypeName;
	}

	public void setTankTypeName(String tankTypeName) {
		this.tankTypeName = tankTypeName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	
	public Double getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Double tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Double getFltPlatWgt() {
		return fltPlatWgt;
	}

	public void setFltPlatWgt(Double fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
	}

	public Double getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Double tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Double getFltPlatPerhgt() {
		return fltPlatPerhgt;
	}

	public void setFltPlatPerhgt(Double fltPlatPerhgt) {
		this.fltPlatPerhgt = fltPlatPerhgt;
	}

	public Double getFltTipLst() {
		return fltTipLst;
	}

	public void setFltTipLst(Double fltTipLst) {
		this.fltTipLst = fltTipLst;
	}

	public Double getMaxTankHgt() {
		return maxTankHgt;
	}

	public void setMaxTankHgt(Double maxTankHgt) {
		this.maxTankHgt = maxTankHgt;
	}

	public Double getMinTankHgt() {
		return minTankHgt;
	}

	public void setMinTankHgt(Double minTankHgt) {
		this.minTankHgt = minTankHgt;
	}

	public Double getMaxTankStoarge() {
		return maxTankStoarge;
	}

	public void setMaxTankStoarge(Double maxTankStoarge) {
		this.maxTankStoarge = maxTankStoarge;
	}

	public Double getMinTankStoarge() {
		return minTankStoarge;
	}

	public void setMinTankStoarge(Double minTankStoarge) {
		this.minTankStoarge = minTankStoarge;
	}

	public Integer getHtPretTank() {
		return htPretTank;
	}

	public void setHtPretTank(Integer htPretTank) {
		this.htPretTank = htPretTank;
	}

	public String getNodeLongitude() {
		return nodeLongitude;
	}

	public void setNodeLongitude(String nodeLongitude) {
		this.nodeLongitude = nodeLongitude;
	}

	public String getNodeLatitude() {
		return nodeLatitude;
	}

	public void setNodeLatitude(String nodeLatitude) {
		this.nodeLatitude = nodeLatitude;
	}

	public String getNodeAltitude() {
		return nodeAltitude;
	}

	public void setNodeAltitude(String nodeAltitude) {
		this.nodeAltitude = nodeAltitude;
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

	public List<String> getNodeCodes() {
		return nodeCodes;
	}

	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
	}

	public List<String> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<String> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}
	
	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
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

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	
}
