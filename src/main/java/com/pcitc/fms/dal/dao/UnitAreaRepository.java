package com.pcitc.fms.dal.dao;


import com.pcitc.fms.dal.pojo.UnitArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yalin.zhao
 * @date 2021/4/21 9:29
 */
public interface UnitAreaRepository extends JpaRepository<UnitArea, Long>, JpaSpecificationExecutor<UnitArea> {

    @Transactional
    public Page<UnitArea> findUnitAreas(com.pcitc.fms.service.model.UnitArea UnitAreaModel, Pageable pageable);

}
