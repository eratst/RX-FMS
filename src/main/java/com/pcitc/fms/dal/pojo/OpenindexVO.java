package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@Entity
@Table(name = "T_OPM_OPEINDEX")
public class OpenindexVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@RegionMember
	@Id
	@Column(name = "OPEINDEX_ID")
	private Long openindexId;//指标id
	@Column(name = "OPEINDEX_NAME")
	private String name;// 指标名称
	@Column(name = "OPEINDEX_STDCODE")
	private String code;// 指标标准编码
	@Column(name = "NODE_ID")
	private Long unitId;// 装置Id 
	@Column(name = "OPEINDEXCLASS_ID")
	private Long opeindexClassId;// 操作指标类型Id 
	@Column(name = "CONTROLDEP_ID")
	private Long controlDepId;// 控制部门Id
	@Column(name = "CALC_FORMULA")
	private String calcFormula;// 计算公式
	@Column(name = "IS_PUBLIC")
	private Integer isPublic;// 工艺方案公用标识
	@Column(name = "MEAS_UNIT_ID")
	private Integer measUnitId;// 设备Id
	@Column(name = "AREA_ID")
	private Long equipId;// 设备Id
	@Column(name = "IS_INNER_OP")
	private Integer isInnerOp;// 是否内操
	@Transient
	private Integer weightFactor;//权重
	public OpenindexVO() {
		super();
	}
	
	public OpenindexVO(Long openindexId, String name, String code, Long unitId, Long opeindexClassId,
			Long controlDepId, String calcFormula, Integer isPublic, Integer measUnitId, Long equipId,
			Integer isInnerOp, Integer weightFactor) {
		super();
		this.openindexId = openindexId;
		this.name = name;
		this.code = code;
		this.unitId = unitId;
		this.opeindexClassId = opeindexClassId;
		this.controlDepId = controlDepId;
		this.calcFormula = calcFormula;
		this.isPublic = isPublic;
		this.measUnitId = measUnitId;
		this.equipId = equipId;
		this.isInnerOp = isInnerOp;
		this.weightFactor = weightFactor;
	}

	public Integer getMeasUnitId() {
		return measUnitId;
	}

	public void setMeasUnitId(Integer measUnitId) {
		this.measUnitId = measUnitId;
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
	public Integer getWeightFactor() {
		return weightFactor;
	}
	public void setWeightFactor(Integer weightFactor) {
		this.weightFactor = weightFactor;
	}
	
}
