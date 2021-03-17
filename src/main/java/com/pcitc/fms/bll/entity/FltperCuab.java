package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class FltperCuab implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long dataId;
	
	private Long nodeId;
	
	private Double fltPerHgt;
	
	private Double fltPerCuba;
	
	private Integer dispSeqNbr;
	
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

	public Double getFltPerHgt() {
		return fltPerHgt;
	}

	public void setFltPerHgt(Double fltPerHgt) {
		this.fltPerHgt = fltPerHgt;
	}

	public Double getFltPerCuba() {
		return fltPerCuba;
	}

	public void setFltPerCuba(Double fltPerCuba) {
		this.fltPerCuba = fltPerCuba;
	}

	public Integer getDispSeqNbr() {
		return dispSeqNbr;
	}

	public void setDispSeqNbr(Integer dispSeqNbr) {
		this.dispSeqNbr = dispSeqNbr;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
}
