package com.pcitc.fms.bll.entity;

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
public class UnitAlarm implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Long unitAlarmId;
	private String unitAlarmCode;
	private String unitAlarmName;
	private String unitAlarmAlias;
	private Long unitId;
	private Long idxId;
	private String idxType;
	private String itemNo;
	private Integer upLimit;
	private Integer downLimit;
	private Integer upperLimit;
	private Integer lowerLimit;
	private String crtUserCode; // 创建人ID
	private String crtUserName;// 创建人名称
	private Date crtDate;// 创建时间
	private String mntUserCode;// 最后维护人ID
	private String mntUserName;// 最后维护人名称
	private Date mntDate;//维护时间
	private Integer sortNum;
	private String des;
	private Integer version;
	private Integer  inUse;
	private Integer top;
	private Integer skip;//12.2改为int
	
	
	public UnitAlarm() {
		super();
	}
	
	public UnitAlarm(Long unitId, Integer top, Integer skip) {
		super();
		this.unitId = unitId;
		this.top = top;
		this.skip = skip;
	}

	

	public Long getUnitAlarmId() {
		return unitAlarmId;
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

	public Long getUnitId() {
		return unitId;
	}

	public Long getIdxId() {
		return idxId;
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

	public String getUnitAlarmAlias() {
		return unitAlarmAlias;
	}

	public void setUnitAlarmAlias(String unitAlarmAlias) {
		this.unitAlarmAlias = unitAlarmAlias;
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

	public void setUnitAlarmId(Long unitAlarmId) {
		this.unitAlarmId = unitAlarmId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public void setIdxId(Long idxId) {
		this.idxId = idxId;
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
