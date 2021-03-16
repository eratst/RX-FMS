package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;


public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mtrlId;
	// 物料编码
	private String mtrlCode;
	// 物料名称
	private String mtrlName;
	// 物料别名
	private String mtrlAlias;
	// 上级物料编码
	private String upperMtrlCode;
	private String upperMtrlName;
	private String upperMtrlAlias;
	// 物料类型ID
	private Long mtrlTypeId;
	// 物料类型编码（显示用）
	private String mtrlTypeCode;
	// 物料类型名称（显示用）
	private String mtrlTypeName;
	// VCF类别
	private Long vcfTypeId;
	// VCF表类型编码(显示用)
	private String vcfTypeCode;
	// VCF表类型名称(显示用)
	private String vcfTypeName;
	// 量纲ID
	private Long dimensionId;
	private String dimensionCode;

	private String dimensionName;
	private String dimensionAlias;
	// 创建人ID
	private String crtUserCode;
	// 创建人名称
	private String crtUserName;
	// 创建时间
	private Date crtDate;
	// 最后维护人ID
	private String mntUserCode;
	// 最后维护人名称
	private String mntUserName;
	// 维护日期
	private Date mntDate;
	// 数据精度
	private Integer dec;
	private Integer sortNum;
	// 状态
	private Integer inUse;
	private Long tankIdt;
	private String des;
	private Long nodeId;
	private String OrderBy;
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
	public String getDimensionAlias() {
		return dimensionAlias;
	}
	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
	}
	
	public Long getMtrlId() {
		return mtrlId;
	}
	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}
	public Long getMtrlTypeId() {
		return mtrlTypeId;
	}
	public void setMtrlTypeId(Long mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}
	
	public Long getVcfTypeId() {
		return vcfTypeId;
	}
	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
	}
	public Long getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
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
	public Long getTankIdt() {
		return tankIdt;
	}
	public void setTankIdt(Long tankIdt) {
		this.tankIdt = tankIdt;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getOrderBy() {
		return OrderBy;
	}
	public void setOrderBy(String orderBy) {
		OrderBy = orderBy;
	}
	
	

	
}
