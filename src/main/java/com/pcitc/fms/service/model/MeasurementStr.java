package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class MeasurementStr extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String measurementId;
	
	private String code;
	private String name;
	private String shortName;
	private String measurementType;
	private String creator;
	private String createTime;
	private String editor;
	private String maintainTime;
	private String enabled;
	private String des;
	private String formula;
	/**测量单位id**/
	private Integer measunitId;
	/**系数**/
	private Integer coefficient;
	/****/
//	private String emModel;
	/**区域id**/
	private Integer areaId;
	/****/
	private Integer mtrlId;
	/**项目号**/
	private String itemNo;
	/**源数据类型**/
	private String sourceDataType;
	/**
	 * 查询专用
	 */
	private List<String> codeList;
	private List<Integer> idList;
	private String parentType;
	private String parentId;
	private String factoryID;
	private String top;
	private String skip;
	
	
	public Integer getMeasunitId() {
		return measunitId;
	}
	public void setMeasunitId(Integer measunitId) {
		this.measunitId = measunitId;
	}
	public Integer getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getMtrlId() {
		return mtrlId;
	}
	public void setMtrlId(Integer mtrlId) {
		this.mtrlId = mtrlId;
	}
	
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codes) {
		this.codeList = codes;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> ids) {
		this.idList = ids;
	}
	public String getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getSourceDataType() {
		return sourceDataType;
	}
	public void setSourceDataType(String sourceDataType) {
		this.sourceDataType = sourceDataType;
	}
	
}
