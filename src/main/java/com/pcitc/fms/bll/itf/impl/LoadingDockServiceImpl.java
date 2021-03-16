/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: LoadingDockServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.LoadingDockDaoImpl;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Loading dock service.
 */
@Service
public class LoadingDockServiceImpl implements LoadingDockService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(LoadingDockServiceImpl.class);
	/**
	 * The Loading dock dao.
	 */
	@Autowired
	private LoadingDockDao loadingDockDao;
	/**
	 * Gets loading docks by model.
	 *
	 * @param loadingDockModel the loading dock model
	 * @param pageable the pageable
	 * @return the loading docks by model
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<com.pcitc.fms.bll.entity.LoadingDock> getLoadingDocksByModel(com.pcitc.fms.service.model.LoadingDock loadingDockModel, Pageable pageable) throws BusinessException {
		MyPageImpl properties  = null;
		List<LoadingDock> loadingDockEntityList = null;
		try {
			properties = loadingDockDao.getLoadingDocksByModel(loadingDockModel ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			loadingDockEntityList = ObjectConverter.listConverter(properties.getContent(),LoadingDock.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.LoadingDock> pageData = new Pager<>();
		pageData.setContent(loadingDockEntityList);
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
