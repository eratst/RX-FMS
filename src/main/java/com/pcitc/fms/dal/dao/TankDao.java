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
import com.pcitc.fms.dal.pojo.Tank;

public interface TankDao extends JpaSpecificationExecutor<Tank>,JpaRepository<Tank, Integer>  {

	
//	@Query("select t from Tank t,Connections c where c.targetCode = t.code and c.sourceId = :parentCode and c.sourceType =:parentType and c.targetType = 'tanks'")
//	@Query("from Tank a where  a.nodeCode = :code")
//	List<Tank> findLinkTanks(@Param("parentCode")String parentCode,
//			@Param("parentType")String parentType);
	
//	@Query("select t from Tank t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'tanks' and t.code=:code")
	@Query(AreaNodeBasicSql.Tank+"and  a.nodeCode = :code")
	List<Tank> findLinkTanks(@Param("code")String code);
	/**
	 * 查询单个罐
	 * @param factoryId
	 * @return
	 */
	@Query(AreaNodeBasicSql.Tank+"and  a.nodeCode = :code")
	List<Tank> getTankByNodeCode(@Param("code") String code);
	
//	@Query("from Tank where parentCode = :parentCode")
//	List<Tank> getTankByTankAreaCode(@Param("parentCode")String parentCode);
	
	/**   
	 * @date 2017年9月21日     
	 * @author 赵振强      
	 */
	List<Tank> findTankByNodeCode(String parentCode);
	
//	@Modifying
//	@Transactional
//	@Query("update Tank set code = :code,name = :name,shortName = :shortName,tankType = :tankType,height = :height,cubage = :cubage,unit=:unit,enabled = :enabled,editor = :editor,creator = :creator,maintainTime=sysdate where tankId = :tankId")
//	void updateTank(@Param("tankId") Integer tankId, 
//			@Param("code") String code, 
//			@Param("name") String name, 
//			@Param("shortName") String shortName, 
//			@Param("tankType") String tankType, 
//			@Param("height") String height,
//			@Param("cubage") String cubage, 
//			@Param("unit") String unit, 
//			@Param("enabled") Integer enabled, 
//			@Param("editor") String editor,
//			@Param("creator") String creator);
	
	@Transactional
	@SQLDelete(sql = "delete from Tank where nodeCode = :code")
	void deleteByNodeCode(@Param("code")String code);
	
	@Transactional
	public MyPageImpl findPageTanks(com.pcitc.fms.service.model.Tank modelStr, Pageable pageable);
	
	List<com.pcitc.fms.dal.pojo.Tank> findAll();


}
