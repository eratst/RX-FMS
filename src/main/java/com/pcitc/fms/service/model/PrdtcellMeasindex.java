package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * 生产单元度量指标
 *
 * zhao.li
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class PrdtcellMeasindex extends BaseResRep implements Serializable {

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
	@ResourceMember(OnlyQuery=true)
	private Long idxTypeId;// 新增需要
	
	private String idxTypeCode;

	/**
	 * 指标类型名称
	 */
	private String idxTypeName;

	/**
	 * 生产单元
	 */
	@ResourceMember(OnlyQuery=true)
	private Long cellId;
	
	@BuildLink(resourceCode = "cellCode")
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
	@ResourceMember(OnlyQuery=true)
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
	
	@ResourceMember(OnlyQuery = true, Name = "$orderby")
	private String orderby;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$idxCodes")
	private List<String> codeList;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$cellCodes")
	private List<String> cellCodes;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@ResourceMember(OnlyQuery=true)
	private String rentCode;
	
	@ResourceMember(OnlyQuery=true)
	private String bizCode;
	
	public PrdtcellMeasindex() {
		super();
	}

	public PrdtcellMeasindex(String idxCode, String idxName, String idxAlias, String idxTypeCode, Integer inUse,
			String orderby, List<String> codeList, List<String> cellCodes, Integer top, Integer skip,String rentCode,String bizCode,String cellCode) {
		super();
		this.idxCode = idxCode;
		this.idxName = idxName;
		this.idxAbbrName = idxAlias;
		this.idxTypeCode = idxTypeCode;
		this.inUse = inUse;
		this.orderby = orderby;
		this.codeList = codeList;
		this.cellCodes = cellCodes;
		this.top = top;
		this.skip = skip;
		this.rentCode=rentCode;
		this.bizCode=bizCode;
		this.rentCode=rentCode;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public List<String> getCellCodes() {
		return cellCodes;
	}

	public void setCellCodes(List<String> cellCodes) {
		this.cellCodes = cellCodes;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}



}
