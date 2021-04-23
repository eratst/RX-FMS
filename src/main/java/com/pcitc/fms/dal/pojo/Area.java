package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "T_PM_AREA")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AREA_ID")
    private Integer areaId;

    @Column(name = "AREA_CODE")
    private String areaCode;

    @Column(name = "AREA_NAME")
    private String areaName;

    @Column(name = "AREA_ALIAS")
    private String areaAlias;

    @Column(name = "ORG_ID")
    private Integer orgId;

    @Transient
    private String orgCode;

    @Transient
    private String orgName;

    @Transient
    private String orgAlias;

    @Column(name = "AREATYPE_ID")
    private long areaTypeId;

    @Transient
    private String areaTypeCode;

    @Transient
    private String areaTypeName;

    @Column(name = "DATA_STATUS")
    private Integer inUse;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "DES")
    private String des;

    public Area() {
        super();
    }

    public Area(Integer areaId, String areaCode, String areaName, String areaAlias, Integer orgId, String orgCode,
                String orgName, String orgAlias, Long areaTypeId, String areaTypeCode, String areaTypeName,
                Integer inUse, Integer sortNum, String des) {
        super();
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgAlias = orgAlias;
        this.areaTypeId = areaTypeId;
        this.areaTypeCode = areaTypeCode;
        this.areaTypeName = areaTypeName;
        this.inUse = inUse;
        this.sortNum = sortNum;
        this.des = des;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
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

    public Long getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(Integer areaTypeId) {
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

}
