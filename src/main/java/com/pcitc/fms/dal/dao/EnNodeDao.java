package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.EnNode;

public interface EnNodeDao extends JpaRepository<EnNode, Long>, JpaSpecificationExecutor<EnNode> {

	@Transactional
	public Page<EnNode> findEnNodes(com.pcitc.fms.service.model.EnNode enNodeModel, Pageable pageable);

}
