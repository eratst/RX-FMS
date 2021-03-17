package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

public class TIcNodetopDTL implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer dataId;  //主键ID

  private String dataCode; //主键CODE
  
  private Integer snodeId;//源节点ID

  private Integer tnodeId; //目标节点ID

  private Integer topId; //拓扑结构ID

  private String crtUserId; //创建人ID

  private String crtUserName;//创建人名称

  private Date crtDate;//创建时间

  private Integer mntUserId;//最后维护人ID

  private String mntUserName;//最后维护人名称

  private Date mntDate;//最后维护人名称

  private Integer version;

  private Integer sortNum;//排序

  private String des;//描述

  private String snodeCode; //源节点CODE

  private String snodeAlias;//源节点简称

  private String tnodeCode; //目标节点CODE

  private String tnodeAlias; //目标节点简称

  private String topCode; //拓扑关系CODE

  private String topName; //拓扑关系名称

  private String topAlias; //拓扑关系简称

  private String opertype;



  public TIcNodetopDTL() {
    super();
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


public String getOpertype() {
    return opertype;
  }

  public void setOpertype(String opertype) {
    this.opertype = opertype;
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
