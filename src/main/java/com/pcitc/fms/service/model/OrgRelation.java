package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

 /**
 * Title: ORG_RELATION
* Description: 机构单元关系映射  exel导入
 * @author zhenqiang.zhao
 * @date 2017年7月21日
 * @version 1.0
 */
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询")
public class OrgRelation  extends BaseResRep  implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate =true)
	private Integer orgRelationId;  	//机构关系映射ID
	
	@CheckField(CheckName = CheckNameType.CODE , StrLength = 50)
	private String code;  
	
	private Integer factoryId;    		//工厂ID
	
	@CheckField(CheckName = CheckNameType.CODE , StrLength = 50)
	private String factoryCode;
	
	
	
	@ExcelProperty(value="源ID")
	@ResourceMember(InQueries="search,condition",Name="sourceId", InTemplate =true)
	private Integer sourceId;	   		//源ID
	
	@ExcelProperty(value="源类型")
	@ResourceMember(InQueries="search,condition",Name="sourceType", InTemplate =true)
	@CheckField(CheckName = CheckNameType.NAME , StrLength = 50)
	private String sourceType;    		//源类型
	
	@CheckField(CheckName = CheckNameType.CODE , StrLength = 50)
	private String sourceCode;
	
	@ExcelProperty(value="目标ID")
	private Integer targetId;      		//目标ID
	
	@CheckField(CheckName = CheckNameType.CODE , StrLength = 50)
	private String targetCode;
	
	@ExcelProperty(value="目标类型")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String targetType;   		//目标类型
	
	@ExcelProperty(value="机构关系映射类型")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	private String orgRelationType;      //机构关系映射类型 ,区分机构单元视图,机构单元
	
	@ExcelProperty(value="创建人")
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
	private String creator;        		//创建人
	
	@CheckField(CheckName = CheckNameType.CREATETIME)
	private Date createTime;    		//创建时间
	
	@ExcelProperty(value="修改人")
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	private String editor;         		//修改人
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime; 		    //修改时间
	
	@ExcelProperty(value="启用标识")
	@CheckField(CheckName = CheckNameType.ENABLED)
	private Integer enabled;       		//启用标识
	
	@ExcelProperty(value="描述")
	@CheckField(CheckName = CheckNameType.DES)
	private String des;           		//描述
	
	@ResourceMember(InQueries="search",Name="$idList", OnlyQuery =true)
	private List<Integer> idList;
	
	@ResourceMember(InQueries="search,condition",Name="$top", OnlyQuery =true)
	private Integer top;
	
	@ResourceMember(InQueries="search,condition",Name="$skip", OnlyQuery =true)
	private Integer skip;
	
	private Integer sortNum;      		//排序
	
	private Integer version;       		//乐观锁版本


	/**
	 * 
	 */
	public OrgRelation() {
		super();
	}

	/**
	 * @param factoryId
	 * @param sourceId
	 * @param sourceType
	 * @param targetId
	 * @param targetType
	 * @param orgRelationType
	 * @param enabled
	 * @param idList
	 * @param top
	 * @param skip
	 */
	public OrgRelation(Integer factoryId, Integer sourceId, String sourceType, Integer targetId, String targetType,
			String orgRelationType, Integer enabled, List<Integer> idList, Integer top, Integer skip) {
		super();
		this.factoryId = factoryId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.targetId = targetId;
		this.targetType = targetType;
		this.orgRelationType = orgRelationType;
		this.enabled = enabled;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}
	
	/**
	 * @param factoryCode
	 * @param sourceCode2
	 * @param sourceType
	 * @param targetCode2
	 * @param targetType
	 * @param enabled
	 * @param idList
	 * @param top
	 * @param skip
	 */
	public OrgRelation(String factoryCode, String sourceCode, String sourceType, String targetCode, String targetType , Integer enabled, List<Integer> idList, Integer top, Integer skip) {
		super();
		this.factoryCode = factoryCode;
		this.sourceCode = sourceCode;
		this.sourceType = sourceType;
		this.targetCode = targetCode;
		this.targetType = targetType;
		this.enabled = enabled;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}

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
	 * @return the idList
	 */
	public List<Integer> getIdList() {
		return idList;
	}

	/**
	 * @param idList the idList to set
	 */
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	/**
	 * @return the top
	 */
	public Integer getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(Integer top) {
		this.top = top;
	}

	/**
	 * @return the skip
	 */
	public Integer getSkip() {
		return skip;
	}

	/**
	 * @param skip the skip to set
	 */
	public void setSkip(Integer skip) {
		this.skip = skip;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
