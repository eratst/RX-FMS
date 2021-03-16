package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.dal.pojo.NodeTopDTL;

public interface FindPathService {
	public List<String> findPath(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTLModel,List<NodeTopDTL> nodeTopDTLList );

}
