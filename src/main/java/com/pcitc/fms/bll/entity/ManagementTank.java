package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class ManagementTank implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer nodeId;
	
	private String nodeCode;
	
	private String nodeName;
	
	private String nodeSname;
	
	private Integer tankTypeId;
	
	private String tankTypeCode;
	
	private String tankTypeName;
	
	private Double tankHgt;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaSname;
	
	private Double maxTankHgt;
	
	private Double minTankHgt;
	
	private Double maxTankStorage;
	
	private Double minTankStorage;
	
	private Double tankTotlCuba;
	
	private Integer sortNum;
	
	private Double fltPlatWgt;
	
	private Double fltPlatPerhgt;
	
	private Double fltTipLst;
	
	private Integer htPretTank;
	
	private String nodeLongitude;
	
	private String nodeLatitude;
	
	private String nodeAltitude;
	
	private Integer inUse;

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

	public String getNodeSname() {
		return nodeSname;
	}

	public void setNodeSname(String nodeSname) {
		this.nodeSname = nodeSname;
	}

	public Integer getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
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

	public Double getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Double tankHgt) {
		this.tankHgt = tankHgt;
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

	public Double getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Double tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

}
