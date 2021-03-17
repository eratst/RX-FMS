package com.pcitc.fms.service.model;
import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * 罐对象 resource类
 * 
 * @author <a href="mailto:deng.zhao@pcitc.com">Deng Zhao</a>
 * @version 1.0, 2018年1月24日
 */
public class TankR extends BaseResRep {

	/**
	 * Id
	 */
	@ResourceMember(InTemplate = true, Name = "id")
	private Integer id;
	/**
	 * 编码
	 */
	@ResourceMember(InTemplate = true, Name = "code")
	private String code;
	/**
	 * 名称
	 */
	@ResourceMember(InTemplate = true, Name = "name")
	private String name;
	/**
	 * 别名
	 */
	@ResourceMember(InTemplate = true, Name = "alias")
	private String alias;
	/**
	 * 区域Id
	 */
	@ResourceMember(InTemplate = true, Name = "areaId")
	private Integer areaId;
	/**
	 * 区域名称
	 */
	@ResourceMember(InTemplate = true, Name = "areaName")
	private String areaName;
	/**
	 * 节点类型Id
	 */
	@ResourceMember(InTemplate = true, Name = "nodeTypeId")
	private Integer nodeTypeId;
	/**
	 * 节点类型名称
	 */
	@ResourceMember(InTemplate = true, Name = "nodeTypeName")
	private String nodeTypeName;
	/**
	 * 精度
	 */
	@ResourceMember(InTemplate = true, Name = "pres")
	private Double pres;
	/**
	 * 罐类型id
	 */
	@ResourceMember(InTemplate = true, Name = "tankTypeId")
	private Integer tankTypeId;
	/**
	 * 罐类型名称
	 */
	@ResourceMember(InTemplate = true, Name = "tankTypeName")
	private String tankTypeName;
	/**
	 * 料仓标识
	 */
	@ResourceMember(InTemplate = true, Name = "stkhsFlag")
	private Integer stkhsFlag;
	/**
	 * 浮盘中
	 */
	@ResourceMember(InTemplate = true, Name = "fltPlatWgt")
	private Double fltPlatWgt;
	/**
	 * 罐最大高度
	 */
	@ResourceMember(InTemplate = true, Name = "tankHigh")
	private Double tankHigh;
	/**
	 * 是否为保温罐
	 */
	@ResourceMember(InTemplate = true, Name = "htPretTank")
	private Integer htPretTank;
	/**
	 * 罐公容积
	 */
	@ResourceMember(InTemplate = true, Name = "tankTotalCuab")
	private Integer tankTotalCuab;
	/**
	 * 浮顶最低点
	 */
	@ResourceMember(InTemplate = true, Name = "fltTipLst")
	private Double fltTipLst;
	/**
	 * 浮盘前高
	 */
	@ResourceMember(InTemplate = true, Name = "fltPlatPerHgt")
	private Double fltPlatPerHgt;
	/**
	 * 罐最大安全高度
	 */
	@ResourceMember(InTemplate = true, Name = "maxTankHigh")
	private Double maxTankHigh;
	/**
	 * 罐最小安全高度
	 */
	@ResourceMember(InTemplate = true, Name = "minTankHigh")
	private Double minTankHigh;
	/**
	 * 排序
	 */
	@ResourceMember(InTemplate = true, Name = "displayOrder")
	private Integer displayOrder;
	/**
	 * 创建时间
	 */
	@ResourceMember(InTemplate = true, Name = "crtTime")
	private Date crtTime;

	/**
	 * 启用标识
	 */
	@ResourceMember(InTemplate = true, Name = "useFlag")
	private Integer useFlag;
	/**
	 * 说明
	 */
	@ResourceMember(InTemplate = true, Name = "des")
	private String des;

	/**
	 * 板块
	 */
	@ResourceMember(InTemplate = true, Name = "sectorId")
	private Integer sectorId;

	@ResourceMember(InTemplate = true, Name = "sectorName")
	private String sectorName;


	public Integer getSectorId() {
		return sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public Integer getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getNodeTypeId() {
		return nodeTypeId;
	}
	public void setNodeTypeId(Integer nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}
	public String getNodeTypeName() {
		return nodeTypeName;
	}
	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public Double getPres() {
		return pres;
	}

	public void setPres(Double pres) {
		this.pres = pres;
	}
	public Integer getTankTypeId() {
		return tankTypeId;
	}
	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
	}
	public String getTankTypeName() {
		return tankTypeName;
	}
	public void setTankTypeName(String tankTypeName) {
		this.tankTypeName = tankTypeName;
	}
	public Integer getStkhsFlag() {
		return stkhsFlag;
	}
	public void setStkhsFlag(Integer stkhsFlag) {
		this.stkhsFlag = stkhsFlag;
	}
	public Double getFltPlatWgt() {
		return fltPlatWgt;
	}
	public void setFltPlatWgt(Double fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
	}
	public Double getTankHigh() {
		return tankHigh;
	}
	public void setTankHigh(Double tankHigh) {
		this.tankHigh = tankHigh;
	}
	public Integer getHtPretTank() {
		return htPretTank;
	}
	public void setHtPretTank(Integer htPretTank) {
		this.htPretTank = htPretTank;
	}
	public Integer getTankTotalCuab() {
		return tankTotalCuab;
	}
	public void setTankTotalCuab(Integer tankTotalCuab) {
		this.tankTotalCuab = tankTotalCuab;
	}
	public Double getFltTipLst() {
		return fltTipLst;
	}
	public void setFltTipLst(Double fltTipLst) {
		this.fltTipLst = fltTipLst;
	}
	public Double getFltPlatPerHgt() {
		return fltPlatPerHgt;
	}
	public void setFltPlatPerHgt(Double fltPlatPerHgt) {
		this.fltPlatPerHgt = fltPlatPerHgt;
	}
	public Double getMaxTankHigh() {
		return maxTankHigh;
	}
	public void setMaxTankHigh(Double maxTankHigh) {
		this.maxTankHigh = maxTankHigh;
	}
	public Double getMinTankHigh() {
		return minTankHigh;
	}
	public void setMinTankHigh(Double minTankHigh) {
		this.minTankHigh = minTankHigh;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

}
