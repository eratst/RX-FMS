package com.pcitc.fms.dal.pojo;

import com.pcitc.fms.common.annotation.RegionMember;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/* @Description 拓扑关系明细
 * parameter a * @DATE 2017/12/27
 * @Author zhaozhenqiang
 * return @a
 */
@Entity
@Table(name = "T_IC_NODETOP_DTL")
public class TIcNodetopDTL implements  Serializable {

  private static final long serialVersionUID = 1L;
  @RegionMember
  @Id
  @Column(name = "DATA_ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NODETOP_DTL_GENERATOR")
  @SequenceGenerator(name = "NODETOP_DTL_GENERATOR", sequenceName = "SEQ_T_IC_NODETOP_DTL", allocationSize = 1)
  private Integer dataId;  //主键ID

  @Column(name = "DATA_CODE")
  private String dataCode;  //主键ID
  
  @Column(name = "S_NODE_ID")
  private Integer snodeId;//源节点ID

  @Column(name = "T_NODE_ID")
  private Integer tnodeId; //目标节点ID

  @Column(name = "TOP_ID")
  private Integer topId; //拓扑结构ID

  @Column(name = "CRT_USER_ID")
  private String crtUserId; //创建人ID

  @Column(name = "CRT_USER_NAME")
  private String crtUserName;//创建人名称

  @Column(name = "CRT_DATE")
  private Date crtDate;//创建时间

  @Column(name = "MNT_USER_ID")
  private Integer mntUserId;//最后维护人ID

  @Column(name = "mnt_user_name")
  private String mntUserName;//最后维护人名称

  @Column(name = "MNT_DATE")
  private Date mntDate;//最后维护人名称

  @Column(name = "version")
  private Integer version;

  @Column(name = "sort_num")
  private Integer sortNum;//排序
  // 标准 standard
  @Column(name = "DES")
  private String des;//描述

  @Transient
  private String snodeCode; //源节点CODE

  @Transient
  private String snodeAlias; //源节点简称

  @Transient
  private String tnodeCode; //目标节点CODE

  @Transient
  private String tnodeAlias; //目标节点简称

  @Transient
  private String topCode; //拓扑关系CODE

  @Transient
  private String topName; //拓扑关系名称

  @Transient
  private String topAlias; //拓扑关系简称


  public TIcNodetopDTL() {
    super();
  }

  /* @Description
   * parameter a   * @DATE 2012017/12/27 * @Author zhaozhaozhenqiang* return @a
  **/
  public TIcNodetopDTL(Integer dataId, Integer snodeId, Integer tnodeId, Integer topId,
      String crtUserId, String crtUserName, Date crtDate, Integer mntUserId,
      String mntUserName, Date mntDate, Integer version, Integer sortNum, String des,
      String snodeCode, String snodeAlias ,String tnodeCode, String tnodeAlias ,String topCode, String topName, String topAlias) {
    this.dataId = dataId;
    this.snodeId = snodeId;
    this.tnodeId = tnodeId;
    this.topId = topId;
    this.crtUserId = crtUserId;
    this.crtUserName = crtUserName;
    this.crtDate = crtDate;
    this.mntUserId = mntUserId;
    this.mntUserName = mntUserName;
    this.mntDate = mntDate;
    this.version = version;
    this.sortNum = sortNum;
    this.des = des;
    this.snodeCode = snodeCode;
    this.tnodeCode = tnodeCode;
    this.snodeAlias = snodeAlias;
    this.tnodeAlias = tnodeAlias;
    this.topCode = topCode;
    this.topName = topName;
    this.topAlias = topAlias;
  }

  public TIcNodetopDTL(Integer dataId,String dataCode, Integer snodeId, Integer tnodeId, Integer topId,
	      String crtUserId, String crtUserName, Date crtDate, Integer mntUserId,
	      String mntUserName, Date mntDate, Integer version, Integer sortNum, String des,
	      String snodeCode, String snodeAlias ,String tnodeCode, String tnodeAlias ,String topCode, String topName, String topAlias) {
	    this.dataId = dataId;
	    this.dataCode = dataCode;
	    this.snodeId = snodeId;
	    this.tnodeId = tnodeId;
	    this.topId = topId;
	    this.crtUserId = crtUserId;
	    this.crtUserName = crtUserName;
	    this.crtDate = crtDate;
	    this.mntUserId = mntUserId;
	    this.mntUserName = mntUserName;
	    this.mntDate = mntDate;
	    this.version = version;
	    this.sortNum = sortNum;
	    this.des = des;
	    this.snodeCode = snodeCode;
	    this.tnodeCode = tnodeCode;
	    this.snodeAlias = snodeAlias;
	    this.tnodeAlias = tnodeAlias;
	    this.topCode = topCode;
	    this.topName = topName;
	    this.topAlias = topAlias;
	  }
  /**
 * @return the dataCode
 */
public String getDataCode() {
	return dataCode;
}

/**
 * @param dataCode the dataCode to set
 */
public void setDataCode(String dataCode) {
	this.dataCode = dataCode;
}

public Integer getDataId() {
    return dataId;
  }

  public void setDataId(Integer dataId) {
    this.dataId = dataId;
  }

  public Integer getSnodeId() {
    return snodeId;
  }

  public void setSnodeId(Integer snodeId) {
    this.snodeId = snodeId;
  }

  public Integer getTnodeId() {
    return tnodeId;
  }

  public void setTnodeId(Integer tnodeId) {
    this.tnodeId = tnodeId;
  }

  public Integer getTopId() {
    return topId;
  }

  public void setTopId(Integer topId) {
    this.topId = topId;
  }

  public String getCrtUserId() {
    return crtUserId;
  }

  public void setCrtUserId(String crtUserId) {
    this.crtUserId = crtUserId;
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

  public Integer getMntUserId() {
    return mntUserId;
  }

  public void setMntUserId(Integer mntUserId) {
    this.mntUserId = mntUserId;
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

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
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

  public String getSnodeCode() {
    return snodeCode;
  }

  public void setSnodeCode(String snodeCode) {
    this.snodeCode = snodeCode;
  }

  public String getTnodeCode() {
    return tnodeCode;
  }

  public void setTnodeCode(String tnodeCode) {
    this.tnodeCode = tnodeCode;
  }

  public String getTopCode() {
    return topCode;
  }

  public void setTopCode(String topCode) {
    this.topCode = topCode;
  }

  public String getTopName() {
    return topName;
  }

  public void setTopName(String topName) {
    this.topName = topName;
  }

  public String getTopAlias() {
    return topAlias;
  }

  public void setTopAlias(String topAlias) {
    this.topAlias = topAlias;
  }

  public String getSnodeAlias() {
    return snodeAlias;
  }

  public void setSnodeAlias(String snodeAlias) {
    this.snodeAlias = snodeAlias;
  }

  public String getTnodeAlias() {
    return tnodeAlias;
  }

  public void setTnodeAlias(String tnodeAlias) {
    this.tnodeAlias = tnodeAlias;
  }
}
