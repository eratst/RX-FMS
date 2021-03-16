package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Outlet extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;
	// 节点id
//	private Integer nodeId;
	// 节点编码
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	@ResourceMember(InQueries = "condition")
	@BuildLink
	private String nodeCode;
	// 节点名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition",Name = "nodeName")
	private String nodeName;
	// 节点简称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition",Name = "nodeAlias")
	private String nodeAlias;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	// 区域编码（用于查询）
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	// 标识牌类型（用于显示）
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	@ResourceMember(InQueries = "condition")
	private String signboardTypeCode;
	// 标识牌类型（用于显示）
	private String signboardTypeName;
	// 位置经度
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=20)
	private String nodeLongitude;
	// 位置纬度
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=20)
	private String nodeLatitude;
	// 位置海拔
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=20)
	private String nodeAltitude;
	// 状态
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition",Name = "inUse")
	private Integer inUse;
	private Integer sortNum;
	// 描述
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> nodeCodes;
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> areaCodes;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;// 返回的数据条数

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip;// 跳过的数据条数

	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String orgCode;
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String areaTypeCode;
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
	@ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	
	
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
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
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
	public String getSignboardTypeCode() {
		return signboardTypeCode;
	}
	public void setSignboardTypeCode(String signboardTypeCode) {
		this.signboardTypeCode = signboardTypeCode;
	}
	public String getSignboardTypeName() {
		return signboardTypeName;
	}
	public void setSignboardTypeName(String signboardTypeName) {
		this.signboardTypeName = signboardTypeName;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
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
