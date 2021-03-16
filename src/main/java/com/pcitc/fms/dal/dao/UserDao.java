package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.User;


public interface UserDao  extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	
	@Transactional
	public MyPageImpl findUsers(com.pcitc.fms.service.model.User org, Pageable pageable);

	
	@Query(AreaNodeBasicSql.users+" and user.userCode =:userCode and user.orgId =:fmOrgId ")
	public User findUserByOrgIdAndUserCode(@Param ("fmOrgId") Long fmOrgId, @Param ("userCode") String userCode);
	
	
}
