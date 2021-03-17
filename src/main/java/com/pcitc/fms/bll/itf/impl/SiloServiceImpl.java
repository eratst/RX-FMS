/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SiloServiceImpl
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

import com.pcitc.fms.bll.entity.Silo;
import com.pcitc.fms.bll.itf.SiloService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.SiloDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Silo service.
 */
@Service
public class SiloServiceImpl implements SiloService {

	/**
	 * The Silo dao.
	 */
	@Autowired
	private SiloDao siloDao;
	

	
	/**
	 * Gets page silos.
	 *
	 * @param modelStr the model str
	 * @param areaCode the area code
	 * @param pageable the pageable
	 * @return the page silos
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<Silo> getPageSilos(com.pcitc.fms.service.model.Silo modelStr, Pageable pageable) throws BusinessException {
		List<Silo> siloEntityList = new ArrayList<>();
		Pager<Silo> pageData = new Pager<>();
		MyPageImpl properties = null;
		try {
			properties = siloDao.findPageSilos(modelStr ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			siloEntityList = ObjectConverter.listConverter(properties.getContent(),Silo.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(siloEntityList);
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
