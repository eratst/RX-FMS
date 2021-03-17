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
public class MaterialUpper implements Serializable {
	private static final long serialVersionUID = 1L;
	// 物料id
	@Id
	@Column(name = "MTRL_ID")
	@SpecialResource(name="a.mtrlId")
	private Integer mtrlId;
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
	@Column(name = "MTRL_TYPE_ID")
	@SpecialResource(name="a.mtrlTypeId")
	private Integer mtrlTypeId;
	// 物料类型编码（显示用）
	@Transient
	@SpecialResource(name="m.mtrlTypeCode")
	private String mtrlTypeCode;
	// 物料类型名称（显示用）
	@Transient
	@SpecialResource(name="m.mtrlTypeName")
	private String mtrlTypeName;
	// VCF类别
	@Column(name = "VCF_TYPE")
	@SpecialResource(name="a.vcfType")
	private Integer vcfType;
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
	private Integer dimensionId;
	@Transient
	@SpecialResource(name="d.dimensionCode")
	private String dimensionCode;

	@Transient
	@SpecialResource(name="d.dimensionName")
	private String dimensionName;
	@Transient
	@SpecialResource(name="d.dimensionAlias")
	private String dimensionAlias;
	// 创建人ID
	@Column(name = "CRT_USER_ID")
	private String crtUserId;
	// 创建人名称
	@Column(name = "CRT_USER_NAME")
	private String crtUserName;
	// 创建时间
	@Column(name = "CRT_DATE", insertable = false)
	private Date crtDate;
	// 最后维护人ID
	@Column(name = "MNT_USER_ID")
	private String mntUserId;
	// 最后维护人名称
	@Column(name = "MNT_USER_NAME")
	private String mntUserName;
	// 维护日期
	@Column(name = "MNT_DATE", insertable = false)
	private Date mntDate;
	// 数据精度
	@Column(name = "DATA_DEC")
	@SpecialResource(name="a.dec")
	private Integer dec;
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	// 罐物料标识
	@Column(name = "TANK_IDT")
	@SpecialResource(name="a.tankIdt")
	private Integer tankIdt;
	// 状态
	@Column(name = "DATA_STATUS")
	@SpecialResource(name="a.dataStatus")
	private Integer dataStatus;
	@Column(name = "DES")
	private String des;
	@Column(name = "NODE_ID")
	private Integer nodeId;
	@Transient
	private String OrderBy;

	


	public MaterialUpper() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MaterialUpper(Integer mtrlId, String mtrlCode, String mtrlName,
					String mtrlAlias, String upperMtrlCode, Integer vcfType,
					Integer mtrlTypeId, Integer dec, Integer dimensionId,
					Integer tankIdt, Integer dataStatus, String crtUserId,
					String crtUserName, Date crtDate, String mntUserId,
					String mntUserName, Date mntDate, String des, Integer nodeId,
					String mtrlTypeName, String vcfTypeName, String nodeTypeName) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
		this.upperMtrlCode = upperMtrlCode;
		this.mtrlTypeId = mtrlTypeId;
		this.dec = dec;
		this.dimensionId = dimensionId;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.nodeId = nodeId;
		this.mtrlTypeName = mtrlTypeName;
		this.vcfTypeName = vcfTypeName;
//		this.nodeTypeName = nodeTypeName;
	}


	public MaterialUpper(Integer mtrlId, String mtrlCode, String mtrlName,
			String mtrlAlias, String upperMtrlCode, Integer vcfType,
			Integer mtrlTypeId, Integer dec, Integer dimensionId,
			Integer tankIdt, Integer dataStatus, String crtUserId,
			String crtUserName, Date crtDate, String mntUserId,
			String mntUserName, Date mntDate, String des,
			String mtrlTypeName, String vcfTypeName) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
		this.upperMtrlCode = upperMtrlCode;
		this.mtrlTypeId = mtrlTypeId;
		this.dec = dec;
		this.dimensionId = dimensionId;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.mtrlTypeName = mtrlTypeName;
		this.vcfTypeName = vcfTypeName;
	}



	public MaterialUpper(Integer mtrlId, String mtrlCode, String mtrlName,
			String mtrlAlias, String upperMtrlCode, Integer vcfType,
			Integer mtrlTypeId, Integer dec, Integer dimensionId,
			Integer tankIdt, Integer dataStatus, String crtUserId,
			String crtUserName, Date crtDate, String mntUserId,
			String mntUserName, Date mntDate, String des, Integer nodeId,
			String mtrlTypeName, String vcfTypeName, String nodeTypeName,String nodeCode) {
		super();
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAlias = mtrlAlias;
		this.upperMtrlCode = upperMtrlCode;
		this.mtrlTypeId = mtrlTypeId;
		this.dec = dec;
		this.dimensionId = dimensionId;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.nodeId = nodeId;
		this.mtrlTypeName = mtrlTypeName;
		this.vcfTypeName = vcfTypeName;
//		this.nodeTypeName = nodeTypeName;
//		this.nodeCode = nodeCode;
	}

	//**********************************************
	public MaterialUpper(Integer mtrlId, String mtrlCode, String mtrlName, String mtrlAlias, String upperMtrlCode,String upperMtrlName,
			String upperMtrlAlias,Integer mtrlTypeId, String mtrlTypeCode, String mtrlTypeName, Integer vcfType, String vcfTypeCode,
			String vcfTypeName, Integer dimensionId, String dimensionCode, String dimensionName, String dimensionAlias
			,String crtUserId,String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer dec,
			Integer sortNum, Integer dataStatus,Integer tankIdt) {
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
		this.vcfType = vcfType;
		this.vcfTypeCode = vcfTypeCode;
		this.vcfTypeName = vcfTypeName;
		this.dimensionId = dimensionId;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.dimensionAlias = dimensionAlias;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.dec = dec;
		this.sortNum = sortNum;
		this.dataStatus = dataStatus;
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



	public Integer getVcfType() {
		return vcfType;
	}



	public void setVcfType(Integer vcfType) {
		this.vcfType = vcfType;
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
	

	public Integer getDataStatus() {
		return dataStatus;
	}



	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public Integer getMtrlTypeId() {
		return mtrlTypeId;
	}

	public void setMtrlTypeId(Integer mtrlTypeId) {
		this.mtrlTypeId = mtrlTypeId;
	}

	public Integer getDec() {
		return dec;
	}

	public void setDec(Integer dec) {
		this.dec = dec;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}


	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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
