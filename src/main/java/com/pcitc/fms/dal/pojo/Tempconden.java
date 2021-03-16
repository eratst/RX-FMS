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
@Table(name = "T_IC_TEMPCONDEN")
public class Tempconden implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TEMPCONDEN_ID")
	private Long tempcondenId;
	
	@SpecialResource(name="t.mtrlId")
	@Column(name = "MTRL_ID")
	private Long mtrlId;
	
	@SpecialResource(name="m.mtrlCode")
	@Transient
	private String mtrlCode;
	
	@SpecialResource(name="t.cubaTempCofe")
	@Column(name = "CUBA_TEMP_COFE")
	private Double cubaTempCofe;
	
	@SpecialResource(name="t.den")
	@Column(name = "DEN")
	private Double den;
	
	@SpecialResource(name="t.con")
	@Column(name = "CON")
	private Double con;
	
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
	@SpecialResource(name="t.des")
	private String des;
	
	@Column(name = "INUSE")
	@SpecialResource(name="t.inUse")
	private Integer inUse;
	
	@Column(name="SORT_NUM")
	@SpecialResource(name="t.sortNum")
	private Integer sortNum;

	public Tempconden(Long tempcondenId, Long mtrlId, String mtrlCode, Double cubaTempCofe, Double den,
			Double con,String crtUserCode,String crtUserName,Date crtDate,String mntUserCode,
			String mntUserName,Date mntDate,String des,Integer inUse,Integer sortNum) {
		super();
		this.tempcondenId = tempcondenId;
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.cubaTempCofe = cubaTempCofe;
		this.den = den;
		this.con = con;
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

	

	public Long getTempcondenId() {
		return tempcondenId;
	}



	public void setTempcondenId(Long tempcondenId) {
		this.tempcondenId = tempcondenId;
	}



	public Long getMtrlId() {
		return mtrlId;
	}



	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
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



	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public Double getCubaTempCofe() {
		return cubaTempCofe;
	}

	public void setCubaTempCofe(Double cubaTempCofe) {
		this.cubaTempCofe = cubaTempCofe;
	}

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public Double getCon() {
		return con;
	}

	public void setCon(Double con) {
		this.con = con;
	}
	
}
