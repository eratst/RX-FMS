package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.StdSec;

public interface StdSecDao extends JpaRepository<StdSec, Integer>, JpaSpecificationExecutor<StdSec>{
	
	@Transactional
	public MyPageImpl getStdSec(com.pcitc.fms.service.model.StdSec stdSec,Pageable pageable);
}
