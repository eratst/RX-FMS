package com.pcitc.fms.bll.entity;

import java.io.Serializable;

public class Inclusion implements Serializable {
	private static final long serialVersionUID = 1L;
	/**包含关系id**/
	private Integer inclusionId;
	/**源实体的编码**/
	private String sourceCode;
	/**源的类型**/
	private String sourceType;
	/**目标的编码**/
	private String targetCode;
	/**目标的类型**/
	private String targetType;

	
	
	public Integer getInclusionId() {
		return inclusionId;
	}

	public void setInclusionId(Integer inclusionId) {
		this.inclusionId = inclusionId;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	
	
	
	

}
