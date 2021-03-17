package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

import cc.aicode.e2e.annotation.ExcelEntity;

@ExcelEntity
public class Measurement implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idxId;
	
	private String idxCode;

	private String idxName;

	private String idxAlias;

	private Long idxTypeId;
	
	private String idxTypeCode;
	
	private String idxTypeName;

	private Long nodeId;
	
	private String nodeCode;
	
	private String nodeName;

	private String nodeAlias;
	
	private Long dimensionId;
	
	private String dimensionCode;
	
	private String dimensionName;
	
	private String dimensionAlias;

	private String idxFormula;

	private Integer inUse;

	private String crtUserCode;

	private String crtUserName;

	private Date crtDate;

	private String mntUserCode;

	private String mntUserName;

	private Date mntDate;
	
	private Integer sortNum;

	private String des;
	
	private String sourceDataType;
		
	private String nodeTypeName;
	
	private String exchangeRate;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	
	private String nodeTypeCode;
	
	
	
	
	public String getNodeTypeCode() {
		return nodeTypeCode;
	}

	public void setNodeTypeCode(String nodeTypeCode) {
		this.nodeTypeCode = nodeTypeCode;
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

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Long getIdxId() {
		return idxId;
	}

	public void setIdxId(Long idxId) {
		this.idxId = idxId;
	}

	public String getIdxCode() {
		return idxCode;
	}

	public void setIdxCode(String idxCode) {
		this.idxCode = idxCode;
	}

	public String getIdxName() {
		return idxName;
	}

	public void setIdxName(String idxName) {
		this.idxName = idxName;
	}

	public String getIdxAlias() {
		return idxAlias;
	}

	public void setIdxAlias(String idxAlias) {
		this.idxAlias = idxAlias;
	}

	public Long getIdxTypeId() {
		return idxTypeId;
	}

	public void setIdxTypeId(Long idxTypeId) {
		this.idxTypeId = idxTypeId;
	}

	public String getIdxTypeCode() {
		return idxTypeCode;
	}

	public void setIdxTypeCode(String idxTypeCode) {
		this.idxTypeCode = idxTypeCode;
	}

	public String getIdxTypeName() {
		return idxTypeName;
	}

	public void setIdxTypeName(String idxTypeName) {
		this.idxTypeName = idxTypeName;
	}

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

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public String getDimensionAlias() {
		return dimensionAlias;
	}

	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
	}

	public String getIdxFormula() {
		return idxFormula;
	}

	public void setIdxFormula(String idxFormula) {
		this.idxFormula = idxFormula;
	}


	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public String getSourceDataType() {
		return sourceDataType;
	}

	public void setSourceDataType(String sourceDataType) {
		this.sourceDataType = sourceDataType;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	
}
