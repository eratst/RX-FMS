package com.pcitc.fms.dal.pojo;

import com.pcitc.fms.common.annotation.SpecialResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PM_TANK")
public class Tank implements Serializable {
    /**
     * 罐
     */
    private static final long serialVersionUID = 1L;
    // 节点id
    @Id
    @Column(name = "NODE_ID")
    @SpecialResource(name = "a.nodeId")
    private Long nodeId;
    // 节点编码
    @Column(name = "NODE_CODE")
    @SpecialResource(name = "a.nodeCode")
    private String nodeCode;

    // 节点名称
    @Transient
    @SpecialResource(name = "b.nodeName")
    private String nodeName;


    // 节点简称
    @Transient
    @SpecialResource(name = "b.nodeAlias")
    private String nodeAlias;

    // 罐类型ID
    @SpecialResource(name = "a.tankTypeId")
    @Column(name = "TANKTYPE_ID")
    private Long tankTypeId;

    @Transient
    @SpecialResource(name = "d.tankTypeCode")
    private String tankTypeCode;

    // 罐类型（用于显示）
    @Transient
    @SpecialResource(name = "d.tankTypeName")
    private String tankTypeName;

    // 区域编码（用于显示）
    @Transient
    @SpecialResource(name = "e.areaCode")
    private String areaCode;

    // 区域名称（用于显示）
    @Transient
    @SpecialResource(name = "e.name")
    private String areaName;

    @Transient
    @SpecialResource(name = "org.orgCode")
    private String orgCode;

    // 区域名称（用于显示）
    @Transient
    @SpecialResource(name = "e.shortName")
    private String areaAlias;

    // 罐高
    @SpecialResource(name = "a.tankHgt")
    @Column(name = "TANK_HGT")
    private Double tankHgt;

    // 罐公称容积
    @SpecialResource(name = "a.tankTotlCuba")
    @Column(name = "TANK_TOTL_CUBA")
    private Double tankTotlCuba;

    /**
     * 浮盘重
     **/
    @SpecialResource(name = "a.fltPlatWgt")
    @Column(name = "FLT_PLAT_WGT")
    private Double fltPlatWgt;

    // 浮盘前高
    @SpecialResource(name = "a.fltPlatPerhgt")
    @Column(name = "FLT_PLAT_PERHGT")
    private Double fltPlatPerhgt;

    // 浮顶最低点
    @SpecialResource(name = "a.fltTipLst")
    @Column(name = "FLT_TIP_LST")
    private Double fltTipLst;

    // 罐安全高度
    @SpecialResource(name = "a.maxTankHgt")
    @Column(name = "MAX_TANK_HGT")
    private Double maxTankHgt;

    // 罐底高度
    @SpecialResource(name = "a.minTankHgt")
    @Column(name = "MIN_TANK_HGT")
    private Double minTankHgt;

    // 安全罐量上限
    @SpecialResource(name = "a.maxTankStoarge")
    @Column(name = "MAX_TANK_STOARGE")
    private Double maxTankStoarge;

    // 安全罐量下限
    @SpecialResource(name = "a.minTankStoarge")
    @Column(name = "MIN_TANK_STOARGE")
    private Double minTankStoarge;

    // 是否保温罐
    @SpecialResource(name = "a.htPretTank")
    @Column(name = "HT_PRET_TANK")
    private Integer htPretTank;

    @Transient
    @SpecialResource(name = "b.nodeLongitude")
    private String nodeLongitude;
    // 位置纬度
    @Transient
    @SpecialResource(name = "b.nodeLatitude")
    private String nodeLatitude;

    @Transient
    @SpecialResource(name = "b.nodeAltitude")
    private String nodeAltitude;

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

    @Transient
    @SpecialResource(name = "b.dataStatus")
    private Integer inUse;

    @Column(name = "SORT_NUM")
    @SpecialResource(name = "a.sortNum")
    private Integer sortNum;

