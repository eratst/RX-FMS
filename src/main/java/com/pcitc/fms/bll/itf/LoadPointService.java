package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.LoadPoint;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;


public interface LoadPointService {
	
	Pager<LoadPoint> getLoadPoints(com.pcitc.fms.service.model.LoadPoint loadPoint, Pageable pageable) throws BusinessException;

}
