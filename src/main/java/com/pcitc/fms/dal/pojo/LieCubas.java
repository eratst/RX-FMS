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
@Table(name = "T_IC_LIECUBA")
public class LieCubas implements Serializable {
	/**
	 * 罐量指标—卧罐罐容
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="LIECUBA_ID")
	@SpecialResource(name="lie.dataId")
	private Long dataId;
	
	@Column(name="NODE_ID")
	@SpecialResource(name="lie.nodeId")
	private Long nodeId;
	
	@Column(name="LIECUBA_HGT")
	@SpecialResource(name="lie.hgt")
	private Double hgt;
	
	@Column(name="LIECUBA_VAL")
	@SpecialResource(name="lie.cuba")
	private Double cuba;
	
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
	@SpecialResource(name="lie.des")
	private String des;
	
	@Column(name = "INUSE")
	@SpecialResource(name="lie.inUse")
	private Integer inUse;
	
	@Column(name="SORT_NUM")
	@SpecialResource(name="lie.sortNum")
	private Integer sortNum;
	
	
	public LieCubas(Long dataId, Long nodeId, Double hgt, Double cuba, String nodeCode,
			String crtUserCode,String crtUserName,Date crtDate,String mntUserCode,
			String mntUserName,Date mntDate,String des,Integer inUse,Integer sortNum) {
		super();
		this.dataId = dataId;
		this.nodeId = nodeId;
		this.hgt = hgt;
		this.cuba = cuba;
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



	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
}
