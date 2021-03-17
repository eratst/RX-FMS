package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface GlbPreCoefDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.GlbPreCoef>, JpaRepository<com.pcitc.fms.dal.pojo.GlbPreCoef,Integer>{
	
	@Transactional
	MyPageImpl findGlbPreCoefs(com.pcitc.fms.service.model.GlbPreCoef glbPreCoef, Pageable pageable);

}
