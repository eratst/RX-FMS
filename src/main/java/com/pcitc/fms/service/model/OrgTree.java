package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class OrgTree extends BaseResRep implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long orgId;

	private String orgCode;

	private String orgName; // 组织机构名称

	private String orgAlias; // 组织机构简称

	private Long orgTypeId; // 组织机构类型

	private String orgTypeCode;

	private String orgTypeName;

	private Long upperOrgId;

	private String upperOrgCode;

	private String upperOrgName;

	private String upperOrgAlias;

    private Integer inUse;

    private String des;

    private Integer sortNum;

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

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

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
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

	public Long getUpperOrgId() {
		return upperOrgId;
	}

	public void setUpperOrgId(Long upperOrgId) {
		this.upperOrgId = upperOrgId;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getUpperOrgName() {
		return upperOrgName;
	}

	public void setUpperOrgName(String upperOrgName) {
		this.upperOrgName = upperOrgName;
	}

	public String getUpperOrgAlias() {
		return upperOrgAlias;
	}

	public void setUpperOrgAlias(String upperOrgAlias) {
		this.upperOrgAlias = upperOrgAlias;
	}
	

}
