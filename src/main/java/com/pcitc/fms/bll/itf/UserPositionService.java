package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.UserPosition;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

public interface UserPositionService {
	
	public Pager<UserPosition> getUserPositions(com.pcitc.fms.service.model.UserPosition userPosition,
		      Pageable pageable) throws BusinessException;

}
