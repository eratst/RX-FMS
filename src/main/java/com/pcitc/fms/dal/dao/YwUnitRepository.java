package com.pcitc.fms.dal.dao;

import com.pcitc.fms.dal.pojo.YwUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface YwUnitRepository extends JpaRepository<YwUnit, Long>, JpaSpecificationExecutor<YwUnit> {

    @Transactional
    public Page<YwUnit> findYwUnits(com.pcitc.fms.service.model.YwUnit YwUnitModel, Pageable pageable);

}
