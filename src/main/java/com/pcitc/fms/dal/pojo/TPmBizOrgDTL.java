package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_BIZORGDTL")
public class TPmBizOrgDTL implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SpecialResource(name="orgDTL.dtlId")
	@Column(name = "BIZORGDTL_ID")
	private Long dtlId; // 主键ID

	@SpecialResource(name="orgDTL.orgId")
	@Column(name = "ORG_ID")
	private Long orgId; // 组织机构ID

	@SpecialResource(name="orgDTL.orgCode")
	@Column(name = "ORG_CODE") // 组织机构编码
	private String orgCode;

	@SpecialResource(name="orgDTL.orgName")
	@Column(name = "ORG_NAME")
	private String orgName; // 组织机构名称

	@SpecialResource(name="orgDTL.orgAlias")
	@Column(name = "ORG_ALIAS")
	private String orgAlias; // 组织机构简称
	
	@SpecialResource(name="orgDTL.orgTypeId")
	@Column(name = "ORGTYPE_ID")
	private Long orgTypeId; // 组织机构类型

	@SpecialResource(name="orgType.orgTypeCode")
	@Transient
	private String orgTypeCode;

	@SpecialResource(name="orgType.orgTypeName")
	@Transient
	private String orgTypeName;

	@SpecialResource(name="orgDTL.expendFlag")
	@Column(name = "EXPEND_FLAG")
	private Long expendFlag;// 扩展标识

	@SpecialResource(name="orgDTL.inUse")
	@Column(name = "INUSE")
	private Integer inUse;// 状态

	@SpecialResource(name="orgDTL.bizId")
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId; // 业务域ID

	@SpecialResource(name="orgDTL.parentOrgId")
	@Column(name = "PARENT_ORG_ID")
	private Long parentOrgId;// 上一级组织机构ID 用于插入

	@SpecialResource(name="tpmorg.orgCode")
	@Transient
	private String parentOrgCode;// 上一级组织机构编码 用于显示

	@SpecialResource(name="tpmorg.orgName")
	@Transient
	private String parentOrgName;

	@SpecialResource(name="tpmorg.orgAlias")
	@Transient
	private String parentOrgAlias;

	@SpecialResource(name="orgMain.bizCode")
	@Transient
	private String bizCode; // 业务域CODE

	@SpecialResource(name="orgMain.bizName")
	@Transient
	private String bizName; // 业务域名称

	@SpecialResource(name="orgMain.bizAlias")
	@Transient
	private String bizAlias; // 业务域简称

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
	private Date mntDate;// 最后维护人名称

	@SpecialResource(name="orgDTL.version")
	@Column(name = "version")
	private Integer version;

	@SpecialResource(name="orgDTL.sortNum")
	@Column(name = "sort_num")
	private Integer sortNum;// 排序
	// 标准 standard
	@SpecialResource(name="orgDTL.des")
	@Column(name = "DES")
	private String des;// 描述

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentOrgId", fetch = FetchType.EAGER)
	private List<TPmBizOrgDTL> children;// 多端需要初始化一个空数组 如果进行了初始化则递归查询是 是否为null
										// 所以查询结果不包含本身

	public List<TPmBizOrgDTL> getChildren() {
		return children;
	}

	public void setChildren(List<TPmBizOrgDTL> children) {
		this.children = children;
	}

	public TPmBizOrgDTL() {
		super();
	}

	public TPmBizOrgDTL(String orgCode) {
		this.orgCode = orgCode;
	}


	/**
	 * 全部属性
	 * 
	 * @param dtlId
	 * @param orgId
	 * @param orgCode
	 * @param orgName
	 * @param orgAlias
	 * @param orgTypeId
	 * @param orgType
	 * @param expendFlag
	 * @param dataStatus
	 * @param bizId
	 * @param parentOrgId
	 * @param parentOrgCode
	 * @param bizCode
	 * @param bizName
	 * @param bizAlias
	 * @param crtUserId
	 * @param crtUserName
	 * @param crtDate
	 * @param mntUserId
	 * @param mntUserName
	 * @param mntDate
	 * @param version
	 * @param sortNum
	 * @param des
	 */
	public TPmBizOrgDTL(Long dtlId, Long orgId, String orgCode, String orgName, String orgAlias,
			Long orgTypeId, String orgTypeCode, String orgTypeName, Long expendFlag, Integer dataStatus,
			Long bizId, Long parentOrgId, String parentOrgCode, String parentOrgName, String parentOrgAlias,
			String bizCode, String bizName, String bizAlias, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, Integer version, Integer sortNum, String des) {
		this.dtlId = dtlId;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
		this.orgTypeCode = orgTypeCode;
		this.orgTypeName = orgTypeName;
		this.expendFlag = expendFlag;
		this.inUse = dataStatus;
		this.bizId = bizId;
		this.parentOrgId = parentOrgId;
		this.parentOrgCode = parentOrgCode;
		this.parentOrgName = parentOrgName;
		this.parentOrgAlias = parentOrgAlias;
		this.bizCode = bizCode;
		this.bizName = bizName;
		this.bizAlias = bizAlias;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.version = version;
		this.sortNum = sortNum;
		this.des = des;
	}

	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
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

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizAlias() {
		return bizAlias;
	}

	public void setBizAlias(String bizAlias) {
		this.bizAlias = bizAlias;
	}

	public Long getDtlId() {
		return dtlId;
	}

	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}


	public Long getExpendFlag() {
		return expendFlag;
	}

	public void setExpendFlag(Long expendFlag) {
		this.expendFlag = expendFlag;
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

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}


	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getParentOrgAlias() {
		return parentOrgAlias;
	}

	public void setParentOrgAlias(String parentOrgAlias) {
		this.parentOrgAlias = parentOrgAlias;
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
