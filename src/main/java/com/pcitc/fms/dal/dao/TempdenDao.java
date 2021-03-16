package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.Tempden;

public interface TempdenDao extends JpaSpecificationExecutor<Tempden>,JpaRepository<Tempden, Integer> {
	@Transactional
	public MyPageImpl findTempden(com.pcitc.fms.service.model.Tempden tempden, Pageable pageable);
}
