package com.pcitc.fms.dal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_SIMPLENODEMAP")
public class MapSampleNode {
	@Id
	@Column(name="SIMPLENODEMAP_ID")
	private Long SnodeMapId;
	
	@Column(name = "SNODE_ID")
	private Long SnodeId;
	
	@Column(name = "MNODE_ID")
	private Long MnodeId;
	
	@SpecialResource(name="a.areaCode")
	@Transient
	private String areaCode;
	
	@SpecialResource(name="a.name")
	@Transient
	private String areaName;
	
	@SpecialResource(name="a.shortName")
	@Transient
	private String areaAlias;
	
	@SpecialResource(name="n.nodeCode")
	@Transient
	private String nodeCode;
	
	@SpecialResource(name="n.nodeName")
	@Transient
	private String nodeName;
	
	@SpecialResource(name="n.nodeAlias")
	@Transient
	private String nodeAlias;
	
	@SpecialResource(name="n.nodeCode")
	@Transient
	private String SampleCode;
	
	@SpecialResource(name="n.nodeName")
	@Transient
	private String SampleName;
	
	@SpecialResource(name="n.nodeAlias")
	@Transient
	private String SampleAlias;
	
	@Transient
	private String samplePointTypeCode;
	
	@Transient
	private String samplePointTypeName;
	
	@Transient
	private Integer inUse;
	
	@Transient
	private Integer sortNum;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode; // 创建人ID

	@Column(name = "CRTUSER_NAME")
	private String crtUserName;// 创建人名称

	@Column(name = "CRTDATE")
	private Date crtDate;// 创建时间

	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;// 最后维护人ID

	@Column(name = "MNTUSER_NAME")
	private String mntUserName;// 最后维护人名称

	@Column(name = "MNTDATE")
	private Date mntDate;//维护时间
	

	public MapSampleNode(Long snodeMapId, Long snodeId, Long mnodeId, String areaCode, String areaName, String areaAlias,
			String nodeCode, String nodeName, String nodeAlias,String samplePointTypeCode,String samplePointTypeName,Integer inUse,Integer sortNum
			,String des,String SampleCode,String SampleName,String SampleAlias) {
		super();
		this.SnodeMapId=snodeMapId;
		this.SnodeId = snodeId;
		this.MnodeId = mnodeId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaAlias = areaAlias;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.samplePointTypeCode=samplePointTypeCode;
		this.samplePointTypeName=samplePointTypeName;
		this.inUse=inUse;
		this.sortNum=sortNum;
		this.des=des;
		this.SampleAlias=SampleAlias;
		this.SampleCode=SampleCode;
		this.SampleName=SampleName;
	}
	
	
	public Long getSnodeMapId() {
		return SnodeMapId;
	}



	public void setSnodeMapId(Long snodeMapId) {
		SnodeMapId = snodeMapId;
	}



	public Long getSnodeId() {
		return SnodeId;
	}



	public void setSnodeId(Long snodeId) {
		SnodeId = snodeId;
	}



	public String getSamplePointTypeCode() {
		return samplePointTypeCode;
	}

	public void setSamplePointTypeCode(String samplePointTypeCode) {
		this.samplePointTypeCode = samplePointTypeCode;
	}

	public String getSamplePointTypeName() {
		return samplePointTypeName;
	}

	public void setSamplePointTypeName(String samplePointTypeName) {
		this.samplePointTypeName = samplePointTypeName;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
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



	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaAlias() {
		return areaAlias;
	}

	public void setAreaAlias(String areaAlias) {
		this.areaAlias = areaAlias;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeAlias() {
		return nodeAlias;
	}

	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
	}

	public String getSampleCode() {
		return SampleCode;
	}

	public void setSampleCode(String sampleCode) {
		SampleCode = sampleCode;
	}

	public String getSampleName() {
		return SampleName;
	}

	public void setSampleName(String sampleName) {
		SampleName = sampleName;
	}

	public String getSampleAlias() {
		return SampleAlias;
	}

	public void setSampleAlias(String sampleAlias) {
		SampleAlias = sampleAlias;
	}
	
	
}
