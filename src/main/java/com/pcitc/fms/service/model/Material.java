package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
public class Material extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;
	// 物料id
	private Integer mtrlId;
	// 物料编码
	@ResourceMember(InQueries = "condition")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String mtrlCode;
	// 物料名称
	@ResourceMember(InQueries = "condition",Name = "mtrlName")
	@CheckField(CheckName = CheckNameType.CONDITION ,StrLength=50)
	private String mtrlName;
	// 物料别名
	@ResourceMember(InQueries = "condition",Name = "mtrlAlias")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String mtrlAlias;
	// 上级物料编码
	@CheckField(CheckName = CheckNameType.DES, StrLength = 20)
	@ResourceMember(InQueries = "condition",Name = "upperMtrlCode")
	private String upperMtrlCode;
	
	private String upperMtrlName;
	
	private String upperMtrlAlias;
	
	// 算法类别:罐量计算添加
	private Long vcfTypeId;
		// VCF表类型编码(显示用)
	private String vcfTypeCode;
		// VCF表类型名称(显示用)
	private String vcfTypeName;	
	// 物料类型编码（显示用）
	@ResourceMember( InQueries = "condition")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String mtrlTypeCode;
	// 物料类型名称（显示用）
	private String mtrlTypeName;
	
	private Long mtrlTypeId;
	
	// 数据精度
	private Integer dec;
	
	private String dimensionId;
	
	private String dimensionCode;

	private String dimensionName;
	
	private String dimensionAlias;
	
	
	// 状态
	@ResourceMember(InQueries = "condition",Name = "inUse")
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;

	private Integer sortNum;	
	
	private String des;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$mtrlCodes")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> mtrlCodes;
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip;
	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@CheckField(CheckName = CheckNameType.TREE)
	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$operType")
	private String operType;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$filterData")
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	private List<String> filterData;
	
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
	
	
	
	
	public Long getVcfTypeId() {
		return vcfTypeId;
	}
	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
	}
	public String getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
	public Integer getMtrlId() {
		return mtrlId;
	}
	public void setMtrlId(Integer mtrlId) {
		this.mtrlId = mtrlId;
	}
	public Long getMtrlTypeId() {
		return mtrlTypeId;
	}
	public void setMtrlTypeId(Long mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
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
	public String getUpperMtrlName() {
		return upperMtrlName;
	}
	public void setUpperMtrlName(String upperMtrlName) {
		this.upperMtrlName = upperMtrlName;
	}
	public String getUpperMtrlAlias() {
		return upperMtrlAlias;
	}
	public void setUpperMtrlAlias(String upperMtrlAlias) {
		this.upperMtrlAlias = upperMtrlAlias;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
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
	public String getMtrlAlias() {
		return mtrlAlias;
	}
	public void setMtrlAlias(String mtrlAlias) {
		this.mtrlAlias = mtrlAlias;
	}
	public String getUpperMtrlCode() {
		return upperMtrlCode;
	}
	public void setUpperMtrlCode(String upperMtrlCode) {
		this.upperMtrlCode = upperMtrlCode;
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
	
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	public String getDimensionAlias() {
		return dimensionAlias;
	}
	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}


	
}
