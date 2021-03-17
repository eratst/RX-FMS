package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_ENTRTYPE")
public class EntrType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ENTRTYPE_ID")
	private Long entrTypeId;
	
	@Column(name = "ENTRTYPE_CODE")
	private String entrTypeCode;
	
	@Column(name = "ENTRTYPE_NAME")
	private String entrTypeName;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	

	public Long getEntrTypeId() {
		return entrTypeId;
	}

	public void setEntrTypeId(Long entrTypeId) {
		this.entrTypeId = entrTypeId;
	}

	public String getEntrTypeCode() {
		return entrTypeCode;
	}

	public void setEntrTypeCode(String entrTypeCode) {
		this.entrTypeCode = entrTypeCode;
	}

	public String getEntrTypeName() {
		return entrTypeName;
	}

	public void setEntrTypeName(String entrTypeName) {
		this.entrTypeName = entrTypeName;
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
