package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "ORG")
public class OrgMeta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 机构ID
	@Id
	@Column(name = "ORGID")
	@SequenceGenerator(name = "ORG_GENERATOR", sequenceName = "ORG_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORG_GENERATOR")
	private Integer orgId;
	
	// 机构名称
	@Column(name = "NAME")
	private String name;

	// 机构的简称
	@Column(name = "SHORTNAME")
	private String shortName;



	// 机构编码
	@RegionMember
	@Column(name = "CODE")
	private String code;

	// 机构的英文名称
	@Column(name = "ENGLISHNAME")
	private String englishName;

	// 机构性质
	@Column(name = "ORGTYPE")
	private String orgType;

	// 银行账号
	@Column(name = "ACCOUNTNO")
	private String accountNo;

	// 启用标志
	@Column(name = "ENABLED")
	private Integer enabled;

	// 法人代表
	@Column(name = "LEGAR")
	private String legar;

	// 机构地址;
	@Column(name = "ADDRESS")
	private String address;

	// 邮政编码
	@Column(name = "ZIPCODE")
	private Integer zipCode;

	// 联系人
	@Column(name = "CONTRACT")
	private String contract;

	// 联系电话
	@Column(name = "CONTRACTTEL")
	private String contractTel;

	// 传真
	@Column(name = "FAX")
	private String fax;

	// 邮箱
	@Column(name = "EMAIL")
	private String email;

	// 开户银行
	@Column(name = "BANK")
	private String bank;

	// 税号
	@Column(name = "TAXNO")
	private String taxNo;

	// 发票抬头
	@Column(name = "INVOICETITLE")
	private String invoiceTitle;

	// 创建人ID
	@Column(name = "CREATORID")
	private int creatorId;

	// 创建人
	@Column(name = "CREATOR")
	private String creator;

	// 创建时间
	@Column(name = "CREATETIME", insertable = false)
	private Date createTime;

	// 修改人Id
	@Column(name = "EDITORID")
	private int editorId;

	// 修改人(更新时必填)
	@Column(name = "EDITOR")
	private String editor;

	// 维护时间(如果为空字符串，则按系统时间生成)
	@Column(name = "MAINTAINTIME", insertable = false)
	private Date maintainTime;

	// 排序
	@Column(name = "ORDERID")
	private Integer orderId;

	// 说明
	@Column(name = "DES")
	private String des;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getEditorId() {
		return editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
