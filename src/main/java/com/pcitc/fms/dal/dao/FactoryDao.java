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

import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.service.model.FactoryModelStr;

public interface FactoryDao extends JpaSpecificationExecutor<Factory>,JpaRepository<Factory, Integer> {

	/**
	 * 查询所有
	 * @return
	 */
	@Query("from Factory order by factoryId")
	public List<Factory> getFactories();
	
	/**
	 * 查询单个工厂
	 * @param factoryId
	 * @return
	 */
	@Query("from Factory where code = :code")
	public List<Factory> getFactoryByCode(@Param("code")String code);

	/**
	 * 更新工厂信息
	 * @param factoryId
	 * @param code
	 * @param name
	 * @param shortName
	 * @param businessType
	 * @param enabled
	 * @param editor
	 */
	@Modifying
	@Transactional
	@Query("update Factory set code = :code,name = :name,shortName = :shortName,businessType = :businessType,enabled = :enabled,editor = :editor where factoryId = :factoryId")
	public void updateFactory(@Param("factoryId")Integer factoryId,
			@Param("code")String code, 
			@Param("name")String name, 
			@Param("shortName")String shortName, 
			@Param("businessType")String businessType,
			@Param("enabled")Integer enabled, 
			@Param("editor")String editor);

	
	@Transactional
	@SQLDelete(sql = "delete from Factory where code = :code")
	public void deleteByCode(@Param("code")String code);

//	public List<Factory> findFactories(FactoryModelStr modelStr);

	public Page<Factory> findFactories(FactoryModelStr modelStr, Pageable pageable);

//	@Query("select d from Factory d, OrgRelation o where o.sourceId = :sourceId and o.sourceType =:sourceType and o.targetId = d.factoryId and o.targetType = 'Factory'")
	@Query("select d from Factory d, OrgRelation o where o.sourceCode = :sourceCode and o.sourceType =:sourceType and o.targetCode = d.code and o.targetType = :urlType")
	public List<Factory> getFactoryByOrgCode(@Param("sourceCode")String code,@Param("sourceType")String type,@Param("urlType")String urlType);

	public Factory findByCode(String factoryCode);

}
