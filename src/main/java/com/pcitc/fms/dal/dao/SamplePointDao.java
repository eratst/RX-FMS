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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.SamplePoint;

/** 
* @author: jzx
* @version 创建时间：2017年6月8日 下午4:08:58 
* 类说明 
*/
@Service
public interface SamplePointDao extends JpaSpecificationExecutor<SamplePoint>, JpaRepository<SamplePoint, Integer>{
//	public List<SamplePoint> findSamplePoints(com.pcitc.fms.service.model.SamplePoint findSamplePointModel);
//	@Modifying
//	@Transactional
//	@Query("update SamplePoint set code = :code,name = :name,shortName = :shortName,businessType = :businessType,interval = :interval,limsCode = :limsCode,limsAccessCode = :limsAccessCode,enabled = :enabled,editor = :editor,maintainTime=sysdate where samplePointCode = :samplePointCode")
//	public void updateSamplePoint(
//			@Param("samplePointCode")String samplePointCode, 
//			@Param("code")String code, 
//			@Param("name")String name, 
//			@Param("shortName")String shortName,
//			@Param("businessType")String businessType, 
//			@Param("interval")Integer interval, 
//			@Param("limsCode")String limsCode, 
//			@Param("limsAccessCode")String limsAccessCode, 
//			@Param("enabled")Integer enabled,
//			@Param("editor")String editor
//			);
//	
//	
	@Transactional
	@SQLDelete(sql = "delete from SamplePoint  t where t.nodeCode = :code")
	void deleteByNodeCode(@Param("code")String code);
//	
//	@Query("select t from SamplePoint t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'samplePoints' and t.code=:samplePointCode")
//	public List<com.pcitc.fms.dal.pojo.SamplePoint> findLinkSamplePoints(@Param("parentCode") String parentCode, @Param("parentType")String parentType,@Param("samplePointCode")String samplePointCode);
//	
//	@Query("select t from SamplePoint t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'samplePoints'")
//	public List<com.pcitc.fms.dal.pojo.SamplePoint> findLinkSamplePoints(@Param("parentCode")String parentCode, @Param("parentType")String parentType);
//	
	@Transactional
	public MyPageImpl findPageSamplePoints(com.pcitc.fms.service.model.SamplePoint samplePointModel,Pageable pageable);
//	
	@Transactional
	@Query(AreaNodeBasicSql.samplePoint)
	public List<SamplePoint> getSamplePoints();
	
	@Transactional
	@Query(AreaNodeBasicSql.samplePoint+" and a.nodeId in :ids")
	public List<SamplePoint> getSamplePointsByIds(@Param("ids")List<Long> ids);
	
	@Query(AreaNodeBasicSql.samplePoint+" and  a.nodeCode = :code ")
	public List<com.pcitc.fms.dal.pojo.SamplePoint> getSamplePointByNodeCode(@Param("code")String samplePointCode);
//	
//	public List<com.pcitc.fms.dal.pojo.SamplePoint> findByParentTypeAndParentCode(String parentType, String plantCode);	
	@Query(AreaNodeBasicSql.samplePoint+" and  a.nodeCode = :code ")
    com.pcitc.fms.dal.pojo.SamplePoint findSamplePointByNodeCode(String code);
//  
//    @Query("from SamplePoint where parentType = :parentType and parentCode = :parentCode and code = :code")
//    public List<com.pcitc.fms.dal.pojo.SamplePoint> getSamplePointByCode(@Param("parentCode")String parentCode,@Param("code") String code,@Param("parentType") String parentType);	
}
