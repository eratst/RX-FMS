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
@Table(name="T_IC_GLBCUBA")
public class GlbCubas implements Serializable {
	/**
	 * 罐量指标—球罐罐容
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="GLBCUBA_ID")
	@SpecialResource(name="glb.dataId")
	private Long dataId;
	
	@Column(name="NODE_ID")
	@SpecialResource(name="glb.nodeId")
	private Long nodeId;
	
	@Column(name="GLBCUBA_HGT")
	@SpecialResource(name="glb.hgt")
	private Double hgt;
	
	@Column(name="GLBCUBA_VAL")
	@SpecialResource(name="glb.cuba")
	private Double cuba;
	
	//压力下限
	@Column(name="PRES_FLR_LMT")
	@SpecialResource(name="glb.presFlrLmt")
	private Integer presFlrLmt;
	
	//压力上限
	@Column(name="PRES_UP_LMT")
	@SpecialResource(name="glb.presUpLmt")
	private Integer presUpLmt;
	
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
	@SpecialResource(name="glb.des")
	private String des;
	
	@Column(name = "INUSE")
	@SpecialResource(name="glb.inUse")
	private Integer inUse;
	
	@Column(name="SORT_NUM")
	@SpecialResource(name="glb.sortNum")
	private Integer sortNum;
	
	

	public GlbCubas(Long dataId, Long nodeId, Double hgt, Double cuba, Integer presFlrLmt, Integer presUpLmt,
			String nodeCode,String crtUserCode,String crtUserName,Date crtDate,String mntUserCode,
			String mntUserName,Date mntDate,String des,Integer inUse,Integer sortNum) {
		super();
		this.dataId = dataId;
		this.nodeId = nodeId;
		this.hgt = hgt;
		this.cuba = cuba;
		this.presFlrLmt = presFlrLmt;
		this.presUpLmt = presUpLmt;
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



	public Double getCuba() {
		return cuba;
	}



	public void setCuba(Double cuba) {
		this.cuba = cuba;
	}



	public Integer getPresFlrLmt() {
		return presFlrLmt;
	}

	public void setPresFlrLmt(Integer presFlrLmt) {
		this.presFlrLmt = presFlrLmt;
	}

	public Integer getPresUpLmt() {
		return presUpLmt;
	}

	public void setPresUpLmt(Integer presUpLmt) {
		this.presUpLmt = presUpLmt;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
}
