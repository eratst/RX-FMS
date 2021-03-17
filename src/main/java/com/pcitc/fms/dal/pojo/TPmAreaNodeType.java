package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Title:  区域节点类型关系
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2018/1/3
 */
@Entity
@Table(name = "T_PM_AREA_NODETYPE")
public class TPmAreaNodeType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  private Integer id;//主键ID

  @Column(name = "AREA_CODE")
  private String areaCode;//区域编码

  @Column(name = "NODE_TYPE_CODE")
  private String nodeTypeCode;//节点类型编码

  @Transient
  private String nodeTypeName;

  public TPmAreaNodeType() {
  }
  public TPmAreaNodeType(Integer id, String areaCode, String nodeTypeCode,String nodeTypeName) {
    this.id = id;
    this.areaCode = areaCode;
    this.nodeTypeCode = nodeTypeCode;
    this.nodeTypeName = nodeTypeName;
  }
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public String getNodeTypeCode() {
    return nodeTypeCode;
  }

  public void setNodeTypeCode(String nodeTypeCode) {
    this.nodeTypeCode = nodeTypeCode;
  }

  public String getNodeTypeName() {
    return nodeTypeName;
  }

  public void setNodeTypeName(String nodeTypeName) {
    this.nodeTypeName = nodeTypeName;
  }
}
