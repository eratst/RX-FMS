package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.StddmCuabs;

public interface StddmCuabsDao extends JpaRepository<StddmCuabs, Integer>, JpaSpecificationExecutor<StddmCuabs>{
	@Transactional
	public MyPageImpl getStddmCuabs(com.pcitc.fms.service.model.StddmCuabs stddmCuabs,Pageable pageable);
}
