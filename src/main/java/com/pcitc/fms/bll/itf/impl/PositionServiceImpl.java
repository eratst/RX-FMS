package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Position;
import com.pcitc.fms.bll.itf.PositionService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.PositionDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	private PositionDao positionDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Position> getPositions(com.pcitc.fms.service.model.Position position, Pageable pageable)
			throws BusinessException {
		MyPageImpl properties  = null;
		List<Position> positionEntityList = null;
		try {
			properties = positionDao.getPositionsByModel(position, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			positionEntityList = ObjectConverter.listConverter(properties.getContent(),Position.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Position> pageData = new Pager<>();
		pageData.setContent(positionEntityList);
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
