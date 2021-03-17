package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.MtrlVcf;
import com.pcitc.imp.common.exception.BusiException;

public interface MtrlVcfDao extends JpaSpecificationExecutor<MtrlVcf>, JpaRepository<MtrlVcf,Integer>{

	public MyPageImpl findAllDeltCnfgs(com.pcitc.fms.service.model.MtrlVcf model, Pageable pageable) throws BusiException;

}
