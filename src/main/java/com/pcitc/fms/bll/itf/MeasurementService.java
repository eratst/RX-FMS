package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.Measurement;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;


public interface MeasurementService {
    Pager<Measurement> getMeasurements(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable) throws Exception;
}





















