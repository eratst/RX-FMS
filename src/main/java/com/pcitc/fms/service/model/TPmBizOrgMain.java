package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.data.domain.Sort;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * [组态关系表] 多业务组织层次(业务域)主表
 * 
 * @author Administrator
 *
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class TPmBizOrgMain extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	@ResourceMember(OnlyQuery = true)
	private Long bizId;

	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(Name = "bizCode", InQueries = "condition")
	@BuildLink(resourceCode = "bizCode")
	private String bizCode;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "bizName")
	private String bizName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", Name = "bizAlias")
	private String bizAlias;

	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;

	@ResourceMember(OnlyQuery = true)
	private String crtUserCode;

	@ResourceMember(OnlyQuery = true)
	private String crtUserName;

	@ResourceMember(OnlyQuery = true)
	private Date crtDate;

	@ResourceMember(OnlyQuery = true)
	private String mntUserCode;

	@ResourceMember(OnlyQuery = true)
	private String mntUserName;

	@ResourceMember(OnlyQuery = true)
	private Date mntDate;

	@ResourceMember(OnlyQuery = true)
	private Integer version;

	@CheckField(CheckName = CheckNameType.SORTNUM)
	private Integer sortNum;

	@CheckField(CheckName = CheckNameType.CODELIST)
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$bizCodes")
	private List<String> bizCodes;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@CheckField(CheckName = CheckNameType.ORDER)
	@ResourceMember(OnlyQuery = true)
	private String orderby;
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer isStandard;
	
	@ResourceMember(OnlyQuery = true)
	private Long rentId;
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;

	public TPmBizOrgMain() {
		super();
	}

	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getOrderby() {
		return orderby;
	}


	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}


	public List<String> getBizCodes() {
		return bizCodes;
	}

	public void setBizCodes(List<String> bizCodes) {
		this.bizCodes = bizCodes;
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

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAlias() {
		return bizAlias;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

}
