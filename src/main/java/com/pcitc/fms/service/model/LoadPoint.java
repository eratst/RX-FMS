package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class LoadPoint extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ResourceMember(InTemplate = false)
	private Long nodeId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装卸点编码")
	private String nodeCode;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装卸点名称")
	private String nodeName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装卸点简称")
	private String nodeAlias;
	
	@ResourceMember(InTemplate = false)
	private Long loadPointTypeId;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装卸点类型编码")
	private String loadPointTypeCode;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装卸点类型名称")
	private String loadPointTypeName;

	@CheckField(CheckName = CheckNameType.ID, Explain = "进出类型")
	private Long inOutTypeId;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 4000, Explain = "计量公式")
	private String formular;

	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 50, Explain = "创建人ID")
	private String crtUserId;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;

	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 50, Explain = "最后维护人ID")
	private String mntUserId;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;

	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;

	private Integer version;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 80, Explain = "组织机构编码")
	private String orgCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "组织机构简称")
	@ResourceMember(InQueries = "condition", Name = "orgAlias")
	private String orgAlias;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "所属装卸台简称")
	@ResourceMember(InQueries = "condition", Name = "areaAlias")
	private String areaAlias;

	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20, Explain = "位置经度")
	private String nodeLongitude;

	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20, Explain = "位置纬度")
	private String nodeLatitude;
	
	private String nodeAltitude;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;
	
	@CheckField(CheckName = CheckNameType.CODELIST)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@ResourceMember(OnlyQuery = true )
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true )
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> areaCodes;

	@CheckField(CheckName = CheckNameType.CODE)
	private String areaCode;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
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

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
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

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
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

}
