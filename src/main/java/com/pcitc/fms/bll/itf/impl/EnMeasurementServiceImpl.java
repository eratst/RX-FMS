package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.EnMeasurementService;
import com.pcitc.fms.dal.dao.EnMeasurementRepository;
import com.pcitc.fms.dal.pojo.EnMeasurement;
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
public class EnMeasurementServiceImpl implements EnMeasurementService {

    @Autowired
    private EnMeasurementRepository repo;

    @Override
    public Pager<EnMeasurement> getEnMeasurements(com.pcitc.fms.service.model.EnMeasurement enMeasurement, Pageable pageable)
            throws Exception {
        Pager<EnMeasurement> pageData = new Pager<>();
        Page<EnMeasurement> enMeasurementPojoList = repo.findEnMeasurement(enMeasurement, pageable);
        List<EnMeasurement> result = ObjectConverter.listConverter(enMeasurementPojoList.getContent(), EnMeasurement.class);
        pageData.setContent(result);
        pageData.setFirst(enMeasurementPojoList.isFirst());
        pageData.setLast(enMeasurementPojoList.isLast());
        pageData.setNumber(enMeasurementPojoList.getNumber());
        pageData.setNumberOfElements(enMeasurementPojoList.getNumberOfElements());
        pageData.setSize(enMeasurementPojoList.getSize());
        pageData.setSort(enMeasurementPojoList.getSort());
        pageData.setTotalElements(enMeasurementPojoList.getTotalElements());
        pageData.setTotalPages(enMeasurementPojoList.getTotalPages());
        return pageData;
    }
}
