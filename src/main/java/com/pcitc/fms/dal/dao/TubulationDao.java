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


public interface TubulationDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Tubulation>,JpaRepository<com.pcitc.fms.dal.pojo.Tubulation, Integer>{

//	@Query(AreaNodeBasicSql.Tubulation+" and a.nodeCode = :tubulationCode order by a.nodeCode")
//	List<com.pcitc.fms.dal.pojo.Tubulation> getTubulationByNodeCode(@Param("tubulationCode") String tubulationCode);
//	
//	@Query("from Tubulation t  where parentType = :parentType and t.parentCode = :parentCode and t.code in (:codeList) order by code")
//	List<com.pcitc.fms.dal.pojo.Tubulation> getTubulationByCodeList(@Param("codeList") List<String> codeList, @Param("parentCode") String parentCode, @Param("parentType") String parentType);
//	
//	@Query("from Tubulation t where parentType = :parentType and t.parentCode = :parentCode and t.code in (:idList) order by code")
//	List<com.pcitc.fms.dal.pojo.Tubulation> getTubulationByIdList(@Param("idList") List<Integer> idList, @Param("parentCode") String parentCode, @Param("parentType") String parentType);
//
//
//	@Modifying
//	@Transactional
//	@Query("update Tubulation set name=:name , shortName=:shortName, code=:code, diameter=:diameter,materialName=:materialName,dynamicData=:dynamicData,maintaintime=sysdate, editor=:editor,enabled=:enabled,des =:des where tubulationId = :tubulationId")
//	void updateTubulation(
//			@Param("name") String name, 
//			@Param("shortName") String shortName,
//			@Param("code") String code,
//			@Param("diameter") String diameter,
//			@Param("materialName") String materialName,
//			@Param("dynamicData") String dynamicData,
//			@Param("editor") String editor,
//			@Param("enabled") Integer enabled,
//			@Param("tubulationId") Integer tubulationId,
//		    @Param("des") String des
//		);
//
	@Transactional
	@SQLDelete(sql ="delete from Tubulation where nodeCode = :code")
    void deleteByNodeCode(@Param("code") String code);
//
//	@Query("from Tubulation  t where t.parentType = :parentType and t.parentCode = :parentCode  order by code")
//    List<com.pcitc.fms.dal.pojo.Tubulation> getTubulations(@Param("parentCode") String parentCode, @Param("parentType") String parentType);
//
//	@Query("select t from Tubulation t, Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType = :parentType and c.targetType = 'tubulations'")
//    List<com.pcitc.fms.dal.pojo.Tubulation> getTubulationsByLinks(@Param("parentCode") String parentCode, @Param("parentType") String parentType);
//
//	@Query("from Tubulation  t where t.parentType = :parentType")
//    List<com.pcitc.fms.dal.pojo.Tubulation> getTubulations(@Param("parentType") String parentType);
	@Transactional
	MyPageImpl getTeesByModel(com.pcitc.fms.service.model.Tubulation tubulationModel,
            Pageable pageable);

}
