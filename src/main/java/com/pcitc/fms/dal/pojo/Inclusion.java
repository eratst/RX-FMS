package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_FMS_INCLUSION")
@SequenceGenerator(name = "mseq", sequenceName = "SEQ_T_FMS_INCLUSION", allocationSize = 1)
public class Inclusion implements Serializable {
	private static final long serialVersionUID = 1L;
	/**包含关系id**/
	@Id
	@Column(name="INCLUSIONS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mseq")
	private Integer inclusionId;
	
	/**源的类型**/
	@Column(name="SOURCE_TYPE")
	private String sourceType;
	/**源实体的编码**/
	@Column(name="SOURCE_CODE")
	private String sourceCode;
	/**目标的类型**/
	@Column(name="TARGET_TYPE")
	private String targetType;
	
	
	/**目标的编码**/
	@Column(name="TARGET_CODE")
	private String targetCode;
	

	
//	@ManyToMany
//	private Set<SamplePoint> samplepoints ;


	/**
	 * 
	 */
	public Inclusion() {
		super();
	}

	public Integer getInclusionId() {
		return inclusionId;
	}

	/**
 * @param inclusionId
 * @param sourceType
 * @param sourceCode
 * @param targetType
 * @param targetCode
 */
public Inclusion(Integer inclusionId, String sourceType, String sourceCode, String targetType, String targetCode) {
	super();
	this.inclusionId = inclusionId;
	this.sourceType = sourceType;
	this.sourceCode = sourceCode;
	this.targetType = targetType;
	this.targetCode = targetCode;
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
