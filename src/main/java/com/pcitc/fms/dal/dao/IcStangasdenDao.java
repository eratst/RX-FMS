package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.IcStangasden;

public interface IcStangasdenDao extends JpaRepository<IcStangasden, Long> {
	
	@Transactional
	public MyPageImpl findIcStangasdens(com.pcitc.fms.service.model.IcStangasden icStangasden, Pageable pageable);

}
