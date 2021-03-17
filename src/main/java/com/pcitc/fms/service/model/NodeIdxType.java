package com.pcitc.fms.service.model;


import java.io.Serializable;

import cc.aicode.e2e.annotation.ExcelEntity;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class NodeIdxType extends BaseResRep implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ResourceMember(InTemplate = false)
		private Integer idxId;
		private String idxCode;
		@ResourceMember(InQueries="condition",Name="nodeType")
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
