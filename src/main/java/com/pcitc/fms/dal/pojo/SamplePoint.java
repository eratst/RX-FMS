package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_SAMPLEPOINT")
public class SamplePoint implements Serializable {
	private static final long serialVersionUID = 1L;
	// 节点id
	@Id
	@Column(name = "NODE_ID")
	@SpecialResource(name="a.nodeId")
	private Long nodeId;
	// 节点编码
	@RegionMember
	@Column(name = "NODE_CODE")
	@SpecialResource(name="a.nodeCode")
	private String nodeCode;

	@Transient
	@SpecialResource(name="b.nodeName")
	private String nodeName;

	@Transient
	@SpecialResource(name="b.nodeAlias")
	private String nodeAlias;

	// 区域ID
	@Transient
	@SpecialResource(name="e.areaDictionaryId")
	private Long areaId;

	// 区域编码（用于查询）
	@Transient
	@SpecialResource(name="e.areaCode")
	private String areaCode;

	@Transient
	@SpecialResource(name="e.name")
	private String areaName;

	@Transient
	@SpecialResource(name="e.shortName")
	private String areaAlias;

	// 采样点业务类型
	@Column(name = "SAMPLEPOINTTYPE_ID")
	@SpecialResource(name="a.samplepointTypeId")
	private Long samplepointTypeId;

	@Transient
	@SpecialResource(name="d.samplepointTypeCode")
	private String samplePointTypeCode;

	@Transient
	@SpecialResource(name="d.samplepointTypeName")
	private String samplePointTypeName;

	// 位置经度
	@Transient
	@SpecialResource(name="b.nodeLongitude")
	private String nodeLongitude;

	// 位置纬度
	@Transient
	@SpecialResource(name="b.nodeLatitude")
	private String nodeLatitude;

	// 位置海拔
	@Transient
	@SpecialResource(name="b.nodeAltitude")
	private String nodeAltitude;

	@Transient
	private String crtUserCode; // 创建人ID

	@Transient
	private String crtUserName;// 创建人名称

	@Transient
	private Date crtDate;// 创建时间

	@Transient
	private String mntUserCode;// 最后维护人ID

	@Transient
	private String mntUserName;// 最后维护人名称

	@Transient
	private Date mntDate;//维护时间

	// 状态
	@Transient
	@SpecialResource(name="b.dataStatus")
	private Integer inUse;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	// 描述
	@Transient
	private String des;

	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@Transient
	private Integer nodeTypeId;

	// 节点类型（用于显示）
	@Transient
	private String nodeTypeName;

	public SamplePoint() {
	}

	public SamplePoint(Long nodeId, String nodeCode, String nodeName, String nodeAlias, Long areaId,
			String areaCode, String areaName, String areaAlias, Long samplePointTypeId, String samplepointTypeCode,
			String samplepointTypeName, String nodeLongitude, String nodeLatitude, String nodeAltitude,
			String crtUserCode, String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate,
			Integer inUse, Integer sortNum, String des) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.areaId = areaId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaAlias = areaAlias;
		this.samplepointTypeId = samplePointTypeId;
		this.samplePointTypeCode = samplepointTypeCode;
		this.samplePointTypeName = samplepointTypeName;
		this.nodeLongitude = nodeLongitude;
		this.nodeLatitude = nodeLatitude;
		this.nodeAltitude = nodeAltitude;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.inUse = inUse;
		this.sortNum = sortNum;
		this.des = des;
	}


	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public String getSamplePointTypeName() {
		return samplePointTypeName;
	}

	public void setSamplePointTypeName(String samplepointTypeName) {
		this.samplePointTypeName = samplepointTypeName;
	}

	public String getNodeLongitude() {
		return nodeLongitude;
	}

	public void setNodeLongitude(String nodeLongitude) {
		this.nodeLongitude = nodeLongitude;
	}

	public String getNodeLatitude() {
		return nodeLatitude;
	}

	public void setNodeLatitude(String nodeLatitude) {
		this.nodeLatitude = nodeLatitude;
	}

	public Integer getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Integer nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	public String getNodeAltitude() {
		return nodeAltitude;
	}

	public void setNodeAltitude(String nodeAltitude) {
		this.nodeAltitude = nodeAltitude;
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

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}


	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	public Date getMntDate() {
		return mntDate;
	}

	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getSamplePointTypeCode() {
		return samplePointTypeCode;
	}

	public void setSamplePointTypeCode(String samplepointTypeCode) {
		this.samplePointTypeCode = samplepointTypeCode;
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

}
