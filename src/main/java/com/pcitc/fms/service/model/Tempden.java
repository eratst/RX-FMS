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

@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class Tempden extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ResourceMember(OnlyQuery=true,InTemplate=false)
	private Long tempdenId;
	
	private Long mtrlId;
	
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50)
	@ResourceMember(InQueries="condition")
	private String mtrlCode;
	
	private Double cubaTempCofe;
	
	private Double den;
	
	@CheckField(CheckName = CheckNameType.CODELIST, StrLength = 50)
	@ResourceMember(OnlyQuery=true,InQueries="search",InTemplate=false)
	private List<String> mtrlCodeList;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer skip;
	
	@CheckField(CheckName=CheckNameType.PAGEINFO)
	@ResourceMember(OnlyQuery=true,InQueries="search,condition",InTemplate=false)
	private Integer top;
	
	@CheckField(CheckName=CheckNameType.ORDER)
	@ResourceMember(OnlyQuery=true,InTemplate=false)
	public String orderby;
	
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
	
	private String des;
	
	private Integer inUse;
	
	private Integer sortNum;
	

	public Long getTempdenId() {
		return tempdenId;
	}

	public void setTempdenId(Long tempdenId) {
		this.tempdenId = tempdenId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public Double getCubaTempCofe() {
		return cubaTempCofe;
	}

	public void setCubaTempCofe(Double cubaTempCofe) {
		this.cubaTempCofe = cubaTempCofe;
	}

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public List<String> getMtrlCodeList() {
		return mtrlCodeList;
	}

	public void setMtrlCodeList(List<String> mtrlCodeList) {
		this.mtrlCodeList = mtrlCodeList;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}


}
