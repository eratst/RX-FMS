package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

 /**
 * Title: Relations
 * Description:关联集合(Relations)
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@Entity
@Table(name = "T_FMS_RELATIONS")
public class Relations implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RELATION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mseq")
	@SequenceGenerator(name="mseq", sequenceName="SEQ_T_FMS_RELATIONS",allocationSize=1)  
	private Integer relationId;//关联id
	
	@RegionMember
	@Column(name = "CODE")
	private String code;   //编码
	
	@Column(name = "SOURCE_ID")
	private Integer sourceId;   //源ID
	
	@Column(name = "SOURCE_CODE")
	private String  sourceCode; //源编码
	
	@Column(name = "SOURCE_TYPE")
	private String  sourceType;  //源类型
	
	@Column(name = "TARGET_ID")
	private Integer targetId;    //目标id
	
	@Column(name = "TARGET_CODE")
	private String  targetCode;  //目标编码
	
	@Column(name = "TARGET_TYPE")
	private String  targetType;   //目标类型
	
	@Column(name = "SORT_NUM")
	private Integer sort_num;    //排序
	
	@Column(name = "VERSION")
	private Integer version;     //乐观锁版本
	
//	@Column(name = "PARENT_TYPE")
//	private String  parent_type; // 上一级类型

	
	

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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	/*public String getParent_type() {
		return parent_type;
	}

	*//**
	 * @param parent_type the parent_type to set
	 *//*
	public void setParent_type(String parent_type) {
		this.parent_type = parent_type;
	}*/
	
	
}
