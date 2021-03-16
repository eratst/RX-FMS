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
@Table(name = "T_PM_ADMINISTRATION")
//@SecondaryTable(name="T_PM_AREA")
public class Administration implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column( name = "AREA_ID")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMINISTRATION_GENERATOR")
//	@SequenceGenerator(name="ADMINISTRATION_GENERATOR", sequenceName="SEQ_ADMINISTRATION",allocationSize=1) 
	private Integer administrationId;
	//区域编码
	@Column( name="AREA_CODE")
	private String administrationCode;
	//创建人Id
	@Column( name="CRT_USER_ID")
	private String creatorId;
	//创建人名称
	@Column( name="CRT_USER_NAME")
	private String creator;
	//创建时间
	@Column( name="CRT_DATE",insertable = false)
	private Date createTime;
	//最后维护人Id
	@Column( name="MNT_USER_ID")
	private String editorId;
	//最后维护人
	@Column( name="MNT_USER_NAME")
	private String editor;
	//维护日期
	@Column( name="MNT_DATE",insertable = false)
	private Date maintainTime;
	//排序
	@Column( name="SORT_NUM")
	private Integer sortNum;
	//乐观锁版本
	@Column( name="VERSION")
	private Integer version;
	
//	//状态
////	@Column(table = "T_PM_AREA",name="DATA_STATUS")
//	private Integer enabled;

	public Administration(Integer administrationId, String administrationCode,
			String creatorId, String creator, Date createTime, String editorId, String editor,
			Date maintainTime, Integer sortNum, Integer version) {
		super();
		this.administrationId = administrationId;
		this.administrationCode = administrationCode;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.version = version;
		
	}
	

	public String getAdministrationCode() {
		return administrationCode;
	}


	public void setAdministrationCode(String administrationCode) {
		this.administrationCode = administrationCode;
	}


	public Administration() {
		super();
	}
	public Integer getAdministrationId() {
		return administrationId;
	}
	public void setAdministrationId(Integer administrationId) {
		this.administrationId = administrationId;
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
	
	public Integer getVersion() {
		return version;
	}



	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
	
}
