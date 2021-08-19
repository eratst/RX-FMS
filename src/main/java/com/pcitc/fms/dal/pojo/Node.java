package com.pcitc.fms.dal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_NODE")
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NODE_ID")
    private Long nodeId;

    @Column(name = "NODE_CODE")
    private String nodeCode;

    @Column(name = "NODE_NAME")
    private String nodeName;

    @Column(name = "NODE_ALIAS")
    private String nodeAlias;

    @Column(name = "AREA_ID")
    private Long areaId;

    @Column(name = "NODE_LONGITUDE")
    private String nodeLongitude;

    @Column(name = "NODE_LATITUDE")
    private String nodeLatitude;

    @Column(name = "NODETYPE_ID")
    private Long nodeTypeId;

    @Column(name = "INUSE")
    private Integer inUse;

    @Column(name = "DES")
    private String des;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "NODE_ALTITUDE")
    private String nodeAltitude;

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

    @Transient
    private String nodeTypeCode;

    @Transient
    private String nodeTypeName;

    @Transient
    private String areaCode;

    @Transient
    private String areaName;

    @Transient
    private String areaAlias;

    @Transient
    private String orgAlias;

    @Column(name = "NODE_LEVEL")
    private String nodeLevel;

    @Column(name = "NODE_MODEL")
    private String nodeModel;

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

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
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

    public String getNodeAlias() {
        return nodeAlias;
    }

    public void setNodeAlias(String nodeAlias) {
        this.nodeAlias = nodeAlias;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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


    public Long getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Long nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getNodeAltitude() {
        return nodeAltitude;
    }

    public void setNodeAltitude(String nodeAltitude) {
        this.nodeAltitude = nodeAltitude;
    }

    public String getNodeTypeCode() {
        return nodeTypeCode;
    }

    public void setNodeTypeCode(String nodeTypeCode) {
        this.nodeTypeCode = nodeTypeCode;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
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

    public Node() {
        super();
    }

    public Node(Long nodeId, String nodeCode, String nodeName, String nodeAlias, Long areaId,
                String nodeLongitude, String nodeLatitude, Long nodeTypeId, Integer inUse, String des,
                Integer sortNum, Integer version, String nodeAltitude, String nodeTypeCode, String nodeTypeName,
                String areaCode, String areaName, String areaAlias, String crtUserId, String crtUserName,
                Date crtDate, String mntUserId, String mntUserName, Date mntDate, String orgAlias) {
        super();
        this.nodeId = nodeId;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeAlias = nodeAlias;
        this.areaId = areaId;
        this.nodeLongitude = nodeLongitude;
        this.nodeLatitude = nodeLatitude;
        this.nodeTypeId = nodeTypeId;
        this.inUse = inUse;
        this.des = des;
        this.sortNum = sortNum;
        this.version = version;
        this.nodeAltitude = nodeAltitude;
        this.nodeTypeCode = nodeTypeCode;
        this.nodeTypeName = nodeTypeName;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.crtUserId = crtUserId;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserId = mntUserId;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.orgAlias = orgAlias;
    }
}
