package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.CnfgTank;

public interface CnfgTankDao extends JpaRepository<CnfgTank, Integer>, JpaSpecificationExecutor<CnfgTank>{
	@Transactional
	public Page<CnfgTank> findCnfgTanks(com.pcitc.fms.service.model.CnfgTank cnfgTank, Pageable pageable);
	
	
	@Query("select new CnfgTank(t.nodeId,n.areaId,t.tankTypeId) from NodeDictionary n,Tank t where t.nodeId=n.nodeId and t.nodeCode= :nodeCode ")
	public CnfgTank findTank(@Param("nodeCode") String nodeCode);
	
}
