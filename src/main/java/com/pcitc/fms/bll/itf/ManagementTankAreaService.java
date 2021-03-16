package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementTankArea;
import com.pcitc.fms.service.model.Pager;

@Service
public interface ManagementTankAreaService {
	public Pager<ManagementTankArea> getManagementTankAreas(com.pcitc.fms.service.model.ManagementTankArea managementTankArea, Pageable pageable) throws Exception;
}
