package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_ENTERPRISE")
public class Enterprise implements INodeAndArea, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SpecialResource(name="t.orgId")
	@Column(name = "ORG_ID")
	private Long orgId;

	@SpecialResource(name="t.orgCode")
	@Column(name = "ORG_CODE")
	private String orgCode;

	@SpecialResource(name="org.orgName")
	@Transient
	private String orgName; // 组织机构名称

	@SpecialResource(name="org.orgAlias")
	@Transient
	private String orgAlias; // 组织机构简称

	@SpecialResource(name="org.orgTypeId")
	@Transient
	private Long orgTypeId; // 组织机构类型

	@SpecialResource(name="orgType.orgTypeCode")
	@Transient
	private String orgTypeCode; // 组织机构类型(用于显示)

	@SpecialResource(name="orgType.orgTypeName")
	@Transient
	private String orgTypeName; // 组织机构类型(用于显示)

	@SpecialResource(name="entrType.entrTypeId")
	@Column(name = "ENTRTYPE_ID")
	private Long entrTypeId;

	@SpecialResource(name="entrType.entrTypeCode")
	@Transient
	private String entrTypeCode;

	@SpecialResource(name="entrType.entrTypeName")
	@Transient
	private String entrTypeName;

	@Transient
	private String crtUserCode; // 创建人ID

	@Transient
	private String crtUserName;// 创建人名称

	@Transient
	private Date crtDate;// 创建时间

	@Transient
	private String mntUserCode;// 最后维护人ID

	@Transient
	private String mntUserName;// 最后维护人名称

	@Transient
	private Date mntDate;//维护时间

	@SpecialResource(name="org.inUse")
	@Transient
	private Integer inUse;// 状态

	@SpecialResource(name="org.des")
	@Column(name = "DES")
	private String des;// 描述

	@Column(name = "VERSION")
	private Integer version;

	@SpecialResource(name="t.sortNum")
	@Column(name = "SORT_NUM")
	private Integer sortNum;// 排序
	
	@Transient
	private String orgLatitude;
	
	@Transient
	private String orgAltitude;
	
	@Transient
	private String orgLongitude;
	
	public Enterprise() {
	}

	/**
	 * @param orgId
	 * @param orgCode
	 * @param orgName
	 * @param orgAlias
	 * @param orgTypeId
	 * @param orgType
	 * @param dataStatus
	 * @param crtUserId
	 * @param crtUserName
	 * @param crtDate
	 * @param mntUserId
	 * @param mntUserName
	 * @param mntDate
	 * @param version
	 * @param sortNum
	 * @param des
	 */
	public Enterprise(Long orgId, String orgCode, String orgName, String orgAlias, Long orgTypeId,
			String orgTypeCode, String orgTypeName, String entryTypeCode, String entryTypeName, Integer inUse,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
			String des, Integer sortNum,String orgLatitude,String orgAltitude,String orgLongitude) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.orgTypeId = orgTypeId;
		this.orgTypeCode = orgTypeCode;
		this.orgTypeName = orgTypeName;
		this.entrTypeCode = entryTypeCode;
		this.entrTypeName = entryTypeName;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.sortNum = sortNum;
		this.orgLatitude=orgLatitude;
		this.orgAltitude=orgAltitude;
		this.orgLongitude=orgLongitude;
	}
	
	
	
	
	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getEntrTypeId() {
		return entrTypeId;
	}

	public void setEntrTypeId(Long entrTypeId) {
		this.entrTypeId = entrTypeId;
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


	public String getEntrTypeCode() {
		return entrTypeCode;
	}

	public void setEntrTypeCode(String entrTypeCode) {
		this.entrTypeCode = entrTypeCode;
	}

	public String getEntrTypeName() {
		return entrTypeName;
	}

	public void setEntrTypeName(String entrTypeName) {
		this.entrTypeName = entrTypeName;
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

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * Title: getId Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getId()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setId Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param id
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setId(java.lang.Integer)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getCode Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getCode()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setCode Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param nodeCode
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setCode(java.lang.String)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setCode(String nodeCode) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getName Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getName()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setName Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param nodeName
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setName(java.lang.String)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setName(String nodeName) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getType Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getType()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setType Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param nodeType
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setType(java.lang.String)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setType(String nodeType) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getParentId Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentId()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public Integer getParentId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setParentId Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param id
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setParentId(java.lang.Integer)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setParentId(Integer id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getParentCode Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentCode()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public String getParentCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setParentCode Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param code
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setParentCode(java.lang.String)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setParentCode(String code) {
		// TODO Auto-generated method stub

	}

	/**
	 * Title: getParentType Description: TODO task mark zhenqiang.zhao
	 * 
	 * @return
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentType()
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public String getParentType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Title: setParentType Description: TODO task mark zhenqiang.zhao
	 * 
	 * @param nodeType
	 * @see com.pcitc.fms.dal.pojo.INodeAndArea#setParentType(java.lang.String)
	 * @date 2017年11月21日
	 * @author zhenqiang.zhao
	 */
	@Override
	public void setParentType(String nodeType) {
		// TODO Auto-generated method stub

	}

}
