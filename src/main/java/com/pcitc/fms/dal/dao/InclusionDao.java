package com.pcitc.fms.dal.dao;


import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.Inclusion;


public interface InclusionDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Inclusion>, JpaRepository<com.pcitc.fms.dal.pojo.Inclusion,Integer>{

	@Modifying
	@Transactional
	@Query("delete from Inclusion t where t.sourceType = :parentType and t.sourceCode = :parentCode and  t.targetType = :targetType and t.targetCode = :targetCode")
	void deleteByParam(@Param("parentType")String parentType, @Param("parentCode")String parentCode ,@Param("targetType")String targetType, @Param("targetCode")String targetCode);
	
	/*@Transactional
	@SQLDelete(sql ="delete from Inclusion t where t.sourceType = :parentType and t.sourceCode = :parentCode and  t.targetType = :targetType and t.targetCode = :targetCode")
	void deleteByTargetTypeAndTargetCodeAndSourceTypeAndSourceCode
	(@Param("parentType")String parentType, @Param("parentCode")String parentCode ,@Param("targetType")String targetType, @Param("targetCode")String targetCode);
	*/
	@Query("from Inclusion t where t.sourceType = :parentType and t.sourceCode = :parentCode and  t.targetType = :targetType and t.targetCode = :targetCode")
	List<Inclusion> getInclusion(@Param("parentType")String parentType, @Param("parentCode")String parentCode ,@Param("targetType")String targetType, @Param("targetCode")String targetCode);
	@Modifying
	@Transactional
	@Query("delete from Inclusion t where  t.targetType = :targetType and t.targetCode = :targetCode")
	void deleteByTargetCodeAndTargetType(@Param("targetCode")String targetCode,@Param("targetType")String targetType);
	@Modifying
	@Transactional
	@Query("delete from Inclusion t where  t.sourceCode = :sourceCode")
	void deleteBySourceCode(@Param("sourceCode")String sourceCode);

	
	Inclusion findBySourceCode(String sourceCode);
	@Query("from Inclusion t where t.sourceCode = :sourceCode")
	List<Inclusion> getBySourceCode(@Param("sourceCode")String sourceCode);

	


}
