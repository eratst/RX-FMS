package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.CapacityUnit;
import com.pcitc.fms.service.model.Pager;

public interface CapacityUnitService {

	public Pager<CapacityUnit> findAll(com.pcitc.fms.service.model.CapacityUnit capacityUnit, Pageable pageable) throws Exception;

	public  List<CapacityUnit> findOne(String capacityUnitCode) throws Exception;
}
