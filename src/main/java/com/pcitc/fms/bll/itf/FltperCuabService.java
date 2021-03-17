package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.FltperCuab;
import com.pcitc.fms.service.model.Pager;

public interface FltperCuabService {
	
	public Pager<FltperCuab> getFltperCuab(com.pcitc.fms.service.model.FltperCuab fltperCuab,Pageable pageable) throws Exception;
}
