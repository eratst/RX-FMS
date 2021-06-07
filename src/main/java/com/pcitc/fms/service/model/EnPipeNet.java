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
public class EnPipeNet extends BaseResRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResourceMember(InTemplate = false)
    private Long netId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "能源管网编码")
    @ResourceMember(InQueries = "condition", Name = "netCode")
    private String netCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 4000, Explain = "能源管网名称")
    @ResourceMember(InQueries = "condition", Name = "netName")
    private String netName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 4000, Explain = "能源管网简称")
    /*@ResourceMember(InQueries = "condition", Name = "netAlias")*/
    private String netAlias;

    //@CheckField(CheckName = CheckNameType.CODEMAYBENULL, StrLength = 50, Explain = "上级能源管网编码")
    private String upperNetCode;

    @ResourceMember(InTemplate = false)
    private Long mtrlId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "物料编码")
    private String mtrlCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 200, Explain = "物料名称")
    @ResourceMember(InQueries = "condition", Name = "mtrlName")
    private String mtrlName;

    @ResourceMember(InTemplate = false)
    private Long orgId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "组织机构编码")
    private String orgCode;

    @ResourceMember(InQueries = "condition", Name = "orgName")
    private String orgName;

    @ResourceMember(InQueries = "condition", Name = "orgAlias")
    /*@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "组织机构简称")*/
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

    @ResourceMember(OnlyQuery = true)
    private Long bizId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;

    @ResourceMember(OnlyQuery = true)
    private Long rentId;

    @CheckField(CheckName = CheckNameType.CODE, AllowNull = true)
    @ResourceMember(InTemplate = false)
    private String rentCode;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getNetId() {
        return netId;
    }

    public void setNetId(Long netId) {
        this.netId = netId;
    }

    public String getNetCode() {
        return netCode;
    }

    public void setNetCode(String netCode) {
        this.netCode = netCode;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getNetAlias() {
        return netAlias;
    }

    public void setNetAlias(String netAlias) {
        this.netAlias = netAlias;
    }

    public String getUpperNetCode() {
        return upperNetCode;
    }

    public void setUpperNetCode(String upperNetCode) {
        this.upperNetCode = upperNetCode;
    }

    public Long getMtrlId() {
        return mtrlId;
    }

    public void setMtrlId(Long mtrlId) {
        this.mtrlId = mtrlId;
    }

    public String getMtrlCode() {
        return mtrlCode;
    }

    public void setMtrlCode(String mtrlCode) {
        this.mtrlCode = mtrlCode;
    }

    public String getMtrlName() {
        return mtrlName;
    }

    public void setMtrlName(String mtrlName) {
        this.mtrlName = mtrlName;
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
