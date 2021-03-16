package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.IcPipenettankCoef;

public interface IcPipenettankCoefDao extends JpaRepository<IcPipenettankCoef, Long> {
	
	@Transactional
	public MyPageImpl findIcPipenettankCoefs(com.pcitc.fms.service.model.IcPipenettankCoef icPipenettankCoef, Pageable pageable);

	@Query(" from IcPipenettankCoef where pipenettankCoefId = :pipenettankCoefId ")
	public IcPipenettankCoef findById(@Param("pipenettankCoefId") Long pipenettankCoefId);

}
