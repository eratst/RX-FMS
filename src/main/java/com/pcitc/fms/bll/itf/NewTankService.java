package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NewTank;
import com.pcitc.fms.service.model.Pager;

public interface NewTankService {

	public Pager<NewTank> getPageTanks(com.pcitc.fms.service.model.NewTank modelStr,Pageable pageable)
			throws BusinessException;

}
