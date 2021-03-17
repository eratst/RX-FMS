package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "T_PM_LOADRACK")
//@SequenceGenerator(name = "mseq", sequenceName = "SEQ_LOADINGDOCK", allocationSize = 1)
public class LoadingDock implements Serializable{

	
	@Id
	@Column( name = "AREA_ID")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMINISTRATION_GENERATOR")
//	@SequenceGenerator(name="ADMINISTRATION_GENERATOR", sequenceName="SEQ_ADMINISTRATION",allocationSize=1) 
	private Integer loadingDockId;
	//区域编码
	@Column( name="AREA_CODE")
	private String loadingDockCode;
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
	//装卸台工艺类型
	@Column(name="TECHNIC_ID")
	private Integer technicId;
	@Transient
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private LoadrackTechnic loadrackTechnic;
	public LoadingDock() {
		super();
	}
	public LoadingDock(Integer loadingDockId, String loadingDockCode, String creatorId, String creator,
			Date createTime, String editorId, String editor, Date maintainTime, Integer sortNum,
			Integer version, Integer technicId) {
		super();
		this.loadingDockId = loadingDockId;
		this.loadingDockCode = loadingDockCode;
		this.creatorId = creatorId;
		this.creator = creator;
		this.createTime = createTime;
		this.editorId = editorId;
		this.editor = editor;
		this.maintainTime = maintainTime;
		this.sortNum = sortNum;
		this.version = version;
		this.technicId = technicId;
	}
	
	public LoadrackTechnic getLoadrackTechnic() {
		return loadrackTechnic;
	}
	public void setLoadrackTechnic(LoadrackTechnic loadrackTechnic) {
		this.loadrackTechnic = loadrackTechnic;
	}
	public Integer getLoadingDockId() {
		return loadingDockId;
	}
	public void setLoadingDockId(Integer loadingDockId) {
		this.loadingDockId = loadingDockId;
	}
	public String getLoadingDockCode() {
		return loadingDockCode;
	}
	public void setLoadingDockCode(String loadingDockCode) {
		this.loadingDockCode = loadingDockCode;
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
	public Integer getTechnicId() {
		return technicId;
	}
	public void setTechnicId(Integer technicId) {
		this.technicId = technicId;
	}
	
}
