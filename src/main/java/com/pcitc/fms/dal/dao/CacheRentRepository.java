package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcitc.fms.dal.pojo.CacheRent;

public interface CacheRentRepository extends JpaRepository<CacheRent, Integer>, JpaSpecificationExecutor<CacheRent>{

	static String cacheRent = "select new CacheRent(rent.rentCode) from CacheBizMain biz,CacheRent rent, CacheBizDtl org where biz.rentId = rent.rentId and biz.bizId = org.bizId ";
	static String cacheRentRelationArea = "select new CacheRent(rent.rentCode) from CacheBizMain biz,CacheRent rent, CacheBizDtl org,TPmOrg orgOwn, AreaDictionary area where biz.rentId = rent.rentId and biz.bizId = org.bizId and orgOwn.orgId = org.orgId and area.factoryId = orgOwn.orgId ";
	
	@Query(" from CacheRent where rentCode in :rentCodes")
	public List<CacheRent> findCacheRentByRentCode(@Param("rentCodes") List<String> rentCodes);
	
	@Query(cacheRent+" and biz.bizCode = :bizCode")
	public List<CacheRent> findCacheRentByBizCode(@Param("bizCode") String bizCode);
	
	@Query(cacheRent+" and org.orgCode = :orgCode")
	public List<CacheRent> findCacheRentByOrgCode(@Param("orgCode") String orgCode);
	
	@Query(cacheRentRelationArea+" and area.areaCode = :areaCode")
	public List<CacheRent> findCacheRentByAreaCode(@Param("areaCode") String areaCode);
	
}
