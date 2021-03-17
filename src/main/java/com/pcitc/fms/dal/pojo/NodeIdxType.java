package com.pcitc.fms.dal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_PM_NODE_IDX_TYPE")
public class NodeIdxType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="IDX_ID")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idxId;
	@Column(name="IDX_CODE")
	private String idxCode;
	@Column(name="NODE_TYPE	")
	private String nodeType;
	@Column(name="IDX_TYPE")
	private String idxType;
	@Column(name="IDX_TYPE_NAME")
	private String idxTypeName;
	@Column(name="IDX_TYPE_ID")
	private Integer idxTypeId;
	@Column(name="NODE_NAME")
	private String nodeName;
	public Integer getIdxId() {
		return idxId;
	}
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setIdxId(Integer idxId) {
		this.idxId = idxId;
	}
	public String getIdxCode() {
		return idxCode;
	}
	public void setIdxCode(String idxCode) {
		this.idxCode = idxCode;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getIdxType() {
		return idxType;
	}
	public void setIdxType(String idxType) {
		this.idxType = idxType;
	}
	public String getIdxTypeName() {
		return idxTypeName;
	}
	public void setIdxTypeName(String idxTypeName) {
		this.idxTypeName = idxTypeName;
	}
	public Integer getIdxTypeId() {
		return idxTypeId;
	}
	public void setIdxTypeId(Integer idxTypeId) {
		this.idxTypeId = idxTypeId;
	}
	
	
}
