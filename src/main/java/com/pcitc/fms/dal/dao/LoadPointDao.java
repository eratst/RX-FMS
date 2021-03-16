package com.pcitc.fms.dal.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;


public interface LoadPointDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.LoadPoint>, JpaRepository<com.pcitc.fms.dal.pojo.LoadPoint,Integer>{

	@Transactional
	Page<com.pcitc.fms.dal.pojo.LoadPoint> findLoadPoints(com.pcitc.fms.service.model.LoadPoint loadPoint, Pageable pageable);
	

}
