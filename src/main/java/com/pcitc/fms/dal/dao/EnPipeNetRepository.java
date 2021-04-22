package com.pcitc.fms.dal.dao;

import com.pcitc.fms.dal.pojo.EnPipeNet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface EnPipeNetRepository extends JpaRepository<EnPipeNet, Long>, JpaSpecificationExecutor<EnPipeNet> {

    @Transactional
    public Page<EnPipeNet> findEnPipeNets(com.pcitc.fms.service.model.EnPipeNet EnPipeNetModel, Pageable pageable);

}
