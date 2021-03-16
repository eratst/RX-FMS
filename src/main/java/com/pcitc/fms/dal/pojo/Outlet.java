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
@Table(name = "T_PM_DRAINAGEPORT")
public class Outlet implements Serializable {
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
	// 标识牌类别
	@Column(name = "SIGNBOARDTYPE_ID")
	@SpecialResource(name="a.signboardTypeId")
	private Long signboardTypeId;
	
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
	// 标识牌类型（用于显示）
	@Transient
	@SpecialResource(name="d.signboardTypeName")
	private String signboardTypeName;
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
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	@Transient
	@SpecialResource(name="d.signboardTypeCode")
	private String signboardTypeCode;
	
	public Outlet() {
		
	}

	public Outlet(Long nodeId, String nodeCode, Long signboardTypeId,
			String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, String des,
			Long areaId, String nodeLongitude, String nodeLatitude,
			Long nodeTypeId, Integer inUse, String nodeAltitude,
			String nodeName, String nodeAlias, String nodeTypeName,
			String signboardTypeName,String signboardTypeCode,String areaCode,String areaName,String areaAlias,Integer sortNum) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.signboardTypeId = signboardTypeId;
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
		this.signboardTypeName = signboardTypeName;
		this.signboardTypeCode = signboardTypeCode;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaAlias = areaAlias;
		this.sortNum = sortNum;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

	public String getSignboardTypeName() {
		return signboardTypeName;
	}

	public void setSignboardTypeName(String signboardTypeName) {
		this.signboardTypeName = signboardTypeName;
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

	public String getSignboardTypeCode() {
		return signboardTypeCode;
	}

	public void setSignboardTypeCode(String signboardTypeCode) {
		this.signboardTypeCode = signboardTypeCode;
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

}
