package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_PM_BIZORGMAIN")
public class CacheBizMain implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BIZORGMAIN_ID")
	private Long bizId;
	
	@Column(name = "RENT_ID")
	private Integer rentId;

	@Column(name = "BIZORGMAIN_CODE")
	private String bizCode;
	
	@Column(name = "IS_STANDARD")
	private Integer isStandard;
	
	@Transient
	private String rentCode;
	
	@Transient
	private String orgCode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bizId", cascade = CascadeType.ALL)
	private List<CacheBizDtl> cacheBizDtls;
	
	public CacheBizMain() {
		super();
	}
	public CacheBizMain(String bizCode, String rentCode, String orgCode) {
		super();
		this.bizCode = bizCode;
		this.rentCode = rentCode;
		this.orgCode = orgCode;
	}

	
	public Integer getIsStandard() {
		return isStandard;
	}
	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}
	public String getRentCode() {
		return rentCode;
	}
	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public Long getBizId() {
		return bizId;
	}
	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}
	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public List<CacheBizDtl> getCacheBizDtls() {
		return cacheBizDtls;
	}

	public void setCacheBizDtls(List<CacheBizDtl> cacheBizDtls) {
		this.cacheBizDtls = cacheBizDtls;
	}

}
