package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.LoadPointType;

public interface LoadPointTypeDao extends JpaRepository<LoadPointType, Long>, JpaSpecificationExecutor<LoadPointType>{

	@Transactional
	public Page<LoadPointType> findLoadPointTypes(com.pcitc.fms.service.model.LoadPointType loadPointType,
			Pageable pageable);

	@Query("from LoadPointType where loadPointTypeCode = :loadPointTypeCode")
	public List<LoadPointType> findOneByLoadPointTypeCode(@Param("loadPointTypeCode") String loadPointTypeCode);
	
	@Query("from LoadPointType where loadPointTypeId = :loadPointTypeId")
	public LoadPointType findOneByLoadPointTypeId(@Param("loadPointTypeId") Long loadPointTypeId);


	@Transactional
	@SQLDelete(sql="delete from LoadPointType where loadPointTypeCode = :loadPointTypeCode")
	public void deleteByLoadPointTypeCode(@Param("loadPointTypeCode") String loadPointTypeCode);
}
