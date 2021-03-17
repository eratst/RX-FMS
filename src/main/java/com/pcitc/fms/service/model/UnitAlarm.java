package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class UnitAlarm extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@ExcelProperty("装置告Id")
	@ResourceMember(InTemplate = false)
	private Long unitAlarmId;
	@ExcelProperty("装置告警编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String unitAlarmCode;
	
	@ExcelProperty("装置告警名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="unitAlarmName")
	private String unitAlarmName;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ExcelProperty("装置告警简称")
	@ResourceMember(InQueries="condition",Name="unitAlarmShortName")
	private String unitAlarmAlias;
	
	@ExcelProperty("装置id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long unitId;
	
	@ExcelProperty("度量指标id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long idxId;
	@ExcelProperty("度量指标类型")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String idxType;
	@ExcelProperty("度量指标位号")
//	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String itemNo;
	@ExcelProperty("上限值")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer upLimit;
	@ExcelProperty("下限值")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer downLimit;
	@ExcelProperty("上上限值")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer upperLimit;
	@ExcelProperty("下下限值")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer lowerLimit;
	
	private String crtUserCode;
	
	private String crtUserName;
	
	private Date crtDate;
	
	private String mntUserCode;
	
	private String mntUserName;
	
	private Date mntDate;
	//排序
	private Integer sortNum;
	//描述
	@ExcelProperty("说明")
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	//乐观锁版本
	private Integer version;
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries="condition",Name="inUse")
	private Integer  inUse;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private Integer top;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private Integer skip;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$codeList")
	private List<String> codeList;
	@ResourceMember(InTemplate = false)
	private String unitCode;//装置编码
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$orderby")
	private String orderby;
	
	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public UnitAlarm() {
		super();
	}
	
	public UnitAlarm(String unitAlarmName, String unitAlarmAlias, Long unitId, Integer inUse, Integer top,
			Integer skip, List<String> codeList,String orderby) {
		super();
		this.unitAlarmName = unitAlarmName;
		this.unitAlarmAlias = unitAlarmAlias;
		this.unitId = unitId;
		this.inUse = inUse;
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.orderby = orderby;
	}

	public UnitAlarm(String unitAlarmName, String unitAlarmAlias, Integer inUse, Integer top, Integer skip,
			List<String> codeList, String unitCode) {
		super();
		this.unitAlarmName = unitAlarmName;
		this.unitAlarmAlias = unitAlarmAlias;
		this.inUse = inUse;
		this.top = top;
		this.skip = skip;
		this.codeList = codeList;
		this.unitCode = unitCode;
	}

	
	public Long getUnitAlarmId() {
		return unitAlarmId;
	}

	public void setUnitAlarmId(Long unitAlarmId) {
		this.unitAlarmId = unitAlarmId;
	}

	public String getUnitAlarmAlias() {
		return unitAlarmAlias;
	}

	public void setUnitAlarmAlias(String unitAlarmAlias) {
		this.unitAlarmAlias = unitAlarmAlias;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getIdxId() {
		return idxId;
	}

	public void setIdxId(Long idxId) {
		this.idxId = idxId;
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public String getUnitAlarmCode() {
		return unitAlarmCode;
	}

	public void setUnitAlarmCode(String unitAlarmCode) {
		this.unitAlarmCode = unitAlarmCode;
	}

	public String getUnitAlarmName() {
		return unitAlarmName;
	}

	public void setUnitAlarmName(String unitAlarmName) {
		this.unitAlarmName = unitAlarmName;
	}

	public String getIdxType() {
		return idxType;
	}

	public void setIdxType(String idxType) {
		this.idxType = idxType;
	}

	
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	

	public Integer getUpLimit() {
		return upLimit;
	}







	public void setUpLimit(Integer upLimit) {
		this.upLimit = upLimit;
	}







	public Integer getDownLimit() {
		return downLimit;
	}







	public void setDownLimit(Integer downLimit) {
		this.downLimit = downLimit;
	}







	public Integer getUpperLimit() {
		return upperLimit;
	}







	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}







	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
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

	
}
