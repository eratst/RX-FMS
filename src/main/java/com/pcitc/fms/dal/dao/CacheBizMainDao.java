package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcitc.fms.dal.pojo.CacheBizMain;

public interface CacheBizMainDao extends JpaRepository<CacheBizMain, Integer>, JpaSpecificationExecutor<CacheBizMain>{

	static String cacheBizMain = "select new CacheBizMain(biz.bizCode,rent.rentCode,org.orgCode) from CacheBizMain biz,CacheRent rent, CacheBizDtl org where biz.rentId = rent.rentId and biz.bizId = org.bizId ";

	@Query(cacheBizMain+" and rent.rentCode = :rentCode and biz.bizCode = :bizCode")
	public List<CacheBizMain> findCacheBizMainByRentCodeAndBizCode(@Param("rentCode") String rentCode,@Param("bizCode") String bizCode);
	
}
