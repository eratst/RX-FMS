package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * [组态关系表] 拓扑关系主表
 *
 * @author 赵振强
 */
public class TIcNodetopMain implements Serializable {

  private static final long serialVersionUID = 1L;
  private Integer topId;

  private String topCode;

  private String topName;

  private String topAlias;

  private Integer dataStatus;

  private String crtUserId;

  private String crtUserName;

  private Date crtDate;

  private Integer mntUserId;

  private String mntUserName;

  private Date mntDate;

  private Integer version;

  private Integer sortNum;

  private String des;

  public Integer getTopId() {
    return topId;
  }

  public void setTopId(Integer topId) {
    this.topId = topId;
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

  public Integer getDataStatus() {
    return dataStatus;
  }

  public void setDataStatus(Integer dataStatus) {
    this.dataStatus = dataStatus;
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
}
