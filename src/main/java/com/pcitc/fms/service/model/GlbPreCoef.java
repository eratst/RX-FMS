package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class GlbPreCoef extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate = false)
	private Long glbPreCoefId;
	
	@CheckField(CheckName = CheckNameType.ID)
	private Long nodeId;//罐ID
	
	@CheckField(CheckName = CheckNameType.CODE)
	private String nodeCode;//罐编码
	
	@CheckField(CheckName = CheckNameType.NAME)
	private String nodeName;//罐名称
	
	private Double presRevCofe;
	
	private Double presFlrLmt;
	
	private Double presUpLmt;
	
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人编码")
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;
	
	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;
	
	/**
	 * 乐观锁版本
	 */
	private Integer version;
	
	/**
	 * 状态
	 */
	@CheckField(CheckName = CheckNameType.ENABLED,Explain="状态")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;
	
	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CODE)
	private String bizCode;
	

	public Long getGlbPreCoefId() {
		return glbPreCoefId;
	}

	public void setGlbPreCoefId(Long glbPreCoefId) {
		this.glbPreCoefId = glbPreCoefId;
	}

	public Double getPresRevCofe() {
		return presRevCofe;
	}

	public void setPresRevCofe(Double presRevCofe) {
		this.presRevCofe = presRevCofe;
	}

	public Double getPresFlrLmt() {
		return presFlrLmt;
	}

	public void setPresFlrLmt(Double presFlrLmt) {
		this.presFlrLmt = presFlrLmt;
	}

	public Double getPresUpLmt() {
		return presUpLmt;
	}

	public void setPresUpLmt(Double presUpLmt) {
		this.presUpLmt = presUpLmt;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
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
	

}
