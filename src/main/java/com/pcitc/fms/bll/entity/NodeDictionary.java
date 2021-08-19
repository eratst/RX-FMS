package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class NodeDictionary implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    // 节点id
    private Long nodeId;
    // 节点编码
    private String nodeCode;
    // 节点名称
    private String nodeName;
    // 节点简称
    private String nodeAlias;
    // 区域ID
    private Long areaId;
    // 位置经度
    private String nodeLongitude;
    // 位置纬度
    private String nodeLatitude;
    // 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
    private Long nodeTypeId;
    // 状态
    private Integer inUse;
    // 位置海拔
    private String nodeAltitude;
    // 描述
    private String des;

    private String nodeTypeCode;

    private String nodeTypeName;

    private String areaCode;

    private String areaName;

    private String areaAlias;

    private Integer sortNum;

    private String crtUserCode; // 创建人ID

    private String crtUserName;// 创建人名称

    private Date crtDate;// 创建时间

    private String mntUserCode;// 最后维护人ID

    private String mntUserName;// 最后维护人名称

    private Date mntDate;//维护时间

    private String nodeLevel;

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

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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


    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
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

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
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

    public String getMntUserCode() {
        return mntUserCode;
    }

    public void setMntUserCode(String mntUserCode) {
        this.mntUserCode = mntUserCode;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
