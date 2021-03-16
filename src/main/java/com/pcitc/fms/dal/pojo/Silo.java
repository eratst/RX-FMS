package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_PM_SILO")
public class Silo implements Serializable {
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
	@RegionMember
	@Column(name = "NODE_CODE")
	@SpecialResource(name="a.nodeCode")
	private String nodeCode;
	// 料仓业务类型
	@Column(name = "SILOTYPE_ID")
	@SpecialResource(name="a.siloTypeId")
	private Long siloTypeId;
	// 料仓公称容积
	@Column(name = "CUBAGE")
	@SpecialResource(name="a.cubage")
	private Integer cubage;
	// 料仓高度
	@Column(name = "SILO_HGT")
	@SpecialResource(name="a.siloHgt")
	private Integer siloHgt;
	// 料仓上限高度
	@Column(name = "MAX_SILO_HGT")
	@SpecialResource(name="a.maxSiloHgt")
	private Integer maxSiloHgt;
	// 料仓下限高度
	@Column(name = "MIN_SILO_HGT")
	@SpecialResource(name="a.minSiloHgt")
	private Integer minSiloHgt;
	// 安全料仓量上限
	@Column(name = "MAX_SILO_STOARGE")
	@SpecialResource(name="a.maxSiloStoarge")
	private Integer maxSiloStoarge;
	// 安全料仓量下限
	@Column(name = "MIN_SILO_STOARGE")
	@SpecialResource(name="a.minSiloStoarge")
	private Integer minSiloStoarge;
	
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
	// 料仓业务类型（用于显示）
	@Transient
	@SpecialResource(name="d.siloTypeName")
	private String siloTypeName;
	@Transient
	@SpecialResource(name="d.siloTypeCode")
	private String siloTypeCode;
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

	public Silo() {
		// TODO Auto-generated constructor stub
	}

	public String getSiloTypeCode() {
		return siloTypeCode;
	}

	public void setSiloTypeCode(String siloTypeCode) {
		this.siloTypeCode = siloTypeCode;
	}

	public Silo(Long nodeId, String nodeCode, Long siloTypeId,
			Integer cubage, Integer siloHgt, Integer maxSiloHgt,
			Integer minSiloHgt, Integer maxSiloStoarge, Integer minSiloStoarge,
			String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, String des,
			Long areaId, String nodeLongitude, String nodeLatitude,
			Long nodeTypeId, Integer inUse, String nodeAltitude,
			String nodeName, String nodeAlias, String nodeTypeName,
			String siloTypeName,String siloTypeCode,String areaCode,String areaName,String areaAlias,Integer sortNum) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.siloTypeId = siloTypeId;
		this.cubage = cubage;
		this.siloHgt = siloHgt;
		this.maxSiloHgt = maxSiloHgt;
		this.minSiloHgt = minSiloHgt;
		this.maxSiloStoarge = maxSiloStoarge;
		this.minSiloStoarge = minSiloStoarge;
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
		this.siloTypeName = siloTypeName;
		this.siloTypeCode = siloTypeCode;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaAlias = areaAlias;
		this.sortNum  =sortNum;
	}
	public Silo(Long nodeId, String nodeCode, Long siloTypeId,
			Integer cubage, Integer siloHgt, Integer maxSiloHgt,
			Integer minSiloHgt, Integer maxSiloStoarge, Integer minSiloStoarge,
			String crtUserCode, String crtUserName, Date crtDate,
			String mntUserCode, String mntUserName, Date mntDate, String des,
			Long areaId, String nodeLongitude, String nodeLatitude,
			Long nodeTypeId, Integer inUse, String nodeAltitude,
			String nodeName, String nodeAlias, String nodeTypeName,
			String siloTypeName) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.siloTypeId = siloTypeId;
		this.cubage = cubage;
		this.siloHgt = siloHgt;
		this.maxSiloHgt = maxSiloHgt;
		this.minSiloHgt = minSiloHgt;
		this.maxSiloStoarge = maxSiloStoarge;
		this.minSiloStoarge = minSiloStoarge;
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
		this.siloTypeName = siloTypeName;
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

	public String getSiloTypeName() {
		return siloTypeName;
	}

	public void setSiloTypeName(String siloTypeName) {
		this.siloTypeName = siloTypeName;
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

	public Integer getCubage() {
		return cubage;
	}

	public void setCubage(Integer cubage) {
		this.cubage = cubage;
	}

	public Integer getSiloHgt() {
		return siloHgt;
	}

	public void setSiloHgt(Integer siloHgt) {
		this.siloHgt = siloHgt;
	}

	public Integer getMaxSiloHgt() {
		return maxSiloHgt;
	}

	public void setMaxSiloHgt(Integer maxSiloHgt) {
		this.maxSiloHgt = maxSiloHgt;
	}

	public Integer getMinSiloHgt() {
		return minSiloHgt;
	}

	public void setMinSiloHgt(Integer minSiloHgt) {
		this.minSiloHgt = minSiloHgt;
	}

	public Integer getMaxSiloStoarge() {
		return maxSiloStoarge;
	}

	public void setMaxSiloStoarge(Integer maxSiloStoarge) {
		this.maxSiloStoarge = maxSiloStoarge;
	}

	public Integer getMinSiloStoarge() {
		return minSiloStoarge;
	}

	public void setMinSiloStoarge(Integer minSiloStoarge) {
		this.minSiloStoarge = minSiloStoarge;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
