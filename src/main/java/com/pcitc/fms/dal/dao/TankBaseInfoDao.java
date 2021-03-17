package com.pcitc.fms.dal.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.TankBaseInfo;


public interface TankBaseInfoDao extends JpaRepository<TankBaseInfo, Integer>, JpaSpecificationExecutor<TankBaseInfo> {

	@Transactional
	public Page<TankBaseInfo> findTankBaseInfos(com.pcitc.fms.service.model.TankBaseInfo tankmo, Pageable pageable);
}