    @Transient
    @SpecialResource(name = "b.des")
    private String des;

    @Transient
    @SpecialResource(name = "b.nodeTypeId")
    private Long nodeTypeId;

    @Transient
    private Long areaId;

    @Transient
    private String nodeLevel;

    @Transient
    private String nodeModel;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getTankTypeId() {
        return tankTypeId;
    }

    public void setTankTypeId(Long tankTypeId) {
        this.tankTypeId = tankTypeId;
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

    public Long getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Long nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Tank() {
    }

    public Tank(Long nodeId, Long tankTypeId, Long areaId) {
        super();
        this.nodeId = nodeId;
        this.tankTypeId = tankTypeId;
        this.areaId = areaId;
    }
    public Tank(Long nodeId, String nodeCode, String nodeName, String nodeAlias, Long tankTypeId,
                String tankTypeCode, String tankTypeName, String areaCode, String areaName, String areaAlias,
                Double tankHgt, Double tankTotlCuba, Double fltPlatWgt, Double fltPlatPerhgt, Double fltTipLst,
                Double maxTankHgt, Double minTankHgt, Double maxTankStoarge, Double minTankStoarge, Integer htPretTank,
                String nodeLongitude, String nodeLatitude, String nodeAltitude, String crtUserCode, String crtUserName,
                Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer inUse, Integer sortNum,
                String des, Long nodeTypeId, String orgCode, Long areaId,String nodeLevel, String nodeModel) {
        super();
        this.nodeId = nodeId;
        this.nodeCode = nodeCode;
        this.nodeName = nodeName;
        this.nodeAlias = nodeAlias;
        this.tankTypeId = tankTypeId;
        this.tankTypeCode = tankTypeCode;
        this.tankTypeName = tankTypeName;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaAlias = areaAlias;
        this.tankHgt = tankHgt;
        this.tankTotlCuba = tankTotlCuba;
        this.fltPlatWgt = fltPlatWgt;
        this.fltPlatPerhgt = fltPlatPerhgt;
        this.fltTipLst = fltTipLst;
        this.maxTankHgt = maxTankHgt;
        this.minTankHgt = minTankHgt;
        this.maxTankStoarge = maxTankStoarge;
        this.minTankStoarge = minTankStoarge;
        this.htPretTank = htPretTank;
        this.nodeLongitude = nodeLongitude;
        this.nodeLatitude = nodeLatitude;
        this.nodeAltitude = nodeAltitude;
        this.crtUserCode = crtUserCode;
        this.crtUserName = crtUserName;
        this.crtDate = crtDate;
        this.mntUserCode = mntUserCode;
        this.mntUserName = mntUserName;
        this.mntDate = mntDate;
        this.inUse = inUse;
        this.sortNum = sortNum;
        this.des = des;
        this.nodeTypeId = nodeTypeId;
        this.orgCode = orgCode;
        this.areaId = areaId;
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


    public Double getTankHgt() {
        return tankHgt;
    }


    public void setTankHgt(Double tankHgt) {
        this.tankHgt = tankHgt;
    }


    public Double getFltPlatWgt() {
        return fltPlatWgt;
    }


    public void setFltPlatWgt(Double fltPlatWgt) {
        this.fltPlatWgt = fltPlatWgt;
    }


    public Double getTankTotlCuba() {
        return tankTotlCuba;
    }


    public void setTankTotlCuba(Double tankTotlCuba) {
        this.tankTotlCuba = tankTotlCuba;
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


    public Double getMaxTankStoarge() {
        return maxTankStoarge;
    }


    public void setMaxTankStoarge(Double maxTankStoarge) {
        this.maxTankStoarge = maxTankStoarge;
    }


    public Double getMinTankStoarge() {
        return minTankStoarge;
    }


    public void setMinTankStoarge(Double minTankStoarge) {
        this.minTankStoarge = minTankStoarge;
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

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

}
