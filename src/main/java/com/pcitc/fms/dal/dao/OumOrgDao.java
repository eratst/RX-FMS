package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.OumOrg;

@Service
public interface OumOrgDao extends JpaRepository<OumOrg, Long>, JpaSpecificationExecutor<OumOrg>{
	
	
	
	@Query("from OumOrg where orgCode = :orgCode")
	public OumOrg findOumOrgByOrgCode(@Param("orgCode") String orgCode);
	
	@Query("from OumOrg where  orgId = :orgId")
	public OumOrg findOumOrgByOrgId(@Param("orgId") Long orgId);
	
	
	@Transactional
	@SQLDelete(sql = "delete from OumOrg where orgCode = :orgCode")
	public Integer deleteOumOrgByOrgCode(@Param("orgCode") String orgCode);

}
