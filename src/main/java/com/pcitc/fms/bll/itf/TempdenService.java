package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Tempden;
import com.pcitc.fms.service.model.Pager;

public interface TempdenService {
	public Pager<Tempden> getTempden(com.pcitc.fms.service.model.Tempden tempden,Pageable pageable) throws Exception;
}
