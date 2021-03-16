package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

public class NodeTopDTL implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据ID
	 */
	private Long dataId;
	/**
	 * 数据编码
	 */
	private String dataCode;
	
	
	/**
	 * 	S_NODE_ID
	 */
	private Long snodeId;
	
	/**
	 * 	T_NODE_ID
	 */
	private Long tnodeId;
	
	private String snodeName;

	private String tnodeName;
	
	private String snodeCode;
	
	private String tnodeCode;
	
	/**
	 * 拓扑关系ID
	 */
	private Long topId;
	
	/**
	 * 创建人ID
	 */
	private String crtUserId;
	
	/**
	 * 创建人名称
	 */
	private String crtUserName; 

	/**
	 *  创建时间
	 */
	private Date crtDate;

	/**
	 * 维护人ID
	 */
	private String mntUserId;
	/**
	 * 维护人名称
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
	
	private Integer inUse;
	
	private String topCode;
	
	private String topName;
	
	private String nodeName;
	
	private String nodeCode;

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	
}
