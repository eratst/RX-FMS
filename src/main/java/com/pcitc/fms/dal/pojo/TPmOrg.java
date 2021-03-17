package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_PM_ORG")
public class TPmOrg implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ORG_ID")
	private Long orgId;

	@Column(name = "ORG_CODE")
	private String orgCode;

	@Column(name = "ORG_NAME")
	private String orgName; // 组织机构名称

	@Column(name = "ORG_ALIAS")
	private String orgAlias; // 组织机构简称

	@Column(name = "ORGTYPE_ID")
	private Long orgTypeId; // 组织机构类型

	@Transient
	private String orgType; // 组织机构类型(用于显示)

	@Transient
	private String orgTypeCode;

	@Transient
	private String orgTypeName;

	@Column(name = "INUSE")
	private Integer inUse;// 状态

	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;// 最后维护人名称

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "SORT_NUM")
	private Integer sortNum;// 排序

	@Column(name = "DES")
	private String des;// 描述

	@Column(name = "ORG_LATITUDE")
	private String orgLatitude;

	@Column(name = "ORG_ALTITUDE")
	private String orgAltitude;

	@Column(name = "ORG_LONGITUDE")
	private String orgLongitude;

	@Column(name = "UPPER_ORG_ID")
	private Long upperOrgId;

	@Transient
	private String upperOrgCode;

	@Transient
	private String upperOrgName;

	@Transient
	private String upperOrgAlias;

	public TPmOrg() {
		super();
	}

	//标准组织机构树
	public TPmOrg(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId, String orgTypeCode,
			String orgTypeName, String orgType, String des, Long upperOrgId, String upperOrgCode,
			String upperOrgName, String upperOrgAlias) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
		this.orgTypeCode = orgTypeCode;
		this.orgTypeName = orgTypeName;
		this.orgType = orgType;
		this.des = des;
		this.upperOrgId = upperOrgId;
		this.upperOrgCode = upperOrgCode;
		this.upperOrgName = upperOrgName;
		this.upperOrgAlias = upperOrgAlias;
	}

	/**
	 * @param orgId
	 * @param orgCode
	 * @param orgName
	 * @param orgAlias
	 * @param orgTypeId
	 * @param dataStatus
	 * @param crtUserId
	 * @param crtUserName
	 * @param crtDate
	 * @param mntUserId
	 * @param mntUserName
	 * @param des
	 */
	public TPmOrg(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId, Integer inUse,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
			String des, String orgLatitude, String orgAltitude, String orgLongitude) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.orgLatitude = orgLatitude;
		this.orgAltitude = orgAltitude;
		this.orgLongitude = orgLongitude;
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

	public Long getUpperOrgId() {
		return upperOrgId;
	}

	public void setUpperOrgId(Long upperOrgId) {
		this.upperOrgId = upperOrgId;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getUpperOrgName() {
		return upperOrgName;
	}

	public void setUpperOrgName(String upperOrgName) {
		this.upperOrgName = upperOrgName;
	}

	public String getUpperOrgAlias() {
		return upperOrgAlias;
	}

	public void setUpperOrgAlias(String upperOrgAlias) {
		this.upperOrgAlias = upperOrgAlias;
	}

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

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	// /**
	// * Title: getId
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getId()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public Integer getId() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setId
	// * Description: TODO task mark zhenqiang.zhao
	// * @param id
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#setId(java.lang.Integer)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setId(Integer id) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getCode
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getCode()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public String getCode() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setCode
	// * Description: TODO task mark zhenqiang.zhao
	// * @param nodeCode
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#setCode(java.lang.String)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setCode(String nodeCode) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getName
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getName()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public String getName() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setName
	// * Description: TODO task mark zhenqiang.zhao
	// * @param nodeName
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#setName(java.lang.String)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setName(String nodeName) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getType
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getType()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public String getType() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setType
	// * Description: TODO task mark zhenqiang.zhao
	// * @param nodeType
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#setType(java.lang.String)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setType(String nodeType) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getParentId
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentId()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public Integer getParentId() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setParentId
	// * Description: TODO task mark zhenqiang.zhao
	// * @param id
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#setParentId(java.lang.Integer)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setParentId(Integer id) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getParentCode
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentCode()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public String getParentCode() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setParentCode
	// * Description: TODO task mark zhenqiang.zhao
	// * @param code
	// * @see
	// com.pcitc.fms.dal.pojo.INodeAndArea#setParentCode(java.lang.String)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setParentCode(String code) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// /**
	// * Title: getParentType
	// * Description: TODO task mark zhenqiang.zhao
	// * @return
	// * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentType()
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public String getParentType() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * Title: setParentType
	// * Description: TODO task mark zhenqiang.zhao
	// * @param nodeType
	// * @see
	// com.pcitc.fms.dal.pojo.INodeAndArea#setParentType(java.lang.String)
	// * @date 2017年11月20日
	// * @author 赵振强
	// */
	// @Override
	// public void setParentType(String nodeType) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	//

}
