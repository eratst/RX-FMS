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
 * [组态关系表] 拓扑关系主表
 *
 * @author 杨贺
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class TIcNodetopMain extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	@ResourceMember(OnlyQuery = true)
	private Integer topId;// 拓扑关系id
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 36)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topCode")
	private String topCode;// 拓扑关系编码
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topName")
	private String topName;// 拓扑关系名称
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 50)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topAlias")
	private String topAlias;// 拓扑关系简称
	@CheckField(CheckName = CheckNameType.ENABLED)
	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "dataStatus")
	private Integer dataStatus;// 状态
//	@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 20)
	private String crtUserId;// 创建人id
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间
//	@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 20)
	private String mntUserId;// 最后维护人ID
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
	private String mntUserName;// 最后维护人名称

	private Date mntDate;// 最后维护人时间

	private String version;

	private Integer sortNum;
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
	private String des;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$CodeList")
	private List<String> codeList;


	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	/**
	 * 
	 */
	public TIcNodetopMain() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param topCode
	 * @param topName
	 * @param topAlias
	 * @param dataStatus
	 * @param sortNum
	 * @param codeList
	 * @param top
	 * @param skip
	 */
	public TIcNodetopMain(String topCode, String topName, String topAlias, Integer dataStatus, Integer sortNum,
			List<String> codeList, Integer top, Integer skip) {
		super();
		this.topCode = topCode;
		this.topName = topName;
		this.topAlias = topAlias;
		this.dataStatus = dataStatus;
		this.sortNum = sortNum;
		this.codeList = codeList;
		this.top = top;
		this.skip = skip;
	}



	/**
	 * @return the topId
	 */
	public Integer getTopId() {
		return topId;
	}

	/**
	 * @param topId
	 *            the topId to set
	 */
	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	/**
	 * @return the topCode
	 */
	public String getTopCode() {
		return topCode;
	}

	/**
	 * @param topCode
	 *            the topCode to set
	 */
	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	/**
	 * @return the topName
	 */
	public String getTopName() {
		return topName;
	}

	/**
	 * @param topName
	 *            the topName to set
	 */
	public void setTopName(String topName) {
		this.topName = topName;
	}

	/**
	 * @return the topAlias
	 */
	public String getTopAlias() {
		return topAlias;
	}

	/**
	 * @param topAlias
	 *            the topAlias to set
	 */
	public void setTopAlias(String topAlias) {
		this.topAlias = topAlias;
	}

	/**
	 * @return the dataStatus
	 */
	public Integer getDataStatus() {
		return dataStatus;
	}

	/**
	 * @param dataStatus
	 *            the dataStatus to set
	 */
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	/**
	 * @return the crtUserId
	 */
	public String getCrtUserId() {
		return crtUserId;
	}

	/**
	 * @param crtUserId
	 *            the crtUserId to set
	 */
	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	/**
	 * @return the crtUserName
	 */
	public String getCrtUserName() {
		return crtUserName;
	}

	/**
	 * @param crtUserName
	 *            the crtUserName to set
	 */
	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	/**
	 * @return the crtDate
	 */
	public Date getCrtDate() {
		return crtDate;
	}

	/**
	 * @param crtDate
	 *            the crtDate to set
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	/**
	 * @return the mntUserId
	 */
	public String getMntUserId() {
		return mntUserId;
	}

	/**
	 * @param mntUserId
	 *            the mntUserId to set
	 */
	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
	}

	/**
	 * @return the mntUserName
	 */
	public String getMntUserName() {
		return mntUserName;
	}

	/**
	 * @param mntUserName
	 *            the mntUserName to set
	 */
	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	/**
	 * @return the mntDate
	 */
	public Date getMntDate() {
		return mntDate;
	}

	/**
	 * @param mntDate
	 *            the mntDate to set
	 */
	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the sortNum
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum
	 *            the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des
	 *            the des to set
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
	 * @return the top
	 */
	public Integer getTop() {
		return top;
	}

	/**
	 * @param top
	 *            the top to set
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
	 * @param skip
	 *            the skip to set
	 */
	public void setSkip(Integer skip) {
		this.skip = skip;
	}

}
