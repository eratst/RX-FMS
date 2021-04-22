package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_PM_YWUNIT")
public class YwUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "YWUNIT_ID")
    private Long ywUnitId;

    @Column(name = "YWUNIT_CODE")
    private String ywUnitCode;

    @Column(name = "YWUNIT_NAME")
    private String ywUnitName;

    @Column(name = "YWUNIT_ALIAS")
    private String ywUnitAlias;

    @Column(name = "UNITTYPE_ID")
    private Long unitTypeId;

    @Transient
    private String unitTypeName;

    @Column(name = "UNITTECHNIC_ID")
    private Long technicId;

    @Transient
    private String technicName;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Transient
    private String orgCode;

    @Transient
    private String orgName;

    @Transient
    private String orgAlias;

    @Transient
    private String crtUserId;

    @Transient
    private String crtUserName;

    @Transient
    private Date crtDate;

    @Transient
    private String mntUserId;

    @Transient
    private String mntUserName;

    @Transient
    private Date mntDate;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;

    @Column(name = "CAPACITYUNIT_ID")
    private Long capacityUnitId;

    @Transient
    private String capacityUnitName;

    @Column(name = "CAPACITY")
    private Long capacity;

    @Column(name = "INITIAL_ASSET_VALUE")
    private BigDecimal initialAssetValue;

    @Column(name = "NET_ASSET_VALUE")
    private BigDecimal netAssetValue;

    @Column(name = "INUSE")
    private Integer dataStatus;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "DES")
    private String des;

    @Column(name = "VERSION")
    private Integer version;

    public YwUnit() {
        super();
    }

    public YwUnit(Long ywUnitId, String ywUnitCode, String ywUnitName, String ywUnitAlias, Long unitTypeId, String unitTypeName, Long technicId, String technicName, Long orgId, String orgCode, String orgName, String orgAlias, String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName, Date mntDate, Long bizId, String bizCode, Long capacity, BigDecimal initialAssetValue, BigDecimal netAssetValue, Integer dataStatus, Integer sortNum, String des, Integer version, Long capacityUnitId, String capacityUnitName) {
        super();
        this.ywUnitId = ywUnitId;
        this.ywUnitCode = ywUnitCode;
        this.ywUnitName = ywUnitName;
        this.ywUnitAlias = ywUnitAlias;
        this.unitTypeId = unitTypeId;
        this.unitTypeName = unitTypeName;
        this.technicId = technicId;
        this.technicName = technicName;
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
        this.bizId = bizId;
        this.bizCode = bizCode;
        this.capacity = capacity;
        this.initialAssetValue = initialAssetValue;
        this.netAssetValue = netAssetValue;
        this.dataStatus = dataStatus;
        this.sortNum = sortNum;
        this.des = des;
        this.version = version;
        this.capacityUnitId = capacityUnitId;
        this.capacityUnitName = capacityUnitName;
    }

    public Long getYwUnitId() {
        return ywUnitId;
    }

    public void setYwUnitId(Long ywUnitId) {
        this.ywUnitId = ywUnitId;
    }

    public String getYwUnitCode() {
        return ywUnitCode;
    }

    public void setYwUnitCode(String ywUnitCode) {
        this.ywUnitCode = ywUnitCode;
    }

    public String getYwUnitName() {
        return ywUnitName;
    }

    public void setYwUnitName(String ywUnitName) {
        this.ywUnitName = ywUnitName;
    }

    public String getYwUnitAlias() {
        return ywUnitAlias;
    }

    public void setYwUnitAlias(String ywUnitAlias) {
        this.ywUnitAlias = ywUnitAlias;
    }

    public Long getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Long unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public Long getTechnicId() {
        return technicId;
    }

    public void setTechnicId(Long technicId) {
        this.technicId = technicId;
    }

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

    public Long getCapacityUnitId() {
        return capacityUnitId;
    }

    public void setCapacityUnitId(Long capacityUnitId) {
        this.capacityUnitId = capacityUnitId;
    }

    public String getCapacityUnitName() {
        return capacityUnitName;
    }

    public void setCapacityUnitName(String capacityUnitName) {
        this.capacityUnitName = capacityUnitName;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
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
}
