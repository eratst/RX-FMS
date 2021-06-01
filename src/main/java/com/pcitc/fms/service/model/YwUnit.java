package com.pcitc.fms.service.model;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class YwUnit extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResourceMember(InTemplate = false)
    private Long aredId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务装置编码")
    @ResourceMember(InQueries = "condition", Name = "areaCode")
    private String areaCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 4000, Explain = "业务装置名称")
    @ResourceMember(InQueries = "condition", Name = "areaName")
    private String areaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 4000, Explain = "业务装置简称")
    /*@ResourceMember(InQueries = "condition", Name = "areaAlias")*/
    private String areaAlias;

    /*@ResourceMember(InTemplate = false)
    private Long unitTypeId;*/

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置业务类型名称")
    private String unitTypeName;

    /*@ResourceMember(InTemplate = false)
    private Long technicId;*/

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置工艺类型名称")
    private String technicName;

    @ResourceMember(InTemplate = false)
    private Long orgId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "组织机构编码")
    private String orgCode;

    @ResourceMember(InQueries = "condition", Name = "orgName")
    private String orgName;

    @ResourceMember(InQueries = "condition", Name = "orgAlias")
    /*@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "组织机构简称")*/
    private String orgAlias;

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

    @ResourceMember(OnlyQuery = true)
    private Long bizId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;

    /*@ResourceMember(OnlyQuery = true)
    private Long capacityUnitId;*/

    private String capacityUnitName;

    private String capacity;

    //@CheckField(CheckName = CheckNameType.DecimalTwoDigitsZero, Explain = "资产原值")
    private BigDecimal initialAssetValue;

    //@CheckField(CheckName = CheckNameType.DecimalTwoDigitsZero, Explain = "资产净值")
    private BigDecimal netAssetValue;

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

    @ResourceMember(OnlyQuery = true)
    private String rentCode;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    public Long getAredId() {
        return aredId;
    }

    public void setAredId(Long aredId) {
        this.aredId = aredId;
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

    /*public Long getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Long unitTypeId) {
        this.unitTypeId = unitTypeId;
    }*/

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    /*public Long getTechnicId() {
        return technicId;
    }

    public void setTechnicId(Long technicId) {
        this.technicId = technicId;
    }*/

    public String getTechnicName() {
        return technicName;
    }

    public void setTechnicName(String technicName) {
        this.technicName = technicName;
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

    /*public Long getCapacityUnitId() {
        return capacityUnitId;
    }

    public void setCapacityUnitId(Long capacityUnitId) {
        this.capacityUnitId = capacityUnitId;
    }*/

    public String getCapacityUnitName() {
        return capacityUnitName;
    }

    public void setCapacityUnitName(String capacityUnitName) {
        this.capacityUnitName = capacityUnitName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getInitialAssetValue() {
        return initialAssetValue;
    }

    public void setInitialAssetValue(BigDecimal initialAssetValue) {
        this.initialAssetValue = initialAssetValue;
    }

    public BigDecimal getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
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

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
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
