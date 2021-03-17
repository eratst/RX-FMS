package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * The type T ic nodetop dtl.
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class TIcNodetopDTL extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	@ResourceMember( OnlyQuery = true)
	private Integer dataId;  //主键ID
//	@ResourceMember( OnlyQuery = true)
	private String dataCode;  //主键ID
	@ResourceMember
	private Integer snodeId;//源节点ID

	@ResourceMember( OnlyQuery = true)
	private Integer tnodeId; //目标节点ID

	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "sNodeCode")
	private String snodeCode; //源节点CODE

	private String snodeAlias; //源节点简称

	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "tNodeCode")
	private String tnodeCode; //目标节点CODE

	private String tnodeAlias; //目标节点简称

	@ResourceMember( OnlyQuery = true)
	private Integer topId; //拓扑结构ID

	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topCode")
	private String topCode; //拓扑关系CODE

	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topName")
	private String topName; //拓扑关系名称

	@ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "topAlias")
	private String topAlias; //拓扑关系简称

	private String crtUserId; //创建人ID

	private String crtUserName;//创建人名称

	private Date crtDate;//创建时间

	private Integer mntUserId;//最后维护人ID

	private String mntUserName;//最后维护人名称

	private Date mntDate;//最后维护人名称

	private Integer version;

	private Integer sortNum;//排序

	private String des;//描述

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$sCodeList")
	private List<String> scodeList;

	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$tCodeList")
	private List<String> tcodeList;

	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
	private Integer skip =  0;

	@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$opertype" ,InTemplate = false)
	private String opertype;

	/**
	 * Instantiates a new T ic nodetop dtl.
	 */
	public TIcNodetopDTL() {
		super();
	}

	/**
	 * Instantiates a new T ic nodetop dtl.
	 *
	 * @param snodeCode the snode code
	 * @param tnodeCode the tnode code
	 * @param topCode the top code
	 * @param topName the top name
	 * @param topAlias the top alias
	 * @param sortNum the sort num
	 * @param scodeList the scode list
	 * @param tcodeList the tcode list
	 * @param top the top
	 * @param skip the skip
	 * @param opertype the opertype
	 */
	public TIcNodetopDTL(String snodeCode, String tnodeCode, String topCode, String topName, String topAlias, Integer sortNum, List<String> scodeList,
			List<String> tcodeList, Integer top, Integer skip,String opertype) {
		this.snodeCode = snodeCode;
		this.tnodeCode = tnodeCode;
		this.topCode = topCode;
		this.topName = topName;
		this.topAlias = topAlias;
		this.sortNum = sortNum;
		this.scodeList = scodeList;
		this.tcodeList = tcodeList;
		this.top = top;
		this.skip = skip;
		this.opertype = opertype;
	}


	/**
	 * @return the dataCode
	 */
	public String getDataCode() {
		return dataCode;
	}

	/**
	 * @param dataCode the dataCode to set
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * Gets opertype.
	 *
	 * @return the opertype
	 */
	public String getOpertype() {
		return opertype;
	}

	/**
	 * Sets opertype.
	 *
	 * @param opertype the opertype
	 */
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	/**
	 * Gets data id.
	 *
	 * @return the data id
	 */
	public Integer getDataId() {
		return dataId;
	}

	/**
	 * Sets data id.
	 *
	 * @param dataId the data id
	 */
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	/**
	 * Gets snode id.
	 *
	 * @return the snode id
	 */
	public Integer getSnodeId() {
		return snodeId;
	}

	/**
	 * Sets snode id.
	 *
	 * @param snodeId the snode id
	 */
	public void setSnodeId(Integer snodeId) {
		this.snodeId = snodeId;
	}

	/**
	 * Gets tnode id.
	 *
	 * @return the tnode id
	 */
	public Integer getTnodeId() {
		return tnodeId;
	}

	/**
	 * Sets tnode id.
	 *
	 * @param tnodeId the tnode id
	 */
	public void setTnodeId(Integer tnodeId) {
		this.tnodeId = tnodeId;
	}

	/**
	 * Gets snode code.
	 *
	 * @return the snode code
	 */
	public String getSnodeCode() {
		return snodeCode;
	}

	/**
	 * Sets snode code.
	 *
	 * @param snodeCode the snode code
	 */
	public void setSnodeCode(String snodeCode) {
		this.snodeCode = snodeCode;
	}

	/**
	 * Gets tnode code.
	 *
	 * @return the tnode code
	 */
	public String getTnodeCode() {
		return tnodeCode;
	}

	/**
	 * Sets tnode code.
	 *
	 * @param tnodeCode the tnode code
	 */
	public void setTnodeCode(String tnodeCode) {
		this.tnodeCode = tnodeCode;
	}

	/**
	 * Gets top id.
	 *
	 * @return the top id
	 */
	public Integer getTopId() {
		return topId;
	}

	/**
	 * Sets top id.
	 *
	 * @param topId the top id
	 */
	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	/**
	 * Gets top code.
	 *
	 * @return the top code
	 */
	public String getTopCode() {
		return topCode;
	}

	/**
	 * Sets top code.
	 *
	 * @param topCode the top code
	 */
	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	/**
	 * Gets top name.
	 *
	 * @return the top name
	 */
	public String getTopName() {
		return topName;
	}

	/**
	 * Sets top name.
	 *
	 * @param topName the top name
	 */
	public void setTopName(String topName) {
		this.topName = topName;
	}

	/**
	 * Gets top alias.
	 *
	 * @return the top alias
	 */
	public String getTopAlias() {
		return topAlias;
	}

	/**
	 * Sets top alias.
	 *
	 * @param topAlias the top alias
	 */
	public void setTopAlias(String topAlias) {
		this.topAlias = topAlias;
	}

	/**
	 * Gets crt user id.
	 *
	 * @return the crt user id
	 */
	public String getCrtUserId() {
		return crtUserId;
	}

	/**
	 * Sets crt user id.
	 *
	 * @param crtUserId the crt user id
	 */
	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	/**
	 * Gets crt user name.
	 *
	 * @return the crt user name
	 */
	public String getCrtUserName() {
		return crtUserName;
	}

	/**
	 * Sets crt user name.
	 *
	 * @param crtUserName the crt user name
	 */
	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	/**
	 * Gets crt date.
	 *
	 * @return the crt date
	 */
	public Date getCrtDate() {
		return crtDate;
	}

	/**
	 * Sets crt date.
	 *
	 * @param crtDate the crt date
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	/**
	 * Gets mnt user id.
	 *
	 * @return the mnt user id
	 */
	public Integer getMntUserId() {
		return mntUserId;
	}

	/**
	 * Sets mnt user id.
	 *
	 * @param mntUserId the mnt user id
	 */
	public void setMntUserId(Integer mntUserId) {
		this.mntUserId = mntUserId;
	}

	/**
	 * Gets mnt user name.
	 *
	 * @return the mnt user name
	 */
	public String getMntUserName() {
		return mntUserName;
	}

	/**
	 * Sets mnt user name.
	 *
	 * @param mntUserName the mnt user name
	 */
	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	/**
	 * Gets mnt date.
	 *
	 * @return the mnt date
	 */
	public Date getMntDate() {
		return mntDate;
	}

	/**
	 * Sets mnt date.
	 *
	 * @param mntDate the mnt date
	 */
	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	/**
	 * Gets version.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets version.
	 *
	 * @param version the version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Gets sort num.
	 *
	 * @return the sort num
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * Sets sort num.
	 *
	 * @param sortNum the sort num
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * Gets des.
	 *
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * Sets des.
	 *
	 * @param des the des
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * Gets scode list.
	 *
	 * @return the scode list
	 */
	public List<String> getScodeList() {
		return scodeList;
	}

	/**
	 * Sets scode list.
	 *
	 * @param scodeList the scode list
	 */
	public void setScodeList(List<String> scodeList) {
		this.scodeList = scodeList;
	}

	/**
	 * Gets tcode list.
	 *
	 * @return the tcode list
	 */
	public List<String> getTcodeList() {
		return tcodeList;
	}

	/**
	 * Sets tcode list.
	 *
	 * @param tcodeList the tcode list
	 */
	public void setTcodeList(List<String> tcodeList) {
		this.tcodeList = tcodeList;
	}

	/**
	 * Gets top.
	 *
	 * @return the top
	 */
	public Integer getTop() {
		return top;
	}

	/**
	 * Sets top.
	 *
	 * @param top the top
	 */
	public void setTop(Integer top) {
		this.top = top;
	}

	/**
	 * Gets skip.
	 *
	 * @return the skip
	 */
	public Integer getSkip() {
		return skip;
	}

	/**
	 * Sets skip.
	 *
	 * @param skip the skip
	 */
	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public String getSnodeAlias() {
		return snodeAlias;
	}

	public void setSnodeAlias(String snodeAlias) {
		this.snodeAlias = snodeAlias;
	}

	public String getTnodeAlias() {
		return tnodeAlias;
	}

	public void setTnodeAlias(String tnodeAlias) {
		this.tnodeAlias = tnodeAlias;
	}
}
