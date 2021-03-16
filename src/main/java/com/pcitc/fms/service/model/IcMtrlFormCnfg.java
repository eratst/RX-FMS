package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询")
public class IcMtrlFormCnfg extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@CheckField(CheckName = CheckNameType.IDFORQUERY)
	private Long mtrlFormCnfgId;

	private Long mtrlId;

	@ResourceMember(InQueries = "condition", Name = "mtrlCode")
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "物料编码")
	private String mtrlCode;

	@ResourceMember(InQueries = "condition", Name = "mtrlName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "物料名称")
	private String mtrlName;

	@ResourceMember(InQueries = "condition", Name = "nodeId")
	private Long nodeId;

	@ResourceMember(InQueries = "condition", Name = "nodeCode")
	@CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "罐编码")
	private String nodeCode;

	@ResourceMember(InQueries = "condition", Name = "nodeName")
	@CheckField(CheckName = CheckNameType.NAME, StrLength = 80, Explain = "罐名称")
	private String nodeName;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "罐公式")
	private String tankForm;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50, Explain = "创建人编码")
	private String crtUserCode;

	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;

	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50, Explain = "最后维护人编码")
	private String mntUserCode;

	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;

	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

	@CheckField(CheckName = CheckNameType.DES, StrLength = 200, Explain = "描述")
	private String des;

	@CheckField(CheckName = CheckNameType.SORTNUM, Explain = "排序")
	private Integer sortNum;

	private Integer version;

	@CheckField(CheckName = CheckNameType.ENABLED, Explain = "是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;

	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$top")
	private Integer top;

	@CheckField(CheckName = CheckNameType.PAGEINFO )
	@ResourceMember(InTemplate = false, InQueries = "condition", OnlyQuery = true, Name = "$skip")
	private Integer skip = 0;

	public Long getMtrlFormCnfgId() {
		return mtrlFormCnfgId;
	}

	public void setMtrlFormCnfgId(Long mtrlFormCnfgId) {
		this.mtrlFormCnfgId = mtrlFormCnfgId;
	}

	public Long getMtrlId() {
		return mtrlId;
	}

	public void setMtrlId(Long mtrlId) {
		this.mtrlId = mtrlId;
	}

	public String getMtrlCode() {
		return mtrlCode;
	}

	public void setMtrlCode(String mtrlCode) {
		this.mtrlCode = mtrlCode;
	}

	public String getMtrlName() {
		return mtrlName;
	}

	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}

	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getTankForm() {
		return tankForm;
	}

	public void setTankForm(String tankForm) {
		this.tankForm = tankForm;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
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

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

}
