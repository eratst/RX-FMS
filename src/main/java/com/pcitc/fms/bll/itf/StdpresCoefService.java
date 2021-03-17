package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.StdpresCoef;
import com.pcitc.fms.service.model.Pager;

public interface StdpresCoefService {
	public Pager<StdpresCoef> getStdpresCoe(com.pcitc.fms.service.model.StdpresCoef stdpresCoef,Pageable pageable) throws Exception;
}
