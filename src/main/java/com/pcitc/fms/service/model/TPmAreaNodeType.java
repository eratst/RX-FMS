package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * Title:  区域节点类型关系
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2018/1/3
 */
@ResourceContract(ReadOnly = false)
@QueryContract(rel = "search" ,href = "" ,prompt = "")
@QueryContract(rel = "condition" ,href = "" ,prompt = "")
public class TPmAreaNodeType extends BaseResRep implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;//主键ID

  @ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "areaCode")
  private String areaCode;//区域编码

  @ResourceMember(InQueries = "condition", OnlyQuery = false, Name = "nodeTypeCode")
  private String nodeTypeCode;//节点类型编码

  private String nodeTypeName; //节点类型名称

  @ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
  private List<String> codeList;

  @ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
  private Integer top;

  @ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
  private Integer skip =  0;

  public TPmAreaNodeType() {
  }

  public TPmAreaNodeType(String areaCode, String nodeTypeCode,
      List<String> codeList, Integer top, Integer skip) {
    this.areaCode = areaCode;
    this.nodeTypeCode = nodeTypeCode;
    this.codeList = codeList;
    this.top = top;
    this.skip = skip;
  }

  public String getNodeTypeName() {
    return nodeTypeName;
  }

  public void setNodeTypeName(String nodeTypeName) {
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

  public List<String> getCodeList() {
    return codeList;
  }

  public void setCodeList(List<String> codeList) {
    this.codeList = codeList;
  }

  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
  }

  public Integer getSkip() {
    return skip;
  }

  public void setSkip(Integer skip) {
    this.skip = skip;
  }
}
