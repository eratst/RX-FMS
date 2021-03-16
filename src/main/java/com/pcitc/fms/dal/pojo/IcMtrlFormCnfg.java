package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_MTRLFORMCNFG")
public class IcMtrlFormCnfg implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MTRLFORMCNFG_ID")
	private Long mtrlFormCnfgId;

	@Column(name = "MTRL_ID")
	private Long mtrlId;

	@Transient
	private String mtrlCode;

	@Transient
	private String mtrlName;

	@Column(name = "TANK_ID")
	private Long nodeId;

	@Transient
	private String nodeCode;

	@Transient
	private String nodeName;

	@Column(name = "TANK_FORM")
	private String tankForm;

	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;

	@Column(name = "CRTDATE")
	private Date crtDate;

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;

	@Column(name = "MNTDATE")
	private Date mntDate;

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "INUSE")
	private Integer inUse;

	public IcMtrlFormCnfg() {
	}

	public IcMtrlFormCnfg(Long mtrlFormCnfgId, Long mtrlId, String mtrlCode, String mtrlName, Long nodeId,
			String nodeCode, String nodeName, String tankForm, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, String des, Integer sortNum, Integer version,
			Integer inUse) {
		super();
		this.mtrlFormCnfgId = mtrlFormCnfgId;
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.tankForm = tankForm;
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

	public Long getMtrlFormCnfgId() {
		return mtrlFormCnfgId;
	}

	public void setMtrlFormCnfgId(Long mtrlFormCnfgId) {
		this.mtrlFormCnfgId = mtrlFormCnfgId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
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

	public String getTankForm() {
		return tankForm;
	}

	public void setTankForm(String tankForm) {
		this.tankForm = tankForm;
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

}
