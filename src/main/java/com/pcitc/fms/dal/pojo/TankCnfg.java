package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_IC_TANKCNFG")
public class TankCnfg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TANKCNFG_ID")
	private Long tankCnfgId;
	
	@Column(name = "TANK_ID")
	private Long nodeId;
	
	@Transient
	private String nodeCode;
	
	@Column(name = "STD_DEN")
	private String stdDen;
	
	@Column(name = "VTF")
	private String vtf;
	
	@Column(name = "MOD_CHK_FORM")
	private String modChkForm;
	
	@Column(name = "TOTL_CHK_VPF")
	private String totlChkVpf;
	
	@Column(name = "WTR_CHK_VPF")
	private String wtrChkVpf;
	
	@Column(name = "CUBA_TEMP_COEF")
	private String cubaTempCoef;
	
	@Column(name = "VCF")
	private String vcf;
	
	@Column(name = "VCF_DEC_FRA_DGT")
	private String vcfDecFraDgt;
	
	@Column(name = "TNK_FORM")
	private String tnkForm;
	
	@Column(name = "TNK_AIR_DEN")
	private String tnkAirDen;
	
	@Column(name = "TNK_GAS_FORM")
	private String tnkGasForm;
	
	@Column(name = "TNK_FORM_MODE")
	private String tnkFormMode;
	
	@Column(name = "STD_CUBA_FORM")
	private String stdCubaForm;
	
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
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "VERSION")
	private Long version;

	

	public Long getTankCnfgId() {
		return tankCnfgId;
	}

	public void setTankCnfgId(Long tankCnfgId) {
		this.tankCnfgId = tankCnfgId;
	}


	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getStdDen() {
		return stdDen;
	}

	public void setStdDen(String stdDen) {
		this.stdDen = stdDen;
	}

	public String getVtf() {
		return vtf;
	}

	public void setVtf(String vtf) {
		this.vtf = vtf;
	}

	public String getModChkForm() {
		return modChkForm;
	}

	public void setModChkForm(String modChkForm) {
		this.modChkForm = modChkForm;
	}

	public String getTotlChkVpf() {
		return totlChkVpf;
	}

	public void setTotlChkVpf(String totlChkVpf) {
		this.totlChkVpf = totlChkVpf;
	}

	public String getWtrChkVpf() {
		return wtrChkVpf;
	}

	public void setWtrChkVpf(String wtrChkVpf) {
		this.wtrChkVpf = wtrChkVpf;
	}

	public String getCubaTempCoef() {
		return cubaTempCoef;
	}

	public void setCubaTempCoef(String cubaTempCoef) {
		this.cubaTempCoef = cubaTempCoef;
	}

	public String getVcf() {
		return vcf;
	}

	public void setVcf(String vcf) {
		this.vcf = vcf;
	}

	public String getVcfDecFraDgt() {
		return vcfDecFraDgt;
	}

	public void setVcfDecFraDgt(String vcfDecFraDgt) {
		this.vcfDecFraDgt = vcfDecFraDgt;
	}

	public String getTnkForm() {
		return tnkForm;
	}

	public void setTnkForm(String tnkForm) {
		this.tnkForm = tnkForm;
	}

	public String getTnkAirDen() {
		return tnkAirDen;
	}

	public void setTnkAirDen(String tnkAirDen) {
		this.tnkAirDen = tnkAirDen;
	}

	public String getTnkGasForm() {
		return tnkGasForm;
	}

	public void setTnkGasForm(String tnkGasForm) {
		this.tnkGasForm = tnkGasForm;
	}

	public String getTnkFormMode() {
		return tnkFormMode;
	}

	public void setTnkFormMode(String tnkFormMode) {
		this.tnkFormMode = tnkFormMode;
	}

	public String getStdCubaForm() {
		return stdCubaForm;
	}

	public void setStdCubaForm(String stdCubaForm) {
		this.stdCubaForm = stdCubaForm;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public TankCnfg(Long tankCnfgId, Long nodeId, String nodeCode, String stdDen, String vtf, String modChkForm,
			String totlChkVpf, String wtrChkVpf, String cubaTempCoef, String vcf, String vcfDecFraDgt, String tnkForm,
			String tnkAirDen, String tnkGasForm, String tnkFormMode, String stdCubaForm, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, Integer inUse,
			Integer sortNum, String des, Long version) {
		super();
		this.tankCnfgId = tankCnfgId;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.stdDen = stdDen;
		this.vtf = vtf;
		this.modChkForm = modChkForm;
		this.totlChkVpf = totlChkVpf;
		this.wtrChkVpf = wtrChkVpf;
		this.cubaTempCoef = cubaTempCoef;
		this.vcf = vcf;
		this.vcfDecFraDgt = vcfDecFraDgt;
		this.tnkForm = tnkForm;
		this.tnkAirDen = tnkAirDen;
		this.tnkGasForm = tnkGasForm;
		this.tnkFormMode = tnkFormMode;
		this.stdCubaForm = stdCubaForm;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
	}

	public TankCnfg() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}
