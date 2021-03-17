package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_BIZORGDTL")
public class CacheBizDtl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BIZORGDTL_ID")
	private Long dtlId;
	
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId;

	@Column(name = "ORG_ID")
	private Long orgId;

	@Column(name = "ORG_CODE")
	private String orgCode;


	public Long getDtlId() {
		return dtlId;
	}

	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
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

}
