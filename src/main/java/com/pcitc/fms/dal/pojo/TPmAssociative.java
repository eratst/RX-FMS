

package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_PM_ASSOCIATIVE")
public class TPmAssociative implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ASSOCIATIVE_ID")
	private Integer associativeId;

	@Column(name = "NODE_ID")
	private Integer nodeId;

	@Column(name = "NODE_CODE")
	private String nodeCode;

	@Column(name = "NODE_NAME")
	private String nodeName;

	@Column(name = "MTRL_ID")
	private Integer mtrlId;

	@Column(name = "MTRL_CODE")
	private String mtrlCode;

	@Column(name = "MTRL_NAME")
	private String mtrlName;

	@Column(name = "CRT_USER_ID")
	private Integer crtUserId;

	@Column(name = "CRT_USER_NAME")
	private String crtUserName;

	@Column(name = "CRT_DATE")
	private Date crtDate;

	@Column(name = "MNT_USER_ID")
	private Integer mntUserId;

	@Column(name = "MNT_DATE")
	private Date mntDate;

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "DATA_STATUS")
	private Integer dataStatus;

	@Column(name = "MNT_USER_NAME")
	private String mntUserName;


	public TPmAssociative() {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getAssociativeId() {
		return associativeId;
	}

	public void setAssociativeId(Integer associativeId) {
		this.associativeId = associativeId;
	}

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

	public Integer getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Integer mtrlId) {
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

	public Integer getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(Integer crtUserId) {
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

	public Integer getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(Integer mntUserId) {
		this.mntUserId = mntUserId;
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

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}
}
