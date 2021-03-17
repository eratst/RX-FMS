package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")

public class TankCnfg extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long tankCnfgId;
	
	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(Name="nodeId")
	private Long nodeId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(Name="nodeCode")
	private String nodeCode;
	
	private String stdDen;
	
	private String vtf;
	
	private String modChkForm;
	
	private String totlChkVpf;
	
	private String wtrChkVpf;
	
	private String cubaTempCoef;
	
	private String vcf;
	
	private String vcfDecFraDgt;
	
	private String tnkForm;
	
	private String tnkAirDen;
	
	private String tnkGasForm;
	
	private String tnkFormMode;
	
	private String stdCubaForm;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称
	
	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;
	
	private Integer sortNum;
	
	private String des;
	
	private Long version;
	
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(OnlyQuery = true)
	private String orderby;

	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	
	

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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
	

}
