package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.Tempconden;

public interface TempcondenDao extends JpaSpecificationExecutor<Tempconden>,JpaRepository<Tempconden, Integer> {
	@Transactional
	public MyPageImpl findTempconden(com.pcitc.fms.service.model.Tempconden tempconden, Pageable pageable);
}
