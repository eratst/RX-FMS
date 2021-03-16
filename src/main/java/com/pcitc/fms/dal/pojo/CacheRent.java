package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_RENT")
public class CacheRent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RENT_ID")
	private Long rentId;
	
	@Column(name = "RENT_CODE")
	private String rentCode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "rentId", cascade = CascadeType.ALL)
	private List<CacheBizMain> cacheBizMains;
	
	public CacheRent() {
		super();
	}

	public CacheRent(String rentCode) {
		super();
		this.rentCode = rentCode;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public List<CacheBizMain> getCacheBizMains() {
		return cacheBizMains;
	}

	public void setCacheBizMains(List<CacheBizMain> cacheBizMains) {
		this.cacheBizMains = cacheBizMains;
	}
	
}
