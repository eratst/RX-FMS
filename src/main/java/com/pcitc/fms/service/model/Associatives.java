package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class Associatives extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String nodeCode;

	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="nodeName")
	private String nodeName;

	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="nodeAbbrName")
	private String nodeAbbrName;

	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String mtrlCode;

	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="mtrlName")
	private String mtrlName;

	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="mtrlAbbrName")
	private String mtrlAbbrName;

	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String nodeTypeCode;

	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="nodeTypeName")
	private String nodeTypeName;

	@CheckField(CheckName = CheckNameType.ENABLED ,StrLength=10)
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer sortNum;

	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	
	
	private String upperMtrlCode;
	
	private String upperMtrlName;
	
	private String upperMtrlAbbrName;
	
	@ResourceMember( OnlyQuery = true)
	private Long VcfTypeId;
	
	private String VcfTypeCode;
	
	private String VcfTypeName;
	
	@ResourceMember( OnlyQuery = true)
	private Long mtrlTypeId;
	
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=200)
	private String mtrlTypeCode;
	
	private String mtrlTypeName;
	
	private Integer dec;
	
	@ResourceMember( OnlyQuery = true)
	private Long dimensionId;
	
	private String dimensionCode;
	
	private String dimensionName;

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
	private List<String> nodeCodes;

	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$mtrlCodes")
	private List<String> mtrlCodes;

	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	
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

	public String getUpperMtrlCode() {
		return upperMtrlCode;
	}

	public void setUpperMtrlCode(String upperMtrlCode) {
		this.upperMtrlCode = upperMtrlCode;
	}

	public String getUpperMtrlName() {
		return upperMtrlName;
	}

	public void setUpperMtrlName(String upperMtrlName) {
		this.upperMtrlName = upperMtrlName;
	}

	public String getUpperMtrlAbbrName() {
		return upperMtrlAbbrName;
	}

	public void setUpperMtrlAbbrName(String upperMtrlAbbrName) {
		this.upperMtrlAbbrName = upperMtrlAbbrName;
	}
	
	public Long getVcfTypeId() {
		return VcfTypeId;
	}

	public void setVcfTypeId(Long vcfTypeId) {
		VcfTypeId = vcfTypeId;
	}

	public Long getMtrlTypeId() {
		return mtrlTypeId;
	}

	public void setMtrlTypeId(Long mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getVcfTypeCode() {
		return VcfTypeCode;
	}

	public void setVcfTypeCode(String vcfTypeCode) {
		VcfTypeCode = vcfTypeCode;
	}

	public String getVcfTypeName() {
		return VcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		VcfTypeName = vcfTypeName;
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

	public Integer getDec() {
		return dec;
	}

	public void setDec(Integer dec) {
		this.dec = dec;
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

	public List<String> getNodeCodes() {
		return nodeCodes;
	}

	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
	}

	public List<String> getMtrlCodes() {
		return mtrlCodes;
	}

	public void setMtrlCodes(List<String> mtrlCodes) {
		this.mtrlCodes = mtrlCodes;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeAbbrName() {
		return nodeAbbrName;
	}

	public void setNodeAbbrName(String nodeAbbrName) {
		this.nodeAbbrName = nodeAbbrName;
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

	public String getMtrlAbbrName() {
		return mtrlAbbrName;
	}

	public void setMtrlAbbrName(String mtrlAbbrName) {
		this.mtrlAbbrName = mtrlAbbrName;
	}

	public String getNodeTypeCode() {
		return nodeTypeCode;
	}

	public void setNodeTypeCode(String nodeTypeCode) {
		this.nodeTypeCode = nodeTypeCode;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
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
