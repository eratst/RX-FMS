package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class ProduceFactory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long orgId;

	private String orgCode;

	private Long fctrBlockId;

	private String fctrBlockCode;

	private String fctrBlockName;

	private Long businessTypeId;

	private String businessTypeCode;

	private String businessTypeName;

	private String orgName;

	private String orgAlias;

	private Long orgTypeId;

	private String orgTypeCode; // 组织机构类型(用于显示)

	private String orgTypeName; // 组织机构类型(用于显示)

	private Integer inUse;

	private String crtUserCode;

	private String crtUserName;

	private Date crtDate;

	private String mntUserCode;

	private String mntUserName;

	private Date mntDate;

	private Integer version;

	private Integer sortNum;

	private String des;
	
	private String orgLatitude;
	
	private String orgAltitude;
		
	private String orgLongitude;
	
	

	public String getOrgLatitude() {
		return orgLatitude;
	}

	public void setOrgLatitude(String orgLatitude) {
		this.orgLatitude = orgLatitude;
	}

	public String getOrgAltitude() {
		return orgAltitude;
	}

	public void setOrgAltitude(String orgAltitude) {
		this.orgAltitude = orgAltitude;
	}

	public String getOrgLongitude() {
		return orgLongitude;
	}

	public void setOrgLongitude(String orgLongitude) {
		this.orgLongitude = orgLongitude;
	}

	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getFctrBlockId() {
		return fctrBlockId;
	}

	public void setFctrBlockId(Long fctrBlockId) {
		this.fctrBlockId = fctrBlockId;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getCrtUserCode() {
		return crtUserCode;
	}

	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}

	public String getMntUserCode() {
		return mntUserCode;
	}

	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getFctrBlockCode() {
		return fctrBlockCode;
	}

	public void setFctrBlockCode(String fctrBlockCode) {
		this.fctrBlockCode = fctrBlockCode;
	}

	public String getFctrBlockName() {
		return fctrBlockName;
	}

	public void setFctrBlockName(String fctrBlockName) {
		this.fctrBlockName = fctrBlockName;
	}


	public String getBusinessTypeCode() {
		if (this.businessTypeId == 1) {
			return "1";
		} 
		if (this.businessTypeId == 2) {
			return "2";
		}
		if (this.businessTypeId == 3) {
			return "3";
		}
		if (this.businessTypeId == 4) {
			return "4";
		}
		if (this.businessTypeId == 9) {
			return "9";
		}
		if (this.businessTypeId == 0) {
			return "0";
		}
		return null;
		
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getBusinessTypeName() {
		if (this.businessTypeId == 1) {
			return "炼油";
		}
		if (this.businessTypeId == 2) {
			return "化工";
		}
		if (this.businessTypeId == 3) {
			return "化纤";
		}
		if (this.businessTypeId == 4) {
			return "煤化工";
		}
		if (this.businessTypeId == 9) {
			return "动力";
		}
		if (this.businessTypeId == 0) {
			return "其他";
		}
		return null;
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
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


	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

}
