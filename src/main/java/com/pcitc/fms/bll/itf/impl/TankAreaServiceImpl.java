/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankAreaServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TankArea;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Tank area service.
 */
@Service
public class TankAreaServiceImpl implements TankAreaService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(TankAreaServiceImpl.class);
	/**
	 * The Tank area dao.
	 */
	@Autowired
	private  TankAreaDao tankAreaDao;

	/**
	 * Gets tank areas.
	 *
	 * @param tankAreamodel the tank areamodel
	 * @param pageable the pageable
	 * @return the tank areas
	 * @throws BusinessException the business exception
	 * @throws BusiException 
	 */
	@Override
	public Pager<TankArea> getTankAreas(com.pcitc.fms.service.model.TankArea tankAreamodel,Pageable pageable) throws BusinessException, BusiException {
		MyPageImpl properties  = null;
		List<TankArea> tankAreaEntityList = null;
		
		try {
			properties = tankAreaDao.findTankAreas(tankAreamodel ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		
		try {
			tankAreaEntityList = ObjectConverter.listConverter(properties.getContent(),TankArea.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.TankArea> pageData = new Pager<>();
		pageData.setContent(tankAreaEntityList);
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
