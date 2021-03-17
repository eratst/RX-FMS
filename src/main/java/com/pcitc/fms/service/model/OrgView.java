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
 * Title: orgView
 * Description:组织机构视图
 * @author zhenqiang.zhao
 * @date 2017年6月21日
 * @version 1.0
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class OrgView extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer orgViewId;   	//组织机构视图Id
	
	@CheckField(CheckName = CheckNameType.CODE,StrLength = 50)
	private String code;         	//组织机构视图编码，唯一约束，不重复
	
	@CheckField(CheckName = CheckNameType.NAME,StrLength = 50)
	@ResourceMember(InQueries="condition",Name="name")
	private String name;         	//组织机构视图名称，如（中石化机构视图）
	
	@CheckField(CheckName = CheckNameType.NAMEMAYBENULL,StrLength = 50)
	@ResourceMember(InQueries="condition",Name="shortName")
	private String shortName;		//组织机构视图简称，如（石化机构视图）
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer appId;          //应用id
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer orgId;			//组织机构Id
	
	@CheckField(CheckName = CheckNameType.NAME,StrLength = 50)
	private String type;           //组织类型
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer creatorId;		//创建人ID
	
	@CheckField(CheckName = CheckNameType.CREATOR,StrLength = 50)
	private String creator;			//创建人
	
	@CheckField(CheckName = CheckNameType.CREATETIME)
	private Date createTime;		//创建时间
	
	@CheckField(CheckName = CheckNameType.ID)
	private Integer editorId;		//修改人ID
	
	@CheckField(CheckName = CheckNameType.EDITOR,StrLength = 50)
	private String editor;			//修改人
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME)
	private Date maintainTime;		//修改时间
	
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries="condition",Name="enabled")
	private Integer enabled;		//启用标志
	
	private Integer orderId;		//排序
	
	@CheckField(CheckName = CheckNameType.DES,StrLength = 200)
	private String  des;			//说明
	
	@ResourceMember(InQueries="search",Name="$codeList" ,OnlyQuery =true)
	private List<String> codeList;
	
	@ResourceMember(InQueries="search",Name="$idList" ,OnlyQuery =true)
	private List<Integer> idList;
	
	@ResourceMember(InQueries="condition,search",Name="$top" ,OnlyQuery =true)
	private Integer top;
	
	@ResourceMember(InQueries="condition",Name="$skip" ,OnlyQuery =true)
	private Integer skip;
	
	

	/**
	 * 
	 */
	public OrgView() {
		super();
	}

	/**
	 * @param name
	 * @param shortName
	 * @param enabled
	 * @param codeList
	 * @param idList
	 * @param top
	 * @param skip
	 */
	public OrgView(String name, String shortName, Integer enabled,
			List<String> codeList, List<Integer> idList, Integer top,
			Integer skip) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.enabled = enabled;
		this.codeList = codeList;
		this.idList = idList;
		this.top = top;
		this.skip = skip;
	}

	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the orgViewId
	 */
	public Integer getOrgViewId() {
		return orgViewId;
	}

	
	/**
	 * @return the appId
	 */
	public Integer getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	/**
	 * @param orgViewId the orgViewId to set
	 */
	public void setOrgViewId(Integer orgViewId) {
		this.orgViewId = orgViewId;
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
