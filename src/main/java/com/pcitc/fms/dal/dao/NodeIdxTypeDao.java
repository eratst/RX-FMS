package com.pcitc.fms.dal.dao;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
@Service
public interface NodeIdxTypeDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NodeIdxType>, JpaRepository<com.pcitc.fms.dal.pojo.NodeIdxType,Integer>{
	@Query("from NodeIdxType it where it.nodeType =:nodeType")
	List<com.pcitc.fms.dal.pojo.NodeIdxType> getByIdxType(@Param("nodeType") String nodeType);
}
