package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
@Service
public interface SideMtrlTypeDao extends JpaRepository<com.pcitc.fms.dal.pojo.SideMtrlType,Integer> {

	public List<com.pcitc.fms.dal.pojo.SideMtrlType> findBySideMtrlTypeCode(String code);
	
	@Query("select s from SideMtrlType s ")
	public List<com.pcitc.fms.dal.pojo.SideMtrlType> getSideMtrl();

}
