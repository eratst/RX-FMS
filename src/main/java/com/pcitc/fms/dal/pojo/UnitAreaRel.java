package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_UNITAREAREL")
public class UnitAreaRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 装置与装置界区关系ID
     */
    @Id
    @Column(name = "UNIT_AREA_REL_ID")
    private Long unitAreaRelId;

    /**
     * 装置ID
     */
    @Column(name = "AREA_ID")
    private Long areaId;

    @Transient
    private String areaCode;

    @Transient
    private String areaName;

    @Transient
    private String areaAlias;

    /**
     * 装置界区ID
     */
    @Column(name = "UNIT_AREA_ID")
    private Long unitAreaId;

    @Transient
    private String unitAreaCode;

    @Transient
    private String unitAreaName;

    @Transient
    private String unitAreaAlias;

    @Column(name = "INUSE")
    private Integer dataStatus;

    @Column(name = "CRTUSER_CODE")
    private String crtUserId;

    @Column(name = "CRTUSER_NAME")
    private String crtUserName;

    @Column(name = "CRTDATE")
    private Date crtDate;

    @Column(name = "MNTUSER_CODE")
    private String mntUserId;

    @Column(name = "MNTUSER_NAME")
    private String mntUserName;

    @Column(name = "MNTDATE")
    private Date mntDate;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "DES")
    private String des;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;

    @Column(name = "OFFMS")
    private Integer ofFms;

    public UnitAreaRel() {
        super();
    }

    public UnitAreaRel(Long unitAreaRelId, Long areaId, String areaCode, String areaName, String areaAlias, Long unitAreaId, String unitAreaCode, String unitAreaName, String unitAreaAlias, Integer dataStatus, String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer sortNum, Integer version, String des, Long bizId, String bizCode, Integer ofFms) {
        this.unitAreaRelId = unitAreaRelId;
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.unitAreaId = unitAreaId;
        this.unitAreaCode = unitAreaCode;
        this.unitAreaName = unitAreaName;
        this.unitAreaAlias = unitAreaAlias;
        this.dataStatus = dataStatus;
        this.crtUserId = crtUserId;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserId = mntUserId;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.sortNum = sortNum;
        this.version = version;
        this.des = des;
        this.bizId = bizId;
        this.bizCode = bizCode;
        this.ofFms = ofFms;
    }

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
