package com.pcitc.fms.bll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class MapSampleNode extends BaseResRep implements Serializable{
	private static final long serialVersionUID = 1L;
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;
	
	private String nodeCode;
	
	private String nodeName;
	
	private String nodeAlias;
	
	private String SampleCode;
	
	private String SampleName;
	
	private String SampleAlias;
	
	private String samplePointTypeCode;
	
	private String samplePointTypeName;
	
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	
	public String getSamplePointTypeCode() {
		return samplePointTypeCode;
	}

	public void setSamplePointTypeCode(String samplePointTypeCode) {
		this.samplePointTypeCode = samplePointTypeCode;
	}

	public String getSamplePointTypeName() {
		return samplePointTypeName;
	}

	public void setSamplePointTypeName(String samplePointTypeName) {
		this.samplePointTypeName = samplePointTypeName;
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

	public String getSampleCode() {
		return SampleCode;
	}

	public void setSampleCode(String sampleCode) {
		SampleCode = sampleCode;
	}

	public String getSampleName() {
		return SampleName;
	}

	public void setSampleName(String sampleName) {
		SampleName = sampleName;
	}

	public String getSampleAlias() {
		return SampleAlias;
	}

	public void setSampleAlias(String sampleAlias) {
		SampleAlias = sampleAlias;
	}
	
	
}
