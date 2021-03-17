package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pcitc.fms.common.Test;
import com.pcitc.fms.dal.pojo.Plant;

/** 
* @author: jzx
* @version 创建时间：2017年6月8日 下午4:08:58 
* 类说明 
*/
@Service
public interface PlantDao extends JpaSpecificationExecutor<Plant>, JpaRepository<Plant, Integer>{
	@Transactional
	public Test findPagePlants(com.pcitc.fms.service.model.Plant model ,Pageable pageable);

	@Query("from PlantArea a where a.plantCode =:plantCode")
	Plant findByCode(@Param("plantCode")String plantCode);
}
