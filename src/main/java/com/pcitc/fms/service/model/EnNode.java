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
public class EnNode extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResourceMember(InTemplate = false)
    private Long enNodeId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "能源节点编码")
    @ResourceMember(InQueries = "condition", Name = "enNodeCode")
    private String enNodeCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "能源节点名称")
    @ResourceMember(InQueries = "condition", Name = "enNodeName")
    private String enNodeName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "能源节点简称")
    @ResourceMember(InQueries = "condition", Name = "enNodeAlias")
    private String enNodeAlias;

    @ResourceMember(InTemplate = false)
    private Long enNodeTypeId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "能源节点类型编码")
    @ResourceMember(InQueries = "condition", Name = "enNodeTypeCode")
    private String enNodeTypeCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "能源节点类型名称")
    @ResourceMember(InQueries = "condition", Name = "enNodeTypeName")
    private String enNodeTypeName;

    /**
     * 组织机构
     */
    @ResourceMember(InTemplate = false)
    private Long areaId;

    //@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务装置编码")
    @ResourceMember(InQueries = "condition", Name = "areaCode")
    private String areaCode;

    private String areaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "业务装置简称")
    @ResourceMember(InQueries = "condition", Name = "areaAlias")
    private String areaAlias;

    /**
     * 能源管网
     */
    @ResourceMember(InTemplate = false)
    private Long netId;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 200, Explain = "能源管网名称")
    private String netName;

    @CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "能源管网编码")
    private String netCode;

    /**
     * 业务域
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    //@ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;

    @ResourceMember(OnlyQuery = true)
    private Long bizId;

    /**
     * 计量公式
     */
    @CheckField(CheckName = CheckNameType.DES, StrLength = 4000, Explain = "计量公式")
    /*@ResourceMember(InQueries = "condition", Name = "formula")*/
    private String formula;

    /**
     * 创建信息
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "创建人ID")
    private String crtUserId;

    @CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
    private String crtUserName;

    @CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
    private Date crtDate;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "最后维护人ID")
    private String mntUserId;

    @CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
    private String mntUserName;

    @CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
    private Date mntDate;

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
    @ResourceMember(InQueries = "condition", Name = "dataStatus")
    private Integer dataStatus;

    @CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
    /*@ResourceMember(InQueries = "condition", Name = "sortNum")*/
    private Integer sortNum;

    @CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
    /*@ResourceMember(InQueries = "condition", Name = "des")*/
    private String des;

    @CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
    private Integer version;

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

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否是虚拟装置")
    private Integer markOfVirtual;

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public Long getEnNodeId() {
        return enNodeId;
    }

    public void setEnNodeId(Long enNodeId) {
        this.enNodeId = enNodeId;
    }

    public String getEnNodeCode() {
        return enNodeCode;
    }

    public void setEnNodeCode(String enNodeCode) {
        this.enNodeCode = enNodeCode;
    }

    public String getEnNodeName() {
        return enNodeName;
    }

    public void setEnNodeName(String enNodeName) {
        this.enNodeName = enNodeName;
    }

    public String getEnNodeAlias() {
        return enNodeAlias;
    }

    public void setEnNodeAlias(String enNodeAlias) {
        this.enNodeAlias = enNodeAlias;
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

    public Long getNetId() {
        return netId;
    }

    public void setNetId(Long netId) {
        this.netId = netId;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getNetCode() {
        return netCode;
    }

    public void setNetCode(String netCode) {
        this.netCode = netCode;
    }

    public String getFormula() {
        return formula;
    }

    public Long getEnNodeTypeId() {
        return enNodeTypeId;
    }

    public void setEnNodeTypeId(Long enNodeTypeId) {
        this.enNodeTypeId = enNodeTypeId;
    }

    public String getEnNodeTypeCode() {
        return enNodeTypeCode;
    }

    public void setEnNodeTypeCode(String enNodeTypeCode) {
        this.enNodeTypeCode = enNodeTypeCode;
    }

    public String getEnNodeTypeName() {
        return enNodeTypeName;
    }

    public void setEnNodeTypeName(String enNodeTypeName) {
        this.enNodeTypeName = enNodeTypeName;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getMarkOfVirtual() {
        return markOfVirtual;
    }

    public void setMarkOfVirtual(Integer markOfVirtual) {
        this.markOfVirtual = markOfVirtual;
    }
}
