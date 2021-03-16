package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.MapSampleNode;


public interface MapSampleNodeDao  extends JpaSpecificationExecutor<MapSampleNode>,JpaRepository<MapSampleNode, Integer>  {
	public MyPageImpl findPageMapSampleNodes(com.pcitc.fms.service.model.MapSampleNode modelStr, Pageable pageable);
}
