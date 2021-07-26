package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_MEASINDEX")
public class Measurement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 度量指标ID
     */
    @Id
    @Column(name = "MEASINDEX_ID")
    private Long idxId;

    /**
     * 节点ID
     */
    @Column(name = "NODE_ID")
    private Long nodeId;// 新增需要

    @Column(name = "AREA_ID")
    private Long areaId;// 新增需要

    @Transient
    private String areaAlias;

    @Transient
    private String areaCode;

    @Transient
    private String areaName;

    @Transient
    private String areaTypeCode;

    /**
     * 区域类型名称
     */
    @Transient
    private String areaTypeName;

    /**
     * 节点简称
     */
    @Transient
    private String nodeAlias;

    /**
     * 节点编码
     */
    @Transient
    private String nodeCode;

    @Transient
    private String nodeName;

    /**
     * 节点类型编码
     */
    @Transient
    private String nodeTypeCode;

    /**
     * 节点类型名称
     */
    @Transient
    private String nodeTypeName;

    /**
     * 度量指标编码
     */
    @Column(name = "MEASINDEX_CODE")
    private String idxCode;

    /**
     * 度量指标名称
     */
    @Column(name = "MEASINDEX_NAME")
    private String idxName;

    /**
     * 度量指标简称
     */
    @Column(name = "MEASINDEX_ALIAS")
    private String idxAlias;

    /**
     * 度量指标类型
     */
    @Column(name = "IDXTYPE_ID")
    private Long idxTypeId;// 新增需要

    /**
     * 指标类型名称
     */
    @Transient
    private String idxTypeName;

    /**
     * 量纲Id
     */
    @Column(name = "DIMENSION_ID")
    private Long dimensionId;

    @Transient
    private String dimensionCode;

    @Transient
    private String dimensionName;

    /**
     * 量纲简称
     */
    @Transient
    private String dimensionAlias;

    /**
     * 量纲调整系数
     */
    @Column(name = "EXCHANGE_RATE")
    private String exchangeRate;


    /**
     * 度量公式
     */
    @Column(name = "MEASINDEX_FORMULA")
    private String idxFormula;

    /**
     * 源数据类型 SG8000,RTDB
     */
    @Column(name = "SOURCE_DATA_TYPE")
    private String sourceDataType;

    /**
     * 是否启用
     */
    @Column(name = "INUSE")
    private Integer inUse;

    /**
     * 创建人Id
     */
    @Column(name = "CRTUSER_CODE")
    private String crtUserId;

    /**
     * 创建人名称
     */
    @Column(name = "CRTUSER_NAME")
    private String crtUserName;

    /**
     * 创建时间
     */
    @Column(name = "CRTDATE")
    private Date crtDate;

    /**
     * 最后维护人Id
     */
    @Column(name = "MNTUSER_CODE")
    private String mntUserId;

    /**
     * 最后维护人名称
     */
    @Column(name = "MNTUSER_NAME")
    private String mntUserName;

    /**
     * 维护时间
     */
    @Column(name = "MNTDATE")
    private Date mntDate;

    /**
     * 描述
     */
    @Column(name = "DES")
    private String des;

    /**
     * 排序
     */
    @Column(name = "SORT_NUM")
    private Integer sortNum;

    /**
     * 乐观锁版本
     */
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "OF_MEASINDEX_TYPE")
    private Integer ofMeasindexType;

    @Column(name = "ORG_ID")
    private Long orgId;// 新增需要

    @Transient
    private String orgAlias;

    @Transient
    private String orgCode;

    @Transient
    private String orgName;

    @Transient
    private String orgTypeCode;

    /**
     * 节点类型编码
     */
    @Transient
    private String orgTypeName;

    public Measurement() {
        super();
    }

    public Measurement(Long idxId, Long nodeId, String nodeAlias, String nodeCode, String nodeName, String nodeTypeCode,
                       String nodeTypeName, Long areaId, String areaAlias, String areaCode, String areaName,
                       String areaTypeCode, String areaTypeName, String idxCode, String idxName, String idxAlias,
                       Long idxTypeId, String idxTypeName, Long dimensionId, String dimensionCode,String dimensionName, String dimensionAlias, String exchangeRate,
                       String idxFormula, String sourceDataType, Integer inUse, String crtUserId, String crtUserName,
                       Date crtDate, String mntUserId, String mntUserName, Date mntDate, String des, Integer sortNum,
                       Integer version, Integer ofMeasindexType, Long orgId, String orgAlias, String orgCode, String orgName,
                       String orgTypeCode, String orgTypeName) {
        this.idxId = idxId;
        this.nodeId = nodeId;
        this.nodeAlias = nodeAlias;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeTypeCode = nodeTypeCode;
        this.nodeTypeName = nodeTypeName;
        this.areaId = areaId;
        this.areaAlias = areaAlias;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaTypeCode = areaTypeCode;
        this.areaTypeName = areaTypeName;
        this.idxCode = idxCode;
        this.idxName = idxName;
        this.idxAlias = idxAlias;
        this.idxTypeId = idxTypeId;
        this.idxTypeName = idxTypeName;
        this.dimensionId = dimensionId;
        this.dimensionCode = dimensionCode;
        this.dimensionName = dimensionName;
        this.dimensionAlias = dimensionAlias;
        this.exchangeRate = exchangeRate;
        this.exchangeRate = exchangeRate;
        this.idxFormula = idxFormula;
        this.sourceDataType = sourceDataType;
        this.inUse = inUse;
        this.crtUserId = crtUserId;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserId = mntUserId;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.des = des;
        this.sortNum = sortNum;
        this.version = version;
        this.ofMeasindexType = ofMeasindexType;
        this.orgId = orgId;
        this.orgAlias = orgAlias;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgTypeCode = orgTypeCode;
        this.orgTypeName = orgTypeName;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgTypeCode() {
        return orgTypeCode;
    }

    public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public String getIdxTypeName() {
        return idxTypeName;
    }

    public void setIdxTypeName(String idxTypeName) {
        this.idxTypeName = idxTypeName;
    }

    public Long getIdxId() {
        return idxId;
    }

    public void setIdxId(Long idxId) {
        this.idxId = idxId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getIdxTypeId() {
        return idxTypeId;
    }

    public void setIdxTypeId(Long idxTypeId) {
        this.idxTypeId = idxTypeId;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDimensionAlias() {
        return dimensionAlias;
    }

    public void setDimensionAlias(String dimensionAlias) {
        this.dimensionAlias = dimensionAlias;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
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

    public Integer getOfMeasindexType() {
        return ofMeasindexType;
    }

    public void setOfMeasindexType(Integer ofMeasindexType) {
        this.ofMeasindexType = ofMeasindexType;
    }

    public String getAreaAlias() {
        return areaAlias;
    }

    public void setAreaAlias(String areaAlias) {
        this.areaAlias = areaAlias;
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

    public String getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(String areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
    }

    public String getAreaTypeName() {
        return areaTypeName;
    }

    public void setAreaTypeName(String areaTypeName) {
        this.areaTypeName = areaTypeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
