package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class SamplePoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long nodeId;

	private String nodeCode;

	private String nodeName;

	private String nodeAlias;

	private Long areaId;

	private String areaCode;

	private String areaName;

	private String areaAlias;

	private Long samplepointTypeId;

	private String samplePointTypeCode;

	private String samplePointTypeName;

	private String nodeLongitude;

	private String nodeLatitude;

	private String nodeAltitude;

	private String crtUserCode;

	private String crtUserName;

	private Date crtDate;

	private String mntUserCode;

	private String mntUserName;

	private Date mntDate;

	private Integer inUse;

	private Integer sortNum;

	private String des;

	private Integer dataStatus;

	private Long nodeTypeId;

	private String nodeTypeName;


	
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getSamplepointTypeId() {
		return samplepointTypeId;
	}

	public void setSamplepointTypeId(Long samplepointTypeId) {
		this.samplepointTypeId = samplepointTypeId;
	}

	public Long getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
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


	public String getSamplePointTypeCode() {
		return samplePointTypeCode;
	}

	public void setSamplePointTypeCode(String samplePointTypeCode) {
		this.samplePointTypeCode = samplePointTypeCode;
	}

	public String getSamplePointTypeName() {
		return samplePointTypeName;
	}

	public void setSamplePointTypeName(String samplePointTypeName) {
		this.samplePointTypeName = samplePointTypeName;
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

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

}
