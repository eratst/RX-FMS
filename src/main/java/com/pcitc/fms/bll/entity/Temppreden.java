package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 温度压力标准密度表
 * @author xin.kou
 *
 */
public class Temppreden implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 温度压力标准密度ID
	 */
	private Long temppredenId;

	/**
	 * 温度
	 */
	private Double temp;

	/**
	 * 压力
	 */
	private Double pre;

	/**
	 * 气体密度
	 */
	private Double gasDen;

	/**
	 * 密度
	 */
	private Double den;

	
	private String crtUserCode; // 创建人ID
	private String crtUserName;// 创建人名称
	private Date crtDate;// 创建时间
	private String mntUserCode;// 最后维护人ID
	private String mntUserName;// 最后维护人名称
	private Date mntDate;//维护时间
	private Integer inUse;
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



	public Long getTemppredenId() {
		return temppredenId;
	}

	public void setTemppredenId(Long temppredenId) {
		this.temppredenId = temppredenId;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getPre() {
		return pre;
	}

	public void setPre(Double pre) {
		this.pre = pre;
	}

	public Double getGasDen() {
		return gasDen;
	}

	public void setGasDen(Double gasDen) {
		this.gasDen = gasDen;
	}

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
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

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	
	
}
