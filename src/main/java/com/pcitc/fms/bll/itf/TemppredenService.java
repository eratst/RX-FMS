package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Temppreden;
import com.pcitc.fms.service.model.Pager;

public interface TemppredenService {
	/**
	 * 查询所有
	 * 
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Pager<Temppreden> findTemppredens(String temp, Pageable pageable) throws Exception;
}
