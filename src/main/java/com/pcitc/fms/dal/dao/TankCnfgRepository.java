package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TankCnfg;

public interface TankCnfgRepository extends JpaRepository<TankCnfg, Long>, JpaSpecificationExecutor<TankCnfg> {

	@Transactional
	@SQLDelete(sql = " delete TankCnfg where tankCnfgId = :tankCnfgId ")
	public int deleteByTankCnfgId(@Param("tankCnfgId") Long TankCnfgId);

	@Query(" from TankCnfg where tankCnfgId = :tankCnfgId ")
	public TankCnfg findOneByTankCnfgId(@Param("tankCnfgId") Long tankCnfgId);

	@Query(AreaNodeBasicSql.tankCnfgs + " and tc.tankCnfgId = :tankCnfgId")
	com.pcitc.fms.bll.entity.TankCnfg findTankCnfg(@Param("tankCnfgId") Long tankCnfgId);

	@Transactional
	public MyPageImpl findTankCnfgs(com.pcitc.fms.service.model.TankCnfg tankCnfg,
			Pageable pageable);
}
