package com.pcitc.fms.dal.dao;

import com.pcitc.fms.dal.pojo.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface StationRepository extends JpaRepository<Station, Integer>, JpaSpecificationExecutor<Station> {

    @Transactional
    public Page<Station> findStations(com.pcitc.fms.service.model.Station station, Pageable pageable);

}
