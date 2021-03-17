package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author xin.kou
 *
 */
@Entity
@Table(name = "T_IC_CNFGTANK")
public class CnfgTank implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 罐量计算单罐配置ID
	 */
	@Id
	@Column(name = "CNFGTANK_ID")
	private Long cnfgTankId;
	
	/**
	 * 罐ID
	 */
	@Column(name = "NODE_ID")
	private Long nodeId;
	
	
	/**
	 * 罐节点编码（罐号）
	 */
	@Transient
	private String nodeCode;// 节点表
	/**
	 * 基础分类ID
	 */
	@Column(name = "CNFGCLASS_ID")
	private Long cnfgClassId;
	
	/**
	 * 基础分类编码(STD_DEN:标准密度公式分类;MOD_CHK_FORM:修正油尺公式分类;WTR_CHK_FORM:修正水尺公式分类;VTF:VTF公式分类;CUBA_TEMP_COEF:液化产品系数公式分类;VCF:VCF公式分类;TOTL_CHK_VPF:总尺VPF公式分类;WTR_CHK_VPF:水尺VPF公式分类;LIE_TNK_FORM_MODE:卧罐体积公式分类;GLB_TNK_FORM_MODE:球罐体积公式分类;STD_TNK_FORM_MODE:立罐罐体积公式分类;G:浮盘重公式分类;GLB_TNK_AIR_DEN:卧罐空气密度;LIE_TNK_AIR_DEN:球罐空气密度;STD_TNK_AIR_DEN:立罐空气密度;LIE_TNK_FORM:卧罐罐公式分类;GLB_TNK_FORM:球罐罐公式分类;STD_TNK_FORM:立罐罐公式分类;LIE_TNK_STD_CUBA_FORM:卧罐标准体积公式分类;GLB_TNK_STD_CUBA_FORM:球罐标准体积公式分类;STD_TNK_STD_CUBA_FORM:立罐标准体积公式分类)
	 */
	@Transient
	private String cnfgClassCode;// 罐量计算配置基础分类
	/**
	 * 基础分类排序
	 */
	@Transient
	private Integer cnfgClassSortNum;
	
	/**
	 * 适用罐类型(0全部；1卧罐；2球罐；3立罐）
	 */
	@Transient
	private String applayTankType;// 罐量计算配置基础分类
	
	/**
	 * 计算方法
	 */
	@Column(name = "CNFGCLASSPARA_ID")
	private Long classParaId;
	
	/**
	 * 配置参数值
	 */
	@Transient
	private Integer cnfgParaValue;// 罐量计算配置分类参数
	
	/**
	 * 是否自定义公式(1:是；0:否)
	 * 
	 */
	@Transient
	private Integer isUseFormula;// 罐量计算配置分类参数
	
	/**
	 * 公式
	 */
	@Column(name = "FORMULA")
	private String formula;
	
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

	/**
	 * 描述
	 */
	@Column(name = "DES")
	private String des;
	
	@Transient
	private Long tankAreaId;
	
	@Transient
	private Long tankTypeId;
	

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getCnfgClassCode() {
		return cnfgClassCode;
	}

	public void setCnfgClassCode(String cnfgClassCode) {
		this.cnfgClassCode = cnfgClassCode;
	}

	public Integer getCnfgClassSortNum() {
		return cnfgClassSortNum;
	}

	public void setCnfgClassSortNum(Integer cnfgClassSortNum) {
		this.cnfgClassSortNum = cnfgClassSortNum;
	}

	public String getApplayTankType() {
		return applayTankType;
	}

	public void setApplayTankType(String applayTankType) {
		this.applayTankType = applayTankType;
	}

	public Integer getCnfgParaValue() {
		return cnfgParaValue;
	}

	public void setCnfgParaValue(Integer cnfgParaValue) {
		this.cnfgParaValue = cnfgParaValue;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	

	public Long getCnfgTankId() {
		return cnfgTankId;
	}

	public void setCnfgTankId(Long cnfgTankId) {
		this.cnfgTankId = cnfgTankId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getCnfgClassId() {
		return cnfgClassId;
	}

	public void setCnfgClassId(Long cnfgClassId) {
		this.cnfgClassId = cnfgClassId;
	}

	public Long getClassParaId() {
		return classParaId;
	}

	public void setClassParaId(Long classParaId) {
		this.classParaId = classParaId;
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

	public Long getTankAreaId() {
		return tankAreaId;
	}

	public void setTankAreaId(Long tankAreaId) {
		this.tankAreaId = tankAreaId;
	}

	public Long getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Long tankTypeId) {
		this.tankTypeId = tankTypeId;
	}

	public CnfgTank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CnfgTank(Long cnfgTankId, Long nodeId, String nodeCode, Long cnfgClassId, String cnfgClassCode,
			Integer cnfgClassSortNum, String applayTankType, Long classParaId, Integer cnfgParaValue,
			Integer isUseFormula, String formula, String crtUserCode, String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, String des) {
		super();
		this.cnfgTankId = cnfgTankId;
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.cnfgClassId = cnfgClassId;
		this.cnfgClassCode = cnfgClassCode;
		this.cnfgClassSortNum = cnfgClassSortNum;
		this.applayTankType = applayTankType;
		this.classParaId = classParaId;
		this.cnfgParaValue = cnfgParaValue;
		this.isUseFormula = isUseFormula;
		this.formula = formula;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
	}
	
	
	
	public CnfgTank(Long cnfgClassId, String cnfgClassCode, Integer cnfgClassSortNum,
			String applayTankType, Long classParaId, Integer cnfgParaValue, Integer isUseFormula, String formula) {
		super();
		this.cnfgClassId = cnfgClassId;
		this.cnfgClassCode = cnfgClassCode;
		this.cnfgClassSortNum = cnfgClassSortNum;
		this.applayTankType = applayTankType;
		this.classParaId = classParaId;
		this.cnfgParaValue = cnfgParaValue;
		this.isUseFormula = isUseFormula;
		this.formula = formula;
	}

	public CnfgTank(Long nodeId, Long tankAreaId, Long tankTypeId) {
		super();
		this.nodeId = nodeId;
		this.tankAreaId = tankAreaId;
		this.tankTypeId = tankTypeId;
	}
	
	
}
