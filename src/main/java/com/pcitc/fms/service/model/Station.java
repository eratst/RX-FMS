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
@QueryContract(href = "", prompt = "查询", rel = "search")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Station extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @ResourceMember(InTemplate = false)
    private Long areaId;

    /**
     * 组织机构简称
     */
    @ResourceMember(InQueries = "condition", Name = "orgAlias")
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "组织机构简称")
    private String orgAlias;

    /**
     * 组织机构编码
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 80, Explain = "组织机构编码")
    private String orgCode;

    /**
     * 区域编码
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "区域编码")
    @ResourceMember(InQueries = "condition", Name = "areaCode")
    private String areaCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "区域名称")
    @ResourceMember(InQueries = "condition", Name = "areaName")
    private String areaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "区域简称")
    @ResourceMember(InQueries = "condition", Name = "areaAlias")
    private String areaAlias;

    @CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
    @ResourceMember(InTemplate = true, InQueries = "condition", Name = "inUse")
    private Integer inUse;

    /**
     * 创建人id
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "创建人ID")
    private String crtUserId;

    /**
     * 创建人姓名
     */
    @CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
    private String crtUserName;

    /**
     * 创建时间
     */
    @CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
    private Date crtDate;

    /**
     * 最后维护人id
     */
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "最后维护人ID")
    private String mntUserId;

    /**
     * 最后维护人姓名
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

    @CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
    @ResourceMember(InTemplate = true)
    private String des;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20)
    private String orgAltitude;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20)
    private String orgLongitude;

    @CheckField(CheckName = CheckNameType.NAMEMAYBENULL, StrLength = 20)
    private String orgLatitude;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    @ResourceMember(OnlyQuery = true)
    private String rentCode;

    @ResourceMember(OnlyQuery = true)
    private String bizCode;

    private List<String> orgCodeList;
    private Integer isRecursive;

    @CheckField(CheckName = CheckNameType.ORDER)
    @ResourceMember(OnlyQuery = true)
    private String orderby;

    public Station() {
        super();
    }

    public Station(String orgAlias, String areaCode, String areaName, String areaAlias, Integer inUse,
                   List<String> codeList, Integer top, Integer skip, String rentCode, String bizCode, Integer isRecursive, String orgCode) {
        super();
        this.orgAlias = orgAlias;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.inUse = inUse;
        this.codeList = codeList;
        this.top = top;
        this.skip = skip;
        this.rentCode = rentCode;
        this.bizCode = bizCode;
        this.isRecursive = isRecursive;
        this.orgCode = orgCode;
    }

    public String getOrgAltitude() {
        return orgAltitude;
    }

    public void setOrgAltitude(String orgAltitude) {
        this.orgAltitude = orgAltitude;
    }

    public String getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(String orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    public String getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(String orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public List<String> getOrgCodeList() {
        return orgCodeList;
    }

    public void setOrgCodeList(List<String> orgCodeList) {
        this.orgCodeList = orgCodeList;
    }

    public Integer getIsRecursive() {
        return isRecursive;
    }

    public void setIsRecursive(Integer isRecursive) {
        this.isRecursive = isRecursive;
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
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


    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
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


}
