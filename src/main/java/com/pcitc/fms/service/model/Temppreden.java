package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

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
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Temppreden extends BaseResRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 温度压力标准密度ID
	 */
	@ResourceMember(InTemplate = false)
	private Long temppredenId;

	/**
	 * 温度
	 */
	private Double temp;

	/**
	 * 压力
	 */
	private Double pre;

	/**
	 * 气体密度
	 */
	private Double gasDen;

	/**
	 * 密度
	 */
	private Double den;

	private String crtUserCode; // 创建人ID
	private String crtUserName;// 创建人名称
	private Date crtDate;// 创建时间
	private String mntUserCode;// 最后维护人ID
	private String mntUserName;// 最后维护人名称
	private Date mntDate;//维护时间
	private Integer inUse;

	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES)
	private String des;

	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM)
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;

	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;

	public Temppreden() {
		super();
	}

	public Temppreden(Integer top, Integer skip) {
		super();
		this.top = top;
		this.skip = skip;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getPre() {
		return pre;
	}

	public void setPre(Double pre) {
		this.pre = pre;
	}

	public Double getGasDen() {
		return gasDen;
	}

	public void setGasDen(Double gasDen) {
		this.gasDen = gasDen;
	}

	public Double getDen() {
		return den;
	}

	public void setDen(Double den) {
		this.den = den;
	}

	public Long getTemppredenId() {
		return temppredenId;
	}

	public void setTemppredenId(Long temppredenId) {
		this.temppredenId = temppredenId;
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

	

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	
	
}
