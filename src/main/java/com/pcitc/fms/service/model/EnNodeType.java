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
public class EnNodeType extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate = false)
	private Long enNodeTypeId;
	
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=50,Explain="能源节点类型编码")
	@ResourceMember(InQueries = "condition", Name = "enNodeTypeCode")
	private String enNodeTypeCode;
	
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=80,Explain="能源节点类型名称")
	@ResourceMember(InQueries = "condition", Name = "enNodeTypeName")
	private String enNodeTypeName;

    @ResourceMember(OnlyQuery = true)
    private Long bizId;
    
    @CheckField(CheckName = CheckNameType.CODE, StrLength = 50, Explain = "业务域编码")
    @ResourceMember(InQueries = "condition", Name = "bizCode")
    private String bizCode;
	
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200,Explain="描述")
	private String des;
	
	@CheckField(CheckName = CheckNameType.SORTNUM,Explain="排序")
	private Integer sortNum;
	
	@CheckField(CheckName = CheckNameType.ENABLED,Explain="是否启用")
	@ResourceMember(InQueries = "condition", Name = "inUse")
	private Integer inUse;
	
    @CheckField(CheckName = CheckNameType.ID, Explain = "乐观锁版本")
	private Integer version;
	
    @CheckField(CheckName = CheckNameType.CREATORID, StrLength = 50, Explain = "创建人ID")
	private String crtUserId;
	
	@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 80, Explain = "创建人名称")
	private String crtUserName;
	
	@CheckField(CheckName = CheckNameType.CREATETIME, Explain = "创建时间")
	private Date crtDate;
	
    @CheckField(CheckName = CheckNameType.EDITORID, StrLength = 50, Explain = "最后维护人ID")
	private String mntUserId;
	
	@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 80, Explain = "最后维护人名称")
	private String mntUserName;
	
	@CheckField(CheckName = CheckNameType.MAINTAINTIME, Explain = "维护日期")
	private Date mntDate;

    @ResourceMember(InTemplate = false, InQueries = "search", OnlyQuery = true, Name = "$codeList")
    private List<String> codeList;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$top")
    private Integer top;

    @CheckField(CheckName = CheckNameType.PAGEINFO)
    @ResourceMember(InTemplate = false, InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
    private Integer skip = 0;

    @ResourceMember(OnlyQuery = true)
    private Long rentId;

    @CheckField(CheckName = CheckNameType.CODE, AllowNull = true)
    @ResourceMember(InTemplate = false)
    private String rentCode;

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getEnNodeTypeId() {
		return enNodeTypeId;
	}

	public void setEnNodeTypeId(Long enNodeTypeId) {
		this.enNodeTypeId = enNodeTypeId;
	}

	public String getEnNodeTypeCode() {
		return enNodeTypeCode;
	}

	public void setEnNodeTypeCode(String enNodeTypeCode) {
		this.enNodeTypeCode = enNodeTypeCode;
	}

	public String getEnNodeTypeName() {
		return enNodeTypeName;
	}

	public void setEnNodeTypeName(String enNodeTypeName) {
		this.enNodeTypeName = enNodeTypeName;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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

	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
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

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}

}
