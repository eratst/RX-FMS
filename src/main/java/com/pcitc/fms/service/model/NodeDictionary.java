package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import oracle.sql.DATE;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class NodeDictionary extends BaseResRep implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 节点id
    @ResourceMember(OnlyQuery = true)
    private Long nodeId;
    // 节点编码
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
    @BuildLink
    private String nodeCode;
    // 节点名称
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    @ResourceMember(InQueries = "condition",  Name = "nodeName")
    private String nodeName;
    // 节点简称
    @ResourceMember(InQueries = "condition",  Name = "nodeAlias")
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    private String nodeAlias;
    // 区域ID
    @ResourceMember(OnlyQuery = true)
    private Long areaId;
    // 位置经度
    private String nodeLongitude;
    // 位置纬度
    private String nodeLatitude;
    // 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
    @ResourceMember(OnlyQuery = true)
    private Long nodeTypeId;
    // 状态
    @ResourceMember(InQueries = "condition",  Name = "inUse")
    @CheckField(CheckName = CheckNameType.ENABLED, StrLength = 50)
    private Integer inUse;
    // 位置海拔
    private String nodeAltitude;

    @ResourceMember(InQueries = "condition",  Name = "nodeTypeCode")
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
    private String nodeTypeCode;

    private String nodeTypeName;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
    private String areaCode;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    private String areaName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    private String areaAlias;

    private String crtUserCode; // 创建人ID

    private String crtUserName;// 创建人名称

    private Date crtDate;// 创建时间

    private String mntUserCode;// 最后维护人ID

    private String mntUserName;// 最后维护人名称

    private Date mntDate;//维护时间

    // 描述
    private String des;

    private Integer sortNum;

    @CheckField(CheckName = CheckNameType.ORDER)
    @ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
    private String orderby;

    @CheckField(CheckName = CheckNameType.CODELIST, StrLength = 100)
    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
    private List<String> areaCodes;

    @CheckField(CheckName = CheckNameType.CODELIST, StrLength = 100)
    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
    private List<String> nodeCodes;

    @CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 100)
    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 100)
    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
    private Integer skip;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE)
    private String rentCode;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE)
    private String bizCode;

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
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

    public String getNodeTypeCode() {
        return nodeTypeCode;
    }

    public void setNodeTypeCode(String nodeTypeCode) {
        this.nodeTypeCode = nodeTypeCode;
    }

    public List<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(List<String> areaCodes) {
        this.areaCodes = areaCodes;
    }

    public List<String> getNodeCodes() {
        return nodeCodes;
    }

    public void setNodeCodes(List<String> nodeCodes) {
        this.nodeCodes = nodeCodes;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

}
