package com.pcitc.fms.dal.pojo;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_EQUIPMENT")
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NODE_ID")
    @SpecialResource(name = "a.nodeId")
    private Long nodeId;
    // 节点编码 智能工厂设备节点标准编码
    @RegionMember
    @Column(name = "NODE_CODE")
    @SpecialResource(name = "a.nodeCode")
    private String nodeCode;
    // 设备管理码
    @Column(name = "EQUIPMENTMGR_CODE")
    @SpecialResource(name = "a.equMgrCode")
    private String equMgrCode;
    // 设备工艺类型
    @Column(name = "EQUTECHNIC_ID")
    @SpecialResource(name = "a.technicId")
    private Long technicId;
    @Transient
    private String crtUserCode; // 创建人ID

    @Transient
    private String crtUserName;// 创建人名称

    @Transient
    private Date crtDate;// 创建时间

    @Transient
    private String mntUserCode;// 最后维护人ID

    @Transient
    private String mntUserName;// 最后维护人名称

    @Transient
    private Date mntDate;//维护时间
    // 描述
    @Transient
    @SpecialResource(name = "b.des")
    private String des;
    // 区域ID
    @Transient
    @SpecialResource(name = "b.areaId")
    private Long areaId;
    // 位置经度
    @Transient
    @SpecialResource(name = "b.nodeLongitude")
    private String nodeLongitude;
    // 位置纬度
    @Transient
    @SpecialResource(name = "b.nodeLatitude")
    private String nodeLatitude;
    // 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
    @Transient
    @SpecialResource(name = "b.nodeTypeId")
    private Long nodeTypeId;
    // 状态
    @Transient
    @SpecialResource(name = "b.dataStatus")
    private Integer inUse;
    // 位置海拔
    @Transient
    @SpecialResource(name = "b.nodeAltitude")
    private String nodeAltitude;
    // 节点名称
    @Transient
    @SpecialResource(name = "b.nodeName")
    private String nodeName;
    // 节点简称
    @Transient
    @SpecialResource(name = "b.nodeAlias")
    private String nodeAlias;
    // 节点类型（用于显示）
    @Transient
    @SpecialResource(name = "c.nodeTypeName")
    private String nodeTypeName;
    // 设备工艺类型（用于显示）
    @Transient
    @SpecialResource(name = "d.technicName")
    private String technicName;
    // 区域编码（用于查询）
    @Transient
    @SpecialResource(name = "e.areaCode")
    private String areaCode;

    @Column(name = "SORT_NUM")
    @SpecialResource(name = "a.sortNum")
    private Integer sortNum;

    @Transient
    @SpecialResource(name = "e.name")
    private String areaName;

    @Transient
    @SpecialResource(name = "e.shortName")
    private String areaAlias;

    @Transient
    @SpecialResource(name = "d.technicCode")
    private String technicCode;

    @Transient
    @SpecialResource(name = "org.orgCode")
    private String orgCode;

    @Transient
    private String areaTypeCode;

    @Transient
    private String nodeLevel;

    @Transient
    private String nodeModel;

    public Equipment() {
    }

    public Equipment(Long nodeId, String nodeCode, String equMgrCode,
                     Long technicId, String crtUserCode, String crtUserName,
                     Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
                     String des, Long areaId, String nodeLongitude,
                     String nodeLatitude, Long nodeTypeId, Integer inUse,
                     String nodeAltitude, String nodeName, String nodeAlias,
                     String nodeTypeName, String technicName, Integer sortNum,
                     String areaName, String areaAlias, String technicCode,
                     String orgCode, String areaCode, String nodeLevel, String nodeModel) {
        super();
        this.nodeId = nodeId;
        this.nodeCode = nodeCode;
        this.equMgrCode = equMgrCode;
        this.technicId = technicId;
        this.crtUserCode = crtUserCode;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserCode = mntUserCode;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.des = des;
        this.areaId = areaId;
        this.nodeLongitude = nodeLongitude;
        this.nodeLatitude = nodeLatitude;
        this.nodeTypeId = nodeTypeId;
        this.inUse = inUse;
        this.nodeAltitude = nodeAltitude;
        this.nodeName = nodeName;
        this.nodeAlias = nodeAlias;
        this.nodeTypeName = nodeTypeName;
        this.technicName = technicName;
        this.sortNum = sortNum;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.technicCode = technicCode;
        this.orgCode = orgCode;
        this.areaCode = areaCode;
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

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getTechnicId() {
        return technicId;
    }

    public void setTechnicId(Long technicId) {
        this.technicId = technicId;
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
    }

    public String getMntUserCode() {
        return mntUserCode;
    }

    public void setMntUserCode(String mntUserCode) {
        this.mntUserCode = mntUserCode;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Long nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(String areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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

    public String getTechnicCode() {
        return technicCode;
    }

    public void setTechnicCode(String technicCode) {
        this.technicCode = technicCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }

    public String getTechnicName() {
        return technicName;
    }

    public void setTechnicName(String technicName) {
        this.technicName = technicName;
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

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

    public String getNodeAltitude() {
        return nodeAltitude;
    }

    public void setNodeAltitude(String nodeAltitude) {
        this.nodeAltitude = nodeAltitude;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getEquMgrCode() {
        return equMgrCode;
    }

    public void setEquMgrCode(String equMgrCode) {
        this.equMgrCode = equMgrCode;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
