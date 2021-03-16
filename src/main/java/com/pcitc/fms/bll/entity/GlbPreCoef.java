package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class GlbPreCoef implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long glbPreCoefId;
	
	private Long nodeId;//罐ID
	
	private String nodeCode;//罐编码
	
	private String nodeName;//罐名称
	
	private Double presRevCofe;
	
	private Double presFlrLmt;
	
	private Double presUpLmt;
	
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	private String crtUserName;

	/**
	 * 创建时间
	 */
	private Date crtDate;


	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	private String mntUserName;

	/**
	 * 维护日期
	 */
	private Date mntDate;

	/**
	 * 描述
	 */
	private String des;
	
	/**
	 * 排序
	 */
	private Integer sortNum;
	
	/**
	 * 乐观锁版本
	 */
	private Integer version;
	
	/**
	 * 状态
	 */
	private Integer inUse;

	public Long getGlbPreCoefId() {
		return glbPreCoefId;
	}

	public void setGlbPreCoefId(Long glbPreCoefId) {
		this.glbPreCoefId = glbPreCoefId;
	}

	public Double getPresRevCofe() {
		return presRevCofe;
	}

	public void setPresRevCofe(Double presRevCofe) {
		this.presRevCofe = presRevCofe;
	}

	public Double getPresFlrLmt() {
		return presFlrLmt;
	}

	public void setPresFlrLmt(Double presFlrLmt) {
		this.presFlrLmt = presFlrLmt;
	}

	public Double getPresUpLmt() {
		return presUpLmt;
	}

	public void setPresUpLmt(Double presUpLmt) {
		this.presUpLmt = presUpLmt;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	

}
