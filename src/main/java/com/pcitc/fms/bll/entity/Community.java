package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
public class Community implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long communityId;
	//区域编码
	private String communityCode;
	//区域名称
	private String communityName;
	//区域简称
	private String communityAlias;
	//区域类型Id
	private Long areaTypeId;
	//上级区域（工厂）
	private Long factoryId;
	//组织机构Id
	private Long orgId;
	
	private String orgCode;
	
	private String orgName;
	
	private String orgAlias;
	
	private String crtUserCode; // 创建人ID

	private String crtUserName;// 创建人名称

	private Date crtDate;// 创建时间

	private String mntUserCode;// 最后维护人ID

	private String mntUserName;// 最后维护人名称

	private Date mntDate;//维护时间
	//排序
	private Integer sortNum;
	//描述
	private String des;
	//乐观锁版本
	private Integer version;
	//状态
	private Integer inUse;
	//区域类型名称
	private String areaType;
	private List<String> codeList;
	private List<Integer> idList;
	
	private String areaLatitude;
	
	private String areaAltitude;
	
	private String areaLongitude;
	public Community() {
		super();
	}
	
	
	
	public String getAreaLatitude() {
		return areaLatitude;
	}



	public void setAreaLatitude(String areaLatitude) {
		this.areaLatitude = areaLatitude;
	}



	public String getAreaAltitude() {
		return areaAltitude;
	}



	public void setAreaAltitude(String areaAltitude) {
		this.areaAltitude = areaAltitude;
	}



	public String getAreaLongitude() {
		return areaLongitude;
	}



	public void setAreaLongitude(String areaLongitude) {
		this.areaLongitude = areaLongitude;
	}



	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	public Long getCommunityId() {
		return communityId;
	}



	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}



	public Long getAreaTypeId() {
		return areaTypeId;
	}



	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}



	public Long getFactoryId() {
		return factoryId;
	}



	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}



	public Long getOrgId() {
		return orgId;
	}



	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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



	public String getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}
	
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityAlias() {
		return communityAlias;
	}

	public void setCommunityAlias(String communityAlias) {
		this.communityAlias = communityAlias;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public List<String> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	
}
