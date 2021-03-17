package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface OpenindexDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Openindex>, JpaRepository<com.pcitc.fms.dal.pojo.Openindex,Integer>  {
	
	@Transactional
	public MyPageImpl findPageOpenindexs(com.pcitc.fms.service.model.Openindex openindex, Pageable pageable);
}
