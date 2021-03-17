package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelProperty;
@Entity
@Table(name = "T_PM_TANKAREA")
@SecondaryTables({
	@SecondaryTable(name="T_PM_AREA"),
	@SecondaryTable(name="T_PM_TANKAREATECHNIC"),
	@SecondaryTable(name="T_PM_TANKAREATYPE")
})
public class TankAreaArea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(table = "T_PM_TANKAREA",name = "AREA_ID")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMINISTRATION_GENERATOR")
//	@SequenceGenerator(name="ADMINISTRATION_GENERATOR", sequenceName="SEQ_ADMINISTRATION",allocationSize=1) 
	private Integer tankAreaId;
	//区域编码
	@Column(table = "T_PM_TANKAREA",name="AREA_CODE")
	private String tankAreaCode;
	//区域类型Id
	@Column(table = "T_PM_AREA",name="AREA_TYPE_ID")
	private Integer areaTypeId;
	//区域名称
	@Column(table = "T_PM_AREA",name="AREA_NAME")
	private String name;
	//区域简称
	@Column(table = "T_PM_AREA",name="AREA_ALIAS")
	private String shortName;
	//上级区域（工厂）
	@Column(table = "T_PM_AREA",name="PARENT_AREA_ID")
	private Integer factoryId;
	//创建人Id
	@Column(table = "T_PM_TANKAREA",name="CRT_USER_ID")
	private String creatorId;
	//创建人名称
	@Column(table = "T_PM_TANKAREA",name="CRT_USER_NAME")
	private String creator;
	//创建时间
	@Column(table = "T_PM_TANKAREA",name="CRT_DATE",insertable = false)
	private Date createTime;
	//最后维护人Id
	@Column(table = "T_PM_TANKAREA",name="MNT_USER_ID")
	private String editorId;
	//最后维护人
	@Column(table = "T_PM_TANKAREA",name="MNT_USER_NAME")
	private String editor;
	//维护日期
	@Column(table = "T_PM_TANKAREA",name="MNT_DATE",insertable = false)
	private Date maintainTime;
	//排序
	@Column(table = "T_PM_TANKAREA",name="SORT_NUM")
	private Integer sortNum;
	//描述
	@Column(table = "T_PM_TANKAREA",name="DES")
	private String des;
	//乐观锁版本
	@Column(table = "T_PM_TANKAREA",name="VERSION")
	private Integer version;
	//状态
	@Column(table = "T_PM_AREA",name="DATA_STATUS")
	private Integer enabled;
	//罐区业务类型
	@Column(table="T_PM_TANKAREATYPE", name="TANKAREA_TYPE_NAME")
	private String tankAreaType;
	//罐区工艺类型
	@Column(table="T_PM_TANKAREATECHNIC", name="TECHNIC_NAME")
	private String technic;


	
	public TankAreaArea() {
		super();
	}



	public TankAreaArea(Integer tankAreaId, String tankAreaCode, Integer areaTypeId, String name, String shortName,
			Integer factoryId, String creatorId, String creator, Date createTime, String editorId,
			String editor, Date maintainTime, Integer sortNum, String des, Integer version, Integer enabled,
			String tankAreaType, String technic) {
		super();
		this.tankAreaId = tankAreaId;
		this.tankAreaCode = tankAreaCode;
		this.areaTypeId = areaTypeId;
		this.name = name;
		this.shortName = shortName;
		this.factoryId = factoryId;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.des = des;
		this.version = version;
		this.enabled = enabled;
		this.tankAreaType = tankAreaType;
		this.technic = technic;
	}



	public Integer getTankAreaId() {
		return tankAreaId;
	}



	public void setTankAreaId(Integer tankAreaId) {
		this.tankAreaId = tankAreaId;
	}



	public String getTankAreaCode() {
		return tankAreaCode;
	}



	public void setTankAreaCode(String tankAreaCode) {
		this.tankAreaCode = tankAreaCode;
	}



	public Integer getAreaTypeId() {
		return areaTypeId;
	}



	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getShortName() {
		return shortName;
	}



	public void setShortName(String shortName) {
		this.shortName = shortName;
	}



	public Integer getFactoryId() {
		return factoryId;
	}



	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getCreator() {
		return creator;
	}



	public void setCreator(String creator) {
		this.creator = creator;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}


	public String getEditorId() {
		return editorId;
	}


	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}
	public String getEditor() {
		return editor;
	}



	public void setEditor(String editor) {
		this.editor = editor;
	}



	public Date getMaintainTime() {
		return maintainTime;
	}



	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
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



	public Integer getEnabled() {
		return enabled;
	}



	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}



	public String getTankAreaType() {
		return tankAreaType;
	}



	public void setTankAreaType(String tankAreaType) {
		this.tankAreaType = tankAreaType;
	}



	public String getTechnic() {
		return technic;
	}



	public void setTechnic(String technic) {
		this.technic = technic;
	}


}
