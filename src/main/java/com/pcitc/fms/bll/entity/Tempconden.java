package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class Tempconden implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long tempcondenId;
	
	private Long mtrlId;
	
	private String mtrlCode;
	
	private Double cubaTempCofe;
	
	private Double den;
	
	private Double con;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称
	
	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	private String des;
	
	private Integer inUse;
	
	private Integer sortNum;
	
	
	

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
