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

import com.pcitc.fms.dal.pojo.PropertyMeta;

public interface PropertyDao extends JpaRepository<com.pcitc.fms.dal.pojo.PropertyMeta, Integer> {
	@Transactional
	public Page<com.pcitc.fms.dal.pojo.PropertyMeta> findProperties(
			com.pcitc.fms.service.model.PropertyMeta propertyModel,Pageable pageable);

	@Query("select table from PropertyMeta table where table.propertyCode = :propertyCode")
	List<com.pcitc.fms.dal.pojo.PropertyMeta> getPropertyByPropertyCode(@Param("propertyCode") String propertyCode);
	
	@Query("select table from PropertyMeta table where table.entityCode = :entityCode")
	List<com.pcitc.fms.dal.pojo.PropertyMeta> getPropertyByEntityCode(@Param("entityCode") String entityCode);

//	@Modifying
//	@Transactional
//	@Query("update PropertyMeta set " 
//			+ "entityCode = :entityCode," 
//			+ "shortName = :shortName,"
//			+ "type = :type,"
//			+ "propertyCode = :propertyCode," 
//			+ "propertyName = :propertyName," 
//			+ "dataType = :dataType,"
//			+ "editor = :editor,"
//			+ "maintainTime=sysdate  " 
//			+ "where " 
//			+ "propertyCode = :propertyCode")
//	public void updateProperty(
//			@Param("entityCode") Integer entityCode,
//			@Param("shortName") String shortName,
//			@Param("type") String type,
//			@Param("propertyCode") String propertyCode, 
//			@Param("propertyName") String propertyName,
//			@Param("dataType") String dataType, 
//			@Param("propertyCode") Integer propertyCode,
//			@Param("editor") String editor);

	@Transactional
	@SQLDelete(sql = "delete from PropertyMeta where propertyCode = :propertyCode")
	void deleteByPropertyCode(@Param("propertyCode") String propertyCode);
	@Query("select table from PropertyMeta table where table.propertyCode = :propertyCode and table.entityCode = :entityCode")
	public List<PropertyMeta> getPropertyByEntityAndPropertyCode(@Param("entityCode") String entityCode,@Param("propertyCode") String propertyCode);

	@Modifying
	@Transactional
	@Query("update PropertyMeta set " 
			+ "propertyName = :propertyName,"
			+ "type = :type,"
			+ "shortName = :shortName,"
			+ "entityTableName = :entityTableName," 
			+ "editor = :editor," 
			+ "maintainTime=sysdate, " 
			+ "des = :des "
			+ "where "
			+ "propertyCode = :propertyCode")
	public void updatProperty(
			@Param("propertyName") String propertyName,
			@Param("type") String type,
			@Param("shortName") String shortName,
			@Param("entityTableName") String entityTableName,
			@Param("editor") String editor,
			@Param("des") String des,
			@Param("propertyCode") String propertyCode);
}
