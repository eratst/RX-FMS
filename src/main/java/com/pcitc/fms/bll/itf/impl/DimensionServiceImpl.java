/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DimensionServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pcitc.imp.common.ettool.utils.ObjectConverter;

import com.pcitc.fms.bll.entity.Dimension;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.bll.itf.DimensionService;
import com.pcitc.fms.bll.itf.TankService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.DimensioDao;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.TankArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Dimension service.
 */
@Service
public class DimensionServiceImpl implements DimensionService{

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DimensionServiceImpl.class);
	/**
	 * The Dimensio dao.
	 */
	@Autowired
	private DimensioDao dimensioDao;

	/**
	 * Gets page dimensions.
	 *
	 * @param modelStr the model str
	 * @param areaCode the area code
	 * @param pageable the pageable
	 * @return the page dimensions
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<Dimension> getPageDimensions(com.pcitc.fms.service.model.Dimension modelStr,
			Pageable pageable) throws BusinessException {
		String tankAreaCode = modelStr.getDimensionCode();
//		getCheckTankArea(factoryCode,tankAreaCode);
		List<Dimension> tankEntityList = new ArrayList<>(); 
		Pager<Dimension> pageData = new Pager<>();
		MyPageImpl properties = null;
		try {
			properties = dimensioDao.findPageDimensions(modelStr,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			tankEntityList = ObjectConverter.listConverter(properties.getContent(),Dimension.class);
		} catch (Exception e) {
			log.error("fail",e);
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

	/**
	 * 用于罐量计算，不需要工厂id等父类id
	 *
	 * @param tankCode the tank code
	 * @return the dimension by dimension code
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Dimension> getDimensionByDimensionCode(String tankCode) throws BusinessException {
		List<Dimension> tanklist_entity = new ArrayList<>();
			List<com.pcitc.fms.dal.pojo.Dimension> tanklist = new ArrayList<>();
			if(null == tankCode || "".equals(tankCode)){
				tanklist = dimensioDao.findAll();
			}else if(null != tankCode){
				tanklist = dimensioDao.getDimensionByDimensionCode(tankCode);
			}
		try {
			tanklist_entity = ObjectConverter.listConverter(tanklist,Dimension.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return tanklist_entity;
	}

	/**
	 * Gets check tank.
	 *
	 * @param tankCode the tank code
	 * @return the check tank
	 */
	private List<com.pcitc.fms.dal.pojo.Dimension> getCheckTank(String tankCode) {
		com.pcitc.fms.service.model.Dimension tank = new com.pcitc.fms.service.model.Dimension();
//		tank.setParentType(parentType);
//		tank.setParentCode(tankAreaCode);
//		tank.setCode(tankCode);
		Page<com.pcitc.fms.dal.pojo.Dimension> pageTanks = dimensioDao.findPageDimensions(tank, null);
		return pageTanks.getContent();
	}


}
