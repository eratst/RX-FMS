package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_ENMEASINDEX")
public class EnMeasurement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能源度量指标
     */
    @Id
    @Column(name = "ENMEASINDEX_ID")
    private Long enMeasindexId;

    /**
     * 度量指标Id
     */
    @Column(name = "MEASINDEX_ID")
    private Long idxId;

    /**
     * 倍率
     */
    @Column(name = "MAGNIFICATION")
    private Long magnification;

    /**
     * 核算仪表标识
     */
    @Column(name = "ACCOUNT")
    private Integer account;

    /**
     * 虚实标识
     */
    @Column(name = "ISVIRTUAL")
    private Integer isVirtual;

    /**
     * 量程上限
     */
    @Column(name = "UPPERLIMIT")
    private Long upperLimit;

    /**
     * 量程下限
     */
    @Column(name = "LOWERLIMIT")
    private Long lowerLimit;

    /**
     * 数采是否可靠
     */
    @Column(name = "ISRELIABLE")
    private Integer isReliable;

    /**
     * 度量指标编码
     */
    @Transient
    private String idxCode;

    /**
     * 度量指标名称
     */
    @Transient
    private String idxName;

    /**
     * 度量指标简称
     */
    @Transient
    private String idxAlias;

    /**
     * 节点ID
     */
    @Transient
    private Long nodeId;

    /**
     * 节点编码
     */
    @Transient
    private String nodeCode;

    /**
     * 节点名称
     */
    @Transient
    private String nodeName;

    /**
     * 节点简称
     */
    @Transient
    private String nodeAlias;

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

    @Transient
    private Long areaId;

    /**
     * 区域编码
     */
    @Transient
    private String areaCode;

    /**
     * 区域名称
     */
    @Transient
    private String areaName;

    /**
     * 区域简称
     */
    @Transient
    private String areaAlias;

    /**
     * 区域类型编码
     */
    @Transient
    private String areaTypeCode;

    /**
     * 区域类型名称
     */
    @Transient
    private String areaTypeName;

    @Transient
    private Long orgId;

    /**
     * 组织机构编码
     */
    @Transient
    private String orgCode;

    /**
     * 组织机构名称
     */
    @Transient
    private String orgName;

    /**
     * 组织机构简称
     */
    @Transient
    private String orgAlias;

    /**
     * 组织机构类型编码
     */
    @Transient
    private String orgTypeCode;

    /**
     * 组织机构类型名称
     */
    @Transient
    private String orgTypeName;

    /**
     * 指标类型编码
     */
    @Transient
    private String idxTypeCode;

    /**
     * 指标类型名称
     */
    @Transient
    private String idxTypeName;

    /**
     * 量纲调整系数
     */
    @Transient
    private String exchangeRate;

    /**
     * 量纲简称
     */
    @Transient
    private String dimensionAlias;

    /**
     * 度量公式
     */
    @Transient
    private String idxFormula;

    /**
     * 源数据类型 SG8000,RTDB
     */
    @Transient
    private String sourceDataType;

    /**
     * 是否启用
     */
    @Transient
    private Integer inUse;

    /**
     * 创建人Id
     */
    @Transient
    private String crtUserId;

    /**
     * 创建人名称
     */
    @Transient
    private String crtUserName;

    /**
     * 创建时间
     */
    @Transient
    private Date crtDate;

    /**
     * 最后维护人Id
     */
    @Transient
    private String mntUserId;

    /**
     * 最后维护人名称
     */
    @Transient
    private String mntUserName;

    /**
     * 维护时间
     */
    @Transient
    private Date mntDate;

    /**
     * 描述
     */
    @Transient
    private String des;

    /**
     * 排序
     */
    @Transient
    private Integer sortNum;

    /**
     * 乐观锁版本
     */
    @Transient
    private Integer version;

    @Transient
    private Integer ofMeasindexType;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;

    public EnMeasurement() {
        super();
    }

    public EnMeasurement(Long enMeasindexId, Long idxId, Long magnification, Integer account, Integer isVirtual, Long upperLimit,
                         Long lowerLimit, Integer isReliable, String idxCode, String idxName, String idxAlias, Long nodeId,
                         String nodeCode, String nodeName, String nodeAlias, String nodeTypeCode, String nodeTypeName,
                         Long areaId, String areaCode, String areaName, String areaAlias, String areaTypeCode, String areaTypeName,
                         Long orgId, String orgCode, String orgName, String orgAlias, String orgTypeCode, String orgTypeName,
                         String idxTypeCode, String idxTypeName, String exchangeRate, String dimensionAlias, String idxFormula,
                         String sourceDataType, Integer inUse, String crtUserId, String crtUserName, Date crtDate, String mntUserId,
                         String mntUserName, Date mntDate, String des, Integer sortNum, Integer version, Integer ofMeasindexType,
                         Long bizId, String bizCode) {
        this.enMeasindexId = enMeasindexId;
        this.idxId = idxId;
        this.magnification = magnification;
        this.account = account;
        this.isVirtual = isVirtual;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.isReliable = isReliable;
        this.idxCode = idxCode;
        this.idxName = idxName;
        this.idxAlias = idxAlias;
        this.nodeId = nodeId;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeAlias = nodeAlias;
        this.nodeTypeCode = nodeTypeCode;
        this.nodeTypeName = nodeTypeName;
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.areaTypeCode = areaTypeCode;
        this.areaTypeName = areaTypeName;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgAlias = orgAlias;
        this.orgTypeCode = orgTypeCode;
        this.orgTypeName = orgTypeName;
        this.idxTypeCode = idxTypeCode;
        this.idxTypeName = idxTypeName;
        this.exchangeRate = exchangeRate;
        this.dimensionAlias = dimensionAlias;
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
        this.bizId = bizId;
        this.bizCode = bizCode;
    }

    public Long getEnMeasindexId() {
        return enMeasindexId;
    }

    public void setEnMeasindexId(Long enMeasindexId) {
        this.enMeasindexId = enMeasindexId;
    }

    public Long getIdxId() {
        return idxId;
    }

    public void setIdxId(Long idxId) {
        this.idxId = idxId;
    }

    public Long getMagnification() {
        return magnification;
    }

    public void setMagnification(Long magnification) {
        this.magnification = magnification;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Long getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Long upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getIsReliable() {
        return isReliable;
    }

    public void setIsReliable(Integer isReliable) {
        this.isReliable = isReliable;
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

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
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

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
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

    public Integer getOfMeasindexType() {
        return ofMeasindexType;
    }

    public void setOfMeasindexType(Integer ofMeasindexType) {
        this.ofMeasindexType = ofMeasindexType;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}
