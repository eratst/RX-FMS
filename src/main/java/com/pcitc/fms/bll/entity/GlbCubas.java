package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.pcitc.fms.common.annotation.SpecialResource;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class GlbCubas extends BaseResRep implements Serializable{
/**
 * 罐量指标—球罐罐容
 */
private static final long serialVersionUID = 1L;
	
	private Long dataId;
	
	private Long nodeId;
	
	private Double hgt;
	
	private Double cuba;
	
	private Integer presFlrLmt;
	
	private Integer presUpLmt;
	
	private String nodeCode;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	private String des;
	
	private Integer inUse;
	
	private Integer sortNum;
	
	
	
	
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Double getHgt() {
		return hgt;
	}

	public void setHgt(Double hgt) {
		this.hgt = hgt;
	}

	public Double getCuba() {
		return cuba;
	}

	public void setCuba(Double cuba) {
		this.cuba = cuba;
	}

	public Integer getPresFlrLmt() {
		return presFlrLmt;
	}

	public void setPresFlrLmt(Integer presFlrLmt) {
		this.presFlrLmt = presFlrLmt;
	}

	public Integer getPresUpLmt() {
		return presUpLmt;
	}

	public void setPresUpLmt(Integer presUpLmt) {
		this.presUpLmt = presUpLmt;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
}
