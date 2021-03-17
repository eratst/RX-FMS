package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_NODE")
public class NodeDictionary implements Serializable {
	private static final long serialVersionUID = 1L;
	// 节点id
	@Id
	@Column(name = "NODE_ID")
	@SpecialResource(name="a.nodeId")
	private Long nodeId;
	// 节点编码
	@RegionMember
	@Column(name = "NODE_CODE")
	@SpecialResource(name="a.nodeCode")
	private String nodeCode;
	// 节点名称
	@Column(name = "NODE_NAME")
	@SpecialResource(name="a.nodeName")
	private String nodeName;
	// 节点简称
	@Column(name = "NODE_ALIAS")
	@SpecialResource(name="a.nodeAlias")
	private String nodeAlias;
	// 区域ID
	@Column(name = "AREA_ID")
	@SpecialResource(name="a.areaId")
	private Long areaId;
	// 位置经度
	@Column(name = "NODE_LONGITUDE")
	@SpecialResource(name="a.nodeLongitude")
	private String nodeLongitude;
	// 位置纬度
	@Column(name = "NODE_LATITUDE")
	@SpecialResource(name="a.nodeLatitude")
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@Column(name = "NODETYPE_ID")
	@SpecialResource(name="a.nodeTypeId")
	private Long nodeTypeId;
	// 状态
	@Column(name = "INUSE")
	@SpecialResource(name="a.dataStatus")
	private Integer dataStatus;
	// 位置海拔
	@Column(name = "NODE_ALTITUDE")
	@SpecialResource(name="a.nodeAltitude")
	private String nodeAltitude;
	// 描述
	@Column(name = "DES")
	@SpecialResource(name="a.des")
	private String des;
	
	@Column(name = "SORT_NUM")
	@SpecialResource(name="a.sortNum")
	private Integer sortNum;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	
	@Transient
	@SpecialResource(name="b.nodeTypeCode")
	private String nodeTypeCode;
	@Transient
	@SpecialResource(name="b.nodeTypeName")
	private String nodeTypeName;
	@Transient
	@SpecialResource(name="ad.areaCode")
	private String areaCode;
	@Transient
	@SpecialResource(name="ad.name")
	private String areaName;
	@Transient
	@SpecialResource(name="ad.shortName")
	private String areaAlias;

	public NodeDictionary() {
	}
	public NodeDictionary(Long nodeId, String nodeCode, String nodeName,
			String nodeAlias, Long areaId, String nodeLongitude,
			String nodeLatitude, Long nodeTypeId, Integer dataStatus,
			String nodeAltitude, String des,Integer sortNum) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.areaId = areaId;
		this.nodeLongitude = nodeLongitude;
		this.nodeLatitude = nodeLatitude;
		this.nodeTypeId = nodeTypeId;
		this.dataStatus = dataStatus;
		this.nodeAltitude = nodeAltitude;
		this.des = des;
		this.sortNum = sortNum;
	}
	public NodeDictionary(Long nodeId, String nodeCode, String nodeName,
			String nodeAlias, Long areaId, String nodeLongitude,
			String nodeLatitude, Long nodeTypeId, Integer dataStatus,
			String nodeAltitude, String des,String nodeTypeCode,String nodeTypeName,
			String areaCode,String areaName,String areaAlias,Integer sortNum,String crtUserCode,
			String crtUserName,Date crtDate,String mntUserCode,String mntUserName,Date mntDate) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.areaId = areaId;
		this.nodeLongitude = nodeLongitude;
		this.nodeLatitude = nodeLatitude;
		this.nodeTypeId = nodeTypeId;
		this.dataStatus = dataStatus;
		this.nodeAltitude = nodeAltitude;
		this.des = des;
		this.nodeTypeCode=nodeTypeCode;
		this.nodeTypeName=nodeTypeName;
		this.areaCode=areaCode;
		this.areaName=areaName;
		this.areaAlias=areaAlias;
		this.sortNum = sortNum;
		this.crtUserCode=crtUserCode;
		this.crtUserName=crtUserName;
		this.crtDate=crtDate;
		this.mntUserCode=mntUserCode;
		this.mntUserName=mntUserName;
		this.mntDate=mntDate;
	}
	
	
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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


	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}


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
	public Long getNodeTypeId() {
		return nodeTypeId;
	}
	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
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
	public String getNodeAltitude() {
		return nodeAltitude;
	}

	public void setNodeAltitude(String nodeAltitude) {
		this.nodeAltitude = nodeAltitude;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
