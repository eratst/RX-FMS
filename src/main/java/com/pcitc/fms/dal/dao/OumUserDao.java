package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.OumOrgUnit;
import com.pcitc.fms.dal.pojo.OumUser;

public interface OumUserDao extends JpaRepository<OumUser, Long>, JpaSpecificationExecutor<OumUser> {
	
	@Query("from OumUser where userCode = :userCode")
	public OumUser findOumUserByCode(@Param("userCode") String userCode);
	
	
	@Transactional
	@SQLDelete(sql = "delete from OumUser where userCode = :userCode")
	public void deleteOumUserByUserCode(@Param("userCode") String userCode);
	
}
