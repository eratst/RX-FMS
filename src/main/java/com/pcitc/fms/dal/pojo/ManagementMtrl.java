package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_PM_MTRL")
public class ManagementMtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MTRL_ID")
	private Integer mtrlId;

	@Column(name = "MTRL_CODE")
	private String mtrlCode;

	@Column(name = "MTRL_NAME")
	private String mtrlName;

	@Column(name = "MTRL_ALIAS")
	private String mtrlSname;

	@Column(name = "UPPER_MTRL_CODE")
	private String upperMtrlCode;

	@Column(name = "MTRL_TYPE_ID")
	private Integer mtrlTypeId;

	@Transient
	private String mtrlTypeCode;

	@Transient
	private String mtrlTypeName;

	@Column(name = "VCF_TYPE")
	private Integer vcfTypeId;

	@Transient
	private String vcfTypeCode;

	@Transient
	private String vcfTypeName;

	@Column(name = "DIMENSION_ID")
	private Integer dimensionId;

	@Transient
	private String dimensionCode;

	@Transient
	private String dimensionName;

	@Transient
	private String dimensionSname;

	@Transient
	private Integer inUse;

	@Column(name = "DATA_DEC")
	private Integer dec;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	public ManagementMtrl(Integer mtrlId, String mtrlCode, String mtrlName, String mtrlAlias, String upperMtrlCode,
			Integer mtrlTypeId, String mtrlTypeCode, String mtrlTypeName, Integer vcfTypeId, String vcfTypeCode,
			String vcfTypeName, Integer dimensionId, String dimensionCode, String dimensionName, String dimensionSname,
			Integer inUse, Integer dec, Integer sortNum) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlSname = mtrlAlias;
		this.upperMtrlCode = upperMtrlCode;
		this.mtrlTypeId = mtrlTypeId;
		this.mtrlTypeCode = mtrlTypeCode;
		this.mtrlTypeName = mtrlTypeName;
		this.vcfTypeId = vcfTypeId;
		this.vcfTypeCode = vcfTypeCode;
		this.vcfTypeName = vcfTypeName;
		this.dimensionId = dimensionId;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.dimensionSname = dimensionSname;
		this.inUse = inUse;
		this.dec = dec;
		this.sortNum = sortNum;
	}

	public Integer getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Integer mtrlId) {
		this.mtrlId = mtrlId;
	}

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}

	public String getMtrlSname() {
		return mtrlSname;
	}

	public void setMtrlSname(String mtrlSname) {
		this.mtrlSname = mtrlSname;
	}

	public String getDimensionSname() {
		return dimensionSname;
	}

	public void setDimensionSname(String dimensionSname) {
		this.dimensionSname = dimensionSname;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getUpperMtrlCode() {
		return upperMtrlCode;
	}

	public void setUpperMtrlCode(String upperMtrlCode) {
		this.upperMtrlCode = upperMtrlCode;
	}

	public Integer getMtrlTypeId() {
		return mtrlTypeId;
	}

	public void setMtrlTypeId(Integer mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}

	public String getMtrlTypeCode() {
		return mtrlTypeCode;
	}

	public void setMtrlTypeCode(String mtrlTypeCode) {
		this.mtrlTypeCode = mtrlTypeCode;
	}

	public String getMtrlTypeName() {
		return mtrlTypeName;
	}

	public void setMtrlTypeName(String mtrlTypeName) {
		this.mtrlTypeName = mtrlTypeName;
	}

	public Integer getVcfTypeId() {
		return vcfTypeId;
	}

	public void setVcfTypeId(Integer vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
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

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
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

	public Integer getDec() {
		return dec;
	}

	public void setDec(Integer dec) {
		this.dec = dec;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
