package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.StdcmmmCubas;

public interface StdcmmmCubasDao extends JpaRepository<StdcmmmCubas, Integer>, JpaSpecificationExecutor<StdcmmmCubas>{
	@Transactional
	public MyPageImpl getStdcmmmCubas(com.pcitc.fms.service.model.StdcmmmCubas stdcmmmCubas,Pageable pageable);
	
}
