package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_IC_STDDMCUAB")
public class StddmCuabs implements Serializable {
	/**
	 * 罐量指标—立罐分米（厘米）表
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="STDDMCUAB_ID")
	@SpecialResource(name="st.dataId")
	private Long dataId;
	
	@Column(name="NODE_ID")
	@SpecialResource(name="st.nodeId")
	private Long nodeId;
	
	@Column(name="STDDMCUAB_HGT")
	@SpecialResource(name="st.hgt")
	private Double hgt;
	
	@Column(name="STDDMCUAB_VAL")
	@SpecialResource(name="st.dmCuba")
	private Double dmCuba;
	
	@Transient
	@SpecialResource(name="t.nodeCode")
	private String nodeCode;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	
	@Column(name = "DES")
	@SpecialResource(name="st.des")
	private String des;
	
	@Column(name = "INUSE")
	@SpecialResource(name="st.inUse")
	private Integer inUse;
	
	@Column(name="SORT_NUM")
	@SpecialResource(name="st.sortNum")
	private Integer sortNum;
	

	public StddmCuabs(Long dataId, Long nodeId, Double hgt, Double dmCuba, String nodeCode,String crtUserCode,String crtUserName,Date crtDate,String mntUserCode,
			String mntUserName,Date mntDate,String des,Integer inUse,Integer sortNum) {
		super();
		this.dataId = dataId;
		this.nodeId = nodeId;
		this.hgt = hgt;
		this.dmCuba = dmCuba;
		this.nodeCode = nodeCode;
		this.crtUserCode=crtUserCode;
		this.crtUserName=crtUserName;
		this.crtDate=crtDate;
		this.mntUserCode=mntUserCode;
		this.mntUserName=mntUserName;
		this.mntDate=mntDate;
		this.des=des;
		this.inUse=inUse;
		this.sortNum=sortNum;
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

	public Double getHgt() {
		return hgt;
	}



	public void setHgt(Double hgt) {
		this.hgt = hgt;
	}



	public Double getDmCuba() {
		return dmCuba;
	}



	public void setDmCuba(Double dmCuba) {
		this.dmCuba = dmCuba;
	}



	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	
}
