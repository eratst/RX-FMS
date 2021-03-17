package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.GlbPreCoef;
import com.pcitc.fms.service.model.Pager;


public interface GlbPreCoefService {
	
	Pager<com.pcitc.fms.bll.entity.GlbPreCoef> getGlbPreCoefs(GlbPreCoef glbPreCoef, Pageable pageable) throws Exception;

}
