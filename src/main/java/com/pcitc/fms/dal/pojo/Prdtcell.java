package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_PRDTCELL")
public class Prdtcell implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 生产单元ID
	 */
	@Id
	@Column(name = "PRDTCELL_ID")
	private Long cellId;

	/**
	 * 区域简称
	 */
	@Transient
	@SpecialResource(name="area.shortName")
	private String plantAbbrName;
	
	@Transient
	@SpecialResource(name="area.shortName")
	private String plantCode;
	
	@Transient
	@SpecialResource(name="area.shortName")
	private String plantName;

	/**
	 * 区域ID
	 */
	@Column(name = "AREA_ID")
	private Long areaId;

	/**
	 * 生产单元编码
	 */
	@SpecialResource(name="prdtcell.cellCode")
	@Column(name = "PRDTCELL_CODE")
	private String cellCode;

	/**
	 * 生产单元名称
	 */
	@SpecialResource(name="prdtcell.cellName")
	@Column(name = "PRDTCELL_NAME")
	private String cellName;

	/**
	 * 生产单元简称
	 */
	@SpecialResource(name="prdtcell.cellAbbrName")
	@Column(name = "PRDTCELL_ALIAS")
	private String cellAbbrName;

	/**
	 * 是否启用
	 */
	@SpecialResource(name="prdtcell.inUse")
	@Column(name = "INUSE")
	private Integer inUse;

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

	/**
	 * 排序
	 */
	@SpecialResource(name="prdtcell.sortNum")
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	/**
	 * 乐观锁版本
	 */
	@Column(name = "VERSION")
	private Integer version;

	/**
	 * 描述
	 */
	@SpecialResource(name="prdtcell.des")
	@Column(name = "DES")
	private String des;
	
	public Prdtcell() {
		super();
	}

	public Prdtcell(String cellCode, String cellName,
			String cellAbbrName, String plantCode, String plantName, String plantAbbrName, Integer inUse, Integer sortNum, String des) {
		super();
		this.plantAbbrName = plantAbbrName;
		this.plantCode = plantCode;
		this.plantName = plantName;
		this.cellCode = cellCode;
		this.cellName = cellName;
		this.cellAbbrName = cellAbbrName;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
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
