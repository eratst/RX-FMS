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
public class UnitArea extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResourceMember(InTemplate = false)
    private Long unitAreaId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装置界区编码")
    @ResourceMember(InQueries = "condition", Name = "unitAreaCode")
    private String unitAreaCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "装置界区名称")
    @ResourceMember(InQueries = "condition", Name = "unitAreaName")
    private String unitAreaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "装置界区简称")
    /*@ResourceMember(InQueries = "condition", Name = "unitAreaAlias")*/
    private String unitAreaAlias;

    @ResourceMember(InTemplate = false)
    private Long orgId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "组织机构编码")
    private String orgCode;

    @ResourceMember(InQueries = "condition", Name = "orgName")
    private String orgName;
    /**
     * 组织机构简称
     */
    @ResourceMember(InQueries = "condition", Name = "orgAlias")
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "组织机构简称")
    private String orgAlias;

    /**
     * 创建人ID
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "创建人ID")
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
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "最后维护人ID")
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

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
    @ResourceMember(InQueries = "condition", Name = "dataStatus")
    private Integer dataStatus;

    /**
     * 排序
     */
    @CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
    /*@ResourceMember(InQueries = "condition", Name = "sortNum")*/
    private Integer sortNum;

    /**
     * 描述
     */
    @CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
    /*@ResourceMember(InQueries = "condition", Name = "des")*/
    private String des;

    /**
     * 乐观锁版本
     */
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
    private Long rentId;

    @CheckField(CheckName = CheckNameType.CODE, AllowNull = true)
    @ResourceMember(InTemplate = false)
    private String rentCode;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;

    @ResourceMember(OnlyQuery = true)
    private Long bizId;

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
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
}
