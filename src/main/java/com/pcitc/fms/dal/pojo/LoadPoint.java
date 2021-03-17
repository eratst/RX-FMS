package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_LOADPOINT")
public class LoadPoint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NODE_ID")
	@SpecialResource(name="loadPoint.nodeId")
	private Long nodeId;

	@Column(name = "NODE_CODE")
	@SpecialResource(name="node.nodeCode")
	private String nodeCode;
	
	@Transient
	private String nodeName;
	
	@Transient
	private String nodeAlias;

	@Column(name = "LOADPOINTTYPE_ID")
	@SpecialResource(name="loadPoint.loadPointTypeId")
	private Long loadPointTypeId;
	
	@Transient
	@SpecialResource(name="loadPointType.loadPointTypeCode")
	private String loadPointTypeCode;
	
	@Transient
	private String loadPointTypeName;

	@Column(name = "INOUTTYPE_ID")
	private Long inOutTypeId;

	@Column(name = "FORMULAR")
	private String formular;

	@Transient
	private String crtUserId;

	@Transient
	private String crtUserName;

	@Transient
	private Date crtDate;

	@Transient
	private String mntUserId;

	@Transient
	private String mntUserName;

	@Transient
	private Date mntDate;

	@Column(name = "SORT_NUM")
	@SpecialResource(name="loadPoint.sortNum")
	private Integer sortNum;

	@Column(name = "VERSION")
	private Integer version;

	@Transient
	private String areaCode;
	
	@Transient
	private String areaAlias;
	
	@Transient
	private String orgCode;
	
	@Transient
	private String orgAlias;

	@Transient
	private String nodeLongitude;
	
	@Transient
	private String nodeLatitude;
	
	@Transient
	private String nodeAltitude;

	@Transient
	private String nodeTypeCode;

	@Transient
	private String nodeTypeName;

	@Transient
	private Integer inUse;
	
	@Transient
	private String des;
	
	public LoadPoint() {
		super();
	}
	
	public LoadPoint(Long nodeId, String nodeCode, String nodeName, String nodeAlias, Long loadPointTypeId,
			String loadPointTypeCode, String loadPointTypeName, Long inOutTypeId, String formular, String crtUserId,
			String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer sortNum,
			Integer version, String areaCode, String areaAlias, String orgCode, String orgAlias, String nodeLongitude,
			String nodeLatitude, String nodeAltitude, String nodeTypeCode, String nodeTypeName, Integer inUse,
			String des) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.loadPointTypeId = loadPointTypeId;
		this.loadPointTypeCode = loadPointTypeCode;
		this.loadPointTypeName = loadPointTypeName;
		this.inOutTypeId = inOutTypeId;
		this.formular = formular;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.version = version;
		this.areaCode = areaCode;
		this.areaAlias = areaAlias;
		this.orgCode = orgCode;
		this.orgAlias = orgAlias;
		this.nodeLongitude = nodeLongitude;
		this.nodeLatitude = nodeLatitude;
		this.nodeAltitude = nodeAltitude;
		this.nodeTypeCode = nodeTypeCode;
		this.nodeTypeName = nodeTypeName;
		this.inUse = inUse;
		this.des = des;
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

	public Long getLoadPointTypeId() {
		return loadPointTypeId;
	}

	public void setLoadPointTypeId(Long loadPointTypeId) {
		this.loadPointTypeId = loadPointTypeId;
	}

	public String getLoadPointTypeCode() {
		return loadPointTypeCode;
	}

	public void setLoadPointTypeCode(String loadPointTypeCode) {
		this.loadPointTypeCode = loadPointTypeCode;
	}

	public String getLoadPointTypeName() {
		return loadPointTypeName;
	}

	public void setLoadPointTypeName(String loadPointTypeName) {
		this.loadPointTypeName = loadPointTypeName;
	}

	public Long getInOutTypeId() {
		return inOutTypeId;
	}

	public void setInOutTypeId(Long inOutTypeId) {
		this.inOutTypeId = inOutTypeId;
	}

	public String getFormular() {
		return formular;
	}

	public void setFormular(String formular) {
		this.formular = formular;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	

}
