package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.RegionMember;

 /**
 * Title: ORG_RELATION
* Description: 机构单元关系映射
 * @author zhenqiang.zhao
 * @date 2017年7月21日
 * @version 1.0
 */
@Entity
@Table(name= "T_FMS_ORG_RELATION")
public class OrgRelation   implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ORG_RELATION_ID")
	@SequenceGenerator(allocationSize = 1,name = "mseq" ,sequenceName = "SEQ_T_FMS_ORG_RELATION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mseq")
	private Integer orgRelationId;  	//机构关系映射ID
	
	@Column(name = "FACTORY_ID")
	private Integer factoryId;  
	
	@RegionMember
	@Column(name = "CODE")
	private String code;    		//编码
	
	@Column(name = "FACTORY_CODE")
	private String factoryCode;
	
//	@Column(name = "SOURCE_ID")
//	private Integer sourceId;	   		//源ID
	
	@Column(name = "SOURCE_CODE")
	private String sourceCode;
	
	@Column(name = "SOURCE_TYPE")
	private String sourceType;    		//源类型
	
//	@Column(name = "TARGET_ID")
//	private Integer targetId;      		//目标ID
	
	@Column(name = "TARGET_CODE")
	private String targetCode;
	
	@Column(name = "TARGET_TYPE")
	private String targetType;   		//目标类型
	
	@Column(name = "ORG_RELATION_TYPE")
	private String orgRelationType;      //机构关系映射类型 ,区分机构单元视图,机构单元
	
	@Column(name = "CREATOR")
	private String creator;        		//创建人
	
	@Column(name = "CREATE_TIME", insertable = false)
	private Date createTime;    		//创建时间
	
	@Column(name = "EDITOR")
	private String editor;         		//修改人
	
	@Column(name = "MAINTAIN_TIME", insertable = false)
	private Date maintainTime; 		    //修改时间
	
	@Column(name = "ENABLED")
	private Integer enabled;       		//启用标识
	
	@Column(name = "DES")
	private String des;          //描述
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;      		//排序
	
	@Column(name = "VERSION")
	private Integer version;       		//乐观锁版本

	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	/**
	 * @return the orgRelationId
	 */
	public Integer getOrgRelationId() {
		return orgRelationId;
	}

	/**
	 * @param orgRelationId the orgRelationId to set
	 */
	public void setOrgRelationId(Integer orgRelationId) {
		this.orgRelationId = orgRelationId;
	}

	/**
	 * @return the factoryId
	 */
	public Integer getFactoryId() {
		return factoryId;
	}

	/**
	 * @param factoryId the factoryId to set
	 */
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
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
	 * @return the orgRelationType
	 */
	public String getOrgRelationType() {
		return orgRelationType;
	}

	/**
	 * @param orgRelationType the orgRelationType to set
	 */
	public void setOrgRelationType(String orgRelationType) {
		this.orgRelationType = orgRelationType;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the maintainTime
	 */
	public Date getMaintainTime() {
		return maintainTime;
	}

	/**
	 * @param maintainTime the maintainTime to set
	 */
	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * @return the sortNum
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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
