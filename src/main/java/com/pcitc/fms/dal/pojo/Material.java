package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;

@Entity
@Table(name = "T_PM_MTRL")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;
	// 物料id
	@Id
	@Column(name = "MTRL_ID")
	@SpecialResource(name="a.mtrlId")
	private Long mtrlId;
	// 物料编码
	@RegionMember
	@Column(name = "MTRL_CODE")
	@SpecialResource(name="a.mtrlCode")
	private String mtrlCode;
	// 物料名称
	@Column(name = "MTRL_NAME")
	@SpecialResource(name="a.mtrlName")
	private String mtrlName;
	// 物料别名
	@Column(name = "MTRL_ALIAS")
	@SpecialResource(name="a.mtrlAlias")
	private String mtrlAlias;
	// 上级物料编码
	@Column(name = "UPPER_MTRL_CODE")
	@SpecialResource(name="a.upperMtrlCode")
	private String upperMtrlCode;
	@Transient
	private String upperMtrlName;
	@Transient
	private String upperMtrlAlias;
	// 物料类型ID
	@Column(name = "MTRLTYPE_ID")
	@SpecialResource(name="a.mtrlTypeId")
	private Long mtrlTypeId;
	// 物料类型编码（显示用）
	@Transient
	@SpecialResource(name="m.mtrlTypeCode")
	private String mtrlTypeCode;
	// 物料类型名称（显示用）
	@Transient
	@SpecialResource(name="m.mtrlTypeName")
	private String mtrlTypeName;
	// VCF类别
	@Column(name = "VCFTYPE_ID")
	@SpecialResource(name="a.vcfType")
	private Long vcfTypeId;
	// VCF表类型编码(显示用)
	
	@Transient
	@SpecialResource(name="vcf.vcfTypeCode")
	private String vcfTypeCode;
	// VCF表类型名称(显示用)
	@Transient
	@SpecialResource(name="vcf.vcfTypeName")
	private String vcfTypeName;
	// 量纲ID
	@Column(name = "DIMENSION_ID")
	@SpecialResource(name="a.dimensionId")
	private Long dimensionId;
	@Transient
	@SpecialResource(name="d.dimensionCode")
	private String dimensionCode;

	@Transient
	@SpecialResource(name="d.dimensionName")
	private String dimensionName;
	@Transient
	@SpecialResource(name="d.dimensionAlias")
	private String dimensionAlias;
	
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
	
	// 数据精度
	@Column(name = "DATA_DEC")
	@SpecialResource(name="a.dec")
	private Integer dec;
	
	@Column(name = "SORT_NUM")
	@SpecialResource(name="a.sortNum")
	private Integer sortNum;
	// 罐物料标识
	@Column(name = "TANK_IDT")
	@SpecialResource(name="a.tankIdt")
	private Integer tankIdt;
	// 状态
	@Column(name = "INUSE")
	@SpecialResource(name="a.inUse")
	private Integer inUse;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "NODE_ID")
	private Integer nodeId;
	@Transient
	
	private String OrderBy;

	private Integer count;
	
	
	
	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
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



	public Material() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Material(Long mtrlId, String mtrlCode, String mtrlName, String mtrlAlias, String upperMtrlCode,
			Long mtrlTypeId, String mtrlTypeCode, String mtrlTypeName, Long vcfTypeId, String vcfTypeCode,
			String vcfTypeName, Long dimensionId, String dimensionCode, String dimensionName, String dimensionAlias
			,String crtUserCode,String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer dec,
			Integer sortNum, Integer inUse,Integer tankIdt) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
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
		this.dimensionAlias = dimensionAlias;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.dec = dec;
		this.sortNum = sortNum;
		this.inUse = inUse;
		this.tankIdt = tankIdt;
	}
	//**********************************************
	public Material(Long mtrlId, String mtrlCode, String mtrlName, String mtrlAlias, String upperMtrlCode,String upperMtrlName,
			String upperMtrlAlias,Long mtrlTypeId, String mtrlTypeCode, String mtrlTypeName, Long vcfTypeId, String vcfTypeCode,
			String vcfTypeName, Long dimensionId, String dimensionCode, String dimensionName, String dimensionAlias
			,String crtUserCode,String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer dec,
			Integer sortNum, Integer inUse,Integer tankIdt) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
		this.upperMtrlCode = upperMtrlCode;
		this.upperMtrlName  = upperMtrlName;
		this.upperMtrlAlias = upperMtrlAlias;
		this.mtrlTypeId = mtrlTypeId;
		this.mtrlTypeCode = mtrlTypeCode;
		this.mtrlTypeName = mtrlTypeName;
		this.vcfTypeId = vcfTypeId;
		this.vcfTypeCode = vcfTypeCode;
		this.vcfTypeName = vcfTypeName;
		this.dimensionId = dimensionId;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.dimensionAlias = dimensionAlias;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.dec = dec;
		this.sortNum = sortNum;
		this.inUse = inUse;
		this.tankIdt = tankIdt;
	}

	public String getOrderBy() {
		return OrderBy;
	}



	public void setOrderBy(String orderBy) {
		OrderBy = orderBy;
	}



	public String getDimensionAlias() {
		return dimensionAlias;
	}



	public Integer getNodeId() {
		return nodeId;
	}



	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}



	public String getDes() {
		return des;
	}



	public void setDes(String des) {
		this.des = des;
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



	public Integer getTankIdt() {
		return tankIdt;
	}



	public void setTankIdt(Integer tankIdt) {
		this.tankIdt = tankIdt;
	}



	public void setDimensionAlias(String dimensionAlias) {
		this.dimensionAlias = dimensionAlias;
	}



	public String getMtrlTypeCode() {
		return mtrlTypeCode;
	}
	public void setMtrlTypeCode(String mtrlTypeCode) {
		this.mtrlTypeCode = mtrlTypeCode;
	}
	public String getVcfTypeCode() {
		return vcfTypeCode;
	}
	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
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

	public String getMtrlTypeName() {
		return mtrlTypeName;
	}

	public void setMtrlTypeName(String mtrlTypeName) {
		this.mtrlTypeName = mtrlTypeName;
	}

	public String getVcfTypeName() {
		return vcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
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


	public Integer getDec() {
		return dec;
	}

	public void setDec(Integer dec) {
		this.dec = dec;
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


}
