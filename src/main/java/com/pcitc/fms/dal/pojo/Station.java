package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 站/厂
 */

@Entity
@Table(name = "T_PM_STATION")
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @Id
    @Column(name = "AREA_ID")
    private Long areaId;

    /**
     * 组织机构简称
     */
    @Transient
    private String orgAlias;

    /**
     * 组织机构编码
     */
    @Transient
    private String orgCode;

    /**
     * 上级区域（工厂）
     */
    @Transient
    private Long orgId;

    /**
     * 区域编码
     */
    @Column(name = "AREA_CODE")
    private String areaCode;

    /**
     * 区域名称
     */
    @Transient
    private String areaName;

    /**
     * 区域简称
     */
    @Transient
    private String areaAlias;

    /**
     * 区域类型ID
     */
    @Transient
    private Long areaTypeId;

    /**
     * 区域类型编码
     */
    @Transient
    private String areaTypeCode;

    /**
     * 区域类型名称
     */
    @Transient
    private String areaTypeName;

    /**
     * 状态
     */
    @Transient
    private Integer inUse;

    /**
     * 创建人id
     */
    @Transient
    private String crtUserId;

    /**
     * 创建人姓名
     */
    @Transient
    private String crtUserName;

    /**
     * 创建时间
     */
    @Transient
    private Date crtDate;

    /**
     * 最后维护人id
     */
    @Transient
    private String mntUserId;

    /**
     * 最后维护人姓名
     */
    @Transient
    private String mntUserName;

    /**
     * 维护日期
     */
    @Transient
    private Date mntDate;

    /**
     * 排序
     */
    @Column(name = "SORT_NUM")
    private Integer sortNum;

    /**
     * 乐观锁版本
     */
    @Column(name = "VERSION")
    private Integer version;

    @Transient
    private String orgAltitude;

    @Transient
    private String orgLongitude;

    @Transient
    private String orgLatitude;

    @Transient
    private String des;// 描述

    public Station() {
        super();
    }

    public Station(String orgCode, Long areaId, String orgAlias,  String areaCode, String areaName,
                   String areaAlias, Long areaTypeId, String areaTypeCode, String areaTypeName, Integer inUse,
                   String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate,
                   Integer sortNum, Integer version, String des, String orgAltitude, String orgLongitude, String orgLatitude) {
        super();
        this.orgCode = orgCode;
        this.areaId = areaId;
        this.orgAlias = orgAlias;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.areaTypeId = areaTypeId;
        this.areaTypeCode = areaTypeCode;
        this.areaTypeName = areaTypeName;
        this.inUse = inUse;
        this.crtUserId = crtUserId;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserId = mntUserId;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.sortNum = sortNum;
        this.version = version;
        this.des = des;
        this.orgAltitude = orgAltitude;
        this.orgLongitude = orgLongitude;
        this.orgLatitude = orgLatitude;
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public Long getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(Long areaTypeId) {
        this.areaTypeId = areaTypeId;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
