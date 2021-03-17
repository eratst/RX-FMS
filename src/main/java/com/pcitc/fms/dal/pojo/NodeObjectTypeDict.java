package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="T_FMS_NODE_OBJECTTYPE_DICTION")
public class NodeObjectTypeDict implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="NODE_OBJECTTYPE_ID")
	private Long nodeObjectTypeId;
	@Column(name="CODE")
	private Long code;
	@Column(name="NAME")
	private String name;
	@Column(name="GROUPING")
	private String grouping;
	
	public Long getNodeObjectTypeId() {
		return nodeObjectTypeId;
	}
	public void setNodeObjectTypeId(Long nodeObjectTypeId) {
		this.nodeObjectTypeId = nodeObjectTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrouping() {
		return grouping;
	}
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	
}
