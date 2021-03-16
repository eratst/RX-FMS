package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

public class Prdtcell implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long cellId;

	/**
	 * 区域简称
	 */
	private String plantAbbrName;
	
	private String plantCode;
	
	private String plantName;

	/**
	 * 区域ID
	 */
	private Long areaId;

	/**
	 * 生产单元编码
	 */
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	private String cellName;

	/**
	 * 生产单元简称
	 */
	private String cellAbbrName;

	/**
	 * 是否启用
	 */
	private Integer inUse;

	/**
	 * 创建人ID
	 */
	private String crtUserCode;

	/**
	 * 创建人名称
	 */
	private String crtUserName;

	/**
	 * 创建时间
	 */
	private Date crtDate;

	/**
	 * 最后维护人ID
	 */
	private String mntUserCode;

	/**
	 * 最后维护人名称
	 */
	private String mntUserName;

	/**
	 * 维护日期
	 */
	private Date mntDate;

	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	private Integer version;

	/**
	 * 描述
	 */
	private String des;

	
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

	public String getPlantAbbrName() {
		return plantAbbrName;
	}

	public void setPlantAbbrName(String plantAbbrName) {
		this.plantAbbrName = plantAbbrName;
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

}
