package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.service.model.Pager;

public interface TankService {

	public Pager<Tank> getPageTanks(com.pcitc.fms.service.model.Tank modelStr,Pageable pageable)throws BusinessException;

}
