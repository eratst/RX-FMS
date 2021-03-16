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

import org.junit.ClassRule;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_DIMENSION")
public class Dimension implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 量纲ID
	@Id
	@Column(name = "DIMENSION_ID")
	@SpecialResource(name="a.dimensionId")
	private Long dimensionId;
	// 量纲编码
	@Column(name = "DIMENSION_CODE")
	@SpecialResource(name="a.dimensionCode")
	private String dimensionCode;
	// 量纲名称
	@Column(name = "DIMENSION_NAME")
	@SpecialResource(name="a.dimensionName")
	private String dimensionName;
	// 量纲简称名称
	@Column(name = "DIMENSION_ALIAS")
	@SpecialResource(name="a.dimensionAlias")
	private String dimensionAlias;
	// 状态
	@Column(name = "INUSE")
	@SpecialResource(name="a.inUse")
	private Integer inUse;
	
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
	
	// 描述
	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "SYMBOL")
	private String symbol;
	
	public Dimension() {
		// TODO Auto-generated constructor stub
	}

	public Long getDimensionId() {
		return dimensionId;
	}


	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}


	public Integer getInUse() {
		return inUse;
	}


	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public Integer getSortNum() {
		return sortNum;
	}


	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}


	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public String getDimensionAlias() {
		return dimensionAlias;
	}

	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
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

}
