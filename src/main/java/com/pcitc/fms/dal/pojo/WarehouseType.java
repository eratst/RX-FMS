package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**仓库业务类型
 * @author Administrator
 *
 */
@Entity
@Table(name = "T_PM_WAREHOUSETYPE")
public class WarehouseType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "WAREHOUSETYPE_ID")
	private Long warehouseTypeId;
	
	@Column(name = "WAREHOUSETYPE_CODE")
	private String warehouseTypeCode;
	
	@Column(name = "WAREHOUSETYPE_NAME")
	private String warehouseTypeName;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "VERSION")
	private Integer version;
	
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
	
	
	
	public Long getWarehouseTypeId() {
		return warehouseTypeId;
	}
	public void setWarehouseTypeId(Long warehouseTypeId) {
		this.warehouseTypeId = warehouseTypeId;
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
	public String getWarehouseTypeCode() {
		return warehouseTypeCode;
	}
	public void setWarehouseTypeCode(String warehouseTypeCode) {
		this.warehouseTypeCode = warehouseTypeCode;
	}
	public String getWarehouseTypeName() {
		return warehouseTypeName;
	}
	public void setWarehouseTypeName(String warehouseTypeName) {
		this.warehouseTypeName = warehouseTypeName;
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
	
	
}
