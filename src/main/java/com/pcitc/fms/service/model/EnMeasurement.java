package com.pcitc.fms.service.model;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class EnMeasurement extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResourceMember(InTemplate = false)
    private Long enMeasindexId;

    @ResourceMember(InTemplate = false)
    private Long idxId;

    private Long magnification;

    private Long account;

    private Integer isVirtual;

    private Long upperLimit;

    private Long lowerLimit;

    private Integer isReliable;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "度量指标编码")
    @ResourceMember(InQueries = "condition", Name = "idxCode")
    private String idxCode;

    private String idxName;

    private String idxAlias;

    private String nodeCode;

    private String nodeName;

    private String nodeAlias;

    private String nodeTypeCode;

    private String nodeTypeName;

    private String areaCode;

    private String areaName;

    private String areaAlias;

    private String areaTypeCode;

    private String areaTypeName;

    private String orgCode;

    private String orgName;

    private String orgAlias;

    private String orgTypeCode;

    private String orgTypeName;

    private String exchangeRate;

    private String dimensionAlias;

    private String idxFormula;

    private String sourceDataType;

    private Integer inUse;

    private String idxTypeCode;

    private String idxTypeName;

    private String crtUserId;

    private String crtUserName;

    private Date crtDate;

    private String mntUserId;

    private String mntUserName;

    private Date mntDate;

    private String des;

    private Integer sortNum;

    private Integer version;

    //度量指标所属单元类型:区域为1,组织机构为2,节点为3
    @CheckField(CheckName = CheckNameType.ID, Explain = "度量指标所属单元类型")
    private Integer ofMeasindexType;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;

    @ResourceMember(OnlyQuery = true)
    private String rentCode;

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

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
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

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }
}
