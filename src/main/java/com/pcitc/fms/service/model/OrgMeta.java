package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class OrgMeta extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	
	// Query
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private int top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private int skip;

	// 机构名称
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "name")
	private String name;

	// 机构的简称
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "shortName")
	private String shortName;

	// 机构ID
//	@ResourceMember(OnlyQuery = true)
	private Integer orgId;
	// 机构编码
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String code;
	

	// 机构的英文名称
	//@CheckField(CheckName =CheckNameType.ENGLISHNAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "englishName")
	private String englishName;

	// 机构性质
	@CheckField(CheckName=CheckNameType.NAME,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "orgType")
	private String orgType;

	// 银行账号
	//@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "accountNo")
	private String accountNo;

	// 启用标志
	@CheckField(CheckName =CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition", Name = "enabled")
	private Integer enabled;

	// 法人代表
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String legar;

	// 机构地址;
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String address;

	// 邮政编码
	@CheckField(CheckName =CheckNameType.IDMAYBENULL,StrLength=50)
	private Integer zipCode;

	// 联系人
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String contract;

	// 联系电话
//	@CheckField(CheckName =CheckNameType.CONTRACTTEL ,StrLength=50)
	private String contractTel;

	// 传真
	//@CheckField(CheckName =CheckNameType.FAX,StrLength=50)
	private String fax;

	// 邮箱
	//@CheckField(CheckName =CheckNameType ,StrLength=50)
	private String email;

	// 开户银行
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String bank;

	// 税号
//	@CheckField(CheckName =CheckNameType.IDMAYBENULL ,StrLength=50)
	private String taxNo;

	// 发票抬头
	@CheckField(CheckName =CheckNameType.NAMEMAYBENULL ,StrLength=50)
	private String invoiceTitle;

	// 创建人ID
	@CheckField(CheckName=CheckNameType.CREATORID )
	private int creatorId;

	// 创建人
	@CheckField(CheckName=CheckNameType.CREATOR,StrLength=50 )
	private String creator;

	// 创建时间
//	@CheckField(CheckName =CheckNameType.CREATETIME)
	@ResourceMember(InTemplate = false)
	private Date createTime;

	// 修改人Id
	@CheckField(CheckName=CheckNameType.EDITORID )
	private Integer editorId;

	// 修改人(更新时必填)
	@CheckField(CheckName=CheckNameType.EDITOR,StrLength=50 )
	private String editor;

	// 维护时间(如果为空字符串，则按系统时间生成)
//	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	@ResourceMember(InTemplate = false)
	private Date maintainTime;

	// 排序
	//@CheckField(CheckName =CheckNameType.ID)
	private Integer orderId;

	// 说明
	@CheckField(CheckName =CheckNameType.DES,StrLength=200)
	private String des;

	public OrgMeta() {
		super();
	}

	public OrgMeta(Integer orgId, String name, String shortName, String orgType, String accountNo, Integer enabled,
			List<Integer> idList, List<String> codeList, int top, int skip) {
		super();
		this.orgId = orgId;
		this.name = name;
		this.shortName = shortName;
		this.orgType = orgType;
		this.accountNo = accountNo;
		this.enabled = enabled;
		this.idList = idList;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
	}

	
	public Integer getEditorId() {
		return editorId;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

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

	public String getLegar() {
		return legar;
	}

	public void setLegar(String legar) {
		this.legar = legar;
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
