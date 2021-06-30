package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.Station;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

public interface StationService {

    public Pager<Station> findAllStation(com.pcitc.fms.service.model.Station station, Pageable pageable) throws Exception;

}
