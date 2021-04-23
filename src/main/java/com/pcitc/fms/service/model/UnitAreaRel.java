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
 * @author zhao.li
 *
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class UnitAreaRel extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 装置与装置界区关系ID
	 */
	@ResourceMember(InTemplate = false)
	private Long unitAreaRelId;

	@ResourceMember(InTemplate = false)
	private Long unitId;
	/**
	 * 装置名称
	 */
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置简称")
	@ResourceMember(InQueries = "condition", Name = "unitName")
	private String unitName;
	
	/**
	 * 装置编码
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装置编码")
	@ResourceMember(InQueries = "condition", Name = "unitCode")
	private String unitCode;

	@ResourceMember(InTemplate = false)
	private Long unitAreaId;
	/**
	 * 装置界区名称
	 */
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "装置界区名称")
	@ResourceMember(InQueries = "condition", Name = "unitAreaName")
	private String unitAreaName;
	
	/**
	 * 装置界区编码
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "装置界区编码")
	@ResourceMember(InQueries = "condition", Name = "unitAreaCode")
	private String unitAreaCode;

	/**
	 * 状态
	 */
	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "dataStatus")
	private Integer dataStatus;

	/**
	 * 创建人ID
	 */
	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 50, Explain = "创建人编码")
	private String crtUserId;

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

	/**
	 * 最后维护人ID
	 */
	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserId;

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
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
	private Integer version;

	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	@ResourceMember(OnlyQuery=true)
	private String rentCode;

	@ResourceMember(InTemplate = false)
	private Long bizId;

    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
	private String bizCode;
	
	public UnitAreaRel() {
		super();
	}

	public UnitAreaRel(Long unitAreaRelId, Long unitId, String unitName, String unitCode, Long unitAreaId,
			String unitAreaName, String unitAreaCode, Integer dataStatus, String crtUserId, String crtUserName,
			Date crtDate, String mntUserId, String mntUserName, Date mntDate, Integer sortNum, Integer version,
			String des, List<String> codeList, Integer top, Integer skip, String rentCode, Long bizId, String bizCode) {
		super();
		this.unitAreaRelId = unitAreaRelId;
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitCode = unitCode;
		this.unitAreaId = unitAreaId;
		this.unitAreaName = unitAreaName;
		this.unitAreaCode = unitAreaCode;
		this.dataStatus = dataStatus;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.version = version;
		this.des = des;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
		this.rentCode = rentCode;
		this.bizId = bizId;
		this.bizCode = bizCode;
	}


	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getUnitAreaId() {
		return unitAreaId;
	}

	public void setUnitAreaId(Long unitAreaId) {
		this.unitAreaId = unitAreaId;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public Long getUnitAreaRelId() {
		return unitAreaRelId;
	}

	public void setUnitAreaRelId(Long unitAreaRelId) {
		this.unitAreaRelId = unitAreaRelId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitAreaName() {
		return unitAreaName;
	}

	public void setUnitAreaName(String unitAreaName) {
		this.unitAreaName = unitAreaName;
	}

	public String getUnitAreaCode() {
		return unitAreaCode;
	}

	public void setUnitAreaCode(String unitAreaCode) {
		this.unitAreaCode = unitAreaCode;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

}
