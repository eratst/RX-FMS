package com.pcitc.fms.service.stormModel;

import java.io.Serializable;

/**
 * 油品信息
 * @author haiwen.wang
 *
 */
public class MatInitVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String materialid ;//油品ID;
	
	private String  code ;//油品编码;
	
	private String   name;//油品名称;
	
	private int  type;//油品类型;

	

	public String getMaterialid() {
		return materialid;
	}



	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
