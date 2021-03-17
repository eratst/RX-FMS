package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.SpclCuab;

public interface SpclCuabDao extends JpaSpecificationExecutor<SpclCuab>,JpaRepository<SpclCuab, Integer> {
	@Transactional
	public MyPageImpl findSpclCuab(com.pcitc.fms.service.model.SpclCuab spclCuab, Pageable pageable);
}

