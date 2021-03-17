package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class EdgePoint extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 节点id
	@ResourceMember(OnlyQuery=true)
	private Long nodeId;
	// 节点编码
	@BuildLink
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String nodeCode;
	// 运输类型ID
	@CheckField(CheckName = CheckNameType.IDTYPE)
	@ResourceMember(InQueries = "condition", Name = "transTypeId",OnlyQuery=true)
	private Long transTypeId;
	//运输类型CODE
	@CheckField(CheckName = CheckNameType.CODE)
	private String transTypeCode;
	// 计量公式
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String formula;
	// 创建人ID
	@CheckField(CheckName = CheckNameType.ID ,StrLength=20)
	@ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	// 创建人名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	// 创建时间
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	// 最后维护人ID
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=20)
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	// 最后维护人名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	// 维护日期
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	// 描述
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	@ResourceMember(InQueries = "condition",Name = "des")
	private String des;
	// 区域ID
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(OnlyQuery=true)
	private Long areaId;
	// 位置经度
	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
	private String nodeLongitude;
	// 位置纬度
	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(OnlyQuery=true)
	private Long nodeTypeId;
	// 状态
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition")
	private Integer inUse;
	// 位置海拔
	@CheckField(CheckName = CheckNameType.DES ,StrLength=20)
	private String nodeAltitude;
	// 节点名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "nodeName")
	private String nodeName;
	// 节点简称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "nodeAlias")
	private String nodeAlias;
	// 节点类型（用于显示）
	@ResourceMember(OnlyQuery=true)
	private String nodeTypeName;
	// 罐类型（用于显示）
	private String transTypeName;
	// 区域编码（用于查询）
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String areaCode;
	
	@CheckField(CheckName = CheckNameType.ID ,StrLength=50)
	private Long inOutTypeId;
	
	private Integer sortNum;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "areaName")
	private String areaName;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "areaAlias")
	private String areaAlias;
	
	@ResourceMember(InQueries = "condition", Name = "areaTypeCode",OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String areaTypeCode;
	
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	@ResourceMember(OnlyQuery=true)
	private String orgCode;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO ,StrLength=50)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	private Integer top;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO ,StrLength=50)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCods")
	@CheckField(CheckName = CheckNameType.CODELIST ,StrLength=50)
	private List<String> nodeCodes;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST ,StrLength=50)
	private List<String> areaCodes;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
	
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Long transTypeId) {
		this.transTypeId = transTypeId;
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

	public Long getInOutTypeId() {
		return inOutTypeId;
	}

	public void setInOutTypeId(Long inOutTypeId) {
		this.inOutTypeId = inOutTypeId;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<String> getNodeCodes() {
		return nodeCodes;
	}

	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
	}

	public List<String> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<String> areaCodes) {
		this.areaCodes = areaCodes;
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

	public String getTransTypeName() {
		return transTypeName;
	}

	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
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

	public String getTransTypeCode() {
		return transTypeCode;
	}

	public void setTransTypeCode(String transTypeCode) {
		this.transTypeCode = transTypeCode;
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

}
