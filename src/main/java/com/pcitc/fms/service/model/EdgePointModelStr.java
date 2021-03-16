package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class EdgePointModelStr extends BaseResRep implements Serializable{
    private static final long serialVersionUID = 1L;
 // 节点id
 	private Integer nodeId;
 	// 节点编码
 	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
 	private String nodeCode;
 	// 运输类型ID
 	@CheckField(CheckName = CheckNameType.IDTYPE)
 	private Integer transTypeId;
 	// 进出类型ID
 	@CheckField(CheckName = CheckNameType.ID)
 	private Integer inoutTypeId;
 	// 计量公式
 	@CheckField(CheckName = CheckNameType.DES ,StrLength=20000)
 	private String formula;
 	// 创建人ID
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=20)
 	private String crtUserId;
 	// 创建人名称
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
 	private String crtUserName;
 	// 创建时间
 	private Date crtDate;
 	// 最后维护人ID
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=20)
 	private String mntUserId;
 	// 最后维护人名称
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
 	private String mntUserName;
 	// 维护日期
 	private Date mntDate;
 	// 描述
 	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
 	private String des;
 // 区域ID
 	@CheckField(CheckName = CheckNameType.ID )
 	private Integer areaId;
 	// 位置经度
 	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
 	private String nodeLongitude;
 	// 位置纬度
 	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
 	private String nodeLatitude;
 	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
 	@CheckField(CheckName = CheckNameType.ID )
 	private Integer nodeTypeId;
 	// 状态
 	@CheckField(CheckName = CheckNameType.ENABLED)
 	private Integer dataStatus;
 	// 位置海拔
 	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
 	private String nodeAltitude;
 	// 节点名称
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
 	private String nodeName;
 	// 节点简称
 	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
 	private String nodeAlias;
 	public Integer getNodeId() {
 		return nodeId;
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

	public void setNodeId(Integer nodeId) {
 		this.nodeId = nodeId;
 	}

 	public String getNodeCode() {
 		return nodeCode;
 	}

 	public void setNodeCode(String nodeCode) {
 		this.nodeCode = nodeCode;
 	}

 	public Integer getTransTypeId() {
 		return transTypeId;
 	}

 	public void setTransTypeId(Integer transTypeId) {
 		this.transTypeId = transTypeId;
 	}

 	public Integer getInoutTypeId() {
 		return inoutTypeId;
 	}

 	public void setInoutTypeId(Integer inoutTypeId) {
 		this.inoutTypeId = inoutTypeId;
 	}

 	public String getFormula() {
 		return formula;
 	}

 	public void setFormula(String formula) {
 		this.formula = formula;
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
    
}
