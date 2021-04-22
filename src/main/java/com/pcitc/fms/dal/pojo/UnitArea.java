package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yalin.zhao
 * @date 2021/4/21 9:22
 */
@Entity
@Table(name = "T_PM_UNITAREA")
public class UnitArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UNIT_AREA_ID")
    private Long unitAreaId;

    @Column(name = "UNIT_AREA_CODE")
    private String unitAreaCode;

    @Column(name = "UNIT_AREA_NAME")
    private String unitAreaName;

    @Column(name = "UNIT_AREA_ALIAS")
    private String unitAreaAlias;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Transient
    private String orgCode;

    @Transient
    private String orgName;

    @Transient
    private String orgAlias;

    /**
     * 创建人ID
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
     * 最后维护人ID
     */
    @Transient
    private String mntUserId;

    /**
     * 最后维护人名称
     */
    @Transient
    private String mntUserName;

    /**
     * 维护日期
     */
    @Transient
    private Date mntDate;

    @Column(name = "INUSE")
    private Integer dataStatus;

    /**
     * 排序
     */
    @Column(name = "SORT_NUM")
    private Integer sortNum;

    /**
     * 描述
     */
    @Column(name = "DES")
    private String des;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;


    public UnitArea() {
        super();
    }

    public UnitArea(Long unitAreaId, String unitAreaCode, String unitAreaName, String unitAreaAlias, Long orgId, String orgCode, String orgName, String orgAlias, String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer dataStatus, Integer sortNum, String des, Integer version, Long bizId, String bizCode) {
        this.unitAreaId = unitAreaId;
        this.unitAreaCode = unitAreaCode;
        this.unitAreaName = unitAreaName;
        this.unitAreaAlias = unitAreaAlias;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgAlias = orgAlias;
        this.crtUserId = crtUserId;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserId = mntUserId;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.dataStatus = dataStatus;
        this.sortNum = sortNum;
        this.des = des;
        this.version = version;
        this.bizId = bizId;
        this.bizCode = bizCode;
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
