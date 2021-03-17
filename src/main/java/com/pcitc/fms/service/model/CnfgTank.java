package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
/**
 * 
 * @author xin.kou
 *
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询")
public class CnfgTank extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	/**
//	 * 罐量计算单罐配置ID
//	 */
//	@ResourceMember(InTemplate = false)
//	private Integer cnfgTankId;
//	
//	/**
//	 * 罐ID
//	 */
//	@ResourceMember(InTemplate = false)
//	private Integer nodeId;
//	
	
	/**
	 * 罐节点编码（罐号）
	 */
	@ResourceMember(InQueries = "search", InTemplate = false)
	private String nodeCode;// 节点表
//	/**
//	 * 基础分类ID
//	 */
//	@ResourceMember(InTemplate = false)
//	private Integer cnfgClassId;
	
	/**
	 * 基础分类编码(STD_DEN:标准密度公式分类;MOD_CHK_FORM:修正油尺公式分类;WTR_CHK_FORM:修正水尺公式分类;VTF:VTF公式分类;CUBA_TEMP_COEF:液化产品系数公式分类;VCF:VCF公式分类;TOTL_CHK_VPF:总尺VPF公式分类;WTR_CHK_VPF:水尺VPF公式分类;LIE_TNK_FORM_MODE:卧罐体积公式分类;GLB_TNK_FORM_MODE:球罐体积公式分类;STD_TNK_FORM_MODE:立罐罐体积公式分类;G:浮盘重公式分类;GLB_TNK_AIR_DEN:卧罐空气密度;LIE_TNK_AIR_DEN:球罐空气密度;STD_TNK_AIR_DEN:立罐空气密度;LIE_TNK_FORM:卧罐罐公式分类;GLB_TNK_FORM:球罐罐公式分类;STD_TNK_FORM:立罐罐公式分类;LIE_TNK_STD_CUBA_FORM:卧罐标准体积公式分类;GLB_TNK_STD_CUBA_FORM:球罐标准体积公式分类;STD_TNK_STD_CUBA_FORM:立罐标准体积公式分类)
	 */
	private String cnfgClassCode;// 罐量计算配置基础分类
	/**
	 * 基础分类排序
	 */
	private Integer cnfgClassSortNum;
	
	/**
	 * 适用罐类型(0全部；1卧罐；2球罐；3立罐）
	 */
	private String applayTankType;// 罐量计算配置基础分类
	
//	/**
//	 * 计算方法
//	 */
//	@ResourceMember(InTemplate = false)
//	private Integer classParaId;
	
	/**
	 * 配置参数值
	 */
	private Integer cnfgParaValue;// 罐量计算配置分类参数
	
	/**
	 * 是否自定义公式(1:是；0:否)
	 * 
	 */
	private Integer isUseFormula;// 罐量计算配置分类参数
	
	/**
	 * 公式
	 */
	private String formula;
	
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Long nodeId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
//	/**
//	 * 创建人ID
//	 */
//	private String crtUserId;
//
//	/**
//	 * 创建人名称
//	 */
//	private String crtUserName;
//
//	/**
//	 * 创建时间
//	 */
//	private Date crtDate;
//
//	/**
//	 * 最后维护人ID
//	 */
//	private String mntUserId;
//
//	/**
//	 * 最后维护人名称
//	 */
//	private String mntUserName;
//
//	/**
//	 * 维护日期
//	 */
//	private Date mntDate;
//
//	/**
//	 * 描述
//	 */
//	private String des;
//	
	@ResourceMember(InTemplate = false, InQueries = "condition,search", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "condition,search", OnlyQuery = true, Name = "$skip")
	private Integer skip;
	
	@ResourceMember(InQueries = "condition",OnlyQuery = true, Name = "$nodeCodeList")
	private String nodeCodeList;
	
    @ResourceMember(OnlyQuery=true)
	private String crtUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String crtUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date crtDate;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserCode;
	
	@ResourceMember(OnlyQuery=true)
	private String mntUserName;
	
	@ResourceMember(OnlyQuery=true)
	private Date mntDate;
	
	

	
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

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

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	
	public String getNodeCodeList() {
		return nodeCodeList;
	}

	public void setNodeCodeList(String nodeCodeList) {
		this.nodeCodeList = nodeCodeList;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public CnfgTank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CnfgTank(String nodeCode, Integer top, Integer skip, String nodeCodeList,String rentCode,String bizCode) {
		super();
		this.nodeCode = nodeCode;
		this.top = top;
		this.skip = skip;
		this.nodeCodeList = nodeCodeList;
		this.rentCode=rentCode;
		this.bizCode=bizCode;
	}
	
	
}
