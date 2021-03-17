package com.pcitc.fms.service.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询")
public class NodeAndArea  extends BaseResRep  implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@ResourceMember(InTemplate =true)
	private Integer nodeAndAreaId ;  	//节点区域id
	
	@ResourceMember(InQueries="condition",Name="nodeId")
	private Integer nodeId;    		    //节点id
	
	private String nodeCode;	   	    //节点编码
	
	private String nodeName;    	    //节点名称
	
	@ResourceMember(InQueries="condition",Name="nodeType")
	private String nodeType;      	    //节点类型
	
	private Integer areaId;   		    //区域id
	
	private String areaCode;            //区域编码
	 
	private String areaName;            //区域名称
	
	private String areaType;            //区域类型
	
	@ResourceMember(InTemplate =false)
	private Integer factoryId;          //工厂ID
	
	private String factoryCode;          //工厂编码
	
	

	/**
	 * 
	 */
	public NodeAndArea() {
		super();
	}





	/**
	 * @param nodeId
	 * @param nodeCode
	 * @param nodeName
	 * @param nodeType
	 * @param areaId
	 * @param areaCode
	 * @param areaName
	 * @param areaType
	 * @param factoryId
	 */
	public NodeAndArea(Integer nodeId, String nodeCode, String nodeName, String nodeType, Integer areaId,
			String areaCode, String areaName, String areaType, String factoryCode) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.areaId = areaId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaType = areaType;
		this.factoryCode = factoryCode;
	}



	public String getFactoryCode() {
		return factoryCode;
	}


	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}


	/** 
	* Title: toString
	* Description: TODO task mark zhenqiang.zhao
	* @return
	* @see java.lang.Object#toString()
	* @date 2017年8月4日      
	* @author 赵振强  
	*/
	@Override
	public String toString() {
		return "NodeAndArea [nodeAndAreaId=" + nodeAndAreaId + ", nodeId=" + nodeId + ", nodeCode=" + nodeCode
				+ ", nodeName=" + nodeName + ", nodeType=" + nodeType + ", areaId=" + areaId + ", areaCode=" + areaCode
				+ ", areaName=" + areaName + ", areaType=" + areaType + "]";
	}



	/**
	 * @return the factoryId
	 */
	public Integer getFactoryId() {
		return factoryId;
	}


	/**
	 * @param factoryId the factoryId to set
	 */
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}


	/**
	 * @return the nodeAndAreaId
	 */
	public Integer getNodeAndAreaId() {
		return nodeAndAreaId;
	}



	/**
	 * @param nodeAndAreaId the nodeAndAreaId to set
	 */
	public void setNodeAndAreaId(Integer nodeAndAreaId) {
		this.nodeAndAreaId = nodeAndAreaId;
	}



	/**
	 * @return the nodeId
	 */
	public Integer getNodeId() {
		return nodeId;
	}



	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}



	/**
	 * @return the nodeCode
	 */
	public String getNodeCode() {
		return nodeCode;
	}



	/**
	 * @param nodeCode the nodeCode to set
	 */
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}



	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}



	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}



	/**
	 * @return the nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}



	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}



	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}



	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}



	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}



	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}



	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}



	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	/**
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}



	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
	
	
}
