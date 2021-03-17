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
 * model 液化产品系数（体积温度）实体类
 * 
 * @author xin.kou
 *
 */

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "查询")
public class LiqprodCubaTempCoef extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	// 体积温度系数编码
	@ResourceMember(InTemplate = false)
	private Long cubaTempCofeId;

	// 密度下限
	@CheckField(CheckName = CheckNameType.IDMAYBENULL)
	private Double denFlrLmt;

	// 密度上限
	@CheckField(CheckName = CheckNameType.IDMAYBENULL)
	private Double denUpLmt;

	// 体积温度系数
	@CheckField(CheckName = CheckNameType.IDMAYBENULL)
	private Double cubaTempCofe;

	// 创建人ID
	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 20)
	private String crtUserCode;

	// 创建人名称
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
	private String crtUserName;

	// 创建时间
	@CheckField(CheckName = CheckNameType.CREATETIME)
	private Date crtDate;

	// 最后维护人ID
	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 20)
	private String mntUserCode;

	// 最后维护人名称
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	private String mntUserName;

	// 维护日期
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date mntDate;

	// 描述
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer inUse;

	// 排序
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10)
	private Integer sortNum;

	// 乐观锁版本
	private Integer version;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	
	public Long getCubaTempCofeId() {
		return cubaTempCofeId;
	}

	public void setCubaTempCofeId(Long cubaTempCofeId) {
		this.cubaTempCofeId = cubaTempCofeId;
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

	public Double getDenFlrLmt() {
		return denFlrLmt;
	}

	public void setDenFlrLmt(Double denFlrLmt) {
		this.denFlrLmt = denFlrLmt;
	}

	public Double getDenUpLmt() {
		return denUpLmt;
	}

	public void setDenUpLmt(Double denUpLmt) {
		this.denUpLmt = denUpLmt;
	}

	public Double getCubaTempCofe() {
		return cubaTempCofe;
	}

	public void setCubaTempCofe(Double cubaTempCofe) {
		this.cubaTempCofe = cubaTempCofe;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	
	
}
