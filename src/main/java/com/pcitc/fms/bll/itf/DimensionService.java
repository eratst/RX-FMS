package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Dimension;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.service.model.Pager;

public interface DimensionService {

	public Pager<Dimension> getPageDimensions(com.pcitc.fms.service.model.Dimension modelStr, 
			Pageable pageable)throws BusinessException;

	public List<Dimension> getDimensionByDimensionCode(String dimensionCode)throws BusinessException;

}
