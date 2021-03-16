package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;

public interface PositionOrgDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.PositionOrg>, JpaRepository<com.pcitc.fms.dal.pojo.PositionOrg,Long>  {

	@Transactional
	public MyPageImpl findPagePositionOrgs(com.pcitc.fms.service.model.PositionOrg positionOrg, Pageable pageable);

}
