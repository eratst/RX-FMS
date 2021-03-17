package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**VCF类型
 * @author Administrator
 *
 */
@Entity
@Table(name = "T_PM_VCFTYPE")
public class VcfType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="VCFTYPE_ID")
	private Long vcfTypeId;
	
	@Column(name="VCFTYPE_CODE")
	private String vcfTypeCode;
	
	@Column(name="VCFTYPE_NAME")
	private String vcfTypeName;
	
	@Column(name="DES")
	private String des;
	
	@Column(name="SORT_NUM")
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
	
	
	public Long getVcfTypeId() {
		return vcfTypeId;
	}
	public void setVcfTypeId(Long vcfTypeId) {
		this.vcfTypeId = vcfTypeId;
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
	public String getVcfTypeCode() {
		return vcfTypeCode;
	}
	public void setVcfTypeCode(String vcfTypeCode) {
		this.vcfTypeCode = vcfTypeCode;
	}
	public String getVcfTypeName() {
		return vcfTypeName;
	}
	public void setVcfTypeName(String vcfTypeName) {
		this.vcfTypeName = vcfTypeName;
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
