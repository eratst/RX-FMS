package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class OpenindexVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer openindexId;//指标id
	private String name;// 指标名称
	private String code;// 指标标准编码
	private Integer unitId;// 装置Id 
	private Integer opeindexClassId;// 操作指标类型Id 
	private Integer controlDepId;// 控制部门Id
	private String calcFormula;// 计算公式
	private Integer measUnitId;// 量纲Id
	private Integer isPublic;// 工艺方案公用标识
	private Integer equipId;// 设备Id
	private Integer isInnerOp;// 是否内操
	private Integer weightFactor;//权重
	public OpenindexVO() {
		super();
	}
	
	public Integer getMeasUnitId() {
		return measUnitId;
	}

	public void setMeasUnitId(Integer measUnitId) {
		this.measUnitId = measUnitId;
	}

	public Integer getOpenindexId() {
		return openindexId;
	}
	public void setOpenindexId(Integer openindexId) {
		this.openindexId = openindexId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getOpeindexClassId() {
		return opeindexClassId;
	}
	public void setOpeindexClassId(Integer opeindexClassId) {
		this.opeindexClassId = opeindexClassId;
	}
	public Integer getControlDepId() {
		return controlDepId;
	}
	public void setControlDepId(Integer controlDepId) {
		this.controlDepId = controlDepId;
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
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	public Integer getIsInnerOp() {
		return isInnerOp;
	}
	public void setIsInnerOp(Integer isInnerOp) {
		this.isInnerOp = isInnerOp;
	}
	public Integer getWeightFactor() {
		return weightFactor;
	}
	public void setWeightFactor(Integer weightFactor) {
		this.weightFactor = weightFactor;
	}
	
}
