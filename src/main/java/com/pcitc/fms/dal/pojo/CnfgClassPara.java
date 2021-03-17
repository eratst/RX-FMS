package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * [字典表]罐量计算配置分类参数
 */
@Entity
@Table(name = "T_IC_CNFGCLASSPARA")
public class CnfgClassPara implements Serializable {
private static final long serialVersionUID = 1L;
	
	/*
	 * 配置参数ID CLASS_PARA_ID
	 */
	@Id
	@Column(name = "CNFGCLASSPARA_ID")
	private Long classParaId;
	
	/*
	 * 基础分类ID CNFG_CLASS_ID
	 */
	@Column(name = "CNFGCLASS_ID")
	private Long cnfgClassId;

	/*
	 * 配置参数值 CNFG_PARA_VALUE
	 */
	@Column(name = "CNFGCLASSPARA_VALUE")
	private Integer cnfgParaValue;

	/*
	 * 配置参数显示值  CNFG_PARA_SHOWVALUE
	 */
	@Column(name = "CNFGCLASSPARA_SHOWVALUE")
	private String cnfgParaShowvalue;

	/*
	 * 配置参数说明  CNFG_PARA_NOTE
	 */
	@Column(name = "CNFGCLASSPARA_NOTE")
	private String cnfgParaNote;  
	
	
	/*
	 * 是否自定义公式(1:是；0:否)  IS_USE_FORMULA
	 */
	@Column(name = "IS_USE_FORMULA")
	private Integer isUseFormula;
	
	/*
	 * 默认公式  FORMULA
	 */
	@Column(name = "FORMULA")
	private String formula;
	
	/*
	 * 创建人ID  CRT_USER_ID
	 */
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;
	
	/*
	 * 创建人名称 CRT_USER_NAME
	 */
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	
	/*
	 * 创建时间 CRT_DATE
	 */
	@Column(name = "CRTDATE")
	private Date crtDate;
	
	/*
	 * 最后维护人ID MNT_USER_ID
	 */
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;
	
	/*
	 * 最后维护人名称 MNT_USER_NAME
	 */
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;
	
	/*
	 * 维护日期 MNT_DATE
	 */
	@Column(name = "MNTDATE")
	private Date mntDate;
	
	/*
	 * 排序   SORT_NUM
	 */
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	/*
	 * 描述  DES
	 */
	@Column(name = "DES")
	private String des;
	
	@Transient
	private String cnfgClassCode;
	
	@Transient
	private String cnfgClassName;

	
	
	
	public CnfgClassPara() {
		super();
	}

	
	
	public CnfgClassPara(Long classParaId, Long cnfgClassId, Integer cnfgParaValue, String cnfgParaShowvalue,
			String cnfgParaNote, Integer isUseFormula, String formula, String crtUserCode, String crtUserName,
			Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer sortNum, String des,
			String cnfgClassCode, String cnfgClassName) {
		super();
		this.classParaId = classParaId;
		this.cnfgClassId = cnfgClassId;
		this.cnfgParaValue = cnfgParaValue;
		this.cnfgParaShowvalue = cnfgParaShowvalue;
		this.cnfgParaNote = cnfgParaNote;
		this.isUseFormula = isUseFormula;
		this.formula = formula;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.des = des;
		this.cnfgClassCode = cnfgClassCode;
		this.cnfgClassName = cnfgClassName;
	}




	public Long getClassParaId() {
		return classParaId;
	}



	public void setClassParaId(Long classParaId) {
		this.classParaId = classParaId;
	}



	public Long getCnfgClassId() {
		return cnfgClassId;
	}



	public void setCnfgClassId(Long cnfgClassId) {
		this.cnfgClassId = cnfgClassId;
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



	public Integer getCnfgParaValue() {
		return cnfgParaValue;
	}

	public void setCnfgParaValue(Integer cnfgParaValue) {
		this.cnfgParaValue = cnfgParaValue;
	}

	public String getCnfgParaShowvalue() {
		return cnfgParaShowvalue;
	}

	public void setCnfgParaShowvalue(String cnfgParaShowvalue) {
		this.cnfgParaShowvalue = cnfgParaShowvalue;
	}

	public String getCnfgParaNote() {
		return cnfgParaNote;
	}

	public void setCnfgParaNote(String cnfgParaNote) {
		this.cnfgParaNote = cnfgParaNote;
	}

	public Integer getIsUseFormula() {
		return isUseFormula;
	}

	public void setIsUseFormula(Integer isUseFormula) {
		this.isUseFormula = isUseFormula;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
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

	public String getCnfgClassCode() {
		return cnfgClassCode;
	}

	public void setCnfgClassCode(String cnfgClassCode) {
		this.cnfgClassCode = cnfgClassCode;
	}

	public String getCnfgClassName() {
		return cnfgClassName;
	}

	public void setCnfgClassName(String cnfgClassName) {
		this.cnfgClassName = cnfgClassName;
	}
}
