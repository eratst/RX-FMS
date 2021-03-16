package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.MapSampleNode;
import com.pcitc.fms.bll.itf.MapSampleNodeService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.MapSampleNodeDao;
import com.pcitc.fms.dal.dao.SamplePointDao;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class MapSampleNodeServiceImpl implements MapSampleNodeService{
	@Autowired
	private MapSampleNodeDao mapSampleNodeDao;
	
	@Autowired
	private SamplePointDao samplePointDao;
	
	@SuppressWarnings("unchecked")
	public Pager<MapSampleNode> getPageMapSampleNodes(com.pcitc.fms.service.model.MapSampleNode modelStr,
			Pageable pageable) throws BusinessException {
		List<MapSampleNode> tankEntityList = new ArrayList<>(); 
		Pager<MapSampleNode> pageData = new Pager<>();
		MyPageImpl properties = null;
		try {
			properties = mapSampleNodeDao.findPageMapSampleNodes(modelStr, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			tankEntityList = ObjectConverter.listConverter(properties.getContent(),MapSampleNode.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(tankEntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}
}
