package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [组态关系表]4.1 拓扑关系主表
 *
 */
@Entity
@Table(name="T_PM_NODETOPMAIN")
public class NodeTopMain  implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * 拓扑关系ID
	 */
	@Id
	@Column(name="NODETOPMAIN_ID")
	private Long topId;
	
	/**
	 * 拓扑关系编码
	 */
	@Column(name="NODETOPMAIN_CODE")
	private String topCode;
	
	/**
	 * 拓扑关系名称
	 */
	@Column(name="NODETOPMAIN_NAME")
	private String topName;
	
	/**
	 * 拓扑关系简称
	 */
	@Column(name="NODETOPMAIN_ALIAS")
	private String topAlias;
	
	/**
	 * 状态
	 */
	@Column(name="INUSE")
	private Integer dataStatus;
	
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
	 *  创建时间
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

	public Long getTopId() {
		return topId;
	}

	public void setTopId(Long topId) {
		this.topId = topId;
	}

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

	public String getTopAlias() {
		return topAlias;
	}

	public void setTopAlias(String topAlias) {
		this.topAlias = topAlias;
	}

	

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public NodeTopMain() {
		super();
	}

	public NodeTopMain(Long topId, String topCode, String topName, String topAlias, Integer dataStatus,
			String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
			String des, Integer sortNum, Integer version) {
		super();
		this.topId = topId;
		this.topCode = topCode;
		this.topName = topName;
		this.topAlias = topAlias;
		this.dataStatus = dataStatus;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
	}
	
	
}
