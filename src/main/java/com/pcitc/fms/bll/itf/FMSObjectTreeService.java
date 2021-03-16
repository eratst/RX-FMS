package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.bll.entity.FMSObjectTree;

public interface FMSObjectTreeService {
	
	public List<FMSObjectTree> getFMSObjectTreeToNode(String rentCode,String bizCode,String orgCode,String type) throws Exception;

}
