package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@Entity
@Table(name = "T_OPM_UNITALARM")
public class UnitAlarm implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SpecialResource(name="a.unitAlarmId")
	@Column(name = "UNITALARM_ID")
	private Long unitAlarmId;
	
	@SpecialResource(name="a.unitAlarmCode")
	@Column(name="UNITALARM_CODE")
	private String unitAlarmCode;
	
	@SpecialResource(name="a.unitAlarmName")
	@Column(name="UNITALARM_NAME")
	private String unitAlarmName;
	
	@SpecialResource(name="a.unitAlarmShortName")
	@Column(name="UNITALARM_ALIAS")
	private String unitAlarmAlias;
	
	@SpecialResource(name="a.unitId")
	@Column(name="AREA_ID")
	private Long unitId;
	
	@SpecialResource(name="a.idxId")
	@Column(name="MEASINDEX_ID")
	private Long idxId;
	
	@SpecialResource(name="a.idxType")
	@Column(name="IDX_TYPE")
	private String idxType;
	
	@SpecialResource(name="a.itemNo")
	@Column(name="ITEM_NO")
	private String itemNo;
	
	@SpecialResource(name="a.upLimit")
	@Column(name="UP_LIMIT_VALUE")
	private Integer upLimit;
	
	@SpecialResource(name="a.downLimit")
	@Column(name="DOWN_LIMIT_VALUE")
	private Integer downLimit;
	
	@SpecialResource(name="a.upperLimit")
	@Column(name="UPPER_LIMIT_VALUE")
	private Integer upperLimit;
	
	@SpecialResource(name="a.lowerLimit")
	@Column(name="LOWER_LIMIT_VALUE")
	private Integer lowerLimit;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	
	@SpecialResource(name="a.sortNum")
	@Column(name="SORT_NUM")
	private Integer sortNum;
	
	@SpecialResource(name="a.des")
	@Column(name="DES")
	private String des;
	
	@SpecialResource(name="a.version")
	@Column(name="VERSION")
	private Integer version;
	
	@SpecialResource(name="a.inUse")
	@Column(name="INUSE")
	private Integer inUse;
	
	public UnitAlarm(Long unitAlarmId, String unitAlarmCode, String unitAlarmName, String unitAlarmAlias,
			Long unitId, Long idxId, String idxType, String itemNo, Integer upLimit, Integer downLimit,
			Integer upperLimit, Integer lowerLimit, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, Integer sortNum, String des, Integer version,
			Integer inUse) {
		super();
		this.unitAlarmId = unitAlarmId;
		this.unitAlarmCode = unitAlarmCode;
		this.unitAlarmName = unitAlarmName;
		this.unitAlarmAlias = unitAlarmAlias;
		this.unitId = unitId;
		this.idxId = idxId;
		this.idxType = idxType;
		this.itemNo = itemNo;
		this.upLimit = upLimit;
		this.downLimit = downLimit;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
	}

	
	public Long getUnitAlarmId() {
		return unitAlarmId;
	}


	public void setUnitAlarmId(Long unitAlarmId) {
		this.unitAlarmId = unitAlarmId;
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


	public UnitAlarm() {
		super();
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


	public String getUnitAlarmAlias() {
		return unitAlarmAlias;
	}


	public void setUnitAlarmAlias(String unitAlarmAlias) {
		this.unitAlarmAlias = unitAlarmAlias;
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

	
}
