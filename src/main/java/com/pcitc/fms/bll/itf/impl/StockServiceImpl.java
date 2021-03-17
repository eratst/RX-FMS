/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StockServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
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

import com.pcitc.fms.bll.entity.Stock;
import com.pcitc.fms.bll.itf.StockService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.StockDao;
import com.pcitc.fms.dal.dao.WarehouseDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StockModelStr;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Stock service.
 */
@Service
public class StockServiceImpl implements StockService {

	

	@Autowired
	private StockDao stockDao;
	/**
	 * The Node dictionary dao.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Stock> getStocks(com.pcitc.fms.service.model.Stock modelStr, Pageable pageable)
			throws BusinessException {
		List<Stock> stockEntityList = new ArrayList<>();
		Pager<com.pcitc.fms.bll.entity.Stock> pageData = new Pager<>();
		MyPageImpl properties = null;
		try {
			properties = stockDao.findPageStocks(modelStr, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			stockEntityList = ObjectConverter.listConverter(properties.getContent(), Stock.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(stockEntityList);
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
