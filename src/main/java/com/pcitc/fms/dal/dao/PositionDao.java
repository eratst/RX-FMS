package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface PositionDao extends JpaRepository<com.pcitc.fms.dal.pojo.Position,Long> {

	@Transactional
	public MyPageImpl getPositionsByModel(
                com.pcitc.fms.service.model.Position position, Pageable pageable);
}
