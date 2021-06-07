package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_ENPIPENET")
public class EnPipeNet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NET_ID")
    private Long netId;

    @Column(name = "NET_CODE")
    private String netCode;

    @Column(name = "NET_NAME")
    private String netName;

    @Column(name = "NET_ALIAS")
    private String netAlias;

    @Column(name = "UPPER_NET_CODE")
    private String upperNetCode;

    @Column(name = "MTRL_ID")
    private Long mtrlId;

    @Transient
    private String mtrlCode;

    @Transient
    private String mtrlName;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Transient
    private String orgCode;

    @Transient
    private String orgName;

    @Transient
    private String orgAlias;

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

    @Column(name = "INUSE")
    private Integer dataStatus;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "DES")
    private String des;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;

    @Column(name = "RENT_ID")
    private Long rentId;

    @Transient
    private String rentCode;

    public EnPipeNet() {
        super();
    }

    public EnPipeNet(Long netId, String netCode, String netName, String netAlias, String upperNetCode, Long mtrlId,
                     String mtrlCode, String mtrlName, Long orgId, String orgCode, String orgName, String orgAlias,
                     String crtUserId, String crtUserName, Date crtDate, String mntUserId, String mntUserName,
                     Date mntDate, Integer dataStatus, Integer sortNum, String des, Integer version, Long bizId,
                     String bizCode, Long rentId, String rentCode) {
        this.netId = netId;
        this.netCode = netCode;
        this.netName = netName;
        this.netAlias = netAlias;
        this.upperNetCode = upperNetCode;
        this.mtrlId = mtrlId;
        this.mtrlCode = mtrlCode;
        this.mtrlName = mtrlName;
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
        this.rentId = rentId;
        this.rentCode = rentCode;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
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
}
