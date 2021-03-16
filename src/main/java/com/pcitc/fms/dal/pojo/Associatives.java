package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_ASSOCIATIVE")
public class Associatives implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ASSOCIATIVE_ID")
	private Long associativeId;

	@Column(name = "NODE_ID")
	private Long nodeId;

	@SpecialResource(name="associatives.nodeCode")
	@Column(name = "NODE_CODE")
	private String nodeCode;

	@SpecialResource(name="associatives.nodeName")
	@Column(name = "NODE_NAME")
	private String nodeName;

	@SpecialResource(name="node.nodeAlias")
	@Transient
	private String nodeAbbrName;

	@Column(name = "MTRL_ID")
	private Long mtrlId;

	@SpecialResource(name="associatives.mtrlCode")
	@Column(name = "MTRL_CODE")
	private String mtrlCode;

	@SpecialResource(name="associatives.mtrlName")
	@Column(name = "MTRL_NAME")
	private String mtrlName;

	@SpecialResource(name="mtrl.mtrlAlias")
	@Transient
	private String mtrlAbbrName;
	
	@SpecialResource(name="mtrl.upperMtrlCode")
	@Transient
	private String upperMtrlCode;
	
	@Transient
	private String upperMtrlName;
	
	@Transient
	private String upperMtrlAbbrName;
	
	@SpecialResource(name="v.vcfTypeId")
	@Transient
	private Long VcfTypeId;
	
	@SpecialResource(name="v.vcfTypeCode")
	@Transient
	private String VcfTypeCode;
	
	@SpecialResource(name="v.vcfTypeName")
	@Transient
	private String VcfTypeName;
	
	@SpecialResource(name="mt.mtrlTypeId")
	@Transient
	private Long mtrlTypeId;
	
	@SpecialResource(name="mt.mtrlTypeCode")
	@Transient
	private String mtrlTypeCode;
	
	@SpecialResource(name="mt.mtrlTypeName")
	@Transient
	private String mtrlTypeName;
	
	@SpecialResource(name="mtrl.dec")
	@Transient
	private Integer dec;
	
	@SpecialResource(name="d.dimensionId")
	@Transient
	private Long dimensionId;
	
	@SpecialResource(name="d.dimensionCode")
	@Transient
	private String dimensionCode;
	
	@SpecialResource(name="d.dimensionName")
	@Transient
	private String dimensionName;
	
	@Column(name = "NODETYPE_ID")
	private Long nodeTypeId;

	@SpecialResource(name="nodeType.nodeTypeCode")
	@Transient
	private String nodeTypeCode;

	@SpecialResource(name="nodeType.nodeTypeName")
	@Transient
	private String nodeTypeName;

	@SpecialResource(name="associatives.inUse")
	@Column(name = "INUSE")
	private Integer inUse;

	@SpecialResource(name="associatives.sortNum")
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@SpecialResource(name="associatives.des")
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
	

	public Associatives() {
		super();
	}

	public Associatives(Long associativeId, Long nodeId, String nodeCode, String nodeName, String nodeAbbrName,
			Long mtrlId, String mtrlCode, String mtrlName, String mtrlAbbrName, String upperMtrlCode,
			String upperMtrlName, String upperMtrlAbbrName, Long VcfTypeId, String VcfTypeCode, String VcfTypeName,
			Long mtrlTypeId, String mtrlTypeCode, String mtrlTypeName, Integer dec, Long dimensionId,
			String dimensionCode, String dimensionName, Long nodeTypeId, String nodeTypeCode, String nodeTypeName,
			Integer inUse, Integer sortNum, String des) {
		super();
		this.associativeId = associativeId;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAbbrName = nodeAbbrName;
		this.mtrlId = mtrlId;
		this.mtrlCode = mtrlCode;
		this.mtrlName = mtrlName;
		this.mtrlAbbrName = mtrlAbbrName;
		this.upperMtrlCode = upperMtrlCode;
		this.upperMtrlName = upperMtrlName;
		this.upperMtrlAbbrName = upperMtrlAbbrName;
		this.VcfTypeId = VcfTypeId;
		this.VcfTypeCode = VcfTypeCode;
		this.VcfTypeName = VcfTypeName;
		this.mtrlTypeId = mtrlTypeId;
		this.mtrlTypeCode = mtrlTypeCode;
		this.mtrlTypeName = mtrlTypeName;
		this.dec = dec;
		this.dimensionId = dimensionId;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.nodeTypeId = nodeTypeId;
		this.nodeTypeCode = nodeTypeCode;
		this.nodeTypeName = nodeTypeName;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
	}
	
	
	
	public Long getAssociativeId() {
		return associativeId;
	}

	public void setAssociativeId(Long associativeId) {
		this.associativeId = associativeId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
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

	public Long getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
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

	public String getVcfTypeCode() {
		return VcfTypeCode;
	}

	public void setVcfTypeCode(String VcfTypeCode) {
		this.VcfTypeCode = VcfTypeCode;
	}

	public String getVcfTypeName() {
		return VcfTypeName;
	}

	public void setVcfTypeName(String VcfTypeName) {
		this.VcfTypeName = VcfTypeName;
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
