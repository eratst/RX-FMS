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
 * Title: Connections
 * Description:连接集合（Connections）
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询")
public class Connections  extends BaseResRep  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer connectionId;//连接id
	
	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	private String code;   //编码
	
	@CheckField(CheckName=CheckNameType.IDMAYBENULL)
	@ExcelProperty(value = "源id")
	private Integer sourceId;	 //源id
	
//	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	@ExcelProperty(value = "源编码")
	private String sourceCode;   //源编码
	
	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL,StrLength=50)
	@ExcelProperty(value = "源类型")
	@ResourceMember(InQueries="condition",Name="sourceType")
	private String sourceType;   //源类型
	
	@CheckField(CheckName=CheckNameType.IDMAYBENULL)
	@ExcelProperty(value = "目标id")
	private Integer targetId;    //目标id
	
	@CheckField(CheckName=CheckNameType.CODE,StrLength=50)
	@ExcelProperty(value = "目标编码")
	private String targetCode;   //目标编码
	
	@CheckField(CheckName=CheckNameType.NAME,StrLength=50)
	@ExcelProperty(value = "目标类型")
	@ResourceMember(InQueries="condition",Name="targetType")
	private String targetType;   //目标类型
	
	@ResourceMember(InQueries="search",Name="$codeList")
	private List<String> codeList;
	
	
	private Integer top;         //每页显示数据数
	
	private Integer skip;    	 //跳过的数据数
	
	private String parent_type;  //上一级类型
	
	private Integer sort_num;    //排序
	
	private Integer version;     //乐观锁版本

	
	
	/**
	 * 
	 */
	public Connections() {
		super();
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @param sourceId
	 * @param sourceCode
	 * @param sourceType
	 * @param targetId
	 * @param targetCode
	 * @param targetType
	 * @param top
	 * @param skip
	 */
	public Connections(Integer sourceId, String sourceCode, String sourceType, Integer targetId, String targetCode,
			String targetType, Integer top, Integer skip) {
		super();
		this.sourceId = sourceId;
		this.sourceCode = sourceCode;
		this.sourceType = sourceType;
		this.targetId = targetId;
		this.targetCode = targetCode;
		this.targetType = targetType;
		this.top = top;
		this.skip = skip;
	}
	
	/**
	 * @param sourceId
	 * @param sourceCode
	 * @param sourceType
	 * @param targetId
	 * @param targetCode
	 * @param targetType
	 * @param codeList
	 * @param top
	 * @param skip
	 */
	public Connections(Integer sourceId, String sourceCode, String sourceType, Integer targetId, String targetCode,
			String targetType, List<String> codeList, Integer top, Integer skip) {
		super();
		this.sourceId = sourceId;
		this.sourceCode = sourceCode;
		this.sourceType = sourceType;
		this.targetId = targetId;
		this.targetCode = targetCode;
		this.targetType = targetType;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
	}



	/** 
	* Title: toString
	* Description: TODO task mark zhenqiang.zhao
	* @return
	* @see java.lang.Object#toString()
	* @date 2017年7月27日      
	* @author zhenqiang.zhao    
	*/
	@Override
	public String toString() {
		return "Connections [sourceId=" + sourceId + ", sourceCode=" + sourceCode + ", sourceType=" + sourceType
				+ ", targetId=" + targetId + ", targetCode=" + targetCode + ", targetType=" + targetType + ", idList="
				+ codeList + ", top=" + top + ", skip=" + skip + "]";
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
