package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pcitc.fms.dal.pojo.FacilityTypeDict;

@Repository
public interface FactoryDictDao extends JpaRepository<FacilityTypeDict,Integer>{
	@Query("from FacilityTypeDict where code = ?1")
	List<com.pcitc.fms.dal.pojo.FacilityTypeDict> getFacilityTypeDict(Long value);

}
