package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;
@Entity
@Table(name = "T_OPM_OPEINDEX")
public class Openindex implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SpecialResource(name="a.openindexId")
	@Column(name = "OPEINDEX_ID")
	private Long openindexId;
	
	@SpecialResource(name="a.name")
	@Column(name = "OPEINDEX_NAME")
	private String name;//名称
	
	@SpecialResource(name="a.code")
	@Column(name = "OPEINDEX_STDCODE")
	private String code;//标准编码
	
	@SpecialResource(name="a.equipId")
	@Column(name = "AREA_ID")
	private Long equipId;//装置Id
	
	@Column(name = "OPEINDEXCLASS_ID")
	private Long opeindexClassId;//操作指标类型Id
	
	@Column(name = "CONTROLDEP_ID")
	private Long controlDepId;//控制部门Id
	
	@SpecialResource(name="a.calcFormula")
	@Column(name = "CALC_FORMULA")
	private String calcFormula;//计算公式
	
	@Column(name = "DIMENSION_ID")
	private Long measUnitId;//量纲Id
	
	@SpecialResource(name="a.isPublic")
	@Column(name = "IS_PUBLIC")
	private Integer isPublic;//工艺方案公用标识
	
	@SpecialResource(name="a.unitId")
	@Column(name = "NODE_ID")
	private Long unitId;//设备Id
	
	@SpecialResource(name="a.isInnerOp")
	@Column(name = "IS_INNER_OP")
	private Integer isInnerOp;//是否内操
	
	@SpecialResource(name="a.inUse")
	@Column(name = "INUSE")
	private Integer inUse;//是否启用
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	
	@SpecialResource(name="a.des")
	@Column(name = "DES")
	private String des;//说明
	
	@SpecialResource(name="a.sortnum")
	@Column(name = "SORT_NUM")
	private Integer sortnum;//排序
	
	@SpecialResource(name="a.tagName")
	@Column(name = "TAG_NAME")
	private String tagName;//
	
	@SpecialResource(name="op.name")
	@Transient
	private String openindexClass;//操作指标类型
	
	@SpecialResource(name="co.name")
	@Transient
	private String controlDep;//控制
	
	@SpecialResource(name="di.dimensionName")
	@Transient
	private String measUnit;//量纲
	

	public Openindex(Long openindexId, String name, String code, Long unitId, String calcFormula,
			Integer isPublic, Long equipId, Integer isInnerOp, Integer inUse, String crtUserCode, String crtUserName,
			Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des, Integer sortnum,
			String tagName, String openindexClass, String controlDep, String measUnit) {
		super();
		this.openindexId = openindexId;
		this.name = name;
		this.code = code;
		this.unitId = unitId;
		this.calcFormula = calcFormula;
		this.isPublic = isPublic;
		this.equipId = equipId;
		this.isInnerOp = isInnerOp;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortnum = sortnum;
		this.tagName = tagName;
		this.openindexClass = openindexClass;
		this.controlDep = controlDep;
		this.measUnit = measUnit;
	}
	
	
	
	public Long getOpenindexId() {
		return openindexId;
	}



	public void setOpenindexId(Long openindexId) {
		this.openindexId = openindexId;
	}



	public Long getEquipId() {
		return equipId;
	}



	public void setEquipId(Long equipId) {
		this.equipId = equipId;
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



	public Long getUnitId() {
		return unitId;
	}



	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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



	public Openindex() {
		super();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public String getOpenindexClass() {
		return openindexClass;
	}
	public void setOpenindexClass(String openindexClass) {
		this.openindexClass = openindexClass;
	}
	public String getControlDep() {
		return controlDep;
	}
	public void setControlDep(String controlDep) {
		this.controlDep = controlDep;
	}
	public String getMeasUnit() {
		return measUnit;
	}
	public void setMeasUnit(String measUnit) {
		this.measUnit = measUnit;
	}
	
	
	
}
