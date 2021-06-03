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
public class UnitAreaRel extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 装置与装置界区关系ID
     */
    @ResourceMember(InTemplate = false)
    private Long unitAreaRelId;

    @ResourceMember(InTemplate = false)
    private Long areaId;

    /**
     * 装置编码
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装置编码")
    @ResourceMember(InQueries = "condition", Name = "areaCode")
    private String areaCode;

    /**
     * 装置名称
     */
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置名称")
    //@ResourceMember(InQueries = "condition", Name = "areaName")
    private String areaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "装置简称")
    @ResourceMember(InQueries = "condition", Name = "areaAlias")
    private String areaAlias;

    @ResourceMember(InTemplate = false)
    private Long unitAreaId;

    /**
     * 装置界区编码
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装置界区编码")
    @ResourceMember(InQueries = "condition", Name = "unitAreaCode")
    private String unitAreaCode;

    /**
     * 装置界区名称
     */
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置界区名称")
    //@ResourceMember(InQueries = "condition", Name = "unitAreaName")
    private String unitAreaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置界区简称")
    @ResourceMember(InQueries = "condition", Name = "unitAreaAlias")
    private String unitAreaAlias;

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
    @ResourceMember(InQueries = "condition", Name = "dataStatus")
    private Integer dataStatus;

    /**
     * 创建人ID
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "创建人编码")
    private String crtUserId;

    /**
     * 创建人名称
     */
    @CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
    private String crtUserName;

    /**
     * 创建时间
     */
    @CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
    private Date crtDate;

    /**
     * 最后维护人ID
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "最后维护人编码")
    private String mntUserId;

    /**
     * 最后维护人名称
     */
    @CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
    private String mntUserName;

    /**
     * 维护日期
     */
    @CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
    private Date mntDate;

    /**
     * 排序
     */
    @CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
    private Integer sortNum;

    /**
     * 乐观锁版本
     */
    @CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
    private Integer version;

    /**
     * 描述
     */
    @CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
    private String des;

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

    @ResourceMember(InTemplate = false)
    private Long bizId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    private String bizCode;

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否是工厂模型装置")
    private Integer ofFms;

    public Long getUnitAreaRelId() {
        return unitAreaRelId;
    }

    public void setUnitAreaRelId(Long unitAreaRelId) {
        this.unitAreaRelId = unitAreaRelId;
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

    public Long getUnitAreaId() {
        return unitAreaId;
    }

    public void setUnitAreaId(Long unitAreaId) {
        this.unitAreaId = unitAreaId;
    }

    public String getUnitAreaCode() {
        return unitAreaCode;
    }

    public void setUnitAreaCode(String unitAreaCode) {
        this.unitAreaCode = unitAreaCode;
    }

    public String getUnitAreaName() {
        return unitAreaName;
    }

    public void setUnitAreaName(String unitAreaName) {
        this.unitAreaName = unitAreaName;
    }

    public String getUnitAreaAlias() {
        return unitAreaAlias;
    }

    public void setUnitAreaAlias(String unitAreaAlias) {
        this.unitAreaAlias = unitAreaAlias;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public Integer getOfFms() {
        return ofFms;
    }

    public void setOfFms(Integer ofFms) {
        this.ofFms = ofFms;
    }
}
