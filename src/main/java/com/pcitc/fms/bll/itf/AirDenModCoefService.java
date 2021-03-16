package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.AirDenModCoef;
import com.pcitc.fms.service.model.Pager;

public interface AirDenModCoefService {

	Pager<com.pcitc.fms.bll.entity.AirDenModCoef> getPageAirDenModCoef(AirDenModCoef model, Pageable pageable) throws Exception;

}
