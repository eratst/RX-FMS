package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Rent;

public interface RentDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Rent>, JpaRepository<com.pcitc.fms.dal.pojo.Rent,Integer>{
	
	@Transactional
	Page<com.pcitc.fms.dal.pojo.Rent> findRents(com.pcitc.fms.service.model.Rent rent, Pageable pageable);
	
	
	@Query(AreaNodeBasicSql.rents+"where r.rentCode = :rentCode")
	Rent getRentCode(@Param("rentCode") String rentCode);
	
	@Query("from Rent r where r.rentCode = :rentCode")
	Rent getRentByRentCode(@Param("rentCode") String rentCode);
	
	@Query(AreaNodeBasicSql.rents+"where r.rentName= :rentName")
	Rent getRentName(@Param("rentName") String rentName);
	
	@Transactional
	void deleteByRentCode(String rentCode);
	
	
}
