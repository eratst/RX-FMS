package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.CapacityUnit;

public interface CapacityUnitDao extends JpaRepository<CapacityUnit, Long>, JpaSpecificationExecutor<CapacityUnit>{

	@Transactional
	public Page<CapacityUnit> findCapacityUnits(com.pcitc.fms.service.model.CapacityUnit capacityUnit,
			Pageable pageable);
	
	@Query("from CapacityUnit where capacityUnitCode = :capacityUnitCode")
	public List<CapacityUnit> findOneByCapacityUnitCode(@Param("capacityUnitCode") String capacityUnitCode);

}
