package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.EnMeasurement;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

public interface EnMeasurementService {

    Pager<EnMeasurement> getEnMeasurements(com.pcitc.fms.service.model.EnMeasurement enMeasurement, Pageable pageable) throws Exception;

}
