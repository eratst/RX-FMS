/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MeasurementServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessExceptionMessage;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Measurement;
import com.pcitc.fms.bll.itf.MeasurementService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.MeasurementDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.RelationsDao;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.Relations;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

import org.springframework.data.domain.Example;

/**
 * The type Measurement service.
 */
@Service
@Component
@SuppressWarnings("unchecked")
public class MeasurementServiceImpl implements MeasurementService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(MeasurementServiceImpl.class);
	/**
	 * The Measurement dao.
	 */
	@Autowired
	private MeasurementDao measurementDao;




	/**
	 * Gets page measurements.
	 *
	 * @param measurementModel the measurement model
	 * @param pageable the pageable
	 * @return the page measurements
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional
	public Pager<com.pcitc.fms.bll.entity.Measurement> getPageMeasurements(com.pcitc.fms.service.model.Measurement measurementModel, Pageable pageable)
			throws BusinessException {
		MyPageImpl properties = null;
		List<Measurement> propertiesEntity = null;
		try {
			properties = measurementDao.findPageMeasurements(measurementModel, pageable); 
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			propertiesEntity = ObjectConverter.listConverter(properties.getContent(), Measurement.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Measurement> pageData = new Pager<>();
		pageData.setContent(propertiesEntity);
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
