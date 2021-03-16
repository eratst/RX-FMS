package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.OumOrgUnit;


public interface OumOrgUnitDao extends JpaRepository<OumOrgUnit, Long>, JpaSpecificationExecutor<OumOrgUnit>{
	
	
	@Query("from OumOrgUnit where orgUnitCode = :orgUnitCode")
	public OumOrgUnit findOumOrgUnitByCode(@Param("orgUnitCode") String orgUnitCode);
	
	
	@Transactional
	@SQLDelete(sql = "delete from OumOrgUnit where orgUnitCode = :orgUnitCode")
	public void deleteOumOrgUnitByOrgUnitCode(@Param("orgUnitCode") String orgUnitCode);


	
}
