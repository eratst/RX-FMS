package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.LoadPointType;
import com.pcitc.fms.service.model.Pager;

public interface LoadPointTypeService {
	
	public Pager<LoadPointType> findAll(com.pcitc.fms.service.model.LoadPointType loadPointType, Pageable pageable) throws Exception;

	public  List<LoadPointType> findOne(String loadPointTypeCode) throws Exception;


}
