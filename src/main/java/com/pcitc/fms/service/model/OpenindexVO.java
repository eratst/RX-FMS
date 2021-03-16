package com.pcitc.fms.service.model;

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
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class OpenindexVO extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ResourceMember(InTemplate = false)
	private Integer openindexId;
	@ExcelProperty("指标标准编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String code;
	//区域名称
	@ExcelProperty("指标名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	private String name;
	//区域类型Id
	@ExcelProperty("装置Id")
	@ResourceMember(InTemplate = false ,InQueries="search,condition",OnlyQuery=true,Name="unitId")
	private Integer unitId;
	//所属组织机构（工厂）
	@ExcelProperty("操作指标类型Id")
	private Integer opeindexClassId;
	//区域简称
	@ExcelProperty("控制部门Id")
	private Integer controlDepId;
	//创建人Id
	@ExcelProperty("计算公式")
	private String calcFormula;
	//量纲id
	@ExcelProperty("量纲id")
	private Integer measUnitId;
	//创建人名称
	@ExcelProperty("工艺方案公用标识")
	private Integer isPublic;
	//创建时间
	@ExcelProperty("设备Id")
	private Integer equipId;
	//最后维护人Id
	@ExcelProperty("是否内操")
	private Integer isInnerOp;
	//最后维护人
	@ExcelProperty("权重")
	private Integer weightFactor;
	public OpenindexVO() {
		super();
	}
	
	

	public Integer getOpenindexId() {
		return openindexId;
	}
	public void setOpenindexId(Integer openindexId) {
		this.openindexId = openindexId;
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
	public Integer getMeasUnitId() {
		return measUnitId;
	}
	public void setMeasUnitId(Integer measUnitId) {
		this.measUnitId = measUnitId;
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
