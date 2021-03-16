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
 * @date 2017年7月13日
 * @version 1.0
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称支持模糊查询）")
public class OrgUnitModel extends BaseResRep implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orgUnitId;
	
	private String orgUnitCode; // 机构编码
	
	@ResourceMember(InQueries = "condition", Name = "orgUnitName")
	private String orgUnitName;// 机构名称

	
	@ResourceMember(InQueries = "condition", Name = "shortName")
	private String orgUnitAlia; // 
	
	private String englishName;// 机构的英文名称

	@ResourceMember(InQueries = "condition", Name = "typeId")
	private Integer orgUnitTypeId;// 类型Id

	
	private String contract;// 联系人

	
	private Integer orgId;// 机构Id

	
	private Integer parentId;// 机构单元父级Id

	
	private Integer rootId;
	
	private String crtUserId;// 创建人ID

	
	private String crtUserName;// 创建人

	// 创建时间
	private Date crtUserDate;

	
	private String mntUserId;// 修改人Id

	
	private String mntUserName;// 修改人(更新时必填)

	private Date mntUserDate;
	
	@ResourceMember(InQueries = "condition", Name = "enabled")
	private Integer enabled;
	
	private String orderId;// 
	
	private String version;// 排序
	
	
	private String des;// 说明


	public Integer getOrgUnitId() {
		return orgUnitId;
	}


	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}


	public String getOrgUnitCode() {
		return orgUnitCode;
	}


	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}


	public String getOrgUnitName() {
		return orgUnitName;
	}


	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}


	public String getOrgUnitAlia() {
		return orgUnitAlia;
	}


	public void setOrgUnitAlia(String orgUnitAlia) {
		this.orgUnitAlia = orgUnitAlia;
	}


	public String getEnglishName() {
		return englishName;
	}


	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}


	public Integer getOrgUnitTypeId() {
		return orgUnitTypeId;
	}


	public void setOrgUnitTypeId(Integer orgUnitTypeId) {
		this.orgUnitTypeId = orgUnitTypeId;
	}


	public String getContract() {
		return contract;
	}


	public void setContract(String contract) {
		this.contract = contract;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public Integer getRootId() {
		return rootId;
	}


	public void setRootId(Integer rootId) {
		this.rootId = rootId;
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


	public Date getCrtUserDate() {
		return crtUserDate;
	}


	public void setCrtUserDate(Date crtUserDate) {
		this.crtUserDate = crtUserDate;
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


	public Date getMntUserDate() {
		return mntUserDate;
	}


	public void setMntUserDate(Date mntUserDate) {
		this.mntUserDate = mntUserDate;
	}


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}
	
	

	
}
