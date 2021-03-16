package com.pcitc.fms.dal.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LiqprodCubaTempCoefDaoImpl {
	@PersistenceContext
    private EntityManager em;
	
	public Page<com.pcitc.fms.dal.pojo.LiqprodCubaTempCoef> getLiqprodCubaTempCoefs(com.pcitc.fms.service.model.LiqprodCubaTempCoef liqprodCubaTempCoef,Pageable pageable){
		
		
		return null;
	}
}
