/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OutletServiceImpl
 * Date:18-3-8 下午6:23
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.pcitc.fms.bll.itf.OutletService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.NodeTypeDao;
import com.pcitc.fms.dal.dao.OutletDao;
import com.pcitc.fms.dal.dao.SignboardTypeDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.NodeType;
import com.pcitc.fms.dal.pojo.SignboardType;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Outlet;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Outlet service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01  类说明
 * @author:
 */
@Service
@Component
@SuppressWarnings("unchecked")
public class OutletServiceImpl implements OutletService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(OutletServiceImpl.class);
	/**
	 * The Outlet dao.
	 */
	@Autowired
	private OutletDao outletDao;

	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public  Pager<com.pcitc.fms.bll.entity.Outlet> getPageOutlets(Outlet outletModel,Pageable pageable) throws BusinessException {
		System.out.println(outletModel);
		
		
		MyPageImpl properties=  null;
		try {
			properties = outletDao.findPageOutlets(outletModel,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<com.pcitc.fms.bll.entity.Outlet> propertiesEntity= null;
		try {
			propertiesEntity = ObjectConverter.listConverter(properties.getContent(), com.pcitc.fms.bll.entity.Outlet.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Outlet> pageData = new Pager<>();
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


