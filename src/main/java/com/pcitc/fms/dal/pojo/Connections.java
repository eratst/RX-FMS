package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;

 /**
 * Title: Connections
 * Description:连接集合（Connections）
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */

@Entity
@Table(name = "T_FMS_CONNECTIONS")
public class Connections implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CONNECTION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mseq")
	@SequenceGenerator(name = "mseq", sequenceName = "SEQ_CONNECTIONS", allocationSize = 1)
	private Integer connectionId;//连接id
	
	@Column(name = "CODE")
	private String code;   //编码
	
	@Column(name = "SOURCE_ID")
	private Integer sourceId;   //源ID
	
	@Column(name = "SOURCE_CODE")
	private String sourceCode;   //源编码
	
	@Column(name = "SOURCE_TYPE")
	private String sourceType;   //源类型
	
	@Column(name = "TARGET_ID")
	private Integer targetId;    //目标id
	
	@Column(name = "TARGET_CODE")
	private String targetCode;   //目标编码
	
	@Column(name = "TARGET_TYPE")
	private String targetType;   //目标类型
	
	@Column(name = "SORT_NUM")
	private Integer sort_num;    //排序
	
	@Column(name = "VERSION")
	private Integer version;     //乐观锁版本


	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	 * @return the connectionId
	 */
	public Integer getConnectionId() {
		return connectionId;
	}

	/**
	 * @param connectionId the connectionId to set
	 */
	public void setConnectionId(Integer connectionId) {
		this.connectionId = connectionId;
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

	
	
}
