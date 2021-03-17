package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;

 /**
 * Title: orgUnitView
 * Description:组织机构单元视图
 * @author zhenqiang.zhao
 * @date 2017年6月21日
 * @version 1.0
 */
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class OrgUnitView extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orgUnitViewId; 		//组织机构单元视图Id
	
	@CheckField(CheckName=CheckNameType.CODE ,StrLength= 50)
	@ExcelProperty(value = "机构单元视图编码")
	private String code;				//组织机构单元视图编码，唯一约束，不重复
	
	@CheckField(CheckName=CheckNameType.NAME ,StrLength= 50)
	@ExcelProperty(value = "机构单元视图名称")
	@ResourceMember(InQueries="condition",Name="name")
	private String name;				//组织机构单元视图名称，如（炼油一厂）      
	
	@CheckField(CheckName=CheckNameType.NAME ,StrLength= 50)
	@ExcelProperty(value = "机构单元视图简称")
	@ResourceMember(InQueries="condition",Name="shortName")
	private String shortName;			//组织机构单元视图简称
	
	private Integer orgViewId;			//组织机构视图Id
	
	@CheckField(CheckName=CheckNameType.IDMAYBENULL )
	@ExcelProperty(value = "类型Id")
	private Integer typeId;				//类型ID（从字典获取，如：分厂、车间…）
	
	@CheckField(CheckName=CheckNameType.NAMEMAYBENULL ,StrLength= 50)
	@ExcelProperty(value = "联系人")
	private String contract;			//联系人
	
	@CheckField(CheckName=CheckNameType.ID )
	@ExcelProperty(value = "机构单元视图父级Id")
	private Integer parentId;			//机构单元视图父级Id
	
	@ExcelProperty(value = "机构单元Id")
	private Integer orgUnitId;			//机构单元Id
	
	@CheckField(CheckName=CheckNameType.ID )
	private Integer creatorId;			//创建人ID
	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;				//创建人
	
	@CheckField(CheckName=CheckNameType.CREATETIME )
	private Date createTime;			//创建时间
		
	@CheckField(CheckName=CheckNameType.ID )
	private Integer editorId;
	
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength= 50)
	private String editor;				//修改人
	
	@CheckField(CheckName=CheckNameType.MAINTAINTIME )
	private Date maintainTime;			//修改时间
	
	@CheckField(CheckName=CheckNameType.IDMAYBENULL )
	private Integer enabled;			//启用标志
	
	private Integer orderId;			//排序
	
	@CheckField(CheckName = CheckNameType.DES,StrLength= 200)
	@ExcelProperty(value = "说明")
	private String des;					//说明
	
	@ResourceMember(InQueries="search",Name="$codeList")
	private List<String> codeList;
	
	@ResourceMember(InQueries="search",Name="$idList")
	private List<Integer> idList;
	
	private Integer top;
	
	private Integer skip;

	
	
	/**
	 * @param name
	 * @param shortName
	 * @param orgViewId
	 * @param enabled
	 */
	public OrgUnitView(String name, String shortName, Integer orgViewId,
			Integer enabled) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.orgViewId = orgViewId;
		this.enabled = enabled;
	}

	/**
	 * 
	 */
	public OrgUnitView() {
		super();
	}

	/**
	 * @param name
	 * @param shortName
	 * @param orgViewId
	 * @param enabled
	 * @param codeList
	 * @param idList
	 * @param top
	 * @param skip
	 */
	public OrgUnitView(String name, String shortName, Integer orgViewId,
			Integer enabled, List<String> codeList, List<Integer> idList,
			Integer top, Integer skip) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.orgViewId = orgViewId;
		this.enabled = enabled;
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}
	
	

	/**
	 * @param name
	 * @param shortName
	 * @param parentId
	 * @param enabled
	 * @param codeList
	 * @param idList
	 * @param top
	 * @param skip
	 */
	public OrgUnitView(Integer parentId,String name, String shortName ,Integer enabled, List<String> codeList,
			List<Integer> idList, Integer top, Integer skip) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.parentId = parentId;
		this.enabled = enabled;
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}

	/**
	 * @return the orgUnitViewId
	 */
	public Integer getOrgUnitViewId() {
		return orgUnitViewId;
	}

	/**
	 * @param orgUnitViewId the orgUnitViewId to set
	 */
	public void setOrgUnitViewId(Integer orgUnitViewId) {
		this.orgUnitViewId = orgUnitViewId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the orgViewId
	 */
	public Integer getOrgViewId() {
		return orgViewId;
	}

	/**
	 * @param orgViewId the orgViewId to set
	 */
	public void setOrgViewId(Integer orgViewId) {
		this.orgViewId = orgViewId;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the orgUnitId
	 */
	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	/**
	 * @param orgUnitId the orgUnitId to set
	 */
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	/**
	 * @return the creatorId
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
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
	 * @return the editorId
	 */
	public Integer getEditorId() {
		return editorId;
	}

	/**
	 * @param editorId the editorId to set
	 */
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
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
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	 * @return the codeList
	 */
	public List<String> getCodeList() {
		return codeList;
	}

	/**
	 * @param codeList the codeList to set
	 */
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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

	
	
}
