package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

/* @Description 拓扑关系主表
 * parameter a * @DATE 2017/12/27
 * @Author zhaozhenqiang
 * return @a
 */
@Entity
@Table(name = "T_IC_NODETOP_MAIN")
public class TIcNodetopMain implements Serializable {
	private static final long serialVersionUID = 1L;
	@RegionMember
	@Id
	@Column(name = "TOP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NODETOP_MAIN_GENERATOR")
	@SequenceGenerator(name = "NODETOP_MAIN_GENERATOR", sequenceName = "SEQ_T_IC_NODETOP_MAIN", allocationSize = 1)
	private Integer topId;

	@Column(name = "TOP_CODE")
	private String topCode;

	@Column(name = "TOP_NAME")
	private String topName;

	@Column(name = "TOP_ALIAS")
	private String topAlias;

	@Column(name = "DATA_STATUS")
	private Integer dataStatus;

	@Column(name = "CRT_USER_ID")
	private String crtUserId;

	@Column(name = "CRT_USER_NAME")
	private String crtUserName;

	@Column(name = "CRT_DATE")
	private Date crtDate;

	@Column(name = "MNT_USER_ID")
	private Integer mntUserId;

	@Column(name = "MNT_USER_NAME")
	private String mntUserName;

	@Column(name = "MNT_DATE")
	private Date mntDate;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Column(name = "DES")
	private String des;


	public TIcNodetopMain() {
	}


	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
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

	public Integer getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(Integer mntUserId) {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
}
