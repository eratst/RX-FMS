package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.EntityMeta;

public interface EntityDao extends JpaRepository<com.pcitc.fms.dal.pojo.EntityMeta, Integer> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@Query("from EntityMeta order by code")
	List<com.pcitc.fms.dal.pojo.EntityMeta> getEntities(com.pcitc.fms.service.model.EntityMeta findEntityModel);

	/**
	 * 查询单个实体
	 * 
	 * @param code
	 * @return
	 */
	@Query("select table from EntityMeta  table where table.code = :code")
	List<EntityMeta> getEntityByEntityCode(@Param("code") String code);

	/**
	 * 更新工厂信息
	 * 
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
	@Query("update EntityMeta set " 
			+ "entityName = :entityName,"
			+ "entityType = :entityType,"
			+ "shortName = :shortName,"
			+ "entityTableName = :entityTableName," 
			+ "enabled = :enabled," 
			+ "editor = :editor , "
			+ "maintainTime=sysdate " 
			+ "where "
			+ "entityId = :entityId")
	void updateEntity( 
			@Param("entityId") Integer entityId,
			@Param("entityName") String entityName,
			@Param("entityType") String entityType,
			@Param("shortName") String shortName, 
			@Param("entityTableName") String entityTableName,
			@Param("enabled") Integer enabled, 
			@Param("editor") String editor);

	@Transactional
	@SQLDelete(sql = "delete from EntityMeta where code = :code")
	void deleteByCode(@Param("code") String code);
	@Transactional
	public Page<EntityMeta> findEntities(com.pcitc.fms.service.model.EntityMeta findEntityModel,Pageable pageable);

	EntityMeta findByCode(String code);
	@Modifying
	@Transactional
	@Query("update EntityMeta set " 
			+ "entityName = :entityName,"
			+ "entityType = :entityType,"
			+ "shortName = :shortName,"
			+ "entityTableName = :entityTableName," 
			+ "enabled = :enabled," 
			+ "editor = :editor , "
			+ "maintainTime=sysdate " 
			+ "where "
			+ "code = :code")
	void updateEntity(
			@Param("code") String code,
			@Param("entityName") String entityName,
			@Param("entityType") String entityType,
			@Param("shortName") String shortName,
			@Param("entityTableName") String entityTableName,
			@Param("enabled") Integer enabled, 
			@Param("editor") String editor);


}
