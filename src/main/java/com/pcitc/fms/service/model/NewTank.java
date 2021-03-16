package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.SpecialResource;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class NewTank extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	@ResourceMember(InTemplate = false)
	private Integer nodeId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	private String nodeCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String nodeName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String nodeAlias;

	@CheckField(CheckName = CheckNameType.ID, StrLength = 20)
	private Integer areaId;

	private String areaCode;

	private String areaName;

	private String areaAlias;

	private Integer areaTypeId;

	private String areaTypeCode;

	private String areaTypeName;

	private Integer tankAreaTypeId;

	private String tankAreaTypeCode;

	private String tankAreaTypeName;

	@SpecialResource(name = "newArea.newOrg.orgId")
	private Integer orgId;

	private String orgCode;

	private String orgName;

	private String orgAlias;

	private Integer orgTypeId;

	private String orgTypeCode;

	private String orgTypeName;

	private String nodeLongitude;

	private String nodeLatitude;

	private Integer nodeTypeId;

	@CheckField(CheckName = CheckNameType.ENABLED, StrLength = 50)
	private Integer dataStatus;

	private String nodeAltitude;

	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 36)
	private String crtUserId;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	private String crtUserName;

	private Date crtDate;

	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 36)
	private String mntUserId;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	private String mntUserName;

	private Date mntDate;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer tankTypeSortNum;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer nodeTypeSortNum;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer nodeSortNum;

	@ResourceMember(InTemplate = false)
	private String nodeTypeDes;

	@ResourceMember(InTemplate = false)
	private String tankTypeDes;

	private String nodeDes;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer version;

	@CheckField(CheckName = CheckNameType.ID, StrLength = 36)
	private Integer tankTypeId;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer tankHgt;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer tankTotlCuba;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer fltPlatWgt;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer fltPlatPerhgt;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer fltTipLst;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer maxTankHgt;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer minTankHgt;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer maxTankStoarge;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer minTankStoarge;

	@CheckField(CheckName = CheckNameType.IDMAYBENULL, StrLength = 20)
	private Integer htPretTank;

	@ResourceMember(InTemplate = false)
	private String nodeTypeCode;

	@ResourceMember(InTemplate = false)
	private String nodeTypeName;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", InTemplate = false)
	@SpecialResource(name = "tankType.tankTypeCode")
	private String tankTypeCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 36)
	@ResourceMember(InQueries = "condition", InTemplate = false)
	@SpecialResource(name = "tankType.tankTypeName")
	private String tankTypeName;

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(OnlyQuery = true, InQueries = "search,condition", InTemplate = false)
	private List<String> nodeCodes;
	
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(OnlyQuery = true, InQueries = "search,condition", InTemplate = false)
	@SpecialResource(name = "newArea.newOrg.orgCode")
	private List<String> orgCodes;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery = true, InQueries = "search,condition", InTemplate = false)
	private Integer skip;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery = true, InQueries = "search,condition", InTemplate = false)
	private Integer top;

	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(OnlyQuery = true, InTemplate = false)
	public Sort orderby;
	

	public List<String> getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(List<String> orgCodes) {
		this.orgCodes = orgCodes;
	}

	public Sort getOrderby() {
		return orderby;
	}

	public void setOrderby(Sort orderby) {
		this.orderby = orderby;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public List<String> getNodeCodes() {
		return nodeCodes;
	}

	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
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

	public Integer getTankTypeSortNum() {
		return tankTypeSortNum;
	}

	public void setTankTypeSortNum(Integer tankTypeSortNum) {
		this.tankTypeSortNum = tankTypeSortNum;
	}

	public Integer getNodeTypeSortNum() {
		return nodeTypeSortNum;
	}

	public void setNodeTypeSortNum(Integer nodeTypeSortNum) {
		this.nodeTypeSortNum = nodeTypeSortNum;
	}

	public Integer getNodeSortNum() {
		return nodeSortNum;
	}

	public void setNodeSortNum(Integer nodeSortNum) {
		this.nodeSortNum = nodeSortNum;
	}

	public String getNodeTypeDes() {
		return nodeTypeDes;
	}

	public void setNodeTypeDes(String nodeTypeDes) {
		this.nodeTypeDes = nodeTypeDes;
	}

	public String getTankTypeDes() {
		return tankTypeDes;
	}

	public void setTankTypeDes(String tankTypeDes) {
		this.tankTypeDes = tankTypeDes;
	}

	public String getNodeDes() {
		return nodeDes;
	}

	public void setNodeDes(String nodeDes) {
		this.nodeDes = nodeDes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
	}

	public Integer getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Integer tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Integer getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Integer tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Integer getFltPlatWgt() {
		return fltPlatWgt;
	}

	public void setFltPlatWgt(Integer fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
	}

	public Integer getFltPlatPerhgt() {
		return fltPlatPerhgt;
	}

	public void setFltPlatPerhgt(Integer fltPlatPerhgt) {
		this.fltPlatPerhgt = fltPlatPerhgt;
	}

	public Integer getFltTipLst() {
		return fltTipLst;
	}

	public void setFltTipLst(Integer fltTipLst) {
		this.fltTipLst = fltTipLst;
	}

	public Integer getMaxTankHgt() {
		return maxTankHgt;
	}

	public void setMaxTankHgt(Integer maxTankHgt) {
		this.maxTankHgt = maxTankHgt;
	}

	public Integer getMinTankHgt() {
		return minTankHgt;
	}

	public void setMinTankHgt(Integer minTankHgt) {
		this.minTankHgt = minTankHgt;
	}

	public Integer getMaxTankStoarge() {
		return maxTankStoarge;
	}

	public void setMaxTankStoarge(Integer maxTankStoarge) {
		this.maxTankStoarge = maxTankStoarge;
	}

	public Integer getMinTankStoarge() {
		return minTankStoarge;
	}

	public void setMinTankStoarge(Integer minTankStoarge) {
		this.minTankStoarge = minTankStoarge;
	}

	public Integer getHtPretTank() {
		return htPretTank;
	}

	public void setHtPretTank(Integer htPretTank) {
		this.htPretTank = htPretTank;
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

	public String getTankTypeCode() {
		return tankTypeCode;
	}

	public void setTankTypeCode(String tankTypeCode) {
		this.tankTypeCode = tankTypeCode;
	}

	public String getTankTypeName() {
		return tankTypeName;
	}

	public void setTankTypeName(String tankTypeName) {
		this.tankTypeName = tankTypeName;
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

	public Integer getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public Integer getTankAreaTypeId() {
		return tankAreaTypeId;
	}

	public void setTankAreaTypeId(Integer tankAreaTypeId) {
		this.tankAreaTypeId = tankAreaTypeId;
	}

	public String getTankAreaTypeCode() {
		return tankAreaTypeCode;
	}

	public void setTankAreaTypeCode(String tankAreaTypeCode) {
		this.tankAreaTypeCode = tankAreaTypeCode;
	}

	public String getTankAreaTypeName() {
		return tankAreaTypeName;
	}

	public void setTankAreaTypeName(String tankAreaTypeName) {
		this.tankAreaTypeName = tankAreaTypeName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

}
