package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.NodeTopDTL;

public interface LoadNodeTopDTLService {
	public List<com.pcitc.fms.dal.pojo.NodeTopDTL> getLoadRangeForGraph(NodeTopDTL nodeTopDTLModel) throws BusinessException;

}
