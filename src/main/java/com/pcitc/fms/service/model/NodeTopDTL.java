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

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class NodeTopDTL extends BaseResRep implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@ResourceMember(InTemplate = false)
	private Long dataId;

	/**
	 * 数据编码
	 */
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "拓扑关系明细编码")
	@ResourceMember(InQueries = "condition", Name = "dataCode")
	private String dataCode;

	/**
	 * S_NODE_ID
	 */
	@ResourceMember(InTemplate = false, Name = "sNodeId")
	private Long snodeId;

	/**
	 * T_NODE_ID
	 */
	@ResourceMember(InTemplate = false, Name = "tNodeId")
	private Long tnodeId;
	/**
	 * 拓扑关系ID
	 */
	@ResourceMember(InTemplate = false)
	private Long topId;

	/**
	 * 拓扑关系编码
	 */
	@ResourceMember(InQueries = "condition", Name = "topCode", InTemplate = false)
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50,Explain="拓扑关系编码")
	private String topCode;

	/**
	 * 拓扑关系名称
	 */
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "拓扑关系名称")
	@ResourceMember(InQueries = "condition", Name = "topName")
	private String topName;

	/**
	 * 创建人ID
	 */
	@ResourceMember(OnlyQuery = true)
	private String crtUserId;

	/**
	 * 创建人名称
	 */
	@ResourceMember(OnlyQuery = true)
	private String crtUserName;

	/**
	 * 创建时间
	 */
	@ResourceMember(OnlyQuery = true)
	private Date crtDate;

	/**
	 * 维护人ID
	 */
	@ResourceMember(OnlyQuery = true)
	private String mntUserId;
	/**
	 * 维护人名称
	 */
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;

	/**
	 * 维护日期
	 */
	@ResourceMember(OnlyQuery = true)
	private Date mntDate;
	/**
	 * 描述
	 */
	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;

	/**
	 * 排序
	 */

	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "源节点名称")
	private String snodeName;

	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "目标节点名称")
	private String tnodeName;

	@ResourceMember(InTemplate = false)
	private String snodeCode;

	@ResourceMember(InTemplate = false)
	private String tnodeCode;

	/**
	 * 乐观锁版本
	 */
	@ResourceMember(OnlyQuery = true)
	private Integer version;

	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private List<String> codeList;
	
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$orderby")
	@CheckField(CheckName = CheckNameType.ORDER)
	private String orderby;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$top")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer top;
	
	@ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$skip")
	@CheckField(CheckName = CheckNameType.PAGEINFO, StrLength = 50)
	private Integer skip = 0;
	
	@ResourceMember(OnlyQuery = true )
	private String rentCode;
	
	@ResourceMember(OnlyQuery = true )
	private String bizCode;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	

	public String getSnodeCode() {
		return snodeCode;
	}

	public void setSnodeCode(String snodeCode) {
		this.snodeCode = snodeCode;
	}

	public String getSnodeName() {
		return snodeName;
	}

	public void setSnodeName(String snodeName) {
		this.snodeName = snodeName;
	}

	

	public String getTnodeCode() {
		return tnodeCode;
	}

	public void setTnodeCode(String tnodeCode) {
		this.tnodeCode = tnodeCode;
	}

	public String getTnodeName() {
		return tnodeName;
	}

	public void setTnodeName(String tnodeName) {
		this.tnodeName = tnodeName;
	}

	

	public String getTopCode() {
		return topCode;
	}

	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	public Date getMntDate() {
		return mntDate;
	}

	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getSnodeId() {
		return snodeId;
	}

	public void setSnodeId(Long snodeId) {
		this.snodeId = snodeId;
	}

	public Long getTnodeId() {
		return tnodeId;
	}

	public void setTnodeId(Long tnodeId) {
		this.tnodeId = tnodeId;
	}

	public Long getTopId() {
		return topId;
	}

	public void setTopId(Long topId) {
		this.topId = topId;
	}

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
	}

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	
	
}
