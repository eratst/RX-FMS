package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Position;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

public interface PositionService {
	
	public Pager<Position> getPositions(com.pcitc.fms.service.model.Position position,
		      Pageable pageable) throws BusinessException;

}
