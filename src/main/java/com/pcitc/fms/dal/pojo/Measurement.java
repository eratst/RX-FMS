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

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_MEASINDEX")
public class Measurement implements Serializable {

	private static final long serialVersionUID = 1L;
	// 度量指标ID
	@Id
	@Column(name = "MEASINDEX_ID")
	@SpecialResource(name="a.idxId")
	private Long idxId;
	// 度量指标编码
	@RegionMember
	@Column(name = "MEASINDEX_CODE")
	@SpecialResource(name="a.idxCode")
	private String idxCode;
	// 度量指标名称
	@Column(name = "MEASINDEX_NAME")
	@SpecialResource(name="a.idxName")
	private String idxName;
	// 度量指标简称
	@Column(name = "MEASINDEX_ALIAS")
	@SpecialResource(name="a.idxAlias")
	private String idxAlias;
	// 度量指标类型
	@Column(name = "IDXTYPE_ID")
	@SpecialResource(name="a.idxTypeId")
	private Long idxTypeId;
	
	// 指标类型（用于显示）
	@Transient
	@SpecialResource(name="b.idxTypeCode")
	private String idxTypeCode;
	
	// 指标类型（用于显示）
	@Transient
	@SpecialResource(name="b.idxTypeName")
	private String idxTypeName;
	// 节点ID
	@Column(name = "NODE_ID")
	@SpecialResource(name="a.nodeId")
	private Long nodeId;
	
	// 节点code（用于显示）
	@Transient
	@SpecialResource(name="c.nodeCode")
	private String nodeCode;
	
	// 节点名称（用于显示）
	@Transient
	@SpecialResource(name="c.nodeName")
	private String nodeName;
	// 节点简称（用于显示）
	@Transient
	@SpecialResource(name="c.nodeAlias")
	private String nodeAlias;
	
	// 量纲ID
	@Column(name = "DIMENSION_ID")
	@SpecialResource(name="a.dimensionId")
	private Long dimensionId;
	
	@Transient
	@SpecialResource(name="e.dimensionCode")
	private String dimensionCode;
	
	@Transient
	@SpecialResource(name="e.dimensionName")
	private String dimensionName;
	
	@Transient
	@SpecialResource(name="e.dimensionAlias")
	private String dimensionAlias;
	// 度量公式
	@Column(name = "MEASINDEX_FORMULA")
	@SpecialResource(name="a.idxFormula")
	private String idxFormula;
	// 状态
	@Column(name = "INUSE")
	@SpecialResource(name="a.inUse")
	private Integer inUse;
	
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
	
	// 物料ID
	@Column(name = "SORT_NUM")
	@SpecialResource(name="a.sortNum")
	private Integer sortNum;
	// 描述
	@Column(name = "DES")
	private String des;
	
	// s8000
	@Column(name = "SOURCE_DATA_TYPE")
	@SpecialResource(name="a.sourceDataType")
	private String sourceDataType;
	
	@Column(name = "EXCHANGE_RATE")
	@SpecialResource(name="a.exchangeRate")
	private String exchangeRate;
	// 节点类型（用于显示）
	@Transient
	private String nodeTypeName;
	
	@Transient
	private String nodeTypeCode;
	
	@Transient
	@SpecialResource(name="area.areaCode")
	private String areaCode;
	
	@Transient
	@SpecialResource(name="area.name")
	private String areaName;
	
	@Transient
	@SpecialResource(name="area.shortName")
	private String areaAlias;
	
	public Measurement() {
		// TODO Auto-generated constructor stub
	}

	public Measurement(Long idxId, String idxCode, String idxName, String idxAlias, Long idxTypeId, String idxTypeCode,
			String idxTypeName, Long nodeId, String nodeCode, String nodeName, String nodeAlias, Long dimensionId,
			String dimensionCode, String dimensionName, String dimensionAlias, String idxFormula, Integer inUse,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
			Integer sortNum, String des,String sourceDataType,String exchangeRate,String areaCode,String areaName,String areaAlias,
			String nodeTypeCode,String nodeTypeName) {
		super();
		this.idxId = idxId;
		this.idxCode = idxCode;
		this.idxName = idxName;
		this.idxAlias = idxAlias;
		this.idxTypeId = idxTypeId;
		this.idxTypeCode = idxTypeCode;
		this.idxTypeName = idxTypeName;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.dimensionId = dimensionId;
		this.dimensionCode = dimensionCode;
		this.dimensionName = dimensionName;
		this.dimensionAlias = dimensionAlias;
		this.idxFormula = idxFormula;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.des = des;
		this.sourceDataType=sourceDataType;
		this.exchangeRate=exchangeRate;
		this.areaName=areaName;
		this.areaAlias=areaAlias;
		this.areaCode=areaCode;
		this.nodeTypeCode=nodeTypeCode;
		this.nodeTypeName = nodeTypeName;
	}
	
	
	
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

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public Long getIdxId() {
		return idxId;
	}

	public void setIdxId(Long idxId) {
		this.idxId = idxId;
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

	public Long getIdxTypeId() {
		return idxTypeId;
	}

	public void setIdxTypeId(Long idxTypeId) {
		this.idxTypeId = idxTypeId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getIdxTypeCode() {
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

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getIdxFormula() {
		return idxFormula;
	}

	public void setIdxFormula(String idxFormula) {
		this.idxFormula = idxFormula;
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

	public String getSourceDataType() {
		return sourceDataType;
	}

	public void setSourceDataType(String sourceDataType) {
		this.sourceDataType = sourceDataType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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




}
