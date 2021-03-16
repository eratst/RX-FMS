package com.pcitc.fms.bll.entity;

import java.util.Date;

/**
 * entity 液化产品系数（体积温度）实体类
 * 
 * @author xin.kou
 *
 */
public class LiqprodCubaTempCoef {

	// 体积温度系数编码
	private Long cubaTempCofeId;

	// 密度下限
	private Double denFlrLmt;

	// 密度上限
	private Double denUpLmt;

	// 体积温度系数
	private Double cubaTempCofe;

	// 创建人ID
	private String crtUserCode;

	// 创建人名称
	private String crtUserName;

	// 创建时间
	private Date crtDate;

	// 最后维护人ID
	private String mntUserCode;

	// 最后维护人名称
	private String mntUserName;

	// 维护日期
	private Date mntDate;

	// 描述
	private String des;
	
	private Integer inUse;

	// 排序
	private Integer sortNum;

	// 乐观锁版本
	private Integer version;
	

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Long getCubaTempCofeId() {
		return cubaTempCofeId;
	}

	public void setCubaTempCofeId(Long cubaTempCofeId) {
		this.cubaTempCofeId = cubaTempCofeId;
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

	public Double getDenFlrLmt() {
		return denFlrLmt;
	}

	public void setDenFlrLmt(Double denFlrLmt) {
		this.denFlrLmt = denFlrLmt;
	}

	public Double getDenUpLmt() {
		return denUpLmt;
	}

	public void setDenUpLmt(Double denUpLmt) {
		this.denUpLmt = denUpLmt;
	}

	public Double getCubaTempCofe() {
		return cubaTempCofe;
	}

	public void setCubaTempCofe(Double cubaTempCofe) {
		this.cubaTempCofe = cubaTempCofe;
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
