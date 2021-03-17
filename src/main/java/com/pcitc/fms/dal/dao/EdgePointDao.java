package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
@Service
public interface EdgePointDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.EdgePoint>,JpaRepository<com.pcitc.fms.dal.pojo.EdgePoint, Integer>{

//	@Query("from EdgePoint where parentCode = :parentCode and parentType = :parentType and code = :edgePointCode order by code")
	@Query(AreaNodeBasicSql.edgePoints+" and a.nodeCode = :nodeCode")
	List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointbyNodeCode(@Param("nodeCode") String nodeCode);
	
//	@Query("from EdgePoint e where and e.parentCode = :parentCode and e.parentType = :parentType and e.code in (:codeList)")
	@Query(AreaNodeBasicSql.edgePoints+" and a.nodeCode in (:codeList)")
	List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointsByCodeList(@Param("codeList")List<String> codeList);

//	@Query(AreaNodeBasicSql.edgePoints+" and e.parentCode = :parentCode and e.parentType = :parentType and e.edgePointId in (:idList) order by code")
	@Query(AreaNodeBasicSql.edgePoints+" and  a.nodeId in (:idList) order by a.nodeCode")
	List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointsByIdList(@Param("idList")List<Integer> idList);

	@Transactional
	public MyPageImpl getEdgePointByModel(com.pcitc.fms.service.model.EdgePoint edgePointModel, Pageable pageable);

//	@Modifying
//	@Transactional
//	@Query("update EdgePoint set code=:code, name=:name , shortName=:shortName,  inOut=:inOut, maintainTime=sysdate,editor=:editor,enabled=:enabled,des =:des where code = :code")
//	void updateEdgePoint(
//			@Param("code") String code, 
//			@Param("name") String name,
//			@Param("shortName") String shortName,
//			@Param("inOut") String inOut,
//			@Param("enabled") Integer enabled,
//			@Param("editor") String editor,
//			@Param("des") String des
//			                             );

	@Transactional
	@SQLDelete(sql = "delete from EdgePoint where nodeCode = :edgePointCode")
	void deleteByNodeCode(@Param("edgePointCode") String edgePointCode);


	@Query(AreaNodeBasicSql.edgePoints+" and  a.nodeCode = :parentCode  order by a.nodeCode")
    List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointes(@Param("parentCode") String parentCode );

//	@Query(AreaNodeBasicSql.edgePointsCon+",Connections c where c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'edgePoints' and c.targetCode = e.code and e.code= :edgePointCode and e.inOut = d.code")
	@Query(AreaNodeBasicSql.edgePoints+" and  a.nodeCode = :parentCode  order by a.nodeCode")
	List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointesByCodeLinks(@Param("parentCode") String parentCode);

//	@Query(AreaNodeBasicSql.edgePointsCon+",Connections c where c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'edgePoints' and e.inOut = d.code")
	@Query(AreaNodeBasicSql.edgePoints+" and  a.nodeCode = :parentCode  order by a.nodeCode")
	List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointesByLinks(@Param("parentCode") String parentCode);

//	@Query(AreaNodeBasicSql.edgePoints+" and e.parentType = :parentType")
//    List<com.pcitc.fms.dal.pojo.EdgePoint> getEdgePointes(@Param("parentType") String parentType);

}




