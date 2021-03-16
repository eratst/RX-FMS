package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.LiquidProdCoef;

public interface LiquidProdCoefDao extends JpaRepository<LiquidProdCoef, Integer>{

	@Transactional              
	public MyPageImpl findLiquidProdCoefs(com.pcitc.fms.service.model.LiquidProdCoef liquidProdCoef,
			Pageable pageable);
}
