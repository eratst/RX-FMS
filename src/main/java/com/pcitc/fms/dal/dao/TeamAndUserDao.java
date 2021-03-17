package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.TeamAndUser;


public interface TeamAndUserDao extends JpaRepository<TeamAndUser, Integer>, JpaSpecificationExecutor<TeamAndUser> {
	
	@Transactional
	public MyPageImpl getTeamAndUsers(com.pcitc.fms.service.model.TeamAndUser teamAndUser, Pageable pageable);
	

}
