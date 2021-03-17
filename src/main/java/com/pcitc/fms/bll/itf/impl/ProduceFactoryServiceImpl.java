/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ProduceFactoryServiceImpl
 * Date:18-3-8 下午6:23
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
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.ProduceFactory;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.ProduceFactoryDao;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: ProduceFactoryServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
//@Transactional(rollbackFor = BusinessException.class)
public class ProduceFactoryServiceImpl implements ProduceFactoryService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(ProduceFactoryServiceImpl.class);
	/**
	 * The Produce factory dao.
	 */
	@Autowired
	private  ProduceFactoryDao produceFactoryDao;
	/**
	 * The T pm org dao.
	 */
	@Autowired
	private TPmOrgDao tPmOrgDao;
	/**
	 * The Bizorgdtldao.
	 */
	@Autowired
	private BizOrgDTLDao bizorgdtldao;
	
	@Autowired
	private RentDao rentdao;

	/**
	 * Gets produce factories.
	 *
	 * @param produceFactoryModel the produce factory model
	 * @param pageable the pageable
	 * @return the produce factories
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<ProduceFactory> getProduceFactories(com.pcitc.fms.service.model.ProduceFactory produceFactoryModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.ProduceFactory>  pageData = new Pager<>();
		MyPageImpl properties = null;
		List<ProduceFactory> EntityList = null;
		try {
			properties = produceFactoryDao.findProduceFactories(produceFactoryModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),ProduceFactory.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}else{
				throw new BusinessException("", e.getMessage());
			}
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
	 * Gets produce factory by code.
	 *
	 * @param orgCode the org code
	 * @return the produce factory by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public ProduceFactory getProduceFactoryByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.ProduceFactory produceFactory =  produceFactoryDao.getProduceFactoryByOrgCode(orgCode);
			if(null == produceFactory){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(produceFactory, ProduceFactory.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}



}
