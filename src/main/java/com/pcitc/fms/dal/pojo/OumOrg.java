package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "T_PM_OUMORG")
public class OumOrg implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构Id
	 */
	@Id
	@Column(name = "ORG_ID")
	private Long orgId;
	/**
	 * 编码
	 */
	@Column(name = "ORG_CODE")
	private String orgCode;
	/**
	 * 机构名称
	 */
	@Column(name = "ORG_NAME")
	private String orgName;
	
	/**
	 * 机构简称
	 */
	@Column(name = "ORG_ALIA")
	private String orgAlia;
	
	/**
	 * 机构的英文名称
	 */
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	/**
	 * 机构性质
	 */
	@Column(name = "ORG_TYPE_ID")
	private Long orgTypeId;
	/**
	 * 法人代表
	 */
	@Column(name = "LEGAR")
	private String legar;
	/**
	 * 机构地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	/**
	 * 邮政编码
	 */
	@Column(name = "ZIP_CODE")
	private Long zipCode;
	/**
	 * 联系人
	 */
	@Column(name = "CONTRACT")
	private String contract;
	/**
	 * 联系电话
	 */
	@Column(name = "CONTRACT_TEL")
	private String contractTel;
	/**
	 * 传真
	 */
	@Column(name = "FAX")
	private String fax;
	/**
	 * 邮箱
	 */
	@Column(name = "EMAIL")
	private String email;
	/**
	 * 开户银行
	 */
	@Column(name = "BANK_ID")
	private String bankId;
	/**
	 * 银行账号
	 */
	@Column(name = "BANK_NO")
	private String accountNo;
	/**
	 * 税号
	 */
	@Column(name = "TAX_NO")
	private String taxNo;
	/**
	 * 发票抬头
	 */
	@Column(name = "INVOICE_TITLE")
	private String invoiceTitle;
	/**
	 * 创建人Id
	 */
	@Column(name = "CRT_USER_ID")
	private String crtUserId = "admin";
	/**
	 * 创建人Name
	 */
	@Column(name = "CRT_USER_NAME")
	private String crtUserName = "admin";
	/**
	 * 创建时间
	 */
	@Column(name = "CRT_USER_DATE")
	private Date crtUserDate = new Date();
	/**
	 * 修改人Id
	 */
	@Column(name = "MNT_USER_ID")
	private String mntUserId = "admin";
	/**
	 * 修改人Name
	 */
	@Column(name = "MNT_USER_NAME")
	private String mntUserName = "admin";
	/**
	 * 修改时间
	 */
	@Column(name = "MNT_USER_DATE")
	private Date mntUserDate = new Date();

	/**
	 * 乐观锁
	 */
	@Version
	@Column(name = "VERSION")
	private Integer version;

	/**
	 * 是否启用
	 */
	@Column(name = "ENABLED")
	private Long enabled = 1l;

	/**
	 * 排序
	 */
	@Column(name = "ORDER_ID")
	private Long orderId = 1l;

	/**
	 * 备注
	 */
	@Column(name = "DES")
	private String des="";

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlia() {
		return orgAlia;
	}

	public void setOrgAlia(String orgAlia) {
		this.orgAlia = orgAlia;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getLegar() {
		return legar;
	}

	public void setLegar(String legar) {
		this.legar = legar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
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

	public Date getCrtUserDate() {
		return crtUserDate;
	}

	public void setCrtUserDate(Date crtUserDate) {
		this.crtUserDate = crtUserDate;
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

	public Date getMntUserDate() {
		return mntUserDate;
	}

	public void setMntUserDate(Date mntUserDate) {
		this.mntUserDate = mntUserDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getEnabled() {
		return enabled;
	}

	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
}
