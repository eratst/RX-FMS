/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: UnitAlarmServiceImpl
 * Date:18-3-8 下午6:30
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.UnitAlarm;
import com.pcitc.fms.bll.itf.UnitAlarmService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.UnitAlarmDao;
import com.pcitc.fms.dal.dao.UnitAlarmDaoImpl;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Unit alarm service.
 *
 * @version 创建时间 ：12.6  类说明
 * @author: hanxiao
 */
@Service
@Component
public class UnitAlarmServiceImpl implements UnitAlarmService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(UnitAlarmServiceImpl.class);
	/**
	 * The Unit alarm dao.
	 */
	@Autowired
	private UnitAlarmDaoImpl unitAlarmDaoImpl;
	/**
	 * The Unit alarm dao.
	 */
	@Autowired
	private UnitAlarmDao unitAlarmDao;

	/**
	 * Gets page unit alarms.
	 *
	 * @param unitAlarm the unit alarm
	 * @return the page unit alarms
	 * @throws BusinessException the business exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pager<UnitAlarm> getPageUnitAlarms(com.pcitc.fms.service.model.UnitAlarm unitAlarm) throws BusinessException {
		//创建 Pageable
		Pageable pageable = getPageable(unitAlarm);
		//传入dao层
		MyPageImpl properties=null;
		try {
			properties = unitAlarmDaoImpl.findPageUnitAlarms(unitAlarm ,pageable);
		} catch (BusiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//对返回做处理
		List<UnitAlarm> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(properties.getContent(),UnitAlarm.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.UnitAlarm> pageData = new Pager<>();
		pageData.setContent(entityList);
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
	 * Gets pageable.
	 *
	 * @param unitAlarm the unit alarm
	 * @return the pageable
	 * @throws BusinessException the business exception
	 */
	private Pageable getPageable(com.pcitc.fms.service.model.UnitAlarm unitAlarm) throws BusinessException {
		Pageable  pageable  = null;
		Sort sort = new Sort(Sort.Direction.ASC, "unitAlarmId");
		//分页校验
		if (null != unitAlarm.getTop() && null != unitAlarm.getSkip()) {
			pageable = PageUtil.pageable(unitAlarm.getTop(), unitAlarm.getSkip(), sort);
		}
		return pageable;
	}

	/**
	 * Gets by code.
	 *
	 * @param code the code
	 * @return the by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public UnitAlarm getByCode(String code) throws BusinessException {
		com.pcitc.fms.dal.pojo.UnitAlarm pojo = unitAlarmDao.getByUnitAlarmCode(code);
		UnitAlarm entity = null;
		try {
			entity = ObjectConverter.entityConverter(pojo, UnitAlarm.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}

		return entity;
	}

}
