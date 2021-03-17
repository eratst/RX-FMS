package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PM_FCTRBLOCKTYPE")
public class FctrBlockType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FCTRBLOCKTYPE_ID")
	private Long fctrBlockTypeId;

	@Column(name = "FCTRBLOCKTYPE_CODE")
	private String fctrBlockTypeCode;

	@Column(name = "FCTRBLOCKTYPE_NAME")
	private String fctrBlockTypeName;

	@Column(name = "DES")
	private String des;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	

	public Long getFctrBlockTypeId() {
		return fctrBlockTypeId;
	}

	public void setFctrBlockTypeId(Long fctrBlockTypeId) {
		this.fctrBlockTypeId = fctrBlockTypeId;
	}

	public String getFctrBlockTypeCode() {
		return fctrBlockTypeCode;
	}

	public void setFctrBlockTypeCode(String fctrBlockTypeCode) {
		this.fctrBlockTypeCode = fctrBlockTypeCode;
	}

	public String getFctrBlockTypeName() {
		return fctrBlockTypeName;
	}

	public void setFctrBlockTypeName(String fctrBlockTypeName) {
		this.fctrBlockTypeName = fctrBlockTypeName;
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
