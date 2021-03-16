package com.pcitc.fms.bll.entity;

import java.io.Serializable;
public class NodeIdxType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idxId;
	private String idxCode;
	private String nodeType;
	private String idxType;
	private String idxTypeName;
	private Integer idxTypeId;
	private String nodeName;
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getIdxId() {
		return idxId;
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