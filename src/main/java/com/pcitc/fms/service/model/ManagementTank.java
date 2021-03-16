package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import cc.aicode.e2e.annotation.ExcelEntity;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class ManagementTank extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nodeCode;
	
	private String nodeName;
	
	private String nodeSname;
	
	private String tankTypeCode;
	
	private String tankTypeName;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaSname;
	
	private Double tankHgt;
	
	private Double tankTotlCuba;
	
	private Double fltPlatWgt;
	
	private Double fltPlatPerhgt;
	
	private Double fltTipLst;
	
	private Double maxTankHgt;
	
	private Double minTankHgt;
	
	private Double maxTankStorage;
	
	private Double minTankStorage;
	
	private Integer htPretTank;
	
	private String nodeLongitude;
	
	private String nodeLatitude;
	
	private String nodeAltitude;
	
	private Integer inUse;
	
	private Integer sortNum;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="orgCode")
	private List<String> orgCodeList;
	
	@ResourceMember(InTemplate = false ,InQueries="condition",OnlyQuery=true,Name="bizCode")
	private String bizCode;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$nodeCodeList")
	private List<String> nodeCodeList;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$tankAreaList")
	private List<String> tankAreaList;
	
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;
	
	public ManagementTank() {
		super();
	}

	public ManagementTank(List<String> orgCodeList, String bizCode, List<String> nodeCodeList, List<String> tankAreaList,Integer top,Integer skip,Integer inUse) {
		super();
		this.orgCodeList = orgCodeList;
		this.bizCode = bizCode;
		this.nodeCodeList = nodeCodeList;
		this.tankAreaList = tankAreaList;
		this.top = top;
		this.skip = skip;
		this.inUse = inUse;
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

	public String getNodeSname() {
		return nodeSname;
	}

	public void setNodeSname(String nodeSname) {
		this.nodeSname = nodeSname;
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

	public String getAreaSname() {
		return areaSname;
	}

	public void setAreaSname(String areaSname) {
		this.areaSname = areaSname;
	}

	public Double getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Double tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Double getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Double tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Double getFltPlatWgt() {
		return fltPlatWgt;
	}

	public void setFltPlatWgt(Double fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
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

	public Double getMaxTankStorage() {
		return maxTankStorage;
	}

	public void setMaxTankStorage(Double maxTankStorage) {
		this.maxTankStorage = maxTankStorage;
	}

	public Double getMinTankStorage() {
		return minTankStorage;
	}

	public void setMinTankStorage(Double minTankStorage) {
		this.minTankStorage = minTankStorage;
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

	public List<String> getOrgCodeList() {
		return orgCodeList;
	}

	public void setOrgCodeList(List<String> orgCodeList) {
		this.orgCodeList = orgCodeList;
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

	public List<String> getTankAreaList() {
		return tankAreaList;
	}

	public void setTankAreaList(List<String> tankAreaList) {
		this.tankAreaList = tankAreaList;
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
