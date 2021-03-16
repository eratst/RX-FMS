package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Silo;
import com.pcitc.fms.dal.pojo.Tank;

public interface SiloDao extends JpaSpecificationExecutor<Silo>, JpaRepository<Silo, Integer>{

//	List<Silo> findSilos(com.pcitc.fms.service.model.Silo modelStr);
	/**
	 * 查询单个料仓
	 * @param factoryId
	 * @return
	 */
//	@Query("from Silo where siloId = :siloId")
//	List<Silo> getSiloByID(@Param("siloId")Integer siloId);
//	
//	@Modifying
//	@Transactional
//	@Query("update Silo set parentCode = :parentCode,name = :name,shortName = :shortName,businessType = :businessType,cubage = :cubage,unit=:unit,enabled = :enabled,editor = :editor,creator = :creator,maintainTime=sysdate where siloId = :siloId")
//	void updateSilo(@Param("siloId")Integer siloId, 
//			@Param("parentCode") String parentCode, 
//			@Param("name") String name, 
//			@Param("shortName") String shortName, 
//			@Param("businessType") String businessType, 
//			@Param("cubage") Double cubage,
//			@Param("unit") String unit, 
//			@Param("enabled") Integer enabled, 
//			@Param("editor") String editor,
//			@Param("creator") String creator);
//
	@Transactional
	@Modifying
	@SQLDelete(sql = "delete from Silo  where nodeCode = :code")
	void deleteByNodeCode(@Param("code") String code);
//	
//	@Query("select t from Silo t,Connections c where c.targetId = t.siloId and c.sourceId = :parentId and c.sourceType =:parentType and c.targetType = 'silos'")
//	List<Silo> findLinkSilos(@Param("parentId")Integer parentId,
//			@Param("parentType")String parentType);
//	
//	@Query("select t from Silo t,Connections c where c.targetId = t.siloId and c.sourceId = :parentId and c.sourceType =:parentType and c.targetType = 'silos' and t.siloId=:tankId")
//	List<Silo> findLinkSilos(@Param("parentId")Integer parentId,
//			@Param("parentType")String parentType,
//			@Param("tankId")Integer tankId);
//	
	@Transactional
	public MyPageImpl findPageSilos(com.pcitc.fms.service.model.Silo modelStr, Pageable pageable);
//
//	List<Silo> findByParentTypeAndParentCode(String string, String plantCode);
//	@Query(AreaNodeBasicSql.Silo+" and a.nodeCode = :siloCode")
//	com.pcitc.fms.dal.pojo.Silo findByNodeCode(@Param("siloCode")String siloCode);
}
