package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author xin.kou
 *
 */

@Entity
@Table(name = "T_IC_VCF")
public class IcVcf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * VCFID
	 */
	@Id
	@Column(name = "VCF_ID")
	private Long vcfId;

	/**
	 * 密度
	 */
	@Column(name = "DEN")
	private Double den;

	/**
	 * 温度
	 */
	@Column(name = "TEMP")
	private Double temp;

	/**
	 * VCF值
	 */
	@Column(name = "VCF_VAL")
	private Double vcfVal;

	/**
	 * 算法类别
	 */
	@Column(name = "VCFTYPE_ID")
	private Long vcfTypeId;
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

	/**
	 * 描述
	 */
	@Column(name = "DES")
	private String des;

	/**
	 * 排序
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;

	@Transient
	private String vcfTypeCode;
	
	@Transient
	private String vcfTypeName;


	
	
	public Long getVcfId() {
		return vcfId;
	}

	public void setVcfId(Long vcfId) {
		this.vcfId = vcfId;
	}

	public Long getVcfTypeId() {
		return vcfTypeId;
	}

	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
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

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getVcfVal() {
		return vcfVal;
	}

	public void setVcfVal(Double vcfVal) {
		this.vcfVal = vcfVal;
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

	public String getVcfTypeCode() {
		return vcfTypeCode;
	}

	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
	}

	public String getVcfTypeName() {
		return vcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
	}

	public IcVcf() {
		super();
	}

	public IcVcf(Long vcfId, Double den, Double temp, Double vcfVal, Long vcfTypeId, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
			Integer sortNum, Integer version, String vcfTypeCode, String vcfTypeName) {
		super();
		this.vcfId = vcfId;
		this.den = den;
		this.temp = temp;
		this.vcfVal = vcfVal;
		this.vcfTypeId = vcfTypeId;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.version = version;
		this.vcfTypeCode = vcfTypeCode;
		this.vcfTypeName = vcfTypeName;
	}
	
	
	
}
