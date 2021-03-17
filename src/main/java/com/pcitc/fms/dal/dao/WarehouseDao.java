package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.exception.BusinessException;
@Service
public interface WarehouseDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Warehouse>,JpaRepository<com.pcitc.fms.dal.pojo.Warehouse, Integer> {

	
	@Transactional(rollbackFor=BusinessException.class)
	public MyPageImpl findPageWarehouses(com.pcitc.fms.service.model.Warehouse modelStr,
			Pageable pageable);


}
