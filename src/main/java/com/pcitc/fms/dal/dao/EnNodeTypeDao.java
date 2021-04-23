package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.EnNodeType;

public interface EnNodeTypeDao extends JpaRepository<EnNodeType, Long>, JpaSpecificationExecutor<EnNodeType> {

    @Transactional
    public Page<EnNodeType> findEnNodeTypes(com.pcitc.fms.service.model.EnNodeType EnNodeTypeModel, Pageable pageable);

}
