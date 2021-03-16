package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_CNFGBASE")
public class IcCnfgBase  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CNFGBASE_ID")
	private Long bCnfgBaseId;
	
	@Column(name = "AREA_ID")
	private Long bAreaId;
	
	@Column(name = "CNFGCLASS_ID")
	private Long bCnfgClassId;
	
	@Column(name = "CNFGCLASSPARA_ID")
	private Long bClassParaId;
	
	@Column(name = "FORMULA")
	private String bFormula;
	
	@Transient
	private String tankType;
	
	@Transient
	private String bCnfgClassCode;
	
	@Transient
	private Integer bCnfgClassSortNum;
	
	@Transient
	private Integer bCnfgParaValue;
	
	@Transient
	private Integer isUseFormula;
	
	@Transient
	private String bNodeCode;
	
	
	
	public String getbNodeCode() {
		return bNodeCode;
	}

	public void setbNodeCode(String bNodeCode) {
		this.bNodeCode = bNodeCode;
	}

	public String getbCnfgClassCode() {
		return bCnfgClassCode;
	}

	public void setbCnfgClassCode(String bCnfgClassCode) {
		this.bCnfgClassCode = bCnfgClassCode;
	}

	public Integer getbCnfgClassSortNum() {
		return bCnfgClassSortNum;
	}

	public void setbCnfgClassSortNum(Integer bCnfgClassSortNum) {
		this.bCnfgClassSortNum = bCnfgClassSortNum;
	}

	public Integer getbCnfgParaValue() {
		return bCnfgParaValue;
	}

	public void setbCnfgParaValue(Integer bCnfgParaValue) {
		this.bCnfgParaValue = bCnfgParaValue;
	}

	public Integer getIsUseFormula() {
		return isUseFormula;
	}

	public void setIsUseFormula(Integer isUseFormula) {
		this.isUseFormula = isUseFormula;
	}


	public Long getbCnfgBaseId() {
		return bCnfgBaseId;
	}

	public void setbCnfgBaseId(Long bCnfgBaseId) {
		this.bCnfgBaseId = bCnfgBaseId;
	}

	public Long getbAreaId() {
		return bAreaId;
	}

	public void setbAreaId(Long bAreaId) {
		this.bAreaId = bAreaId;
	}

	public Long getbCnfgClassId() {
		return bCnfgClassId;
	}

	public void setbCnfgClassId(Long bCnfgClassId) {
		this.bCnfgClassId = bCnfgClassId;
	}

	public Long getbClassParaId() {
		return bClassParaId;
	}

	public void setbClassParaId(Long bClassParaId) {
		this.bClassParaId = bClassParaId;
	}

	public String getbFormula() {
		return bFormula;
	}

	public void setbFormula(String bFormula) {
		this.bFormula = bFormula;
	}

	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
	}

	public IcCnfgBase() {
		super();
	}

	public IcCnfgBase(Long bCnfgBaseId, Long bAreaId, Long bCnfgClassId, Long bClassParaId, String bFormula,
			String tankType, String bCnfgClassCode, Integer bCnfgClassSortNum, Integer bCnfgParaValue,
			Integer isUseFormula,String bNodeCode) {
		super();
		this.bCnfgBaseId = bCnfgBaseId;
		this.bAreaId = bAreaId;
		this.bCnfgClassId = bCnfgClassId;
		this.bClassParaId = bClassParaId;
		this.bFormula = bFormula;
		this.tankType = tankType;
		this.bCnfgClassCode = bCnfgClassCode;
		this.bCnfgClassSortNum = bCnfgClassSortNum;
		this.bCnfgParaValue = bCnfgParaValue;
		this.isUseFormula = isUseFormula;
		this.bNodeCode = bNodeCode;
	}  
	
	
	
	
	

}
