package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class IcVcf extends BaseResRep implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * VCFID
	 */
	@ResourceMember(InTemplate = false)
	private Long vcfId;

	/**
	 * 密度
	 */
	private Double den;

	/**
	 * 温度
	 */
	private Double temp;

	/**
	 * VCF值
	 */
	private Double vcfVal;

	/**
	 * 算法类别
	 */
	@CheckField(CheckName = CheckNameType.ID)
	private Long vcfTypeId;
	/**
	 * 创建人ID
	 */
	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 20)
	@ResourceMember(OnlyQuery = true)
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
	@ResourceMember(OnlyQuery = true)
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@CheckField(CheckName = CheckNameType.CREATETIME)
	@ResourceMember(OnlyQuery = true)
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 20)
	@ResourceMember(OnlyQuery = true)
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	@ResourceMember(OnlyQuery = true)
	private Date mntDate;

	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;

	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10)
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;

	@ResourceMember(InTemplate = false, InQueries = "condition", Name = "vcfTypeCode")
	private String vcfTypeCode;
	
//	@ResourceMember(InTemplate = false, InQueries = "condition", Name = "vcfTypeName")
	private String vcfTypeName;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$vcfTypeCodeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	
	
	

	public Long getVcfId() {
		return vcfId;
	}

	public void setVcfId(Long vcfId) {
		this.vcfId = vcfId;
	}

	public Long getVcfTypeId() {
		return vcfTypeId;
	}

	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
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

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getVcfVal() {
		return vcfVal;
	}

	public void setVcfVal(Double vcfVal) {
		this.vcfVal = vcfVal;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getVcfTypeCode() {
		return vcfTypeCode;
	}

	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
	}

	public String getVcfTypeName() {
		return vcfTypeName;
	}

	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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

	public IcVcf() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IcVcf(String vcfTypeCode, String vcfTypeName, List<String> codeList, Integer top, Integer skip) {
		super();
		this.vcfTypeCode = vcfTypeCode;
		this.vcfTypeName = vcfTypeName;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
	}

}
