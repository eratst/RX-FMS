package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_ENNODE")
public class EnNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EN_NODE_ID")
    private Long enNodeId;

    @Column(name = "EN_NODE_CODE")
    private String enNodeCode;

    @Column(name = "EN_NODE_NAME")
    private String enNodeName;

    @Column(name = "EN_NODE_ALIAS")
    private String enNodeAlias;

    @Column(name = "ENNODETYPE_ID")
    private Long enNodeTypeId;

    @Transient
    private String enNodeTypeCode;

    @Transient
    private String enNodeTypeName;

    @Column(name = "FORMULA")
    private String formula;

    @Column(name = "YWUNIT_ID")
    private Long areaId;

    @Transient
    private String areaCode;

    @Transient
    private String areaName;

    @Transient
    private String areaAlias;

    @Column(name = "BIZORGMAIN_ID")
    private Long bizId;

    @Transient
    private String bizCode;

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

    @Column(name = "NET_ID")
    private Long netId;

    @Transient
    private String netCode;

    @Transient
    private String netName;

    @Transient
    private String netAlias;

    @Column(name = "MARKOFVIRTUAL")
    private Integer markOfVirtual;


    public EnNode() {
        super();
    }

    public EnNode(Long enNodeId, String enNodeCode, String enNodeName, String enNodeAlias, Long enNodeTypeId,
                  String enNodeTypeCode, String enNodeTypeName, String formula, Long areaId, String areaCode,
                  String areaName, String areaAlias, Long bizId, String bizCode, String crtUserId, String crtUserName,
                  Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer dataStatus, Integer sortNum,
                  String des, Integer version, Integer markOfVirtual, Long netId, String netCode, String netName, String netAlias) {
        this.enNodeId = enNodeId;
        this.enNodeCode = enNodeCode;
        this.enNodeName = enNodeName;
        this.enNodeAlias = enNodeAlias;
        this.enNodeTypeId = enNodeTypeId;
        this.enNodeTypeCode = enNodeTypeCode;
        this.enNodeTypeName = enNodeTypeName;
        this.formula = formula;
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.bizId = bizId;
        this.bizCode = bizCode;
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
        this.markOfVirtual = markOfVirtual;
        this.netId = netId;
        this.netCode = netCode;
        this.netName = netName;
        this.netAlias = netAlias;

    }

    public Long getEnNodeId() {
        return enNodeId;
    }

    public void setEnNodeId(Long enNodeId) {
        this.enNodeId = enNodeId;
    }

    public String getEnNodeCode() {
        return enNodeCode;
    }

    public void setEnNodeCode(String enNodeCode) {
        this.enNodeCode = enNodeCode;
    }

    public String getEnNodeName() {
        return enNodeName;
    }

    public void setEnNodeName(String enNodeName) {
        this.enNodeName = enNodeName;
    }

    public String getEnNodeAlias() {
        return enNodeAlias;
    }

    public void setEnNodeAlias(String enNodeAlias) {
        this.enNodeAlias = enNodeAlias;
    }

    public Long getEnNodeTypeId() {
        return enNodeTypeId;
    }

    public void setEnNodeTypeId(Long enNodeTypeId) {
        this.enNodeTypeId = enNodeTypeId;
    }

    public String getEnNodeTypeCode() {
        return enNodeTypeCode;
    }

    public void setEnNodeTypeCode(String enNodeTypeCode) {
        this.enNodeTypeCode = enNodeTypeCode;
    }

    public String getEnNodeTypeName() {
        return enNodeTypeName;
    }

    public void setEnNodeTypeName(String enNodeTypeName) {
        this.enNodeTypeName = enNodeTypeName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    public Integer getMarkOfVirtual() {
        return markOfVirtual;
    }

    public void setMarkOfVirtual(Integer markOfVirtual) {
        this.markOfVirtual = markOfVirtual;
    }
}
