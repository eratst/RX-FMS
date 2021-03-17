package com.pcitc.fms.service.model;

import java.io.Serializable;
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
 * Title: Relations
 * Description:关联集合(Relations)
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询")
public class Relations extends BaseResRep  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer relationId;//关联id
	
	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	private String code;//关联id
	
	@ExcelProperty(value = "源id")
	private Integer sourceId;  //源id
	
	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	@ExcelProperty(value = "源编码")
	private String  sourceCode; //源编码
	
	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL,StrLength=50)
	@ExcelProperty(value = "源类型")
	@ResourceMember(InQueries = "condition" , Name = "sourceType")
	private String  sourceType;  //源类型
	
	@ExcelProperty(value = "目标id")
	private Integer targetId;    //目标id
	
	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	@ExcelProperty(value = "目标编码")
	private String  targetCode;  //目标编码
	
	@CheckField(CheckName=CheckNameType.NAME,StrLength=50)
	@ExcelProperty(value = "目标类型")
	@ResourceMember(InQueries = "condition" , Name = "targetType")
	private String  targetType;   //目标类型
	
	@ResourceMember(InTemplate = false,InQueries="search",Name="$codeList",OnlyQuery=true)
	private List<String> codeList;
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Integer top;          //显示数据数
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Integer skip;         //跳过条数
	
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Integer sort_num;    //排序
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Integer version;     //乐观锁版本
	
//	private String  parent_type; // 上一级类型
	
	
	/**
	 * 
	 */
	public Relations() {
		super();
	}

	
	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @param sourceCode2
	 * @param sourceType2
	 * @param targetCode2
	 * @param targetType2
	 * @param idList2
	 * @param top2
	 * @param skip2
	 */
	public Relations(String sourceCode, String sourceType, String targetCode, String targetType,
			List<String> codeList, Integer top, Integer skip) {
			super();
			this.sourceCode = sourceCode;
			this.sourceType = sourceType;
			this.targetCode = targetCode;
			this.targetType = targetType;
			this.codeList = codeList;
			this.top = top;
			this.skip = skip;
	}


	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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

	
	
}
