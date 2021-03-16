package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name= "T_PM_NODETOPDTL")
public class NodeTopDTL implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@Id
	@Column(name = "NODETOPDTL_ID")
	private Long dataId;

	/**
	 * 数据编码
	 */
	@Column(name = "NODETOPDTL_CODE")
	private String dataCode;

	/**
	 * S_NODE_ID
	 */
	@Column(name = "SNODE_ID")
	private Long snodeId;

	/**
	 * T_NODE_ID
	 */
	@Column(name = "TNODE_ID")
	private Long tnodeId;

	@Transient
	private String snodeName;

	@Transient
	private String tnodeName;

	@Transient
	private String snodeCode;

	@Transient
	private String tnodeCode;

	/**
	 * 拓扑关系ID
	 */
	@Column(name = "NODETOPMAIN_ID")
	private Long topId;

	/**
	 * 创建人ID
	 */
	@Column(name = "CRTUSER_CODE")
	private String crtUserId;

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

	/**
	 * 维护人ID
	 */
	@Column(name = "MNTUSER_CODE")
	private String mntUserId;
	/**
	 * 维护人名称
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
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Transient
	private String topCode;

	@Transient
	private String topName;


	public String getTopCode() {
		return topCode;
	}

	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
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


	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getTopId() {
		return topId;
	}

	public void setTopId(Long topId) {
		this.topId = topId;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Long getSnodeId() {
		return snodeId;
	}

	public void setSnodeId(Long snodeId) {
		this.snodeId = snodeId;
	}

	public Long getTnodeId() {
		return tnodeId;
	}

	public void setTnodeId(Long tnodeId) {
		this.tnodeId = tnodeId;
	}

	public String getSnodeName() {
		return snodeName;
	}

	public void setSnodeName(String snodeName) {
		this.snodeName = snodeName;
	}

	public String getTnodeName() {
		return tnodeName;
	}

	public void setTnodeName(String tnodeName) {
		this.tnodeName = tnodeName;
	}

	public String getSnodeCode() {
		return snodeCode;
	}

	public void setSnodeCode(String snodeCode) {
		this.snodeCode = snodeCode;
	}

	public String getTnodeCode() {
		return tnodeCode;
	}

	public void setTnodeCode(String tnodeCode) {
		this.tnodeCode = tnodeCode;
	}

	public NodeTopDTL() {
		super();
	}

	public NodeTopDTL(Long dataId, String dataCode, Long snodeId, Long tnodeId, String snodeName, String tnodeName,
			String snodeCode, String tnodeCode, Long topId, String crtUserId, String crtUserName, Date crtDate,
			String mntUserId, String mntUserName, Date mntDate, String des, Integer sortNum, Integer version,
			Integer inUse, String topCode, String topName) {
		this.dataId = dataId;
		this.dataCode = dataCode;
		this.snodeId = snodeId;
		this.tnodeId = tnodeId;
		this.snodeName = snodeName;
		this.tnodeName = tnodeName;
		this.snodeCode = snodeCode;
		this.tnodeCode = tnodeCode;
		this.topId = topId;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.inUse = inUse;
		this.topCode = topCode;
		this.topName = topName;
	}



}
