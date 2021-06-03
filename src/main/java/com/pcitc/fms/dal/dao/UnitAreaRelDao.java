package com.pcitc.fms.dal.dao;

import com.pcitc.fms.dal.pojo.UnitAreaRel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface UnitAreaRelDao extends JpaRepository<UnitAreaRel, Long>, JpaSpecificationExecutor<UnitAreaRel> {

    @Transactional
    public Page<UnitAreaRel> findUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel unitAreaRelModel, Pageable pageable);

}
