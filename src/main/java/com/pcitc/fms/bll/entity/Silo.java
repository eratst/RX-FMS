package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
public class Silo extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 节点id
	private Long nodeId;
	// 节点编码
	private String nodeCode;
	// 料仓业务类型
	private Long siloTypeId;
	// 料仓公称容积
	private Integer cubage;
	// 料仓高度
	private Integer siloHgt;
	// 料仓上限高度
	private Integer maxSiloHgt;
	// 料仓下限高度
	private Integer minSiloHgt;
	// 安全料仓量上限
	private Integer maxSiloStoarge;
	// 安全料仓量下限
	private Integer minSiloStoarge;
	// 创建人ID
	private String crtUserCode;
	// 创建人名称
	private String crtUserName;
	// 创建时间
	private Date crtDate;
	// 最后维护人ID
	private String mntUserCode;
	// 最后维护人名称
	private String mntUserName;
	// 维护日期
	private Date mntDate;
	// 描述
	private String des;
	// 区域ID
	private Long areaId;
	// 位置经度
	private String nodeLongitude;
	// 位置纬度
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	private Long nodeTypeId;
	// 状态
	private Integer inUse;
	// 位置海拔
	private String nodeAltitude;
	// 节点名称
	private String nodeName;
	// 节点简称
	private String nodeAlias;
	// 节点类型（用于显示）
	private String nodeTypeName;
	// 料仓业务类型（用于显示）
	private String siloTypeName;
	private String siloTypeCode;
	// 区域编码（用于查询）
	private String areaCode;
	private String areaName;
	private String areaAlias;
	private Integer sortNum;

	
	
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getSiloTypeId() {
		return siloTypeId;
	}

	public void setSiloTypeId(Long siloTypeId) {
		this.siloTypeId = siloTypeId;
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

	public String getSiloTypeCode() {
		return siloTypeCode;
	}

	public void setSiloTypeCode(String siloTypeCode) {
		this.siloTypeCode = siloTypeCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public String getSiloTypeName() {
		return siloTypeName;
	}

	public void setSiloTypeName(String siloTypeName) {
		this.siloTypeName = siloTypeName;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public Integer getCubage() {
		return cubage;
	}

	public void setCubage(Integer cubage) {
		this.cubage = cubage;
	}

	public Integer getSiloHgt() {
		return siloHgt;
	}

	public void setSiloHgt(Integer siloHgt) {
		this.siloHgt = siloHgt;
	}

	public Integer getMaxSiloHgt() {
		return maxSiloHgt;
	}

	public void setMaxSiloHgt(Integer maxSiloHgt) {
		this.maxSiloHgt = maxSiloHgt;
	}

	public Integer getMinSiloHgt() {
		return minSiloHgt;
	}

	public void setMinSiloHgt(Integer minSiloHgt) {
		this.minSiloHgt = minSiloHgt;
	}

	public Integer getMaxSiloStoarge() {
		return maxSiloStoarge;
	}

	public void setMaxSiloStoarge(Integer maxSiloStoarge) {
		this.maxSiloStoarge = maxSiloStoarge;
	}

	public Integer getMinSiloStoarge() {
		return minSiloStoarge;
	}

	public void setMinSiloStoarge(Integer minSiloStoarge) {
		this.minSiloStoarge = minSiloStoarge;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
