package com.pcitc.fms.dal.dao;


import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.FactorySiteArea;
@Service
public interface FactorySiteDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.FactorySite>, JpaRepository<com.pcitc.fms.dal.pojo.FactorySite,Integer>  {


//	@Query("from FactorySite t where t.factorySiteCode =:factorySiteCode")
//	public com.pcitc.fms.dal.pojo.FactorySite findByCode(@Param("factorySiteCode")String factorySiteCode);
//
//
//	@Transactional
//	@SQLDelete(sql = "delete from FactorySite where factorySiteCode = :factorySiteCode")
//	void deleteByFactorySiteCode(@Param("factorySiteCode")String factorySiteCode);
//
//
//	@Query(AreaNodeBasicSql.factorySite + "where ad.factoryId = :factoryId and a.factorySiteCode = :factorySiteCode and a.factorySiteId = ad.areaDictionaryId")
//	public com.pcitc.fms.dal.pojo.FactorySiteArea getfactorySiteBycode(@Param("factoryId")Integer factoryId, @Param("factorySiteCode")String factorySiteCode);
//
//	@Query(AreaNodeBasicSql.factorySite + "where a.factorySiteCode = :factorySiteCode and a.factorySiteId = ad.areaDictionaryId")
//	public FactorySiteArea getfactorySiteBycode(@Param("factorySiteCode")String factorySiteCode);

}

