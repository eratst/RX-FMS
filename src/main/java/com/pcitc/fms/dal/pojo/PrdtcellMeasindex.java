package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

/**
 * 生产单元度量指标
 *
 * zhao.li
 */
@Entity
@Table(name = "T_PM_PCMEASINDEX")
public class PrdtcellMeasindex implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 度量指标ID
	 */
	@Id
	@Column(name = "PCMEASINDEX_ID")
	private Long idxId;

	/**
	 * 度量指标编码
	 */
	@SpecialResource(name="prdtcellMeasindex.idxCode")
	@Column(name = "PCMEASINDEX_CODE")
	private String idxCode;

	/**
	 * 度量指标名称
	 */
	@SpecialResource(name="prdtcellMeasindex.idxName")
	@Column(name = "PCMEASINDEX_NAME")
	private String idxName;

	/**
	 * 度量指标简称
	 */
	@SpecialResource(name="prdtcellMeasindex.idxAbbrName")
	@Column(name = "PCMEASINDEX_ALIAS")
	private String idxAbbrName;

	/**
	 * 度量指标类型
	 */
	@Column(name = "IDXTYPE_ID")
	private Long idxTypeId;// 新增需要
	
	@SpecialResource(name="idxType.idxTypeCode")
	@Transient
	private String idxTypeCode;

	/**
	 * 指标类型名称
	 */
	@SpecialResource(name="idxType.idxTypeName")
	@Transient
	private String idxTypeName;

	/**
	 * 生产单元
	 */
	@Column(name = "PRDTCELL_ID")
	private Long cellId;
	
	@SpecialResource(name="prdtcell.cellCode")
	@Transient
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	@SpecialResource(name="prdtcell.cellName")
	@Transient
	private String cellName;

	/**
	 * 生产单元简称
	 */
	@SpecialResource(name="prdtcell.cellAbbrName")
	@Transient
	private String cellAbbrName;

	/**
	 * 量纲Id
	 */
	@Column(name = "DIMENSION_ID")
	private Long dimensionId;
	
	@Transient
	@SpecialResource(name="dimension.dimensionCode")
	private String dimensionCode;
	
	@Transient
	@SpecialResource(name="dimension.dimensionName")
	private String dimensionName;

	/**
	 * 量纲简称
	 */
	@Transient
	@SpecialResource(name="dimension.dimensionAbbrName")
	private String dimensionAbbrName;
	
	/**
	 * 量纲调整系数
	 */
	@SpecialResource(name="rdtcellMeasindex.exchangeRate")
	@Column(name = "EXCHANGE_RATE")
	private Double exchangeRate;

	/**
	 * 度量公式
	 */
	@SpecialResource(name="prdtcellMeasindex.idxFormula")
	@Column(name = "PCMEASINDEX_FORMULA")
	private String idxFormula;

	/**
	 * 源数据类型 SG8000,RTDB
	 */
	@SpecialResource(name="prdtcellMeasindex.sourceDataType")
	@Column(name = "SOURCE_DATA_TYPE")
	private String sourceDataType;

	/**
	 * 是否启用
	 */
	@SpecialResource(name="prdtcellMeasindex.inUse")
	@Column(name = "INUSE")
	private Integer inUse;
	
	/**
	 * 排序
	 */
	@SpecialResource(name="prdtcellMeasindex.sortNum")
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	/**
	 * 描述
	 */
	@SpecialResource(name="prdtcellMeasindex.des")
	@Column(name = "DES")
	private String des;
	
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
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;

	public PrdtcellMeasindex() {
		super();
	}

	public PrdtcellMeasindex(String idxCode, String idxName, String idxAlias, String idxTypeCode, String idxTypeName,
			String cellCode, String cellName, String cellAlias, String dimensionCode, String dimensionName,
			String dimensionAlias, Double exchangeRate, String idxFormula, String sourceDataType, Integer inUse,
			Integer sortNum, String des) {
		super();
		this.idxCode = idxCode;
		this.idxName = idxName;
		this.idxAbbrName = idxAlias;
		this.idxTypeCode = idxTypeCode;
		this.idxTypeName = idxTypeName;
		this.cellCode = cellCode;
		this.cellName = cellName;
		this.cellAbbrName = cellAlias;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.dimensionAbbrName = dimensionAlias;
		this.exchangeRate = exchangeRate;
		this.idxFormula = idxFormula;
		this.sourceDataType = sourceDataType;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
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

	public Long getIdxId() {
		return idxId;
	}

	public void setIdxId(Long idxId) {
		this.idxId = idxId;
	}

	public Long getIdxTypeId() {
		return idxTypeId;
	}

	public void setIdxTypeId(Long idxTypeId) {
		this.idxTypeId = idxTypeId;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getIdxCode() {
		return idxCode;
	}

	public void setIdxCode(String idxCode) {
		this.idxCode = idxCode;
	}

	public String getIdxName() {
		return idxName;
	}

	public void setIdxName(String idxName) {
		this.idxName = idxName;
	}

	public String getIdxAbbrName() {
		return idxAbbrName;
	}

	public void setIdxAbbrName(String idxAbbrName) {
		this.idxAbbrName = idxAbbrName;
	}


	public String getIdxTypeCode() {
		return idxTypeCode;
	}

	public void setIdxTypeCode(String idxTypeCode) {
		this.idxTypeCode = idxTypeCode;
	}

	public String getIdxTypeName() {
		return idxTypeName;
	}

	public void setIdxTypeName(String idxTypeName) {
		this.idxTypeName = idxTypeName;
	}


	public String getCellCode() {
		return cellCode;
	}

	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellAbbrName() {
		return cellAbbrName;
	}

	public void setCellAbbrName(String cellAbbrName) {
		this.cellAbbrName = cellAbbrName;
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

	public String getDimensionAbbrName() {
		return dimensionAbbrName;
	}

	public void setDimensionAbbrName(String dimensionAbbrName) {
		this.dimensionAbbrName = dimensionAbbrName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getIdxFormula() {
		return idxFormula;
	}

	public void setIdxFormula(String idxFormula) {
		this.idxFormula = idxFormula;
	}

	public String getSourceDataType() {
		return sourceDataType;
	}

	public void setSourceDataType(String sourceDataType) {
		this.sourceDataType = sourceDataType;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
