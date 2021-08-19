package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "T_PM_TANK")
public class ManagementTank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NODE_ID")
    private Integer nodeId;

    @Column(name = "NODE_CODE")
    private String nodeCode;

    @Transient
    private String nodeName;

    @Transient
    private String nodeSname;

    @Column(name = "TANK_TYPE_ID")
    private Integer tankTypeId;

    @Transient
    private String tankTypeCode;

    @Transient
    private String tankTypeName;

    @Column(name = "TANK_HGT")
    private Double tankHgt;

    @Transient
    private String areaCode;

    @Transient
    private String areaName;

    @Transient
    private String areaSname;

    @Column(name = "MAX_TANK_HGT")
    private Double maxTankHgt;

    @Column(name = "MIN_TANK_HGT")
    private Double minTankHgt;

    @Column(name = "MAX_TANK_STOARGE")
    private Double maxTankStorage;

    @Column(name = "MIN_TANK_STOARGE")
    private Double minTankStorage;

    @Column(name = "TANK_TOTL_CUBA")
    private Double tankTotlCuba;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "FLT_PLAT_WGT")
    private Double fltPlatWgt;

    @Column(name = "FLT_PLAT_PERHGT")
    private Double fltPlatPerhgt;

    @Column(name = "FLT_TIP_LST")
    private Double fltTipLst;

    @Column(name = "HT_PRET_TANK")
    private Integer htPretTank;

    private String nodeLongitude;

    private String nodeLatitude;

    private String nodeAltitude;

    private Integer inUse;

    @Transient
    private String nodeLevel;

    @Transient
    private String nodeModel;

    public ManagementTank() {
        super();
    }

    public ManagementTank(Integer nodeId, String nodeCode, String nodeName, String nodeSname, String tankTypeCode,
                          String tankTypeName, Double tankHgt, String areaCode, String areaName, String areaSname, Double maxTankHgt,
                          Double minTankHgt, Double maxTankStorage, Double minTankStorage, Double tankTotlCuba, Integer sortNum,
                          Double fltPlatWgt, Double fltPlatPerhgt, Double fltTipLst, Integer htPretTank, String nodeLongitude,
                          String nodeLatitude, String nodeAltitude, Integer inUse, String nodeLevel, String nodeModel) {
        super();
        this.nodeId = nodeId;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeSname = nodeSname;
        this.tankTypeCode = tankTypeCode;
        this.tankTypeName = tankTypeName;
        this.tankHgt = tankHgt;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaSname = areaSname;
        this.maxTankHgt = maxTankHgt;
        this.minTankHgt = minTankHgt;
        this.maxTankStorage = maxTankStorage;
        this.minTankStorage = minTankStorage;
        this.tankTotlCuba = tankTotlCuba;
        this.sortNum = sortNum;
        this.fltPlatWgt = fltPlatWgt;
        this.fltPlatPerhgt = fltPlatPerhgt;
        this.fltTipLst = fltTipLst;
        this.htPretTank = htPretTank;
        this.nodeLongitude = nodeLongitude;
        this.nodeLatitude = nodeLatitude;
        this.nodeAltitude = nodeAltitude;
        this.inUse = inUse;
        this.nodeLevel = nodeLevel;
        this.nodeModel = nodeModel;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getNodeModel() {
        return nodeModel;
    }

    public void setNodeModel(String nodeModel) {
        this.nodeModel = nodeModel;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeSname() {
        return nodeSname;
    }

    public void setNodeSname(String nodeSname) {
        this.nodeSname = nodeSname;
    }

    public Integer getTankTypeId() {
        return tankTypeId;
    }

    public void setTankTypeId(Integer tankTypeId) {
        this.tankTypeId = tankTypeId;
    }

    public String getTankTypeCode() {
        return tankTypeCode;
    }

    public void setTankTypeCode(String tankTypeCode) {
        this.tankTypeCode = tankTypeCode;
    }

    public String getTankTypeName() {
        return tankTypeName;
    }

    public void setTankTypeName(String tankTypeName) {
        this.tankTypeName = tankTypeName;
    }

    public Double getTankHgt() {
        return tankHgt;
    }

    public void setTankHgt(Double tankHgt) {
        this.tankHgt = tankHgt;
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

    public String getAreaSname() {
        return areaSname;
    }

    public void setAreaSname(String areaSname) {
        this.areaSname = areaSname;
    }

    public Double getMaxTankHgt() {
        return maxTankHgt;
    }

    public void setMaxTankHgt(Double maxTankHgt) {
        this.maxTankHgt = maxTankHgt;
    }

    public Double getMinTankHgt() {
        return minTankHgt;
    }

    public void setMinTankHgt(Double minTankHgt) {
        this.minTankHgt = minTankHgt;
    }

    public Double getMaxTankStorage() {
        return maxTankStorage;
    }

    public void setMaxTankStorage(Double maxTankStorage) {
        this.maxTankStorage = maxTankStorage;
    }

    public Double getMinTankStorage() {
        return minTankStorage;
    }

    public void setMinTankStorage(Double minTankStorage) {
        this.minTankStorage = minTankStorage;
    }

    public Double getTankTotlCuba() {
        return tankTotlCuba;
    }

    public void setTankTotlCuba(Double tankTotlCuba) {
        this.tankTotlCuba = tankTotlCuba;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Double getFltPlatWgt() {
        return fltPlatWgt;
    }

    public void setFltPlatWgt(Double fltPlatWgt) {
        this.fltPlatWgt = fltPlatWgt;
    }

    public Double getFltPlatPerhgt() {
        return fltPlatPerhgt;
    }

    public void setFltPlatPerhgt(Double fltPlatPerhgt) {
        this.fltPlatPerhgt = fltPlatPerhgt;
    }

    public Double getFltTipLst() {
        return fltTipLst;
    }

    public void setFltTipLst(Double fltTipLst) {
        this.fltTipLst = fltTipLst;
    }

    public Integer getHtPretTank() {
        return htPretTank;
    }

    public void setHtPretTank(Integer htPretTank) {
        this.htPretTank = htPretTank;
    }

    public String getNodeLongitude() {
        return nodeLongitude;
    }

    public void setNodeLongitude(String nodeLongitude) {
        this.nodeLongitude = nodeLongitude;
    }

    public String getNodeLatitude() {
        return nodeLatitude;
    }

    public void setNodeLatitude(String nodeLatitude) {
        this.nodeLatitude = nodeLatitude;
    }

    public String getNodeAltitude() {
        return nodeAltitude;
    }

    public void setNodeAltitude(String nodeAltitude) {
        this.nodeAltitude = nodeAltitude;
    }

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

}
