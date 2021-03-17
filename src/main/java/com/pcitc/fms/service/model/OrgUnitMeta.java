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

 /**
 * Title: OrgUnitMeta
* Description: model 机构单元
 * @author zhenqiang.zhao
 * @date 2017年7月13日
 * @version 1.0
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class OrgUnitMeta extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orgUnitId;
	
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50)
	private String code; // 机构编码
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries = "condition", Name = "name")
	private String name;// 机构名称

	
	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL,StrLength= 50)
	@ResourceMember(InQueries = "condition", Name = "shortName")
	private String shortName; // 机构的简称
	
	private String englishName;// 机构的英文名称

	@CheckField(CheckName = CheckNameType.ID)
	@ResourceMember(InQueries = "condition", Name = "typeId")
	private Integer typeId;// 类型Id

	
	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL,StrLength= 50)
	private String contract;// 联系人

	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer orgId;// 机构Id

	
	@CheckField(CheckName = CheckNameType.NEGATIVEID)
	private Integer parentId;// 机构单元父级Id

	
	@CheckField(CheckName=CheckNameType.CREATORID )
	private Integer creatorId;// 创建人ID

	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;// 创建人

	// 创建时间
	@CheckField(CheckName =CheckNameType.CREATETIME)
	private Date createTime;

	
	@CheckField(CheckName=CheckNameType.EDITORID )
	private Integer editorId;// 修改人Id

	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String editor;// 修改人(更新时必填)

	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime;
	
	@ResourceMember(InQueries = "condition", Name = "enabled")
	@CheckField(CheckName =CheckNameType.ENABLED)// 启用标志
	private Integer enabled;
	
	private Integer orderId;// 排序
	
	@CheckField(CheckName = CheckNameType.DES,StrLength=200)
	private String des;// 说明
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$idList")
	private List<Integer> idList;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip;

	public OrgUnitMeta() {
		super();
	}

	public OrgUnitMeta(Integer orgId, String name, String shortName, Integer typeId, Integer enabled,
			List<Integer> idList, List<String> codeList,Integer parentId, Integer top, Integer skip) {
		super();
		this.orgId = orgId;
		this.name = name;
		this.shortName = shortName;
		this.typeId = typeId;
		this.enabled = enabled;
		this.idList = idList;
		this.codeList = codeList;
		this.parentId = parentId;
		this.top = top;
		this.skip = skip;
	}
	
	/**@Description: 根节点查询条件
	 * @param orgId
	 * @param orgUnitId
	 * @param name
	 * @param shortName
	 * @param typeId
	 * @param enabled
	 * @param idList
	 * @param codeList
	 * @param top
	 * @param skip
	 */
	public OrgUnitMeta(Integer orgId, Integer orgUnitId,String name, String shortName, Integer typeId, Integer enabled,
			List<Integer> idList, List<String> codeList, Integer top, Integer skip) {
		super();
		this.orgId = orgId;
		this.orgUnitId = orgUnitId;
		this.name = name;
		this.shortName = shortName;
		this.typeId = typeId;
		this.enabled = enabled;
		this.idList = idList;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
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
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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
