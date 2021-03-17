package com.pcitc.fms.bll.entity;

import java.io.Serializable;

 /**
 * Title: Relations
 * Description:关联集合(Relations)
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */

public class Relations implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer relationId;//关联id
	
	private String code;//关联id
	
	private Integer sourceId;  //源id
	
	private String  sourceCode; //源编码
	
	private String  sourceType;  //源类型
	
	private Integer targetId;    //目标id
	
	private String  targetCode;  //目标编码
	
	private String  targetType;   //目标类型
	
	private Integer sort_num;    //排序
	
	private Integer version;     //乐观锁版本
	
	private String  parent_type; // 上一级类型


	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the relationId
	 */
	public Integer getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	/**
	 * @return the sourceId
	 */
	public Integer getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the sourceCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * @param sourceCode the sourceCode to set
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the targetId
	 */
	public Integer getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	/**
	 * @return the targetCode
	 */
	public String getTargetCode() {
		return targetCode;
	}

	/**
	 * @param targetCode the targetCode to set
	 */
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	/**
	 * @return the targetType
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * @param targetType the targetType to set
	 */
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	/**
	 * @return the sort_num
	 */
	public Integer getSort_num() {
		return sort_num;
	}

	/**
	 * @param sort_num the sort_num to set
	 */
	public void setSort_num(Integer sort_num) {
		this.sort_num = sort_num;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the parent_type
	 */
	public String getParent_type() {
		return parent_type;
	}

	/**
	 * @param parent_type the parent_type to set
	 */
	public void setParent_type(String parent_type) {
		this.parent_type = parent_type;
	}
	
}
