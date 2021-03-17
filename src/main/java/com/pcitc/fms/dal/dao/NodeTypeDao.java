package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.NodeType;
@Service
public interface NodeTypeDao extends JpaRepository<com.pcitc.fms.dal.pojo.NodeType,Integer> {

	public List<com.pcitc.fms.dal.pojo.NodeType> findByNodeTypeCode(String code);

	public NodeType findByNodeTypeId(Integer typeId);


	
}
