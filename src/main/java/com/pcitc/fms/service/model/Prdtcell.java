package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.BuildLink;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class Prdtcell extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 生产单元ID
	 */
	@ResourceMember(OnlyQuery=true)
	private Long cellId;
	
	/**
	 * 生产单元编码
	 */
	@ResourceMember(InQueries = "condition", Name = "cellCode")
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@BuildLink(resourceCode = "cellCode")
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	@ResourceMember(InQueries = "condition", Name = "cellName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50, Explain = "名称")
	private String cellName;
	
	/**
	 * 生产单元简称
	 */
	@ResourceMember(InQueries = "condition", Name = "cellAbbrName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50, Explain = "简称")
	private String cellAbbrName;

	
	@ResourceMember(Name = "plantCode")
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	private String plantCode;
	
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String plantName;
	/**
	 * 区域简称
	 */
	@ResourceMember(InQueries = "condition", Name = "plantAbbrName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 36)
	private String plantAbbrName;

	/**
	 * 区域ID
	 */
	@ResourceMember(OnlyQuery=true)
	private Long areaId;
	
	/**
	 * 是否启用
	 */
	@ResourceMember(InQueries = "condition", Name = "inUse")
	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	private Integer inUse;
	
	/**
	 * 创建人ID
	 */
	@ResourceMember(OnlyQuery=true)
	private String crtUserId;

	/**
	 * 创建人名称
	 */
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人名称")
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	@ResourceMember(OnlyQuery=true)
	private String mntUserId;

	/**
	 * 最后维护人名称
	 */
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人名称")
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@ResourceMember(OnlyQuery=true)
	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	/**
	 * 排序
	 */
	@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10, Explain = "排序")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@ResourceMember(OnlyQuery=true)
	private Integer version;
	
	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, Explain = "描述")
	private String des;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$plantCodes")
	private List<String> plantCodes;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;
	
	@ResourceMember(OnlyQuery = true, Name = "$orderby")
	private String orderby;
	
	@ResourceMember(OnlyQuery=true)
	private String rentCode;
	
	@ResourceMember(OnlyQuery=true)
	private String bizCode;
	
	public Prdtcell() {
		super();
	}
	
	public Prdtcell(String cellCode, String cellName, String cellAbbrName,
			Integer inUse, List<String> codeList, List<String> plantCodes,Integer top, Integer skip,String orderby,String rentCode,String bizCode,String plantCode) {
		this.plantCodes = plantCodes;
		this.cellCode = cellCode;
		this.cellName = cellName;
		this.cellAbbrName = cellAbbrName;
		this.inUse = inUse;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
		this.orderby = orderby;
		this.rentCode=rentCode;
		this.bizCode=bizCode;
		this.plantCode=plantCode;
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

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getCellCode() {
		return cellCode;
	}

	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellAbbrName() {
		return cellAbbrName;
	}

	public void setCellAbbrName(String cellAbbrName) {
		this.cellAbbrName = cellAbbrName;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getPlantAbbrName() {
		return plantAbbrName;
	}

	public void setPlantAbbrName(String plantAbbrName) {
		this.plantAbbrName = plantAbbrName;
	}


	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public List<String> getPlantCodes() {
		return plantCodes;
	}

	public void setPlantCodes(List<String> plantCodes) {
		this.plantCodes = plantCodes;
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
