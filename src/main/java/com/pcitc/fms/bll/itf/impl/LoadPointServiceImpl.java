package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.LoadPointDao;
import com.pcitc.fms.bll.entity.LoadPoint;
import com.pcitc.fms.bll.itf.LoadPointService;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class LoadPointServiceImpl implements LoadPointService{
	
	@Autowired
	private LoadPointDao loadPointDao;

	@Override
	public Pager<LoadPoint> getLoadPoints(com.pcitc.fms.service.model.LoadPoint loadPoint, Pageable pageable)
			throws BusinessException {

		Pager<LoadPoint> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.LoadPoint> properties = null;
		List<LoadPoint> EntityList = new ArrayList<>();
		try {
			properties = loadPointDao.findLoadPoints(loadPoint, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), LoadPoint.class);
		} catch (Exception e) {
			throw new BusinessException("", "", e.getMessage());
		}
		pageData.setContent(EntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	
	}

}
