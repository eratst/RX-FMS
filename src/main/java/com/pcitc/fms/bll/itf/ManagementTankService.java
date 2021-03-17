package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementTank;
import com.pcitc.fms.service.model.Pager;

@Service
public interface ManagementTankService {
	public Pager<ManagementTank> getManagementTanks(com.pcitc.fms.service.model.ManagementTank managementTank,Pageable pageable) throws Exception;
}
