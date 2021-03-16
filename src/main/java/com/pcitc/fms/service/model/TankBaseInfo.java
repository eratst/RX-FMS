package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", prompt = "查询", rel = "search")
public class TankBaseInfo extends BaseResRep implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nodeId;
	
	private String nodeCode;
	
	private String nodeName;
	
	private String nodeAlias;
	
	private Integer tankTypeId;
	
	private String tankTypeCode;
	
	private String tankTypeName;
	
	private Integer tankHgt;
	
	private Integer areaId;
	
	private String areaCode;
	
	private String areaName;
	
	// 罐安全高度
	private Integer maxTankHgt;
	// 罐底高度
	private Integer minTankHgt;
	
	// 安全罐量上限
	private Integer maxTankStoarge;
	// 安全罐量下限
	private Integer minTankStoarge;
	
	// 罐公称容积
	private Integer tankTotlCuba;
	
	private Date chkTime;
	
	private Integer netoilMass;
	
	private String mtrlId;
	
	private String mtrlCode;
	
	private String mtrlName;
	
	private String tankErrorMessage;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	

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

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public TankBaseInfo() {
		super();
	}

	public TankBaseInfo(List<String> codeList, List<Integer> idList, Integer top, Integer skip) {
		super();
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
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

	public Integer getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
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

	public float getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Integer tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public Integer getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Integer tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Date getChkTime() {
		return chkTime;
	}

	public void setChkTime(Date chkTime) {
		this.chkTime = chkTime;
	}

	public Integer getNetoilMass() {
		return netoilMass;
	}

	public void setNetoilMass(Integer netoilMass) {
		this.netoilMass = netoilMass;
	}

	public String getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(String mtrlId) {
		this.mtrlId = mtrlId;
	}

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}

	public String getTankErrorMessage() {
		return tankErrorMessage;
	}

	public void setTankErrorMessage(String tankErrorMessage) {
		this.tankErrorMessage = tankErrorMessage;
	}
	
	
}
