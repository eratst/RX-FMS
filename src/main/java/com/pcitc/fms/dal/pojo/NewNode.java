package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

@Entity
@Table(name = "T_PM_NODE")
@Inheritance(strategy = InheritanceType.JOINED)
public class NewNode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NODE_ID")
	private Integer nodeId;
	// 节点编码
	@Column(name = "NODE_CODE")
	private String nodeCode;
	// 节点名称
	@Column(name = "NODE_NAME")
	private String nodeName;
	// 节点简称
	@Column(name = "NODE_ALIAS")
	private String nodeAlias;
	// 区域ID
	@Column(name = "AREA_ID")
	private Integer areaId;
	// 位置经度
	@Column(name = "NODE_LONGITUDE")
	private String nodeLongitude;
	// 位置纬度
	@Column(name = "NODE_LATITUDE")
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@Column(name = "NODE_TYPE_ID")
	private Integer nodeTypeId;
	// 状态
	@Column(name = "DATA_STATUS")
	private Integer dataStatus;
	// 位置海拔
	@Column(name = "NODE_ALTITUDE")
	private String nodeAltitude;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "DES")
	private String des;

	@Column(name = "VERSION")
	private Integer version;
	

	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NODE_TYPE_ID", insertable = false, updatable = false)
	private NodeType nodeType;
	
	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_ID", insertable = false, updatable = false)
	private NewArea newArea;

	public NewNode() {
		super();
	}

	public NewArea getNewArea() {
		return newArea;
	}


	public void setNewArea(NewArea newArea) {
		this.newArea = newArea;
	}


	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
