package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.IcMtrlFormCnfg;

public interface IcMtrlFormCnfgDao extends JpaRepository<IcMtrlFormCnfg, Long> {

	@Transactional
	public MyPageImpl findIcMtrlFormCnfgs(com.pcitc.fms.service.model.IcMtrlFormCnfg icMtrlFormCnfg, Pageable pageable);

	@Query(" from IcMtrlFormCnfg where mtrlFormCnfgId = :mtrlFormCnfgId ")
	public IcMtrlFormCnfg findById(@Param("mtrlFormCnfgId") Long mtrlFormCnfgId);
}
