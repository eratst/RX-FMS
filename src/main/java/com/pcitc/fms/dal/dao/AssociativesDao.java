package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.Associatives;
import com.pcitc.imp.common.exception.BusiException;

public interface AssociativesDao extends JpaSpecificationExecutor<Associatives>,JpaRepository<Associatives, Integer> {

	@Transactional
	public MyPageImpl findAssociatives(com.pcitc.fms.service.model.Associatives Associatives, Pageable pageable) throws BusiException;
}
