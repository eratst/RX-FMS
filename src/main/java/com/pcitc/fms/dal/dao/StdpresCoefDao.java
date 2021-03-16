package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.StdpresCoef;

public interface StdpresCoefDao extends JpaSpecificationExecutor<StdpresCoef>,JpaRepository<StdpresCoef, Integer> {
	@Transactional
	public MyPageImpl findStdpresCoef(com.pcitc.fms.service.model.StdpresCoef stdpresCoef, Pageable pageable);
}
