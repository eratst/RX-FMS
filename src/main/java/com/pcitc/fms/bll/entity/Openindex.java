package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
public class Openindex implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long openindexId;
	private String name;//名称
	private String code;//标准编码
	private Long unitId;//装置Id
	private Long opeindexClassId;//操作指标类型Id
	private Long controlDepId;//控制部门Id
	private String calcFormula;//计算公式
	private Long measUnitId;//量纲Id
	private Integer isPublic;//工艺方案公用标识
	private Long equipId;//设备Id
	private Integer isInnerOp;//是否内操
	private Integer inUse;//是否启用
	// 创建人ID
	private String crtUserCode;
	// 创建人名称
	private String crtUserName;
	// 创建时间
	private Date crtDate;
	// 最后维护人ID
	private String mntUserCode;
	// 最后维护人名称
	private String mntUserName;
	// 维护日期
	private Date mntDate;
	private String des;//说明
	private Integer sortnum;//排序
	private String tagName;//
	private String measUnit;//量纲
	private String controlDep;//控制
	private String openindexClass;//操作指标类型
	



	public String getMeasUnit() {
		return measUnit;
	}

	public void setMeasUnit(String measUnit) {
		this.measUnit = measUnit;
	}

	public String getControlDep() {
		return controlDep;
	}

	public void setControlDep(String controlDep) {
		this.controlDep = controlDep;
	}

	public String getOpenindexClass() {
		return openindexClass;
	}

	public void setOpenindexClass(String openindexClass) {
		this.openindexClass = openindexClass;
	}

	public Openindex() {
		super();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getOpenindexId() {
		return openindexId;
	}

	public void setOpenindexId(Long openindexId) {
		this.openindexId = openindexId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getOpeindexClassId() {
		return opeindexClassId;
	}

	public void setOpeindexClassId(Long opeindexClassId) {
		this.opeindexClassId = opeindexClassId;
	}

	public Long getControlDepId() {
		return controlDepId;
	}

	public void setControlDepId(Long controlDepId) {
		this.controlDepId = controlDepId;
	}

	public Long getMeasUnitId() {
		return measUnitId;
	}

	public void setMeasUnitId(Long measUnitId) {
		this.measUnitId = measUnitId;
	}

	public Long getEquipId() {
		return equipId;
	}

	public void setEquipId(Long equipId) {
		this.equipId = equipId;
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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCalcFormula() {
		return calcFormula;
	}
	public void setCalcFormula(String calcFormula) {
		this.calcFormula = calcFormula;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getIsInnerOp() {
		return isInnerOp;
	}
	public void setIsInnerOp(Integer isInnerOp) {
		this.isInnerOp = isInnerOp;
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
	public Integer getSortnum() {
		return sortnum;
	}
	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
	}
	
	
}
