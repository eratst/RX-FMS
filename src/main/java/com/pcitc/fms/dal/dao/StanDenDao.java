package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface StanDenDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.StanDen>, JpaRepository<com.pcitc.fms.dal.pojo.StanDen,Integer>{

	
	@Transactional
	MyPageImpl findStanDens(com.pcitc.fms.service.model.StanDen stanDen, Pageable pageable);
}
