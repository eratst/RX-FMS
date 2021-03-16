package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelProperty;
@Entity
@Table(name = "T_PM_SITE")
@SecondaryTable(name="T_PM_AREA")
public class FactorySiteArea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(table = "T_PM_SITE",name = "AREA_ID")
	private Integer factorySiteId;
	//区域编码
	@Column(table = "T_PM_SITE",name="AREA_CODE")
	private String factorySiteCode;
	//区域类型Id
	@Column(name="AREA_TYPE_ID")
	private Integer areaTypeId;
	//区域名称
	@Column(table = "T_PM_AREA",name="AREA_NAME")
	private String name;
	//区域简称
	@Column(name="AREA_ALIAS")
	private String shortName;
	//上级区域（工厂）
	@Column(name="PARENT_AREA_ID")
	private Integer factoryId;
	//创建人Id
	@Column(table = "T_PM_SITE",name="CRT_USER_ID")
	private Integer creatorId;
	//创建人名称
	@Column(table = "T_PM_SITE",name="CRT_USER_NAME")
	private String creator;
	//创建时间
	@Column(table = "T_PM_SITE",name="CRT_DATE",insertable = false)
	private Date createTime;
	//最后维护人Id
	@Column(table = "T_PM_SITE",name="MNT_USER_ID")
	private Integer editorId;
	//最后维护人
	@Column(table = "T_PM_SITE",name="MNT_USER_NAME")
	private String editor;
	//维护日期
	@Column(table = "T_PM_SITE",name="MNT_DATE",insertable = false)
	private Date maintainTime;
	//排序
	@Column(table = "T_PM_SITE",name="SORT_NUM")
	private Integer sortNum;
	//描述
	@Column(table = "T_PM_SITE",name="DES")
	private String des;
	//乐观锁版本
	@Column(table = "T_PM_SITE",name="VERSION")
	private Integer version;
	//状态
	@Column(table = "T_PM_AREA",name="DATA_STATUS")
	private Integer enabled;



	public FactorySiteArea(Integer factorySiteId, String factorySiteCode, Integer areaTypeId, String name,
			String shortName, Integer factoryId, Integer creatorId, String creator, Date createTime,
			Integer editorId, String editor, Date maintainTime, Integer sortNum, String des, Integer version,
			Integer enabled) {
		super();
		this.factorySiteId = factorySiteId;
		this.factorySiteCode = factorySiteCode;
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
	}


	public FactorySiteArea() {
		super();
	}


	public Integer getFactorySiteId() {
		return factorySiteId;
	}


	public void setFactorySiteId(Integer factorySiteId) {
		this.factorySiteId = factorySiteId;
	}


	public String getFactorySiteCode() {
		return factorySiteCode;
	}


	public void setFactorySiteCode(String factorySiteCode) {
		this.factorySiteCode = factorySiteCode;
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




	public Integer getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
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


	public Integer getEditorId() {
		return editorId;
	}


	public void setEditorId(Integer editorId) {
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


	
	
}
