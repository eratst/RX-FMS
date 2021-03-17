package com.pcitc.fms.dal.dao;

import org.apache.poi.ss.formula.functions.T;



public interface CheckUrlDao {

	public T getEntityAndPartent(String Type,String id);
	
//	@Query("from App where enabled = 1 and appId = :appId")
//	App findAppByAppId(@Param("appId") Integer appId);
//
//	@Transactional
//	@SQLDelete(sql = "delete from App where enabled = 1 and appId = :appId")
//	Integer deleteAppByAppId(@Param("appId") Integer appId);
//	@Transactional
//	Page<App> findPageApps(String appName, String code, Integer enabled,Pageable pageable,Integer skip);
}
