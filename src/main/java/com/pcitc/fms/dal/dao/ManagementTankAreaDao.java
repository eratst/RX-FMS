package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.ManagementTankArea;

public interface ManagementTankAreaDao extends JpaRepository<ManagementTankArea, Integer>, JpaSpecificationExecutor<ManagementTankArea> {
	@Transactional
	public Page<ManagementTankArea> getManagementTankAreas(com.pcitc.fms.service.model.ManagementTankArea managementTankArea,Pageable pageable);
	
	@Query(value="select t.org_code from T_PM_BIZORG_DTL t start with t.org_id = :orgId CONNECT BY PRIOR t.org_id = t.praent_org_id",nativeQuery=true)
	public List<String> getOrgCodes(@Param("orgId") Integer orgId);
	
	@Query(value="select t1.org_id from T_PM_BIZORG_DTL t1 where t1.org_code in :orgCodes",nativeQuery=true)
	public List<BigDecimal> getOrgIds(@Param("orgCodes") List<String> orgCodes);
}
