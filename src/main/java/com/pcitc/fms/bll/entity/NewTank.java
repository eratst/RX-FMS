package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

public class NewTank implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nodeId;
	
	private String nodeCode;
	
	private String nodeName;
	
	private String nodeAlias;
	
	private Integer areaId;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	
	private Integer areaTypeId;
	
	private String areaTypeCode;
	
	private String areaTypeName;
	
	private Integer tankAreaTypeId;
	
	private String tankAreaTypeCode;
	
	private String tankAreaTypeName;
	
	private Integer orgId;
	
	private String orgCode;
	
	private String orgName;
	
	private String orgAlias;
	
	private Integer orgTypeId;
	
	private String orgTypeCode;
	
	private String orgTypeName;
	
	private String nodeLongitude;
	
	private String nodeLatitude;
	
	private Integer nodeTypeId;
	
	private Integer dataStatus;
	
	private String nodeAltitude;
	
	private String crtUserId;

	private String crtUserName;

	private Date crtDate;

	private String mntUserId;

	private String mntUserName;

	private Date mntDate;

	private Integer tankTypeSortNum;
	
	private Integer nodeTypeSortNum;
	
	private Integer nodeSortNum;

	private String nodeTypeDes;
	
	private String tankTypeDes;
	
	private String nodeDes;

	private Integer version;
	
	private Integer tankTypeId;
	
	private Integer tankHgt;
	
	private Integer tankTotlCuba;
	
	private Integer fltPlatWgt;
	
	private Integer fltPlatPerhgt;
	
	private Integer fltTipLst;
	
	private Integer maxTankHgt;
	
	private Integer minTankHgt;
	
	private Integer maxTankStoarge;
	
	private Integer minTankStoarge;
	
	private Integer htPretTank;
	
	private String nodeTypeCode;
	
	private String nodeTypeName;
	
	private String tankTypeCode;
	
	@ResourceMember(Name="NewTank.TankType.TankTypeName")
	private String tankTypeName;
	
	
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

	public Integer getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public Integer getTankAreaTypeId() {
		return tankAreaTypeId;
	}

	public void setTankAreaTypeId(Integer tankAreaTypeId) {
		this.tankAreaTypeId = tankAreaTypeId;
	}

	public String getTankAreaTypeCode() {
		return tankAreaTypeCode;
	}

	public void setTankAreaTypeCode(String tankAreaTypeCode) {
		this.tankAreaTypeCode = tankAreaTypeCode;
	}

	public String getTankAreaTypeName() {
		return tankAreaTypeName;
	}

	public void setTankAreaTypeName(String tankAreaTypeName) {
		this.tankAreaTypeName = tankAreaTypeName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getNodeDes() {
		return nodeDes;
	}

	public void setNodeDes(String nodeDes) {
		this.nodeDes = nodeDes;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public Integer getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Integer nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getNodeAltitude() {
		return nodeAltitude;
	}

	public void setNodeAltitude(String nodeAltitude) {
		this.nodeAltitude = nodeAltitude;
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

	public Integer getTankTypeSortNum() {
		return tankTypeSortNum;
	}

	public void setTankTypeSortNum(Integer tankTypeSortNum) {
		this.tankTypeSortNum = tankTypeSortNum;
	}

	public Integer getNodeTypeSortNum() {
		return nodeTypeSortNum;
	}

	public void setNodeTypeSortNum(Integer nodeTypeSortNum) {
		this.nodeTypeSortNum = nodeTypeSortNum;
	}

	public Integer getNodeSortNum() {
		return nodeSortNum;
	}

	public void setNodeSortNum(Integer nodeSortNum) {
		this.nodeSortNum = nodeSortNum;
	}

	public String getNodeTypeDes() {
		return nodeTypeDes;
	}

	public void setNodeTypeDes(String nodeTypeDes) {
		this.nodeTypeDes = nodeTypeDes;
	}

	public String getTankTypeDes() {
		return tankTypeDes;
	}

	public void setTankTypeDes(String tankTypeDes) {
		this.tankTypeDes = tankTypeDes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
	}

	public Integer getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Integer tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Integer getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Integer tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Integer getFltPlatWgt() {
		return fltPlatWgt;
	}

	public void setFltPlatWgt(Integer fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
	}

	public Integer getFltPlatPerhgt() {
		return fltPlatPerhgt;
	}

	public void setFltPlatPerhgt(Integer fltPlatPerhgt) {
		this.fltPlatPerhgt = fltPlatPerhgt;
	}

	public Integer getFltTipLst() {
		return fltTipLst;
	}

	public void setFltTipLst(Integer fltTipLst) {
		this.fltTipLst = fltTipLst;
	}

	public Integer getMaxTankHgt() {
		return maxTankHgt;
	}

	public void setMaxTankHgt(Integer maxTankHgt) {
		this.maxTankHgt = maxTankHgt;
	}

	public Integer getMinTankHgt() {
		return minTankHgt;
	}

	public void setMinTankHgt(Integer minTankHgt) {
		this.minTankHgt = minTankHgt;
	}

	public Integer getMaxTankStoarge() {
		return maxTankStoarge;
	}

	public void setMaxTankStoarge(Integer maxTankStoarge) {
		this.maxTankStoarge = maxTankStoarge;
	}

	public Integer getMinTankStoarge() {
		return minTankStoarge;
	}

	public void setMinTankStoarge(Integer minTankStoarge) {
		this.minTankStoarge = minTankStoarge;
	}

	public Integer getHtPretTank() {
		return htPretTank;
	}

	public void setHtPretTank(Integer htPretTank) {
		this.htPretTank = htPretTank;
	}

	public String getNodeTypeCode() {
		return nodeTypeCode;
	}

	public void setNodeTypeCode(String nodeTypeCode) {
		this.nodeTypeCode = nodeTypeCode;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
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

}
