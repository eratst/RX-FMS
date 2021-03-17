package com.pcitc.fms.dal.dao;

import java.util.List;

import javax.persistence.Transient;

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
import com.pcitc.fms.dal.pojo.Dimension;
@Service
public interface DimensioDao extends JpaSpecificationExecutor<Dimension>,JpaRepository<Dimension, Integer>{
	@Transactional
	public MyPageImpl findPageDimensions(com.pcitc.fms.service.model.Dimension modelStr, Pageable pageable);
	
	@Query("from Dimension where dimensionCode = :dimensionCode")
	List<com.pcitc.fms.dal.pojo.Dimension> getDimensionByDimensionCode(@Param("dimensionCode")String tankCode);

}
