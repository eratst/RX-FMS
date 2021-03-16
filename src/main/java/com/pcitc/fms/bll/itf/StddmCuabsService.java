package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StddmCuabs;

public interface StddmCuabsService {
	Pager<com.pcitc.fms.bll.entity.StddmCuabs> getPageStddmCuabs(StddmCuabs stddmCuabsModel, Pageable pageable) throws Exception;
}
