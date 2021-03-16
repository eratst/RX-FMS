package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

/**
 * 生产单元度量指标
 *
 * zhao.li
 */
public class PrdtcellMeasindex implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idxCode;

	/**
	 * 度量指标名称
	 */
	private String idxName;

	/**
	 * 度量指标简称
	 */
	private String idxAbbrName;

	/**
	 * 度量指标类型
	 */
	private Long idxTypeId;// 新增需要
	
	private String idxTypeCode;

	/**
	 * 指标类型名称
	 */
	private String idxTypeName;

	/**
	 * 生产单元
	 */
	private Long cellId;
	
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	private String cellName;

	/**
	 * 生产单元简称
	 */
	private String cellAbbrName;

	/**
	 * 量纲Id
	 */
	private Long dimensionId;
	
	private String dimensionCode;
	
	private String dimensionName;

	/**
	 * 量纲简称
	 */
	private String dimensionAbbrName;
	
	/**
	 * 量纲调整系数
	 */
	private Double exchangeRate;

	/**
	 * 度量公式
	 */
	private String idxFormula;

	/**
	 * 源数据类型 SG8000,RTDB
	 */
	private String sourceDataType;

	/**
	 * 是否启用
	 */
	private Integer inUse;
	
	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 描述
	 */
	private String des;

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


}
