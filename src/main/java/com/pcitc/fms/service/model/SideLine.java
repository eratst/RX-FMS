package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class SideLine extends BaseResRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 节点id
	@ResourceMember(OnlyQuery=true)
	 private Long nodeId;
	// 节点编码
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@BuildLink
	private String nodeCode;
	// 侧线进出类型ID
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = false, Name = "slineInoutTypeId")
	private Long slineInOutTypeId;
	// 侧线业务类型
	@ResourceMember(InTemplate = true, InQueries = "condition", OnlyQuery = true, Name = "sidelineTypeId")
	@CheckField(CheckName = CheckNameType.ID)
	private Long sidelineTypeId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String sidelineTypeCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String slineMtrlTypeCode;
	
	private String slineMtrlTypeName;
	// 侧线物料类型ID
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(OnlyQuery=true)
	private Long slineMtrlTypeId;

	// 界区量公式
	@CheckField(CheckName = CheckNameType.DES, StrLength = 4000)
	private String areaForm;
	// 侧线量公式
	@CheckField(CheckName = CheckNameType.DES, StrLength = 4000)
	private String formula;
	// 创建人ID
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 20)
	@ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	// 创建人名称
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	// 创建时间
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	// 最后维护人ID
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 20)
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	// 最后维护人名称
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	// 维护日期
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	// 描述
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;
	// 区域ID
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(OnlyQuery=true)
	private Long areaId;
	// 位置经度
	@CheckField(CheckName = CheckNameType.DES, StrLength = 20)
	private String nodeLongitude;
	// 位置纬度
	@CheckField(CheckName = CheckNameType.DES, StrLength = 20)
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(OnlyQuery=true)
	private Long nodeTypeId;
	// 状态
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;
	//
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery=true)
	private String orgCode;
	// 位置海拔
	@CheckField(CheckName = CheckNameType.DES, StrLength = 20)
	private String nodeAltitude;
	// 节点名称
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String nodeName;
	// 节点简称
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String nodeAlias;
	// 节点类型（用于显示）
	@ResourceMember(OnlyQuery=true)
	private String nodeTypeName;
	// 侧线业务类型（用于显示）
	private String sidelineTypeName;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String areaName;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String areaAlias;
	
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
	private String areaTypeCode;
	
	private Integer sortNum;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	// 区域编码（用于查询）
	@CheckField(CheckName = CheckNameType.CODE)
	private String areaCode;
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip = 0;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> nodeCodes;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
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

	public String getSidelineTypeCode() {
		return sidelineTypeCode;
	}

	public void setSidelineTypeCode(String sidelineTypeCode) {
		this.sidelineTypeCode = sidelineTypeCode;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getSlineMtrlTypeName() {
		return slineMtrlTypeName;
	}

	public void setSlineMtrlTypeName(String slineMtrlTypeName) {
		this.slineMtrlTypeName = slineMtrlTypeName;
	}

	public String getSlineMtrlTypeCode() {
		return slineMtrlTypeCode;
	}

	public void setSlineMtrlTypeCode(String slineMtrlTypeCode) {
		this.slineMtrlTypeCode = slineMtrlTypeCode;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
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
