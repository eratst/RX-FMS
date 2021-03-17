package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_PM_TANK")
public class TankBaseInfo implements Serializable {

	/**
	 * 罐
	 */
	private static final long serialVersionUID = 1L;
	// 节点id
	@Id
	@Column(name = "NODE_ID")
	private Integer nodeId;
	// 节点编码
	@RegionMember
	@Column(name = "NODE_CODE")
	private String nodeCode;
	@Transient
	private String nodeName;
	@Transient
	private String nodeAlias;
	@Transient
	private Integer tankTypeId;
	@Transient
	private String tankTypeCode;
	@Transient
	private String tankTypeName;
	@Transient
	private Integer tankHgt;
	@Transient
	private Integer areaId;
	@Transient
	private String areaCode;
	@Transient
	private String areaName;
	
	// 罐安全高度
	@Column(name = "MAX_TANK_HGT")
	private Integer maxTankHgt;
	// 罐底高度
	@Column(name = "MIN_TANK_HGT")
	private Integer minTankHgt;
	
	// 安全罐量上限
	@Column(name = "MAX_TANK_STOARGE")
	private Integer maxTankStoarge;
	// 安全罐量下限
	@Column(name = "MIN_TANK_STOARGE")
	private Integer minTankStoarge;
	
	// 罐公称容积
	@Column(name = "TANK_TOTL_CUBA")
	private Integer tankTotlCuba;
	@Transient
	private Date chkTime;
	@Transient
	private Integer netoilMass;
	@Transient
	private String mtrlId;
	@Transient
	private String mtrlCode;
	@Transient
	private String mtrlName;
	@Transient
	private String tankErrorMessage;

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

	public TankBaseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TankBaseInfo(Integer nodeId, String nodeCode, String nodeName, String nodeAlias, Integer tankTypeId,
			String tankTypeCode, String tankTypeName, Integer tankHgt, Integer areaId, String areaCode, String areaName,
			Integer maxTankHgt, Integer minTankHgt, Integer maxTankStoarge, Integer minTankStoarge,
			Integer tankTotlCuba) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.tankTypeId = tankTypeId;
		this.tankTypeCode = tankTypeCode;
		this.tankTypeName = tankTypeName;
		this.tankHgt = tankHgt;
		this.areaId = areaId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.maxTankHgt = maxTankHgt;
		this.minTankHgt = minTankHgt;
		this.maxTankStoarge = maxTankStoarge;
		this.minTankStoarge = minTankStoarge;
		this.tankTotlCuba = tankTotlCuba;
	}
	
	
}
