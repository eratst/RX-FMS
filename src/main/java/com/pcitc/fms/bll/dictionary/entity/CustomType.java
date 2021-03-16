package com.pcitc.fms.bll.dictionary.entity;

import java.io.Serializable;
/**
 * [字典表] 外部组织机构类型
 * @author Administrator
 *
 */
public class CustomType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer customTypeId;
	private String customTypeCode;
	private String customTyprName;
	private String des;
	
	
	public Integer getCustomTypeId() {
		return customTypeId;
	}
	public void setCustomTypeId(Integer customTypeId) {
		this.customTypeId = customTypeId;
	}
	public String getCustomTypeCode() {
		return customTypeCode;
	}
	public void setCustomTypeCode(String customTypeCode) {
		this.customTypeCode = customTypeCode;
	}
	public String getCustomTyprName() {
		return customTyprName;
	}
	public void setCustomTyprName(String customTyprName) {
		this.customTyprName = customTyprName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
	
	
}
