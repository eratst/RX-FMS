package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StanDen;



public interface StanDenService {
	
	Pager<com.pcitc.fms.bll.entity.StanDen> getStanDens(StanDen stanDen, Pageable pageable) throws Exception;

}
