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
public class Measurement extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 度量指标ID
     */
    @ResourceMember(InTemplate = false)
    private Long idxId;

    @ResourceMember(InTemplate = false)
    private Long nodeId;

    @ResourceMember(InTemplate = false)
    private Long areaId;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 80, Explain = "区域简称")
    //@ResourceMember(InQueries = "condition", Name = "areaAlias")
    private String areaAlias;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "区域编码", AllowNull = true)
    @ResourceMember(InQueries = "condition", Name = "areaCode")
    private String areaCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "区域名称", AllowNull = true)
    @ResourceMember(InQueries = "condition", Name = "areaName")
    private String areaName;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 80, Explain = "区域类型编码")
    //@ResourceMember(InQueries = "condition", Name = "areaTypeCode")
    private String areaTypeCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "区域类型名称")
    //@ResourceMember(InQueries = "condition", Name = "areaTypeName")
    private String areaTypeName;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 80, Explain = "节点简称")
    //@ResourceMember(InQueries = "condition", Name = "nodeAlias")
    private String nodeAlias;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "节点编码", AllowNull = true)
    @ResourceMember(InQueries = "condition", Name = "nodeCode")
    private String nodeCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "节点名称", AllowNull = true)
    @ResourceMember(InQueries = "condition", Name = "nodeName")
    private String nodeName;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "节点类型编码")
    //@ResourceMember(InQueries = "condition", Name = "nodeTypeCode")
    private String nodeTypeCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "节点类型名称")
    /*@ResourceMember(InQueries = "condition", Name = "nodeTypeName")*/
    private String nodeTypeName;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "度量指标编码")
    @ResourceMember(InQueries = "condition", Name = "idxCode")
    private String idxCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "度量指标名称")
    @ResourceMember(InQueries = "condition", Name = "idxName")
    private String idxName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "度量指标简称")
    //@ResourceMember(InQueries = "condition", Name = "idxAlias")
    private String idxAlias;

    /**
     * 度量指标类型
     */
    @ResourceMember(InTemplate = false)
    private Long idxTypeId;

    //@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "指标类型编码")
    //@ResourceMember(InQueries = "condition", Name = "idxTypeCode")
    private String idxTypeCode;

    /**
     * 指标类型名称
     */
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "指标类型名称")
    @ResourceMember(InQueries = "condition", Name = "idxTypeName")
    private String idxTypeName;

    /**
     * 量纲Id
     */
    @ResourceMember(InTemplate = false)
    private Long dimensionId;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "量纲编码")
    private String dimensionCode;

    /**
     * 量纲调整系数
     */
    //@CheckField(CheckName = CheckNameType.DECIMALNOTNULL, Explain = "量纲调整系数")
    private Double exchangeRate;

    @ResourceMember(InTemplate = false)
    private String dimensionName;
    /**
     * 量纲简称
     */
    /*@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "量纲简称")
    @ResourceMember(InQueries = "condition", Name = "dimensionAlias")*/
    private String dimensionAlias;

    /**
     * 度量公式
     */
    @CheckField(CheckName = CheckNameType.SOURCEDATATYPE, Explain = "度量公式")
    //@ResourceMember(InQueries = "condition", Name = "idxFormula")
    private String idxFormula;

    /**
     * 源数据类型 SG8000,RTDB
     */
    /*@CheckField(CheckName = CheckNameType.CODE, StrLength = 80, Explain = "源数据类型")
    @ResourceMember(InQueries = "condition", Name = "sourceDataType")*/
    private String sourceDataType;

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
    @ResourceMember(InQueries = "condition", Name = "inUse")
    private Integer inUse;

    @CheckField(CheckName = CheckNameType.ID, StrLength = 50, Explain = "创建人Id")
    private String crtUserId;

    @CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
    private String crtUserName;

    @CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
    private Date crtDate;

    @CheckField(CheckName = CheckNameType.ID, StrLength = 50, Explain = "最后维护人Id")
    private String mntUserId;

    @CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
    private String mntUserName;

    @CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护时间")
    private Date mntDate;

    @CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
    private String des;

    @CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
    private Integer sortNum;

    @CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
    private Integer version;

    //度量指标所属单元类型:区域为1,组织机构为2,节点为3
    @CheckField(CheckName = CheckNameType.ID, Explain = "度量指标所属单元类型")
    @ResourceMember(InQueries = "condition", Name = "ofMeasindexType")
    private Integer ofMeasindexType;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    @ResourceMember(OnlyQuery = true)
    private String rentCode;

    @ResourceMember(OnlyQuery = true)
    private String bizCode;

    @ResourceMember(InTemplate = false)
    private Long orgId;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 80, Explain = "组织机构简称")
    //@ResourceMember(InQueries = "condition", Name = "orgAlias")
    private String orgAlias;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "组织机构编码", AllowNull = true)
    //@ResourceMember(InQueries = "condition", Name = "orgCode")
    private String orgCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "组织机构名称", AllowNull = true)
    @ResourceMember(InQueries = "condition", Name = "orgName")
    private String orgName;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 80, Explain = "组织机构类型编码")
    @ResourceMember(InQueries = "condition", Name = "orgTypeCode")
    private String orgTypeCode;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "组织机构类型名称")
    //@ResourceMember(InQueries = "condition", Name = "orgTypeName")
    private String orgTypeName;

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

    public String getIdxTypeCode() {
        return idxTypeCode;
    }

    public void setIdxTypeCode(String idxTypeCode) {
        this.idxTypeCode = idxTypeCode;
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

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
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

    public String getIdxTypeName() {
        return idxTypeName;
    }

    public void setIdxTypeName(String idxTypeName) {
        this.idxTypeName = idxTypeName;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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
}
