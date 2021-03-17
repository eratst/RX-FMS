package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.LiqprodCubaTempCoef;

/**
 * 
 * @author xin.kou
 *
 */
public interface LiqprodCubaTempCoefDao extends JpaRepository<LiqprodCubaTempCoef, Integer>{

	@Transactional
	public Page<com.pcitc.fms.dal.pojo.LiqprodCubaTempCoef> getLiqprodCubaTempCoefs(com.pcitc.fms.service.model.LiqprodCubaTempCoef liqprodCubaTempCoef,Pageable pageable);
}
