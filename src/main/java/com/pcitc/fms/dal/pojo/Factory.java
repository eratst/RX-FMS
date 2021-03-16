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

import com.pcitc.fms.common.annotation.RegionMember;

@Entity
@Table(name = "T_FMS_FACTORY")
@SequenceGenerator(name = "mseq", sequenceName = "T_FMS_FACTORY_MSEQ")
public class Factory implements Serializable,INodeAndArea{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FACTORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mseq")
	private Integer factoryId;
	
	@RegionMember
	@Column(name="CODE")
	private String code;
	@Column(name="NAME")
	private String name;
	@Column(name="SHORT_NAME")
	private String shortName;
	@Column(name="BUSINESS_TYPE")
	private String businessType;
	@Column(name="ENABLED")
	private Integer enabled;
	@Column(name="CRT_USER")
	private String creator;
	@Column(name="CREATE_TIME", insertable = false)
	private Date createTime;
	@Column(name="MNT_USER")
	private String editor;
	@Column(name="MAINTAIN_TIME", insertable = false)
	private Date maintainTime;
	@Column(name="DES")
	private String des;
	@Column(name="CRT_USER_ID")
	private String creatorId;
	@Column(name="MNT_USER_ID")
	private String editorId;
	
	
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
	public Integer getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setenabled(Integer enabled) {
		this.enabled = enabled;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public Integer getId() {
		return this.getFactoryId();
	}
	@Override
	public void setId(Integer id) {
		this.setFactoryId(id);
		
	}
	@Override
	public String getType() {
		return null;
	}
	@Override
	public void setType(String nodeType) {
		
	}
	@Override
	public Integer getParentId() {
		return null;
	}
	@Override
	public void setParentId(Integer id) {
		
	}
	@Override
	public String getParentType() {
		return null;
	}
	@Override
	public void setParentType(String nodeType) {
	}
	/** 
	* Title: getParentCode
	* Description: TODO task mark zhenqiang.zhao
	* @return
	* @see com.pcitc.fms.dal.pojo.INodeAndArea#getParentCode()
	* @date 2017年10月13日      
	* @author 赵振强  
	*/
	@Override
	public String getParentCode() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	* Title: setParentCode
	* Description: TODO task mark zhenqiang.zhao
	* @param code
	* @see com.pcitc.fms.dal.pojo.INodeAndArea#setParentCode(java.lang.String)
	* @date 2017年10月13日      
	* @author 赵振强  
	*/
	@Override
	public void setParentCode(String code) {
		// TODO Auto-generated method stub
		
	}
}
