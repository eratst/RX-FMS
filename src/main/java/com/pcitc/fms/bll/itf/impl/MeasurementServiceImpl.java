package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.MeasurementService;
import com.pcitc.fms.dal.dao.MeasurementDao;
import com.pcitc.fms.dal.pojo.Measurement;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@Component
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementDao repo;

    @Override
    public Pager<Measurement> getMeasurements(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable)
            throws Exception {
        Pager<Measurement> pageData = new Pager<>();
        Page<Measurement> measurementPojoList = repo.findMeasurement(measurement, pageable);
        List<Measurement> result = ObjectConverter.listConverter(measurementPojoList.getContent(), Measurement.class);
        pageData.setContent(result);
        pageData.setFirst(measurementPojoList.isFirst());
        pageData.setLast(measurementPojoList.isLast());
        pageData.setNumber(measurementPojoList.getNumber());
        pageData.setNumberOfElements(measurementPojoList.getNumberOfElements());
        pageData.setSize(measurementPojoList.getSize());
        pageData.setSort(measurementPojoList.getSort());
        pageData.setTotalElements(measurementPojoList.getTotalElements());
        pageData.setTotalPages(measurementPojoList.getTotalPages());
        return pageData;
    }
}
