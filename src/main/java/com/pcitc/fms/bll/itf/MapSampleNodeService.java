package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.MapSampleNode;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

public interface MapSampleNodeService {
	public Pager<MapSampleNode> getPageMapSampleNodes(com.pcitc.fms.service.model.MapSampleNode modelStr,
			Pageable pageable) throws BusinessException;
}
