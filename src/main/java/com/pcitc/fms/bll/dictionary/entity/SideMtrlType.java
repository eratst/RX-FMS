package com.pcitc.fms.bll.dictionary.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * [字典表] 侧线物料类型
 * @author Administrator
 *
 */
public class SideMtrlType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer sideMtrlTypeId;
	private String sideMtrlTypeCode;
	private String sideMtrlTypeName;
	private String des;
	private Integer sortNum;
	private Integer version;
	private Integer inUse;
	private Integer crtUserId; // 创建人ID
	private String crtUserName;// 创建人名称
	private Date crtDate;// 创建时间
	private String mntUserCode;// 最后维护人ID
	private String mntUserName;// 最后维护人名称
	private Date mntDate;//维护时间
	
	
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
	public Integer getCrtUserId() {
		return crtUserId;
	}
	public void setCrtUserId(Integer crtUserId) {
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
	public Integer getSideMtrlTypeId() {
		return sideMtrlTypeId;
	}
	public void setSideMtrlTypeId(Integer sideMtrlTypeId) {
		this.sideMtrlTypeId = sideMtrlTypeId;
	}
	public String getSideMtrlTypeCode() {
		return sideMtrlTypeCode;
	}
	public void setSideMtrlTypeCode(String sideMtrlTypeCode) {
		this.sideMtrlTypeCode = sideMtrlTypeCode;
	}
	public String getSideMtrlTypeName() {
		return sideMtrlTypeName;
	}
	public void setSideMtrlTypeName(String sideMtrlTypeName) {
		this.sideMtrlTypeName = sideMtrlTypeName;
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
