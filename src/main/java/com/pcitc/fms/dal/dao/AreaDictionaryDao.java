package com.pcitc.fms.dal.dao;

import java.util.Collection;
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
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Tank;
@Service
public interface AreaDictionaryDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.AreaDictionary>, JpaRepository<com.pcitc.fms.dal.pojo.AreaDictionary,Integer>  {
	@Query("from AreaDictionary where areaCode = :areaCode")
	AreaDictionary getAreaDictionaryByAreaCode(@Param("areaCode") String areaCode);
	@Query("from AreaDictionary where areaCode = :areaCode and areaTypeId = :areaTypeId")
	AreaDictionary getAreaDictionaryByAreaCode(@Param("areaCode") String areaCode,@Param("areaTypeId")Long areaTypeId);
	Page<AreaDictionary> getAreaDictionaryByModel(
			com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel, Pageable pageable);
	@Modifying
	@Transactional
	@SQLDelete(sql = "delete from AreaDictionary where areaCode = :areaCode")
	void deleteByAreaCode(@Param("areaCode") String areaCode);
	
	
	List<AreaDictionary> getAreaDictionaryIdsByModelList(
			List<com.pcitc.fms.bll.entity.AreaDictionary> areaDictionaryEntityList);
	
	@Query("from AreaDictionary where areaCode = :areaCode and areaTypeId = :areaTypeId")
	AreaDictionary getAreaDictionaryByAreaCodeAndAreaTypeId(@Param("areaCode") String areaCode, @Param("areaTypeId") Long areaTypeId);
	
	@Query(AreaNodeBasicSql.orgFindAllArea+" and b.orgCode in :codes")
	List<com.pcitc.fms.dal.pojo.AreaDictionary> findForRelOrg(@Param("codes")List<String> orgCodeList);
	
	@Transactional
	MyPageImpl getAreas(
			com.pcitc.fms.service.model.Area area,
			Pageable pageable);

	Page<com.pcitc.fms.dal.pojo.AreaDictionary> getAreaDictionaryNodesModels(
			com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel,
			Pageable pageable, String orgCode);
	
	@Query("from AreaDictionary where factoryId = :factoryId")
	List<com.pcitc.fms.dal.pojo.AreaDictionary> getAreaDictionaryByFactoryId(@Param("factoryId") Integer factoryId);

	
}
