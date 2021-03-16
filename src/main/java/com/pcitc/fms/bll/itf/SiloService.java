package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Silo;
import com.pcitc.fms.service.model.Pager;

public interface SiloService {

	public Pager<Silo> getPageSilos(com.pcitc.fms.service.model.Silo modelStr, Pageable pageable)throws BusinessException;

}
