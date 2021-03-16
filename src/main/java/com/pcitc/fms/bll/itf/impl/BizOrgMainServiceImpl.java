/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: BizOrgMainServiceImpl
 * Date:18-3-8 下午6:20
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.TPmBizOrgMain;
import com.pcitc.fms.bll.itf.BizOrgMainService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgMainDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: BizOrgMainServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class BizOrgMainServiceImpl implements BizOrgMainService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(BizOrgMainServiceImpl.class);
	/**
	 * The Biz org main dao.
	 */

	@Autowired
	private BizOrgMainDao bizOrgMainDao;

	/**
	 * Gets biz org mains.
	 *
	 * @param bizOrgMainModel
	 *            the biz org main model
	 * @param pageable
	 *            the pageable
	 * @return the biz org mains
	 * @throws BusinessException
	 *             the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<TPmBizOrgMain> getBizOrgMains(com.pcitc.fms.service.model.TPmBizOrgMain bizOrgMainModel,
			Pageable pageable) throws BusinessException {
		Pager<TPmBizOrgMain> pageData = new Pager<>();
		MyPageImpl properties = null;
		List<TPmBizOrgMain> EntityList = new ArrayList<>();
		try {
			properties = bizOrgMainDao.findBizOrgMains(bizOrgMainModel, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), TPmBizOrgMain.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			log.error("fail", e);
			throw new BusinessException("", "", e.getMessage());
		}
		pageData.setContent(EntityList);
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
	 * Gets biz org main by code.
	 *
	 * @param bizCode
	 *            the biz code
	 * @return the biz org main by code
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public TPmBizOrgMain getBizOrgMainByCode(String bizCode) throws BusinessException {
		com.pcitc.fms.dal.pojo.TPmBizOrgMain bizOrgMain = bizOrgMainDao.getBizOrgMainByBizCode(bizCode);
		if (null == bizOrgMain) {
			throw new BusinessException("", bizCode, ":不存在该业务域编码");
		}
		try {
			return ObjectConverter.entityConverter(bizOrgMain, TPmBizOrgMain.class);
		} catch (Exception e) {
			log.error("fail", e);
			throw new BusinessException("", "", e.getMessage());
		}
	}


}
