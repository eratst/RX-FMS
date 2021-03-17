package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_GLBPRECOEF")
public class GlbPreCoef implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GLBPRECOEF_ID")
	private Long glbPreCoefId;
	
	@Column(name = "NODE_ID")
	private Long nodeId;//罐ID
	
	@Transient
	private String nodeCode;//罐编码
	
	@Transient
	private String nodeName;//罐名称
	
	@Column(name = "PRES_REV_COFE")
	private Double presRevCofe;
	
	@Column(name = "PRES_FLR_LMT")
	private Double presFlrLmt;
	
	@Column(name = "PRES_UP_LMT")
	private Double presUpLmt;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@Column(name = "MNTDATE")
	private Date mntDate;

	/**
	 * 描述
	 */
	@Column(name = "DES")
	private String des;
	
	/**
	 * 排序
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	/**
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;
	
	/**
	 * 状态
	 */
	@Column(name = "INUSE")
	private Integer inUse;

	public GlbPreCoef() {
		super();
	}
	
	public GlbPreCoef(Long glbPreCoefId, Long nodeId, String nodeCode, String nodeName, Double presRevCofe,
			Double presFlrLmt, Double presUpLmt, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, String des, Integer sortNum, Integer version,
			Integer inUse) {
		this.glbPreCoefId = glbPreCoefId;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.presRevCofe = presRevCofe;
		this.presFlrLmt = presFlrLmt;
		this.presUpLmt = presUpLmt;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.inUse = inUse;
	}






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
