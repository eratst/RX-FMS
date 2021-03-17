package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StdSec;

public interface StdSecService {

	Pager<com.pcitc.fms.bll.entity.StdSec> getPageStdSec(StdSec stdSecModel, Pageable pageable) throws Exception;

}
