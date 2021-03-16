package com.pcitc.fms.service.model;

import java.io.Serializable;

import com.pcitc.fms.common.annotation.BuildLink;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "条件查询（名称、简称支持模糊查询）")
public class NodeTopDTLPath  extends BaseResRep implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String snodeName;
	
	@BuildLink
	private String tnodeName;
	
	private String path;

	public String getSnodeName() {
		return snodeName;
	}

	public void setSnodeName(String snodeName) {
		this.snodeName = snodeName;
	}

	public String getTnodeName() {
		return tnodeName;
	}

	public void setTnodeName(String tnodeName) {
		this.tnodeName = tnodeName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
