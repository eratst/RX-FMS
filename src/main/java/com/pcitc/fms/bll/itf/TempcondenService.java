package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Tempconden;
import com.pcitc.fms.service.model.Pager;

public interface TempcondenService {
	public Pager<Tempconden> getTempconden(com.pcitc.fms.service.model.Tempconden tempconden,Pageable pageable) throws Exception;
}
