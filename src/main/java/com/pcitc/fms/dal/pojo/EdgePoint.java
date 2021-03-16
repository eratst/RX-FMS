package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_SHIPMENT")
public class EdgePoint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 节点id
	@Id
	@Column(name = "NODE_ID")
	@SpecialResource(name="a.nodeId")
	private Long nodeId;
	// 节点编码
	@Column(name = "NODE_CODE")
	@SpecialResource(name="a.nodeCode")
	private String nodeCode;
	// 运输类型ID
	@Column(name = "TRANSTYPE_ID")
	@SpecialResource(name="a.transTypeId")
	private Long transTypeId;
	// 进出类型ID
	@Column(name = "INOUTTYPE_ID")
	@SpecialResource(name="a.inOutTypeId")
	private Long inOutTypeId;
	// 计量公式
	@Column(name = "FORMULA")
	@SpecialResource(name="a.formula")
	private String formula;
	
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
	// 描述
	@Transient
	@SpecialResource(name="b.des")
	private String des;
	// 区域ID
	@Transient
	@SpecialResource(name="b.areaId")
	private Long areaId;
	// 位置经度
	@Transient
	@SpecialResource(name="b.nodeLongitude")
	private String nodeLongitude;
	// 位置纬度
	@Transient
	@SpecialResource(name="b.nodeLatitude")
	private String nodeLatitude;
	// 节点类型 含： 1侧线;2罐;3料仓;4库位;5进出厂点;6采样点;7排放口;8设备;9管段;10阀门;11盲板;12三通;
	@Transient
	@SpecialResource(name="b.nodeTypeId")
	private Long nodeTypeId;
	// 状态
	@Transient
	@SpecialResource(name="b.dataStatus")
	private Integer inUse;
	// 位置海拔
	@Transient
	@SpecialResource(name="b.nodeAltitude")
	private String nodeAltitude;
	// 节点名称
	@Transient
	@SpecialResource(name="b.nodeName")
	private String nodeName;
	// 节点简称
	@Transient
	@SpecialResource(name="b.nodeAlias")
	private String nodeAlias;
	// 节点类型（用于显示）
	@Transient
	@SpecialResource(name="c.nodeTypeName")
	private String nodeTypeName;
	// 罐类型（用于显示）
	@Transient
	@SpecialResource(name="d.transTypeName")
	private String transTypeName;
	// 区域编码（用于查询）
	@Transient
	@SpecialResource(name="e.areaCode")
	private String areaCode;
	
	@Column(name="SORT_NUM")
	@SpecialResource(name="a.sortNum")
	private Integer sortNum;
	
	@Transient
	@SpecialResource(name="org.orgCode")
	private String orgCode;
	
	@Transient
	@SpecialResource(name="e.name")
	private String areaName;
	
	@Transient
	@SpecialResource(name="e.shortName")
	private String areaAlias;

	@Transient
	@SpecialResource(name="d.transTypeCode")
	private String transTypeCode;
	
	public EdgePoint() {
	}
	

	public EdgePoint(Long nodeId, String nodeCode, Long transTypeId,
			Long inOutTypeId, String formula, String crtUserCode,
			String crtUserName, Date crtDate, String mntUserCode,
			String mntUserName, Date mntDate, String des, Long areaId,
			String nodeLongitude, String nodeLatitude, Long nodeTypeId,
			Integer inUse, String nodeAltitude, String nodeName,
			String nodeAlias, String nodeTypeName, String transTypeName,
			String areaCode,Integer sortNum,String areaName,String areaAlias,
			String orgCode,String transTypeCode) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.transTypeId = transTypeId;
		this.inOutTypeId = inOutTypeId;
		this.formula = formula;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.areaId = areaId;
		this.nodeLongitude = nodeLongitude;
		this.nodeLatitude = nodeLatitude;
		this.nodeTypeId = nodeTypeId;
		this.inUse = inUse;
		this.nodeAltitude = nodeAltitude;
		this.nodeName = nodeName;
		this.nodeAlias = nodeAlias;
		this.nodeTypeName = nodeTypeName;
		this.transTypeName = transTypeName;
		this.areaCode=areaCode;
		this.sortNum=sortNum;
		this.areaName=areaName;
		this.areaAlias=areaAlias;
		this.orgCode=orgCode;
		this.transTypeCode=transTypeCode;
	}

	
	public Integer getSortNum() {
		return sortNum;
	}


	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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


	public String getTransTypeName() {
		return transTypeName;
	}


	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
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

	public Integer getInUse() {
		return inUse;
	}


	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}


	public String getTransTypeCode() {
		return transTypeCode;
	}


	public void setTransTypeCode(String transTypeCode) {
		this.transTypeCode = transTypeCode;
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

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
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


	public Long getNodeId() {
		return nodeId;
	}


	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}


	public Long getTransTypeId() {
		return transTypeId;
	}


	public void setTransTypeId(Long transTypeId) {
		this.transTypeId = transTypeId;
	}


	public Long getInOutTypeId() {
		return inOutTypeId;
	}


	public void setInOutTypeId(Long inOutTypeId) {
		this.inOutTypeId = inOutTypeId;
	}


	public String getCrtUserCode() {
		return crtUserCode;
	}


	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}


	public String getMntUserCode() {
		return mntUserCode;
	}


	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}


	public Long getAreaId() {
		return areaId;
	}


	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}


	public Long getNodeTypeId() {
		return nodeTypeId;
	}


	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}
	
	

}
