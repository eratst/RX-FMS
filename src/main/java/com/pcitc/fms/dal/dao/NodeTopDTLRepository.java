package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface NodeTopDTLRepository extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NodeTopDTL>, JpaRepository<com.pcitc.fms.dal.pojo.NodeTopDTL,Integer>{

	
	@Transactional
	public MyPageImpl getNodeTopDTLs(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTL, Pageable pageable);
}
