package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.SpclCuab;
import com.pcitc.fms.service.model.Pager;

public interface SpclCuabService {
	public Pager<SpclCuab> getSpclCuab(com.pcitc.fms.service.model.SpclCuab spclCuab,Pageable pageable) throws Exception;
}
