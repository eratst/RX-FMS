package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.CnfgTank;
import com.pcitc.fms.service.model.Pager;

/**
 * 
 * @author xin.kou
 *
 */
public interface CnfgTankService {
	public Pager<CnfgTank> findCnfgTanks(com.pcitc.fms.service.model.CnfgTank cnfgTank, Pageable pageable)
			throws Exception;
}
