package com.pcitc.fms.service.model;

import cc.aicode.e2e.annotation.ExcelEntity;
import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Equipment extends BaseResRep implements Serializable {
    private static final long serialVersionUID = 1L;
    // id
    @ResourceMember(OnlyQuery = true)
    private Long nodeId;
    // 节点编码 智能工厂设备节点标准编码
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
    @BuildLink
    private String nodeCode;

    // 节点名称
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    @ResourceMember(InQueries = "condition", Name = "nodeName")
    private String nodeName;

    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    @ResourceMember(InQueries = "condition")
    private String nodeAlias;

    // 区域编码（用于查询）
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 200)
    private String areaCode;

    private String areaName;

    private String areaAlias;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
    private String technicCode;

    // 设备工艺类型（用于显示）
    private String technicName;

    // 设备管理码
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 64)
    @ResourceMember(InQueries = "condition")
    private String equMgrCode;
    // 设备工艺类型
    @CheckField(CheckName = CheckNameType.ID)
    @ResourceMember(InQueries = "condition", Name = "technicId", OnlyQuery = true)
    private Long technicId;
    // 创建人ID
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 20)
    @ResourceMember(OnlyQuery = true)
    private String crtUserCode;
    // 创建人名称
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    @ResourceMember(OnlyQuery = true)
    private String crtUserName;
    // 创建时间
    @ResourceMember(OnlyQuery = true)
    private Date crtDate;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 200)
    private String orgCode;
    // 最后维护人ID
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 20)
    @ResourceMember(OnlyQuery = true)
    private String mntUserCode;
    // 最后维护人名称
    @CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
    @ResourceMember(OnlyQuery = true)
    private String mntUserName;
    // 维护日期
    @ResourceMember(OnlyQuery = true)
    private Date mntDate;

    // 区域ID
    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.ID)
    private Long areaId;
    // 位置经度
    @CheckField(CheckName = CheckNameType.DES, StrLength = 20)
    private String nodeLongitude;
    // 位置纬度
    @CheckField(CheckName = CheckNameType.DES, StrLength = 20)
    private String nodeLatitude;
    // 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
    @ResourceMember(OnlyQuery = true)
    private Integer nodeTypeId;
    // 状态
    @CheckField(CheckName = CheckNameType.ENABLED)
    @ResourceMember(InQueries = "condition", Name = "inUse")
    private Integer inUse;

    private Integer sortNum;

    // 描述
    @CheckField(CheckName = CheckNameType.DES, StrLength = 200)
    private String des;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
    private String areaTypeCode;


    // 位置海拔
    @CheckField(CheckName = CheckNameType.DES, StrLength = 20)
    private String nodeAltitude;

    // 节点简称

    // 节点类型（用于显示）
    @ResourceMember(OnlyQuery = true)
    private String nodeTypeName;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$orderby")
    @CheckField(CheckName = CheckNameType.ORDER)
    private String orderby;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
    @CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
    private Integer top;
    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
    @CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
    private Integer skip = 0;
    @ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$nodeCodes")
    @CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
    private List<String> nodeCodes;
    @ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$areaCodes")
    @CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
    private List<String> areaCodes;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE)
    private String rentCode;

    @ResourceMember(OnlyQuery = true)
    @CheckField(CheckName = CheckNameType.CODE)
    private String bizCode;

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

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public List<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(List<String> areaCodes) {
        this.areaCodes = areaCodes;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(String areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
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

    public Integer getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Integer nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
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

    public List<String> getNodeCodes() {
        return nodeCodes;
    }

    public void setNodeCodes(List<String> nodeCodes) {
        this.nodeCodes = nodeCodes;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


}
