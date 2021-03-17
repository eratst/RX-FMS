package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class TubulationModelStr extends BaseResRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 节点id
	private Integer nodeId;
	// 节点编码
	private String nodeCode;
	// 创建人ID
	private String crtUserId;
	// 创建人名称
	private String crtUserName;
	// 创建时间
	private Date crtDate;
	// 最后维护人ID
	private String mntUserId;
	// 最后维护人名称
	private String mntUserName;
	// 维护日期
	private Date mntDate;
	// 描述
	private String des;
	// 区域ID
	private Integer areaId;
	// 位置经度
	private String nodeLongitude;
	// 位置纬度
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	private Integer nodeTypeId;
	// 状态
	private Integer dataStatus;
	// 位置海拔
	private String nodeAltitude;
	// 节点名称
	private String nodeName;
	// 节点简称
	private String nodeAlias;
	// 节点类型（用于显示）
	private String nodeTypeName;

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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

}
