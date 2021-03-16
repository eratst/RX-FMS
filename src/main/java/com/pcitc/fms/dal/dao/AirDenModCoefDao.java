package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.AirDenModCoef;
import com.pcitc.imp.common.exception.BusiException;

public interface AirDenModCoefDao extends JpaRepository<AirDenModCoef, Integer>, JpaSpecificationExecutor<AirDenModCoef>{

	public MyPageImpl getAllDenModCoefs(com.pcitc.fms.service.model.AirDenModCoef model, Pageable pageable) throws BusiException;

}
