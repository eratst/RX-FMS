package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.UserPosition;

public interface UserPositionDao extends JpaSpecificationExecutor<UserPosition>,JpaRepository<UserPosition, Long>  {
	
	@Transactional
	public MyPageImpl getUserPositionByModel(
                com.pcitc.fms.service.model.UserPosition userPosition, Pageable pageable);

}
