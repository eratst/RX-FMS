package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;

@ExcelEntity
public class SideLine implements Serializable {

	private static final long serialVersionUID = 1L;

	// 节点id
	private Long nodeId;
	// 节点编码
	private String nodeCode;
	// 侧线进出类型ID
	private Long slineInOutTypeId;
	// 侧线业务类型
	private Long sidelineTypeId;
	// 侧线物料类型ID
	private Long slineMtrlTypeId;
	// 界区量公式
	private String areaForm;
	// 侧线量公式
	private String formula;
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
	// 侧线业务类型（用于显示）
	private String sidelineTypeName;
	// 区域编码（用于查询）
	private String areaCode;
	
	private String orgCode;
	
	private String areaName;
	
	private String areaAlias;
	
	private Integer sortNum;
	
	private String sidelineTypeCode;
	
	private String slineMtrlTypeCode;
	
	private String slineMtrlTypeName;
	
	
	
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getSlineInOutTypeId() {
		return slineInOutTypeId;
	}

	public void setSlineInOutTypeId(Long slineInOutTypeId) {
		this.slineInOutTypeId = slineInOutTypeId;
	}

	public Long getSidelineTypeId() {
		return sidelineTypeId;
	}

	public void setSidelineTypeId(Long sidelineTypeId) {
		this.sidelineTypeId = sidelineTypeId;
	}

	public Long getSlineMtrlTypeId() {
		return slineMtrlTypeId;
	}

	public void setSlineMtrlTypeId(Long slineMtrlTypeId) {
		this.slineMtrlTypeId = slineMtrlTypeId;
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

	public String getSlineMtrlTypeName() {
		return slineMtrlTypeName;
	}

	public void setSlineMtrlTypeName(String slineMtrlTypeName) {
		this.slineMtrlTypeName = slineMtrlTypeName;
	}

	public String getSidelineTypeCode() {
		return sidelineTypeCode;
	}

	public void setSidelineTypeCode(String sidelineTypeCode) {
		this.sidelineTypeCode = sidelineTypeCode;
	}

	public String getSlineMtrlTypeCode() {
		return slineMtrlTypeCode;
	}

	public void setSlineMtrlTypeCode(String slineMtrlTypeCode) {
		this.slineMtrlTypeCode = slineMtrlTypeCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getSidelineTypeName() {
		return sidelineTypeName;
	}

	public void setSidelineTypeName(String sidelineTypeName) {
		this.sidelineTypeName = sidelineTypeName;
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

	public String getAreaForm() {
		return areaForm;
	}

	public void setAreaForm(String areaForm) {
		this.areaForm = areaForm;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
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
}
