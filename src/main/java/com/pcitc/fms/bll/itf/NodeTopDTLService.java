package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.NodeTopDTL;
import com.pcitc.fms.service.model.Pager;

public interface NodeTopDTLService {

//	List<String> getNodeTopDTLs(NodeTopDTL nodeTopDTLModel, Pageable pageable) throws BusinessException;
	
	public Pager<com.pcitc.fms.bll.entity.NodeTopDTL> getAll(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTL,Pageable pageable) throws Exception;

}
