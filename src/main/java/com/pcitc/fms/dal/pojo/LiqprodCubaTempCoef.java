package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.SpecialResource;
/**
 * 
 * @author xin.kou
 *
 */
@Entity
@Table(name = "T_IC_CUBATEMPCOEF")
public class LiqprodCubaTempCoef implements Serializable {

	private static final long serialVersionUID = 1L;

	// 体积温度系数编码
	@Id
	@Column(name = "CUBATEMPCOEF_ID")
	private Long cubaTempCofeId;

	// 密度下限
	
	@Column(name = "DEN_FLR_LMT")
	private Double denFlrLmt;

	// 密度上限
	@Column(name = "DEN_UP_LMT")
	private Double denUpLmt;

	// 体积温度系数
	@Column(name = "CUBA_TEMP_COFE")
	private Double cubaTempCofe;

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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	
}
