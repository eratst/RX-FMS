package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.PositionOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

public interface PositionOrgService {
	
	public Pager<PositionOrg> getPositionOrgs(com.pcitc.fms.service.model.PositionOrg positionOrg,
		      Pageable pageable) throws BusinessException;

}
