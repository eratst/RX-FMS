package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.FltperCuab;

public interface FltperCuabDao extends JpaSpecificationExecutor<FltperCuab>,JpaRepository<FltperCuab, Integer> {
	@Transactional
	public MyPageImpl findFltperCuabs(com.pcitc.fms.service.model.FltperCuab fltperCuab, Pageable pageable);
	
}
