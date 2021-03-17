package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;
import com.pcitc.fms.common.dispatcher.SysGlobal;

//import com.pcitc.fms.common.annotation.RegionMember;


import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@Entity
@Table(name = "T_PM_TANKAREA")
public class TankArea  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column( name = "AREA_ID")
	@SpecialResource(name="a.tankAreaId")
	private Long tankAreaId;
	//区域编码
	@Column( name="AREA_CODE")
	@SpecialResource(name="a.tankAreaCode")
	private String tankAreaCode;
	
	@Transient
	@SpecialResource(name="ad.name")
	private String tankAreaName;
	@Transient
	@SpecialResource(name="ad.shortName")
	private String tankAreaAlias;
	@Transient
	@SpecialResource(name="org.orgId")
	private Long orgId;
	@Transient
	@SpecialResource(name="org.orgCode")
	private String orgCode;
	@Transient
	@SpecialResource(name="org.orgName")
	private String orgName;
	@Transient
	@SpecialResource(name="org.orgAlias")
	private String orgAlias;
	
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
	//排序
	@Column( name="SORT_NUM")
	@SpecialResource(name="a.sortNum")
	private Integer sortNum;
	//说明
	@Transient
	@SpecialResource(name="ad.des")
	private String des;
	@Transient
	@SpecialResource(name="ad.enabled")
	private Integer inUse;
	//乐观锁版本
	@Column( name="VERSION")
	@SpecialResource(name="a.version")
	private Integer version;
	//罐区业务类型
	@Column( name="TANKAREATYPE_ID")
	@SpecialResource(name="a.tankAreaTypeId")
	private Long tankAreaTypeId;
	@Transient
	@SpecialResource(name="tt.tankAreaTypeCode")
	private String tankAreaTypeCode;
	@Transient
	@SpecialResource(name="tt.tankAreaTyprName")
	private String tankAreaTypeName;
	//罐区工艺类型
	@Column( name="TANKAREATECHNIC_ID")
	@SpecialResource(name="a.technicId")
	private Long technicId;
	@SpecialResource(name="ta.technicCode")
	@Transient
	private String technicCode;
	@Transient
	@SpecialResource(name="ta.technicName")
	private String technicName;
	
	@Transient
	private String areaLatitude;
	
	@Transient
	private String areaAltitude;
	
	@Transient
	private String areaLongitude;
	
	
	
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
	public String getTechnicCode() {
		return technicCode;
	}
	public void setTechnicCode(String technicCode) {
		this.technicCode = technicCode;
	}
	public String getTechnicName() {
		return technicName;
	}
	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}
	public TankArea(Long tankAreaId, String tankAreaCode, String tankAreaName, String tankAreaAlias, Long orgId,
			String orgCode, String orgName, String orgAlias, String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, Integer sortNum, String des, Integer inUse,
			Integer version, Long tankAreaTypeId, String tankAreaTypeCode, String tankAreaTypeName,
			Long technicId, String technicCode, String technicName,String areaLatitude,String areaAltitude,String areaLongitude) {
		super();
		this.tankAreaId = tankAreaId;
		this.tankAreaCode = tankAreaCode;
		this.tankAreaName = tankAreaName;
		this.tankAreaAlias = tankAreaAlias;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgAlias = orgAlias;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.sortNum = sortNum;
		this.des = des;
		this.inUse = inUse;
		this.version = version;
		this.tankAreaTypeId = tankAreaTypeId;
		this.tankAreaTypeCode = tankAreaTypeCode;
		this.tankAreaTypeName = tankAreaTypeName;
		this.technicId = technicId;
		this.technicCode = technicCode;
		this.technicName = technicName;
		this.areaLongitude=areaLongitude;
	    this.areaAltitude=areaAltitude;
		this.areaLatitude=areaLatitude;
	}
	public String getTankAreaCode() {
		return tankAreaCode;
	}
	public void setTankAreaCode(String tankAreaCode) {
		this.tankAreaCode = tankAreaCode;
	}
	public String getTankAreaName() {
		return tankAreaName;
	}
	public void setTankAreaName(String tankAreaName) {
		this.tankAreaName = tankAreaName;
	}
	public String getTankAreaAlias() {
		return tankAreaAlias;
	}
	public void setTankAreaAlias(String tankAreaAlias) {
		this.tankAreaAlias = tankAreaAlias;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
	public String getTankAreaTypeCode() {
		return tankAreaTypeCode;
	}
	public void setTankAreaTypeCode(String tankAreaTypeCode) {
		this.tankAreaTypeCode = tankAreaTypeCode;
	}
	public String getTankAreaTypeName() {
		return tankAreaTypeName;
	}
	public void setTankAreaTypeName(String tankAreaTypeName) {
		this.tankAreaTypeName = tankAreaTypeName;
	}
	
	
	
	
	
}
