package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Measurement extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

//	private Long idxId;
	
	@ResourceMember(InQueries = "condition", Name = "idxCode")
	@CheckField(CheckName=CheckNameType.CODE)
	private String idxCode;

	@ResourceMember(InQueries = "condition", Name = "idxName")
	@CheckField(CheckName=CheckNameType.NAME)
	private String idxName;

	@ResourceMember(InQueries = "condition", Name = "idxAlias")
	@CheckField(CheckName=CheckNameType.NAME)
	private String idxAlias;

	
	@ResourceMember(InQueries = "condition", Name = "idxTypeCode")
	@CheckField(CheckName=CheckNameType.CODE)
	private String idxTypeCode;
	
	@CheckField(CheckName=CheckNameType.NAME)
	private String idxTypeName;


	@CheckField(CheckName=CheckNameType.CODE)
	@ResourceMember(InQueries = "condition", Name = "nodeCode")
	private String nodeCode;
	
	@CheckField(CheckName=CheckNameType.NAME)
	private String nodeName;

	@CheckField(CheckName=CheckNameType.NAME)
	private String nodeAlias;

	@CheckField(CheckName=CheckNameType.CODE)
	private String dimensionCode;
	
	@CheckField(CheckName=CheckNameType.NAME)
	private String dimensionName;
	
	@CheckField(CheckName=CheckNameType.NAME)
	private String dimensionAlias;

	private String idxFormula;
	
	private String sourceDataType;


	@CheckField(CheckName = CheckNameType.ENABLED )
	private Integer inUse;

	private Long sortNum;

	private String des;
	
	
	private String exchangeRate;
		
	@CheckField(CheckName=CheckNameType.NAME)
	private String nodeTypeName;
	
	private String areaCode;
	
	private String areaName;
	
	private String areaAlias;

	@CheckField(CheckName=CheckNameType.CODELIST,StrLength=50)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	private List<String> nodeCodes;
	
	@CheckField(CheckName=CheckNameType.CODELIST,StrLength=50)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$idxCodes")
	private List<String> idxCodes;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO,StrLength=50)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;
	@CheckField(CheckName=CheckNameType.PAGEINFO,StrLength=50)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@CheckField(CheckName=CheckNameType.CODELIST,StrLength=50)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$filterData")
	private List<String> filterData;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
    @ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	
	@CheckField(CheckName=CheckNameType.CODE)
	private String nodeTypeCode;
	
	
	
	
	public String getNodeTypeCode() {
		return nodeTypeCode;
	}
	public void setNodeTypeCode(String nodeTypeCode) {
		this.nodeTypeCode = nodeTypeCode;
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
	public List<String> getFilterData() {
		return filterData;
	}
	public void setFilterData(List<String> filterData) {
		this.filterData = filterData;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaAlias() {
		return areaAlias;
	}
	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}	public String getIdxTypeCode() {
		return idxTypeCode;
	}
	public void setIdxTypeCode(String idxTypeCode) {
		this.idxTypeCode = idxTypeCode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeAlias() {
		return nodeAlias;
	}
	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
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
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	public Long getSortNum() {
		return sortNum;
	}
	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
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
	public String getIdxAlias() {
		return idxAlias;
	}
	public void setIdxAlias(String idxAlias) {
		this.idxAlias = idxAlias;
	}
	public String getIdxFormula() {
		return idxFormula;
	}
	public void setIdxFormula(String idxFormula) {
		this.idxFormula = idxFormula;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getSourceDataType() {
		return sourceDataType;
	}
	public void setSourceDataType(String sourceDataType) {
		this.sourceDataType = sourceDataType;
	}
	public String getIdxTypeName() {
		return idxTypeName;
	}
	public void setIdxTypeName(String idxTypeName) {
		this.idxTypeName = idxTypeName;
	}
	public String getNodeTypeName() {
		return nodeTypeName;
	}
	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}
	
	public List<String> getNodeCodes() {
		return nodeCodes;
	}
	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
	}
	public List<String> getIdxCodes() {
		return idxCodes;
	}
	public void setIdxCodes(List<String> idxCodes) {
		this.idxCodes = idxCodes;
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
