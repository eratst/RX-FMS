package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Title:  区域节点类型关系
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2018/1/3
 */
public class TPmAreaNodeType implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;//主键ID

  private String areaCode;//区域编码

  private String nodeTypeCode;//节点类型


  private String nodeTypeName; //节点类型名称
  public String getNodeTypeName() {
    return nodeTypeName;
  }

  public void setNodeTypeName(String nodeTypeName) {
    this.nodeTypeName = nodeTypeName;
  }



  public TPmAreaNodeType() {
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
}
