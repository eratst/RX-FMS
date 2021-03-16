package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pcitc.fms.dal.pojo.NodeObjectTypeDict;

public interface NodeObjectTypeDictDao extends JpaRepository<NodeObjectTypeDict,Integer>{
	@Query("from NodeObjectTypeDict where code = ?1")
	List<com.pcitc.fms.dal.pojo.NodeObjectTypeDict> getNodeObjectTypeDict(Long value);
}
